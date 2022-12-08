package com.superarilo.blogsystem.service.Impl;

import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.superarilo.blogsystem.entity.ArtUserLike;
import com.superarilo.blogsystem.mapper.ArtUserLikeMapper;
import com.superarilo.blogsystem.mapper.BlogArticleMapper;
import com.superarilo.blogsystem.service.BlogArticleService;
import com.superarilo.utils.InputCheck;
import com.superarilo.utils.JsonResult;
import com.superarilo.utils.JwtUtils;
import com.superarilo.utils.PageCustom;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogArticleImpl implements BlogArticleService {

    private final BlogArticleMapper blogArticleMapper;

    private final ArtUserLikeMapper artUserLikeMapper;

    public BlogArticleImpl(BlogArticleMapper blogArticleMapper, ArtUserLikeMapper artUserLikeMapper) {
        this.blogArticleMapper = blogArticleMapper;
        this.artUserLikeMapper = artUserLikeMapper;
    }

    @Override
    public PageCustom<Map<String, Object>> getBlogArticleList(Integer pageNum, Integer pageSize, String keyword, HttpServletRequest request) {
        String token = request.getHeader("token");
        Page<Map<String, Object>> blogArticlePage = blogArticleMapper.selectBlogArticleList(new Page<>(pageNum, pageSize));
        List<Map<String, Object>> articleList = blogArticlePage.getRecords();
        if (token == null || token.equals("") || !JwtUtils.verify(token, InputCheck.tokenSecret)) return new PageCustom<>(blogArticlePage.getTotal(), articleList, blogArticlePage.getPages(), blogArticlePage.getCurrent(), blogArticlePage.getSize());
        Long uid = JWT.decode(token).getClaim("uid").asLong();
        for (Map<String, Object> map : articleList){
            map.put("isLike", artUserLikeMapper.queryUserLike((Long) map.get("id"), uid));
        }

        return new PageCustom<>(blogArticlePage.getTotal(), articleList, blogArticlePage.getPages(), blogArticlePage.getCurrent(), blogArticlePage.getSize());
    }

    @Override
    public JsonResult getBlogArticleContent(Long articleId, HttpServletRequest request) {
        if(!blogArticleMapper.increaseArticleView(articleId)) return JsonResult.Error(400, "服务器没有查询到此文章的id");
        String token = request.getHeader("token");
        Long tokenUid;
        if (token == null || token.equals("") || !JwtUtils.verify(token, InputCheck.tokenSecret)){
            tokenUid = null;
            return JsonResult.OK("查询成功", blogArticleMapper.selectBlogArticleContent(articleId, tokenUid));
        }
        tokenUid = JWT.decode(token).getClaim("uid").asLong();
        return JsonResult.OK("查询成功", blogArticleMapper.selectBlogArticleContent(articleId, tokenUid));
    }

    @Override
    public JsonResult increaseBlogArticleLike(Long articleId,HttpServletRequest request) {
        if(!blogArticleMapper.queryArticleIsHave(articleId)) return JsonResult.Error(400, "服务器没有查询到此文章的id");
        Long tokenUid = JWT.decode(request.getHeader("token")).getClaim("uid").asLong();
        Map<String, Boolean> likeStatus = new HashMap<>();
        if (artUserLikeMapper.queryUserLike(articleId, tokenUid)){
            if (artUserLikeMapper.deleteUserLike(articleId, tokenUid)){
                likeStatus.put("status", false);
                return JsonResult.OK("取消喜欢了咯 (´。＿。｀)", likeStatus);
            } else {
                likeStatus.put("status", false);
                return JsonResult.Error(400, "没有找到记录", likeStatus);
            }
        } else {
            ArtUserLike artUserLike = new ArtUserLike();
            artUserLike.setArticleId(articleId);
            artUserLike.setUid(tokenUid);
            artUserLikeMapper.insert(artUserLike);
            likeStatus.put("status", true);
            return JsonResult.OK("喜欢成功，感谢你的点赞 (○｀ 3′○)", likeStatus);
        }
    }
}
