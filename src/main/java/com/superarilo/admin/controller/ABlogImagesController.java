package com.superarilo.admin.controller;

import com.superarilo.admin.service.ABlogImagesService;
import com.superarilo.utils.JsonResult;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/system/image")
public class ABlogImagesController {

    private final ABlogImagesService aBlogImagesService;

    public ABlogImagesController(@Qualifier("ABlogImagesServiceImpl") ABlogImagesService aBlogImagesService) {
        this.aBlogImagesService = aBlogImagesService;
    }

    @PostMapping("/upload")
    public JsonResult uploadImages(@RequestParam(value = "file") MultipartFile file,
                                   HttpServletRequest request){
        if (file.isEmpty()) return JsonResult.OK("上传的数据为空");
        return aBlogImagesService.uploadFiles(file, request);
    }
}
