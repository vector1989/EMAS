package com.evmtv.epg.service;

import java.util.List;

import com.evmtv.epg.entity.TNodeStatus;
import com.evmtv.epg.request.NodeStatusExpand;
/**
 * 用户节点状态
 * @author fangzhu(fangzhu@evmtv.com)
 * @time 2013-6-17 上午9:29:37
 */
public interface INodeStatus {
	/**
	 * 新增
	 * @param status
	 * @return
	 */
	int insert(TNodeStatus status);
	/**
	 * 删除
	 * @param id 主键
	 * @return
	 */
	int delete(List<Long> id);
	/**
	 * 根据合同广告素材索引
	 * @param fcontractadvresourceid
	 * @return
	 */
	int delete(Long fcontractadvresourceid);
	/**
	 * 更新、根据id修改
	 * @param status
	 * @return
	 */
	int update(TNodeStatus status);
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	TNodeStatus selectByKey(Long id);
	/**
	 * 条件查询
	 * @param status
	 * @return
	 */
	List<TNodeStatus> selectByExample(TNodeStatus status);
	/**
	 * 根据合同广告位所采索引查询
	 * @param carid
	 * @return
	 */
	List<TNodeStatus> selectByContractAdvResourceId(Long carid);
	
	/**
	 * 
	 * @param status
	 * @return
	 */
	List<TNodeStatus> selectByNodeStatus(TNodeStatus status);
	/**
	 * 查询
	 * @param status
	 * @return
	 */
	boolean query(TNodeStatus status);
	/**
	 * 
	 * @param sourceId
	 */
	int deleteBySourceId(List<Long> sourceId);
	/**
	 * 根据用户查询
	 * @param uid
	 * @return
	 */
	public List<NodeStatusExpand> selectByUserid(TNodeStatus status);
	/**
	 * 根据合同索引查询
	 * @param cid
	 * @return 
	 */
	TNodeStatus queryByContractId(Long cid);
	/**
	 * 根据合同索引删除
	 * @param ids
	 * @return 
	 */
	int deleteByCids(List<Long> ids);
	/**
	 * @param status
	 * @return
	 */
	int updateByExample(TNodeStatus status);
	/**
	 * 根据父节点查询
	 * @param pid
	 * @return
	 */
	TNodeStatus selectByParentId(Long pid);
	/**
	 * @param carid 合同广告位索引
	 * @param nid 节点索引
	 * @return
	 */
	TNodeStatus selectByCaridOrRvidAndNodeid(Long carid, Long rvid,Long nid);
}