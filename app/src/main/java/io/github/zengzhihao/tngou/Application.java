/*
 * Copyright 2015 zengzhihao.github.io. All rights reserved.
 * Support: http://zengzhihao.github.io
 */

package io.github.zengzhihao.tngou;

import com.facebook.stetho.Stetho;
import com.frogermcs.androiddevmetrics.AndroidDevMetrics;

import android.content.Context;

import io.github.zengzhihao.tngou.core.di.AndroidModule;
import io.github.zengzhihao.tngou.core.di.ApiModule;
import io.github.zengzhihao.tngou.core.di.ApplicationComponent;
import io.github.zengzhihao.tngou.core.di.DaggerApplicationComponent;
import io.github.zengzhihao.tngou.core.di.DataModule;
import io.github.zengzhihao.tngou.core.di.HasComponent;
import io.github.zengzhihao.tngou.core.di.NetworkModule;
import io.github.zengzhihao.tngou.core.di.UtilsModule;
import timber.log.Timber;

/**
 * @author Kela.King
 */
public class Application extends android.app.Application
        implements HasComponent<ApplicationComponent> {

    private ApplicationComponent _component;

    @Override
    public void onCreate() {
        super.onCreate();

        _buildObjectGraph();
        _setupAnalytics();
    }

    private void _setupAnalytics() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            AndroidDevMetrics.initWith(this);
            Stetho.initialize(
                    Stetho.newInitializerBuilder(this)
                            .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                            .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                            .build());
        }
    }

    private void _buildObjectGraph() {
        _component = DaggerApplicationComponent.builder()
                .androidModule(new AndroidModule(this))
                .dataModule(new DataModule())
                .networkModule(new NetworkModule())
                .apiModule(new ApiModule())
                .utilsModule(new UtilsModule())
                .build();
    }

    @Override
    public ApplicationComponent getComponent() {
        return _component;
    }

    public static Application from(Context context) {
        return (Application) context.getApplicationContext();
    }
}
