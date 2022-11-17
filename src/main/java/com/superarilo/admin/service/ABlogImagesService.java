package com.superarilo.admin.service;

import com.superarilo.utils.JsonResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;

@Service
public interface ABlogImagesService{
    JsonResult uploadFiles(MultipartFile file, HttpServletRequest request);
}
