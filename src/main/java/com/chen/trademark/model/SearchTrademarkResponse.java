package com.chen.trademark.model;

public class SearchTrademarkResponse {

    public SearchTrademarkResponse() {
    }

    public SearchTrademarkResponse(String trademarkName, String trademarkOffice) {
        this.trademarkName = trademarkName;
        this.trademarkOffice = trademarkOffice;
    }

    private String trademarkName;

    private TrademarkInfo trademarkInfo;

    private String trademarkOffice;

    public String getTrademarkName() {
        return trademarkName;
    }

    public void setTrademarkName(String trademarkName) {
        this.trademarkName = trademarkName;
    }

    public TrademarkInfo getTrademarkInfo() {
        return trademarkInfo;
    }

    public void setTrademarkInfo(TrademarkInfo trademarkInfo) {
        this.trademarkInfo = trademarkInfo;
    }

    public String getTrademarkOffice() {
        return trademarkOffice;
    }

    public void setTrademarkOffice(String trademarkOffice) {
        this.trademarkOffice = trademarkOffice;
    }
}
