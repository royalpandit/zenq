package com.abhishek.zeiqindia.Response;

import com.abhishek.zeiqindia.Bean.PartyBean;

import java.util.List;

public class GepPartyResponse {


    /**
     * info : [{"id":"1","company_name":"my company","company_address":"231 jaipur","email":"jaipu@gmail.com","contact_person":"ramlala","contact_number":"9099019090190190","gst":"bbcbjc878979","pan":" n cbjcbjcbh","create_date":"2019-12-23 04:36:15","owner_id":"2"}]
     * status : 1
     * msg : Successfully
     */

    private String status;
    private String msg;
    private List<PartyBean> info;

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

    public List<PartyBean> getInfo() {
        return info;
    }

    public void setInfo(List<PartyBean> info) {
        this.info = info;
    }

 }
