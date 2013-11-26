package com.evmtv.epg.service;


import java.util.List;

import com.evmtv.epg.entity.BranchIdAndSourceId;
import com.evmtv.epg.entity.TContractAdvResource;
import com.evmtv.epg.response.ResourceContractAdvResponse;

/***
 * 合同广告素材
 * TODO
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type 
 * @project_name EAMS
 * @file_name IContractAdvRescource.java
 * @package_name com.evmtv.epg.service
 * @date_time 2013-9-8下午3:48:09
 * @type_name IContractAdvRescource
 */
public interface IContractAdvRescource {
	/**
	 * 新增
	 * @param record
	 * @return
	 */
	int insert(TContractAdvResource record);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	int delete(List<Long> id);
	/**
	 * 修改
	 * @param record
	 * @return
	 */
	int update(TContractAdvResource record);
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	TContractAdvResource selectByKey(Long id);
	/**
	 * 查询
	 * @param record
	 * @return
	 */
	List<TContractAdvResource> selectByExample(TContractAdvResource record);
	/**
	 * 记录数
	 * @param contractAdvId
	 * @return
	 */
	int count(Long contractAdvId);
	/**
	 * 广告位资源信息
	 * @param response
	 * @return
	 */
	List<ResourceContractAdvResponse> selectResourceByRcaresponse(ResourceContractAdvResponse response);
	/**
	 * 修改审批节点
	 * @param id
	 * @param nodid
	 * @return
	 */
	int updateCheckedNodeId(Long id,Long nodid,int order);
	/**
	 * 验证广告开始时间
	 * @return
	 */
	List<BranchIdAndSourceId> startDateVerifySource();
	/**
	 * 
	 * @param id
	 */
	int updateBySourceId(List<Long> sourceIds);
}