package com.panku.thirdparty.mzBanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.panku.thirdparty.BaseActivity;
import com.panku.thirdparty.R;
import com.panku.thirdparty.mzBanner.holder.MZHolderCreator;
import com.panku.thirdparty.mzBanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * data 2019/4/26
 * time 11:39
 * Created by huangyanan.
 */
public class BannerActivity extends BaseActivity {
    private int[] imgs = {R.mipmap.ic_home_banner1, R.mipmap.ic_home_banner2, R.mipmap.ic_home_banner3, R.mipmap.ic_home_banner4};
    private MZBannerView bannerView;
    private MZBannerView bannerView1;
    private List<Integer> banners;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_banner;
    }

    @Override
    protected void initView() {
        bannerView = findViewById(R.id.banner);
        bannerView1 = findViewById(R.id.banner1);
    }

    @Override
    public void initData() {
        banners = new ArrayList<>();
        for (int i = 0; i < imgs.length; i++) {
            banners.add(imgs[i]);
        }
        bannerView.setIndicatorRes(R.drawable.bg_home_dot_default, R.drawable.bg_home_dot);
        bannerView.setIndicatorAlign(MZBannerView.IndicatorAlign.CENTER);
        bannerView.setIndicatorPadding(10, 0, 10, 5);
        bannerView.setIndicatorVisible(true);
        bannerView.setPages(banners, new MZHolderCreator<TopBannerViewHolder>() {
            @Override
            public TopBannerViewHolder createViewHolder() {
                return new TopBannerViewHolder();
            }
        });

        bannerView1.setIndicatorRes(R.drawable.bg_home_dot_default, R.drawable.bg_home_dot);
        bannerView1.setIndicatorAlign(MZBannerView.IndicatorAlign.CENTER);
        bannerView1.setIndicatorPadding(10, 2, 10, 0);
        bannerView1.setIndicatorVisible(true);
        bannerView1.setPages(banners, new MZHolderCreator<TopBannerViewHolder>() {
            @Override
            public TopBannerViewHolder createViewHolder() {
                return new TopBannerViewHolder();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        bannerView.pause();//暂停轮播
        bannerView1.pause();//暂停轮播
    }

    @Override
    public void onResume() {
        super.onResume();
        bannerView.start();//开始轮播
        bannerView1.start();//开始轮播
    }

    public static class TopBannerViewHolder implements MZViewHolder<Integer> {
        private ImageView mImageView;

        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.item_home_banner, null);
            mImageView = view.findViewById(R.id.image);
            return view;
        }

        @Override
        public void onBind(Context context, int position, Integer data) {
            // 数据绑定
//            mImageView.setImageResource(data);
            Glide.with(context).load(data).into(mImageView);
        }
    }
}
