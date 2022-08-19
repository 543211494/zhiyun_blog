package org.nwpu.blog.config;

import org.nwpu.blog.interceptor.AdminInterceptor;
import org.nwpu.blog.interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lzy
 * @date 2022/8/16
 * WebMVC配置类
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private UserInterceptor userInterceptor;

    @Autowired
    private AdminInterceptor adminInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(this.userInterceptor).addPathPatterns("/user/**","/api/user/**");
        registry.addInterceptor(this.adminInterceptor).addPathPatterns("/admin/**","/api/admin/**");
    }
}
