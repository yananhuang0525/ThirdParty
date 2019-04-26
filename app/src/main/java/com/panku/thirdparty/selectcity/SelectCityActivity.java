package com.panku.thirdparty.selectcity;


import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gavin.com.library.PowerfulStickyDecoration;
import com.gavin.com.library.listener.PowerGroupListener;
import com.panku.thirdparty.BaseActivity;
import com.panku.thirdparty.BaseApplication;
import com.panku.thirdparty.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * data 2019/4/25
 * time 15:03
 * Created by huangyanan.
 */
public class SelectCityActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private CitySideBar mContactSideber;
    PowerfulStickyDecoration decoration;
    AdapterSelectCity adapterSelectCity;
    private List<CityPickerModel> allCities;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_selectcity;
    }

    @Override
    protected void initView() {
        TextView mContactDialog = findViewById(R.id.tv_dialog);
        mContactSideber = findViewById(R.id.qb);
        mContactSideber.setTextDialog(mContactDialog);
        recyclerView = findViewById(R.id.rv_city);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void initData() {
        allCities = new ArrayList<>();
        CityPickerModel cityPickerModel = null;
        List<CityModel> cityModels = JSON.parseArray(BaseApplication.cityModelStr, CityModel.class);
        for (int i = 0; i < cityModels.size(); i++) {
            for (int j = 0; j < cityModels.get(i).getChildren().size(); j++) {
                CityModel.ChildrenBeanX childrenBeanX = cityModels.get(i).getChildren().get(j);
                City city = new City();
                city.setCityname(childrenBeanX.getName());
                city.setCitycode(childrenBeanX.getCode());
                cityPickerModel = new CityPickerModel(city);
                allCities.add(cityPickerModel);
            }
        }
        Collections.sort(allCities);//排序
        //------------- PowerfulStickyDecoration 使用部分  ----------------
        PowerGroupListener listener = new PowerGroupListener() {
            @Override
            public String getGroupName(int position) {
                //获取组名，用于判断是否是同一组
                if (allCities.size() > position) {
                    return allCities.get(position).getFirstLetter();
                }
                return null;
            }

            @Override
            public View getGroupView(int position) {
                //获取自定定义的组View
                if (allCities.size() > position) {
                    View view = getLayoutInflater().inflate(R.layout.item_city_picker_character, null, false);
                    ((TextView) view.findViewById(R.id.character)).setText(allCities.get(position).getFirstLetter());
                    return view;
                } else {
                    return null;
                }
            }
        };
        decoration = PowerfulStickyDecoration.Builder
                .init(listener)
                .setCacheEnable(false)                                              //是否使用缓存
                .setHeaderCount(0)//头部Item数量仅LinearLayoutManager
                .build();
        //-------------                  ----------------
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(decoration);
        adapterSelectCity = new AdapterSelectCity(allCities);
        recyclerView.setAdapter(adapterSelectCity);
        adapterSelectCity.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CityPickerModel cityPickerModel = (CityPickerModel) adapter.getItem(position);
                toastMessage(cityPickerModel.getCity().getCityname());
//                //数据是使用Intent返回
//                Intent intent = new Intent();
//                //把返回数据存入Intent
//                intent.putExtra("City", JSON.toJSONString(cityPickerModel.getCity()));
//                //设置返回数据
//                SelectCityActivity.this.setResult(RESULT_OK, intent);
//                //关闭Activity
//                SelectCityActivity.this.finish();
            }
        });
        mContactSideber.setOnTouchLitterChangedListener(new CitySideBar.OnTouchLetterChangedListener() {

            @Override
            public void touchLetterChanged(String s) {
                int position = -1;
                for (int i = 0; i < allCities.size(); i++) {
                    if (allCities.get(i).getFirstLetter().equals(s)) {
                        position = i;
                        break;
                    }
                }
                if (position != -1) {
                    linearLayoutManager.scrollToPositionWithOffset(position, 0);
                }
            }

//            }
        });
    }
}
