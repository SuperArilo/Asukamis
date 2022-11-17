package com.superarilo.blogsystem.service;

import com.superarilo.utils.JsonResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface BlogUploadFileService {
    JsonResult uploadFile(MultipartFile file, HttpServletRequest request);
}
