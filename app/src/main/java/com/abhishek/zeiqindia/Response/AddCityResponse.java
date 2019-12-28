package com.abhishek.zeiqindia.Response;

public class AddCityResponse {


    /**
     * success : 1
     * message : City Added successfully.
     */

    private String success;
    private String message;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
