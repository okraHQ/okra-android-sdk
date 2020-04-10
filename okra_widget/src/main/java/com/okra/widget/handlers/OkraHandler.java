package com.okra.widget.handlers;

import java.io.Serializable;

public class OkraHandler implements Serializable {
    public static String data;
    public static Boolean isSuccessful;
    public static Boolean hasError;
    public static Boolean isDone;

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

    public static Boolean getIsSuccessful() {
        return isSuccessful;
    }

    public static void setIsSuccessful(Boolean isSuccessful) {
        OkraHandler.isSuccessful = isSuccessful;
    }

    public static Boolean getIsDone() {
        return isDone;
    }

    public static void setIsDone(Boolean isDone) {
        OkraHandler.isDone = isDone;
    }
}
