package com.evmtv.epg.service;

import java.util.List;

import com.evmtv.epg.entity.TBranchAdvclass;
/**
 * 频点
 * @author fangzhu(fangzhu@evmtv.com)
 * @time 2013-6-17 上午9:29:37
 */
public interface IBranchAdvClass {
	/**
	 * 新增
	 * @param ba
	 * @return
	 */
	int insert(TBranchAdvclass ba);
	/**
	 * 根据branchId删除
	 * @param 
	 * @return
	 */
	int delete(Long branchId);
	/**
	 * 删除
	 * @param id 主键
	 * @return
	 */
	int delete(List<Long> id);
	/**
	 * 更新、根据id修改
	 * @param group
	 * @return
	 */
	int update(TBranchAdvclass ba);
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	TBranchAdvclass selectByKey(Long id);
	/**
	 * 条件查询
	 * @param group
	 * @return
	 */
	List<TBranchAdvclass> selectByExample(TBranchAdvclass ba);
	/**
	 * 根据分公司查询
	 * @param id
	 * @return 广告位索引
	 */
	List<Long> selectByBranchId(Long branchid);
	/**
	 * 分公司广告位
	 * @param branchs
	 * @return
	 */
	List<TBranchAdvclass> selectByBranchIdList(List<Long> branchs);
}
