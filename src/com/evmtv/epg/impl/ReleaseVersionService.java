package com.evmtv.epg.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.evmtv.epg.entity.TReleaseVersion;
import com.evmtv.epg.entity.TReleaseVersionExample;
import com.evmtv.epg.entity.TReleaseVersionExample.Criteria;
import com.evmtv.epg.mapper.TReleaseVersionMapper;
import com.evmtv.epg.service.IReleaseVersion;
import com.evmtv.epg.utils.CollectionUtills;
import com.evmtv.epg.utils.DateUtils;
import com.evmtv.epg.utils.StringUtils;

/**
 * 广告发布 版本 TODO
 * 
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type
 * @project_name EAMS
 * @file_name ReleaseVersionService.java
 * @package_name com.evmtv.epg.impl
 * @date_time 2013年10月30日下午12:33:28
 * @type_name ReleaseVersionService
 */
@Service
public class ReleaseVersionService implements IReleaseVersion {

	@Resource
	TReleaseVersionMapper mapper;

	@Override
	public int insert(TReleaseVersion version) {
		// TODO Auto-generated method stub
		version.setFupdatetime(DateUtils.getCurrentTime());
		return mapper.insertSelective(version);
	}

	@Override
	public int delete(List<Long> id) {
		// TODO Auto-generated method stub
		TReleaseVersionExample example = new TReleaseVersionExample();
		example.createCriteria().andIdIn(id);

		return mapper.deleteByExample(example);
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(TReleaseVersion version) {
		// TODO Auto-generated method stub
		version.setFupdatetime(DateUtils.getCurrentTime());
//		if (StringUtils.hasText(version.getFdesc()))
//			version.setFdesc(";" + version.getFdesc());
//		return mapper.updateDescConcatByExampleSelective(version);
		return mapper.updateByPrimaryKeySelective(version);
	}

	@Override
	public TReleaseVersion selectByKey(Long id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TReleaseVersion> selectByExample(TReleaseVersion v) {
		// TODO Auto-generated method stub
		TReleaseVersionExample example = new TReleaseVersionExample();
		Criteria criteria = example.createCriteria();

		if (v.getFbranchid() != null) {
			criteria.andFbranchidEqualTo(v.getFbranchid());
		}
		if (v.getFdayversion() != null) {
			criteria.andFdayversionEqualTo(v.getFdayversion());
		}
		if (StringUtils.hasText(v.getFcreatetime())) {
			criteria.andFcreatetimeEqualTo(v.getFcreatetime());
		}
		if (StringUtils.hasText(v.getFversion())) {
			criteria.andFversionEqualTo(v.getFversion());
		}
		if (StringUtils.hasText(v.getFdesc())) {
			criteria.andFdescLike("%" + v.getFdesc() + "%");
		}
		
		if(CollectionUtills.hasElements(v.getIds())){
			criteria.andIdIn(v.getIds());
		}
		
		return mapper.selectByExample(example);
	}
 
	@Override
	public List<TReleaseVersion> selectByBranchid(Long fbranchid,String fdefinition,Integer status) {
		TReleaseVersionExample example = new TReleaseVersionExample();
		Criteria criteria = example.createCriteria();

		if (fbranchid != null) {
			criteria.andFbranchidEqualTo(fbranchid);
		}
		
		if(status != null){
			criteria.andFstatusEqualTo(status);
		}
		
		criteria.andFdefinitionEqualTo(fdefinition);
		example.setOrderByClause(" ID DESC");
		return mapper.selectByExample(example);
	}

	@Override
	public TReleaseVersion selectByBranchidAndMaxDayVersion(Long fbranchid,String definition) {
		// TODO Auto-generated method stub
		TReleaseVersionExample example = new TReleaseVersionExample();
		Criteria criteria = example.createCriteria();

		if (fbranchid != null) {
			criteria.andFbranchidEqualTo(fbranchid);
		}
		
		criteria.andFversionLike("%"+DateUtils.formatDate("yyyyMMdd")+"%");
		criteria.andFdefinitionEqualTo(definition);
		
		example.setOrderByClause(" FDayVersion DESC");
		example.setLimit(1);
		example.setStart(0);

		List<TReleaseVersion> versions = mapper.selectByExample(example);

		return CollectionUtills.hasElements(versions) ? versions.get(0) : null;
	}

	@Override
	public TReleaseVersion selectMaxUpdateTimeByBranchid(Long branchid,String definition) {
		// TODO Auto-generated method stub
		TReleaseVersionExample example = new TReleaseVersionExample();
		Criteria criteria = example.createCriteria();

		if (branchid != null) {
			criteria.andFbranchidEqualTo(branchid);
		}
		if (StringUtils.hasText(definition)){
			criteria.andFdefinitionEqualTo(definition);
		}
		example.setOrderByClause(" TIMESTAMPDIFF(SECOND,FUpdateTime,CURDATE()) DESC");
		example.setLimit(1);
		example.setStart(0);

		List<TReleaseVersion> versions = mapper.selectByExample(example);

		return CollectionUtills.hasElements(versions) ? versions.get(0) : null;
	}

	@Override
	public TReleaseVersion selectMaxIdByFbranchidAndFdefinition(TReleaseVersion rv) {
		TReleaseVersionExample example = new TReleaseVersionExample();
		Criteria criteria = example.createCriteria();

		if (rv.getFbranchid() != null) {
			criteria.andFbranchidEqualTo(rv.getFbranchid());
		}
		if (StringUtils.hasText(rv.getFdefinition())){
			criteria.andFdefinitionEqualTo(rv.getFdefinition());
		}
		if(rv.getFstatus() != null){
			criteria.andFstatusEqualTo(rv.getFstatus());
		}
		example.setOrderByClause(" ID DESC");
		example.setLimit(1);
		example.setStart(0);

		List<TReleaseVersion> versions = mapper.selectByExample(example);
		
		return CollectionUtills.hasElements(versions) ? versions.get(0) : null;
	}
	
	@Override
	public TReleaseVersion selectMaxIdByFbranchidAndFdefinition(Long branchid,String definition,Integer status) {
		TReleaseVersionExample example = new TReleaseVersionExample();
		Criteria criteria = example.createCriteria();

		if (branchid != null) {
			criteria.andFbranchidEqualTo(branchid);
		}
		if (StringUtils.hasText(definition)){
			criteria.andFdefinitionEqualTo(definition);
		}
		if(status != null){
			criteria.andFstatusEqualTo(status);
		}
		example.setOrderByClause(" ID DESC");
		example.setLimit(1);
		example.setStart(0);

		List<TReleaseVersion> versions = mapper.selectByExample(example);
		
		return CollectionUtills.hasElements(versions) ? versions.get(0) : null;
	}
}