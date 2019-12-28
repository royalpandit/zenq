package com.abhishek.zeiqindia.Response;

import com.abhishek.zeiqindia.Bean.MyTripsBean;

import java.util.List;

public class FleetOwnerMyTripsResponse {


    /**
     * info : [{"id":"1","owner_id":"1","from_address":"Rajasthan ,jaipur","to_address":"rajasthan ,jodhpur","from_date":"2019-12-23","to_date":"2019-12-27","weight":"100","price_per_ton":"2000","truck_type":"","material_type":"Box","advance":"10000","shortage":"","deduction":"","munsiyana":"","commision":"","other":"","is_start":"0","is_complete":"0","from_state":"rajasthan","from_city":"jaipur","to_state":"rajasthan","to_city":"jaipur","party":"m/s transport","trip_type":"","driver_assign":"","truck_assign":"","create_date":""}]
     * status : 1
     * msg : Successfully
     */

    private String status;
    private String msg;
    private List<MyTripsBean> info;

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

    public List<MyTripsBean> getInfo() {
        return info;
    }

    public void setInfo(List<MyTripsBean> info) {
        this.info = info;
    }

}
