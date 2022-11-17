package com.superarilo.blogsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.superarilo.blogsystem.entity.BlogUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;


@Mapper
public interface BlogUserMapper extends BaseMapper<BlogUser> {
    Map<String, Object> queryHaveUser(@Param("email") String email);
    Map<String, Object> loginUser(@Param("uid") Long uid,
                                  @Param("email") String email,
                                  @Param("password") String password);
    boolean queryHaveUserByUID(@Param(("uid")) Long uid);
}
