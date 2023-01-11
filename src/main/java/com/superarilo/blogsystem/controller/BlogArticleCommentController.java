package com.superarilo.blogsystem.controller;

import com.superarilo.blogsystem.service.ArticleCommentService;
import com.superarilo.utils.JsonResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/article/comment")
public class BlogArticleCommentController {
    private final ArticleCommentService articleCommentService;

    public BlogArticleCommentController(ArticleCommentService articleCommentService) {
        this.articleCommentService = articleCommentService;
    }

    @GetMapping("/list")
    public JsonResult articleCommentListGet(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                            @RequestParam(value = "articleId") Long articleId,
                                            HttpServletRequest request){
        return JsonResult.OK("查询成功！", articleCommentService.articleCommentListGet(pageNum, pageSize, articleId, request));
    }
    @PostMapping("/add")
    public JsonResult articleCommentAdd(@RequestParam(value = "articleId") Long articleId,
                                        @RequestParam(value = "content") String content,
                                        @RequestParam(value = "replyCommentId", required = false) Long replyCommentId,
                                        @RequestParam(value = "replyUserId", required = false) Long replyUserId,
                                        HttpServletRequest request){
        return articleCommentService.articleCommentAdd(articleId, content, replyCommentId, replyUserId, request);
    }
    @DeleteMapping("/delete")
    public JsonResult articleCommentDelete(@RequestParam(value = "articleId") Long articleId,
                                           @RequestParam(value = "commentId") Long commentId,
                                           HttpServletRequest request,
                                           HttpServletResponse response){
        return articleCommentService.deleteArticleCommentById(articleId, commentId, request, response);
    }
    @PutMapping("/like")
    public JsonResult articleCommentLike(@RequestParam(value = "articleId") Long articleId,
                                     @RequestParam(value = "commentId") Long commentId,
                                     HttpServletRequest request){
        return articleCommentService.commentLike(commentId, articleId, request);
    }
}
