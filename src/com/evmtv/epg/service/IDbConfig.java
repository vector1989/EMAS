package com.evmtv.epg.service;

import java.util.List;

import com.evmtv.epg.entity.TDbConfig;

/**
 * 数据库配置
 * 
 * TODO
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type
 */
public interface IDbConfig {
	/**
	 * 新增
	 * @param config
	 * @return
	 */
	int insert(TDbConfig config);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	int delete(List<Long> id);
	/**
	 * 修改
	 * @param config
	 * @return
	 */
	int update(TDbConfig config);
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	TDbConfig queryById(Long id);
	/**
	 * 条件批量查询
	 * @param config
	 * @return
	 */
	List<TDbConfig> query(TDbConfig config);
	/**
	 * 根据分公司查询
	 * @param branchid
	 * @return
	 */
	TDbConfig queryByBranchId(Long branchid);
	
	List<Long> selectAllFBranchid(TDbConfig config);
	/**
	 * 根据branchid查询
	 * @param id
	 * @return
	 */
	List<TDbConfig> query(List<Long> branchid);
	/***
	 * 查询分公司数据库配置
	 * @param fbranchid
	 * @return
	 */
	TDbConfig query(Long fbranchid);
	/**
	 * @param config
	 * @return
	 */
	int updateByBranchid(TDbConfig config);
}