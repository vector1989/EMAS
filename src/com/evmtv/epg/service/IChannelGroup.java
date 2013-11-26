package com.evmtv.epg.service;

import java.util.List;

import com.evmtv.epg.entity.TChannelGroup;
/**
 * 频道分组
 * @author fangzhu(fangzhu@evmtv.com)
 * @time 2013-6-17 上午9:29:37
 */
public interface IChannelGroup {
	/**
	 * 新增
	 * @param node
	 * @return
	 */
	int insert(TChannelGroup group);
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
	int update(TChannelGroup group);
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	TChannelGroup selectByKey(Long id);
	/**
	 * 条件查询
	 * @param node
	 * @return
	 */
	List<TChannelGroup> selectByExample(TChannelGroup group);
}
