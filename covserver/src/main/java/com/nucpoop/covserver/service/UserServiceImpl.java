package com.nucpoop.covserver.service;

import java.util.List;

import javax.transaction.Transactional;

import com.nucpoop.covserver.mapper.UserMapper;
import com.nucpoop.covserver.model.User;
import com.nucpoop.covserver.model.UserEmailCheck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public int insertUser(User user) throws Exception {
        int result = userMapper.insertUser(user);
        return result;
    }

    @Override
    public UserEmailCheck checkEmail(String email) throws Exception {
        User user = userMapper.findByEmail(email);
        UserEmailCheck result = new UserEmailCheck(false);

        if (user == null) {
            result.setCanEmailSignUp(true);
        } else {
            result.setCanEmailSignUp(false);
        }

        return result;
    }

    @Override
    @Transactional
    public int withdrawalUser(User user) throws Exception {
        User compare = userMapper.findByEmail(user.getEmail());
        if(passwordEncoder.matches(user.getPassword(), compare.getPassword())){
            return userMapper.deleteUser(user);
        }
            return 0;
    }

    @Override
    public int updatePassword(User password) throws Exception {
        return userMapper.updatePassword(password);
    }

    @Override
    @Transactional
    public int resetPassword(User user) throws Exception {
        User userObj = userMapper.findByEmail(user.getEmail());
        int passwordTemp = (int) (Math.random() * 8999 + 1000);
        String password = passwordEncoder.encode(Integer.toString(passwordTemp));

        if (userObj != null) {
            user.setPassword(password);
            userMapper.updatePassword(user);
            return passwordTemp;
        } else {
            return 0;
        }

    }

    @Override
    public List<User> selectUsersForNotify(String time) throws Exception {
        return userMapper.notifyEmail(time);
    }

    @Override
    public int updateLocation(User user) throws Exception {
        return userMapper.updateLocation(user);
    }

    @Override
    public int updateNotify(User user) throws Exception {
        return userMapper.updateNofity(user);
    }
}