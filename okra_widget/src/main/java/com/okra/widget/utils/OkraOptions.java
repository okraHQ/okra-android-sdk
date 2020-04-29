package com.okra.widget.utils;

import com.okra.widget.models.Enums;
import com.okra.widget.models.Guarantor;

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
    private String source = "android";
    private String color;





    private String limit;
    private boolean corporate;
    private String connectMessage;
    private Guarantor guarantors;
    private String callback_url;
    private String redirect_url;
    private String logo;
    //filter: ${JSON.stringify(filter)},
    private String widget_success;
    private String currency;
    private String exp;
    private String success_title;
    private String success_message;

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public boolean isCorporate() {
        return corporate;
    }

    public void setCorporate(boolean corporate) {
        this.corporate = corporate;
    }

    public String getConnectMessage() {
        return connectMessage;
    }

    public void setConnectMessage(String connectMessage) {
        this.connectMessage = connectMessage;
    }

    public Guarantor getGuarantors() {
        return guarantors;
    }

    public void setGuarantors(Guarantor guarantors) {
        this.guarantors = guarantors;
    }

    public String getCallback_url() {
        return callback_url;
    }

    public void setCallback_url(String callback_url) {
        this.callback_url = callback_url;
    }

    public String getRedirect_url() {
        return redirect_url;
    }

    public void setRedirect_url(String redirect_url) {
        this.redirect_url = redirect_url;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getWidget_success() {
        return widget_success;
    }

    public void setWidget_success(String widget_success) {
        this.widget_success = widget_success;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getSuccess_title() {
        return success_title;
    }

    public void setSuccess_title(String success_title) {
        this.success_title = success_title;
    }

    public String getSuccess_message() {
        return success_message;
    }

    public void setSuccess_message(String success_message) {
        this.success_message = success_message;
    }
}

