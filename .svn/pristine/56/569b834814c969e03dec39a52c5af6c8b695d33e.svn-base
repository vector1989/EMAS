package com.evmtv.epg.service;

import java.util.List;

import com.evmtv.epg.entity.TNode;
/**
 * 节点
 * @author fangzhu(fangzhu@evmtv.com)
 * @time 2013-6-17 上午9:29:37
 */
public interface INode {
	/**
	 * 新增
	 * @param node
	 * @return
	 */
	int insert(TNode node);
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
	int update(TNode node);
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	TNode selectByKey(Long id);
	/**
	 * 条件查询
	 * @param node
	 * @return
	 */
	List<TNode> selectByExample(TNode node);
	/**
	 * 总记录数
	 * @param node
	 * @return
	 */
	int maxOrder();
	/**
	 * 查询节点索引
	 * @param node
	 * @return
	 */
	TNode selectByNode(TNode node);
	/**
	 * 根据用户组获取节点
	 * @param ugid
	 * @return
	 */
	TNode selectNodeByUsergroupId(Long ugid);
}
