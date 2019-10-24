package com.utvgo.huya.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.utvgo.huya.R;

/**
 * Created by YS on 2016/2/26.
 * 类说明：处理图片的工具类.
 */
public class ImageTool {

    public static void loadImageWithUrl(Context context, String url, ImageView imageView) {
        //不要placeholder
        Glide.with(context).load(url)/*.skipMemoryCache(true).placeholder(R.drawable.default_small)*/.into(imageView);
    }

    public static void loadCircleImageWithUrl(Context context, String url, ImageView imageView) {
        //不要placeholder
        Glide.with(context).load(url)/*.skipMemoryCache(true).transform(new GlideCircleTransform())*//*.placeholder(R.drawable.default_small)*/.into(imageView);
    }

    public static void loadImgByGlideToImageView(Context context, String imgUrl, int placeHolderRes, ImageView imageView) {
        //不要placeholder
        Glide.with(context).load(imgUrl)/*.skipMemoryCache(true)*//*.placeholder(placeHolderRes)*/.into(imageView);
    }

    public static void loadImgByGlide(Context context, String imgUrl, int placeHolderRes) {
        Glide.with(context).load(imgUrl)/*.skipMemoryCache(true)*//*.placeholder(placeHolderRes)*/;
    }

    public static void loadImgByGlide(Context context, String imgUrl) {
        Glide.with(context).load(imgUrl)/*.skipMemoryCache(true)*/;
    }

    public static void loadByGlide(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions().error(R.drawable.place_holder_default).placeholder(R.drawable.place_holder_default);
        Glide.with(context).load(url).apply(options).into(imageView);
    }

    public static void loadByGlide(Context context, int res, ImageView imageView) {
        RequestOptions options = new RequestOptions().error(R.drawable.place_holder_default).placeholder(R.drawable.place_holder_default);
        Glide.with(context).load(res).apply(options).into(imageView);

    }
}