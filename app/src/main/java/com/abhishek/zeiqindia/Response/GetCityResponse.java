package com.abhishek.zeiqindia.Response;

import com.abhishek.zeiqindia.Bean.CityBean;

import java.util.List;

public class GetCityResponse {


    /**
     * info : [{"id":"1","state_name":"Rajasthan","state_id":"1","district_name":"","district_id":"","city_name":"jaipur","state_code":""},{"id":"2","state_name":"Rajasthan","state_id":"1","district_name":"","district_id":"","city_name":"Ajmer","state_code":""},{"id":"6","state_name":"up","state_id":"1","district_name":"","district_id":"","city_name":"Etawa","state_code":"12"}]
     * status : 1
     * msg : Successfully
     */

    private String status;
    private String msg;
    private List<CityBean> info;

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

    public List<CityBean> getInfo() {
        return info;
    }

    public void setInfo(List<CityBean> info) {
        this.info = info;
    }

 }
