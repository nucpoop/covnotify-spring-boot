package com.nucpoop.covserver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class User {
    private int id;
    private String userID;
    private String userPasswd;
    private String userName;
    private String userPhone;
    private String userEmail;
}
