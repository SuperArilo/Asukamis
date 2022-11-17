package com.superarilo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;

@TableName(value = "blog_gossip")
@Data
public class BlogGossip {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField(value = "author")
    private String author;
    @TableField(value = "content")
    private String content;
    @TableField(value = "createTime")
    private Date createTime;
}
