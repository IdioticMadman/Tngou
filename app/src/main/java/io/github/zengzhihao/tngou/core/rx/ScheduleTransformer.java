/*
 * Copyright 2015 zengzhihao.github.io. All rights reserved.
 * Support: http://zengzhihao.github.io
 */

package io.github.zengzhihao.tngou.core.rx;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * @author Kela.King
 */
public class ScheduleTransformer {

    public <T> Observable<T> bindOnMainThread$(Observable<T> observable) {
        return observable.observeOn(AndroidSchedulers.mainThread());
    }
}
