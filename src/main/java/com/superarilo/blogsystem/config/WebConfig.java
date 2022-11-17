package com.superarilo.blogsystem.config;


import com.superarilo.blogsystem.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/api/user/**")
                .excludePathPatterns("/api/user/login", "/api/user/register", "/image/**")
                .addPathPatterns("/api/article/**")
                .excludePathPatterns("/api/article/list", "/api/article/content", "/api/article/comment/list");
    }
}
