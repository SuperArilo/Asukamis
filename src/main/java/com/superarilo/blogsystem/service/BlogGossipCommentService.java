package com.superarilo.blogsystem.service;

import com.superarilo.utils.JsonResult;
import com.superarilo.utils.PageCustom;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface BlogGossipCommentService {
    PageCustom<Map<String, Object>> commentList(Integer pageNum, Integer pageSize, Long gossipId, HttpServletRequest request);
    JsonResult createComment(Long gossipId, String content, Long replyCommentId, Long replyUserId, HttpServletRequest request);
}
