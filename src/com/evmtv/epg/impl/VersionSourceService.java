package com.evmtv.epg.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.evmtv.epg.entity.TSource;
import com.evmtv.epg.entity.TVersionSource;
import com.evmtv.epg.entity.TVersionSourceExample;
import com.evmtv.epg.entity.TVersionSourceExample.Criteria;
import com.evmtv.epg.mapper.TVersionSourceMapper;
import com.evmtv.epg.service.IVersionSource;
/**
 * 
 * TODO
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type 
 * @project_name EAMS
 * @file_name VersionSourceService.java
 * @package_name com.evmtv.epg.impl
 * @date_time 2013年11月9日下午2:10:33
 * @type_name VersionSourceService
 */
@Service
public class VersionSourceService implements IVersionSource {

	@Resource TVersionSourceMapper mapper;
	
	@Override
	public int insert(TVersionSource v) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(v);
	}

	@Override
	public int batchInsert(List<TVersionSource> vs) {
		// TODO Auto-generated method stub
		return mapper.batchInsert(vs);
	}

	@Override
	public int delete(List<Long> id) {
		TVersionSourceExample example = new TVersionSourceExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdIn(id);
		return mapper.deleteByExample(example);
	}

	@Override
	public int update(TVersionSource v) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(v);
	}

	@Override
	public TVersionSource selectByKey(Long id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TVersionSource> selectByExample(TVersionSource vs) {
		// TODO Auto-generated method stub
		TVersionSourceExample example = new TVersionSourceExample();
		Criteria criteria = example.createCriteria();
		if(vs.getFadvid() != null){
			criteria.andFadvidEqualTo(vs.getFadvid());
		}
		if(vs.getFchannelgroupid() != null){
			criteria.andFchannelgroupidEqualTo(vs.getFchannelgroupid());
		}
		if(vs.getFreleaseversionid() != null){
			criteria.andFreleaseversionidEqualTo(vs.getFreleaseversionid());
		}
		if(vs.getFsourceid() != null){
			criteria.andFsourceidEqualTo(vs.getFsourceid());
		}
		if(vs.getFtimeperiodid() != null){
			criteria.andFtimeperiodidEqualTo(vs.getFtimeperiodid());
		}
		
		return mapper.selectByExample(example);
	}

	@Override
	public List<TSource> selectSourceJoinByVersionSource(TVersionSource vs) {
		// TODO Auto-generated method stub
		if(vs.getHolder() != null)
			vs.getHolder().value = mapper.countSourceJoinByVersionSource(vs);
		return mapper.selectSourceJoinByVersionSource(vs);
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Long> selectRvidBySid(String sids) {
		// TODO Auto-generated method stub
		return mapper.selectRvidBySid(sids);
	}

}
