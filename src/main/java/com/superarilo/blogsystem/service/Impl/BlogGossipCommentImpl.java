package com.superarilo.blogsystem.service.Impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.superarilo.blogsystem.mapper.BlogUserMapper;
import com.superarilo.blogsystem.service.BlogGossipCommentService;
import com.superarilo.entity.GossipComment;
import com.superarilo.mapper.BlogGossipCommentLikeMapper;
import com.superarilo.mapper.BlogGossipCommentMapper;
import com.superarilo.mapper.BlogGossipMapper;
import com.superarilo.utils.*;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class BlogGossipCommentImpl implements BlogGossipCommentService {

    private final BlogGossipCommentMapper blogGossipCommentMapper;
    private final BlogGossipCommentLikeMapper blogGossipCommentLikeMapper;
    private final BlogGossipMapper blogGossipMapper;
    private final BlogUserMapper blogUserMapper;

    public BlogGossipCommentImpl(BlogGossipCommentMapper blogGossipCommentMapper, BlogGossipCommentLikeMapper blogGossipCommentLikeMapper, BlogGossipMapper blogGossipMapper, BlogUserMapper blogUserMapper) {
        this.blogGossipCommentMapper = blogGossipCommentMapper;
        this.blogGossipCommentLikeMapper = blogGossipCommentLikeMapper;
        this.blogGossipMapper = blogGossipMapper;
        this.blogUserMapper = blogUserMapper;
    }

    @Override
    public PageCustom<Map<String, Object>> commentList(Integer pageNum, Integer pageSize, Long gossipId, HttpServletRequest request) {
        String token = request.getHeader("token");
        Page<Map<String, Object>> dataPage = blogGossipCommentMapper.commentList(gossipId, new Page<>(pageNum, pageSize));
        List<Map<String, Object>> listData = dataPage.getRecords();
        boolean checkStatus = token == null || token.equals("") || !JwtUtils.verify(token, InputCheck.tokenSecret);
        Long uid = null;
        if (!checkStatus) uid = JWT.decode(token).getClaim("uid").asLong();
        for(Map<String, Object> map : listData){
            map.replace("createTime", TimeFormat.timeCheck(((Date) map.get("createTime")).getTime(), new Date().getTime()));
            if (!checkStatus){
                map.put("isLike", blogGossipCommentLikeMapper.queryUserIsLike((Long) map.get("commentId"), uid));
            }
        }
        return new PageCustom<>(dataPage.getTotal(), listData, dataPage.getPages(), dataPage.getCurrent(), dataPage.getSize());
    }

    @Override
    public JsonResult createComment(Long gossipId, String content, Long replyCommentId, Long replyUserId, HttpServletRequest request) {
        if(content == null || content.equals("") || content.equals("<p><br></p>")) return JsonResult.OK("不能提交空白哦 ˋ( ° ▽、° )");
        if(blogGossipMapper.queryGossipIsHave(gossipId)){
            DecodedJWT jwt = JWT.decode(request.getHeader("token"));
            long userId = jwt.getClaim("uid").asLong();
            if (replyUserId != null && replyUserId.equals(userId)) return JsonResult.OK("不能回复你自己哦");
            if(blogUserMapper.queryHaveUserByUID(userId)){
                GossipComment gossipComment = new GossipComment();
                gossipComment.setGossipId(gossipId);
                gossipComment.setReplyUser(userId);
                gossipComment.setCreateTime(new Date());
                gossipComment.setContent(content);
                if(blogGossipCommentMapper.queryGossipCommentIsHave(gossipId, replyCommentId, replyUserId)){
                    gossipComment.setBeReplyUser(replyUserId);
                }
                blogGossipCommentMapper.insert(gossipComment);
                return JsonResult.OK("评论成功 (￣y▽,￣)╭ ");
            } else {
                return JsonResult.Error(404, "此用户不存在！");
            }
        } else {
            return JsonResult.Error(404, "碎语不存在或者被删除");
        }
    }
}
