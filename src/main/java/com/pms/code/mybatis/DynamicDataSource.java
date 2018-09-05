package com.pms.code.mybatis;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/** * @author  作者 E-mail: * @date 创建时间：2017年4月11日 上午10:26:40 * @version 1.0 * @parameter  * @since  * @return  */
public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		 return DataSourceContextHolder.getDbType(); 
	}

}
