package com.okra.widget.handlers;

import java.io.Serializable;

public class OkraHandler implements Serializable {
    public static String data = "";
    public static Boolean isSuccessful = false;
    public static Boolean hasError = false;
    public static Boolean isDone = false;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Boolean getSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(Boolean successful) {
        isSuccessful = successful;
    }

    public Boolean getHasError() {
        return hasError;
    }

    public void setHasError(Boolean hasError) {
        this.hasError = hasError;
    }

    public Boolean getIsSuccessful() {
        return isSuccessful;
    }

    public void setIsSuccessful(Boolean isSuccessful) {
        OkraHandler.isSuccessful = isSuccessful;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean isDone) {
        OkraHandler.isDone = isDone;
    }
}
