package com.nucpoop.covserver.service;

import java.util.List;

import com.nucpoop.covserver.mapper.UserMapper;
import com.nucpoop.covserver.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> selectUsers() throws Exception {
        List<User> userList = userMapper.selectUsers();
        return userList;
    }

    @Override
    public User selectUserByID(int param) throws Exception {
        User user = userMapper.selectUserByID(param);
        return user;
    }

    @Override
    public int insertUser(User user) throws Exception {
        int result = userMapper.insertUser(user);
        return result;
    }
}