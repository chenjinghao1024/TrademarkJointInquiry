package com.chen.trademark.service;

import com.chen.trademark.model.TrademarkInfo;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author Administrator
 */
@Resource
public interface ITrademarkService {

    /**
     * 美国商标局
     * @param trademarkName
     * @return 商标数量及相关信息
     */
    TrademarkInfo searchTrademarkNameFromUSPTO(String trademarkName);

    /**
     * 欧洲
     * @param trademarkName
     * @return 商标数量及相关信息
     */
    TrademarkInfo searchTrademarkFromEUIPO(String trademarkName);

    /**
     * 英国
     * @param trademarkName 商标名称
     * @return 商标数量及相关信息
     */
    TrademarkInfo searchTrademarkFromUK(String trademarkName);

    /**
     * 德国
     * @param trademarkName 商标名称
     * @return 商标数量及相关信息
     */
    TrademarkInfo searchTrademarkFromDPMA(String trademarkName);

    /**
     * 法国
     * @param trademarkName 商标名称
     * @return 商标数量及相关信息
     */
    TrademarkInfo searchTrademarkFromINPI(String trademarkName);

    /**
     * 意大利(存在验证码 ,略过)
     * @param trademarkName 商标名称
     * @return
     */
    TrademarkInfo searchTrademarkFromUIBM(String trademarkName);

    /**
     * 西班牙
     * @param trademarkName 商标名称
     * @return 商标数量及相关信息
     */
    TrademarkInfo searchTrademarkFromOEPM(String trademarkName);
    /**
     * 日本
     * @param trademarkName 商标名称
     * @return 商标数量及相关信息
     */
    TrademarkInfo searchTrademarkFromJP(String trademarkName);

    /**
     * WIPO
     * @param trademarkName 商标名称
     * @return 商标数量及相关信息
     */
    TrademarkInfo searchTrademarkFromWIPO(String trademarkName);
}
