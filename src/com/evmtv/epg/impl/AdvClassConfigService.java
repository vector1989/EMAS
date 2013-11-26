package com.evmtv.epg.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.evmtv.epg.entity.TAdvClassConfig;
import com.evmtv.epg.entity.TAdvClassConfigExample;
import com.evmtv.epg.entity.TAdvClassConfigExample.Criteria;
import com.evmtv.epg.mapper.TAdvClassConfigMapper;
import com.evmtv.epg.service.IAdvClassConfig;
import com.evmtv.epg.utils.CollectionUtills;

/**
 * 广告位属性管理 TODO
 * 
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type
 * @project_name EAMS
 * @file_name AdvClassConfigService.java
 * @package_name com.evmtv.epg.impl
 * @date_time 2013-8-12上午9:17:33
 * @type_name AdvClassConfigService
 */
@Service
public class AdvClassConfigService implements IAdvClassConfig {

	@Resource
	TAdvClassConfigMapper mapper;

	@Override
	public int insert(TAdvClassConfig adv) {
		return mapper.insertSelective(adv);
	}

	@Override
	public int delete(List<Long> id) {
		TAdvClassConfigExample example = new TAdvClassConfigExample();
		example.createCriteria().andIdIn(id);
		return mapper.deleteByExample(example);
	}

	@Override
	public int update(TAdvClassConfig adv) {
		return mapper.updateByPrimaryKeySelective(adv);
	}

	@Override
	public TAdvClassConfig selectByKey(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TAdvClassConfig> selectByExample(TAdvClassConfig adv) {
		TAdvClassConfigExample example = new TAdvClassConfigExample();

		Criteria criteria = example.createCriteria();
		// 分公司
		if (adv.getFbranchid() != null) {
			criteria.andFbranchidEqualTo(adv.getFbranchid());
		}
		// 广告位
		if (adv.getFpositionid() != null && adv.getFpositionid() > 0) {
			criteria.andFpositionidEqualTo(adv.getFpositionid());
		}
		if (StringUtils.hasLength(adv.getFdefinition())) {
			criteria.andFdefinitionEqualTo(adv.getFdefinition());
		}
		if (adv != null) {
			example.setLimit(adv.getLimit());
			example.setStart(adv.getStart());
		}
		if (adv.getHolder() != null) {
			adv.getHolder().value = mapper.countByExample(example);
		}
		List<TAdvClassConfig> advClassConfigs = mapper.selectByExample(example);
		if(adv != null && !CollectionUtills.hasElements(advClassConfigs) && adv.getStart() > 1){
			int start = adv.getStart() - adv.getLimit();
			adv.setStart(start);
			example.setStart(start);
			advClassConfigs = mapper.selectByExample(example);
		}
		return advClassConfigs;
	}

	@Override
	public TAdvClassConfig selectByObject(TAdvClassConfig config) {
		TAdvClassConfigExample example = new TAdvClassConfigExample();

		Criteria criteria = example.createCriteria();
		// 广告位
		if (config.getFpositionid() != null) {
			criteria.andFpositionidEqualTo(config.getFpositionid());
		}
		if (StringUtils.hasLength(config.getFdefinition())) {
			criteria.andFdefinitionEqualTo(config.getFdefinition());
		}
		return mapper.selectByObject(example);
	}
}
