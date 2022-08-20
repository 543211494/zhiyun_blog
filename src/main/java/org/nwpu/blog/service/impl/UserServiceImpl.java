package org.nwpu.blog.service.impl;

import org.nwpu.blog.bean.User;
import org.nwpu.blog.mapper.UserMapper;
import org.nwpu.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
    public User getUserByUserName(String userName,boolean searchAll) {
        return userMapper.searchUserByUserName(userName,searchAll);
    }

    @Override
    public User getUserById(Integer id,boolean searchAll) {
        return userMapper.searchUserById(id,searchAll);
    }

    @Override
    public User getUserByNickName(String nickname,boolean searchAll) {
        return userMapper.searchUserByNickName(nickname,searchAll);
    }

    @Override
    public User getUserByEmail(String email,boolean searchAll) {
        return userMapper.searchUserByEmail(email,searchAll);
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

    @Override
    public boolean setUserRoleById(Integer userId, String role) {
        try{
            userMapper.setUserRoleById(userId,role);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteUserById(Integer userId) {
        try{
            userMapper.deleteUserById(userId);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<User> listUsers(Integer currentPage, Integer pageSize) {
        int start = (currentPage.intValue()-1)*pageSize;
        return userMapper.listUsers(start,pageSize);
    }

    @Override
    public Integer getTotalPages(Integer pageSize) {
        int size = userMapper.getUsersSize();
        if(size==0){
            return 0;
        }else{
            size = (size/pageSize) + (size%pageSize==0?0:1);
        }
        return size;
    }
}
