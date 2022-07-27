package com.example.strangedemo.dialog;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.OnClick;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.strangedemo.R;
import com.example.strangedemo.base.BaseActivity;

public class DialogActivity extends BaseActivity {

    @BindView(R.id.dialog_a)
    Button dialoga;
    @BindView(R.id.dialog_b)
    Button dialogb;
    @BindView(R.id.dialog_c)
    Button dialogc;

    @Override
    public int bindLayout() {
        return R.layout.activity_dialog;
    }

    @Override
    public void initView() {

    }

    @Override
    public void loadData() {

    }

    @OnClick({R.id.dialog_a,R.id.dialog_b,R.id.dialog_c})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dialog_a:
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setMessage("是否出件")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ITosat.showTextToas(DialogActivity.this,"确定");
                            }
                        }).create();
                dialog.show();
                break;
            case R.id.dialog_b:
                break;
            case R.id.dialog_c:
                break;
            default:
        }
    }
}