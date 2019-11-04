package com.utvgo.huya.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.chinanetcenter.wsupdate.WsUpdateConstant;
import com.chinanetcenter.wsupdate.WsUpdateSdk;
import com.chinanetcenter.wsupdate.model.update.UpdateListener;
import com.gzgd.fml.aidl.IBoxBasicService;
import com.utvgo.handsome.diff.DiffConfig;
import com.utvgo.handsome.diff.GZBNBox;
import com.utvgo.handsome.diff.Platform;
import com.utvgo.handsome.utils.GuizhouUtils;
import com.utvgo.huya.utils.Tools;

import java.util.List;

public class LaunchActivity extends HomeActivity {
    private boolean isBindBoxService = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       checkIntentData();
        if (platfromUtils.isFuMuLe2()){
            bindBoxService();
        }


    }
    private void doAutoUpdate() {
        WsUpdateSdk.getInstance().doUpdate(this, WsUpdateConstant.UPDATE_MODE_AUTO, new UpdateListener() {
            @Override
            public void onDownLoadSuccess(boolean toInstallIntent) {
                Log.i("zyx", "onDownLoadSuccess toInstallIntent=" + toInstallIntent);
            }

            @Override
            public void onUpdateDialogShow() {
                Log.i("zyx", "onUpdateDialogShow");
            }

            @Override
            public void onFail(int code, String description) {
                Log.i("zyx", "onFail code=" + code + "description=" + description);
            }

            @Override
            public void onNoNewVersion() {
                Log.i("zyx", "onNoNewVersion");
            }

            @Override
            public void onCommonUpdateCancel() {
                Log.i("zyx", "onCommonUpdateCancel");
            }

            @Override
            public void onForceUpdateCancel() {
                Log.i("zyx", "onForceUpdateCancel");
            }
        });
    }

    private void bindBoxService() {
        try {
            Intent intent = new Intent("gzgd.intent.action.service.BoxService");
            final Intent eintent = new Intent(createExplicitFromImplicitIntent(this, intent));
            isBindBoxService = bindService(eintent, mConnection, Context.BIND_AUTO_CREATE);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "bindBoxService: 绑定 贵州地区码 BoxService 失败 ");
        }

    }
    public static Intent createExplicitFromImplicitIntent(Context context, Intent implicitIntent) {
        // Retrieve all services that can matchthe given intent
        PackageManager pm =context.getPackageManager();
        List<ResolveInfo> resolveInfo =pm.queryIntentServices(implicitIntent, 0);
        // Make sure only one match was found
        if (resolveInfo == null ||resolveInfo.size() != 1) {
            return null;
        }
        // Get component info and createComponentName
        ResolveInfo serviceInfo =resolveInfo.get(0);
        String packageName =serviceInfo.serviceInfo.packageName;
        String className =serviceInfo.serviceInfo.name;
        ComponentName component = new ComponentName(packageName, className);
        // Create a new intent. Use the old onefor extras and such reuse
        Intent explicitIntent = new Intent(implicitIntent);
        // Set the component to be explicit
        explicitIntent.setComponent(component);
        return explicitIntent;
    }

    private IBoxBasicService mService = null;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = IBoxBasicService.Stub.asInterface(service);
            getGuiZhouAreaCode();
            Log.d(TAG, "IBoxBasicService Connected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
            Log.d(TAG, "IBoxBasicService Disconnected");
        }
    };
    private void getGuiZhouAreaCode(){
        //获取区域码
        try{
            String arecode = mService.getBoxInfo("AREAID");
            Log.e(TAG, "getGuiZhouAreaCode=====> "+arecode );
            Tools.setStringPreference(this, GuizhouUtils.TagGuizhouAreaCode, arecode);
        }catch(Exception o){
            o.printStackTrace();
        }
    }
    @Override
    protected void onDestroy() {
        if(DiffConfig.CurrentPlatform== Platform.gzbn){
            if (platfromUtils.isFuMuLe2()){
                unbindService(mConnection);
            }
        }
        super.onDestroy();
    }
}
