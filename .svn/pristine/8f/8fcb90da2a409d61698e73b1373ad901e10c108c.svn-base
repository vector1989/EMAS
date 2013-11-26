package com.evmtv.epg.service;

import java.util.List;

import com.evmtv.epg.entity.TAdv;
import com.evmtv.epg.entity.TAdvWithBLOBs;

/**
 * 广告位
 * @author fangzhu(fangzhu@evmtv.com)
 * @time 2013-6-17 下午6:38:35
 */
public interface IAdv {

	int insert(TAdvWithBLOBs adv);
	
	int delete(List<Long> id);
	
	int update(TAdvWithBLOBs adv);
	
	TAdvWithBLOBs selectByKey(Long id);
	/**
	 * 查询
	 * @param adv
	 * @return
	 */
	List<TAdv> selectByExample(TAdv adv);
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	List<TAdv> select(List<Long> id);

	/**
	 * @param id
	 * @param adv
	 * @return
	 */
	int checked(List<Long> id, TAdvWithBLOBs adv);
	/**
	 * 
	 * @param fbranchids
	 * @return
	 */
	List<TAdv> query(List<Long> fbranchids);
	/**
	 * 
	 * @param fbranchid
	 * @return
	 */
	List<TAdv> query(Long fbranchid);
	/**
	 * 根据广告位索引和分公司索引查询 该记录是否存在
	 * @param fadvclassid
	 * @param fbranchid
	 * @return
	 */
	List<TAdv> selectByFadvClassIdAndFbranchid(Long fadvclassid,Long fbranchid);
	/**
	 * 修改删除
	 * @param fbranchid
	 * @param ids
	 * @param delete
	 * @param notIn
	 * @return
	 */
	int updateDelete(Long fbranchid,List<Long> ids,String delete,boolean notIn);
	/**
	 * 根据分公司和解析度查询分公司广告位
	 * @param branchid 分公司索引
	 * @param definition 解析度
	 * @return
	 */
	List<TAdv> query(Long branchid,String definition);
	/**
	 * 批量新增
	 * @param advs
	 */
	int batchInsert(List<TAdv> advs);
}
