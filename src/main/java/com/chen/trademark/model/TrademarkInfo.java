package com.chen.trademark.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class TrademarkInfo {

    public TrademarkInfo() {
        trademarks = new ArrayList<>();
    }

    private List<Map<String, String>> trademarks;

    private Long count = 0l;

    public void size() {
        trademarks.size();
    }
}
