package com.evmtv.epg.service;

import java.util.List;

import com.evmtv.epg.entity.TReleaseVersion;
/**
 * 广告发布版本
 * TODO
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type 
 * @project_name EAMS
 * @file_name IReleaseVersion.java
 * @package_name com.evmtv.epg.service
 * @date_time 2013年10月30日下午12:29:38
 * @type_name IReleaseVersion
 */
public interface IReleaseVersion {
	/**
	 * 新增
	 * @param version
	 * @return
	 */
	int insert(TReleaseVersion version);
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
	int update(TReleaseVersion version);
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	TReleaseVersion selectByKey(Long id);
	/**
	 * 条件查询
	 * @param version
	 * @return
	 */
	List<TReleaseVersion> selectByExample(TReleaseVersion version);
	/**
	 * 根据分公司查询
	 * @param fbranchid
	 * @return
	 */
	List<TReleaseVersion> selectByBranchid(Long fbranchid,String fdefinition,Integer status);
	/**
	 * 查询分公司当天版本号最大的
	 * @param fbranchid
	 * @return
	 */
	TReleaseVersion selectByBranchidAndMaxDayVersion(Long fbranchid,String definition);
	/**
	 * 获取分公司最大版本号
	 * @param branchid
	 * @return
	 */
	TReleaseVersion selectMaxUpdateTimeByBranchid(Long branchid,String definition);
	/**
	 * 分公司最大版本好索引
	 * @param fbranchid
	 * @param fdefinition
	 */
	TReleaseVersion selectMaxIdByFbranchidAndFdefinition(TReleaseVersion rv);
	/**
	 * 
	 * @param branchid
	 * @param definition
	 * @param status
	 * @return
	 */
	TReleaseVersion selectMaxIdByFbranchidAndFdefinition(Long branchid,
			String definition, Integer status);
}