package com.superarilo.admin.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.superarilo.utils.DateUtil;
import lombok.Data;

import java.util.Date;

@Data
public class AdminBlogUserVo {
    private Long uid;
    private String nickName;
    private String email;
    private Integer sex;
    private Integer age;
    private String avatar;
    private String autograph;
    @JsonFormat(timezone = "GMT+8", pattern = DateUtil.YYYY_MM_DD_PATTERN)
    private Date registerTime;
    private boolean status;
    private String categoryName;
    private Integer category;
}
