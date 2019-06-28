package com.chen.trademark.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 *
 * @author Mr.D
 * @date 2019/06/19
 */
@Data
@NoArgsConstructor
public class FileRecord implements Serializable {

    public FileRecord(String fileName) {
        this.fileName = fileName;
        this.uploadTime = new Date();
        this.state = 0;
    }

    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 上传文件名称
     */
    private String fileName;

    /**
     * 上传时间
     */
    private Date uploadTime;

    /**
     * 查询状态 0:未开始 1:进行中 2:已完成 ...
     */
    private Integer state;

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
}