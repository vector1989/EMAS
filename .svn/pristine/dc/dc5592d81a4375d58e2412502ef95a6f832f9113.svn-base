package com.evmtv.epg.service;

import java.util.List;

import com.evmtv.epg.entity.TMenuUsergroup;
/**
 * 频点
 * @author fangzhu(fangzhu@evmtv.com)
 * @time 2013-6-17 上午9:29:37
 */
public interface IMenuUsergroup {
	/**
	 * 新增
	 * @param group
	 * @return
	 */
	int insert(TMenuUsergroup group);
	/**
	 * 根据用户分组id删除
	 * @param fusergroupid
	 * @return
	 */
	int delete(Long groupId);
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
	int update(TMenuUsergroup group);
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	TMenuUsergroup selectByKey(Long id);
	/**
	 * 条件查询
	 * @param group
	 * @return
	 */
	List<TMenuUsergroup> selectByExample(TMenuUsergroup group);
	/**
	 * @param groupIds
	 * @return
	 */
	List<TMenuUsergroup> query(List<Long> groupIds);
	/**
	 * @param uids
	 */
	int deleteByUid(List<Long> uids);
	/**
	 * 通过分组id和菜单id查询
	 * @param mid
	 * @param gid
	 * @return
	 */
	TMenuUsergroup queryByUidAndMid(Long mid,Long gid);
}
