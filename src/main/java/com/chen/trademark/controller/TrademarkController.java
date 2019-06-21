package com.chen.trademark.controller;

import com.chen.trademark.conf.UrlConfig;
import com.chen.trademark.model.SearchTrademarkResponse;
import com.chen.trademark.model.TrademarkInfo;
import com.chen.trademark.service.ITrademarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trademark")
public class TrademarkController {

    @Autowired
    ITrademarkService trademarkService;
    @Autowired
    UrlConfig urlConfig;

    @PostMapping
    @RequestMapping("/getAllUrl")
    public UrlConfig getAllUrl() {
        return urlConfig;
    }

    @PostMapping
    @RequestMapping("/searchFromUSPTO")
    public SearchTrademarkResponse searchTrademarkFromUSPTO(String trademarkName) {
        SearchTrademarkResponse response = new SearchTrademarkResponse(trademarkName, "USPTO");
        response.setTrademarkName(trademarkName);
        TrademarkInfo trademarkInfo = trademarkService.searchTrademarkNameFromUSPTO(trademarkName);
        response.setTrademarkInfo(trademarkInfo);
        return response;
    }

    @PostMapping
    @RequestMapping("/searchTrademarkFromEUIPO")
    public SearchTrademarkResponse searchTrademarkFromEUIPO(String trademarkName) {
        SearchTrademarkResponse response = new SearchTrademarkResponse(trademarkName, "EUIPO");
        response.setTrademarkName(trademarkName);
        TrademarkInfo trademarkInfo = trademarkService.searchTrademarkFromEUIPO(trademarkName);
        response.setTrademarkInfo(trademarkInfo);
        return response;
    }

    @PostMapping
    @RequestMapping("/searchTrademarkFromUK")
    public SearchTrademarkResponse searchTrademarkFromUK(String trademarkName) {
        SearchTrademarkResponse response = new SearchTrademarkResponse(trademarkName, "UK");
        response.setTrademarkName(trademarkName);
        TrademarkInfo trademarkInfo = trademarkService.searchTrademarkFromUK(trademarkName);
        response.setTrademarkInfo(trademarkInfo);
        return response;
    }

    @PostMapping
    @RequestMapping("/searchTrademarkFromDPMA")
    public SearchTrademarkResponse searchTrademarkFromDPMA(String trademarkName) {
        SearchTrademarkResponse response = new SearchTrademarkResponse(trademarkName, "DPMA");
        response.setTrademarkName(trademarkName);
        TrademarkInfo trademarkInfo = trademarkService.searchTrademarkFromDPMA(trademarkName);
        response.setTrademarkInfo(trademarkInfo);
        return response;
    }

    @PostMapping
    @RequestMapping("/searchTrademarkFromINPI")
    public SearchTrademarkResponse searchTrademarkFromINPI(String trademarkName) {
        SearchTrademarkResponse response = new SearchTrademarkResponse(trademarkName, "INPI");
        response.setTrademarkName(trademarkName);
        TrademarkInfo trademarkInfo = trademarkService.searchTrademarkFromINPI(trademarkName);
        response.setTrademarkInfo(trademarkInfo);
        return response;
    }

    @PostMapping
    @RequestMapping("/searchTrademarkFromUIBM")
    public SearchTrademarkResponse searchTrademarkFromUIBM(String trademarkName) {
        SearchTrademarkResponse response = new SearchTrademarkResponse(trademarkName, "INPI");
        response.setTrademarkName(trademarkName);
        TrademarkInfo trademarkInfo = trademarkService.searchTrademarkFromUIBM(trademarkName);
        response.setTrademarkInfo(new TrademarkInfo());
        return response;
    }

    @PostMapping
    @RequestMapping("/searchTrademarkFromOEPM")
    public SearchTrademarkResponse searchTrademarkFromOEPM(String trademarkName) {
        SearchTrademarkResponse response = new SearchTrademarkResponse(trademarkName, "OEPM");
        response.setTrademarkName(trademarkName);
        TrademarkInfo trademarkInfo = trademarkService.searchTrademarkFromOEPM(trademarkName);
        response.setTrademarkInfo(trademarkInfo);
        return response;
    }

    @PostMapping
    @RequestMapping("/searchTrademarkFromJP")
    public SearchTrademarkResponse searchTrademarkFromJP(String trademarkName) {
        SearchTrademarkResponse response = new SearchTrademarkResponse(trademarkName, "JP");
        response.setTrademarkName(trademarkName);
        TrademarkInfo trademarkInfo = trademarkService.searchTrademarkFromJP(trademarkName);
        response.setTrademarkInfo(trademarkInfo);
        return response;
    }

    @PostMapping
    @RequestMapping("/searchTrademarkFromWIPO")
    public SearchTrademarkResponse searchTrademarkFromWIPO(String trademarkName) {
        SearchTrademarkResponse response = new SearchTrademarkResponse(trademarkName, "WIPO");
        response.setTrademarkName(trademarkName);
        TrademarkInfo trademarkInfo = trademarkService.searchTrademarkFromWIPO(trademarkName);
        response.setTrademarkInfo(trademarkInfo);
        return response;
    }

}
