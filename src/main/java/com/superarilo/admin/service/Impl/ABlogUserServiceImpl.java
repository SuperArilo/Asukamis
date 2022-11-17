package com.superarilo.admin.service.Impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.superarilo.admin.entity.BlogUserStatus;
import com.superarilo.admin.mapper.AdminBlogUserMapper;
import com.superarilo.admin.service.ABlogUserService;
import com.superarilo.admin.vo.AdminBlogUserVo;
import com.superarilo.blogsystem.entity.BlogUser;
import com.superarilo.blogsystem.entity.BlogUserMeans;
import com.superarilo.blogsystem.mapper.BlogUserMapper;
import com.superarilo.blogsystem.mapper.BlogUserMeansMapper;
import com.superarilo.utils.IdGenerator;
import com.superarilo.utils.InputCheck;
import com.superarilo.utils.JsonResult;
import com.superarilo.utils.PageCustom;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ABlogUserServiceImpl implements ABlogUserService {

    private final AdminBlogUserMapper adminBlogUserMapper;

    private final BlogUserMapper blogUserMapper;
    private final BlogUserMeansMapper blogUserMeansMapper;

    private final Environment environment;
    private final IdGenerator idGenerator;
    public ABlogUserServiceImpl(AdminBlogUserMapper adminBlogUserMapper, IdGenerator idGenerator, Environment environment, BlogUserMapper blogUserMapper, BlogUserMeansMapper blogUserMeansMapper) {
        this.adminBlogUserMapper = adminBlogUserMapper;
        this.idGenerator = idGenerator;
        this.environment = environment;
        this.blogUserMapper = blogUserMapper;
        this.blogUserMeansMapper = blogUserMeansMapper;
    }

    @Override
    public PageCustom<AdminBlogUserVo> queryBlogUser(Integer pageNum, Integer pageSize, Long uid, String nickName, String email, Boolean status, Integer category) {
        Page<AdminBlogUserVo> userPage = adminBlogUserMapper.queryBlogUser(new Page<>(pageNum, pageSize), uid, nickName, email, status, category);
        return new PageCustom<>(userPage.getTotal(), userPage.getRecords(), userPage.getPages(),userPage.getCurrent(), userPage.getSize());
    }

    @Override
    public String deleteBlogUser(List<Long> uidList) {
        return "成功删除" + adminBlogUserMapper.deleteBlogUser(uidList) + "条记录";
    }

    @Override
    public String changeBlogUserInfo(Map<String, Object> object) {
        return "成功修改" + adminBlogUserMapper.updateBlogUserInfo(object) + "条数据";
    }

    @Override
    public JsonResult createBlogUser(String nickName, String email, String password) {

        Map<String, Object> objectMap = blogUserMapper.queryHaveUser(email);

        if (objectMap == null){
            Long uidNew = idGenerator.nextId();
            BlogUser blogUser = new BlogUser();
            blogUser.setUid(uidNew);
            blogUser.setEmail(email);
            blogUser.setRegisterTime(new Date());
            blogUser.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));

            BlogUserMeans blogUserMeans = new BlogUserMeans();
            blogUserMeans.setAvatar(environment.getProperty(InputCheck.defaultAvatar));
            blogUserMeans.setUid(uidNew);
            blogUserMeans.setNickName(nickName);

            BlogUserStatus blogUserStatus = new BlogUserStatus();
            blogUserStatus.setUid(uidNew);
            blogUserStatus.setStatus(true);

            blogUserMapper.insert(blogUser);
            blogUserMeansMapper.insert(blogUserMeans);
            adminBlogUserMapper.insert(blogUserStatus);

            return JsonResult.OK("新增成功");
        } else {
            return JsonResult.Error(400, "该邮箱已被注册");
        }
    }
}
