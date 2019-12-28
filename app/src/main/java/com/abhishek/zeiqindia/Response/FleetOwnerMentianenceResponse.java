package com.abhishek.zeiqindia.Response;

import com.abhishek.zeiqindia.Bean.MentianenceBean;

import java.util.List;

public class FleetOwnerMentianenceResponse {


    /**
     * info : [{"id":"1","owner_id":"1","name":"mukesh","phone":"9314497070","company":"mycompany","gst":"4644fbfb","address1":"a-35 near diving public school","address2":"80 feet road bus stand","address3":"mahesh nagar","create_date":"11-15-2019 13:16:13"},{"id":"2","owner_id":"1","name":"Rajesh","phone":"9314497070","company":"Alpha repair","gst":"12334555","address1":"a-35 near diving public school","address2":"80 feet road bus stand","address3":"mahesh nagar","create_date":"11-15-2019 14:38:35"},{"id":"3","owner_id":"1","name":"Rajesh","phone":"9314497070","company":"Alpha repair","gst":"12334555","address1":"a-35 near diving public school","address2":"80 feet road bus stand","address3":"mahesh nagar","create_date":"11-15-2019 14:39:06"}]
     * status : 1
     * msg : Successfully
     */

    private String status;
    private String msg;
    private List<MentianenceBean> info;

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

    public List<MentianenceBean> getInfo() {
        return info;
    }

    public void setInfo(List<MentianenceBean> info) {
        this.info = info;
    }

 }
