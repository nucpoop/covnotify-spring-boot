package com.nucpoop.covserver.security;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nucpoop.covserver.model.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {
    private Long id;

    private String email;

    @JsonIgnore
    private String password;

    private String notifyYn;
    private String location;
    private String notifyTime;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(Long id, String email, String password, String notifyYn, String location, String notifyTime,
            Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.notifyYn = notifyYn;
        this.location = location;
        this.notifyTime = notifyTime;
        this.authorities = authorities;
    }

    public static UserPrincipal create(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());

        return new UserPrincipal(user.getId(), user.getEmail(), user.getPassword(),user.getNotifyYn(),user.getLocation(),user.getNotifyTime(), authorities);
    }

    public Long getId() {
        return id;
    }

    public String getNotifyYn(){
        return notifyYn;
    }

    public String getLocation(){
        return location;
    }

    public String getNotifyTime(){
        return notifyTime;
    }
    
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

}
