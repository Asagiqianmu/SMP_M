package com.pms.code.service;

import java.util.HashMap;
import java.util.List;

import com.pms.code.entity.base.CostType;

public interface CostTypeService {
	
	/**
	 * 查询缴费类型
	 * @return
	 */
	List<CostType> costTypeList();
}
