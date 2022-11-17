package com.superarilo.admin.controller;

import com.superarilo.admin.service.ABlogArticleService;
import com.superarilo.utils.JsonResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/system/article")
public class ABlogArticleController {

    private final ABlogArticleService aBlogArticleService;

    public ABlogArticleController(ABlogArticleService aBlogArticleService) {
        this.aBlogArticleService = aBlogArticleService;
    }

    @GetMapping("/list")
    public JsonResult ABlogArticleGet(@RequestParam(value = "articleTitle", required = false) String articleTitle,
                                      @RequestParam(value = "author", required = false) String author,
                                      @RequestParam(value = "beforeTime", required = false) String beforeTime,
                                      @RequestParam(value = "afterTime", required = false) String afterTime,
                                      @RequestParam(value = "sortName", required = false) String sortName,
                                      @RequestParam(value = "sort", required = false , defaultValue = "id") String sort,
                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        return JsonResult.OK("查询成功", aBlogArticleService.queryBlogArticle(pageNum, pageSize, articleTitle, author, beforeTime, afterTime, sortName, sort));
    }

    @PostMapping("/create")
    public JsonResult ABlogArticleCreate(@RequestParam("uid") Long uid,
                                         @RequestParam("articleTitle") String articleTitle,
                                         @RequestParam("articleIntroduction") String articleIntroduction,
                                         @RequestParam("articleContent") String articleContent){
        return JsonResult.OK(aBlogArticleService.createBlogArticle(uid, articleTitle, articleIntroduction, articleContent));
    }

    @GetMapping("/find")
    public JsonResult findAccuratelyArticle(@RequestParam("articleId")Long artId){
        return aBlogArticleService.findAccuratelyArticle(artId);
    }

    @PutMapping("/modify")
    public JsonResult modifyBlogArticle(@RequestParam("id") Integer id,
                                        @RequestParam(value = "content", required = false) String content,
                                        @RequestParam(value = "title", required = false) String title,
                                        @RequestParam(value = "introduction", required = false) String introduction,
                                        @RequestParam(value = "picture", required = false) MultipartFile file,
                                        HttpServletRequest request){
        return aBlogArticleService.modifyBlogArticle(id, content, title, introduction, file, request);
    }
}
