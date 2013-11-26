package com.evmtv.epg.service;

import java.util.List;

import com.evmtv.epg.entity.TBranch;
import com.evmtv.epg.entity.TBranchWithBLOBs;

/**
 * 
 * 
 * TODO 分公司业务处理接口
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type
 */

public interface IBranch {
	/**
	 * 新增分公司
	 * @param bloBs
	 * @return
	 */
	int insert(TBranchWithBLOBs bloBs);
	/**
	 * 根据id批量删除分公司
	 * @param id
	 * @return
	 */
	int delete(List<Long> id);
	/**
	 * 修改
	 * @param bloBs
	 * @return
	 */
	int update(TBranchWithBLOBs bloBs);
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	TBranchWithBLOBs queryById(Long id);
	/**
	 * 条件查询
	 * @param bloBs
	 * @return
	 */
	List<TBranchWithBLOBs> queryWithBLOBs(TBranch bloBs);
	/**
	 * 条件查询
	 * @param branch
	 * @return
	 */
	List<TBranch> query(TBranch branch);
	
	List<TBranch> query(List<Long> id);
}
