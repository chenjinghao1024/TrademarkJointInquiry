package com.chen.trademark.service;

import com.chen.trademark.entity.FileRecord;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author Administrator
 */
@Resource
public interface IBatchService {
    boolean batchDeal(MultipartFile file) throws Exception;

    void batchQuery(int id);

    PageInfo<FileRecord> getFileByPage(int page, int pageSize);
}
