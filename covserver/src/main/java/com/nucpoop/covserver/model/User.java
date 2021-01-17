package com.nucpoop.covserver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class User {
    private int id;
    private String userID;
    private String userPasswd;
    private String userName;
    private String userPhone;
    private String userEmail;
}
