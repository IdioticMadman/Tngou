/*
 * Copyright 2015 zengzhihao.github.io. All rights reserved.
 * Support: http://zengzhihao.github.io
 */

package io.github.zengzhihao.tngou.modules;

import dagger.Module;
import io.github.zengzhihao.tngou.ui.HomeActivity;
import io.github.zengzhihao.tngou.ui.base.AbstractActivity;

/**
 * @author Kela.King
 */
@Module(injects = {AbstractActivity.class, HomeActivity.class}, complete = false, library = true)
public class UiModule {

}