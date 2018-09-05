package com.pms.code.util;


/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Utilities for encoding and decoding the Base64 representation of
 * binary data.  See RFCs <a
 * href="http://www.ietf.org/rfc/rfc2045.txt">2045</a> and <a
 * href="http://www.ietf.org/rfc/rfc3548.txt">3548</a>.
 * 
 * BASE64
 * 基于Android Open Source Project修改
 * 
 * @author gaozhenhai
 * @since 2013.01.15
 * @version 1.0.0_1
 * 
 */
public class BASE64 {// 加密  
    public static String encrypt(String str) {  
        byte[] b = null;  
        String s = null;  
        try {  
            b = str.getBytes("utf-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        if (b != null) {  
            s = new BASE64Encoder().encode(b);  
        }  
        return s;  
    }  
  
    // 解密  
    public static String decode(String s) {  
        byte[] b = null;  
        String result = null;  
        if (s != null) {  
            BASE64Decoder decoder = new BASE64Decoder();  
            try {  
                b = decoder.decodeBuffer(s);  
                result = new String(b, "utf-8");  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return result;  
    }  
    public static void main(String[] args) {
    	Map< String, Object> map= new HashMap<String, Object>();
    	map.put("ds","eqdw");
    	map.put("ds1","eqdw1");
    	map.put("ds2","eqdw2");
    	System.out.println(BASE64.encrypt(JSONSerializer.toJSON(map).toString()));
    	System.out.println(BASE64.decode(BASE64.encrypt(JSONSerializer.toJSON(map).toString())));
		Map<String,Object> json = (Map<String,Object>) JSONSerializer.toJSON(BASE64.decode(BASE64.encrypt(JSONSerializer.toJSON(map).toString())));
		System.out.println((String)json.get("ds"));
	}
    
}