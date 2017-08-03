package com.ane.util;

import java.io.UnsupportedEncodingException;

public class DecodeUtils {
	
	public static String encodeStr(String str) {
		if(str==null||"".equals(str)){
			return "";
		}
        try {  
        	//判断是否"ISO-8859-1"编码
        	if(str.equals(new String(str.getBytes("ISO-8859-1"), "ISO-8859-1"))){
        		return new String(str.getBytes("ISO-8859-1"), "UTF-8");
        	}
        	return str;
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
            return null;  
        }
	}
}
