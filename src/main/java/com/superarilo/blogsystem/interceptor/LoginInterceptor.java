package com.superarilo.blogsystem.interceptor;


import com.alibaba.fastjson.JSONObject;
import com.superarilo.utils.InputCheck;
import com.superarilo.utils.JsonResult;
import com.superarilo.utils.JwtUtils;
import lombok.SneakyThrows;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");
        if (token == null || !JwtUtils.verify(token, InputCheck.tokenSecret)){
            setResponse(response, 403, "登录失效，请重新登录！");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    @SneakyThrows
    public void setResponse(HttpServletResponse response, int code, String message){
        response.setStatus(code);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-type", "application/json");
        response.getWriter().write(JSONObject.toJSONString(JsonResult.Error(code, message)));
    }
}
