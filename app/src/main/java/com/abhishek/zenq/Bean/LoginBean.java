package com.abhishek.zenq.Bean;

public class LoginBean {

    /**
     * id : 2
     * email : test@gmail.com
     * password : 123456
     * user_type : 1
     * is_active : 1
     * is_kyc : 0
     * role : owner
     */

    private String id;
    private String email;
    private String password;
    private String user_type;
    private String is_active;
    private String is_kyc;
    private String role;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    public String getIs_kyc() {
        return is_kyc;
    }

    public void setIs_kyc(String is_kyc) {
        this.is_kyc = is_kyc;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }



}
