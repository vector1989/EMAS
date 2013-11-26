package com.evmtv.epg.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.evmtv.epg.entity.TDbConfig;
import com.evmtv.epg.entity.TDbConfigExample;
import com.evmtv.epg.entity.TDbConfigExample.Criteria;
import com.evmtv.epg.mapper.TDbConfigMapper;
import com.evmtv.epg.service.IDbConfig;
import com.evmtv.epg.utils.CollectionUtills;
/**
 * 数据库配置service
 * TODO
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type
 */
@Service
public class TDbconfigService implements IDbConfig {

	@Resource
	TDbConfigMapper mapper;
	@Override
	public int insert(TDbConfig config) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(config);
	}

	@Override
	public int delete(List<Long> id) {
		// TODO Auto-generated method stub
		TDbConfigExample example = new TDbConfigExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdIn(id);
		return mapper.deleteByExample(example);
	}

	@Override
	public int update(TDbConfig config) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(config);
	}

	@Override
	public TDbConfig queryById(Long id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TDbConfig> query(TDbConfig config) {
		// TODO Auto-generated method stub
		TDbConfigExample example = new TDbConfigExample();
		if(config != null){
			Criteria criteria = example.createCriteria();
			if(config.getFbranchid() != null){
				criteria.andFbranchidEqualTo(config.getFbranchid());
			}
			if(StringUtils.hasText(config.getFip())){
				criteria.andFipLike("%".concat(config.getFip()).concat("%"));
			}
			example.setLimit(config.getLimit());
			example.setStart(config.getStart());
			if(config.getHolder() != null){
				config.getHolder().value = mapper.countByExample(example);
			}
		}
		
		List<TDbConfig> configs = mapper.selectByExample(example);
		if(config != null && !CollectionUtills.hasElements(configs) && config.getStart() > 0){
			int start = config.getStart() - config.getLimit();
			config.setStart(start);
			example.setStart(start);
			configs = mapper.selectByExample(example);
		}
		
		return configs;
	}

	@Override
	public TDbConfig queryByBranchId(Long branchid) {
		// TODO Auto-generated method stub
		TDbConfigExample example = new TDbConfigExample();
		example.createCriteria().andFbranchidEqualTo(branchid);
		List<TDbConfig> configs = mapper.selectByExample(example);
		return configs.isEmpty() ? null : configs.get(0);
	}
	
	@Override
	public List<Long> selectAllFBranchid(TDbConfig config){
		TDbConfigExample example = new TDbConfigExample();
		if(config != null){
			Criteria criteria = example.createCriteria();
			if(config.getFbranchid() != null){
				criteria.andFbranchidEqualTo(config.getFbranchid());
			}
		}
		return mapper.selectAllFBranchid(example);
	}

	@Override
	public List<TDbConfig> query(List<Long> branchid){

		TDbConfigExample example = new TDbConfigExample();
		if(branchid != null && !branchid.isEmpty()){
			Criteria criteria = example.createCriteria();
			criteria.andFbranchidIn(branchid);
		}
		return mapper.selectByExample(example);
	}

	@Override
	public TDbConfig query(Long fbranchid) {
		TDbConfigExample example = new TDbConfigExample();
		Criteria criteria = example.createCriteria();
		criteria.andFbranchidEqualTo(fbranchid);
		List<TDbConfig> configs = mapper.selectByExample(example);
		return CollectionUtills.hasElements(configs) ? configs.get(0) : null;
	}
	
}
