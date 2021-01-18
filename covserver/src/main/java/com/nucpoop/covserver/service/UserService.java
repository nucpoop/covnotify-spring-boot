package com.nucpoop.covserver.service;

import java.util.List;

import com.nucpoop.covserver.dao.UserDao;
import com.nucpoop.covserver.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDao {

    @Autowired
    UserDao userDao;
    
    @Override
    public List<User> selectUsers() throws Exception {
        List<User> userList = userDao.selectUsers();
        return userList;
    }

    @Override
    public User selectUserByID(int param) throws Exception {
        User user = userDao.selectUserByID(param);
        return user;
    }

    @Override
    public int insertUser(User user) throws Exception {
        int result = userDao.insertUser(user);
        return result;
    }
    
}
