package com.evmtv.epg.service;

import java.util.List;

import com.evmtv.epg.entity.TStatusCarOrRv;
import com.evmtv.epg.response.MyTask;

/**
 * <p>Title: 状态</p> 
 * <p>Description: 审核状态</p> 
 * <p>Date: 2013年12月2日下午6:06:27</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * <p>Company: www.evmtv.com</p> 
 * @author fangzhu@evmtv.com
 * @version 1.0
 */
public interface IStatusCarOrRv {
	/**
	 * 新增
	 * @param adv
	 * @return
	 */
	int insert(TStatusCarOrRv s);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	int delete(List<Long> id);
	/**
	 * 删除
	 * @param s
	 * @return
	 */
	int delete(TStatusCarOrRv s);
	/**
	 * 修改
	 * @param adv
	 * @return
	 */
	int update(TStatusCarOrRv adv);
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	TStatusCarOrRv selectByKey(Long id);
	/**
	 * 查询
	 * @param TStatusCarOrRv
	 * @return
	 */
	List<TStatusCarOrRv> selectByExample(TStatusCarOrRv record);
	/**
	 * 我的任务
	 * @param record
	 * @return
	 */
	List<MyTask> selectMyTask(TStatusCarOrRv record);
	
	Integer countMyTask(TStatusCarOrRv record);
	/**
	 * @param s
	 */
	int updateByKey(TStatusCarOrRv s);
}