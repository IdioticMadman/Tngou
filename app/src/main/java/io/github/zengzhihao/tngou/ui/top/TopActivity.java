/*
 * Copyright 2015 zengzhihao.github.io. All rights reserved.
 * Support: http://zengzhihao.github.io
 */

package io.github.zengzhihao.tngou.ui.top;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.squareup.picasso.Picasso;
import com.trello.rxlifecycle.ActivityEvent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.zengzhihao.tngou.R;
import io.github.zengzhihao.tngou.lib.api.model.Top;
import io.github.zengzhihao.tngou.lib.api.service.TopService;
import io.github.zengzhihao.tngou.ui.base.AbstractActivity;
import rx.Observer;
import rx.Subscription;
import timber.log.Timber;

/**
 * @author Kela.King
 */
public class TopActivity extends AbstractActivity {
    @Inject
    TopService _topService;
    @Inject
    Picasso _picasso;

    @Bind(R.id.common_list)
    ListView _listView;

    private TopAdapter _topAdapter;
    private List<Top> _result = new ArrayList<>();
    private Subscription _subscription;

    public static void start(Context context) {
        context.startActivity(new Intent(context, TopActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);
        ButterKnife.bind(this);

        _topAdapter = new TopAdapter(this, _result, _picasso);
        _listView.setAdapter(_topAdapter);

        _subscription = bindUntilEvent$(_topService.list(), ActivityEvent.PAUSE).subscribe(new Observer<Top.Result>() {
            @Override
            public void onCompleted() {
                Timber.i("### onCompleted.");
            }

            @Override
            public void onError(Throwable e) {
                Timber.e("### onError. error is %s", e);
            }

            @Override
            public void onNext(Top.Result result) {
                _result.addAll(result.getTngou());
                _topAdapter.notifyDataSetChanged();
            }
        });

        // unsubscribed until onDestroy()
        /**
        bindToLifecycle$(_topService.list()).subscribe(new Observer<Top.Result>() {
            @Override
            public void onCompleted() {
                Timber.i("### onCompleted.");
            }

            @Override
            public void onError(Throwable e) {
                Timber.e("### onError. error is %s", e);
            }

            @Override
            public void onNext(Top.Result result) {
                _result.addAll(result.getTngou());
                _topAdapter.notifyDataSetChanged();
            }
        });
         */
    }

    @Override
    protected void onResume() {
        super.onResume();
        Timber.i("### onResume. subscription is unsubscribed ? %s", _subscription.isUnsubscribed());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Timber.i("### onPause. subscription is unsubscribed ? %s", _subscription.isUnsubscribed());
    }
}
