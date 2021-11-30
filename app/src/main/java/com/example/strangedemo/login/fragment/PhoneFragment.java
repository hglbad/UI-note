package com.example.strangedemo.login.fragment;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.strangedemo.R;
import com.example.strangedemo.base.BaseFragment;

import butterknife.BindView;

/**
 * @author xzy
 * @package com.krt.tour_ja.ui.fragment
 * @description
 * @time 2020/7/9
 */
public class PhoneFragment extends BaseFragment {

    @BindView(R.id.userName_phone)
    EditText phone;
    @BindView(R.id.password_pwd)
    EditText password;
    @BindView(R.id.seePasWord)
    ImageView eyes;
    private boolean isVisiablePwd;

    @Override
    public int bindLayout() {
        return R.layout.fragment_phone;
    }

    @Override
    public void init() {
        eyes.setOnClickListener(view -> {
            eyes.setImageResource(isVisiablePwd ? R.mipmap.closeseep : R.mipmap.eye);
            password.setTransformationMethod(isVisiablePwd ?
                    PasswordTransformationMethod.getInstance() :
                    HideReturnsTransformationMethod.getInstance());
            isVisiablePwd = !isVisiablePwd;
        });
    }

    @Override
    public void loadData() {

    }

    public String[] getInputDate() {
        return new String[]{phone.getText().toString(), password.getText().toString()};
    }
}
