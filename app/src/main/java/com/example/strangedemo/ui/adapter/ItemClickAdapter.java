package com.example.strangedemo.ui.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.strangedemo.R;
import com.example.strangedemo.ui.util.ClickEntity;
import com.example.strangedemo.view.NumberButton;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 *
 */
public class ItemClickAdapter extends BaseMultiItemQuickAdapter<ClickEntity, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {
    NestAdapter nestAdapter;

    private int i = 0;
    private int returnnub = 0;

    public ItemClickAdapter(List<ClickEntity> data) {
        super(data);
        addItemType(ClickEntity.CLICK_ITEM_VIEW, R.layout.item_click_view);
        addItemType(ClickEntity.CLICK_ITEM_CHILD_VIEW, R.layout.item_click_childview);
        addItemType(ClickEntity.LONG_CLICK_ITEM_VIEW, R.layout.item_long_click_view);
        addItemType(ClickEntity.LONG_CLICK_ITEM_CHILD_VIEW, R.layout.item_long_click_childview);
        addItemType(ClickEntity.NEST_CLICK_ITEM_CHILD_VIEW, R.layout.item_nest_click);

    }


    @Override
    protected void convert(final BaseViewHolder helper, final ClickEntity item) {
        switch (helper.getItemViewType()) {
            case ClickEntity.CLICK_ITEM_VIEW:
                helper.addOnClickListener(R.id.btn);
                break;
            case ClickEntity.CLICK_ITEM_CHILD_VIEW:
                ImageView addbtn = helper.getView(R.id.iv_num_add);
                ImageView reducebtn = helper.getView(R.id.iv_num_reduce);
                addbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        i++;
                        helper.setText(R.id.tv_num, String.valueOf(i));
                    }
                });
                reducebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        i--;
                        helper.setText(R.id.tv_num, String.valueOf(i));
                    }
                });
                // set img data
                break;
            case ClickEntity.LONG_CLICK_ITEM_VIEW:
                helper.addOnLongClickListener(R.id.btn);
                break;
            case ClickEntity.LONG_CLICK_ITEM_CHILD_VIEW:
                NumberButton nb = helper.getView(R.id.numbth);
                nb.setMax(5);
                nb.setCount(0);
                returnnub = 0;
                nb.setEnable(true);
                nb.setListener(new NumberButton.CountListener() {
                    @Override
                    public void countChange(int count) {
                        returnnub = count;
                    }
                });
                break;
            case ClickEntity.NEST_CLICK_ITEM_CHILD_VIEW:
                helper.setNestView(R.id.item_click); // u can set nestview id
                final RecyclerView recyclerView = helper.getView(R.id.nest_list);
                recyclerView.setLayoutManager(new LinearLayoutManager(helper.itemView.getContext(), LinearLayoutManager.VERTICAL, false));
                recyclerView.setHasFixedSize(true);

                nestAdapter = new NestAdapter();
                nestAdapter.setOnItemClickListener(this);
                nestAdapter.setOnItemChildClickListener(this);
                recyclerView.setAdapter(nestAdapter);
                break;
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        Toast.makeText(mContext, "childView click", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//        Logger.d("嵌套RecycleView item 收到: " + "点击了第 " + position + " 一次");
        Toast.makeText(mContext, "嵌套RecycleView item 收到: " + "点击了第 " + position + " 一次", Toast.LENGTH_SHORT).show();
    }
}
