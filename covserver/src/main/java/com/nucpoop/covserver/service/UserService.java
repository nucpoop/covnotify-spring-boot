package com.nucpoop.covserver.service;

import java.util.List;

import com.nucpoop.covserver.model.User;
import com.nucpoop.covserver.model.UserEmailCheck;

public interface UserService {
    int insertUser(User user) throws Exception;

    UserEmailCheck checkEmail(String email) throws Exception;

    int updatePassword(User password) throws Exception;

    int resetPassword(User user) throws Exception;

    int withdrawalUser(User user) throws Exception;

    List<User> selectUsersForNotify(String time) throws Exception;

    int updateLocation(User user) throws Exception;

    int updateNotify(User user) throws Exception;
}
