package com.example.strangedemo.base;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.strangedemo.util.IStatusBarUtil;
import com.jaeger.library.StatusBarUtil;

import androidx.core.app.ActivityOptionsCompat;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import krt.wid.base.MBaseActivity;

/**
 * author: MaGua
 * create on:2021/4/25 10:16
 * description
 */
public abstract class BaseActivity extends MBaseActivity {

    private Unbinder mUnbinder;


    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    public void beforeBindLayout() {
        setStatusBar();
        IStatusBarUtil.setTranslucentStatus(this);
        IStatusBarUtil.setStatusBarDarkTheme(this, true);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public void bindButterKnife() {
        mUnbinder = ButterKnife.bind(this);
    }

    @Override
    public void unbindButterknife() {
        mUnbinder.unbind();
    }

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    public void init() {
//        setBarColor();
    }


    /**
     * 设置状态栏颜色
     */
    protected void setStatusBar() {
        StatusBarUtil.setTranslucentForImageView(this, 0, null);
    }

    public void startActivityTransform(ImageView img, String element, Intent intent) {
        Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(this, img, element).toBundle();
        startActivity(intent, bundle);
    }

    public void setBarColor() {
        View decor = getWindow().getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

}
