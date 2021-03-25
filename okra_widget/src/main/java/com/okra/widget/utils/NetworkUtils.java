package com.okra.widget.utils;



import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class NetworkUtils {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private com.okra.widget.utils.OkraOptions okraOptions;

    public NetworkUtils(com.okra.widget.utils.OkraOptions okraOptions) {
        this.okraOptions = okraOptions;
    }

    public static Map<String, String> getAuthorizationHeader(com.okra.widget.utils.OkraOptions okraOptions){
        Map<String, String> headers = new HashMap<>();
       // headers.put("Authorization", okraOptions.getPrivateKey());
        return headers;
    }

    public Response get(String url){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
             //   .addHeader("Authorization",okraOptions.getPrivateKey())
                .build();
        try{
            Response response = client.newCall(request).execute();
            return response;
        }catch (Exception ex){
            return null;
        }
    }

    String post(String url, String json) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        }catch (Exception ex){
            return url;
        }
    }
}
