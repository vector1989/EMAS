package com.evmtv.epg.service;

import java.util.List;

import com.evmtv.epg.entity.TBranchSourceRelease;
import com.evmtv.epg.entity.TContractAdv;
import com.evmtv.epg.entity.TSourceWithBLOBs;

/**
 * 已发布广告广告发布状态
 * TODO
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type 
 * @project_name EAMS
 * @file_name IBranchSourceRelease.java
 * @package_name com.evmtv.epg.service
 * @date_time 2013年7月26日下午12:58:05
 * @type_name IBranchSourceRelease
 */
public interface IBranchSourceRelease {
	/**
	 * 新增已发布广告
	 * @param bloBs
	 * @return
	 */
	int insert(TBranchSourceRelease bsr);
	/**
	 * 根据id批量删除已发布广告
	 * @param id
	 * @return
	 */
	int delete(List<Long> id);
	/**
	 * 修改
	 * @param bsr
	 * @return
	 */
	int update(TBranchSourceRelease bsr);
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	TBranchSourceRelease queryById(Long id);
	/**
	 * 条件查询
	 * @param bloBs
	 * @return
	 */
	List<TBranchSourceRelease> query(TBranchSourceRelease bsr);
	/**
	 * 查询广告索引
	 * @param bsr
	 * @return
	 */
	List<Long> queryBranchId(TBranchSourceRelease bsr);
	/***
	 * 查询广告索引
	 * @param fbranchid
	 * @return
	 */
	List<Long> queryBranchId(Long fbranchid);
	/**
	 * 
	 * @param release
	 * @return
	 */
	String selectByCreateTime(Long fbranchid);
	/**
	 * 根据广告删除
	 * @param sourceId
	 * @return
	 */
	int delete(Long sourceId);
	/**
	 * 过期合同索引
	 * @param fbranchid
	 * @return
	 */
	List<TContractAdv> selectExpiredContractId(Long fbranchid);
	/**
	 * 条件修改
	 * @param bsr
	 * @return
	 */
	int updateByExample(TBranchSourceRelease bsr);
	
	/**
	 * 根据分公司索引查询
	 * @param fbranchid
	 * @return
	 */
	List<TBranchSourceRelease> selectByBranchid(Long fbranchid);
	/**
	 * 去除已发布的广告
	 * @param sources
	 * @param branchid 
	 */
	List<TSourceWithBLOBs> query(List<TSourceWithBLOBs> sources, Long branchid);
	/**
	 * 删除 根据广告id
	 * @param sourceId
	 */
	int deleteBySourceId(List<Long> sourceId);
}