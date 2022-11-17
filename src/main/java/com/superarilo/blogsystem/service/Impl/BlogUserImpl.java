package com.superarilo.blogsystem.service.Impl;

import com.alibaba.fastjson2.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.superarilo.admin.entity.BlogUserStatus;
import com.superarilo.admin.mapper.AdminBlogUserMapper;
import com.superarilo.blogsystem.entity.BlogUser;
import com.superarilo.blogsystem.entity.BlogUserMeans;
import com.superarilo.blogsystem.mapper.BlogUserMapper;
import com.superarilo.blogsystem.mapper.BlogUserMeansMapper;
import com.superarilo.blogsystem.service.BlogUserService;
import com.superarilo.utils.*;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

@Service
public class BlogUserImpl extends ServiceImpl<BlogUserMapper, BlogUser> implements BlogUserService {

    private final IdGenerator idGenerator;
    private final BlogUserMapper blogUserMapper;
    private final BlogUserMeansMapper blogUserMeansMapper;
    private final Environment environment;
    private final AdminBlogUserMapper adminBlogUserMapper;
    public BlogUserImpl(BlogUserMapper blogUserMapper, IdGenerator idGenerator, BlogUserMeansMapper blogUserMeansMapper, Environment environment, AdminBlogUserMapper adminBlogUserMapper) {
        this.blogUserMapper = blogUserMapper;
        this.idGenerator = idGenerator;
        this.blogUserMeansMapper = blogUserMeansMapper;
        this.environment = environment;
        this.adminBlogUserMapper = adminBlogUserMapper;
    }

    @Override
    public JsonResult registerUser(String email, String password, String nickName, HttpServletResponse response) {
        if(!email.trim().matches(InputCheck.emailRegex)) return JsonResult.OK("邮箱不合法！");
        if(!password.trim().matches(InputCheck.passwordRegex)) return JsonResult.OK("密码不合法！");
        if(!nickName.trim().matches(InputCheck.nickNameRegex)) return JsonResult.OK("昵称不合法！");
        Map<String, Object> queryUserInfo = blogUserMapper.queryHaveUser(email);
        if (queryUserInfo == null){
            BlogUser blogUser = new BlogUser();
            long newUid = idGenerator.nextId();
            blogUser.setUid(newUid);
            blogUser.setEmail(email);
            blogUser.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
            blogUser.setRegisterTime(new Date());

            BlogUserMeans blogUserMeans = new BlogUserMeans();
            blogUserMeans.setUid(newUid);
            blogUserMeans.setAvatar(environment.getProperty(InputCheck.defaultAvatar));
            blogUserMeans.setNickName(nickName);
            blogUserMeans.setSex(null);
            blogUserMeans.setAge(null);

            BlogUserStatus blogUserStatus = new BlogUserStatus();
            blogUserStatus.setStatus(true);
            blogUserStatus.setUid(newUid);

            blogUserMapper.insert(blogUser);
            blogUserMeansMapper.insert(blogUserMeans);
            adminBlogUserMapper.insert(blogUserStatus);

            return JsonResult.OK("注册成功！");
        } else {
            return JsonResult.OK("邮箱被注册！");
        }
    }

    @Override
    public JsonResult loginUserByNormal(String email, String password, Long uid, HttpServletRequest request) {
        String token = request.getHeader("token");
        Map<String, Object> queryUserInfo;
        if (token != null && !token.equals("")){
            if (JwtUtils.verify(token, InputCheck.tokenSecret)){
                DecodedJWT jwt = JWT.decode(token);
                Long tokenUid = jwt.getClaim("uid").asLong();
                String tokenEmail = jwt.getClaim("email").asString();
                String tokenPassword = jwt.getClaim("password").asString();
                queryUserInfo = blogUserMapper.loginUser(tokenUid, tokenEmail, tokenPassword);
                if(queryUserInfo == null) return JsonResult.Error(404, "查询的用户被封禁或者不存在");
                if (!(Boolean) queryUserInfo.get("status")) return JsonResult.Error(401, "当前用户被禁用，如有疑问，请联系管理员");
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("user", queryUserInfo);
                return JsonResult.OK("登录成功！", jsonObject);
            } else {
                return JsonResult.Error(403, "token无效");
            }
        } else {
            if(uid == null && email == null || password == null) return JsonResult.Error(400, "请求缺少必要的参数");
            queryUserInfo = blogUserMapper.loginUser(uid, email,DigestUtils.md5DigestAsHex(password.getBytes()));
            if(queryUserInfo == null) return JsonResult.Error(404, "密码错误或者用户不存在！");
            if (!(Boolean) queryUserInfo.get("status")) return JsonResult.Error(401, "当前用户被禁用，如有疑问，请联系管理员");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token", JwtUtils.createJWT(email, (Long) queryUserInfo.get("uid"), password,InputCheck.tokenSecret, InputCheck.tokenExpireTime));
            jsonObject.put("user", queryUserInfo);
            jsonObject.put("expireTime", InputCheck.tokenExpireTime);
            return JsonResult.OK("登录成功！", jsonObject);
        }
    }
}
