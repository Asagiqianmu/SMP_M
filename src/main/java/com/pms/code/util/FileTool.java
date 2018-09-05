package com.pms.code.util;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileTool {
	private static Logger logger = LoggerFactory.getLogger(FileTool.class);

	public static synchronized boolean createDir(String destDirName) {
		logger.error("创建目录:" + destDirName);
		File dir = new File(destDirName);
		if (dir.exists()) {
			logger.error("创建目录" + destDirName + "失败，目标目录已经存在");
			return true;
		}
		if (!destDirName.endsWith(File.separator)) {
			destDirName = destDirName + File.separator;
		}
		// 创建目录
		if (dir.mkdirs()) {
			logger.error("创建目录" + destDirName + "成功！");
			return true;
		} else {
			logger.error("创建目录" + destDirName + "失败！");
			return createDir(destDirName);
		}
	}
}
