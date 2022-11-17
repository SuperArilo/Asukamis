package com.superarilo.blogsystem.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("blog_user_means")
public class BlogUserMeans {
    @TableId(value = "uid")
    private Long uid;
    @TableField(value = "avatar")
    private String avatar;
    @TableField(value = "autograph")
    private String autograph;
    @TableField(value = "nickName")
    private String nickName;
    @TableField(value = "age")
    private Integer age;
    @TableField(value = "sex")
    private Integer sex;
}
