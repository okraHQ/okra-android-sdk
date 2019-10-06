package com.okra.widget.utils;

import com.okra.widget.models.Enums;

import java.io.Serializable;
import java.util.ArrayList;

public class OkraOptions implements Serializable {

    public OkraOptions(boolean isWebview, String key, String token, ArrayList<String> products, Enums.Environment environment, String clientName){
      this.isWebview = isWebview;
      this.key = key;
      this.token = token;
      this.products = products;
      this.env = environment;
      this.clientName = clientName;
    }

    boolean isWebview;
    String key;
    String token;
    ArrayList<String> products = new ArrayList<>();
    Enums.Environment env;
    String clientName;

    public boolean isWebview() {
        return isWebview;
    }

    public void setWebview(boolean webview) {
        isWebview = webview;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ArrayList<String> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<String> products) {
        this.products = products;
    }

    public Enums.Environment getEnv() {
        return env;
    }

    public void setEnv(Enums.Environment env) {
        this.env = env;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}

