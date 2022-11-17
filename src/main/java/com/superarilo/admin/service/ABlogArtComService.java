package com.superarilo.admin.service;

import com.superarilo.utils.JsonResult;
import com.superarilo.utils.PageCustom;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface ABlogArtComService {
    PageCustom<Map<String, Object>> articleCommentListGet(Integer pageNum, Integer pageSize, Long articleId, HttpServletRequest request);
    JsonResult articleCommentAdd(Long articleId,
                                 String content,
                                 Long replyCommentId,
                                 Long replyUserId,
                                 HttpServletRequest request);
    JsonResult deleteCommentByAdmin(Long articleId, List<Long> commentListId, HttpServletRequest request);
    JsonResult commentLike(Long commentId, Long articleId, HttpServletRequest request);
}
