package com.example.strangedemo.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.strangedemo.R;

import io.reactivex.annotations.Nullable;
import krt.wid.util.MToast;


/**
 * @author Marcus
 * @package krt.wid.tour_gz.view
 * @description
 * @time 2018/6/20
 */

public class NumberButton extends LinearLayout implements View.OnClickListener, TextWatcher {

    EditText mCount;

    TextView addButton;

    TextView subButton;

    int count = 1;

    boolean enable;

    CountListener mListener;

    boolean ifAddOrSub = true;

    int max = -1;
    int min = 0;

    public NumberButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setListener(CountListener listener) {
        mListener = listener;
    }

    public NumberButton setEnable(boolean enable) {
        this.enable = enable;
        return this;
    }

    public void setMax(int max) {
        this.max = max;
    }


    public void setMin(int min) {
        this.min = min;
    }

    /**
     * 加减按钮是否可用(用于秒杀只能选择一张功能)
     *
     * @param flag 默认为true
     * @return
     */
    public NumberButton setIfAddOrSub(boolean flag) {
        this.ifAddOrSub = flag;
        addButton.setClickable(!ifAddOrSub);
        return this;
    }

    public NumberButton setCount(int count) {
        this.count = count;
        if(max !=-1 && count == max){
            addButton.setClickable(false);
        }
        mCount.setText(count + "");
        return this;
    }

    private void init() {
        LayoutInflater.from(this.getContext()).inflate(R.layout.layout_number_btn, this);

        addButton = findViewById(R.id.button_add);

        addButton.setOnClickListener(this);

        subButton = findViewById(R.id.button_sub);

        subButton.setOnClickListener(this);

        mCount = (EditText) findViewById(R.id.text_count);

        mCount.setEnabled(false);

        mCount.addTextChangedListener(this);

        mCount.setOnClickListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_add:
                if (ifAddOrSub) {
                    if (enable) {
                        //正常添加
                        count++;
                        if (count >= 0) addButton.setClickable(true);

                        if (max !=-1 && count == max){
                            addButton.setClickable(false);
                        }

                        if (count > min) subButton.setClickable(true);

                        mCount.setText(String.valueOf(count));
                        if (mListener != null) mListener.countChange(count);
                    } else {
                        MToast.showToast(getContext(), "请先选择完整的规格!");
                    }
                }
                break;
            case R.id.button_sub:
                if (ifAddOrSub) {
                    if (enable) {
                        if (count > min) {
                            //正常减
                            count--;
                            if (count <= min) subButton.setClickable(false);
                            mCount.setText(String.valueOf(count));
                            if (mListener != null) mListener.countChange(count);

                            if(count < max){
                                addButton.setClickable(true);
                            }
                        }
                    } else {
                        MToast.showToast(getContext(), "请先选择完整的规格!");
                    }
                }
                break;
            default:
                break;
        }
    }

    public int getCount() {
        return count;
    }


    public interface CountListener {
        void countChange(int count);
    }
}
