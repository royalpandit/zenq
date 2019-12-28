package com.abhishek.zeiqindia.Response;

import com.abhishek.zeiqindia.Bean.DriverBean;

import java.util.List;

public class FleetOwnerDriverResponse {


    /**
     * info : [{"id":"3","email":"driver@gmail.com","password":"123456","user_type":"4","is_active":"1","is_kyc":"1","role":"Driver","name":"Radhy Singh","mobile":"1234567890","is_mail":"1","business_name":"Karni Kripa","last_name":"Rajendra","address":"Shanti nagar","locatlity":"sanganer","landline":"","country":"","state":"Rajasthan","city":"jaipur","pincode":"302010","create_date":"4-12-2019","image":"uploads/user/_logo.jpeg","img_proof":"idproof/user/photo.jpg","pancard":"8748927429","mobile_otp":"","owner_id":"2"}]
     * status : 1
     * msg : Successfully
     */

    private String status;
    private String msg;
    private List<DriverBean> info;

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

    public List<DriverBean> getInfo() {
        return info;
    }

    public void setInfo(List<DriverBean> info) {
        this.info = info;
    }


}
