package org.nwpu.blog.controller;

import org.nwpu.blog.result.Response;
import org.nwpu.blog.util.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public class ArticleController {

    @RequestMapping(value = "/user/article/publishArticle",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String addArticle(){
        Response response = new Response<Object>();
        return JSON.toString(response);
    }
}
