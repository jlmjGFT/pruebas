package com.example.pokemonapp.utils.api;


import com.google.gson.Gson;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit.Builder;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestManager {
    private Builder retrofit;

    public RequestManager(String BASE_URL) {
        this.configureRequestManager(BASE_URL, 60L, 10L);
    }

    public RequestManager(String BASE_URL, int timeout) {
        this.configureRequestManager(BASE_URL, (long)timeout, 10L);
    }

    public RequestManager(String BASE_URL, long timeout, long readTimeout) {
        this.configureRequestManager(BASE_URL, timeout, readTimeout);
    }

    private void configureRequestManager(String BASE_URL, long connectTimeout, long readTimeout) {
        OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient().connectTimeout(connectTimeout != 0L ? connectTimeout : 60L, TimeUnit.SECONDS).readTimeout(readTimeout != 0L ? readTimeout : 10L, TimeUnit.SECONDS).build();
        this.retrofit = (new Builder()).addConverterFactory(GsonConverterFactory.create(new Gson())).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).baseUrl(BASE_URL).client(okHttpClient);
    }

    public RequestManager(String BASE_URL, int timeOut, int readTimeOut, int writeTimeOut) {
        OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient().connectTimeout((long)timeOut, TimeUnit.SECONDS).readTimeout((long)readTimeOut, TimeUnit.SECONDS).writeTimeout((long)writeTimeOut, TimeUnit.SECONDS).build();
        this.retrofit = (new Builder()).addConverterFactory(GsonConverterFactory.create(new Gson())).baseUrl(BASE_URL).client(okHttpClient);
    }

    public <T> T create(Class<T> service) {
        HttpLoggingInterceptor interceptorLogger = new HttpLoggingInterceptor();
        interceptorLogger.setLevel(Level.BODY);
        okhttp3.OkHttpClient.Builder httpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();
        httpClient.addInterceptor(interceptorLogger);
        this.retrofit.client(httpClient.build());
        return this.retrofit.build().create(service);
    }
}