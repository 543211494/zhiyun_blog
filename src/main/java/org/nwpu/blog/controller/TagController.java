package org.nwpu.blog.controller;

import org.nwpu.blog.bean.Tag;
import org.nwpu.blog.result.Response;
import org.nwpu.blog.service.TagService;
import org.nwpu.blog.util.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TagController {

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/user/tag/getAllTag",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getTags(@RequestParam("currentPage")String current,@RequestParam("pageSize")String size){
        Response response = new Response<Object>();
        Integer currentPage = null;
        Integer pageSize = null;
        Integer pageNum = null;
        try{
            currentPage = Integer.parseInt(current);
            pageSize = Integer.parseInt(size);
        }catch (Exception e){
            response.setCode(400);
            response.setMessage("参数格式有误!");
            return JSON.toString(response);
        }
        if(currentPage.intValue()<=0||pageSize.intValue()<=0){
            response.setCode(400);
            response.setMessage("页码和页大小不能小于0!");
            return JSON.toString(response);
        }
        List<Tag> tagList = new ArrayList<Tag>();
        pageNum = tagService.getTags(currentPage,pageSize,tagList);
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("tagList",tagList);
        data.put("currentPage",currentPage);
        data.put("pageSize",pageSize);
        data.put("pageNum",pageNum);
        response.setCode(200);
        response.setMessage("查询成功!");
        response.setData(data);
        return JSON.toString(response);
    }
}
