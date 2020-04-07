package com.ysj.sc.mvvm.retrofit;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class ApiServiceFactory {
    private static final String TAG = ApiServiceFactory.class.getSimpleName();

    private static final Interceptor NULL_INTERCEPTOR = null;

    private static final String BASE_URL = "";

    private ApiServiceFactory() {
    }

    public static <T> T getApiService(String url, Class<T> type) {
        return (T) createRestAdapter(url, NULL_INTERCEPTOR).create(type);
    }

    public static Retrofit createRestAdapter(String url, Interceptor interceptor) {

        return new Retrofit.Builder().baseUrl(url).client(getHttpClient(interceptor)).addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static Retrofit createRestAdapter() {

        Interceptor reqInterceptor = null;
//        reqInterceptor = new AuthenticationRequestInterceptor(system.getAccessToken());

        return createRestAdapter(makeBaseUrl(BASE_URL), reqInterceptor);
    }

    private static OkHttpClient getHttpClient(Interceptor interceptor) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(chain -> {
            Request request = chain.request();
            Request.Builder builder = request.newBuilder();
            builder.addHeader("Content-Type", "application/json");
//            builder.addHeader("Authorization", "Basic dXNlcjEyOnBhc3N3b3Jk");

            request = builder.build();
            String body;
            try {
                Response response = chain.proceed(request);
                body = response.body() != null ? response.body().string() : null;
                Log.d("### ", "### Body : " + body);
                return response.newBuilder().body(ResponseBody.create(response.body().contentType(), body)).build();
            } catch (IllegalStateException e) {
                Log.d(TAG, e.toString());
            } catch (Exception e) {
                Log.d(TAG, e.toString());
            }
            return chain.proceed(request);
        });


        if (interceptor != null) {
            httpClient.interceptors().add(interceptor);
        }
        httpClient.interceptors();
        httpClient.connectTimeout(2, TimeUnit.MINUTES).readTimeout(2, TimeUnit.MINUTES).writeTimeout(2, TimeUnit.MINUTES);
        return httpClient.build();
    }

    private static String makeBaseUrl(String baseUrl) {
        return baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";
    }
}