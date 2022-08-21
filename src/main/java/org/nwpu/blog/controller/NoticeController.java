package org.nwpu.blog.controller;

import org.nwpu.blog.bean.Notice;
import org.nwpu.blog.bean.User;
import org.nwpu.blog.result.Response;
import org.nwpu.blog.service.NoticeService;
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
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    /**
     * 发布公告
     * @param content 公告内容
     * @param order 公告优先级
     * @param token 用户登录令牌
     * @return
     */
    @RequestMapping(value = "/admin/notice/publishNotice",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String publishNotice(@RequestParam("content")String content,@RequestParam("order")String order,
                                @RequestParam("token")String token){
        Response response = new Response<Object>();
        Integer noticeOrder = null;
        try{
            noticeOrder = Integer.parseInt(order);
        }catch(Exception e){
            response.setCode(341);
            response.setMessage("公告优先级设置异常!");
            return JSON.toString(response);
        }
        if(noticeOrder.intValue()!=1&&noticeOrder.intValue()!=2){
            response.setCode(341);
            response.setMessage("公告优先级设置异常!");
            return JSON.toString(response);
        }
        if(content.length()>100){
            response.setCode(340);
            response.setMessage("公告过长!");
            return JSON.toString(response);
        }
        Integer publisherId = User.getIdByToken(token);
        Notice notice = new Notice(publisherId,content,noticeOrder);
        noticeService.insertNotice(notice);
        response.setCode(200);
        response.setMessage("发布成功!");
        return JSON.toString(response);
    }

    /**
     * 修改公告
     * @param id 公告id
     * @param content 公告内容
     * @param order 公告优先级
     * @param token 用户登录令牌
     * @return
     */
    @RequestMapping(value = "/admin/notice/updateNotice",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String updateNotice(@RequestParam("noticeId")String id,@RequestParam("content")String content,
                               @RequestParam("order")String order,@RequestParam("token")String token){
        Response response = new Response<Object>();
        Integer noticeId = null;
        Integer noticeOrder = null;
        Integer publisherId = User.getIdByToken(token);
        try{
            noticeId = Integer.parseInt(id);
            noticeOrder = Integer.parseInt(order);
        }catch(Exception e){
            response.setCode(400);
            response.setMessage("参数格式错误!");
            return JSON.toString(response);
        }
        if(noticeOrder.intValue()!=1&&noticeOrder.intValue()!=2){
            response.setCode(341);
            response.setMessage("公告优先级设置异常!");
            return JSON.toString(response);
        }
        if(content.length()>100){
            response.setCode(340);
            response.setMessage("公告过长!");
            return JSON.toString(response);
        }
        Notice notice = new Notice(publisherId,content,noticeOrder);
        notice.setId(noticeId);
        noticeService.updateNotice(notice);
        response.setCode(200);
        response.setMessage("修改成功!");
        return JSON.toString(response);
    }

    /**
     * 删除公告
     * @param id 公告id
     * @param token 用户登录令牌
     * @return
     */
    @RequestMapping(value = "/admin/notice/deleteNotice",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String deleteNotice(@RequestParam("noticeId")String id,@RequestParam("token")String token){
        Response response = new Response<Object>();
        Integer noticeId = null;
        try{
            noticeId = Integer.parseInt(id);
        }catch (Exception e){
            response.setCode(400);
            response.setMessage("参数格式错误!");
            return JSON.toString(response);
        }
        noticeService.deleteNoticeById(noticeId);
        response.setCode(200);
        response.setMessage("删除成功!");
        return JSON.toString(response);
    }

    /**
     * 管理员搜索公告
     * @param current 当前页码
     * @param size 一页的大小
     * @param content 公告内容
     * @param token 用户登录令牌
     * @return
     */
    @RequestMapping(value = "/admin/notice/getAllNotice",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getNoticeAdmin(@RequestParam("currentPage")String current,@RequestParam("pageSize")String size,
                                 @RequestParam(value = "content",required = false,defaultValue = "")String content,
                                 @RequestParam("token")String token){
        Response response = new Response<Object>();
        Integer currentPage = null;
        Integer pageSize = null;
        Integer pageNum = null;
        try{
            currentPage = Integer.parseInt(current);
            pageSize = Integer.parseInt(size);
        }catch(Exception e){
            response.setCode(400);
            response.setMessage("参数格式错误!");
            return JSON.toString(response);
        }
        if(currentPage.intValue()<=0||pageSize.intValue()<=0){
            response.setCode(400);
            response.setMessage("参数不能小于0!");
            return JSON.toString(response);
        }
        if(content.isEmpty()){
            content = null;
        }
        List<Notice> noticeList = new ArrayList<Notice>();
        pageNum = noticeService.getNotices(currentPage,pageSize,noticeList,content);
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("noticeList",noticeList);
        data.put("currentPage",currentPage);
        data.put("pageSize",pageSize);
        data.put("pageNum",pageNum);
        response.setCode(200);
        response.setMessage("查询成功!");
        response.setData(data);
        return JSON.toString(response);
    }

    /**
     * 用户查询公告,默认5条
     * @param token 用户登录令牌
     * @return
     */
    @RequestMapping(value = "/user/notice/getAllNotice",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getNoticeUser(@RequestParam("token")String token){
        Response response = new Response<Object>();
        List<Notice> noticeList = new ArrayList<Notice>();
        noticeService.getNotices(1,5,noticeList,null);
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("noticeList",noticeList);
        response.setCode(200);
        response.setMessage("查询成功!");
        response.setData(data);
        return JSON.toString(response);
    }
}
