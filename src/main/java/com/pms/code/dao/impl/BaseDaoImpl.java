package com.pms.code.dao.impl;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pms.code.dao.BaseDao;
import com.pms.code.exception.DAOException;



/**
 * 
 * 类名: BaseDaoImpl 类描: BaseDao的实现类,定义数据库
 */
@Repository("baseDaoImpl")
public class BaseDaoImpl<T, PK extends Serializable> implements BaseDao<T, PK> {
	
	private Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);

	/**
	 * 数据源写
	 */
	@Resource(name = "WritesqlSession")
	private SqlSession sqlSessionWrite;
	/**
	 * 数据源读
	 */
	@Resource(name = "ReadsqlSession")
	private SqlSession sqlSessionRead;

	@Override
	public int insert(T entity, String mapperName) throws DAOException {
		try {
			return sqlSessionWrite.insert(mapperName, entity);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new DAOException("添加底层失败", e);
		}
	}

	@Override
	public int update(T entity, String mapperName) throws DAOException {
		try {
			return sqlSessionWrite.update(mapperName, entity);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new DAOException("修改底层失败", e);
		}
	}

	@Override
	public int delete(PK primaryKey, String mapperName) throws DAOException {
		try {
			return sqlSessionWrite.delete(mapperName, primaryKey);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new DAOException("根据主键删除底层失败", e);
		}
	}

	@Override
	public int deleteClass(T entity, String mapperName) throws DAOException {
		try {
			return sqlSessionWrite.delete(mapperName, entity);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new DAOException("根据实体类删除底层失败", e);
		}
	}

	@Override
	public int count(T entity, String mapperName) throws DAOException {
		try {
			Integer result = sqlSessionRead.selectOne(mapperName, entity);
			if(result == null){
				return 0;
			}else{
				return result;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new DAOException("统计数量底层失败", e);
		}
	}

	@Override
	public T select(PK primaryKey, String mapperName) throws DAOException {
		try {
			return sqlSessionRead.selectOne(mapperName, primaryKey);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new DAOException("根据主键查询底层失败", e);
		}
	}

	@Override
	public T select(T entity, String mapperName) throws DAOException {
		try {
			return sqlSessionRead.selectOne(mapperName, entity);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new DAOException("根据实体查询底层失败", e);
		}
	}

	@Override
	public boolean batchInsert(List<T> list, String mapperName)
			throws DAOException {
		try {
			for (T entity : list) {
				sqlSessionWrite.insert(mapperName, entity);
			}
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new DAOException("批量添加底层失败", e);
		}
	}

	@Override
	public boolean batchUpdate(List<T> list, String mapperName)
			throws DAOException {
		try {
			for (T entity : list) {
				int bool = sqlSessionWrite.update(mapperName, entity);
				if (bool != 1) {
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new DAOException("批量修改底层失败", e);
		}
	}

	@Override
	public boolean batchDelete(List<PK> list, String mapperName)
			throws DAOException {
		try {
			for (PK primaryKey : list) {
				int bool = sqlSessionWrite.delete(mapperName, primaryKey);
				if (bool != 1) {
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new DAOException("批量删除底层失败", e);
		}
	}

	@Override
	public List<T> paramSQL(String sqlId, T entity) throws DAOException {
		try {
			return sqlSessionRead.selectList(sqlId, entity);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new DAOException("自定义查询底层失败", e);
		}
	}

	@Override
	public List<T> selectListSeek(T entity, Integer currPage, Integer pageSize,
			String mapperName) throws DAOException {
		try {
			return sqlSessionRead.selectList(mapperName, entity);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new DAOException("模糊查询底层失败", e);
		}
	}

	@Override
	public List<T> selectByIds(String sqlId, List<Integer> list)
			throws DAOException {
		try {
			return sqlSessionRead.selectList(sqlId, list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new DAOException("根据主键集合查询集合底层失败", e);
		}
	}

	@Override
	public List<T> selectListPaging(Map<String, Object> map, String mapperName)
			throws DAOException {
		try {
			return sqlSessionRead.selectList(mapperName, map);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new DAOException("分页查询底层失败", e);
		}
	}

	@Override
	public Page<T> selectListPagings(Page<T> page, String mapperName)
			throws DAOException {
		try {
			PageHelper.startPage(page.getPageNum(), page.getPageSize(), true);
			List<T> list = sqlSessionRead.selectList(mapperName);
			page = (Page<T>) list;
			return page;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new DAOException("使用mybatis插件的分页查询底层失败", e);
		}
	}

	@Override
	public boolean batchDelete(Map<String,Object> paramMap, String mapperName) throws DAOException {
		try {
			int bool = sqlSessionWrite.delete(mapperName, paramMap);
			if (bool != 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new DAOException("批量删除失败", e);
		}
	}

	/**
	 * @author songyb E-mail:songyanbiao@kdflink.com
	 * @version 创建时间：2017年4月14日 上午11:42:36
	 * @describe
	 * @parameter
	 * @return 
	 */
	@Override
	public T selectOne(String mapperName, Object paramer) throws DAOException {
		try {
			return sqlSessionRead.selectOne(mapperName, paramer);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new DAOException("根据主键查询底层失败", e);
		}
		
	}

	/**
	 * @author songyb E-mail:songyanbiao@kdflink.com
	 * @version 创建时间：2017年4月14日 下午5:08:51
	 * @describe 查询实体集合
	 * @parameter
	 * @return 
	 */
	@Override
	public List<T> selectListPoJo( Object paramer,String mapperName)
			throws DAOException {
		try {
			return sqlSessionRead.selectList(mapperName, paramer);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new DAOException("查询失败", e);
		}
	}

	@Override
	public boolean batchDeleteList(List<String> adList, String mapperName)
			throws DAOException {
		try {
			for (String primaryKey : adList) {
				int bool = sqlSessionWrite.delete(mapperName, primaryKey);
				if (bool != 1) {
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new DAOException("批量删除底层失败", e);
		}
	}

}
