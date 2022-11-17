package com.superarilo.admin.service.Impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.superarilo.admin.mapper.AdminBlogImagesMapper;
import com.superarilo.admin.vo.AdminBlogArticleVo;
import com.superarilo.admin.mapper.AdminBlogArticleMapper;
import com.superarilo.admin.service.ABlogArticleService;
import com.superarilo.blogsystem.mapper.BlogUserMapper;
import com.superarilo.entity.BlogArticle;
import com.superarilo.entity.BlogImages;
import com.superarilo.utils.InputCheck;
import com.superarilo.utils.JsonResult;
import com.superarilo.utils.PageCustom;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
public class ABlogArticleServiceImpl implements ABlogArticleService {

    private final AdminBlogArticleMapper adminBlogArticleMapper;

    private final BlogUserMapper blogUserMapper;

    private final AdminBlogImagesMapper adminBlogImagesMapper;

    public ABlogArticleServiceImpl(AdminBlogArticleMapper adminBlogArticleMapper, BlogUserMapper blogUserMapper, AdminBlogImagesMapper adminBlogImagesMapper) {
        this.adminBlogArticleMapper = adminBlogArticleMapper;
        this.blogUserMapper = blogUserMapper;
        this.adminBlogImagesMapper = adminBlogImagesMapper;
    }

    @Override
    public PageCustom<AdminBlogArticleVo> queryBlogArticle(Integer pageNum,
                                                           Integer pageSize,
                                                           String articleTitle,
                                                           String author,
                                                           String beforeTime,
                                                           String afterTime,
                                                           String sortName,
                                                           String sort) {
        Page<AdminBlogArticleVo> queryObject = adminBlogArticleMapper.aQueryBlogArticle(new Page<>(pageNum, pageSize), articleTitle, author, beforeTime, afterTime, sortName,sort);
        return new PageCustom<>(queryObject.getTotal(),
                queryObject.getRecords(),
                queryObject.getPages(),
                queryObject.getCurrent(),
                queryObject.getSize());
    }

    @Override
    public String createBlogArticle(Long uid, String articleTitle, String articleIntroduction, String articleContent) {
        if(!blogUserMapper.queryHaveUserByUID(uid)) return "此用户不存在";

        BlogArticle blogArticle = new BlogArticle();
        blogArticle.setPublisher(uid);
        blogArticle.setCreateTime(new Date());
        blogArticle.setArticleTitle(articleTitle);
        blogArticle.setArticleIntroduction(articleIntroduction);
        blogArticle.setArticleContent(articleContent);

        adminBlogArticleMapper.insert(blogArticle);

        return "文章新建成功";
    }

    @Override
    public JsonResult findAccuratelyArticle(Long articleId) {
        Map<String, Object> map = adminBlogArticleMapper.aQueryBlogArticleById(articleId);
        if (map.isEmpty()) return JsonResult.OK("不存在此文章id");
        return JsonResult.OK("查询成功", map);
    }

    @SneakyThrows
    @Override
    public JsonResult modifyBlogArticle(Integer id, String content, String title, String introduction, MultipartFile file, HttpServletRequest request) {
        UUID uuid = UUID.randomUUID();
        String splicingFile = null;
        if (file != null){
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
            splicingFile = request.getRequestURL().toString().replace(request.getRequestURI(),"") + "/image/" + newFileName;
        }
        return JsonResult.OK("成功修改" + adminBlogArticleMapper.aUpdateBlogArticle(id, content, title, introduction, splicingFile) + "条数据", uuid);
    }
}
