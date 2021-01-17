package com.nucpoop.covserver.dao;

import java.util.List;

import com.nucpoop.covserver.model.User;

public interface UserDao {
    //select
    List<User> selectUsers() throws Exception;
    User selectUserByID(int param) throws Exception;

    //insert
    int insertUser(User user) throws Exception;
}
