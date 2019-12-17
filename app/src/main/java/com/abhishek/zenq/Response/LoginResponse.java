package com.abhishek.zenq.Response;

import com.abhishek.zenq.Bean.LoginBean;

public class LoginResponse {


    /**
     * info : {"id":"2","email":"test@gmail.com","password":"123456","user_type":"1","is_active":"1","is_kyc":"0","role":"owner"}
     * status : 1
     * msg : KYC Pending..Contact to admin
     */

    private LoginBean info;
    private String status;
    private String msg;

    public LoginBean getInfo() {
        return info;
    }

    public void setInfo(LoginBean info) {
        this.info = info;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
