package com.chen.trademark.controller;

import com.chen.trademark.entity.FileRecord;
import com.chen.trademark.entity.PageParams;
import com.chen.trademark.service.IBatchService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;
import java.text.ParseException;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("/batch")
public class BatchQueryController {

    @Resource
    private IBatchService batchService;

    @ResponseBody
    @PostMapping(value = "/files")
    public PageInfo<FileRecord> getFileByPage(PageParams params) throws ParseException {
        return batchService.getFileByPage(params);
    }

    @ResponseBody
    @PostMapping(value = "/marks")
    public PageInfo<FileRecord> getMarkByPage(PageParams params,Integer fileId) {
        return batchService.getMarkByPage( params,fileId);
    }


    @ResponseBody
    @PostMapping(value = "/upload")
    public boolean uploadExcel(MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return false;
        }
        InputStream inputStream = file.getInputStream();
        boolean success = batchService.batchDeal(file);
        inputStream.close();

        return success;
    }


    @PostMapping(value = "/query")
    @ResponseBody
    public boolean batchQuery() throws Exception {
        batchService.batchQuery();
        return true;
    }
}
