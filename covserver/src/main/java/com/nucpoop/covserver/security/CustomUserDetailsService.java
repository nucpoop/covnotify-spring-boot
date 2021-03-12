package com.nucpoop.covserver.security;

import com.nucpoop.covserver.mapper.UserMapper;
import com.nucpoop.covserver.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Let people login with either username or email
        User user = new User();
        try {
            user = userMapper.findByEmail(email);
        } catch (Exception e) {
            System.out.println(e);
        }

        // .orElseThrow(
        // () -> new UsernameNotFoundException("User not found with username or email :
        // " + email));

        return UserPrincipal.create(user);
    }

    // This method is used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = new User();
        try {
             user = userMapper.findById(id);
        } catch (Exception e) {
            System.out.println("loadUserById");
            System.out.println(e);
        }
        

        // .orElseThrow(() -> new UsernameNotFoundException("User not found with id : "
        // + id));

        return UserPrincipal.create(user);
    }
}
