package com.evmtv.epg.service;


import java.util.List;

import com.evmtv.epg.entity.TContractAdv;

/**
 * 合同广告位关联
 * TODO
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type 
 * @project_name EAMS
 * @file_name IContractAdv.java
 * @package_name com.evmtv.epg.service
 * @date_time 2013-8-26下午2:40:50
 * @type_name IContractAdv
 */
public interface IContractAdv {
	/**
	 * 新增
	 * @param contractAdv
	 * @return
	 */
	int insert(TContractAdv contractAdv);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	int delete(List<Long> id);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	int delete(TContractAdv adv);
	/**
	 * 修改
	 * @param contractAdv
	 * @return
	 */
	int update(TContractAdv contractAdv);
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	TContractAdv selectByKey(Long id);
	/**
	 * 查询
	 * @param contractAdv
	 * @return
	 */
	List<TContractAdv> selectByExample(TContractAdv contractAdv);
	/**
	 * 
	 * @param fcontractid
	 * @return
	 */
	int deleteConadvByFcontractid(Long fcontractid);
	/**
	 * 检查该合同下广告位是否已存在
	 * @param adv
	 * @return
	 */
	boolean checkAdvExist(TContractAdv adv);
	/**
	 * 根据合同索引查询
	 * @param fcontractid
	 * @return
	 */
	List<TContractAdv> selectByContractId(Long fcontractid);
	/**
	 * 查询合同的广告素材
	 * @param fcontractid
	 * @return
	 */
	List<TContractAdv> selectResourceByContractId(Long fcontractid);
	/**
	 * 根据分公司id查询
	 * @param fbranchid
	 * @return
	 */
	List<TContractAdv> selectByFbranchId(Long fbranchid);
	/**
	 * 根据分公司id查询广告位
	 * @param fbranchid
	 * @return
	 */
	List<TContractAdv> selectAdvByFbranchId(Long fbranchid);
	/**
	 * 合同广告位
	 * @param fcontractadvid
	 * @return
	 */
	TContractAdv selectObjectByContractId(Long fcontractadvid);
}
