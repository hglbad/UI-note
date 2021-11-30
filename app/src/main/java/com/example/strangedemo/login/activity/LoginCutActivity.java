package com.example.strangedemo.login.activity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.strangedemo.R;
import com.example.strangedemo.base.BaseActivity;
import com.example.strangedemo.dialog.ITosat;
import com.example.strangedemo.ui.MainActivity;

/**
 * 可切换登录页面布局
 */
public class LoginCutActivity extends BaseActivity {

    /**
     * 0:密码登录  1：验证码登录
     */
    private int loginType = 1;

    private CountDownTimer timer;
    boolean isshow;

    @BindView(R.id.login_dx)
    LinearLayout loginDx;
    @BindView(R.id.login_sjh)
    LinearLayout loginSjh;
    @BindView(R.id.logintype_yzm)
    LinearLayout logintypeYzm;
    @BindView(R.id.logintype_pwd)
    LinearLayout logintypePwd;
    @BindView(R.id.agree_radio)
    CheckBox agreeRadio;
    @BindView(R.id.dx_phone)
    EditText dxPhone;
    @BindView(R.id.yzm_pwd)
    EditText yzmPwd;
    @BindView(R.id.userName_phone)
    EditText userNamePhone;
    @BindView(R.id.password_pwd)
    EditText passwordPwd;
    @BindView(R.id.phone_xx)
    ImageView phoneXx;
    @BindView(R.id.sjh_xx)
    ImageView sjhXx;
    @BindView(R.id.getYzm)
    TextView getYzm;

    @Override
    public int bindLayout() {
        return R.layout.activity_login_cut;
    }

    @Override
    public void initView() {
        //设置手机号一键清空
        dxPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (dxPhone.getEditableText().length() >= 1) {
                    sjhXx.setVisibility(View.VISIBLE);
                } else sjhXx.setVisibility(View.GONE);
            }
        });

        userNamePhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (userNamePhone.getEditableText().length() >= 1) {
                    phoneXx.setVisibility(View.VISIBLE);
                } else phoneXx.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void loadData() {

    }

    @OnClick({R.id.logintype_pwd, R.id.logintype_yzm, R.id.login, R.id.phone_xx, R.id.sjh_xx,R.id.getYzm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logintype_pwd:
                //密码登录
                loginType = 0;
                showPwd();
                break;
            case R.id.logintype_yzm:
                //验证码登录
                loginType = 1;
                showPwd();
                break;
            case R.id.login:
                startLogin();
                break;
            case R.id.phone_xx:
                userNamePhone.setText("");
                break;
            case R.id.sjh_xx:
                dxPhone.setText("");
                break;
            //获取验证码
            case R.id.getYzm:
                if (TextUtils.isEmpty(dxPhone.getText().toString())) {
                    ITosat.showTextToas(this, "请先输入手机号");
                    return;
                }
                getYzm();
                break;
            default:
                break;
        }
    }

    /**
     * 调取接口获取短信验证码，在请求成功后开始倒计时
     */
    private void getYzm() {
        timer = new CountDownTimer(60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                try {
                    getYzm.setText(" " + millisUntilFinished / 1000 + " 秒");
                    getYzm.setClickable(false);
                } catch (Exception e) {
                }

            }

            @Override
            public void onFinish() {
                try {
                    getYzm.setText("获取验证码");
                    getYzm.setClickable(true);
                } catch (Exception e) {
                }
            }
        }.start();
    }

    /**
     * 开始登录
     */
    private void startLogin() {
        if (!agreeRadio.isChecked()) {
            ITosat.showTextToas(LoginCutActivity.this, "请勾选阅读并同意按钮");
            return;
        }

        //登录信息判空
        if (loginType == 0) {
            getPwdEmpty();
        } else {
            getYzmEmpty();
        }
    }

    private void getYzmEmpty() {
        if (TextUtils.isEmpty(dxPhone.getText().toString())) {
            ITosat.showTextToas(this, "请先输入手机号");
            return;
        }

        if (TextUtils.isEmpty(yzmPwd.getText().toString())) {
            ITosat.showTextToas(this, "请先输入验证码");
            return;
        }

        ITosat.showTextToas(this, "通过验证码登录");
        startActivity(new Intent(LoginCutActivity.this, MainActivity.class));
    }

    private void getPwdEmpty() {
        if (TextUtils.isEmpty(userNamePhone.getText().toString())) {
            ITosat.showTextToas(this, "请先输入账号");
            return;
        }

        if (TextUtils.isEmpty(passwordPwd.getText().toString())) {
            ITosat.showTextToas(this, "请先输入密码");
            return;
        }

        ITosat.showTextToas(this, "通过手机号登录");
        startActivity(new Intent(LoginCutActivity.this, MainActivity.class));
    }

    private void showPwd() {
        //输入页面
        loginSjh.setVisibility(loginType == 0 ? View.VISIBLE : View.GONE);
        loginDx.setVisibility(loginType == 0 ? View.GONE : View.VISIBLE);
        //底部验证码图标
        logintypeYzm.setVisibility(loginType == 0 ? View.VISIBLE : View.GONE);
        //底部密码图标
        logintypePwd.setVisibility(loginType == 0 ? View.GONE : View.VISIBLE);
    }

    @OnClick(R.id.seePasWord)
    public void onShow(View view) {
        if (isshow) {
            //隐藏
            ((ImageButton) view).setImageResource(R.mipmap.closeseep);
            passwordPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else {
            ((ImageButton) view).setImageResource(R.mipmap.eye);
            passwordPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        isshow = !isshow;
        passwordPwd.setSelection(passwordPwd.getText().toString().length());//设置光标位置在文本框末
    }
}