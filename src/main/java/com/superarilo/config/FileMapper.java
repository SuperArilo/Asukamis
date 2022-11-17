package com.superarilo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FileMapper implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            registry.addResourceHandler("/image/**").addResourceLocations("file:D:/TempCenter/imageBlog/");
        } else {  //linux 和mac
            registry.addResourceHandler("/image/**").addResourceLocations("file:/www/wwwroot/imageBlog/");
        }
    }
}
