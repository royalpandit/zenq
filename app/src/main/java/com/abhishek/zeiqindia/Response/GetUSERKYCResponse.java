package com.abhishek.zeiqindia.Response;

import com.abhishek.zeiqindia.Bean.GetUserKYcBean;

import java.util.List;

public class GetUSERKYCResponse {


    /**
     * info : [{"id":"2","email":"test@gmail.com","password":"123456","user_type":"1","is_active":"1","is_kyc":"1","role":"Owner","name":"Test owner","mobile":"9314497070","is_mail":"1"},{"id":"3","email":"driver@gmail.com","password":"123456","user_type":"2","is_active":"1","is_kyc":"0","role":"Driver","name":"Radhy Singh","mobile":"1234567890","is_mail":"1"}]
     * status : 1
     * msg : Successfully
     */

    private String status;
    private String msg;
    private List<GetUserKYcBean> info;

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

    public List<GetUserKYcBean> getInfo() {
        return info;
    }

    public void setInfo(List<GetUserKYcBean> info) {
        this.info = info;
    }


}
