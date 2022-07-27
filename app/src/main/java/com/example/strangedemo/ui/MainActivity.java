package com.example.strangedemo.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

import com.example.strangedemo.R;
import com.example.strangedemo.base.BaseActivity;
import com.example.strangedemo.ui.fragment.HomeFragment;
import com.example.strangedemo.ui.fragment.ListFragment;
import com.example.strangedemo.view.SViewPager;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    @BindView(R.id.viewpager)
    SViewPager viewPager;

    @BindView(R.id.indicator)
    MagicIndicator indicator;

    Adapter adapter;

    private int[] selectMipmap = {R.mipmap.p01_19, R.mipmap.p01_20, R.mipmap.p01_21, R.mipmap.p01_22};
    private int[] defMipmap = {R.mipmap.p01_14, R.mipmap.p01_15, R.mipmap.p01_16, R.mipmap.p01_17};
    private String[] titles = {"首页", "第二", "第三", "第四"};

    @Override
    public int bindLayout() {
        return R.layout.activity_main2;
    }

    @Override
    public void initView() {

        viewPager.setCanScroll(false);
        viewPager.setOffscreenPageLimit(5);

        adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment());//首页
        adapter.addFragment(new ListFragment());//
        adapter.addFragment(new HomeFragment());//
        adapter.addFragment(new HomeFragment());//
        viewPager.setAdapter(adapter);
        initIndicator();

        viewPager.setCurrentItem(0);
    }

    /**
     * 初始化指示器
     */
    private void initIndicator() {
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return titles.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, int index) {
                CommonPagerTitleView commonPagerTitleView = new CommonPagerTitleView(context);
                // load custom layout
                View customLayout = LayoutInflater.from(context).inflate(R.layout.item_main_bottom, null);
                final ImageView imageView = customLayout.findViewById(R.id.item_main_img);
                final TextView textView = customLayout.findViewById(R.id.item_main_title);

                imageView.setImageResource(defMipmap[index]);
                textView.setText(titles[index]);
                commonPagerTitleView.setOnClickListener(v -> viewPager.setCurrentItem(index));
                commonPagerTitleView.setOnPagerTitleChangeListener(new CommonPagerTitleView.OnPagerTitleChangeListener() {
                    @Override
                    public void onSelected(int index, int totalCount) {
                        imageView.setImageResource(selectMipmap[index]);
                        textView.setTextColor(getResources().getColor(R.color.colorPrimary));
                    }

                    @Override
                    public void onDeselected(int index, int totalCount) {
                        imageView.setImageResource(defMipmap[index]);
                        textView.setTextColor(getResources().getColor(R.color.color_767676));
                    }

                    @Override
                    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {

                    }

                    @Override
                    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {

                    }
                });
                commonPagerTitleView.setContentView(customLayout);

                return commonPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                return null;
            }
        });


        commonNavigator.setAdjustMode(true);
        indicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(indicator, viewPager);
    }

    @Override
    public void loadData() {

    }

    /**
     * 配置adapter
     */
    private List<Fragment> mFragments = new ArrayList<>();

    private class Adapter extends FragmentStatePagerAdapter {

        Adapter(FragmentManager fm) {
            super(fm);
        }

        void addFragment(Fragment fragment) {
            mFragments.add(fragment);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

    }
}