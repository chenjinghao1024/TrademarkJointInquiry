package com.chen.trademark.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TrademarkInfo {

    public TrademarkInfo() {
        trademarks = new ArrayList<>();
    }

    private List<Map<String, String>> trademarks;

    private Long count;

    public List<Map<String, String>> getTrademarks() {
        return trademarks;
    }

    public void setTrademarks(List<Map<String, String>> trademarks) {
        this.trademarks = trademarks;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public void size() {
        trademarks.size();
    }
}
