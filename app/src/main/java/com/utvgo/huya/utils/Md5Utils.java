package com.utvgo.huya.utils;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.WeakHashMap;

import butterknife.internal.Utils;

/**
 * Created by hy on 2014/5/23.
 */
public class Md5Utils {
	private static final String TAG = Utils.class.getSimpleName();

	public static String calMD5(String imageKey) {
		String localCacheKey = calMD5(imageKey.getBytes());
		if (localCacheKey == null) {
			return imageKey;
		}
		return localCacheKey;
	}

	private static char hexCharMap[] = new char[]{
		'0', '1', '2', '3',
		'4', '5', '6', '7',
		'8', '9', 'A', 'B',
		'C', 'D', 'E', 'F',
	};

	private static class MessageDigestCtx {
		MessageDigest digest;
		char[] digestStr = new char[32];

		public MessageDigestCtx(MessageDigest digest) {
			this.digest = digest;
		}

		public void reset() {
			digest.reset();
		}

		public char[] digest(byte[] data) {
			byte[] digestVal = digest.digest(data);
			for (int i = 0; i < 16; ++i) {
				int b = digestVal[i] & 0xFF;
				digestStr[i * 2 + 0] = hexCharMap[b / 16];
				digestStr[i * 2 + 1] = hexCharMap[b % 16];
			}
			return digestStr;
		}
	}

	private static final WeakHashMap<Thread, MessageDigestCtx> _threadHashMap = new WeakHashMap<Thread, MessageDigestCtx>();

	private static MessageDigestCtx getMD5() {
		synchronized (_threadHashMap) {
			Thread thread = Thread.currentThread();
			MessageDigestCtx messageDigest = _threadHashMap.get(thread);
			if (messageDigest == null) {
				try {
					MessageDigest md5 = MessageDigest.getInstance("md5");
					MessageDigestCtx digestCtx = new MessageDigestCtx(md5);
					_threadHashMap.put(thread, digestCtx);
					return digestCtx;
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
					return null;
				}
			}
			messageDigest.reset();
			return messageDigest;
		}
	}

	public static String calMD5(byte[] data) {
		MessageDigestCtx md5 = getMD5();
		return String.valueOf(md5.digest(data));
	}

	/**
	 * 读取文件，返回内容
	 *
	 * @param fileName 文件名
	 * @return 文件内容String格式
	 */
	public static String readFile(String fileName) {
		InputStreamReader inputStreamReader;
		BufferedReader bufferedReader;
		StringBuffer stringBuffer;
		try {
			String filePath =getSDPath() + "/" + fileName;
			File file = new File(filePath);
			if (!file.exists()){
				Log.e(TAG,"The file don't exists. file name:\'"+ filePath +"\'");
				return null;
			}
			inputStreamReader = new InputStreamReader(new FileInputStream(file)); // 建立一个输入流对象reader
			bufferedReader = new BufferedReader(inputStreamReader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
			stringBuffer = new StringBuffer();
			String line = bufferedReader.readLine();
			while (line != null) {
				stringBuffer.append(line);
				line = bufferedReader.readLine(); // 一次读入一行数据
			}
			String result = stringBuffer.toString();
			bufferedReader.close();
			inputStreamReader.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取SD卡路径
	 *
	 * @return SD卡存在则返回SD卡路径，不存在则返回空
	 */
	public static String getSDPath(){
		File sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState()
				.equals(Environment.MEDIA_MOUNTED);//判断sd卡是否存在
		if(sdCardExist) {
			sdDir = Environment.getExternalStorageDirectory();//获取跟目录
		}
		return sdDir == null ? null : sdDir.toString();
	}

	/**
	 * JSON字符串格式化
	 *
	 * @param strJson json字符串
	 * @return 格式化后的json字符串
	 */
	public static String stringToJSON(String strJson) {
		if (TextUtils.isEmpty(strJson)){
			return null;
		}
		// 计数tab的个数
		int tabNum = 0;
		StringBuilder jsonFormat = new StringBuilder();
		int length = strJson.length();
		char last = 0;
		for (int i = 0; i < length; i++) {
			char c = strJson.charAt(i);
			if (c == '{') {
				tabNum++;
				jsonFormat.append(c + "\n");
				jsonFormat.append(getSpaceOrTab(tabNum));
			}
			else if (c == '}') {
				tabNum--;
				jsonFormat.append("\n");
				jsonFormat.append(getSpaceOrTab(tabNum));
				jsonFormat.append(c);
			}
			else if (c == ',') {
				jsonFormat.append(c + "\n");
				jsonFormat.append(getSpaceOrTab(tabNum));
			}
			else if (c == ':') {
				jsonFormat.append(c + " ");
			}
			else if (c == '[') {
				tabNum++;
				char next = strJson.charAt(i + 1);
				if (next == ']') {
					jsonFormat.append(c);
				}
				else {
					jsonFormat.append(c + "\n");
					jsonFormat.append(getSpaceOrTab(tabNum));
				}
			}
			else if (c == ']') {
				tabNum--;
				if (last == '[') {
					jsonFormat.append(c);
				}
				else {
					jsonFormat.append("\n" + getSpaceOrTab(tabNum) + c);
				}
			}
			else {
				jsonFormat.append(c);
			}
			last = c;
		}
		return jsonFormat.toString();
	}

	private static String getSpaceOrTab(int tabNum) {
		StringBuilder sbTab = new StringBuilder();
		for (int i = 0; i < tabNum; i++) {
			sbTab.append('\t');
		}
		return sbTab.toString();
	}

}
