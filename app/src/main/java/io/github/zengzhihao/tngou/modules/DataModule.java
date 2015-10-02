/*
 * Copyright 2015 zengzhihao.github.io. All rights reserved.
 * Support: http://zengzhihao.github.io
 */

package io.github.zengzhihao.tngou.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.otto.Bus;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.github.zengzhihao.tngou.BuildConfig;
import io.github.zengzhihao.tngou.core.EventBus;
import io.github.zengzhihao.tngou.core.qualifier.ForApplication;
import io.github.zengzhihao.tngou.core.rx.ScheduleTransformer;
import timber.log.Timber;

/**
 * @author Kela.King
 */
@Module(includes = {ApiModule.class, UtilsModule.class}, complete = false, library = true)
public class DataModule {

    public static OkHttpClient createOkHttpClient(Context context) {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(15000L, TimeUnit.MILLISECONDS);
        okHttpClient.setReadTimeout(20000L, TimeUnit.MILLISECONDS);

        if (BuildConfig.DEBUG) {
            okHttpClient
                    .setCache(new Cache(new File(context
                            .getExternalCacheDir(), "http-cache"),
                            20 * 1024 * 1024));
        } else {
            okHttpClient.setCache(new Cache(new File(context
                    .getCacheDir(), "http-cache"), 20 * 1024 * 1024));
        }
        return okHttpClient;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(@ForApplication Context context) {
        return createOkHttpClient(context);
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(@ForApplication Context context) {
        return context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    Picasso providePicasso(@ForApplication Context context, OkHttpClient okHttpClient) {
        Picasso picasso = new Picasso.Builder(context)
                .downloader(new OkHttpDownloader(okHttpClient))
                .listener(new Picasso.Listener() {
                    @Override
                    public void onImageLoadFailed(Picasso picasso, Uri uri,
                                                  Exception exception) {
                        Timber.e(exception, "### Failed to load image: %s", uri);
                    }
                }).build();

        if (BuildConfig.DEBUG)
            picasso.setIndicatorsEnabled(true);

        return picasso;
    }

    @Provides
    @Singleton
    ScheduleTransformer provideReactiveTransformer() {
        return new ScheduleTransformer();
    }

    @Provides
    @Singleton
    Bus provideEventBus() {
        return EventBus.newInstance();
    }
}
