package com.utvgo.huya.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.utvgo.handsome.utils.XLog;
import com.utvgo.huya.R;
import com.utvgo.huya.beans.BeanUpgrade;
import com.utvgo.huya.listeners.MyDialogEnterListener;
import com.utvgo.huya.views.FilterView;
import java.util.ArrayList;

/**
 * 自定义Dialog 工具类
 */
public class HiFiDialogTools {

    private MyDialogEnterListener listener;

    private static HiFiDialogTools hiFiDialogTools = null;

    public static HiFiDialogTools getInstance() {
        if (hiFiDialogTools == null) {
            hiFiDialogTools = new HiFiDialogTools();
        }
        return hiFiDialogTools;
    }

    /**
     * @param context  弹出订购提示,二次确认
     * @param listener
     */
    public Dialog showLeftRightTip(Context context, String title, String message, String leftBtn, String rightBtn, final MyDialogEnterListener listener) {

        final Dialog singeLeftRightTipDialog = new Dialog(context, R.style.MyDialog);
        singeLeftRightTipDialog.setContentView(R.layout.new_dialog_show_placeorder_second_confirm);

//        TextView txtTltie = (TextView) singeLeftRightTipDialog.findViewById(R.id.txtTltie);
//        txtTltie.setText(title);
        TextView txtAbstruct = (TextView) singeLeftRightTipDialog.findViewById(R.id.tv_toast_clear);
        txtAbstruct.setText(message);

        TextView left = singeLeftRightTipDialog.findViewById(R.id.sure);
        left.setText(leftBtn);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (singeLeftRightTipDialog != null) {
                    singeLeftRightTipDialog.dismiss();
                }
                if (listener != null) {
                    listener.onClickEnter(singeLeftRightTipDialog, 0);
                }
            }
        });

        TextView right = singeLeftRightTipDialog.findViewById(R.id.cannel);
        right.setText(rightBtn);
        right.requestFocus();
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (singeLeftRightTipDialog != null) {
                    singeLeftRightTipDialog.dismiss();
                }
                if (listener != null) {
                    listener.onClickEnter(singeLeftRightTipDialog, 1);
                }
            }
        });


        if (TextUtils.isEmpty(leftBtn)) {
            left.setVisibility(View.GONE);
        }

        if (TextUtils.isEmpty(rightBtn)) {
            right.setVisibility(View.GONE);
        }

        LinearLayout warp = (LinearLayout) singeLeftRightTipDialog.findViewById(R.id.parentWarp);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(Tools.getDisplayWidth((Activity) context), context.getResources().getDimensionPixelOffset(R.dimen.dp450));
        warp.setLayoutParams(lp);
        singeLeftRightTipDialog.setCanceledOnTouchOutside(false);
        singeLeftRightTipDialog.setCancelable(false);

        left.requestFocus();

        safeShowDialog(context, singeLeftRightTipDialog);
        return singeLeftRightTipDialog;
    }

    /**
     * @param stringRes 弹出简单提示语
     * @param listener
     * @return
     */
    public void showtips(Context context, int stringRes, final MyDialogEnterListener listener) {
        String tip = context.getResources().getString(stringRes);
        showtips(context, tip, listener);
    }

    /**
     * 弹出刷选页面
     *
     * @return
     */
    public Dialog showFilterView(Context context, ArrayList<String[]> strList) {
        FilterView filterView = new FilterView((Activity) context);
        final Dialog tipsDialog = new Dialog(context, R.style.MyDialog);
        tipsDialog.setContentView(filterView);

        filterView.addFilterItemView(strList);

        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(Tools.getDisplayWidth((Activity) context), Tools.getDisplayHeight((Activity) context));
        filterView.setLayoutParams(lp);
        tipsDialog.setCanceledOnTouchOutside(true);
        tipsDialog.setCancelable(true);

        safeShowDialog(context, tipsDialog);

        return tipsDialog;
    }

    /**
     * @param strTip   弹出简单提示语
     * @param listener
     * @return
     */
    public void showtips(Context context, String strTip, final MyDialogEnterListener listener) {

        final Dialog tipsDialog = new Dialog(context, R.style.MyDialog);
        tipsDialog.setContentView(R.layout.new_dialog_show_singer_tip);
        tipsDialog.setCanceledOnTouchOutside(true);
        tipsDialog.setCancelable(true);
        ((TextView) tipsDialog.findViewById(R.id.tv_toast_clear)).setText(strTip);
        TextView sure = tipsDialog.findViewById(R.id.sure);
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tipsDialog != null)
                    tipsDialog.dismiss();
                if (listener != null)
                    listener.onClickEnter(tipsDialog, null);
            }
        });
        safeShowDialog(context, tipsDialog);
    }

    /**
     * @param dialog 安全方式showDialog
     */
    private void safeShowDialog(Context context, Dialog dialog) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (context != null && !((Activity) context).isFinishing() && dialog != null && !dialog.isShowing() && !(((Activity) context).isDestroyed()))
                    dialog.show();
            } else {
                if (context != null && !((Activity) context).isFinishing() && dialog != null && !dialog.isShowing())
                    dialog.show();
            }
        } catch (Exception e) {
            XLog.d("e:" + e);
            e.printStackTrace();
        }
    }
    public void showUpgradeTips(Context context, BeanUpgrade beanUpgrade, final MyDialogEnterListener myDialogEnterListener){
        final Dialog dialog = new Dialog(context,R.style.MyDialog);
        dialog.setContentView(R.layout.activity_upgrade_tips);
        dialog.findViewById(R.id.current_version).setVisibility(View.VISIBLE);
        Button button1 = dialog.findViewById(R.id.btn_upgrade);
        Button button2 = dialog.findViewById(R.id.btn_upgrade_cancel);
        TextView textView = dialog.findViewById(R.id.upgrade_tips);
        if(beanUpgrade.getData().getUpgradeTips()!= null){
            textView.setText(beanUpgrade.getData().getUpgradeTips());
        }
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialogEnterListener.onClickEnter(dialog,0);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialogEnterListener.onClickEnter(dialog,1);
            }
        });
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(false);
        safeShowDialog(context,dialog);
    }
    public void showMinUpgradeTips(Context context, BeanUpgrade beanUpgrade, final MyDialogEnterListener myDialogEnterListener){
        final Dialog dialog = new Dialog(context,R.style.MyDialog);
        dialog.setContentView(R.layout.activity_upgrade_tips);
        dialog.findViewById(R.id.min_version).setVisibility(View.VISIBLE);

        Button button1 = dialog.findViewById(R.id.btn_min_upgrade);
        TextView textView = dialog.findViewById(R.id.upgrade_tips);
        if(beanUpgrade.getData().getMinUpgradeTips()!= null){
            textView.setText(beanUpgrade.getData().getMinUpgradeTips());
        }
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialogEnterListener.onClickEnter(dialog,0);
            }
        });
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(false);
        safeShowDialog(context,dialog);
    }
}
