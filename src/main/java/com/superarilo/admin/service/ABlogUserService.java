package com.superarilo.admin.service;

import com.superarilo.admin.vo.AdminBlogUserVo;
import com.superarilo.utils.JsonResult;
import com.superarilo.utils.PageCustom;
import java.util.List;
import java.util.Map;

public interface ABlogUserService {
    PageCustom<AdminBlogUserVo> queryBlogUser(Integer pageNum, Integer pageSize, Long uid, String nickName, String email, Boolean status, Integer category);
    String deleteBlogUser(List<Long> uidList);
    String changeBlogUserInfo(Map<String, Object> object);
    JsonResult createBlogUser(String nickName, String email, String password);
}
