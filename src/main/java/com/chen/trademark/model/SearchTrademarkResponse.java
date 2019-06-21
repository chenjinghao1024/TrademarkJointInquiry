package com.chen.trademark.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchTrademarkResponse {

    public SearchTrademarkResponse(String trademarkName, String trademarkOffice) {
        this.trademarkName = trademarkName;
        this.trademarkOffice = trademarkOffice;
    }

    private String trademarkName;

    private TrademarkInfo trademarkInfo;

    private String trademarkOffice;
}
