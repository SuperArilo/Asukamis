package com.superarilo.admin.controller;

import com.superarilo.admin.service.ABlogArtComService;
import com.superarilo.utils.JsonResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/system/article/comment")
public class ABlogArtComController {
    private final ABlogArtComService aBlogArtComService;

    public ABlogArtComController(ABlogArtComService aBlogArtComService) {
        this.aBlogArtComService = aBlogArtComService;
    }

    @GetMapping("/list")
    public JsonResult articleCommentList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                         @RequestParam(value = "articleId") Long articleId,
                                         HttpServletRequest request){
        return JsonResult.OK("查询成功！", aBlogArtComService.articleCommentListGet(pageNum, pageSize, articleId, request));
    }
    @PostMapping("/add")
    public JsonResult commentCreate(@RequestParam(value = "articleId") Long articleId,
                                    @RequestParam(value = "content") String content,
                                    @RequestParam(value = "replyCommentId", required = false) Long replyCommentId,
                                    @RequestParam(value = "replyUserId", required = false) Long replyUserId,
                                    HttpServletRequest request){
        return aBlogArtComService.articleCommentAdd(articleId, content, replyCommentId, replyUserId, request);
    }
    @PutMapping("/like")
    public JsonResult commentLikeFunction(@RequestParam("commentId") Long commentId,
                                          @RequestParam("articleId") Long articleId,
                                          HttpServletRequest request){
        return aBlogArtComService.commentLike(commentId, articleId, request);
    }
    @DeleteMapping("/delete")
    public JsonResult commentDelete(@RequestParam(value = "articleId") Long articleId,
                                    @RequestParam(value = "commentListId") List<Long> commentListId,
                                    HttpServletRequest request){
        if (commentListId.size() == 0){
            return JsonResult.OK("评论id为空");
        }
        return aBlogArtComService.deleteCommentByAdmin(articleId, commentListId, request);
    }
}
