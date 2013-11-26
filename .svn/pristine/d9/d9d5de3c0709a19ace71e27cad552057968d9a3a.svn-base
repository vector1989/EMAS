package com.evmtv.epg.service;

import java.util.List;

import com.evmtv.epg.entity.TUsergroup;
/**
 * 频点
 * @author fangzhu(fangzhu@evmtv.com)
 * @time 2013-6-17 上午9:29:37
 */
public interface IUserGroup {
	/**
	 * 新增
	 * @param group
	 * @return
	 */
	int insert(TUsergroup group);
	/**
	 * 删除
	 * @param id 主键
	 * @return
	 */
	int delete(List<Long> id);
	/**
	 * 更新、根据id修改
	 * @param group
	 * @return
	 */
	int update(TUsergroup group);
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	TUsergroup selectByKey(Long id);
	/**
	 * 条件查询
	 * @param group
	 * @return
	 */
	List<TUsergroup> selectByExample(TUsergroup group);
}
