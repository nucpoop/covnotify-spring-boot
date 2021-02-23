package com.nucpoop.covserver.model;

// import lombok.Builder;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;
// import lombok.ToString;

// @NoArgsConstructor
// @Setter
// @Getter
// @Builder
// @ToString
public class User {
    private int id;
    private String user_id;
    private String user_passwd;
    private String user_name;
    private String user_phone;
    private String user_email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserID() {
        return user_id;
    }

    public void setUserID(String userID) {
        this.user_id = userID;
    }

    public String getUserPasswd() {
        return user_passwd;
    }

    public void setUserPasswd(String userPasswd) {
        this.user_passwd = userPasswd;
    }

    public String getUserName() {
        return user_name;
    }

    public void setUserName(String userName) {
        this.user_name = userName;
    }

    public String getUserPhone() {
        return user_phone;
    }

    public void setUserPhone(String userPhone) {
        this.user_phone = userPhone;
    }

    public String getUserEmail() {
        return user_email;
    }

    public void setUserEmail(String userEmail) {
        this.user_email = userEmail;
    }
}
