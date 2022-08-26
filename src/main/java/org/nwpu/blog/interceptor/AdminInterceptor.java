package org.nwpu.blog.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.nwpu.blog.bean.User;
import org.nwpu.blog.service.UserService;
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
public class AdminInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier(value = "redisTemplate")
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info(request.getRequestURI());
        String token = request.getParameter("token");
        if(token==null){
            throw new RuntimeException("1");
        }
        String[] tokenInfo = token.split(":");
        if(tokenInfo.length!=2){
            throw new RuntimeException("1");
        }
        Integer userId;
        try{
            userId = Integer.parseInt(tokenInfo[0].split("-")[2]);
        }catch(Exception e){
            throw new RuntimeException("1");
        }

//        String verification = (String) redisTemplate.opsForValue().get("user-token-"+userId);
//        if(verification==null||!verification.equals(token)){
//            throw new RuntimeException("1");
//        }

        if(!redisTemplate.opsForSet().isMember(tokenInfo[0],token)){
            throw new RuntimeException("1");
        }

        User user = userService.getUserById(userId,false);
        if(user.getRole().equals(User.USER)){
            throw new RuntimeException("2");
        }
        if((request.getRequestURI().endsWith("updateUserRole")||request.getRequestURI().endsWith("deleteUser"))&&!user.getRole().equals(User.SUPER)){
            throw new RuntimeException("2");
        }
        return true;
    }
}
