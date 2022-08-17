package org.nwpu.blog.controller;

import lombok.extern.slf4j.Slf4j;
import org.nwpu.blog.bean.User;
import org.nwpu.blog.result.Response;
import org.nwpu.blog.service.UserService;
import org.nwpu.blog.util.EmailSender;
import org.nwpu.blog.util.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author lzy
 * @date 2022/8/17
 * 用户相关操作控制类
 */
@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/user/uploadAvatar",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String uploadAvatar(@RequestParam("avatar") MultipartFile file, @RequestParam("token")String token,
                               HttpSession session) {
        Response response = new Response<Object>();
        Integer userId = Integer.parseInt(token.split(":")[0].split("-")[2]);
        /* 只允许上传xxx.png或xxx.jpg格式的文件作为头像 */
        String[] fileinfo = file.getOriginalFilename().split("\\.");
        if(!(fileinfo[fileinfo.length-1].equals("jpg")||fileinfo[fileinfo.length-1].equals("png"))){
            response.setCode(332);
            response.setMessage("文件格式错误，只允许上传.jpg或.png格式的文件!");
            return JSON.toString(response);
        }
        /* 获取图片资源存在的根目录 */
        ServletContext context = session.getServletContext();
        String root = context.getRealPath("imgs\\avatar");
        /* 判断存放头像图片的根目录是否存在，若不存在则新建文件夹 */
        File avatarRoot = new File(root);
        if(!avatarRoot.exists()){
            log.info("mkdir");
            avatarRoot.mkdirs();
        }
        /* 保存头像图片，文件名为用户id */
        String fileName = root + File.separator + userId + "."+fileinfo[fileinfo.length-1];
        try{
            file.transferTo(new File(fileName));
        }catch(IOException e){
            response.setCode(332);
            response.setMessage(e.getMessage());
            return JSON.toString(response);
        }
        /* 删除可能残留的头像 */
        fileName = root + File.separator + userId + "." + (fileinfo[fileinfo.length-1].equals("jpg")?"png":"jpg");
        File oldAvatar = new File(fileName);
        if(oldAvatar.exists()){
            oldAvatar.delete();
        }
        /* 更新用户头像路径 */
        userService.updateAvatarById(userId,"/imgs/avatar/"+userId + "." + fileinfo[fileinfo.length-1]);
        response.setCode(200);
        response.setMessage("头像上传成功!");
        return JSON.toString(response);
    }

    @RequestMapping(value = "/user/user/updateUser",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String updateUser(@RequestParam("nickname")String nickname,@RequestParam("email")String email,
                             @RequestParam("password")String password,@RequestParam("token")String token){
        Response response = new Response<Object>();
        Integer userId = Integer.parseInt(token.split(":")[0].split("-")[2]);
        if(nickname.isEmpty()||email.isEmpty()||password.isEmpty()){
            response.setCode(400);
            response.setMessage("参数不能为空!");
            return JSON.toString(response);
        }else if(!EmailSender.isEmail(email)){
            response.setCode(331);
            response.setMessage("邮箱格式错误!");
            return JSON.toString(response);
        }else{
            User other = userService.getUserByEmail(email);
            if(other!=null&&!other.getId().equals(userId)){
                response.setCode(400);
                response.setMessage("邮箱已被占用!");
                return JSON.toString(response);
            }
            other = null;
            other = userService.getUserByNickName(nickname);
            if(other!=null&&!other.getId().equals(userId)){
                response.setCode(330);
                response.setMessage("昵称已被占用!");
                return JSON.toString(response);
            }
        }
        User user = new User();
        user.setId(userId);
        user.setEmail(email);
        user.setNickname(nickname);
        user.setPassword(password);
        userService.updateUser(user);
        response.setCode(200);
        response.setMessage("修改成功!");
        return JSON.toString(response);
    }
}
