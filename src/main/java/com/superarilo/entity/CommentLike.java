package com.superarilo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("blog_comment_like_list")
@Data
public class CommentLike {
    @TableId(value = "id", type = IdType.AUTO)
    private Long Id;
    @TableField(value = "commentId")
    private Long commentId;
    @TableField(value = "uid")
    private Long uid;
}
