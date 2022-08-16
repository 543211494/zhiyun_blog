package org.nwpu.blog.controller;

import org.nwpu.blog.util.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Test {

    @Autowired
    private EmailSender sender;

    @RequestMapping(value = "/sendEmail",method = RequestMethod.POST)
    @ResponseBody
    public String sendEmail(){
        sender.send("lha602@163.com","code","修改密码");
        return "{\"status\":200}";
    }
}
