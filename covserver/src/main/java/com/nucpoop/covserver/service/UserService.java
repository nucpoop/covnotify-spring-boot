package com.nucpoop.covserver.service;

import java.util.List;

import com.nucpoop.covserver.model.User;
import com.nucpoop.covserver.model.UserEmailCheck;


public interface UserService {
    List<User> selectUsers() throws Exception ;
    User selectUserByIndex(int index) throws Exception ;
    int insertUser(User user) throws Exception ;  
    User selectUserByID(String id) throws Exception;
    UserEmailCheck checkEmail(String email) throws Exception;
    int updatePassword(String password) throws Exception;
}
