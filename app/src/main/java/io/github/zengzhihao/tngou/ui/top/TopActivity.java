/*
 * Copyright 2015 zengzhihao.github.io. All rights reserved.
 * Support: http://zengzhihao.github.io
 */

package io.github.zengzhihao.tngou.ui.top;

import com.squareup.picasso.Picasso;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.zengzhihao.tngou.R;
import io.github.zengzhihao.tngou.core.qualifier.ClientVersionCode;
import io.github.zengzhihao.tngou.core.qualifier.ClientVersionName;
import io.github.zengzhihao.tngou.data.model.exception.ApiException;
import io.github.zengzhihao.tngou.lib.api.model.Top;
import io.github.zengzhihao.tngou.lib.api.service.TopService;
import io.github.zengzhihao.tngou.ui.base.AbstractActivity;
import io.github.zengzhihao.tngou.util.ToastHelper;
import retrofit2.Retrofit;
import rx.Observer;
import timber.log.Timber;

/**
 * @author Kela.King
 */
public class TopActivity extends AbstractActivity {

    @Inject
    TopService  _topService;
    @Inject
    Picasso     _picasso;
    @Inject
    @ClientVersionCode
    int         _versionCode;
    @Inject
    @ClientVersionName
    String      _versionName;
    @Inject
    ToastHelper _toastHelper;
    @Inject
    Retrofit    _retrofit;

    @Bind(R.id.common_list)
    ListView _listView;

    private TopAdapter _topAdapter;

    public static void start(Context context) {
        context.startActivity(new Intent(context, TopActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);
        ButterKnife.bind(this);

        _topAdapter = new TopAdapter(this, _picasso);
        _listView.setAdapter(_topAdapter);

        bind$(_topService.list()).subscribe(new Observer<Top.Result>() {
            @Override
            public void onCompleted() {
                Timber.i("### onCompleted.");
                _toastHelper.show("Just fucking, never stop! App version is " + _versionName + "-"
                        + _versionCode);
            }

            @Override
            public void onError(Throwable e) {
                ApiException exception = ApiException.create(e, _retrofit);
                // Do what you want with exception.
            }

            @Override
            public void onNext(Top.Result result) {
                _topAdapter.setResult(result.getTngou());
            }
        });
    }

    @Override
    public void injectMembers() {
        getComponent().inject(this);
    }
}