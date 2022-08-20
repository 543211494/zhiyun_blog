package org.nwpu.blog.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @author lzy
 * @date 2022/8/16
 * 用户类
 */
@Data
public class User {

    /**
     * 用户权限————超级管理员
     */
    public static final String SUPER = "SUPER";

    /**
     * 用户权限————管理员
     */
    public static final String ADMIN = "ADMIN";

    /**
     * 用户权限————普通用户
     */
    public static final String USER = "USER";

    /**
     * 默认用户头像路径
     */
    private static String defaultAvatar = "/api/imgs/avatar/avatar.png";

    /**
     * 作者id
     */
    private Integer id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户头像URL
     */
    private String avatar;

    /**
     * 用户注册时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date registerTime;

    /**
     * 用户上次登录时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastLoginTime;

    /**
     * 是否删除
     */
    private boolean isDeleted;

    /**
     * 用户权限
     */
    private String role;

    /**
     * 根据token获取用户id
     * @param token
     * @return
     */
    public static Integer getIdByToken(String token){
        return Integer.parseInt(token.split(":")[0].split("-")[2]);
    }

    public User(Integer id, String userName, String password, String nickname, String email, String avatar, Date registerTime, Date lastLoginTime, boolean isDeleted, String role) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.avatar = avatar;
        this.registerTime = registerTime;
        this.lastLoginTime = lastLoginTime;
        this.isDeleted = isDeleted;
        this.role = role;
    }

    public User(String userName, String password, String nickname, String email) {
        Date currentTime = new Date();
        this.userName = userName;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.registerTime = currentTime;
        this.lastLoginTime = currentTime;
        this.isDeleted = false;
        this.role = User.USER;
        this.avatar = this.defaultAvatar;
    }

    public User() {
        Date currentTime = new Date();
        this.registerTime = currentTime;
        this.lastLoginTime = currentTime;
        this.isDeleted = false;
        this.role = User.USER;
        this.avatar = this.defaultAvatar;
    }
}
