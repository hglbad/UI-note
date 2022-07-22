package com.example.strangedemo;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.OnClick;
import krt.wid.util.MPermissions;
import krt.wid.util.MToast;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.strangedemo.base.BaseActivity;
import com.example.strangedemo.dialog.DialogActivity;
import com.example.strangedemo.login.activity.LoginCut2Activity;
import com.example.strangedemo.login.activity.LoginCutActivity;
import com.example.strangedemo.login.activity.LoginNormActivity;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tv_login)
    Button login;
    @BindView(R.id.tv_homepage)
    Button homepage;
    @BindView(R.id.tv_login_2)
    Button login2;
    @BindView(R.id.tv_login_3)
    Button login3;

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.tv_login, R.id.tv_homepage,R.id.tv_login_2,R.id.tv_login_3,R.id.tv_dialog})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                startActivity(new Intent(MainActivity.this, LoginNormActivity.class));
                break;
            case R.id.tv_login_2:
                startActivity(new Intent(MainActivity.this, LoginCutActivity.class));
                break;
            case R.id.tv_login_3:
                startActivity(new Intent(MainActivity.this, LoginCut2Activity.class));
                break;
            case R.id.tv_homepage:
                startActivity(new Intent(MainActivity.this, com.example.strangedemo.ui.MainActivity.class));
                break;
            case R.id.tv_dialog:
                startActivity(new Intent(MainActivity.this, DialogActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void initView() {

        //动态权限申请
        MPermissions.getInstance().request(MainActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, new MPermissions.PermissionListener() {
            @Override
            public void callBack(boolean value) {
                if (value) {

                } else {
                    MToast.showToast(MainActivity.this, "请授予相关权限,以执行后续操作!");
                }
            }
        });
    }

    @Override
    public void loadData() {

    }
}