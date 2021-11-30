package com.example.strangedemo.base;

import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import krt.wid.base.MBaseFragment;

/**
 * author: MaGua
 * create on:2021/4/25 10:42
 * description
 */
public abstract class BaseFragment extends MBaseFragment {

    private Unbinder mUnbinder;

    private final String TAG = this.getClass().getSimpleName();

    @Override
    public void bindButterKnife(View view) {
        mUnbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void unBindButterKnife() { }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @Override
    public void init() {
    }

    @Override
    public void initView(View view) {
    }

}
