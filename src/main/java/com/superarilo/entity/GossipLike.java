package com.superarilo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName(value = "blog_gossip_like_list")
@Data
public class GossipLike {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField(value = "gossipId")
    private Long gossipId;
    @TableField(value = "uid")
    private Long uid;
}
