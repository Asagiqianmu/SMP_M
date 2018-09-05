package com.pms.code.util;

import com.google.common.collect.Maps;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.cglib.beans.BeanMap;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.security.MessageDigest;
import java.util.*;

/**
 * 
 * @author dengfei E-mail:dengfei@kdflink.com
 * @time 2017年12月5日 上午1:31:58
 */
public class CommonUtil {

	public static String getRandomOrderId() {
		
		String chars = "0123456789AaBbCcDdEeFfGgHhIiJjkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
		String str = "";
		for (int i = 0; i < 32; i++) {
			char s = chars.charAt((int) (Math.random() * 52));
			str += s;
		}
		return str;
	}

	private static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// 增加CDATA标记
				boolean cdata = true;

				@SuppressWarnings("rawtypes")
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}

				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});

	/**
	 * 将对象装换为map
	 * 
	 * @param bean
	 * @return
	 */
	public static <T> Map<String, Object> beanToMap(T bean) {
		Map<String, Object> map = Maps.newHashMap();
		if (bean != null) {
			BeanMap beanMap = BeanMap.create(bean);
			for (Object key : beanMap.keySet()) {
				if(beanMap.get(key) != null){
					map.put(key + "", beanMap.get(key));
				}
			}
		}
		return map;
	}

	/**
	 * 方法用途: 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序），并且生成XML<br>
	 * @param paraMap 要排序的Map对象
	 * @return
	 */
	public static String formatMapToXML(Map<String, Object> paraMap) {
		StringBuffer buffer = new StringBuffer("<xml>");
		String sign = "";
		try {
			List<Map.Entry<String, Object>> infoIds = new ArrayList<Map.Entry<String, Object>>(paraMap.entrySet());
			// 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
			Collections.sort(infoIds, new Comparator<Map.Entry<String, Object>>() {

				@Override
				public int compare(Map.Entry<String, Object> o1, Map.Entry<String, Object> o2) {
					return (o1.getKey()).toString().compareTo(o2.getKey());
				}
			});
			
			for (int i = 0; i < infoIds.size(); i++) {
				String key = infoIds.get(i).getKey();
				String value = infoIds.get(i).getValue() + "";
				if(key.equals("sign")){
					sign = "<"+key+"><![CDATA[" + value + "]]></"+key+">";
				}else{
					buffer.append("<"+key+"><![CDATA[" + value + "]]></"+key+">");
				}
			}
			buffer.append(sign);
			buffer.append("</xml>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString().trim();
	}


	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(String xml) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		Document document = DocumentHelper.parseText(xml);
		Element root = document.getRootElement();
		List<Element> elementList = root.elements();
		for (Element e : elementList)
			map.put(e.getName(), e.getText());
		return map;
	}

	public static String getClientIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();
	}

	public static String MD5Encode(String origin, String charsetname) throws Exception{
		String resultString = null;
		resultString = new String(origin);
		MessageDigest md = MessageDigest.getInstance("MD5");
		if (charsetname == null || "".equals(charsetname))
			resultString = byteArrayToHexString(md.digest(resultString
					.getBytes()));
		else
			resultString = byteArrayToHexString(md.digest(resultString
					.getBytes(charsetname)));
		return resultString;
	}
	
	private static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			resultSb.append(byteToHexString(b[i]));

		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}
	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

}
