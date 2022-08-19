package org.nwpu.blog.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lzy
 * @date 2022/8/16
 * 用户权限拦截器
 */
@Slf4j
@Component
public class UserInterceptor implements HandlerInterceptor {

    @Autowired
    @Qualifier(value = "redisTemplate")
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //System.out.println(request.getHeader("Authorization"));
//        if(request.getParameter("userName").equals("abc")){
//            throw new RuntimeException("test!");
//        }
        log.info("URL:"+request.getRequestURI());
        log.info("token:"+request.getParameter("token"));
        return true;
    }
}
