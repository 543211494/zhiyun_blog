package org.nwpu.blog.controller;

import lombok.extern.slf4j.Slf4j;
import org.nwpu.blog.util.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class Test {

    @Autowired
    private EmailSender sender;

    @RequestMapping(value = "/sendEmail",method = RequestMethod.POST)
    @ResponseBody
    public String sendEmail(@RequestParam("to") String to){
        sender.send(to,"code","修改密码");
        log.info("邮件发送至:"+to);
        return "{\"status\":200}";
    }
}
