package com.nucpoop.covserver.model;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class User {
    private Long id;
    private String email;
    private String password;
    private String notifyYn;
    private String location;
    private String notifyTime;
    private Set<Role> roles = new HashSet<>();
    private Instant createdAt;
    private Instant updatedAt;

    public User(Long id, String email, String password, String notifyYn, String location, String notifyTime,
            Set<Role> roles, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.notifyYn = notifyYn;
        this.location = location;
        this.notifyTime = notifyTime;
        this.roles = roles;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
