package org.nwpu.blog.service;

import org.apache.ibatis.annotations.Mapper;
import org.nwpu.blog.bean.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

public interface UserService {

    /**
     * 新增一个用户
     * @param user 要新增的用户
     * @return 操作成功与否
     */
    public boolean addUser(User user);

    /**
     * 根据用户名查询用户
     * @param userName 用户名
     * @return 查询到的用户
     */
    public User getUserByUserName(String userName,boolean searchAll);

    /**
     * 根据用户id查询用户
     * @param id 用户id
     * @return 查询到的用户
     */
    public User getUserById(Integer id,boolean searchAll);

    /**
     * 根据用户昵称查询用户
     * @param nickname 用户昵称
     * @return 查询到的用户
     */
    public User getUserByNickName(String nickname,boolean searchAll);

    /**
     * 根据用户昵称查询用户
     * @param email 用户昵称
     * @return 查询到的用户
     */
    public User getUserByEmail(String email,boolean searchAll);

    /**
     * 根据邮箱修改密码
     * @param email 用户邮箱
     * @param newPassword 新密码
     * @return 操作结果
     */
    public boolean updatePasswordByEmail(String email,String newPassword);

    /**
     * 根据用户id更新上次更新时间
     * @param userId 用户id
     * @param date 上次登录日期
     * @return 操作结果
     */
    public boolean updateLastLoginTimeById(Integer userId, Date date);

    /**
     * 根据用户id更新用户头像路径
     * @param userId 用户id
     * @param avatar 用户头像路径
     * @return 操作结果
     */
    public boolean updateAvatarById(Integer userId,String avatar);

    /**
     * 更新用户个人信息
     * @param user 要更新的用户实例
     * @return 操作结果
     */
    public boolean updateUser(User user);

    /**
     * 根据用户id修改用户权限
     * @param userId 用户id
     * @param role 用户新权限
     * @return 操作结果
     */
    public boolean setUserRoleById(Integer userId,String role);

    /**
     * 根据用户id删除用户
     * @param userId 用户id
     * @return 操作结果
     */
    public boolean deleteUserById(Integer userId);

    /**
     * 按页查询用户
     * @param currentPage 当前页码
     * @param pageSize 一页的数目
     * @return 查询结果
     */
    public List<User> listUsers(Integer currentPage,Integer pageSize);

    /**
     * 获取用户总页数
     * @param pageSize 一页的大小
     * @return 用户总页数
     */
    public Integer getTotalPages(Integer pageSize);
}
