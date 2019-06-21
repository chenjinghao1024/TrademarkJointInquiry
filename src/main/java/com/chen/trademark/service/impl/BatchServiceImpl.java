package com.chen.trademark.service.impl;

import com.chen.trademark.conf.UrlConfig;
import com.chen.trademark.entity.*;
import com.chen.trademark.mapper.FileRecordMapper;
import com.chen.trademark.mapper.TrademarkRecordMapper;
import com.chen.trademark.mapper.TrademarkResultMapper;
import com.chen.trademark.model.TrademarkInfo;
import com.chen.trademark.service.IBatchService;
import com.chen.trademark.util.UrlType;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.NodeList;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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
    @Resource
    TrademarkResultMapper trademarkResultMapper;

    @Autowired
    private UrlConfig urlConfig;

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
    public void batchQuery(int id) {
        FileRecord file = fileRecordMapper.selectByPrimaryKey(id);
        TrademarkRecordExample example = new TrademarkRecordExample();
        TrademarkRecordExample.Criteria criteria = example.createCriteria();
        criteria.andFileIdEqualTo(id);

        List<TrademarkRecord> trademarkRecords = trademarkRecordMapper.selectByExample(example);
        try {
            batchSearchFromUSPTO(trademarkRecords);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public PageInfo<FileRecord> getFileByPage(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);

        FileRecordExample example = new FileRecordExample();
        example.setOrderByClause("upload_time desc");
        List<FileRecord> li = fileRecordMapper.selectByExample(example);

        return new PageInfo<>(li);
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
                TrademarkResult result = new TrademarkResult(trademarkRecord.getId(), UrlType.USPTO.value(), count);
                trademarkResultMapper.insertSelective(result);
            }


        }
        //关闭所有窗口
        webClient.close();
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

            for (int j = sheet.getFirstRowNum() + 1; j <= sheet.getLastRowNum(); j++) {
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
