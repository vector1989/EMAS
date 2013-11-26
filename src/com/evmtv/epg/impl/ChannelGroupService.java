package com.evmtv.epg.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.evmtv.epg.entity.TChannelGroup;
import com.evmtv.epg.entity.TChannelGroupExample;
import com.evmtv.epg.entity.TChannelGroupExample.Criteria;
import com.evmtv.epg.mapper.TChannelGroupMapper;
import com.evmtv.epg.service.IChannelGroup;
/**
 * 频道分组
 * TODO
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type 
 * @project_name EAMS
 * @file_name ChannelGroupService.java
 * @package_name com.evmtv.epg.impl
 * @date_time 2013-9-11下午9:10:20
 * @type_name ChannelGroupService
 */
@Service
public class ChannelGroupService implements IChannelGroup {

	@Resource TChannelGroupMapper mapper;
	
	@Override
	public int insert(TChannelGroup group) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(group);
	}

	@Override
	public int delete(List<Long> id) {
		// TODO Auto-generated method stub
		TChannelGroupExample example = new TChannelGroupExample();
		example.createCriteria().andIdIn(id);
		return mapper.deleteByExample(example);
	}

	@Override
	public int update(TChannelGroup group) {
		return mapper.updateByPrimaryKeySelective(group);
	}

	@Override
	public TChannelGroup selectByKey(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TChannelGroup> selectByExample(TChannelGroup group) {
		// TODO Auto-generated method stub
		TChannelGroupExample example = new TChannelGroupExample();
		if(group != null){
			Criteria criteria = example.createCriteria();
			if(group.getFbranchid() != null){
				criteria.andFbranchidEqualTo(group.getFbranchid());
			}
			if(StringUtils.hasText(group.getFisprovincecompany())){
				criteria.andFisprovincecompanyEqualTo(group.getFisprovincecompany());
			}
			if(group.getHolder() != null){
				group.getHolder().value = mapper.countByExample(example);
			}
			example.setLimit(group.getLimit());
			example.setStart(group.getStart());
		}
		return mapper.selectByExample(example);
	}
}
