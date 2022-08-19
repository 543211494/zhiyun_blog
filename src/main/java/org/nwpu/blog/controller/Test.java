package org.nwpu.blog.controller;

import lombok.extern.slf4j.Slf4j;
import org.nwpu.blog.service.ArticleService;
import org.nwpu.blog.service.UserService;
import org.nwpu.blog.util.EmailSender;
import org.nwpu.blog.util.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

/**
 * @author lzy
 * @date 2022/8/16
 * 用于测试的controller
 */
@Slf4j
@Controller
public class Test {

    @Autowired
    private EmailSender sender;

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/sendEmail",method = RequestMethod.POST)
    @ResponseBody
    public String sendEmail(@RequestParam("to") String to){
        sender.send(to,"code","修改密码");
        log.info("邮件发送至:"+to);
        return "{\"status\":200}";
    }

    @RequestMapping(value = "/user/register",method = RequestMethod.POST)
    @ResponseBody
    public String register(@RequestParam("userName") String userName){
//        log.info(userName);
//        String preUuid = UUID.randomUUID().toString();
//        log.info(preUuid);
//        log.info(token);
//        System.out.println(JSON.toString(userService.getUserByUserName("user001")));
//        System.out.println(JSON.toString(userService.getUserByUserName("user")));
//        System.out.println(JSON.toString(userService.getUserById(8)));
//        System.out.println(JSON.toString(userService.getUserById(9)));
//        System.out.println(JSON.toString(userService.getUserByEmail("lha602@163.com")));
//        System.out.println(JSON.toString(userService.getUserByEmail("123")));
//        System.out.println(JSON.toString(userService.getUserByNickName("nickname001")));
//        System.out.println(JSON.toString(userService.getUserByNickName("nickname")));
        return JSON.toString(articleService.getArticleById(1,true,true,true));
    }
}
