package com.abhishek.zeiqindia.Response;

public class KYCSubmitResponse {


    /**
     * info : 0
     * status : 1
     * msg : KYC updated successfully.
     */

    private String info;
    private String status;
    private String msg;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

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
}
