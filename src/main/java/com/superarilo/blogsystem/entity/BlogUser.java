package com.superarilo.blogsystem.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.superarilo.utils.DateUtil;
import lombok.Data;

import java.util.Date;

@Data
@TableName("blog_user")
public class BlogUser {

    @TableId(value = "uid")
    private Long uid;
    @TableField(value = "email")
    private String email;
    @TableField(value = "password")
    private String password;
    @TableField(value = "registerTime")
    @JsonFormat(timezone = "GMT+8", pattern = DateUtil.YYYY_MM_DD_BIAS_PATTERN)
    private Date registerTime;
    @TableField(value = "category")
    private Integer category;
}
