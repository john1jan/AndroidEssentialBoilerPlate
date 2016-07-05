package com.johnfrancis.samplearch.api;

import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.johnfrancis.samplearch.BuildConfig;
import com.johnfrancis.samplearch.config.ApiConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import rx.Observable;

/**
 * Created by john.francis on 14/05/16.
 */
public class ApiManager {

    ServerApi serverApi;
    Context context;
    OkHttpClient okHttpClient;

    public ApiManager(Context context) {
        this.context = context;
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(
                BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);


        Interceptor requestInterceptor = new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                // Here you can add request header
                Request request = original.newBuilder()
                        .header("Content-Type", "application/json; charset=utf-8")
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        };

        okHttpClient = new OkHttpClient.Builder().addInterceptor(requestInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create();

        final Retrofit.Builder builder = new Retrofit.Builder().baseUrl(ApiConfig.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

        // Fail early: check Retrofit configuration at creation time
        if (BuildConfig.DEBUG) {
            builder.validateEagerly(true);
        }
        serverApi = builder.build().create(ServerApi.class);
    }

    public Observable getPostList() {
        return serverApi.getPosts();
    }

}
