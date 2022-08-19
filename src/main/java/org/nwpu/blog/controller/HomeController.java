package org.nwpu.blog.controller;

import lombok.extern.slf4j.Slf4j;
import org.nwpu.blog.bean.User;
import org.nwpu.blog.result.Response;
import org.nwpu.blog.service.UserService;
import org.nwpu.blog.util.EmailSender;
import org.nwpu.blog.util.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author lzy
 * @date 2022/8/17
 * 无需权限操作控制类
 */
@Slf4j
@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailSender emailSender;

    @Autowired
    @Qualifier(value = "redisTemplate")
    private RedisTemplate redisTemplate;

    private static Random random=new Random();

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 验证码长度
     */
    private int length = 6;

    @RequestMapping(value = "/register",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String register(@RequestParam("userName")String userName,@RequestParam("nickname")String nickname,
                               @RequestParam("email")String email,@RequestParam("password")String password){
        Response response = new Response<Object>();
        response.setData(null);
        if(userService.getUserByUserName(userName,true)!=null){
            response.setCode(300);
            response.setMessage("用户名重复");
        }else if(userService.getUserByNickName(nickname,true)!=null){
            response.setCode(301);
            response.setMessage("昵称重复");
        }else if(userService.getUserByEmail(email,true)!=null){
            response.setCode(302);
            response.setMessage("邮箱已存在");
        }else{
            User user = new User(userName,password,nickname,email);
            try{
                userService.addUser(user);
            }catch (Exception e){
                response.setCode(400);
                response.setMessage("未知异常!");
                return JSON.toString(response);
            }
            response.setCode(200);
            response.setMessage("注册成功!");
        }
        return JSON.toString(response);
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String login(@RequestParam("userName")String userName,@RequestParam("password")String password){
        User user = userService.getUserByUserName(userName,false);
        Response response = new Response<Object>();
        /* 用户放置返回参数 */
        Map<String,Object> map = new HashMap<String,Object>();
        /* 校验用户名和密码的正确性 */
        if(user!=null&&user.getPassword().equals(password)){
            response.setCode(200);
            response.setMessage("登录成功");
            /* 更新登录时间 */
            userService.updateLastLoginTimeById(user.getId(),new Date());
            /* 使用uuid作为token,保障token的唯一性 */
            String token = UUID.randomUUID().toString();
            /* 设置token2个小时过期 */
            String key = "user-token-"+user.getId();
            redisTemplate.opsForValue().set(key,key+":"+token,2, TimeUnit.HOURS);
            map.put("token",key+":"+token);
            map.put("id",user.getId());
            map.put("userName",user.getUserName());
            map.put("password","");
            map.put("nickname",user.getNickname());
            map.put("email",user.getEmail());
            map.put("avatar",user.getAvatar()==null?"":user.getAvatar());
            map.put("registerTime",simpleDateFormat.format(user.getRegisterTime()));
            map.put("lastLoginTime",simpleDateFormat.format(user.getLastLoginTime()));
            map.put("role",user.getRole());
            response.setData(map);
        }else if(user==null){
            response.setCode(310);
            response.setMessage("用户不存在!");
            response.setData(null);
        }else {
            response.setCode(311);
            response.setMessage("密码错误!");
            response.setData(null);
        }
        return JSON.toString(response);
    }

    @RequestMapping(value = "/getCode",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String sendEmail(@RequestParam("email")String email){
        Response response = new Response<Object>();
        if(!EmailSender.isEmail(email)){
            response.setCode(320);
            response.setMessage("邮箱格式错误!");
        }else{
            try{
                StringBuffer buffer = new StringBuffer();
                for(int i = 0;i<this.length;i++){
                    buffer.append(random.nextInt(10));
                }
                String code = buffer.toString();
                emailSender.send(email,code,"修改密码");
                redisTemplate.opsForValue().set(email,code,2,TimeUnit.MINUTES);
            }catch (Exception e){
                response.setCode(320);
                response.setMessage(e.getMessage());
                return JSON.toString(response);
            }
            response.setCode(200);
            response.setMessage("邮件发送成功!");
        }
        response.setData(null);
        return JSON.toString(response);
    }

    @RequestMapping(value = "/updatePassword",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String updatePassword(@RequestParam("email")String email,@RequestParam("code")String code,@RequestParam("password")String password){
        Response response = new Response<Object>();
        if(code==null||code.isEmpty()){
            response.setCode(400);
            response.setMessage("验证码为空!");
        }else {
            String correctCode = (String) redisTemplate.opsForValue().get(email);
            if(correctCode==null){
                response.setCode(400);
                response.setMessage("验证码已失效!");
            }else if(correctCode.equals(code)){
                if(userService.updatePasswordByEmail(email,password)){
                    response.setCode(200);
                    response.setMessage("修改成功!");
                    redisTemplate.delete(email);
                }else {
                    response.setCode(400);
                    response.setMessage("修改失败!");
                }
            }
        }
        response.setData(null);
        return JSON.toString(response);
    }
}
