package io.github.zengzhihao.tngou.core.di;

import com.squareup.otto.Bus;

import android.content.Context;
import android.content.SharedPreferences;

import dagger.Module;
import dagger.Provides;
import io.github.zengzhihao.tngou.core.EventBus;
import io.github.zengzhihao.tngou.core.qualifier.ApplicationScope;
import io.github.zengzhihao.tngou.core.qualifier.ForApplication;

/**
 * Created by kela.king on 16/3/28.
 */
@Module
public class DataModule {

    @Provides
    @ApplicationScope
    SharedPreferences provideSharedPreferences(@ForApplication Context context) {
        return context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }

    @Provides
    @ApplicationScope
    Bus provideEventBus() {
        return EventBus.newInstance();
    }
}
