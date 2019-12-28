package com.abhishek.zeiqindia.Response;

import com.abhishek.zeiqindia.Bean.StateBean;

import java.util.List;

public class GetStateResponse {


    /**
     * info : [{"id":"1","state_name":"Rajasthan","state_code":"01"},{"id":"2","state_name":"Mp","state_code":"02"},{"id":"3","state_name":"up","state_code":"12"},{"id":"4","state_name":"up","state_code":"12"}]
     * status : 1
     * msg : Successfully
     */

    private String status;
    private String msg;
    private List<StateBean> info;

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

    public List<StateBean> getInfo() {
        return info;
    }

    public void setInfo(List<StateBean> info) {
        this.info = info;
    }

}
