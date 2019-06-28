package com.chen.trademark.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * 
 *
 * @author Mr.D
 * @date 2019/06/27
 */
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class TrademarkRecord implements Serializable {

    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 文件ID
     */
    private Integer fileId;

    /**
     * 商标名称
     */
    @NonNull
    private String trademarkName;

    /**
     * 美国
     */
    private Integer uspto;

    /**
     * 英国
     */
    private Integer uk;

    /**
     * 欧洲
     */
    private Integer euipo;

    /**
     * 德国
     */
    private Integer dpma;

    /**
     * 法国
     */
    private Integer inpi;

    /**
     * 西班牙
     */
    private Integer oepm;

    /**
     * 日本
     */
    private Integer jp;

    /**
     * 
     */
    private Integer wipo;

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
}