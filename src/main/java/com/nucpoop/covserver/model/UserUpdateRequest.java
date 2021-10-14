package com.nucpoop.covserver.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequest {
    String password;
    String location;
}
