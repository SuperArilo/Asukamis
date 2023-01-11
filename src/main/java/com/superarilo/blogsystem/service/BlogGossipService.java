package com.superarilo.blogsystem.service;

import com.superarilo.utils.JsonResult;
import com.superarilo.utils.PageCustom;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface BlogGossipService {
    PageCustom<Map<String, Object>> gossipListGet(Integer pageNum, Integer pageSize, HttpServletRequest request);
    JsonResult gossipLike(Long gossipId,
                          HttpServletRequest request);
}
