package com.superarilo.blogsystem.service.Impl;

import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.superarilo.entity.ArticleComment;
import com.superarilo.entity.CommentLike;
import com.superarilo.mapper.ArticleCommentMapper;
import com.superarilo.blogsystem.mapper.BlogArticleMapper;
import com.superarilo.mapper.BlogCommentLikeMapper;
import com.superarilo.blogsystem.mapper.BlogUserMapper;
import com.superarilo.blogsystem.service.ArticleCommentService;
import com.superarilo.utils.*;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleCommentImpl extends ServiceImpl<ArticleCommentMapper, ArticleComment> implements ArticleCommentService {

    private final BlogArticleMapper blogArticleMapper;
    private final BlogUserMapper blogUserMapper;
    private final ArticleCommentMapper articleCommentMapper;
    private final BlogCommentLikeMapper blogCommentLikeMapper;


    public ArticleCommentImpl(ArticleCommentMapper articleCommentMapper, BlogUserMapper blogUserMapper, BlogArticleMapper blogArticleMapper, BlogCommentLikeMapper blogCommentLikeMapper) {
        this.articleCommentMapper = articleCommentMapper;
        this.blogUserMapper = blogUserMapper;
        this.blogArticleMapper = blogArticleMapper;
        this.blogCommentLikeMapper = blogCommentLikeMapper;
    }

    @Override
    public PageCustom<Map<String, Object>> articleCommentListGet(Integer pageNum, Integer pageSize, Long articleId, HttpServletRequest request) {
        String token = request.getHeader("token");
        Page<Map<String,Object>> page = articleCommentMapper.getArticleCommentList(new Page<>(pageNum, pageSize), articleId);
        List<Map<String ,Object>> commentList = page.getRecords();
        boolean checkStatus = token == null || token.equals("") || !JwtUtils.verify(token, InputCheck.tokenSecret);
        Long uid = null;
        if (!checkStatus) uid = JWT.decode(token).getClaim("uid").asLong();
        for (Map<String, Object> map : commentList){
            map.replace("createTime", TimeFormat.timeCheck(((Date) map.get("createTime")).getTime(), new Date().getTime()));
            if (!checkStatus){
                map.put("isLike", blogCommentLikeMapper.queryUserIsLikeComment((Long) map.get("commentId"), uid));
            }
        }
        return new PageCustom<>(page.getTotal(), commentList, page.getPages(), page.getCurrent(), page.getSize());
    }

    @Override
    public JsonResult articleCommentAdd(Long articleId, String content, Long replyCommentId, Long replyUserId, HttpServletRequest request) {
        if(content == null || content.equals("") || content.equals("<p><br></p>")) return JsonResult.OK("不能提交空白哦 ˋ( ° ▽、° )");
        if (blogArticleMapper.queryArticleIsHave(articleId)){
            long userId = JWT.decode(request.getHeader("token")).getClaim("uid").asLong();
            if (replyUserId != null && replyUserId.equals(userId)) return JsonResult.OK("不能回复你自己哦");
            if (blogUserMapper.queryHaveUserByUID(userId)){
                //检查评论是否存在
                ArticleComment articleComment = new ArticleComment();
                articleComment.setArticleId(articleId);
                articleComment.setReplyUser(userId);
                articleComment.setByReplyCommentId(replyCommentId);
                articleComment.setCreateTime(new Date());
                articleComment.setContent(content);
                if (articleCommentMapper.queryArtComment(articleId, replyCommentId, replyUserId)) {
                    articleComment.setByReplyUser(replyUserId);
                }
                articleCommentMapper.insert(articleComment);
                return JsonResult.OK("评论成功 (￣y▽,￣)╭ ");
            } else {
                return JsonResult.Error(404, "此用户不存在！");
            }
        } else {
            return JsonResult.Error(404, "文章不存在或者已被删除！");
        }
    }

    @Override
    public JsonResult deleteArticleCommentById(Long articleId, Long commentId, HttpServletRequest request, HttpServletResponse response) {
        if (articleCommentMapper.deleteArticleCommentById(articleId, commentId, JWT.decode(request.getHeader("token")).getClaim("uid").asLong())){
            return JsonResult.OK("删除成功！");
        } else {
            return JsonResult.Error(400, "删除失败，服务器没有找到对应的资源！");
        }
    }

    @Override
    public JsonResult commentLike(Long commentId, Long articleId, HttpServletRequest request) {
        Long uid = JWT.decode(request.getHeader("token")).getClaim("uid").asLong();
        Map<String, Boolean> likeStatus = new HashMap<>();
        if (blogCommentLikeMapper.queryUserIsLikeComment(commentId, uid)){
            if (blogCommentLikeMapper.cancelLikeComment(commentId, uid)){
                likeStatus.put("status", false);
                return JsonResult.OK("取消喜欢了咯 (´。＿。｀)", likeStatus);
            } else {
                likeStatus.put("status", false);
                return JsonResult.Error(400, "没有找到记录", likeStatus);
            }
        } else {
            CommentLike commentLike = new CommentLike();
            commentLike.setCommentId(commentId);
            commentLike.setUid(uid);
            blogCommentLikeMapper.insert(commentLike);
            likeStatus.put("status", true);
            return JsonResult.OK("喜欢成功，感谢你的点赞 (○｀ 3′○)", likeStatus);
        }
    }
}
