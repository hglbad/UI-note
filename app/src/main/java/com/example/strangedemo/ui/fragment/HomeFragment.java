package com.example.strangedemo.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import butterknife.BindView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.strangedemo.R;
import com.example.strangedemo.base.BaseFragment;
import com.example.strangedemo.dialog.ITosat;
import com.example.strangedemo.ui.adapter.ImageAdapter;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.config.BannerConfig;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Transformer;

public class HomeFragment extends BaseFragment implements OnBannerListener {

    @BindView(R.id.banner)
    Banner mBanner;

    List<String> images = new ArrayList<>();

    @Override
    public int bindLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void init() {
        initData();

        mBanner.addBannerLifecycleObserver(this)
                .setAdapter(new ImageAdapter(images))
                .setIndicator(new CircleIndicator(getContext()));

//        mBanner.setBannerGalleryMZ(60,0.8f);
        mBanner.setBannerGalleryMZ(0,1);
    }

    private void initData() {
        images = new ArrayList<>();
        images.add("https://ae01.alicdn.com/kf/U54ae3ae4e9ac4572a436c11a9cfa4927E.jpg");
        images.add("https://ae01.alicdn.com/kf/Uda72804a12f94aceb38a28fa6dde9eb3w.jpg");
        images.add("https://ae01.alicdn.com/kf/U5fac38377c6d434b94fd1bbab4e5e0efz.jpg");
        images.add("https://ae01.alicdn.com/kf/U6cdabb09d259457291dee1102d5d7a22i.jpg");
        images.add("https://ae01.alicdn.com/kf/U9a21a2f4b83c4030b87c840bc07105e5A.jpg");
        images.add("https://ae01.alicdn.com/kf/U8f29046315a345b488a91f19e0691d7bx.jpg");
    }

    @Override
    public void loadData() {

    }

    @Override
    public void OnBannerClick(Object data, int position) {   //  监听每一个图片的点击事件
        ITosat.showTextToas(getContext(),"你点了第"+position+"张轮播图");
    }
}