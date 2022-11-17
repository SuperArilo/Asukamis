package com.superarilo.blogsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.superarilo.entity.ArticleComment;
import com.superarilo.utils.JsonResult;
import com.superarilo.utils.PageCustom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface ArticleCommentService extends IService<ArticleComment> {
    PageCustom<Map<String, Object>> articleCommentListGet(Integer pageNum, Integer pageSize, Long articleId, HttpServletRequest request);
    JsonResult articleCommentAdd(Long articleId, String content, Long replyCommentId, Long replyUserId, HttpServletRequest request);
    JsonResult deleteArticleCommentById(Long articleId, Long commentId, HttpServletRequest request, HttpServletResponse response);
    JsonResult commentLike(Long commentId, Long articleId, HttpServletRequest request);
}
