package org.nwpu.blog.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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

//        System.out.println(request.getHeader("Authorization"));
//        if(request.getParameter("userName").equals("abc")){
//            throw new RuntimeException("test!");
//        }

        log.info(request.getRequestURI());
//        System.out.println("==========body=========");
//        Collection<Part> parts = request.getParts();
//        for(Part part:parts){
//            System.out.println(part.getName());
//        }

        String token = request.getParameter("token");
        if(token==null){
            throw new RuntimeException("1");
        }
        String[] tokenInfo = token.split(":");
        if(tokenInfo.length!=2){
            throw new RuntimeException("1");
        }

//        String verification = (String) redisTemplate.opsForValue().get(tokenInfo[0]);
//        log.info("token:"+verification);
//        if(verification==null||!verification.equals(token)){
//            throw new RuntimeException("1");
//        }
        if(!redisTemplate.opsForSet().isMember(tokenInfo[0],token)){
            throw new RuntimeException("1");
        }

        return true;
    }
}
