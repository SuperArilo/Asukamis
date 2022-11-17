package com.superarilo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.superarilo")
@MapperScan({"com.superarilo.blogsystem.mapper", "com.superarilo.admin.mapper", "com.superarilo.mapper"})
public class BlogSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogSystemApplication.class, args);
    }

}
