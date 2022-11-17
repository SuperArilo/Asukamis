package com.superarilo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.superarilo.entity.ArticleComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ArticleCommentMapper extends BaseMapper<ArticleComment> {
    Page<Map<String, Object>> getArticleCommentList(Page<ArticleComment> page,
                                                    @Param("articleId") Long articleId);
    boolean queryArtComment(@Param("articleId") Long articleId,
                            @Param("commentId") Long commentId,
                            @Param("replyUser") Long replyUser);
    boolean deleteArticleCommentById(@Param("articleId") Long articleId,
                                     @Param("commentId") Long commentId,
                                     @Param("userId") Long replyUser);
    Integer deleteArticleCommentByAdmin(@Param("articleId") Long articleId,
                                        @Param("commentId") List<Long> commentListId);
}
