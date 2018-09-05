package com.pms.code.util;

import java.security.MessageDigest;  
import java.text.SimpleDateFormat;
import java.util.Date;

public class MD5 {  
	
    public final static String encode(String s) {  
        char hexDigits[] = { '0', '1', '2', '3', '4',  
                             '5', '6', '7', '8', '9',  
                             'A', 'B', 'C', 'D', 'E', 'F' };  
        try {  
            byte[] btInput = s.getBytes();  
     //获得MD5摘要算法的 MessageDigest 对象  
            MessageDigest mdInst = MessageDigest.getInstance("MD5");  
     //使用指定的字节更新摘要  
            mdInst.update(btInput);  
     //获得密文  
            byte[] md = mdInst.digest();  
     //把密文转换成十六进制的字符串形式  
            int j = md.length;  
            char str[] = new char[j * 2];  
            int k = 0;  
            for (int i = 0; i < j; i++) {  
                byte byte0 = md[i];  
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];  
                str[k++] = hexDigits[byte0 & 0xf];  
            }  
            return new String(str);  
        }  
        catch (Exception e) {
            e.printStackTrace();  
            return null;  
        }  
    }  
    
    /**
	 * 获得今日的key
	 * @return
	 */
	public static String getToDayKey(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dateKey = sdf.format(new Date()).toString();
		return MD5.encode(dateKey + "14v3!solarsys").toLowerCase();
	}
    
    public static void main(String[] args) {  
    	String str = "123";
    	str = MD5.encode(str);
//    	str = MD5.encode(str).toLowerCase();
    	
       // System.out.print(str);  
    }  
    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7",
        "8", "9", "a", "b", "c", "d", "e", "f"};

/**
 * 转换字节数组为16进制字串
 * @param b 字节数组
 * @return 16进制字串
 */
public static String byteArrayToHexString(byte[] b) {
    StringBuilder resultSb = new StringBuilder();
    for (byte aB : b) {
        resultSb.append(byteToHexString(aB));
    }
    return resultSb.toString();
}

/**
 * 转换byte到16进制
 * @param b 要转换的byte
 * @return 16进制格式
 */
private static String byteToHexString(byte b) {
    int n = b;
    if (n < 0) {
        n = 256 + n;
    }
    int d1 = n / 16;
    int d2 = n % 16;
    return hexDigits[d1] + hexDigits[d2];
}

/**
 * MD5编码
 * @param origin 原始字符串
 * @return 经过MD5加密之后的结果
 */
public static String MD5Encode(String origin) {
    String resultString = null;
    try {
        resultString = origin;
        MessageDigest md = MessageDigest.getInstance("MD5");
       // md.update(resultString.getBytes("UTF-8"));
        resultString = byteArrayToHexString(md.digest(resultString.getBytes("utf-8")));
        //resultString = byteArrayToHexString(md.digest());
    } catch (Exception e) {
        e.printStackTrace();
    }
    return resultString;
}
}  
