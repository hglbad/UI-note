package com.example.strangedemo.login.fragment;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.strangedemo.R;
import com.example.strangedemo.base.BaseFragment;
import com.example.strangedemo.dialog.ITosat;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class YZMFragment extends BaseFragment {

    @BindView(R.id.dx_phone)
    EditText phone;
    @BindView(R.id.yzm_pwd)
    EditText yzm;
    @BindView(R.id.getYzm)
    TextView getYzm;


    private Disposable mDisposable;

    private final int timerNum = 60;

    @Override
    public void initView(View view) {
        getYzm.setOnClickListener(view1 -> {
            getYzm.setClickable(false);
            tikTok(timerNum);
        });
    }

    @Override
    public void loadData() {

    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_yzm;
    }

    public String[] getInputDate() {
        return new String[]{phone.getText().toString(), yzm.getText().toString()};
    }

    private void tikTok(int i) {
        if (phone.getText().toString().trim().length() != 11) {
            ITosat.showTextToas(mContext, "请输入正确的手机号码");
            return;
        }

        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(i)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onNext(Long aLong) {
                        if (getYzm != null) {
                            getYzm.setText("重新发送" + (i - aLong));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mDisposable != null)
                            mDisposable.dispose();
                    }

                    @Override
                    public void onComplete() {
                        if (getYzm != null) {
                            getYzm.setText("获取动态密码");
                            getYzm.setClickable(true);
                        }
                        if (mDisposable != null)
                            mDisposable.dispose();
                    }
                });

        //短信验证码接口
    }
}
