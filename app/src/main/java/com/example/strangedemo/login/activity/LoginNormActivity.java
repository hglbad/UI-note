package com.example.strangedemo.login.activity;

import butterknife.BindView;
import butterknife.OnClick;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.strangedemo.R;
import com.example.strangedemo.base.BaseActivity;
import com.example.strangedemo.dialog.ITosat;
import com.example.strangedemo.ui.MainActivity;

/**
 * 普通登录页面布局
 */
public class LoginNormActivity extends BaseActivity {

    @BindView(R.id.userName_phone)
    EditText username;
    @BindView(R.id.password_pwd)
    EditText password;
    @BindView(R.id.agree_radio)
    CheckBox agreeRadio;
    @BindView(R.id.phone_xx)
    ImageView phoneXx;

    private boolean isshow;
    private CountDownTimer timer;

    @Override
    public int bindLayout() {
        return R.layout.activity_home_norm;
    }

    @Override
    public void initView() {
        //设置手机号一键清空
        phoneXx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username.setText("");
            }
        });

        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (username.getEditableText().length() >= 1) {
                    phoneXx.setVisibility(View.VISIBLE);
                } else phoneXx.setVisibility(View.GONE);
            }
        });
    }

    /**
     * 设置点击事件
     *
     * @param view
     */
    @OnClick({R.id.login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                getEmpty();
                break;
            default:
                break;
        }
    }

    /**
     * 对账号密码进行非空判断
     */
    private void getEmpty() {
        //判断是否勾选隐私协议
        if (!agreeRadio.isChecked()) {
            ITosat.showTextToas(LoginNormActivity.this, "请勾选阅读并同意按钮");
            return;
        }

        if (TextUtils.isEmpty(username.getText().toString())) {
            ITosat.showTextToas(this, "请先输入账号");
            return;
        }

        if (TextUtils.isEmpty(password.getText().toString())) {
            ITosat.showTextToas(this, "请先输入密码");
            return;
        }

        startActivity(new Intent(LoginNormActivity.this, MainActivity.class));
    }

    @Override
    public void loadData() {

    }

    /**
     * 控制密码小眼睛
     *
     * @param view
     */
    @OnClick(R.id.seePasWord)
    public void onShow(View view) {
        if (isshow) {
            //隐藏
            ((ImageButton) view).setImageResource(R.mipmap.closeseep);
            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else {
            ((ImageButton) view).setImageResource(R.mipmap.eye);
            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        isshow = !isshow;
        password.setSelection(password.getText().toString().length());//设置光标位置在文本框末
    }

}