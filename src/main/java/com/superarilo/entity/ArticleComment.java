package com.superarilo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.superarilo.utils.DateUtil;
import lombok.Data;
import java.util.Date;

@Data
@TableName(value = "blog_article_comment")
public class ArticleComment {
    @TableId(value = "commentId", type = IdType.AUTO)
    private Long commentId;
    @TableField(value = "articleId")
    private Long articleId;
    @TableField(value = "replyUser")
    private Long replyUser;
    @TableField(value = "byReplyUser")
    private Long byReplyUser;
    @TableField(value = "byReplyCommentId")
    private Long byReplyCommentId;
    @TableField(value = "createTime")
    @JsonFormat(timezone = "GMT+8", pattern = DateUtil.YYYY_MM_DD_PATTERN)
    private Date createTime;
    @TableField(value = "content")
    private String content;
}
