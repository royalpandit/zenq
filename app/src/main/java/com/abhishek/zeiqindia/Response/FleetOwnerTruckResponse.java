package com.abhishek.zeiqindia.Response;

import com.abhishek.zeiqindia.Bean.TruckBean;

import java.util.List;

public class FleetOwnerTruckResponse {


    /**
     * info : [{"id":"1","reg_no":"rj14ym1008","owner_id":"1","reg_date":"12-12-2009","reg_vaild":"12-12-2032","chasis_no":"bnbbn7868668","Engin_no":"zx8787979797nnmbmnbm","make":"TATA","model":"Havey","load_capacity":"40","road_tax":"paid","puv_vaild":"12-12-2020","insurance_vaild":"12-12-2021","permit_valid":"12-12-2021","fitness_valid":"12-12-2021","is_kyc":"0","rc_image_front":"uploads/truck/rc_front.png","rc_image_back":"uploads/truck/rc_back.png","create_date":"2019-12-20 18:08:29"}]
     * status : 1
     * msg : Successfully
     */

    private String status;
    private String msg;
    private List<TruckBean> info;

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

    public List<TruckBean> getInfo() {
        return info;
    }

    public void setInfo(List<TruckBean> info) {
        this.info = info;
    }


}
