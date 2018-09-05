package com.pms.code.service; 
import java.util.HashMap;
import java.util.List;

import com.pms.code.entity.base.SysNotice;
 
public interface SysNoticeService {

	/**
	 * 查询公告列表:用户 
	 * @return
	 */
	HashMap<String, Object> querySysNoticeList(HashMap<String,Object> map);
	 
	/**
	 * 保存公告:修改	
	 * @param id
	 * @return
	 */
	boolean saveEditSysNotice(SysNotice sysNotice);
	
	/**
	 * 查询公告通过id
	 * @param id
	 * @return
	 */
	SysNotice querySysNoticeById(int id);
	
	/**
	 * 公告删除	
	 * @param sysNotice
	 * @return
	 */
	boolean deleteSysNotice(int []ids);
	
	/**
	 * 查询公告总数:用户
	 */
	int querySysNoticeTotal(HashMap<String,Object> map);
	
	/**
	 * 添加新公告
	 * @param sysNotice
	 * @return
	 */
	boolean insertSysNotice(SysNotice sysNotice);
	
	/**
	 * 查询公告列表 :公司
	 * @param map
	 * @return
	 */
	HashMap<String,Object> querySysNoticeListCompany(HashMap<String,Object> map);
	
	/**
	 * 查询公告总数:公司
	 * @param map
	 * @return
	 */
	int querySysNoticeTotalCompany(HashMap<String,Object> map);
	
	/**
	 * 查询公告管理通过物业id
	 * @param id
	 * @return
	 */

 /*   HashMap<String,Object> querySysNoticeByPmid(HashMap<String,Object> map);*/
	
	/**
	 * 查询公告总数通过物业id
	 * @param id
	 * @return
	 */
/*	int querySysNoticeTotalPmid(int id);*/
	
	/**
	 * 搜索公告
	 * @param map
	 * @return
	 */
/*	HashMap<String,Object> searchSysNotice(HashMap<String,Object> map);*/
	
	/**
	 * 搜索公告总数
	 * @param title
	 * @return
	 */
/*	int searchSysNoticeTotal(@Param(value="title")String title);*/
}
