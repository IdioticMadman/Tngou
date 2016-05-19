package io.github.zengzhihao.tngou.core.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import io.github.zengzhihao.tngou.core.qualifier.ApplicationScope;
import io.github.zengzhihao.tngou.core.qualifier.ForApplication;
import io.github.zengzhihao.tngou.util.ToastHelper;

/**
 * Created by kela.king on 16/3/28.
 */
@Module
public class UtilsModule {

    @Provides
    @ApplicationScope
    ToastHelper provideToastHelper(@ForApplication Context context) {
        return new ToastHelper(context);
    }
}
