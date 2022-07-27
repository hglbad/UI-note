package com.example.strangedemo.ui.adapter;

import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.strangedemo.R;
import com.example.strangedemo.dialog.ITosat;
import com.example.strangedemo.ui.util.SpannableStringUtils;
import com.example.strangedemo.ui.util.Status;
import com.example.strangedemo.ui.util.Utils;


public class NestAdapter extends BaseQuickAdapter<Status, BaseViewHolder> {
    public NestAdapter() {
        super(R.layout.layout_nest_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, Status item) {
        helper.addOnClickListener(R.id.tweetText);
        switch (helper.getLayoutPosition()%
                3){
            case 0:
                helper.setImageResource(R.id.img,R.mipmap.animation_img1);
                break;
            case 1:
                helper.setImageResource(R.id.img,R.mipmap.animation_img2);
                break;
            case 2:
                helper.setImageResource(R.id.img,R.mipmap.animation_img3);
                break;
        }
        helper.setText(R.id.tweetName,"测试");
        String msg="\"He was one of Australia's most of distinguished artistes, renowned for his portraits\"";
        ( (TextView)helper.getView(R.id.tweetText)).setText(SpannableStringUtils.getBuilder(msg).append("landscapes and nedes").setClickSpan(clickableSpan).create());
        ( (TextView)helper.getView(R.id.tweetText)).setMovementMethod(LinkMovementMethod.getInstance());
    }

    ClickableSpan clickableSpan = new ClickableSpan() {
        @Override
        public void onClick(View widget) {
            ITosat.showTextToas(mContext,"点击事件");
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(Utils.getContext().getResources().getColor(R.color.clickspan_color));
            ds.setUnderlineText(true);
        }
    };
}
