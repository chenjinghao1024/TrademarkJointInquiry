package com.chen.trademark.service;

import com.chen.trademark.model.TrademarkInfo;

import javax.annotation.Resource;
import java.io.IOException;

@Resource
public interface ITrademarkService {

    TrademarkInfo searchTrademarkNameFromUSPTO(String trademarkName);

    TrademarkInfo searchTrademarkFromEUIPO(String trademarkName);

    TrademarkInfo searchTrademarkFromUK(String trademarkName);

    TrademarkInfo searchTrademarkFromDPMA(String trademarkName);
}
