package io.github.zengzhihao.tngou.core.di;

import io.github.zengzhihao.tngou.ui.HomeActivity;
import io.github.zengzhihao.tngou.ui.top.TopActivity;

/**
 * Created by kela.king on 16/3/28.
 */
public interface ViewInjector {

    void inject(HomeActivity homeActivity);

    void inject(TopActivity topActivity);
}
