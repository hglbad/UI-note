package com.example.strangedemo.ui.fragment;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.strangedemo.R;
import com.example.strangedemo.base.BaseFragment;
import com.example.strangedemo.ui.adapter.ItemClickAdapter;
import com.example.strangedemo.ui.util.ClickEntity;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class ListFragment extends BaseFragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    private ItemClickAdapter adapter;

    @Override
    public int bindLayout() {
        return R.layout.fragment_list;
    }

    @Override
    public void loadData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        initAdapter();
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getActivity(), "单击控件" + position, Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getActivity(), "长按控件" + position, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getActivity(), "单击子控件" + position, Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setOnItemChildLongClickListener(new BaseQuickAdapter.OnItemChildLongClickListener() {
            @Override
            public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getActivity(), "长按子控件" + position, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    private void initAdapter() {
        List<ClickEntity> data = new ArrayList<>();
        data.add(new ClickEntity(ClickEntity.CLICK_ITEM_VIEW));
        data.add(new ClickEntity(ClickEntity.CLICK_ITEM_CHILD_VIEW));
        data.add(new ClickEntity(ClickEntity.LONG_CLICK_ITEM_VIEW));
        data.add(new ClickEntity(ClickEntity.LONG_CLICK_ITEM_CHILD_VIEW));
//        data.add(new ClickEntity(ClickEntity.NEST_CLICK_ITEM_CHILD_VIEW));
        adapter = new ItemClickAdapter(data);
        adapter.openLoadAnimation();
        recyclerView.setAdapter(adapter);
    }

}