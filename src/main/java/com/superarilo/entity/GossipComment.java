package com.superarilo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;

@TableName(value = "blog_gossip_comment")
@Data
public class GossipComment {
    @TableId(value = "commentId", type = IdType.AUTO)
    private Long commentId;
    @TableField(value = "gossipId")
    private Long gossipId;
    @TableField(value = "replyUser")
    private Long replyUser;
    @TableField(value = "beReplyUser")
    private Long beReplyUser;
    @TableField(value = "createTime")
    private Date createTime;
    @TableField(value = "content")
    private String content;
}
