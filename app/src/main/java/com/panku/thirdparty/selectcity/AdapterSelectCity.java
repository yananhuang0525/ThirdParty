package com.panku.thirdparty.selectcity;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.panku.thirdparty.R;

import java.util.List;

/**
 * data 2019/4/25
 * time 16:08
 * Created by huangyanan.
 */
public class AdapterSelectCity extends BaseQuickAdapter<CityPickerModel, BaseViewHolder> {
    public AdapterSelectCity(@Nullable List<CityPickerModel> data) {
        super(R.layout.item_city, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CityPickerModel item) {
        helper.setText(R.id.city_name, item.getCity().getCityname());
    }
}
