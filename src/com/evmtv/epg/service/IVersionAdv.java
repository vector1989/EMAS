package com.evmtv.epg.service;

import java.util.List;

import com.evmtv.epg.entity.TAdv;
import com.evmtv.epg.entity.TVersionAdv;
import com.evmtv.epg.response.VersionAdvResponse;

/**
 * 
 * TODO
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type 
 * @project_name EAMS
 * @file_name IVersionAdv.java
 * @package_name com.evmtv.epg.service
 * @date_time 2013年11月9日下午1:11:30
 * @type_name IVersionAdv
 */
public interface IVersionAdv {
	/**
	 * 新增
	 * @param v
	 * @return
	 */
	int insert(TVersionAdv v);
	/**
	 * 新增
	 * @param v
	 * @return
	 */
	int batchInsert(List<TVersionAdv> vs);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	int delete(List<Long> id);
	/**
	 * 删除
	 * @param va
	 * @return
	 */
	int delete(TVersionAdv va);
	/**
	 * 修改
	 * @param adv
	 * @return
	 */
	int update(TVersionAdv adv);
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	TVersionAdv selectByKey(Long id);
	
	/**
	 * 广告版本索引查询
	 * @param rvid
	 * @return
	 */
	List<TVersionAdv> selectByReleaseVersionId(Long rvid);
	/**
	 * 广告版本索引查询广告位索引
	 * @param rvid
	 * @return
	 */
	List<Long> selectAdvidByReleaseVersionId(Long rvid);
	/**
	 * 查询
	 * @param adv
	 * @return
	 */
	List<TVersionAdv> selectByExample(TVersionAdv adv);
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	List<TVersionAdv> select(List<Long> id);

	/**
	 * 
	 * @param fbranchid
	 * @return
	 */
	List<TVersionAdv> query(Long fbranchid);
	/**
	 * 根据分公司和解析度查询分公司广告位
	 * @param branchid 分公司索引
	 * @param definition 解析度
	 * @return
	 */
	List<TVersionAdv> query(Long branchid,String definition);
	
	/**
	 * 分公司版本号最大的广告位信息
	 * @param id
	 */
	List<TAdv> selectAdvByMaxReleaseVersionid(VersionAdvResponse var);
	/**
	 * 根据版本好查询广告位
	 * @param rvid
	 * @return
	 */
	List<TAdv> selectAdvByReleaseVersionid(Long rvid);
	/**
	 * 查询分公司最大版本号
	 * @param bid
	 * @return
	 */
	Long selectMaxReleaseVersionid(VersionAdvResponse var);
	
	/**
	 * 高清开始视频广告位
	 * @param rvid
	 * @return
	 */
	TAdv selectHdVideoAdvByRvidAndPosid(Long rvid);
}
