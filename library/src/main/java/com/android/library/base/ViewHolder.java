package com.android.library.base;

import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by dugang on 2016/05/19.ViewHolder基类
 */
@SuppressWarnings("unused")
public abstract class ViewHolder {
    public ViewHolder(View view) {
        ButterKnife.bind(this, view);
    }

    public abstract void initData(int position);
}
