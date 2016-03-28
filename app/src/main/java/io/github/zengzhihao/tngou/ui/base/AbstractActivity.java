/*
 * Copyright 2015 zengzhihao.github.io. All rights reserved.
 * Support: http://zengzhihao.github.io
 */

package io.github.zengzhihao.tngou.ui.base;

import com.trello.rxlifecycle.ActivityEvent;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import android.os.Bundle;

import io.github.zengzhihao.tngou.Application;
import io.github.zengzhihao.tngou.core.di.ApplicationComponent;
import io.github.zengzhihao.tngou.core.di.HasComponent;
import io.github.zengzhihao.tngou.core.di.Injectable;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Kela.King
 */
public abstract class AbstractActivity extends RxAppCompatActivity
        implements HasComponent<ApplicationComponent>, Injectable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectMembers();
    }

    @Override
    public ApplicationComponent getComponent() {
        return Application.from(this).getComponent();
    }

    protected <T> Observable<T> bind$(Observable<T> observable) {
        return observable.compose(this.<T>bindToLifecycle()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    protected <T> Observable<T> bind$(Observable<T> observable, ActivityEvent
            activityEvent) {
        return observable.compose(this.<T>bindUntilEvent(activityEvent)).subscribeOn(Schedulers
                .io()).observeOn(AndroidSchedulers.mainThread());
    }
}
