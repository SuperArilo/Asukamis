package com.superarilo.blogsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.superarilo.blogsystem.entity.BlogUser;
import com.superarilo.utils.JsonResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BlogUserService extends IService<BlogUser> {
    JsonResult registerUser(String email, String password, String nickName, HttpServletResponse response);
    JsonResult loginUserByNormal(String email, String password, Long uid, HttpServletRequest request);
}
