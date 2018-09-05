package com.pms.code.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;

/**
 * @author zyl
 * @date 2016年7月26日
 * 配置文件工具类
 */
public class PropertiesUtil {

	private Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
	private String resource;

	private Properties properties = new Properties();

	private Map<String, String> readMap = Maps.newHashMap();

	public PropertiesUtil(String resource) {
		this.resource = resource;
		loadProperties();
		toMap();
	}

	private void loadProperties() {
		InputStream inputStream = PropertiesUtil.class.getClassLoader()
				.getResourceAsStream(resource);
		if (null == inputStream) {
			throw new NullPointerException("inputStream is not null");
		}
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		} finally {
			close(inputStream);
		}

	}

	private void close(InputStream inputStream) {
		try {
			if (null != inputStream) {
				inputStream.close();
			}
		} catch (IOException ex) {

		}
	}

	public Properties getProperties() {
		return properties;
	}

	public String getString(String key) {
		return properties.getProperty(key);
	}
	public int getInt(String key) {
		String string = properties.getProperty(key);
		int parseInt = 0;
		try {
			parseInt = Integer.parseInt(string.trim());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return parseInt;
	}

	public Map<String, String> getReadMap() {
		return readMap;
	}

	private void toMap() {
		Set<Entry<Object, Object>> set = properties.entrySet();
		Iterator<Entry<Object, Object>> iterator = set.iterator();
		while (iterator.hasNext()) {
			Entry<Object, Object> entry = iterator.next();
			if (entry.getKey() != null) {
				String value = entry.getValue() == null ? "" : (String) entry
						.getValue();
				readMap.put((String) entry.getKey(), value);
			}
		}
	}
}
