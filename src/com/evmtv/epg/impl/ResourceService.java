package com.evmtv.epg.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.evmtv.epg.entity.TResource;
import com.evmtv.epg.entity.TResourceExample;
import com.evmtv.epg.entity.TResourceExample.Criteria;
import com.evmtv.epg.entity.TResourceWithBLOBs;
import com.evmtv.epg.mapper.TResourceMapper;
import com.evmtv.epg.service.IResource;
import com.evmtv.epg.utils.CollectionUtills;

/**
 * 资源
 * 
 * @author fangzhu(fangzhu@evmtv.com)
 * @time 2013-6-4 下午1:22:02
 */
@Service
public class ResourceService implements IResource {
	@Resource
	TResourceMapper mapper;

	@Override
	public int insert(TResourceWithBLOBs resource) {

		return mapper.insertSelective(resource);
	}

	@Override
	public int intsertBatch(List<TResourceWithBLOBs> resources) {
		int result = 0;
		for (TResourceWithBLOBs resource : resources) {
			result += mapper.insertSelective(resource);
		}
		return result;
	}

	@Override
	public int update(TResourceWithBLOBs bloBs) {
		return mapper.updateByPrimaryKeySelective(bloBs);
	}

	@Override
	public int delete(List<Long> id) {
		TResourceExample example = new TResourceExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdIn(id);
		return mapper.deleteByExample(example);
	}

	@Override
	public TResourceWithBLOBs queryById(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TResourceWithBLOBs> query(TResourceWithBLOBs resource) {
		TResourceExample example = new TResourceExample();

		example.setLimit(resource.getLimit());
		example.setStart(resource.getStart());
		example.setOrderByClause(" ID DESC");
		resource.getHolder().value = mapper.countByExample(example);
		
		List<TResourceWithBLOBs> bloBs = mapper.selectByExampleWithBLOBs(example);
		if(!CollectionUtills.hasElements(bloBs) && resource.getStart() > 0){
			int start = resource.getStart() - resource.getLimit();
			resource.setStart(start);
			example.setStart(start);
			
			bloBs = mapper.selectByExampleWithBLOBs(example);
		}
		
		return bloBs;
	}

	@Override
	public List<TResource> query(TResource r) {
		TResourceExample example = new TResourceExample();

		Criteria criteria = example.createCriteria();
		if(StringUtils.hasText(r.getFtype())){
			criteria.andFtypeEqualTo(r.getFtype());
		}
		if(r.getFcontractid() != null){
			criteria.andFcontractidEqualTo(r.getFcontractid());
		}
		if (r.getFadvclassid() != null) {
			criteria.andFadvclassidEqualTo(r.getFadvclassid());
		}
		if (r.getFbranchid() != null) {
			criteria.andFbranchidEqualTo(r.getFbranchid());
		}
		if (StringUtils.hasText(r.getFchecked())) {
			criteria.andFcheckedEqualTo(r.getFchecked());
		}
		if (StringUtils.hasText(r.getFdefinition())) {
			criteria.andFdefinitionEqualTo(r.getFdefinition());
		}
		if (StringUtils.hasText(r.getQueryStartTime()) && StringUtils.hasText(r.getQueryEndTime())) {
			criteria.andFcreatetimeBetween(r.getQueryStartTime(), r.getQueryEndTime());
		}
		example.setLimit(r.getLimit());
		example.setStart(r.getStart());
		example.setOrderByClause(" ID DESC");

		if (r.getHolder() != null)
			r.getHolder().value = mapper.countByExample(example);
		List<TResource> bloBs = mapper.selectByExample(example);
		if(!CollectionUtills.hasElements(bloBs) && r.getStart() > 0){
			int start = r.getStart() - r.getLimit();
			r.setStart(start);
			example.setStart(start);
			
			bloBs = mapper.selectByExample(example);
		}
		
		return bloBs;
	}

	@Override
	public List<TResource> selectResource(TResource r) {
		if (r.getHolder() != null)
			r.getHolder().value = mapper.countResource(r);
		return mapper.selectResource(r);
	}

	@Override
	public List<TResource> query(List<Long> resourceIds) {
		TResourceExample example = new TResourceExample();

		Criteria criteria = example.createCriteria();
		criteria.andIdIn(resourceIds);
		return mapper.selectByResource(example);
	}
	
	
}
