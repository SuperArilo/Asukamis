package com.superarilo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.superarilo.entity.GossipComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface BlogGossipCommentMapper extends BaseMapper<GossipComment> {
    Page<Map<String, Object>> commentList(@Param("gossipId") Long gossipId,
                                          Page<GossipComment> page);
    boolean queryGossipCommentIsHave(@Param("gossipId") Long gossipId,
                                     @Param("commentId") Long replyCommentId,
                                     @Param("replyUserId") Long replyUserId);
    boolean deleteCommentById(@Param("gossipId") Long gossipId,
                              @Param("commentId") Long commentId,
                              @Param("uid") Long uid);
}
