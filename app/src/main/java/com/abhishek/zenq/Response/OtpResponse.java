package com.abhishek.zenq.Response;

public class OtpResponse {


    /**
     * otp : 6545
     * status : 1
     * msg : Successfully
     */

    private int otp;
    private String status;
    private String msg;

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
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
