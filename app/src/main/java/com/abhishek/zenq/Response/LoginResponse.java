package com.abhishek.zenq.Response;

import com.abhishek.zenq.Bean.LoginBean;

public class LoginResponse {
    /**
     * info : {"id":"2","email":"test@gmail.com","password":"123456","user_type":"1","is_active":"1","is_kyc":"1","role":"Owner","name":"Test owner","mobile":"9314497070","is_mail":"1","business_name":"rode line","last_name":"owner","address":"tonk road, jaipur","locatlity":"tonk road","landline":"","country":"","state":"Rajasthan","city":"Jaipur","pincode":"302015","create_date":"4-12-2019","image":"uploads/user/_logo.jpeg","img_proof":"idproof/user/photo.jpg","pancard":"089923492792","mobile_otp":""}
     * status : 1
     * msg : Successfully
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
