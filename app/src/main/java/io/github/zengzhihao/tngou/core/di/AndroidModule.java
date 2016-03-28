package io.github.zengzhihao.tngou.core.di;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;

import dagger.Module;
import dagger.Provides;
import io.github.zengzhihao.tngou.core.qualifier.ApplicationScope;
import io.github.zengzhihao.tngou.core.qualifier.ClientVersionCode;
import io.github.zengzhihao.tngou.core.qualifier.ClientVersionName;
import io.github.zengzhihao.tngou.core.qualifier.ForApplication;
import io.github.zengzhihao.tngou.util.Preconditions;
import timber.log.Timber;

/**
 * Created by kela.king on 16/3/28.
 */
@Module
public class AndroidModule {

    private final Context applicationContext;

    public AndroidModule(android.app.Application context) {
        this.applicationContext = Preconditions.checkNotNull(context, "context == null.");
    }

    @ApplicationScope
    @Provides
    @ForApplication
    Context provideApplicationContext() {
        return applicationContext;
    }

    @ApplicationScope
    @Provides
    @ClientVersionCode
    int provideVersionCode(@ForApplication Context context) {
        return getVersionCode(context);
    }

    @ApplicationScope
    @Provides
    @ClientVersionName
    String provideVersionName(@ForApplication Context context) {
        return getVersionName(context);
    }

    @ApplicationScope
    @Provides
    AssetManager provideAssetManager(@ForApplication Context context) {
        return context.getAssets();
    }

    static int getVersionCode(Context context) {
        PackageInfo pi = getPackageInfo(context);
        if (pi != null) {
            return pi.versionCode;
        }
        return 0;
    }

    static String getVersionName(Context context) {
        PackageInfo pi = getPackageInfo(context);
        if (pi != null) {
            return pi.versionName;
        } else {
            return "";
        }
    }

    static PackageInfo getPackageInfo(Context context) {
        PackageManager pm = context.getPackageManager();
        PackageInfo pi = null;
        try {
            pi = pm.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            Timber.e(e, "get package manager failed");
        }
        return pi;
    }
}