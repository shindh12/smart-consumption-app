package com.ysj.sc.mvvm.retrofit;


import com.ysj.sc.mvvm.db.entity.Consumption;
import com.ysj.sc.mvvm.db.entity.Statistics;

import java.util.List;
import retrofit2.Call;

public class BackendService {
//    public static final String BASE_URL = "http://10.0.2.2:9090/";
    public static final String BASE_URL = "http://192.168.0.8:8080/";

    private static BackendService service;
    private BackendClient client;

    public BackendService() {
        client = ApiServiceFactory.getApiService(BASE_URL, BackendClient.class);
    }

    public static BackendService instance() {
        if (service == null) {
            service = new BackendService();
        }
        return service;
    }

    public Call<List<Consumption>> getAllDetailConsumption(String userId) {
        return client.getAllDetailConsumption(userId);
    }

    public Call<List<Statistics>> getAllStatistics(String userId) {
        return client.getAllStatistics(userId);
    }

}
