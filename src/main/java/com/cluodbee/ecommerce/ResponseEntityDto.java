package com.cluodbee.ecommerce;

public class ResponseEntityDto {

    private String message;

    public ResponseEntityDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
