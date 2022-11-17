package com.superarilo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "blog_images")
public class BlogImages {
    @TableId(value = "imageId", type = IdType.AUTO)
    private Long imageId;
    @TableField(value = "uploader")
    private Long uploader;
    @TableField(value = "imName")
    private String imName;
    @TableField(value = "imPath")
    private String imPath;
    @TableField(value = "createTime")
    private Date createTime;
}
