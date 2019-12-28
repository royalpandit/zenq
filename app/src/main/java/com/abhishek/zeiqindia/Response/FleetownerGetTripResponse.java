package com.abhishek.zeiqindia.Response;

import com.abhishek.zeiqindia.Bean.GetTripBean;

import java.util.List;

public class FleetownerGetTripResponse {


    /**
     * info : [{"id":"1","owner_id":"2","trip_id":"1","from_address":"Rajasthan ,jaipur","to_address":"rajasthan ,jodhpur","from_date":"2019-12-23","to_date":"2019-12-27","weight":"100","price_per_ton":"2000","truck_type":"","material_type":"Box","advance":"10000","shortage":"","deduction":"","munsiyana":"","commision":"","other":"","is_start":"0","is_complete":"0","from_state":"rajasthan","from_city":"jaipur","to_state":"rajasthan","to_city":"jaipur","party":"m/s transport","trip_type":"","driver_id":"","truck_id":"","create_date":""},{"id":"2","owner_id":"2","trip_id":"1","from_address":"","to_address":"","from_date":"0000-00-00","to_date":"0000-00-00","weight":"800","price_per_ton":"600","truck_type":"havey load","material_type":"box","advance":"2000","shortage":"10","deduction":"1000","munsiyana":"100","commision":"10","other":"no","is_start":"0","is_complete":"0","from_state":"Rajasthan","from_city":"jaipur","to_state":"Rajasthan","to_city":"Jodhpir","party":"m/s party","trip_type":"single","driver_id":"0","truck_id":"0","create_date":"2019-12-23 00:29:24"}]
     * status : 1
     * msg : Successfully
     */

    private String status;
    private String msg;
    private List<GetTripBean> info;

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

    public List<GetTripBean> getInfo() {
        return info;
    }

    public void setInfo(List<GetTripBean> info) {
        this.info = info;
    }

 }
