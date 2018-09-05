package com.pms.code.util;
/**
 * @author songyb E-mail:songyanbiao@kdflink.com
 * @version 创建时间：2017年4月14日 下午6:35:55
 * @describe
 */
public class PageUtil {
	
	/**
	 * 计算第几页
	 * @author songyb
	 * @2017年4月14日
	 */
	public final static int getPageNum(int pageNum,int pageSize){
		pageNum=pageNum<1?1:pageNum;
		return (pageNum-1)<0?1*pageSize:(pageNum-1)*pageSize;
	}
	/**
	 * 获取总页数
	 * @author songyb
	 * @2017年4月14日
	 */
	public final static int getTotalPage(int totalNum,int pageSize){
		return (totalNum%pageSize)>0?(totalNum/pageSize+1):totalNum/pageSize;
	}
}
