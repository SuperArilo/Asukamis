package com.superarilo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.superarilo.entity.GossipLike;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BlogGossipLikeMapper extends BaseMapper<GossipLike> {
    boolean queryUserIsLike(@Param("gossipId") Long gossipId,
                            @Param("uid") Long uid);
    boolean cancelLikeGossip(@Param("gossipId") Long gossipId,
                             @Param("uid") Long uid);
}
