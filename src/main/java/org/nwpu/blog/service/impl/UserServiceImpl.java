package org.nwpu.blog.service.impl;

import org.nwpu.blog.bean.User;
import org.nwpu.blog.mapper.UserMapper;
import org.nwpu.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean addUser(User user) {
        try{
            userMapper.insertUser(user);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public User getUserByUserName(String userName) {
        return userMapper.searchUserByUserName(userName);
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.searchUserById(id);
    }

    @Override
    public User getUserByNickName(String nickname) {
        return userMapper.searchUserByNickName(nickname);
    }

    @Override
    public User getUserByEmail(String email) {
        return userMapper.searchUserByEmail(email);
    }

    @Override
    public boolean updatePasswordByEmail(String email, String newPassword) {
        try{
            userMapper.updatePasswordByEmail(email,newPassword);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean updateLastLoginTimeById(Integer userId, Date date) {
        try{
            userMapper.updateLastLoginTimeById(userId,date);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean updateAvatarById(Integer userId, String avatar) {
        try{
            userMapper.updateAvatarUrlById(avatar,userId);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean updateUser(User user) {
        try{
            userMapper.updateUser(user);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
