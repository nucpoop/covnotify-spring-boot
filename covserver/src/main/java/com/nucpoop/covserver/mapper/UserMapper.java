package com.nucpoop.covserver.mapper;

import java.util.List;
import java.util.Optional;

import com.nucpoop.covserver.model.User;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    List<User> selectUsers() throws Exception;
    User selectUserByIndex(int index) throws Exception;
    User selectUserByID(String id) throws Exception;
    int insertUser(User user) throws Exception;
    User findByEmail(String email) throws Exception;
    User findById(Long id) throws Exception;
    int updatePassword(String password) throws Exception;
}