package com.superarilo.blogsystem.controller;

import com.superarilo.blogsystem.service.BlogGossipService;
import com.superarilo.utils.JsonResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/gossip")
public class BlogGossipController {
    private final BlogGossipService blogGossipService;

    public BlogGossipController(BlogGossipService blogGossipService) {
        this.blogGossipService = blogGossipService;
    }
    @GetMapping("/list")
    public JsonResult gossipListGet(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                    HttpServletRequest request){
        return JsonResult.OK("请求成功", blogGossipService.gossipListGet(pageNum, pageSize, request));
    }
    @PutMapping("/like")
    public JsonResult gossipLike(@RequestParam("gossipId") Long gossipId,
                                 HttpServletRequest request){
        return blogGossipService.gossipLike(gossipId, request);
    }
}
