package com.evmtv.epg.service;

import java.util.List;

import com.evmtv.epg.entity.TUserNode;
/**
 * 用户节点
 * @author fangzhu(fangzhu@evmtv.com)
 * @time 2013-6-17 上午9:29:37
 */
public interface IUserNode {
	/**
	 * 新增
	 * @param node
	 * @return
	 */
	int insert(TUserNode node);
	/**
	 * 删除
	 * @param id 主键
	 * @return
	 */
	int delete(List<Long> id);
	/**
	 * 更新、根据id修改
	 * @param node
	 * @return
	 */
	int update(TUserNode node);
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	TUserNode selectByKey(Long id);
	/**
	 * 条件查询
	 * @param node
	 * @return
	 */
	List<TUserNode> selectByExample(TUserNode node);
	/**
	 * 总记录数
	 * @param node
	 * @return
	 */
	int count(Long branchid);
	/**
	 * 排序
	 * @param userNode
	 * @return
	 */
	int order(TUserNode userNode);
	/**
	 * 根据分公司查询节点
	 * @param fbranchid
	 * @return
	 */
	List<TUserNode> queryByBranchId(Long fbranchid);
	/**
	 * 根据分公司查询节点 链表查询
	 * @param fbranchid
	 * @return
	 */
	List<TUserNode> selectByExample(Long fbranchid);
}