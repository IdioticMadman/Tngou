/*
 * Copyright 2015 zengzhihao.github.io. All rights reserved.
 * Support: http://zengzhihao.github.io
 */

package io.github.zengzhihao.tngou.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.github.zengzhihao.tngou.BuildConfig;
import io.github.zengzhihao.tngou.data.model.exception.ApiException;
import io.github.zengzhihao.tngou.lib.api.ApiDefaultConfig;
import retrofit.Endpoint;
import retrofit.ErrorHandler;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Client;
import retrofit.client.OkClient;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;

/**
 * @author Kela.King
 */
@Module(complete = false, library = true)
public class ApiModule {

    @Provides
    @Singleton
    Endpoint provideEndpoint() {
        return ApiDefaultConfig.END_POINT;
    }

    @Provides
    @Singleton
    Client provideClient(OkHttpClient okHttpClient) {
        return new OkClient(okHttpClient);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @Singleton
    Converter provideConverter(Gson gson) {
        return new GsonConverter(gson);
    }

    @Provides
    @Singleton
    RestAdapter provideRestAdapter(Endpoint endpoint, Client client, Converter converter) {
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(endpoint).setClient
                (client).setConverter(converter).setErrorHandler(new ErrorHandler() {
            @Override
            public Throwable handleError(RetrofitError cause) {
                return ApiException.create(cause);
            }
        }).build();

        if (BuildConfig.DEBUG) {
            restAdapter.setLogLevel(RestAdapter.LogLevel.FULL);
        } else {
            restAdapter.setLogLevel(RestAdapter.LogLevel.NONE);
        }

        return restAdapter;
    }
}
