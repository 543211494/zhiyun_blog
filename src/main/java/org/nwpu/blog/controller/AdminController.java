package org.nwpu.blog.controller;

import org.nwpu.blog.bean.User;
import org.nwpu.blog.result.Response;
import org.nwpu.blog.service.UserService;
import org.nwpu.blog.util.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理员控制器类
 * @author lzy
 * @date 2022/8/17
 */
@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    /**
     * 修改用户权限
     * @param token 用户登录令牌
     * @param role 用户角色
     * @param id 要修改的用户的id
     * @return
     */
    @RequestMapping(value = "/admin/user/updateUserRole",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String setUserRole(@RequestParam("token")String token, @RequestParam("role")String role,
                              @RequestParam("userId")String id){
        Response response = new Response<Object>();
        if(role.equals(User.USER)||role.equals(User.ADMIN)){
            Integer userId = null;
            try{
                userId = Integer.parseInt(id);
            }catch (Exception e){
                response.setCode(400);
                response.setMessage("id格式错误!");
                return JSON.toString(response);
            }
            if(id.equals(token.split(":")[0].split("-")[2])){
                response.setCode(400);
                response.setMessage("不能修改自己的权限!");
                return JSON.toString(response);
            }
            userService.setUserRoleById(userId,role);
            response.setCode(200);
            response.setMessage("修改成功!");
        }else{
            response.setCode(400);
            response.setMessage("修改失败,权限参数异常!");
        }
        return JSON.toString(response);
    }

    /**
     * 删除用户
     * @param id 用户id
     * @param token 用户登录令牌
     * @return
     */
    @RequestMapping(value = "/admin/user/deleteUser",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String deleteUser(@RequestParam("userId")String id,@RequestParam("token")String token){
        Response response = new Response<Object>();
        Integer userId = null;
        try{
            userId = Integer.parseInt(id);
        }catch (Exception e){
            response.setCode(400);
            response.setMessage("id格式错误!");
            return JSON.toString(response);
        }
        if(id.equals(token.split(":")[0].split("-")[2])){
            response.setCode(400);
            response.setMessage("不能删除自己!");
            return JSON.toString(response);
        }
        userService.deleteUserById(userId);
        response.setCode(200);
        response.setMessage("删除成功!");
        return JSON.toString(response);
    }

    /**
     * 按页获取用户列表
     * @param current 当前页码
     * @param size 一页的大小
     * @param token 用户登录令牌
     * @param token 用户登录令牌
     * @return
     */
    @RequestMapping(value = "/admin/user/getAllUsers",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getUsers(@RequestParam("currentPage")String current,@RequestParam("pageSize")String size,
                           @RequestParam("token")String token){
        Response response = new Response<Object>();
        Integer currentPage = null;
        Integer pageSize = null;
        try{
            currentPage = Integer.parseInt(current);
            pageSize = Integer.parseInt(size);
        }catch (Exception e){
            response.setCode(400);
            response.setMessage("参数格式错误!");
            return JSON.toString(response);
        }
        if(currentPage.intValue()<=0||pageSize.intValue()<=0){
            response.setCode(400);
            response.setMessage("参数不能<=0!");
            return JSON.toString(response);
        }
        Map<String,Object> data = new HashMap<String,Object>();
        List<User> userList = userService.listUsers(currentPage,pageSize);
        Integer pageNum = userService.getTotalPages(pageSize);
        data.put("userList",userList);
        data.put("currentPage",currentPage);
        data.put("pageSize",pageSize);
        data.put("pageNum",userService.getTotalPages(pageSize));
        response.setData(data);
//        if(currentPage.intValue()>pageNum.intValue()){
//            response.setCode(400);
//            response.setMessage("已经超过最后一页!");
//            return JSON.toString(response);
//        }
        response.setCode(200);
        response.setMessage("查询成功!");
        return JSON.toString(response);
    }
}
