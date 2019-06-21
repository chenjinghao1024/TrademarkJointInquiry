package com.chen.trademark.util;

/**
 * @author Administrator
 */

public enum UrlType {
    /**
     * 美国
     */
    USPTO(1),
    /**
     * 英国
     */
    UK(1),
    /**
     * 欧洲
     */
    EUIPO(1),
    /**
     * 德国
     */
    DPMA(1),
    /**
     * 法国
     */
    INPI(1),
    ;

    private Integer type;

    UrlType(int type) {
        this.type = type;
    }

    public Integer value(){
        return this.type;
    }
}
