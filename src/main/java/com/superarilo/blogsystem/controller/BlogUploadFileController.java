package com.superarilo.blogsystem.controller;

import com.superarilo.blogsystem.service.BlogUploadFileService;
import com.superarilo.utils.JsonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/upload")
public class BlogUploadFileController {

    private final BlogUploadFileService blogUploadFileService;

    public BlogUploadFileController(BlogUploadFileService blogUploadFileService) {
        this.blogUploadFileService = blogUploadFileService;
    }

    @PostMapping("/image")
    public JsonResult uploadImage(@RequestParam(value = "file") MultipartFile file,
                                  HttpServletRequest request){
        if (file.isEmpty()) return JsonResult.OK("上传的数据为空");
        return blogUploadFileService.uploadFile(file, request);
    }
}
