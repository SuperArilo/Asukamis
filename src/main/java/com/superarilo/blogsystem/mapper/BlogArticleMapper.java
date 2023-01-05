package com.superarilo.blogsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.superarilo.entity.BlogArticle;
import com.superarilo.blogsystem.vo.BlogArticleContentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface BlogArticleMapper extends BaseMapper<BlogArticle> {
    Page<Map<String, Object>> selectBlogArticleList(Page<BlogArticle> page,
                                                    @Param("keywords") String keywords);
    BlogArticleContentVO selectBlogArticleContent(@Param("articleId") Long articleId,
                                                  @Param("uid") Long uid);
    boolean queryArticleIsHave(@Param("articleId") Long articleId);
    boolean increaseArticleView(@Param("articleId") Long articleId);
}
