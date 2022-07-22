package com.example.strangedemo.double_demo;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.OnClick;
import krt.wid.util.ParseJsonUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.example.strangedemo.R;
import com.example.strangedemo.base.BaseActivity;
import com.example.strangedemo.double_demo.util.CopyButtonLibrary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends BaseActivity {

    @BindView(R.id.show_text)
    TextView showText;
    @BindView(R.id.choose)
    TextView choose;
    @BindView(R.id.copy)
    TextView copy;
    @BindView(R.id.del)
    TextView del;

    int[] hong = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33};

    int[] lan = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};

    List<String> blue = new ArrayList<>();

    @Override
    public int bindLayout() {
        return R.layout.activity_main3;
    }

    @Override
    public void initView() {

    }

    @Override
    public void loadData() {

    }

    @OnClick({R.id.choose, R.id.copy, R.id.del})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.choose:
                chooseRed();
                break;
            case R.id.copy:
                CopyButtonLibrary copyButtonLibrary = new CopyButtonLibrary(getApplicationContext(), showText);
                copyButtonLibrary.init();
                break;
            case R.id.del:
                showText.setText("");
                break;
            case R.id.luy:

                break;
            default:
        }
    }


    private void chooseRed() {
        List<Integer> red = new ArrayList<>();
        for (int i = 0; i < hong.length; i++) {
            red.add(hong[i]);
        }

        Random random = new Random();

        int[] redReturn = new int[6];
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(red.size());
            redReturn[i] = red.get(index);
            red.remove(index);
        }

        showText.setText(ParseJsonUtil.toJson(redReturn).replace("[","").replace("]",""));

        Random random2 = new Random();
        int num = random2.nextInt(16) % (16) + 1;

        showText.append("," + num);
    }
}