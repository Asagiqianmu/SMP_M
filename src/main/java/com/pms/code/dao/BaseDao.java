package com.pms.code.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;




import com.github.pagehelper.Page;
import com.pms.code.exception.DAOException;



/**
 * 类名: BaseDao
 */
public interface BaseDao<T, PK extends Serializable> {

	/**
	 * 接口名称：insert 接口描述：将对象插入到数据库
	 * 
	 * @param entity
	 * @param mapperName
	 * @return 返回插入记录的主�?
	 * @throws DAOException
	 */
	public abstract int insert(T entity, String mapperName) throws DAOException;

	/**
	 * 接口名称：update 接口描述：根据对象更新数据库记录
	 * 
	 * @param entity
	 * @param mapperName
	 * @return 返回被更新记录的条数
	 * @throws DAOException
	 */
	public abstract int update(T entity, String mapperName) throws DAOException;

	/**
	 * 接口名称：delete 接口描述：根据主键删除数据库记录
	 * 
	 * @param primaryKey
	 * @param mapperName
	 * @return 返回删除记录的条�?
	 * @throws DAOException
	 */
	public abstract int delete(PK primaryKey, String mapperName)
			throws DAOException;

	/**
	 * 接口名称：deleteClass 接口描述�?根据条件删除对象
	 * 
	 * @param entity
	 * @param mapperName
	 * @return 返回删除记录的条�?
	 * @throws DAOException
	 */
	public abstract int deleteClass(T entity, String mapperName)
			throws DAOException;

	/**
	 * 接口名称：count 接口描述：根据条件统计个�?
	 * 
	 * @param entity
	 * @param mapperName
	 * @return
	 * @throws DAOException
	 */
	public abstract int count(T entity, String mapperName) throws DAOException;

	/**
	 * 接口名称：select 接口描述：根据主键查询对�?
	 * 
	 * @param primaryKey
	 * @param mapperName
	 * @return 返回查询的对�?
	 * @throws DAOException
	 */
	public abstract T select(PK primaryKey, String mapperName)
			throws DAOException;
	
	/**
	 * 接口名称：select 接口描述：根据条件查询对�?
	 * 
	 * @param entity
	 * @param mapprName
	 * @return 返回查询的对�?
	 * @throws DAOException
	 */
	public abstract T select(T entity, String mapperName) throws DAOException;

	/**
	 * 接口名称：batchInsert 接口描述：批量插入对象到数据�?
	 * 
	 * @param list
	 * @param mapperName
	 * @return 返回true or false
	 * @throws DAOException
	 */
	public abstract boolean batchInsert(List<T> list, String mapperName)
			throws DAOException;

	/**
	 * 接口名称：batchUpdate 接口描述：批量更新数据库记录
	 * 
	 * @param list
	 * @param mapperName
	 * @return 返回true or false
	 * @throws DAOException
	 */
	public abstract boolean batchUpdate(List<T> list, String mapperName)
			throws DAOException;

	/**
	 * 接口名称：batchDelete 接口描述：批量删除数据库记录
	 * 
	 * @param adList
	 * @param mapperName
	 * @return 返回true or false
	 * @throws DAOException
	 */
	public abstract boolean batchDelete(List<PK> adList, String mapperName)
			throws DAOException;

	/**
	 * 接口名称：paramSQL 接口描述：自定义查询条件查询
	 * 
	 * @param sqlId
	 * @param entity
	 * @return 返回自定义实体的列表
	 * @throws DAOException
	 */
	public abstract List<T> paramSQL(String sqlId, T entity)
			throws DAOException;

	/**
	 * 接口名称：selectListSeek 接口描述：根据当前页，也容量以及查询条件进行分页查询
	 * 
	 * @param entity
	 * @param currPage
	 * @param pageSize
	 * @param mapperName
	 * @return 返回分页查询的实体列�?
	 * @throws DAOException
	 */
	public List<T> selectListSeek(T entity, Integer currPage, Integer pageSize,
			String mapperName) throws DAOException;

	/**
	 * 接口名称：selectByIds 接口描述：根据多个ID查询对象集合
	 * 
	 * @param sqlId
	 * @param list
	 * @return 返回对象的列�?
	 * @throws DAOException
	 */
	public abstract List<T> selectByIds(String sqlId, List<Integer> list)
			throws DAOException;

	/**
	 * 接口名称：selectListPaging 接口描述：分页查询的方法
	 * 
	 * @param map
	 * @param mapperName
	 * @return 返回当前页的对象列表
	 * @throws DAOException
	 */
	public abstract List<T> selectListPaging(Map<String, Object> map,
			String mapperName) throws DAOException;
	
	/**
	 * 接口名称：selectListPaging 接口描述：使用Mybatis插件的分页查�?
	 * 
	 * @param page
	 * @param mapperName
	 * @return 返回当前页的对象列表
	 * @throws DAOException
	 */
	public abstract Page<T> selectListPagings(Page<T> page, String mapperName)
			throws DAOException;
	/**
	 * 根据条件批量删除数据�?
	 * @param paramMap
	 * @param mapperName
	 * @return
	 * @throws DAOException
	 */
	public abstract boolean batchDelete(Map<String,Object> paramMap, String mapperName)
			throws DAOException;
	
	/**
	 * 根据条件查询对象
	 * @author songyb
	 * @2017年4月14日
	 */
	public abstract T selectOne(String mapperName, Object paramer)
			throws DAOException;
	/**
	 * 根据条件查询list集合对象
	 * @author songyb
	 * @2017年4月14日
	 */
	public abstract List<T> selectListPoJo(Object paramer,String mapperName)
			throws DAOException;
	/**
	 * 接口名称：batchDelete 接口描述：批量删除数据库记录
	 * 
	 * @param adList
	 * @param mapperName
	 * @return 返回true or false
	 * @throws DAOException
	 */
	  public abstract boolean batchDeleteList(List<String> adList, String mapperName)throws DAOException;
}
