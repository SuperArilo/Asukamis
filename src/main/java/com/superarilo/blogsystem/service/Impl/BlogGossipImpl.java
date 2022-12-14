package com.superarilo.blogsystem.service.Impl;

import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.superarilo.blogsystem.service.BlogGossipService;
import com.superarilo.entity.GossipLike;
import com.superarilo.mapper.BlogGossipLikeMapper;
import com.superarilo.mapper.BlogGossipMapper;
import com.superarilo.utils.*;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogGossipImpl implements BlogGossipService {

    private final BlogGossipMapper blogGossipMapper;
    private final BlogGossipLikeMapper blogGossipLikeMapper;

    public BlogGossipImpl(BlogGossipMapper blogGossipMapper, BlogGossipLikeMapper blogGossipLikeMapper) {
        this.blogGossipMapper = blogGossipMapper;
        this.blogGossipLikeMapper = blogGossipLikeMapper;
    }

    @Override
    public PageCustom<Map<String, Object>> gossipListGet(Integer pageNum, Integer pageSize, HttpServletRequest request) {
        String token = request.getHeader("token");
        Page<Map<String, Object>> dataPage = blogGossipMapper.gossipListGet(new Page<>(pageNum, pageSize));
        List<Map<String, Object>> dataList = dataPage.getRecords();
        boolean checkStatus = token == null || token.equals("") || !JwtUtils.verify(token, InputCheck.tokenSecret);
        Long uid = null;
        if (!checkStatus) uid = JWT.decode(token).getClaim("uid").asLong();
        for (Map<String, Object> map : dataList){
            map.replace("createTime", TimeFormat.timeCheck(((Date) map.get("createTime")).getTime(), new Date().getTime()));
            if (!checkStatus){
                map.put("isLike", blogGossipLikeMapper.queryUserIsLike((Long) map.get("id"), uid));
            }
        }
        return new PageCustom<>(dataPage.getTotal(), dataList, dataPage.getPages(), dataPage.getCurrent(), dataPage.getSize());
    }

    @Override
    public JsonResult gossipLike(Long gossipId, HttpServletRequest request) {
        Long uid = JWT.decode(request.getHeader("token")).getClaim("uid").asLong();
        Map<String, Boolean> likeStatus = new HashMap<>();
        if(blogGossipLikeMapper.queryUserIsLike(gossipId, uid)) {
            if(blogGossipLikeMapper.cancelLikeGossip(gossipId, uid)) {
                likeStatus.put("status", false);
                return JsonResult.OK("?????????????????? (??????????????)", likeStatus);
            } else {
                likeStatus.put("status", false);
                return JsonResult.Error(400, "??????????????????", likeStatus);
            }
        } else {
            GossipLike gossipLike = new GossipLike();
            gossipLike.setGossipId(gossipId);
            gossipLike.setUid(uid);
            blogGossipLikeMapper.insert(gossipLike);
            likeStatus.put("status", true);
            return JsonResult.OK("????????????????????????????????? (?????? 3??????)", likeStatus);
        }
    }
}
