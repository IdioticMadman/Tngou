/*
 * Copyright 2015 zengzhihao.github.io. All rights reserved.
 * Support: http://zengzhihao.github.io
 */

package io.github.zengzhihao.tngou.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import io.github.zengzhihao.tngou.Application;
import io.github.zengzhihao.tngou.core.rx.ScheduleTransformer;

/**
 * @author Kela.King
 */
public class AbstractActivity extends RxAppCompatActivity {
    @Inject
    ScheduleTransformer _scheduleTransformer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Application.getApplicationContext(this).inject(this);
    }

    protected ScheduleTransformer getScheduleTransformer() {
        return _scheduleTransformer;
    }
}
