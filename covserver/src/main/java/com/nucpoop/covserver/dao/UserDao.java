package com.nucpoop.covserver.dao;

import java.util.List;

import com.nucpoop.covserver.model.User;

public interface UserDao {
    List<User> selectUsers() throws Exception;
}
