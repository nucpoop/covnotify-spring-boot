package com.nucpoop.covserver.mapper;

import java.util.List;
import java.util.Optional;

import com.nucpoop.covserver.model.User;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insertUser(User user) throws Exception;

    User findByEmail(String email) throws Exception;

    User findById(Long id) throws Exception;

    int updatePassword(User password) throws Exception;

    int deleteUser(User user) throws Exception;

    List<User> notifyEmail(String time) throws Exception;

    int updateLocation(User user) throws Exception;

    int updateNofity(User user) throws Exception;
}