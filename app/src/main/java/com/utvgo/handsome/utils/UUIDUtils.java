package com.utvgo.handsome.utils;

import java.util.UUID;

/**
 * 生成32位编码  工具类
 * @Description: 
 * @author: zxf
 * @time: 2017年6月9日 下午4:26:20
 */
public class UUIDUtils {
	public static String getUUID(){    
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");    
        return uuid;    
    }  
	
	/**
	 * 获取指定长度随机数
	 * @param num
	 * @return
	 */
	public static String getUUIDLength(Integer num){    
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");   
        return uuid.substring(0, num);    
    }
	
	public static void main(String[] args) {
		System.out.println(getUUIDLength(16));
	}
}
