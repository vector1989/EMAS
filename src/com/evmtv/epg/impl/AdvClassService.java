package com.evmtv.epg.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.evmtv.epg.entity.TAdvClass;
import com.evmtv.epg.entity.TAdvClassExample;
import com.evmtv.epg.mapper.TAdvClassMapper;
import com.evmtv.epg.service.IAdvClass;

/**
 * 广告管理
 * 
 * @author fangzhu(fangzhu@evmtv.com)
 * @time 2013-6-17 下午6:41:13
 */
@Service
public class AdvClassService implements IAdvClass {

	@Resource
	TAdvClassMapper mapper;

	@Override
	public int insert(TAdvClass adv) {
		return mapper.insertSelective(adv);
	}

	@Override
	public int delete(List<Long> id) {
		TAdvClassExample example = new TAdvClassExample();
		example.createCriteria().andIdIn(id);
		return mapper.deleteByExample(example);
	}

	@Override
	public int update(TAdvClass adv) {
		return mapper.updateByPrimaryKeySelective(adv);
	}

	@Override
	public TAdvClass selectByKey(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TAdvClass> selectByExample(TAdvClass adv) {
		TAdvClassExample example = new TAdvClassExample();

		if (adv != null) {
			example.setLimit(adv.getLimit());
			example.setStart(adv.getStart());
			if(adv.getHolder() != null)
				adv.getHolder().value = mapper.countByExample(example);
		}
		return mapper.selectByExample(example);
	}

	@Override
	public List<TAdvClass> select(List<Long> id) {
		TAdvClassExample example = new TAdvClassExample();
		if (id != null && !id.isEmpty())
			example.createCriteria().andIdIn(id);
		return mapper.selectByExample(example);
	}

}
