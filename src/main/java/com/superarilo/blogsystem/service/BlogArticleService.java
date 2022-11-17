package com.superarilo.blogsystem.service;

import com.superarilo.utils.JsonResult;
import com.superarilo.utils.PageCustom;
import com.superarilo.blogsystem.vo.BlogArticleContentVO;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface BlogArticleService {
    PageCustom<Map<String, Object>> getBlogArticleList(Integer pageNum, Integer pageSize, String keyword, HttpServletRequest request);
    JsonResult getBlogArticleContent(Long articleId, HttpServletRequest request);
    JsonResult increaseBlogArticleLike(Long articleId,HttpServletRequest request);
}
