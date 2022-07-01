package com.example.strangedemo.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomView extends View {

    // 如果View是在Java代码里面new的，则调用第一个构造函数
//    public CustomView(Context context) {
//        super(context);
//    }

    // 如果View是在.xml里声明的，则调用第二个构造函数
// 自定义属性是从AttributeSet参数传进来的
//    public  CustomView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }

    // 不会自动调用
// 一般是在第二个构造函数里主动调用// 如View有style属性时
    public  CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //API21之后才使用
// 不会自动调用
// 一般是在第二个构造函数里主动调用
// 如View有style属性时
//    public  CustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }

//    @Override
//    protected void onMeasure() {
//
//    }
}
