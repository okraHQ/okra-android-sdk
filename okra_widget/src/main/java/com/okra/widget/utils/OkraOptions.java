package com.okra.widget.utils;

import com.okra.widget.models.Enums;
import com.okra.widget.models.Filter;
import com.okra.widget.models.Guarantor;

import java.io.Serializable;
import java.util.ArrayList;

public class
OkraOptions implements Serializable {

    public OkraOptions(){}

    public OkraOptions(String key, String token, ArrayList<Enums.Product> products, String environment, String clientName){
      this.key = key;
      this.token = token;
      this.products = products;
      this.env = environment;
      this.clientName = clientName;
    }

    public OkraOptions(String key, String token, ArrayList<Enums.Product> products, String environment, String clientName, String webhook){
        this.key = key;
        this.token = token;
        this.products = products;
        this.env = environment;
        this.clientName = clientName;
        this.webhook = webhook;
    }

    private boolean isWebview = true;
    private String key;
    private String token;
    private ArrayList<Enums.Product> products = new ArrayList<>();
    private String env;
    private String clientName;
    private String webhook;
    private String source = "android";
    private String color;
    private String limit;
    private boolean isCorporate;
    private String connectMessage;
    private Guarantor guarantors;
    private String callback_url;
    private String redirect_url;
    private String logo;
    private Filter filter;
    private String widget_success;
    private String widget_failed;
    private String currency;
    private String exp;
    private String success_title;
    private String success_message;
    private String imei;
    private DeviceInfo deviceInfo = new DeviceInfo();

    public boolean isWebview() {
        return isWebview;
    }

    public OkraOptions setWebview(boolean webview) {
        isWebview = webview;
        return this;
    }

    public String getKey() {
        return key;
    }

    public OkraOptions setKey(String key) {
        this.key = key;
        return this;
    }

    public String getToken() {
        return token;
    }

    public OkraOptions setToken(String token) {
        this.token = token;
        return this;
    }

    public ArrayList<Enums.Product> getProducts() {
        return products;
    }

    public OkraOptions setProducts(ArrayList<Enums.Product> products) {
        this.products = products;
        return  this;
    }

    public String getEnv() {
        return env;
    }

    public OkraOptions setEnv(String env) {
        this.env = env;
        return this;
    }

    public String getClientName() {
        return clientName;
    }

    public OkraOptions setClientName(String clientName) {
        this.clientName = clientName;
        return this;
    }

    public String getWebhook() {
        return webhook;
    }

    public OkraOptions setWebhook(String webhook) {
        this.webhook = webhook;
        return this;
    }

    public String getColor() {
        return color;
    }

    public OkraOptions setColor(String color) {
        this.color = color;
        return  this;
    }

    public String getLimit() {
        return limit;
    }

    public OkraOptions setLimit(String limit) {
        this.limit = limit;
        return this;
    }

    public String getSource() {
        return source;
    }

    public OkraOptions setSource(String source) {
        this.source = source;
        return this;
    }

    public boolean isCorporate() {
        return isCorporate;
    }

    public OkraOptions setCorporate(boolean corporate) {
        isCorporate = corporate;
        return this;
    }

    public String getConnectMessage() {
        return connectMessage;
    }

    public OkraOptions setConnectMessage(String connectMessage) {
        this.connectMessage = connectMessage;
        return this;
    }

    public Guarantor getGuarantors() {
        return guarantors;
    }

    public OkraOptions setGuarantors(Guarantor guarantors) {
        this.guarantors = guarantors;
        return this;
    }

    public String getCallback_url() {
        return callback_url;
    }

    public OkraOptions setCallback_url(String callback_url) {
        this.callback_url = callback_url;
        return this;
    }

    public String getRedirect_url() {
        return redirect_url;
    }

    public OkraOptions setRedirect_url(String redirect_url) {
        this.redirect_url = redirect_url;
        return this;
    }

    public String getLogo() {
        return logo;
    }

    public OkraOptions setLogo(String logo) {
        this.logo = logo;
        return this;
    }

    public String getWidget_success() {
        return widget_success;
    }

    public OkraOptions setWidget_success(String widget_success) {
        this.widget_success = widget_success;
        return this;
    }

    public String getWidget_failed() {
        return widget_failed;
    }

    public OkraOptions setWidget_failed(String widget_failed) {
        this.widget_failed = widget_failed;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public OkraOptions setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public String getExp() {
        return exp;
    }

    public OkraOptions setExp(String exp) {
        this.exp = exp;
        return this;
    }

    public String getSuccess_title() {
        return success_title;
    }

    public OkraOptions setSuccess_title(String success_title) {
        this.success_title = success_title;
        return this;
    }

    public String getSuccess_message() {
        return success_message;
    }

    public OkraOptions setSuccess_message(String success_message) {
        this.success_message = success_message;
        return this;
    }

    public String getImei() {
        return imei;
    }

    public OkraOptions setImei(String imei) {
        this.imei = imei;
        return this;
    }

    public Filter getFilter() {
        return filter;
    }

    public OkraOptions setFilter(Filter filter) {
        this.filter = filter;
        return this;
    }

    public DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }
}

