package com.superarilo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.superarilo.entity.CommentLike;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BlogCommentLikeMapper extends BaseMapper<CommentLike> {
    boolean queryUserIsLikeComment(@Param("commentId") Long commentId,
                                   @Param("uid") Long uid);
    boolean cancelLikeComment(@Param("commentId") Long commentId,
                              @Param("uid") Long uid);
}
