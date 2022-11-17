package com.superarilo.admin.service;

import com.superarilo.admin.vo.AdminBlogArticleVo;
import com.superarilo.utils.JsonResult;
import com.superarilo.utils.PageCustom;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;

public interface ABlogArticleService {
    PageCustom<AdminBlogArticleVo> queryBlogArticle(Integer pageNum,
                                                    Integer pageSize,
                                                    String articleTitle,
                                                    String author,
                                                    String beforeTime,
                                                    String afterTime,
                                                    String sortName,
                                                    String sort);
    String createBlogArticle(Long uid, String articleTitle, String articleIntroduction, String articleContent);
    JsonResult findAccuratelyArticle(Long articleId);
    JsonResult modifyBlogArticle(Integer id,
             String content,
             String title,
             String introduction,
             MultipartFile file,
             HttpServletRequest request);
}
