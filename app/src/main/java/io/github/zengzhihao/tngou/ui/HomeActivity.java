/*
 * Copyright 2015 zengzhihao.github.io. All rights reserved.
 * Support: http://zengzhihao.github.io
 */

package io.github.zengzhihao.tngou.ui;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import android.os.Bundle;

import javax.inject.Inject;

import io.github.zengzhihao.tngou.ui.base.AbstractActivity;
import io.github.zengzhihao.tngou.ui.top.TopActivity;

/**
 * @author Kela.King
 */
public class HomeActivity extends AbstractActivity {

    @Inject
    Bus _bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _bus.register(this);
        _bus.post(new OnStartTopActivityEvent());
    }

    @Override
    public void injectMembers() {
        getComponent().inject(this);
    }

    @Subscribe
    public void onStartTopActivity(OnStartTopActivityEvent event) {
        TopActivity.start(this);
        finish();
    }

    @Override
    protected void onDestroy() {
        _bus.unregister(this);
        super.onDestroy();
    }

    static class OnStartTopActivityEvent {

    }
}
