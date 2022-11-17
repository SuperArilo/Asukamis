package com.superarilo.admin.service.Impl;

import com.superarilo.admin.mapper.AdminBlogImagesMapper;
import com.superarilo.admin.service.ABlogImagesService;
import com.superarilo.entity.BlogImages;
import com.superarilo.utils.InputCheck;
import com.superarilo.utils.JsonResult;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

@Service
public class ABlogImagesServiceImpl implements ABlogImagesService {

    private final AdminBlogImagesMapper adminBlogImagesMapper;

    public ABlogImagesServiceImpl(AdminBlogImagesMapper adminBlogImagesMapper) {
        this.adminBlogImagesMapper = adminBlogImagesMapper;
    }

    @SneakyThrows
    @Override
    public JsonResult uploadFiles(MultipartFile file, HttpServletRequest request) {

        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        if(!Arrays.asList(InputCheck.imageSuffix).contains(suffix.toUpperCase())){
            return JsonResult.Error(400,"图片格式不匹配");
        }
        String newFileName = UUID.randomUUID() + "." + suffix;
        file.transferTo(new File(InputCheck.imagePath, newFileName));
        BlogImages blogImages = new BlogImages();
        blogImages.setCreateTime(new Date());
        blogImages.setUploader(129191756L);
        blogImages.setImName(newFileName);
        blogImages.setImPath(InputCheck.imagePath + newFileName);
        adminBlogImagesMapper.insert(blogImages);
        return JsonResult.OK("上传成功", request.getRequestURL().toString().replace(request.getRequestURI(),"") + "/image/" + newFileName);
    }
}
