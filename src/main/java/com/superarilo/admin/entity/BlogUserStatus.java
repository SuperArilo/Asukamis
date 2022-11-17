package com.superarilo.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "blog_user_status")
public class BlogUserStatus {
    @TableId(value = "uid")
    private Long uid;
    @TableField(value = "status")
    private boolean status;
}
