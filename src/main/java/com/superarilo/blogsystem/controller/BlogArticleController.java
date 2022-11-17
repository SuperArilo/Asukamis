package com.superarilo.blogsystem.controller;
import com.superarilo.blogsystem.service.BlogArticleService;
import com.superarilo.utils.JsonResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/article")
public class BlogArticleController {

    private final BlogArticleService blogArticleService;

    public BlogArticleController(BlogArticleService blogArticleService) {
        this.blogArticleService = blogArticleService;
    }

    @GetMapping("/list")
    public JsonResult blogArticleListGet(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                         @RequestParam(value = "keyword", required = false) String keyword,
                                         HttpServletRequest request){
        return JsonResult.OK("查询成功！", blogArticleService.getBlogArticleList(pageNum, pageSize, keyword, request));
    }

    @GetMapping("/content")
    public JsonResult blogArticleContentGet(@RequestParam(value = "articleId") Long articleId,
                                            HttpServletRequest request){
        return blogArticleService.getBlogArticleContent(articleId, request);
    }

    @PutMapping("/like")
    public JsonResult increaseBlogArticleLike(@RequestParam(value = "articleId") Long articleId,
                                              HttpServletRequest request){
        return blogArticleService.increaseBlogArticleLike(articleId, request);
    }
}
