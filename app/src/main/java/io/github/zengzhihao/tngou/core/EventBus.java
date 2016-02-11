/*
 * Copyright 2015 zengzhihao.github.io. All rights reserved.
 * Support: http://zengzhihao.github.io
 */

package io.github.zengzhihao.tngou.core;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;

/**
 * @author Kela.King
 */
public class EventBus extends Bus {

    private static Bus __BUS;
    private final Handler _handler = new Handler(Looper.getMainLooper());

    public static Bus newInstance() {
        if (__BUS == null)
            __BUS = new EventBus();

        return __BUS;
    }

    @Override
    public void post(final Object event) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.post(event);
        } else {
            _handler.post(new Runnable() {
                @Override
                public void run() {
                    EventBus.super.post(event);
                }
            });
        }
    }
}
