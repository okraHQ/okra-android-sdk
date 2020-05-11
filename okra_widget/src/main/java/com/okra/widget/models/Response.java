package com.okra.widget.models;

public class Response {

    public Response(String message){
        this.message = message;
    }

    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
