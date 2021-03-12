package com.nucpoop.covserver.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSummary {
    private Long id;
    private String userEmail;
    public UserSummary(Long id, String userEmail) {
        this.id = id;
        this.userEmail = userEmail;
    }
}
