/*
 * Copyright 2015 zengzhihao.github.io. All rights reserved.
 * Support: http://zengzhihao.github.io
 */

package io.github.zengzhihao.tngou.ui;

import android.os.Bundle;

import javax.inject.Inject;

import io.github.zengzhihao.tngou.ui.base.AbstractActivity;
import io.github.zengzhihao.tngou.ui.top.TopActivity;
import io.github.zengzhihao.tngou.util.ToastHelper;

/**
 * @author Kela.King
 */
public class HomeActivity extends AbstractActivity {
    @Inject
    ToastHelper _toastHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _toastHelper.show("Hello Tngou!");
        TopActivity.start(this);
        finish();
    }
}
