package com.superarilo.admin.service.Impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.superarilo.admin.mapper.AdminBlogArticleMapper;
import com.superarilo.admin.mapper.AdminBlogUserMapper;
import com.superarilo.admin.service.ABlogArtComService;
import com.superarilo.entity.ArticleComment;
import com.superarilo.entity.CommentLike;
import com.superarilo.mapper.ArticleCommentMapper;
import com.superarilo.mapper.BlogCommentLikeMapper;
import com.superarilo.utils.JsonResult;
import com.superarilo.utils.PageCustom;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ABlogArtComImpl implements ABlogArtComService {


    private final BlogCommentLikeMapper blogCommentLikeMapper;
    private final ArticleCommentMapper articleCommentMapper;
    private final AdminBlogArticleMapper adminBlogArticleMapper;
    private final AdminBlogUserMapper adminBlogUserMapper;

    public ABlogArtComImpl(ArticleCommentMapper articleCommentMapper, BlogCommentLikeMapper blogCommentLikeMapper, AdminBlogArticleMapper adminBlogArticleMapper, AdminBlogUserMapper adminBlogUserMapper) {
        this.articleCommentMapper = articleCommentMapper;
        this.blogCommentLikeMapper = blogCommentLikeMapper;
        this.adminBlogArticleMapper = adminBlogArticleMapper;
        this.adminBlogUserMapper = adminBlogUserMapper;
    }

    @Override
    public PageCustom<Map<String, Object>> articleCommentListGet(Integer pageNum, Integer pageSize, Long articleId, HttpServletRequest request) {
        Page<Map<String, Object>> pageData = articleCommentMapper.getArticleCommentList(new Page<>(pageNum, pageSize), articleId);
        List<Map<String ,Object>> commentList = pageData.getRecords();
        for (Map<String ,Object> map : commentList){
            map.put("isLike", blogCommentLikeMapper.queryUserIsLikeComment((Long) map.get("commentId"), 401682912L));
        }
        return new PageCustom<>(pageData.getTotal(), commentList, pageData.getPages(), pageData.getCurrent(), pageData.getSize());
    }

    @Override
    public JsonResult articleCommentAdd(Long articleId, String content, Long replyCommentId, Long replyUserId, HttpServletRequest request) {
        if(content == null || content.equals("") || content.equals("<p><br></p>")) return JsonResult.OK("不能提交空白");
        if (adminBlogArticleMapper.aQueryIsHaveArticle(articleId)){
            DecodedJWT jwt = JWT.decode(request.getHeader("token"));
            long userId = jwt.getClaim("uid").asLong();
            if (replyUserId != null && replyUserId.equals(userId)) return JsonResult.OK("不能回复你自己");
            if (adminBlogUserMapper.queryIsHaveUser(userId)){
                //检查评论是否存在
                ArticleComment articleComment = new ArticleComment();
                articleComment.setArticleId(articleId);
                articleComment.setArticleId(articleId);
                articleComment.setReplyUser(userId);
                articleComment.setCreateTime(new Date());
                articleComment.setContent(content);
                if (articleCommentMapper.queryArtComment(articleId, replyCommentId, replyUserId))
                {
                    articleComment.setByReplyUser(replyUserId);
                }
                articleCommentMapper.insert(articleComment);
                return JsonResult.OK("评论成功");
            } else {
                return JsonResult.Error(404, "此用户不存在！");
            }
        } else {
            return JsonResult.Error(404, "文章不存在或者已被删除！");
        }
    }

    @Override
    public JsonResult deleteCommentByAdmin(Long articleId, List<Long> commentListId, HttpServletRequest request) {
        return JsonResult.OK("删除了" + articleCommentMapper.deleteArticleCommentByAdmin(articleId, commentListId) + "条数据");
    }

    @Override
    public JsonResult commentLike(Long commentId, Long articleId, HttpServletRequest request) {
        Map<String, Boolean> likeStatus = new HashMap<>();
        if (blogCommentLikeMapper.queryUserIsLikeComment(commentId, 401682912L)){
            if (blogCommentLikeMapper.cancelLikeComment(commentId, 401682912L)){
                likeStatus.put("status", false);
                return JsonResult.OK("取消喜欢了咯 (´。＿。｀)", likeStatus);
            } else {
                likeStatus.put("status", false);
                return JsonResult.Error(400, "没有找到记录", likeStatus);
            }
        } else {
            CommentLike commentLike = new CommentLike();
            commentLike.setCommentId(commentId);
            commentLike.setUid(401682912L);
            blogCommentLikeMapper.insert(commentLike);
            likeStatus.put("status", true);
            return JsonResult.OK("喜欢成功，感谢你的点赞 (○｀ 3′○)", likeStatus);
        }
    }
}
