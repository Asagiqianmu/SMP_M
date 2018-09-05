package com.pms.code.service;

import java.util.List;

import com.pms.code.entity.base.HouseType;

public interface HouseTypeService {
	/**
	 * 查询房子类型
	 * @return
	 */
	List<HouseType> houseTypeList();;
}
