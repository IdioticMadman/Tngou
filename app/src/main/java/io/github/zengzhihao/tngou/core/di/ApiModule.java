package io.github.zengzhihao.tngou.core.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import io.github.zengzhihao.tngou.core.qualifier.ApplicationScope;
import io.github.zengzhihao.tngou.lib.api.ApiDefaultConfig;
import io.github.zengzhihao.tngou.lib.api.service.TopService;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kela.king on 16/3/28.
 */
@Module
public class ApiModule {

    @Provides
    @ApplicationScope
    Gson provideGson() {
        GsonBuilder builder = new GsonBuilder();
//        builder.registerTypeAdapter(T.class, new TDeserializer());
        return builder.create();
    }

    @Provides
    @ApplicationScope
    Converter.Factory provideConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @ApplicationScope
    Retrofit provideRetrofit(OkHttpClient okHttpClient, Converter.Factory converterFactory) {
        return new Retrofit.Builder()
                .baseUrl(ApiDefaultConfig.BASE_ENDPOINT)
                .client(okHttpClient)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    @ApplicationScope
    TopService provideTopService(Retrofit retrofit) {
        return retrofit.create(TopService.class);
    }
}
