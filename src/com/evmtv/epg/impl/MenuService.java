package com.evmtv.epg.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.evmtv.epg.entity.TMenu;
import com.evmtv.epg.entity.TMenuExample;
import com.evmtv.epg.mapper.TMenuMapper;
import com.evmtv.epg.service.IMenu;

/**
 * 菜单
 * 
 * @author fangzhu(fangzhu@evmtv.com)
 * @time 2013-6-13 下午5:16:39
 */
@Service
public class MenuService implements IMenu {

	@Resource
	TMenuMapper mapper;

	@Override
	public List<TMenu> query(List<Long> id) {
		TMenuExample example = new TMenuExample();
		if (id != null && !id.isEmpty()){
			example.createCriteria().andIdIn(id);
			example.or().andFparentidEqualTo(-1);
		}
		return mapper.selectByExample(example);
	}

}
