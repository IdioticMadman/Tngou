package io.github.zengzhihao.tngou.core.di;

import dagger.Component;
import io.github.zengzhihao.tngou.core.qualifier.ApplicationScope;

/**
 * Created by kela.king on 16/3/28.
 */
@ApplicationScope
@Component(modules = {AndroidModule.class, DataModule.class, NetworkModule.class, ApiModule.class,
        UtilsModule.class})
public interface ApplicationComponent extends ViewInjector {

//    void inject(Application application);
}
