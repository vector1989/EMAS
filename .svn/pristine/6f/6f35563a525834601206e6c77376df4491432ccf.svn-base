package com.evmtv.epg.service;

import java.util.List;

import com.evmtv.epg.entity.TIncFile;
/**
 * inc文件
 * TODO
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type 
 * @project_name EAMS
 * @file_name IIncFile.java
 * @package_name com.evmtv.epg.service
 * @date_time 2013年10月29日上午12:16:54
 * @type_name IIncFile
 */
public interface IIncFile {
	/**
	 * 新增
	 * @param incFile
	 * @return
	 */
	int insert(TIncFile incFile);
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
	 * @param incFile
	 * @return
	 */
	int update(TIncFile incFile);
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	TIncFile selectByKey(Long id);
	/**
	 * 条件查询
	 * @param incFile
	 * @return
	 */
	List<TIncFile> selectByExample(TIncFile incFile);
	/**
	 * 根据分公司查询
	 * @param fbranchid
	 * @return
	 */
	List<TIncFile> selectByBranchid(Long fbranchid);
	/**
	 * 根据id查询
	 * @param incIds
	 * @return
	 */
	List<TIncFile> selectById(List<Long> incIds);
}
