package com.nucpoop.covserver.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSummary {
    private Long id;
    private String userEmail;
    private String notifyYn;
    private String location;
    private String notifyTime;
    
    public UserSummary(Long id, String userEmail, String notifyYn, String location, String notifyTime) {
        this.id = id;
        this.userEmail = userEmail;
        this.notifyYn = notifyYn;
        this.location = location;
        this.notifyTime = notifyTime;
    }

}
