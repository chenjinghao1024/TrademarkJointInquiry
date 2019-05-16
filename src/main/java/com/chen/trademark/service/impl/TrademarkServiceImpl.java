package com.chen.trademark.service.impl;

import com.chen.trademark.conf.UrlConfig;
import com.chen.trademark.model.TrademarkInfo;
import com.chen.trademark.service.ITrademarkService;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.NodeList;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class TrademarkServiceImpl implements ITrademarkService {

    @Autowired
    private UrlConfig urlConfig;

    @Override
    public TrademarkInfo searchTrademarkNameFromUSPTO(String trademarkName) {
        try {
            return searchFromUSPTO(trademarkName);
        } catch (Exception e) {
            e.printStackTrace();
            return new TrademarkInfo();
        }
    }

    @Override
    public TrademarkInfo searchTrademarkFromEUIPO(String trademarkName) {
        try {
            return searchFromEUIPO(trademarkName);
        } catch (Exception e) {
            e.printStackTrace();
            return new TrademarkInfo();
        }
    }

    @Override
    public TrademarkInfo searchTrademarkFromUK(String trademarkName) {
        try {
            return searchFromUK(trademarkName);
        } catch (Exception e) {
            e.printStackTrace();
            return new TrademarkInfo();
        }
    }

    @Override
    public TrademarkInfo searchTrademarkFromDPMA(String trademarkName) {
        try {
            return searchFromDPMA(trademarkName);
        } catch (Exception e) {
            e.printStackTrace();
            return new TrademarkInfo();
        }
    }

    private TrademarkInfo searchFromDPMA(String trademarkName) throws Exception {
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

//        Thread.sleep(1000*3);

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
            throw new Exception();
        }finally {
            driver.quit();
        }
        return trademarkInfo;
    }

    private TrademarkInfo searchFromEUIPO(String trademarkName) throws Exception {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get(urlConfig.getEUIPO());

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement searchInput = driver.findElement(new By.ById("basicSearchBigInput"));
        WebElement searchBtn = driver.findElement(new By.ById("basicSearchBigButton"));

        searchInput.sendKeys(trademarkName);
        searchBtn.click();
//        Thread.sleep(5 * 1000);
        List<WebElement> webElements = driver.findElements(new By.ByClassName("searchInfo"));

        String resultInfo = "";
        for (WebElement webElement : webElements) {
            String text = webElement.getText().trim();
            if (text.endsWith("seconds")) {
                resultInfo = text;
                break;
            }
        }

        String count = resultInfo.split(" ")[0];
        TrademarkInfo trademarkInfo = new TrademarkInfo();
        trademarkInfo.setCount(Long.parseLong(count));

        driver.quit();
        return trademarkInfo;
    }

    private TrademarkInfo searchFromUK(String trademarkName) throws Exception {
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setCssEnabled(false);

        HtmlPage searchPage = webClient.getPage(urlConfig.getUK());

        HtmlInput searchInput = (HtmlInput) searchPage.getElementsByName("wordSearchPhrase").get(0);
        searchInput.setValueAttribute(trademarkName);

        HtmlButton searchButton = (HtmlButton) searchPage.getElementById("button");
        HtmlPage resultPage = searchButton.click();

        String count=resultPage.getElementsByTagName("h2").get(0).getTextContent().split(" ")[3];
        System.out.println(count);
        TrademarkInfo trademarkInfo = new TrademarkInfo();

        trademarkInfo.setCount(Long.parseLong(count));

        return trademarkInfo;
    }

    private TrademarkInfo searchFromUSPTO(String trademarkName) throws Exception {
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

        TrademarkInfo trademarkInfo = new TrademarkInfo();

        if (pageAtomicReference.get() != null) {
            HtmlPage searchPage = pageAtomicReference.get();
            // 获取查询表单
            HtmlForm form = searchPage.getFormByName("search_text");
            // 获取查询输入框
            HtmlInput nameInput = form.getInputByName("p_s_PARA2");
            // 将所查询商标输入
            nameInput.setValueAttribute(trademarkName);

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
                String count = cell.getTextContent().split(" ")[0].trim();
                trademarkInfo.setCount(Long.parseLong(count));
            }

                // 获取数据及条数
//            if (resultPage != null) {
//                // 获取table
//                NodeList table = resultPage.getElementsByTagName("table");
//                HtmlTable resultTable = (HtmlTable) table.item(5);
//                List<HtmlTableRow> rows = resultTable.getRows();
//                trademarkInfo.setCount(rows.size() - 1);
//                List<HtmlTableCell> headers = rows.get(0).getCells();
//                for (int rowIndex = 1; rowIndex < rows.size(); rowIndex++) {
//                    HtmlTableRow row = rows.get(rowIndex);
//                    List<HtmlTableCell> cells = row.getCells();
//                    HashMap<String, String> map = new HashMap<>();
//                    for (int cellIndex = 0; cellIndex < cells.size(); cellIndex++) {
//                        String header = headers.get(cellIndex).getTextContent();
//                        header=StringUtils.isEmpty(header) ? "" : header;
//                        map.put(header, cells.get(cellIndex).getTextContent());
//                    }
//                    trademarkInfo.getTrademarks().add(map);
//                }
//            }
            }

        //关闭所有窗口
        webClient.close();
        return trademarkInfo;
    }

}

