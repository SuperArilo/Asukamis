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
@TableName(value = "blog_article")
public class BlogArticle {
    @TableId(value = "id", type = IdType.AUTO)
    private Long articleId;
    @TableField(value = "publisher")
    private Long publisher;
    @TableField(value = "createTime")
    @JsonFormat(timezone = "GMT+8", pattern = DateUtil.YYYY_MM_DD_PATTERN)
    private Date createTime;
    @TableField(value = "articleTitle")
    private String articleTitle;
    @TableField(value = "articleIntroduction")
    private String articleIntroduction;
    @TableField(value = "articleContent")
    private String articleContent;
    @TableField(value = "articleViews")
    private int articleViews;
    @TableField(value = "articlePicture")
    private String articlePicture;
}
