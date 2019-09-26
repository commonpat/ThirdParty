//package com.utvgo.huya.utils;
//
//import android.os.SystemProperties;
//import android.util.VendorUtils;
//
//import com.utvgo.hifi.interfaces.PlatformInterface;
//
//public class PlatfromUtils implements PlatformInterface{
//    @Override
//    public boolean isGuangDong() {
//        return false;
//    }
//
//    @Override
//    public boolean isChongQing() {
//        return false;
//    }
//
//    @Override
//    public boolean isP60() {
//        return false;
//    }
//
//    @Override
//    public boolean isHuBei() {
//        return false;
//    }
//
//    @Override
//    public boolean isHuNan() {
//        return false;
//    }
//
//    @Override
//    public boolean isGuiZhou() {
//        return true;
//    }
//
//    @Override
//    public boolean isGuoAn() {
//        return false;
//    }
//
//    @Override
//    public boolean isGuangDianJingLing() {
//        if (isFML1()){
//            return false;
//        }
//        try{
//            String machineModel = VendorUtils.Xget("GET_MACHINE_MODEL");
//            if (machineModel!=null && (machineModel.startsWith("SE") || machineModel.startsWith("SF"))){
//                return true;
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    @Override
//    public boolean isFuMuLe2() {
//        if (isFML1()){
//            return false;
//        }
//        try{
//            //屏蔽硬件
//            String machineModel = VendorUtils.Xget("GET_MACHINE_MODEL");
//            if (machineModel!=null && machineModel.startsWith("SC")){
//                return true;
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    @Override
//    public boolean isFML1() {
//        boolean isFML = false;
//        try {
//            String value = SystemProperties.get("ro.product.device", "N9202");
//            if(value.equals("N9201"))
//                isFML = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return isFML;
//    }
//
//    @Override
//    public boolean isZJSM() {
//        return false;
//    }
//}
