package com.evmtv.epg.service;

import java.util.List;

import com.evmtv.epg.entity.TSourceReleaseVersion;
/**
 * 分公司广告发布
 * TODO
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type 
 * @project_name EAMS
 * @file_name IReleaseVersion.java
 * @package_name com.evmtv.epg.service
 * @date_time 2013年10月31日下午12:29:38
 * @type_name IReleaseVersion
 */
public interface ISourceReleaseVersion {
	/**
	 * 新增
	 * @param version
	 * @return
	 */
	int insert(TSourceReleaseVersion version);
	/**
	 * 删除
	 * @param id 主键
	 * @return
	 */
	int delete(List<Long> id);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	int delete(Long id);
	/**
	 * 更新、根据id修改
	 * @param version
	 * @return
	 */
	int update(TSourceReleaseVersion version);
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	TSourceReleaseVersion selectByKey(Long id);
	/**
	 * 条件查询
	 * @param version
	 * @return
	 */
	List<TSourceReleaseVersion> selectByExample(TSourceReleaseVersion version);
	/**
	 * 根据分公司查询
	 * @param fbranchid
	 * @return
	 */
	List<TSourceReleaseVersion> selectByBranchid(Long fbranchid);
	/**
	 * 
	 * @param version
	 * @return
	 */
	int insertBatchSelective(TSourceReleaseVersion version);
	/**
	 * 获取广告索引，根据版本索引
	 * @param id
	 * @return
	 */
	List<Long> selectSourceIdByReleaseVersionId(Long id);
}