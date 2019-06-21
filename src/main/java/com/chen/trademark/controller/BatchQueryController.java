package com.chen.trademark.controller;

import com.chen.trademark.entity.FileRecord;
import com.chen.trademark.service.IBatchService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;

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
    public PageInfo<FileRecord> getFileByPage(int pageSize, int page) {
        return batchService.getFileByPage(page, pageSize);
    }

    @ResponseBody
    @PostMapping(value = "/upload")
    public String uploadExcel(HttpServletRequest request) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        MultipartFile file = multipartRequest.getFile("filename");
        if (file.isEmpty()) {
            return "文件不能为空";
        }
        InputStream inputStream = file.getInputStream();
        boolean success = batchService.batchDeal(file);
        inputStream.close();

        return "上传成功";
    }


    @PostMapping(value = "/batchQuery")
    @ResponseBody
    public String batchQuery(int id) throws Exception {
        batchService.batchQuery(id);
        return "";
    }

}
