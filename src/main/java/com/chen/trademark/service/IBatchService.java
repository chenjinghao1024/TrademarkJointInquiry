package com.chen.trademark.service;

import com.chen.trademark.entity.FileRecord;
import com.chen.trademark.entity.PageParams;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.text.ParseException;

/**
 * @author Administrator
 */
@Resource
public interface IBatchService {
    boolean batchDeal(MultipartFile file) throws Exception;

    void batchQuery();

    PageInfo<FileRecord> getFileByPage(PageParams params) throws ParseException;

    PageInfo<FileRecord> getMarkByPage(PageParams params,Integer fileId);
}
