package com.example.strangedemo.login.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.OnClick;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.strangedemo.R;
import com.example.strangedemo.base.BaseActivity;
import com.example.strangedemo.dialog.ITosat;
import com.example.strangedemo.login.fragment.PhoneFragment;
import com.example.strangedemo.login.fragment.YZMFragment;
import com.example.strangedemo.ui.MainActivity;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * 可切换登录页面布局（通过ViewPager实现滑动切换）
 */

public class LoginCut2Activity extends BaseActivity {

    @BindView(R.id.magic)
    MagicIndicator magicIndicator;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.agree_radio)
    CheckBox agreeRadio;

    PhoneFragment phoneFragment;
    YZMFragment yzmFragment;

    private String[] titles = {"账号登录", "验证码登录"};
    private List<Fragment> fragmentList = new ArrayList<>();
    private boolean isshow;

    @Override
    public int bindLayout() {
        return R.layout.activity_login_cut2;
    }

    @Override
    public void initView() {
        yzmFragment = new YZMFragment();
        phoneFragment = new PhoneFragment();
        fragmentList.add(phoneFragment);
        fragmentList.add(yzmFragment);
        initIndicatior();
    }

    /**
     * 初始化initIndicatior配置adapter
     */
    private void initIndicatior() {
        viewPager.setAdapter(new MPagerAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(titles.length);
        LinearLayout.LayoutParams layout = (LinearLayout.LayoutParams) magicIndicator.getLayoutParams();
        layout.gravity = Gravity.CENTER_HORIZONTAL;
        magicIndicator.setLayoutParams(layout);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return titles.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int i) {
                CommonPagerTitleView commonPagerTitleView = new CommonPagerTitleView(context);
                View customLayout = LayoutInflater.from(context).inflate(R.layout.item_login, null);
                final TextView name = customLayout.findViewById(R.id.name);
                name.setText(titles[i]);
                customLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                commonPagerTitleView.setContentView(customLayout);
                commonPagerTitleView.setOnPagerTitleChangeListener(new CommonPagerTitleView.OnPagerTitleChangeListener() {
                    @Override
                    public void onSelected(int index, int totalCount) {

                    }

                    @Override
                    public void onDeselected(int index, int totalCount) {

                    }

                    @Override
                    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
                        float mMinScale = 0.75f;
                        name.setScaleX(1.1f + (mMinScale - 1.0f) * leavePercent);
                        name.setScaleY(1.1f + (mMinScale - 1.0f) * leavePercent);
                        name.setTextColor(ContextCompat.getColor(LoginCut2Activity.this, R.color.color_333333));
                        name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);


                    }

                    @Override
                    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
                        float mMinScale = 0.75f;
                        name.setScaleX(mMinScale + (1.0f - mMinScale) * enterPercent);
                        name.setScaleY(mMinScale + (1.0f - mMinScale) * enterPercent);
                        name.setTextColor(ContextCompat.getColor(LoginCut2Activity.this, R.color.color_2E7EFF));
                        name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);


                    }
                });
                commonPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(i);
                    }
                });


                return commonPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 3));
                indicator.setLineWidth(UIUtil.dip2px(context, 25));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(ContextCompat.getColor(context, R.color.color_2E7EFF));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @OnClick(R.id.login)
    public void onClick(View view) {
        String[] inputs;
        inputs = viewPager.getCurrentItem() == 0 ?
                phoneFragment.getInputDate() : yzmFragment.getInputDate();

        if (!agreeRadio.isChecked()) {
            ITosat.showTextToas(LoginCut2Activity.this, "请勾选阅读并同意按钮");
            return;
        }

        if (TextUtils.isEmpty(inputs[0])) {
            ITosat.showTextToas(this, "请输入手机号");
            return;
        }

        if (TextUtils.isEmpty(inputs[1])) {
            ITosat.showTextToas(this, viewPager.getCurrentItem() == 0 ?
                    "请输入密码" : "请输入收到的短信动态密码");
            return;
        }



        ITosat.showTextToas(this, viewPager.getCurrentItem() == 0 ?
                "通过手机号登录" : "通过验证码登录");
        startActivity(new Intent(LoginCut2Activity.this, MainActivity.class));
    }

    @Override
    public void loadData() {

    }

    private class MPagerAdapter extends FragmentPagerAdapter {

        public MPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}