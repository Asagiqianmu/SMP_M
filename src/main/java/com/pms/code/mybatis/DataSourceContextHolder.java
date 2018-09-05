package com.pms.code.mybatis;
/** * @author  作者 E-mail: * @date 创建时间：2017年4月11日 上午10:27:24 * @version 1.0 * @parameter  * @since  * @return  */
public class DataSourceContextHolder {

	public static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	/**
	 * 设置当前数据源
	 * @param dbType
	 */
	public static void setDbType(String dbType){
		contextHolder.set(dbType);
	}
	/**
	 * 获得当前数据源
	 * @return
	 */
	public static String getDbType(){
		String dbType = (String)contextHolder.get();
		return dbType;
	}
	/**
	 *清除上下文
	 *
	 */
	public void clearContext(){
		contextHolder.remove();
	}
}
