/*
 * Copyright 2015 zengzhihao.github.io. All rights reserved.
 * Support: http://zengzhihao.github.io
 */

package io.github.zengzhihao.tngou.core.rx;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Kela.King
 */
public class ScheduleTransformer {

    public <T> Observable<T> bindObservable$(Observable<T> observable) {
        return observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
    }
}
