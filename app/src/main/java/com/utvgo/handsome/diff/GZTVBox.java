package com.utvgo.handsome.diff;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.utvgo.handsome.utils.XLog;


public class GZTVBox implements ITVBox {

    String ca = "";

    public static String getToken(final Context context)
    {
        String tokenString = "";
        for(int i = 0; i <= 1; i++)
        {
            try{
                Uri uri = Uri.parse("content://com.skyworthdigital.authenticationprovider/login");
                Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);

                if (cursor.moveToFirst()) {
                    int tokenIndex = cursor.getColumnIndex("token");
                    tokenString = cursor.getString(tokenIndex);
                    if(!TextUtils.isEmpty(tokenString))
                    {
                        break;
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
                updateToken(context);
            }
        }

        XLog.d("GetToken:" + tokenString);
        return tokenString;
    }

    public static void updateToken(final Context context)
    {
        try{
            Uri uri = Uri.parse("content://com.skyworthdigital.authenticationprovider/login");
            ContentValues cv = new ContentValues();
            context.getContentResolver().update(uri, cv, null, null);
        }catch (Exception e){
            e.printStackTrace();
            XLog.e("Device", "updateToken error");
        }
    }

    /*
    *** D/QQMusic: deviceID 70927002-6c2c-dc10-240c-718300089493
        D/QQMusic: state 0
        D/QQMusic: token b60ff98fbbc0286a04b85005822f9cc2
        D/QQMusic: userID 0000000024082927
        D/QQMusic: tokenTimeOut 2019-08-23 22:14:31
        D/QQMusic: regionCode Y01P0002
     */
    public static String  getAccount(final Context context)
    {
        String account = "";
        Uri uri = Uri.parse("content://com.skyworthdigital.authenticationprovider/login");
        Cursor  cursor = context.getContentResolver().query(uri, null, null, null, null);

        try{
            if(cursor.moveToFirst())
            {
                for(int i = 0; i < cursor.getColumnCount(); i++)
                {
                    XLog.d("GetDeviceInfo:" + cursor.getColumnName(i) + " " + cursor.getString(i));
                }
                int column = cursor.getColumnIndex("userID");
                if(column >= 0)
                {
                    account = cursor.getString(column);
                }
                else
                {
                    column = cursor.getColumnIndex("account");
                    if(column >= 0)
                    {
                        account = cursor.getString(column);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            XLog.e("Device", "getAccount error");
        }
        return account;
    }
    public static String getDeviceId(final Context context){
        String deviceId = "";
        Uri uri = Uri.parse("content://com.skyworthdigital.authenticationprovider/login");
        Cursor  cursor = context.getContentResolver().query(uri, null, null, null, null);
        try {
            if(cursor.moveToFirst()){
                for (int i = 0;i<cursor.getColumnCount();i++){
                    int  index= cursor.getColumnIndex("deviceID");
                    deviceId = cursor.getString(index);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if(deviceId != null){
            return deviceId;
        }

        return "1";
    }
    //
    @Override
    public String getCA(final Context context) {
        if(TextUtils.isEmpty(this.ca))
        {
            this.ca = getAccount(context);
        }
        return this.ca;
    }

    @Override
    public String getRegionCode(Context context) {
        return "GZTV";
    }

    @Override
    public void initDeviceInfo(Context context) {

    }

    @Override
    public void fetchUrlByVODAssetId(final Context context, String vodId, FetchUrlByVODAssetIdCallBack callback) {

    }
}
