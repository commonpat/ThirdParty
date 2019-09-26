package com.utvgo.huya.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.utvgo.huya.R;
public class ToastUtil {
    public static void showInCenter(Context context, String text) {
        //自定义Toast控件
        View toastView = LayoutInflater.from(context).inflate(R.layout.toast_layout, null);
        LinearLayout relativeLayout = (LinearLayout) toastView.findViewById(R.id.toast_linear);
        //动态设置toast控件的宽高度，宽高分别是130dp
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(800, 300);
        relativeLayout.setLayoutParams(layoutParams);
        TextView textView = toastView.findViewById(R.id.tv_toast_clear);
        textView.setText(text);
        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setView(toastView);
        toast.show();
    }

    public static void showLong(final Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    public static void show(final Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
