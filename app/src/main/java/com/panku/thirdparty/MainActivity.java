package com.panku.thirdparty;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.orhanobut.logger.Logger;
import com.panku.thirdparty.mzBanner.BannerActivity;
import com.panku.thirdparty.selectcity.SelectCityActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private RVAdapter adapter;
    private List<String> items;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void initData() {
        items = new ArrayList<>();
        items.add("选择城市");
        items.add("轮播图");
        Logger.e(items.toString());
        adapter = new RVAdapter(items);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                switch (position) {
                    case 0:
                        intent.setClass(MainActivity.this, SelectCityActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent.setClass(MainActivity.this, BannerActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    class RVAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public RVAdapter(@Nullable List<String> data) {
            super(R.layout.item_home, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.tv_text, item);
        }
    }
}
