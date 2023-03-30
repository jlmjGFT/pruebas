package com.example.pokemonapp.utils.api;


import android.util.Log;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient.Builder;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;

class UnsafeOkHttpClient {
    private static final String TAG = UnsafeOkHttpClient.class.getSimpleName();

    UnsafeOkHttpClient() {
    }

    static Builder getUnsafeOkHttpClient() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain, String authType) {
                    Log.i(UnsafeOkHttpClient.TAG, authType);
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType) {
                    Log.i(UnsafeOkHttpClient.TAG, authType);
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }};
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init((KeyManager[]) null, trustAllCerts, new SecureRandom());
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            Builder builder = new Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier((hostname, session) -> {
                return true;
            });

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(Level.BODY);
            return builder.addInterceptor(interceptor);

        } catch (Exception var5) {
            throw new RuntimeException(var5);
        }
    }
}