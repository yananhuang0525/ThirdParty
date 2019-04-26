package com.panku.thirdparty.selectcity;

import android.support.annotation.NonNull;

import java.util.Locale;

/**
 * data 2019/4/25
 * time 16:59
 * Created by huangyanan.
 */
public class CityPickerModel implements Comparable<CityPickerModel> {
    private City city;
    private String pinyin;
    private String firstLetter;//首字母

    public CityPickerModel(City city) {
        this.city = city;
        //当前城市拼音首字母
        pinyin = PinYinUtil.getPinyin(city.getCityname());
        firstLetter = (pinyin.charAt(0) + "").toUpperCase(Locale.ENGLISH);
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    @Override
    public int compareTo(@NonNull CityPickerModel cityPickerModel) {
        return this.pinyin.compareTo(cityPickerModel.pinyin);
    }
}
