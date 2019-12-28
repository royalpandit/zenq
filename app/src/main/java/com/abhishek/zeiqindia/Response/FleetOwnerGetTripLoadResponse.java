package com.abhishek.zeiqindia.Response;

import com.abhishek.zeiqindia.Bean.GetLoadBean;

import java.util.List;

public class FleetOwnerGetTripLoadResponse {


    /**
     * info : [{"id":"1","trip_id":"2","expenses_type":"Disel","expenses_amount":"5000","comment":"454","d_qty":"rete","d_rate":"","d_amount":"","d_km":"","t_qty":"","t_make":"","t_model":"","t_serial_no":"","t_amount":"","create_date":"2019-12-23 02:47:08"},{"id":"2","trip_id":"2","expenses_type":"Toll","expenses_amount":"250","comment":"","d_qty":"","d_rate":"","d_amount":"","d_km":"","t_qty":"","t_make":"","t_model":"","t_serial_no":"","t_amount":"","create_date":"2019-12-26 01:34:53"},{"id":"3","trip_id":"2","expenses_type":"toll","expenses_amount":"150","comment":"","d_qty":"150","d_rate":"","d_amount":"","d_km":"","t_qty":"","t_make":"","t_model":"","t_serial_no":"","t_amount":"","create_date":"2019-12-26 22:34:32"},{"id":"4","trip_id":"2","expenses_type":"Disel","expenses_amount":"3533","comment":"3535","d_qty":"35353","d_rate":"35353","d_amount":"3353","d_km":"3333","t_qty":"3333","t_make":"5353","t_model":"353535","t_serial_no":"353533","t_amount":"5353","create_date":"35353"},{"id":"5","trip_id":"2","expenses_type":"toll","expenses_amount":"150","comment":"","d_qty":"","d_rate":"","d_amount":"","d_km":"","t_qty":"","t_make":"","t_model":"","t_serial_no":"","t_amount":"","create_date":"2019-12-26 22:39:30"},{"id":"6","trip_id":"2","expenses_type":"toll","expenses_amount":"150","comment":"0","d_qty":"","d_rate":"","d_amount":"","d_km":"","t_qty":"","t_make":"","t_model":"","t_serial_no":"","t_amount":"","create_date":"2019-12-26 22:42:08"},{"id":"7","trip_id":"2","expenses_type":"toll","expenses_amount":"150","comment":"0","d_qty":"","d_rate":"","d_amount":"","d_km":"","t_qty":"","t_make":"","t_model":"","t_serial_no":"","t_amount":"","create_date":"2019-12-26 22:51:31"},{"id":"8","trip_id":"2","expenses_type":"toll","expenses_amount":"150","comment":"","d_qty":"","d_rate":"","d_amount":"","d_km":"","t_qty":"","t_make":"","t_model":"","t_serial_no":"","t_amount":"","create_date":"2019-12-26 22:53:33"},{"id":"9","trip_id":"2","expenses_type":"toll","expenses_amount":"150","comment":"","d_qty":"2","d_rate":"200","d_amount":"400","d_km":"400","t_qty":"","t_make":"","t_model":"","t_serial_no":"","t_amount":"","create_date":"2019-12-26 22:53:53"}]
     * status : 1
     * msg : Successfully
     */

    private String status;
    private String advance;
    private  int expenses;
    private  int remain;

    private String msg;
    private List<GetLoadBean> info;

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

    public String getAdvance() {
        return advance;
    }

    public void setAdvance(String advance) {
        this.advance = advance;
    }

    public int getExpenses() {
        return expenses;
    }

    public int getRemain() {
        return remain;
    }

    public void setRemain(int remain) {
        this.remain = remain;
    }

    public void setExpenses(int expenses) {
        this.expenses = expenses;
    }

    public List<GetLoadBean> getInfo() {
        return info;
    }

    public void setInfo(List<GetLoadBean> info) {
        this.info = info;
    }




 }
