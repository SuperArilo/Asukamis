package com.superarilo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName(value = "blog_gossip_comment_like_list")
@Data
public class GossipCommentLike {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField(value = "commentId")
    private Long commentId;
    @TableField(value = "uid")
    private Long uid;
}
