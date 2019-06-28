package com.chen.trademark.service.impl;

import com.chen.trademark.conf.UrlConfig;
import com.chen.trademark.entity.*;
import com.chen.trademark.mapper.FileRecordMapper;
import com.chen.trademark.mapper.TrademarkRecordMapper;
import com.chen.trademark.model.TrademarkInfo;
import com.chen.trademark.service.IBatchService;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.NodeList;

import javax.annotation.Resource;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Administrator
 */
@Service
public class BatchServiceImpl implements IBatchService {
    @Resource
    FileRecordMapper fileRecordMapper;
    @Resource
    TrademarkRecordMapper trademarkRecordMapper;

    @Autowired
    private UrlConfig urlConfig;

    static SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Override
    public boolean batchDeal(MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();
        FileRecord fileRecord = new FileRecord(fileName);
        fileRecordMapper.insert(fileRecord);

        List<TrademarkRecord> li = dealFile(file);

        li.forEach(trademarkRecord -> {
            trademarkRecord.setFileId(fileRecord.getId());
            trademarkRecordMapper.insert(trademarkRecord);
        });
        return false;
    }

    @Override
    public void batchQuery() {

        FileRecordExample fileE = new FileRecordExample();
        FileRecordExample.Criteria fileECriteria = fileE.createCriteria();
        fileECriteria.andStateEqualTo(0);
        List<FileRecord> files = fileRecordMapper.selectByExample(fileE);
        if (CollectionUtils.isNotEmpty(files)) {
            FileRecord file = files.get(0);
            file.setState(1);
            fileRecordMapper.updateByPrimaryKeySelective(file);
            TrademarkRecordExample example = new TrademarkRecordExample();
            TrademarkRecordExample.Criteria criteria = example.createCriteria();
            criteria.andFileIdEqualTo(file.getId());

            List<TrademarkRecord> trademarkRecords = trademarkRecordMapper.selectByExample(example);
            try {

                batchSearchFromUSPTO(trademarkRecords);
            } catch (Exception e) {
                e.printStackTrace();
            }
            file.setState(2);
            fileRecordMapper.updateByPrimaryKeySelective(file);
            batchQuery();
        }


    }

    @Override
    public PageInfo<FileRecord> getFileByPage(PageParams params) throws ParseException {

        FileRecordExample example = new FileRecordExample();
        FileRecordExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("upload_time desc");
        Map<String, String> searchMap = params.getSearchMap();
        if (!params.getSearchMap().isEmpty()) {
            String fileName = searchMap.get("fileName");
            if (!fileName.isEmpty()) {
                criteria.andFileNameLike("%"+fileName+"%");
            }
            String startDate = searchMap.get("startDate");
            String endDate = searchMap.get("endDate");
            if (!startDate.isEmpty()){
                criteria.andUploadTimeGreaterThanOrEqualTo(format.parse(startDate));
            }
            if (!endDate.isEmpty()){
                criteria.andUploadTimeLessThanOrEqualTo(format.parse(endDate));
            }
        }

        return PageHelper.startPage(params.getPageNumber(), params.getPageSize()).
                doSelectPageInfo(() -> fileRecordMapper.selectByExample(example));
    }

    @Override
    public PageInfo<FileRecord> getMarkByPage(PageParams params,Integer fileId) {
        TrademarkRecordExample example = new TrademarkRecordExample();

        TrademarkRecordExample.Criteria criteria = example.createCriteria();
        criteria.andFileIdEqualTo(fileId);

        Map<String, String> searchMap = params.getSearchMap();
        if (!params.getSearchMap().isEmpty()) {
            String trademarkName = searchMap.get("trademarkName");
            if (!trademarkName.isEmpty()) {
                criteria.andTrademarkNameLike("%" + trademarkName + "%");
            }
        }

        return PageHelper.startPage(params.getPageNumber(), params.getPageSize()).
                doSelectPageInfo(()-> trademarkRecordMapper.selectByExample(example));
    }

    private void batchSearchFromUSPTO(List<TrademarkRecord> trademarkRecords) throws Exception {
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setCssEnabled(false);
        //设置链接地址
        HtmlPage page = webClient.getPage(urlConfig.getUSPTO());

        // 获取所有 锚点标签
        List<HtmlAnchor> anchors = page.getAnchors();
        AtomicReference<HtmlPage> pageAtomicReference = new AtomicReference<>();
        // 遍历链接
        for (HtmlAnchor anchor : anchors) {
            if ("Basic Word Mark Search (New User)".equals(anchor.getTextContent())) {
                // 点击该链接 获取点击后页面
                pageAtomicReference.set(anchor.click());
            }
        }

        if (pageAtomicReference.get() != null) {
            HtmlPage searchPage = pageAtomicReference.get();
            String searchPageURL = searchPage.getBaseURL().toString();
            System.out.println(searchPageURL);
            Long count = null;
            for (TrademarkRecord trademarkRecord : trademarkRecords) {
                try{
                    searchPage = webClient.getPage(searchPageURL);
                    // 获取查询表单
                    HtmlForm form = searchPage.getFormByName("search_text");
                    // 获取查询输入框
                    HtmlInput nameInput = form.getInputByName("p_s_PARA2");
                    // 将所查询商标输入
                    nameInput.setValueAttribute(trademarkRecord.getTrademarkName());

                    // 该页面提交按钮有多个  获取列表
                    List<HtmlInput> inputs = form.getInputsByValue("Submit Query");

                    HtmlInput submit_query = null;
                    // 遍历判断 是否为提交按钮 (有一个是 hidden)
                    // 获取提交按钮
                    for (HtmlInput input : inputs) {
                        if (input instanceof HtmlSubmitInput) {
                            submit_query = input;
                            break;
                        }
                    }
                    // 获取点击后页面
                    HtmlPage resultPage = submit_query.click();

                    if (resultPage != null) {
                        // 获取table
                        NodeList table = resultPage.getElementsByTagName("table");
                        HtmlTable resultTable = (HtmlTable) table.item(2);

                        HtmlTableRow row = resultTable.getRow(0);
                        HtmlTableCell cell = row.getCell(row.getCells().size() - 1);
                        String countStr = cell.getTextContent().split(" ")[0].trim();
                        if (StringUtils.isNotBlank(countStr)) {
                            count = Long.parseLong(countStr);
                        } else {
                            for (DomElement e : resultPage.getBody().getChildElements()) {
                                if (e.getTagName().equals("font")) {
                                    count = Long.parseLong(e.getTextContent().trim().split(" ")[1]);
                                    break;
                                }
                            }
                        }
                    }
                }catch (Exception e){
                    count = 0L;
                }

                Long uk = searchFromUKByChrome(trademarkRecord.getTrademarkName()).getCount();
                Long dpma = searchFromDPMA(trademarkRecord.getTrademarkName()).getCount();

                trademarkRecord.setUk(Math.toIntExact(uk));
                trademarkRecord.setDpma(Math.toIntExact(dpma));
                trademarkRecord.setUspto(Math.toIntExact(count));
                trademarkRecordMapper.updateByPrimaryKeySelective(trademarkRecord);
                Random ra = new Random();

                ;
                Thread.sleep((ra.nextInt(5) + 1) * 3000);

            }


        }
        //关闭所有窗口
        webClient.close();
    }

    private TrademarkInfo searchFromDPMA(String trademarkName) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        WebDriver driver = new ChromeDriver(chromeOptions);
        TrademarkInfo trademarkInfo = new TrademarkInfo();
        try {
            driver.get(urlConfig.getDPMA());

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            WebElement searchInput = driver.findElement(new By.ById("marke"));
            WebElement searchBtn = driver.findElement(new By.ById("rechercheStarten"));

            searchInput.sendKeys(trademarkName);
            searchBtn.click();


            List<WebElement> elements = driver.findElement(new By.ById("form")).findElements(new By.ByTagName("p"));

            String resultInfo = "";
            for (WebElement element : elements) {
                String content = element.getText().trim();
                if (content.startsWith("Trefferliste") || content.startsWith("Result list")) {
                    resultInfo = content;
                    break;
                }
            }
            String count = resultInfo.split(" ")[1];
            trademarkInfo.setCount(Long.parseLong(count));
        } catch (Exception e) {

        }finally {
            driver.quit();
        }
        return trademarkInfo;
    }

    private TrademarkInfo searchFromUKByChrome(String trademarkName) throws Exception {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        WebDriver driver = new ChromeDriver(chromeOptions);
        TrademarkInfo trademarkInfo = new TrademarkInfo();

        try {
            driver.get(urlConfig.getUK());

            driver.navigate().refresh();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            WebElement searchInput = driver.findElement(new By.ByName("wordSearchPhrase"));
            WebElement searchBtn = driver.findElement(new By.ById("button"));
            searchInput.sendKeys(trademarkName);
            searchBtn.click();
            Thread.sleep(1000);
            String result = driver.findElement(new By.ByClassName("heading-medium")).getText();
            String count = result.split(" ")[3];

            trademarkInfo.setCount(Long.parseLong(count));

        } catch (Exception e) {

        }finally {
            driver.quit();
        }
        return trademarkInfo;

    }


    private List dealFile(MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        InputStream is = file.getInputStream();
        Workbook workbook;
        if (".xls".equals(fileType)) {
            workbook = new HSSFWorkbook(is);
        } else if (".xlsx".equals(fileType)) {
            workbook = new XSSFWorkbook(is);
        } else {
            throw new Exception("请上传excel文件！");
        }

        Sheet sheet = null;
        Row row = null;
        Cell cell = null;
        ArrayList list = new ArrayList();
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            sheet = workbook.getSheetAt(i);
            if (sheet == null) {
                continue;
            }

            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                row = sheet.getRow(j);
                if (row == null || row.getFirstCellNum() == j) {
                    continue;
                }

                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    cell = row.getCell(y);
                    String trademark = cell.getStringCellValue();
                    if (StringUtils.isNotBlank(trademark)) {
                        TrademarkRecord record = new TrademarkRecord(cell.getStringCellValue());
                        list.add(record);
                    }
                }
            }
        }
        is.close();
        return list;
    }


}
