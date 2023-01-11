package com.superarilo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.superarilo.entity.GossipCommentLike;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BlogGossipCommentLikeMapper extends BaseMapper<GossipCommentLike> {
    boolean queryUserIsLike(@Param("commentId") Long commentId,
                            @Param("uid") Long uid);
    boolean cancelLikeComment(@Param("commentId") Long commentId,
                              @Param("uid") Long uid);
}
