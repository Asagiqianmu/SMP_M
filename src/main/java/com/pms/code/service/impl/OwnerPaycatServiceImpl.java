package com.pms.code.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pms.code.dao.BaseDao;
import com.pms.code.entity.base.OwnerAccount;
import com.pms.code.entity.base.OwnerPaycat;
import com.pms.code.exception.DAOException;
import com.pms.code.service.OwnerPayCatService;
import com.pms.code.util.MapperConst;
import com.pms.code.util.Page;
import com.thoughtworks.xstream.mapper.Mapper;

@Transactional
@Service("ownerPaycatServiceImpl")
public class OwnerPaycatServiceImpl implements OwnerPayCatService {
	private Logger logger = LoggerFactory.getLogger(SysNoticeServiceImpl.class);

	@Autowired
	private BaseDao<OwnerPaycat, Serializable> ownerPayCatBaseDaoImpl;

	@Autowired
	private BaseDao<Integer, Serializable> countOwnerPayCatBaseDaoImpl;

	/**
	 * 根据Uid删除缴费信息
	 * 
	 * @param ids
	 * @return
	 */
	@Override
	public boolean deleteOwnerPaycatByUIds(int[] ids) {
		int count = 0;
		try {
			count = ownerPayCatBaseDaoImpl.delete(ids, MapperConst.OwnerPayCat_Mapper + ".deleteOwnerPaycatByUIds");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count > 0 ? true : false;
	}

	/**
	 * 查询已经缴费列表:用户登录
	 */
	@Override
	public HashMap<String, Object> queryOwnerPayCatList(HashMap<String, Object> paramMap) {
		List<OwnerPaycat> list = null;
		Integer pageSize = (Integer) paramMap.get("pageSize");
		Integer pageIndex = (Integer) paramMap.get("pageIndex");
		Page<OwnerPaycat> page = new Page<OwnerPaycat>(pageSize, pageIndex);
		// 缴费记录总数
		int payCatTotal = queryOwnerPayCatTotal(paramMap);
		page.setRecord(payCatTotal);
		int startCount = (pageIndex - 1) * pageSize;
		paramMap.put("pageSize", pageSize);
		paramMap.put("startCount", startCount);
		try {
			list = ownerPayCatBaseDaoImpl.selectListPaging(paramMap, MapperConst.OwnerPayCat_Mapper + ".queryOwnerPayCatList");
			page.setHouseList(list);
			paramMap.put("page", page);
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return paramMap;
	}

	/**
	 * 查询未缴费列表:用户登录
	 */
	@Override
	public HashMap<String, Object> queryUnOwnerPayCatList(HashMap<String, Object> paramMap) {
		List<OwnerPaycat> list = null;
		Integer pageSize = (Integer) paramMap.get("pageSize");
		Integer pageIndex = (Integer) paramMap.get("pageIndex");
		Page<OwnerPaycat> page = new Page<OwnerPaycat>(pageSize, pageIndex);
		// 缴费记录总数
		int unOwnerPayCatTptal = queryUnOwnerPayCatTotal(paramMap);
		page.setRecord(unOwnerPayCatTptal);
		int startCount = (pageIndex - 1) * pageSize;
		paramMap.put("startCount", startCount);
		try {
			list = ownerPayCatBaseDaoImpl.selectListPaging(paramMap,
					MapperConst.OwnerPayCat_Mapper + ".queryUnOwnerPayCatList");
			page.setHouseList(list);
			paramMap.put("page", page);
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return paramMap;
	}

	/**
	 * 录入缴费信息
	 */
	@Override
	public boolean OwnerPayCatEnter(OwnerPaycat ownerPaycat) {
		int count = 0;
		try {
			count = ownerPayCatBaseDaoImpl.insert(ownerPaycat, MapperConst.OwnerPayCat_Mapper + ".ownerPayCatEnter");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count > 0 ? true : false;
	}

	/**
	 * 查询月度流水
	 */
	@Override
	public HashMap<String, Object> qureyMonthCost(HashMap<String, Object> paramMap) {
		List<OwnerPaycat> list = null;
		Integer pageSize = (Integer) paramMap.get("pageSize");
		Integer pageIndex = (Integer) paramMap.get("pageIndex");
		Page<OwnerPaycat> page = new Page<OwnerPaycat>(pageSize, pageIndex);
		int monthCostTotal = queryMonthCostTotal();
		int startCount = (pageIndex - 1) * pageSize;
		page.setRecord(monthCostTotal);
		paramMap.put("startCount", startCount);
		try {
			list = ownerPayCatBaseDaoImpl.selectListPaging(paramMap,
					MapperConst.OwnerPayCat_Mapper + ".qureyMonthCost");
			page.setHouseList(list);
			paramMap.put("page", page);
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return paramMap;
	}

	/**
	 * 查询月度流水总数
	 */
	@Override
	public int queryMonthCostTotal() {
		int count = 0;
		try {
			count = countOwnerPayCatBaseDaoImpl.count(0, MapperConst.OwnerPayCat_Mapper + ".queryMonthCostTotal");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count;
	}

	/**
	 * 查询缴费信息:公司
	 */
	@Override
	public HashMap<String, Object> queryOwnerPayCatListCompany(HashMap<String, Object> paramMap) {
		List<OwnerPaycat> listPaging = null;
		Integer pageSize = (Integer) paramMap.get("pageSize");
		Integer pageIndex = (Integer) paramMap.get("pageIndex");
		Page<OwnerPaycat> page = new Page<OwnerPaycat>(pageSize, pageIndex);
		// 查询公告总数
		int total = queryOwnerPayCatTotalCompany(paramMap);
		page.setRecord(total);
		// 计算查询的起始位置
		int startCount = (pageIndex - 1) * pageSize;
		paramMap.put("startCount", startCount);
		try {
			listPaging = ownerPayCatBaseDaoImpl.selectListPaging(paramMap,
					MapperConst.OwnerPayCat_Mapper + ".queryOwnerPayCatListCompany");
			page.setHouseList(listPaging);
			paramMap.put("page", page);
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return paramMap;
	}

	/**
	 * 查询缴费总数:用户登录
	 */
	@Override
	public int queryOwnerPayCatTotal(HashMap<String, Object> paramMap) {
		int count = 0;
		try {
			count = countOwnerPayCatBaseDaoImpl.select(paramMap,
					MapperConst.OwnerPayCat_Mapper + ".queryOwnerPayCatTotal");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count;
	}

	/**
	 * 查询缴费总数:公司登录
	 */
	@Override
	public int queryOwnerPayCatTotalCompany(HashMap<String, Object> paramMap) {
		int count = 0;
		try {
			count = countOwnerPayCatBaseDaoImpl.select(paramMap,
					MapperConst.OwnerPayCat_Mapper + ".queryOwnerPayCatTotalCompany");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count;
	}

	/**
	 * 查询未缴费信息:公司登录
	 */
	@Override
	public HashMap<String, Object> queryUnOwnerPayCatListCompany(HashMap<String, Object> paramMap) {
		List<OwnerPaycat> listPaging = null;
		Integer pageSize = (Integer) paramMap.get("pageSize");
		Integer pageIndex = (Integer) paramMap.get("pageIndex");
		Page<OwnerPaycat> page = new Page<OwnerPaycat>(pageSize, pageIndex);
		// 查询公告总数
		int total = queryUnOwnerPayCatTotalCompany(paramMap);
		page.setRecord(total);
		// 计算查询的起始位置
		int startCount = (pageIndex - 1) * pageSize;
		paramMap.put("startCount", startCount);
		try {
			listPaging = ownerPayCatBaseDaoImpl.selectListPaging(paramMap,
					MapperConst.OwnerPayCat_Mapper + ".queryUnOwnerPayCatListCompany");
			page.setHouseList(listPaging);
			paramMap.put("page", page);
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return paramMap;
	}

	/**
	 * 查询未缴费总数:用户登录
	 */
	@Override
	public int queryUnOwnerPayCatTotal(HashMap<String, Object> map) {
		int count = 0;
		try {
			countOwnerPayCatBaseDaoImpl.select(map, MapperConst.OwnerPayCat_Mapper + ".queryUnOwnerPayCatTotal");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count;
	}

	/**
	 * 查询未缴费总数:公司登录
	 */
	@Override
	public int queryUnOwnerPayCatTotalCompany(HashMap<String, Object> map) {
		int count = 0;
		try {
			countOwnerPayCatBaseDaoImpl.select(map, MapperConst.OwnerPayCat_Mapper + ".queryUnOwnerPayCatTotalCompany");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count;
	}

	/**
	 * 查询缴费信息通过id
	 */
	@Override
	public OwnerPaycat queryPayCatById(int id) {
		 try {
			 return  ownerPayCatBaseDaoImpl.select(id,MapperConst.OwnerPayCat_Mapper+".queryPayCatById");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
			return null;
		} 
	}

	/**
	 * 修改未缴费信息
	 */
	@Override
	public boolean updateUnpayCatInfo(OwnerPaycat ownerPaycat) {
		int count=0;
		try {
			count=ownerPayCatBaseDaoImpl.update(ownerPaycat,MapperConst.OwnerPayCat_Mapper+".updateUnpayCatInfo");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count>0?true:false;
	}

	/**
	 * 删除缴费信息
	 */
	@Override
	public boolean deleteUnPayCatInfo(int id) {
		int count=0;
		try {
			count=ownerPayCatBaseDaoImpl.delete(id,MapperConst.OwnerPayCat_Mapper+".deleteUnPayCatInfo");
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return count>0?true:false;
	}
	
	@Override
	public HashMap<String,Object> queryOwnerPayCat(HashMap<String, Object> paramMap) {
		List<OwnerPaycat> list = null;
		Integer pageSize = (Integer) paramMap.get("pageSize");
		Integer pageIndex = (Integer) paramMap.get("pageIndex");
		Page<OwnerPaycat> page = new Page<OwnerPaycat>(pageSize, pageIndex);
		// 缴费记录总数
		int unOwnerPayCatTptal = queryUnOwnerPayCatTotal(paramMap);
		page.setRecord(unOwnerPayCatTptal);
		int startCount = (pageIndex - 1) * pageSize;
		paramMap.put("startCount", startCount);
		try {
			list = ownerPayCatBaseDaoImpl.selectListPaging(paramMap,
					MapperConst.OwnerPayCat_Mapper + ".queryOwnerPayCat");
			page.setHouseList(list);
			paramMap.put("page", page);
		} catch (DAOException e) {
			logger.error("发生异常:具体信息:{}", e.fillInStackTrace());
		}
		return paramMap;
	}
}
