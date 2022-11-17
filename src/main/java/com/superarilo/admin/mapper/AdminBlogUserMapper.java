package com.superarilo.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.superarilo.admin.entity.BlogUserStatus;
import com.superarilo.admin.vo.AdminBlogUserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

@Mapper
public interface AdminBlogUserMapper extends BaseMapper<BlogUserStatus> {
    Page<AdminBlogUserVo> queryBlogUser(Page<AdminBlogUserVo> page,
                                    @Param("uid") Long uid,
                                    @Param("nickName") String nickName,
                                    @Param("email") String email,
                                    @Param("status") Boolean status,
                                    @Param("category") Integer category);
    Integer deleteBlogUser(@Param("uidList") List<Long> uidList);
    Integer updateBlogUserInfo(Map<String, Object> objectMap);
    boolean queryIsHaveUser(@Param("uid") Long uid);
}
