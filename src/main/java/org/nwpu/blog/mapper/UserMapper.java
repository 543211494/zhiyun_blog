package org.nwpu.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.nwpu.blog.bean.User;

import java.util.Date;

@Mapper
public interface UserMapper {

    /**
     * 新增一个用户
     * @param user 要新增的用户
     * @return 操作结果
     */
    public boolean insertUser(@Param("user")User user);

    /**
     * 根据用户名查询用户
     * @param userName 用户名
     * @param searchAll 是否查询所有(包括被删除的)
     * @return 查询到的用户
     */
    public User searchUserByUserName(@Param("userName") String userName,@Param("searchAll")boolean searchAll);

    /**
     * 根据用户id查询用户
     * @param id 用户id
     * @param searchAll 是否查询所有(包括被删除的)
     * @return 查询到的用户
     */
    public User searchUserById(@Param("id") Integer id,@Param("searchAll")boolean searchAll);

    /**
     * 根据用户昵称查询用户
     * @param nickname 用户昵称
     * @param searchAll 是否查询所有(包括被删除的)
     * @return 查询到的用户
     */
    public User searchUserByNickName(@Param("nickname") String nickname,@Param("searchAll")boolean searchAll);

    /**
     * 根据用户邮箱查询用户
     * @param email 用户邮箱
     * @param searchAll 是否查询所有(包括被删除的)
     * @return 查询到的用户
     */
    public User searchUserByEmail(@Param("email") String email,@Param("searchAll")boolean searchAll);

    /**
     * 根据邮箱修改密码
     * @param newPassword 新密码
     * @return 操作结果
     */
    public boolean updatePasswordByEmail(@Param("email")String email,@Param("newPassword")String newPassword);

    /**
     * 根据用户id更新上次登录时间
     * @param id 用户id
     * @param date 登录时间
     * @return 操作结果
     */
    public boolean updateLastLoginTimeById(@Param("id")Integer id,@Param("date") Date date);

    /**
     * 根据用户id更新头像路径
     * @param avatar 用户头像路径
     * @param id 用户id
     * @return 操作结果
     */
    public boolean updateAvatarUrlById(@Param("avatar")String avatar,@Param("id")Integer id);

    /**
     * 更新用户个人资料
     * @param user 要更新的用户实例
     * @return 操作结果
     */
    public boolean updateUser(@Param("user") User user);
}
