package com.superarilo.blogsystem.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.superarilo.utils.DateUtil;
import lombok.Data;

import java.util.Date;

@Data
public class BlogArticleContentVO {
    private Long id;
    private Long publisher;
    @JsonFormat(timezone = "GMT+8", pattern = DateUtil.YYYY_MM_DD_PATTERN)
    private Date createTime;
    private String articleTitle;
    private String articleContent;
    private String articlePicture;
    private Integer articleLikes;
    private Integer articleViews;
    private String avatar;
    private String nickName;
    private boolean hasLike;
}
