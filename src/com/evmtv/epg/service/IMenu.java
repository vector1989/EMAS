package com.evmtv.epg.service;

import java.util.List;

import com.evmtv.epg.entity.TMenu;

/**
 * 菜单
 * @author fangzhu(fangzhu@evmtv.com)
 * @time 2013-6-13 下午4:56:49
 */
public interface IMenu {
	
	List<TMenu> query(List<Long> id);
}