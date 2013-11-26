package com.evmtv.epg.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.evmtv.epg.entity.TSourceReleaseVersion;
import com.evmtv.epg.entity.TSourceReleaseVersionExample;
import com.evmtv.epg.entity.TSourceReleaseVersionExample.Criteria;
import com.evmtv.epg.mapper.TSourceReleaseVersionMapper;
import com.evmtv.epg.service.ISourceReleaseVersion;
/**
 * 广告发布 版本
 * TODO
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type 
 * @project_name EAMS
 * @file_name ReleaseVersionService.java
 * @package_name com.evmtv.epg.impl
 * @date_time 2013年10月30日下午12:33:28
 * @type_name ReleaseVersionService
 */
@Service
public class SourceReleaseVersionService implements ISourceReleaseVersion {

	@Resource TSourceReleaseVersionMapper mapper;
	
	@Override
	public int insert(TSourceReleaseVersion version) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(version);
	}

	@Override
	public int delete(List<Long> id) {
		// TODO Auto-generated method stub
		TSourceReleaseVersionExample example = new TSourceReleaseVersionExample();
		example.createCriteria().andIdIn(id);
		
		return mapper.deleteByExample(example);
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(TSourceReleaseVersion version) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(version);
	}

	@Override
	public TSourceReleaseVersion selectByKey(Long id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TSourceReleaseVersion> selectByExample(TSourceReleaseVersion v) {
		// TODO Auto-generated method stub
		TSourceReleaseVersionExample example = new TSourceReleaseVersionExample();
		Criteria criteria = example.createCriteria();
		
		if(v.getFbranchid() != null){
			criteria.andFbranchidEqualTo(v.getFbranchid());
		}
		if(v.getFreleaseversionid() != null){
			criteria.andFreleaseversionidEqualTo(v.getFreleaseversionid());
		}
		if(v.getFsourceid() != null){
			criteria.andFsourceidEqualTo(v.getFsourceid());
		}
		return mapper.selectByExample(example);
	}

	@Override
	public List<TSourceReleaseVersion> selectByBranchid(Long fbranchid) {
		TSourceReleaseVersionExample example = new TSourceReleaseVersionExample();
		Criteria criteria = example.createCriteria();
		
		if(fbranchid != null){
			criteria.andFbranchidEqualTo(fbranchid);
		}
		return mapper.selectByExample(example);
	}

	@Override
	public int insertBatchSelective(TSourceReleaseVersion version) {
		// TODO Auto-generated method stub
		if(version.getSources() == null){
			return mapper.insert(version);
		}
		return mapper.insertBatchSelective(version.getSources());
	}

	@Override
	public List<Long> selectSourceIdByReleaseVersionId(Long rvid) {

		TSourceReleaseVersionExample example = new TSourceReleaseVersionExample();
		Criteria criteria = example.createCriteria();
		
		if(rvid != null){
			criteria.andFreleaseversionidEqualTo(rvid);
		}
		return mapper.selectSourceIdByReleaseVersionId(example);
	}
}