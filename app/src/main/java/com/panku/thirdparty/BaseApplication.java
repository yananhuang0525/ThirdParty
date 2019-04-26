package com.panku.thirdparty;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import java.io.InputStream;


/**
 * data 2019/4/25
 * time 15:13
 * Created by huangyanan.
 */
public class BaseApplication extends Application {
    public static String cityModelStr;

    @Override
    public void onCreate() {
        super.onCreate();
        logInit();
        cityModelStr = getFromAssets();
    }

    /**
     * 日志初始化
     */
    private void logInit() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(1)         // (Optional) How many method line to show. Default 2
                .tag("PRETTY_LOGGER")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .methodOffset(0)
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
//        Logger.clearLogAdapters();//取消日志输出
    }

    public String getFromAssets() {
        String result = "";
        try {
            InputStream in = getResources().getAssets().open("province.json");
            //获取文件的字节数
            int lenght = in.available();
            //创建byte数组
            byte[] buffer = new byte[lenght];
            //将文件中的数据读到byte数组中
            in.read(buffer);
            //            result = EncodingUtils.getString(buffer, "utf-8");
            result = new String(buffer, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
