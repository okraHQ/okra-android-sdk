package com.okra.widget.utils;

import com.okra.widget.models.Enums;

import java.io.Serializable;
import java.util.ArrayList;

public class
OkraOptions implements Serializable {

    public OkraOptions(){}

    public OkraOptions(boolean isWebview, String key, String token, ArrayList<Enums.Product> products, Enums.Environment environment, String clientName){
      this.isWebview = isWebview;
      this.key = key;
      this.token = token;
      this.products = products;
      this.env = environment;
      this.clientName = clientName;
    }

    public OkraOptions(boolean isWebview, String key, String token, ArrayList<Enums.Product> products, Enums.Environment environment, String clientName, String webhook){
        this.isWebview = isWebview;
        this.key = key;
        this.token = token;
        this.products = products;
        this.env = environment;
        this.clientName = clientName;
        this.webhook = webhook;
    }

    private boolean isWebview;
    private String key;
    private String token;
    private ArrayList<Enums.Product> products = new ArrayList<>();
    private Enums.Environment env;
    private String clientName;
    private String webhook;

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

    public ArrayList<Enums.Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Enums.Product> products) {
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

    public String getWebhook() {
        return webhook;
    }

    public void setWebhook(String webhook) {
        this.webhook = webhook;
    }
}

