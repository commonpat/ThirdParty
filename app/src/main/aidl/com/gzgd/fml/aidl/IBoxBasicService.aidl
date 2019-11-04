package com.gzgd.fml.aidl;

interface IBoxBasicService{
    //private static final String AIDL_PARA_NAME_AREAID = "AREAID";
    //private static final String AIDL_PARA_NAME_TOKEN = "TONKEN";
    //private static final String AIDL_PARA_NAME_USERID = "USERID";
    //private static final String AIDL_CMD_NAME_START_AUTH = "START_AUTH";
    //private static final String AIDL_CMD_NAME_STOP_AUTH = "STOP_AUTH";
    //private static final String AIDL_PARA_NAME_AUTH_STATUS = "STATUS_AUTH";
	String getBoxInfo(String key);
	int setBoxCmd(String cmd,String args);
}