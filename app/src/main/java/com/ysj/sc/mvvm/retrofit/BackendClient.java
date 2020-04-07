package com.ysj.sc.mvvm.retrofit;

import com.ysj.sc.mvvm.db.entity.Consumption;
import com.ysj.sc.mvvm.db.entity.Statistics;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BackendClient {
    //http://192.168.0.8:8080/transaction/consumption/jinny5025
    @GET("transaction/consumption/{userId}")
    Call<List<Consumption>> getAllDetailConsumption(@Path("userId") String userId);

    @GET("transaction/statistics/{userId}")
    Call<List<Statistics>> getAllStatistics(@Path("userId") String userId);
}
