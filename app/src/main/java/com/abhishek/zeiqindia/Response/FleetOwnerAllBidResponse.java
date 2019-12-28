package com.abhishek.zeiqindia.Response;

import com.abhishek.zeiqindia.Bean.AllBidBean;

import java.util.List;

public class FleetOwnerAllBidResponse {


    /**
     * info : [{"id":"2","from_address":"Kota","to_address":"Mount Abu","from_date":"2019-12-08","to_date":"2020-01-01","weight":"10000","price_per_ton":"31","truck_type":"Truck 2"},{"id":"4","from_address":"Raipur","to_address":"Indore","from_date":"2019-12-05","to_date":"2019-12-28","weight":"10000","price_per_ton":"23","truck_type":"Truck 2"},{"id":"5","from_address":"jaipur","to_address":"jodhpur","from_date":"2019-12-20","to_date":"2019-12-20","weight":"200","price_per_ton":"4000","truck_type":"Truck 1"}]
     * status : 1
     * msg : Successfully
     */

    private String status;
    private String msg;
    private List<AllBidBean> info;

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

    public List<AllBidBean> getInfo() {
        return info;
    }

    public void setInfo(List<AllBidBean> info) {
        this.info = info;
    }

 }
