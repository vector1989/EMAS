package com.evmtv.epg.release;

import java.util.List;
/**
 * 数据库操作接口
 * TODO
 * @param <T
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type 
 * @project_name EAMS
 * @file_name IDao.java
 * @package_name com.evmtv.epg.db
 * @date_time 2013年7月22日下午3:46:20
 * @type_name IDao
 */
public interface IDao<T> {
	
	List<T> find(String queryString, Object... params);
	/**
	 * 添加
	 * @param sql sql语句
	 * @param clz 保存对象
	 * @return
	 */
	int insert(String[] column,String tableName,T clz);
	/**
	 * 新增
	 * @param sql sql语句
	 * @param t 保存对象集合
	 * @return
	 */
	int insert(String[] column,String tableName,List<T> t);
	/**
	 * 修改版本
	 * @param sql sql语句
	 * @param params 条件
	 * @return
	 */
	int update(String...params);
	/**
	 * 修改
	 * @param sql
	 * @param id
	 * @return
	 */
	int update(String[] column,String tableName,List<Long> id);
	/**
	 * 删除
	 * @param sql sql语句
	 * @param params 条件
	 * @return
	 */
	int delete(String[] column,String tableName,String...params);
}
