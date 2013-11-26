package com.evmtv.epg.service;

import java.util.List;

import com.evmtv.epg.entity.TResource;
import com.evmtv.epg.entity.TResourceWithBLOBs;

/**
 * 资源
 * @author fangzhu(fangzhu@evmtv.com)
 * @time 2013-6-4 下午1:11:29
 */
public interface IResource {
	/**
	 * 保存资源信息
	 * @param resource
	 * @return
	 */
	int insert(TResourceWithBLOBs resource);
	/**
	 * 批量保存
	 * @param resources
	 * @return
	 */
	int intsertBatch(List<TResourceWithBLOBs> resources);
	/**
	 * 修改
	 * @param bloBs
	 * @return
	 */
	int update(TResourceWithBLOBs bloBs);
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
	int delete(List<Long> id);
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	TResourceWithBLOBs queryById(Long id);
	/**
	 * 条件查询
	 * @param resource
	 * @return
	 */
	List<TResourceWithBLOBs> query(TResourceWithBLOBs resource);
	List<TResource> query(TResource resource);
	/**
	 * 链表查询
	 * @param resource
	 * @return
	 */
	List<TResource> selectResource(TResource resource);
	/**
	 * 根据索引集合查询
	 * @param resourceIds
	 * @return
	 */
	List<TResource> query(List<Long> resourceIds);
}