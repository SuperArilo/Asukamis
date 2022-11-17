package com.superarilo.blogsystem.controller;

import com.superarilo.blogsystem.service.BlogGossipCommentService;
import com.superarilo.utils.JsonResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/gossip/comment")
public class BlogGossipCommentController {

    private final BlogGossipCommentService blogGossipCommentService;

    public BlogGossipCommentController(BlogGossipCommentService blogGossipCommentService) {
        this.blogGossipCommentService = blogGossipCommentService;
    }
    @GetMapping("/list")
    public JsonResult commentListGet(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                     @RequestParam(value = "gossipId") Long gossipId,
                                     HttpServletRequest request){
        return JsonResult.OK("请求成功", blogGossipCommentService.commentList(pageNum, pageSize, gossipId, request));
    }
    @PostMapping("/add")
    public JsonResult createComment(@RequestParam(value = "gossipId") Long gossipId,
                                    @RequestParam(value = "content") String content,
                                    @RequestParam(value = "replyCommentId", required = false) Long replyCommentId,
                                    @RequestParam(value = "replyUserId", required = false) Long replyUserId,
                                    HttpServletRequest request){
        return blogGossipCommentService.createComment(gossipId, content, replyCommentId, replyUserId, request);
    }
}
