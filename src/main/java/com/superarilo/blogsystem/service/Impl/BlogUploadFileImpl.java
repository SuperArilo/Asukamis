package com.superarilo.blogsystem.service.Impl;

import com.auth0.jwt.JWT;
import com.superarilo.blogsystem.mapper.BlogFileMapper;
import com.superarilo.blogsystem.service.BlogUploadFileService;
import com.superarilo.entity.BlogImages;
import com.superarilo.utils.InputCheck;
import com.superarilo.utils.JsonResult;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

@Service
public class BlogUploadFileImpl implements BlogUploadFileService {

    private final BlogFileMapper blogFileMapper;

    public BlogUploadFileImpl(BlogFileMapper blogFileMapper) {
        this.blogFileMapper = blogFileMapper;
    }

    @SneakyThrows
    @Override
    public JsonResult uploadFile(MultipartFile file, HttpServletRequest request) {
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        if(!Arrays.asList(InputCheck.imageSuffix).contains(suffix.toUpperCase())){
            return JsonResult.Error(400,"图片格式不匹配");
        }
        String newFileName = UUID.randomUUID() + "." + suffix;
        file.transferTo(new File(InputCheck.imagePath, newFileName));
        BlogImages blogImages = new BlogImages();
        blogImages.setCreateTime(new Date());
        blogImages.setUploader(JWT.decode(request.getHeader("token")).getClaim("uid").asLong());
        blogImages.setImName(newFileName);
        blogImages.setImPath(InputCheck.imagePath + newFileName);
        blogFileMapper.insert(blogImages);
        return JsonResult.OK("上传成功", request.getRequestURL().toString().replace(request.getRequestURI(),"") + "/image/" + newFileName);
    }
}
