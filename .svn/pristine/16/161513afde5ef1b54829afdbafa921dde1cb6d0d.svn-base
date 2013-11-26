package com.evmtv.epg.service;

import java.util.List;

import com.evmtv.epg.entity.TUserNodeStatus;
/**
 * 用户节点状态
 * @author fangzhu(fangzhu@evmtv.com)
 * @time 2013-6-17 上午9:29:37
 */
public interface IUserNodeStatus {
	/**
	 * 新增
	 * @param status
	 * @return
	 */
	int insert(TUserNodeStatus status);
	/**
	 * 删除
	 * @param id 主键
	 * @return
	 */
	int delete(List<Long> id);
	/**
	 * 更新、根据id修改
	 * @param status
	 * @return
	 */
	int update(TUserNodeStatus status);
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	TUserNodeStatus selectByKey(Long id);
	/**
	 * 条件查询
	 * @param status
	 * @return
	 */
	List<TUserNodeStatus> selectByExample(TUserNodeStatus status);
	/**
	 * 根据分公司广告索引查询
	 * @param branchid
	 * @return
	 */
	List<TUserNodeStatus> selectBranchUserNodeStatus(Long branchid,Long sourceId);
	/**
	 * 根据分公司查询
	 * @param branchid
	 * @return
	 */
	List<TUserNodeStatus> selectBranchNode(Long branchid,Long sourceId);
}
