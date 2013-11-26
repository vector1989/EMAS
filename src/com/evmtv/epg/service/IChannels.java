package com.evmtv.epg.service;

import java.util.List;

import com.evmtv.epg.entity.TChannels;
import com.evmtv.epg.request.ChannelRequest;
/**
 * 频点
 * @author fangzhu(fangzhu@evmtv.com)
 * @time 2013-6-17 上午9:29:37
 */
public interface IChannels {
	/**
	 * 新增
	 * @param channels
	 * @return
	 */
	int insert(TChannels channels);
	/**
	 * 批量新增
	 * @param channels
	 * @return
	 */
	int batchInsert(List<TChannels> channels);
	/**
	 * 删除
	 * @param id 主键
	 * @return
	 */
	int delete(List<Long> id);
	/**
	 * 根据分公司索引删除
	 * @param fbranchid
	 * @return
	 */
	int delete(Long fbranchid);
	/**
	 * 更新、根据id修改
	 * @param channels
	 * @return
	 */
	int update(TChannels channels);
	/**
	 * 批量修改
	 * @return
	 */
	int updateLevel(String level,List<Long> id);
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	TChannels selectByKey(Long id);
	/**
	 * 条件查询
	 * @param channels
	 * @return
	 */
	List<TChannels> selectByExample(TChannels channels);
	/**
	 * 根据分公司查询
	 * @param fbranchid
	 * @return
	 */
	List<TChannels> queryByBranchId(Long fbranchid,Long rvid);
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	List<TChannels> queryByIdList(List<Long> id);
	/**
	 * 已有频道分公司
	 * @return
	 */
	List<Long> selectBranchid();
	/**
	 * 查询分公司频道 业务号在s1和s2之间
	 * @param fbranchid 分公司索引
	 * @param s1 业务号
	 * @param s2 业务号
	 * @return
	 */
	List<TChannels> queryByBranchIdAndBetweenServiceId(ChannelRequest request);
	/**
	 * 批量修改频道分组
	 * @param level
	 * @param serviceids
	 * @return 
	 */
	int updateGroup(String level, List<String> serviceids);
	/**
	 * 修改原有的级别为空
	 * @param flevel
	 * @param fbranchid
	 * @return
	 */
	int updateLevel(String flevel, Long fbranchid);
	
	/**
	 * 获取分公司版本号
	 * @param fbranchid
	 * @return
	 */
	Long selectMaxRvIdByBranchid(Long fbranchid);
}
