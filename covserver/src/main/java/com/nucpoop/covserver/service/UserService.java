package com.nucpoop.covserver.service;

import java.util.List;

import com.nucpoop.covserver.model.User;


public interface UserService {
    List<User> selectUsers() throws Exception ;
    User selectUserByIndex(int index) throws Exception ;
    int insertUser(User user) throws Exception ;  
    User loginUser(String id) throws Exception;
}
