package com.superarilo.admin.controller;

import com.superarilo.admin.service.ABlogUserService;
import com.superarilo.utils.InputCheck;
import com.superarilo.utils.JsonResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system/user")
public class ABlogUserController {

    private final ABlogUserService aBlogUserService;

    public ABlogUserController(ABlogUserService aBlogUserService) {
        this.aBlogUserService = aBlogUserService;
    }

    @GetMapping("/list")
    public JsonResult getBlogUserList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                  @RequestParam(value = "pageSize", defaultValue = "15") Integer pageSize,
                                  @RequestParam(value = "uid", required = false) Long uid,
                                  @RequestParam(value = "nickName", required = false) String nickName,
                                  @RequestParam(value = "email", required = false) String email,
                                  @RequestParam(value = "status", required = false) Boolean status,
                                  @RequestParam(value = "category", required = false) Integer category){
        return JsonResult.OK("成功！", aBlogUserService.queryBlogUser(pageNum, pageSize, uid, nickName, email, status, category));
    }
    @DeleteMapping("/delete")
    public JsonResult deleteBlogUser(@RequestBody List<Long> uidList){
        if (uidList.size() == 0) return JsonResult.Error(400, "服务器接收到的参数为空");
        return JsonResult.OK(aBlogUserService.deleteBlogUser(uidList));
    }
    @PutMapping("/modify")
    public JsonResult modifyBlogUserInfo(@RequestBody Map<String, Object> objectMap){
        if (objectMap.size() == 0) return JsonResult.Error(400, "服务器接收到的参数为空");
        return JsonResult.OK(aBlogUserService.changeBlogUserInfo(objectMap));
    }
    
    @PostMapping("/create")
    public JsonResult createBlogUser(@RequestParam("nickName") String nickName,
                                     @RequestParam("email") String email,
                                     @RequestParam("password") String password){
        if ((nickName == null || nickName.equals("")) || (email == null || email.equals("")) || (password == null || password.equals("")))
            return JsonResult.Error(400, "服务器接收到的参数为空");
        if (!password.trim().matches(InputCheck.passwordRegex)) return JsonResult.Error(400, "密码格式错误");
        if (!email.trim().matches(InputCheck.emailRegex)) return JsonResult.Error(400, "邮箱格式错误");
        if (!nickName.trim().matches(InputCheck.nickNameRegex)) return JsonResult.Error(400, "用户昵称格式错误");
        return aBlogUserService.createBlogUser(nickName, email, password);
    }

}
