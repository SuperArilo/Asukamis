package com.superarilo.blogsystem.controller;

import com.superarilo.blogsystem.service.BlogUserService;
import com.superarilo.utils.JsonResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/user")
public class BlogUserController {

    private final BlogUserService blogUserService;

    public BlogUserController(BlogUserService blogUserService) {
        this.blogUserService = blogUserService;
    }

    @PostMapping("/register")
    public JsonResult blogUserRegister(@RequestParam(value = "email") String email,
                                       @RequestParam(value = "password") String password,
                                       @RequestParam(value = "nickName") String nickName,
                                       HttpServletResponse response){
        return blogUserService.registerUser(email, password, nickName, response);
    }
    @PostMapping("/login")
    public JsonResult blogUserLogin(@RequestParam(value = "email", required = false) String email,
                                    @RequestParam(value = "password", required = false) String password,
                                    @RequestParam(value = "uid", required = false) Long uid,
                                    HttpServletRequest request){
        return blogUserService.loginUserByNormal(email, password, uid, request);
    }
}
