package com.superarilo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.superarilo.entity.BlogGossip;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface BlogGossipMapper extends BaseMapper<BlogGossip> {
    Page<Map<String, Object>> gossipListGet(Page<BlogGossip> page);
    boolean queryGossipIsHave(@Param("gossipId") Long gossipId);
}
