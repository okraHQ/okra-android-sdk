package com.okra.widget.models;

import java.io.Serializable;

import androidx.annotation.NonNull;

public class Guarantor implements Serializable {

    public Guarantor(boolean status, String message, int number){
       this.status = status;
       this.message = message;
       this.number = number;
    }

    private boolean status;
    private String message;
    private int number;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @NonNull
    @Override
    public String toString() {
        return  "{" +
                   "\"status\" :"  +  "\"" + this.status  +  "\","  +
                   "\"message\" :" +  "\"" + this.message +  "\","  +
                   "\"number\" :"  +  "\"" + this.number  +  "\""   +
                "}";
    }
}
