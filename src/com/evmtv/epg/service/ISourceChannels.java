package com.evmtv.epg.service;

import java.util.List;

import com.evmtv.epg.entity.TSourceChannels;
import com.evmtv.epg.request.SourceChannelsRequest;

/**
 * 广告频点关联
 * @author fangzhu(fangzhu@evmtv.com)
 * @time 2013-6-17 下午6:38:35
 */
public interface ISourceChannels {
	/**
	 * 保存
	 * @param channels
	 * @return
	 */
	int insert(TSourceChannels channels);
	/**
	 * 批量保存
	 * @param channels
	 * @return
	 */
	int insert(SourceChannelsRequest request);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	int delete(List<Long> id);
	/**
	 * 根据广告索引删除
	 * @param fsourceid
	 * @return
	 */
	int delete(Long fsourceid);
	/***
	 * 修改
	 * @param channels
	 * @return
	 */
	int update(TSourceChannels channels);
	/***
	 * 根据id查询
	 * @param id
	 * @return
	 */
	TSourceChannels selectByKey(Long id);
	/**
	 * 查询
	 * @param adv
	 * @return
	 */
	List<TSourceChannels> selectByExample(TSourceChannels adv);
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	List<TSourceChannels> select(List<Long> id);
	/**
	 * 批量删除
	 * @param fsourceid
	 * @return
	 */
	int deleteBySourceId(List<Long> fsourceid);
}
