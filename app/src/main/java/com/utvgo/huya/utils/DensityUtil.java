package com.utvgo.huya.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.TypedValue;
import android.view.WindowManager;

import static android.content.Context.WINDOW_SERVICE;

/**
 * Created by Caodongyao on 2017/8/4.
 */

public class DensityUtil {


    /**
     * 重新计算displayMetrics.
     *      *xhdpi, 使单位pt重定义为设计稿的相对长度
     * @param context
     * @param designWidth 设计稿的宽度
     */
    public static void init(Context context, float designWidth){
        if(context == null)
            return;

        Point size = new Point();
        ((WindowManager)context.getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getSize(size);

        Resources resources = context.getResources();

        resources.getDisplayMetrics().xdpi = size.x/designWidth*72f;

    }


    /**
     * 转换dp为px
     * @param context
     * @param value 需要转换的dp值
     * @return
     */
    public static float dp2px(Context context, float value){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics());
    }

    /**
     * 转换pt为px
     * @param context
     * @param value 需要转换的pt值，若context.resources.displayMetrics经过resetDensity()的修改则得到修正的相对长度，否则得到原生的磅
     * @return
     */
    public static float pt2px(Context context, float value){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, value, context.getResources().getDisplayMetrics());
    }




//   public DensityUtil(Activity activity, int designWidth){
//       initVod(activity, designWidth);
//   }






}