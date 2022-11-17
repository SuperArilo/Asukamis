package com.superarilo.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.superarilo.admin.vo.AdminBlogArticleVo;
import com.superarilo.entity.BlogArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.Map;

@Mapper
public interface AdminBlogArticleMapper extends BaseMapper<BlogArticle> {
    Page<AdminBlogArticleVo> aQueryBlogArticle(Page<BlogArticle> page,
                                               @Param("aTitle") String articleTitle,
                                               @Param("author") String author,
                                               @Param("beforeTime") String beforeTime,
                                               @Param("afterTime") String afterTime,
                                               @Param("sortName") String sortName,
                                               @Param("sort") String sort);
    Map<String, Object> aQueryBlogArticleById(@Param("artId") Long articleId);
    Integer aUpdateBlogArticle(@Param("id") Integer id,
                               @Param("content") String content,
                               @Param("title") String title,
                               @Param("introduction") String introduction,
                               @Param("picture") String picture);
    boolean aQueryIsHaveArticle(@Param("articleId") Long articleId);
}
