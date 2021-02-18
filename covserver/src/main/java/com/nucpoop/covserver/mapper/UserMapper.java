package com.nucpoop.covserver.mapper;

import java.util.List;

import com.nucpoop.covserver.model.User;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    List<User> selectUsers() throws Exception;
    User selectUserByID(int param) throws Exception;
    int insertUser(User user) throws Exception;
}