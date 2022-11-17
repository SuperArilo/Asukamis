package com.superarilo.blogsystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName(value = "blog_article_like_list")
@Data
public class ArtUserLike {
    @TableId(value = "id", type = IdType.AUTO)
    Long id;
    @TableField(value = "articleId")
    Long articleId;
    @TableField(value = "uid")
    Long uid;
}
