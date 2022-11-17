package com.superarilo.blogsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.superarilo.blogsystem.entity.ArtUserLike;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArtUserLikeMapper extends BaseMapper<ArtUserLike> {
    boolean queryUserLike(@Param("articleId") Long articleId,
                          @Param("uid") Long uid);
    boolean deleteUserLike(@Param("articleId") Long articleId,
                           @Param("uid") Long uid);
}
