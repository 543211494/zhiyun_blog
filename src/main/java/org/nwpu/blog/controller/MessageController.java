package org.nwpu.blog.controller;

import lombok.extern.slf4j.Slf4j;
import org.nwpu.blog.bean.Message;
import org.nwpu.blog.bean.User;
import org.nwpu.blog.result.Response;
import org.nwpu.blog.service.MessageService;
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

@Slf4j
@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    /**
     * 新增一条留言
     * @param content 留言内容
     * @param token 用户登录令牌
     * @return
     */
    @RequestMapping(value = "/user/message/publishMessage",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String publishMessage(@RequestParam("content")String content,@RequestParam("token")String token){
        Response response = new Response<Object>();
        if(content==null||content.isEmpty()){
            response.setCode(400);
            response.setMessage("留言不能为空!");
            return JSON.toString(response);
        }
        Integer authorId = User.getIdByToken(token);
        Message message = new Message(0,authorId,content);
        messageService.addMessage(message);
        response.setCode(200);
        response.setMessage("留言成功!");
        return JSON.toString(response);
    }

    /**
     * 回复一条留言
     * @param id 父评论id
     * @param content 评论内容
     * @param token 用户登录令牌
     * @return
     */
    @RequestMapping(value = "/admin/message/answerMessage",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String answerMessage(@RequestParam("pid")String id,@RequestParam("content")String content,
                                @RequestParam("token")String token){
        Response response = new Response<Object>();
        if(id==null||id.isEmpty()||content==null||content.isEmpty()){
            response.setCode(400);
            response.setMessage("参数不能为空!");
            return JSON.toString(response);
        }
        Integer pid = null;
        try{
            pid = Integer.parseInt(id);
        }catch (Exception e){
            response.setCode(400);
            response.setMessage("参数格式错误!");
            return JSON.toString(response);
        }
        Message message = messageService.getMessageById(pid);
        if(message==null){
            response.setCode(381);
            response.setMessage("留言不存在!");
            return JSON.toString(response);
        }else if(message.getPid().intValue()!=0){
            response.setCode(400);
            response.setMessage("不能回复二级评论!");
            return JSON.toString(response);
        }
        Integer authorId = User.getIdByToken(token);
        Message newMessage = new Message(pid,authorId,content);
        messageService.addMessage(newMessage);
        response.setCode(200);
        response.setMessage("回复成功!");
        return JSON.toString(response);
    }

    /**
     * 获取留言板
     * @param current 当前页码
     * @param size 一页的大小
     * @param token 用户登录令牌
     * @return
     */
    @RequestMapping(value = "/user/message/getAllMessages",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getAllMessageBoard(@RequestParam("currentPage")String current,@RequestParam("pageSize")String size,
                                 @RequestParam("token")String token){
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
        List<Message> messageList = new ArrayList<Message>();
        pageNum = messageService.getMessageBoard(currentPage,pageSize,messageList);
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("messageList",messageList);
        data.put("currentPage",currentPage);
        data.put("pageSize",pageSize);
        data.put("pageNum",pageNum);
        response.setCode(200);
        response.setMessage("查询成功!");
        response.setData(data);
        return JSON.toString(response);
    }

    /**
     * 查询全部评论
     * @param choice 0是查看所有一级留言 1是查看没有回复的一级留言
     * @param size 一页的大小
     * @param current 当前页码
     * @param token 用户登录令牌
     * @return
     */
    @RequestMapping(value = "/admin/message/getAllMessages",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getMessageBoard(@RequestParam("hasReply")String choice,@RequestParam("pageSize")String size,
                                  @RequestParam("currentPage")String current,@RequestParam("token")String token){
        Response response = new Response<Object>();
        Integer hasReply = null;
        Integer currentPage = null;
        Integer pageSize = null;
        Integer pageNum = null;
        boolean searchAll;
        try{
            hasReply = Integer.parseInt(choice);
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
        searchAll = hasReply.intValue()==0;
        List<Message> messageList = new ArrayList<Message>();
        pageNum = messageService.getMessagesReply(currentPage,pageSize,messageList,searchAll);
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("messageList",messageList);
        data.put("currentPage",currentPage);
        data.put("pageSize",pageSize);
        data.put("pageNum",pageNum);
        response.setCode(200);
        response.setMessage("查询成功!");
        response.setData(data);
        return JSON.toString(response);
    }
}
