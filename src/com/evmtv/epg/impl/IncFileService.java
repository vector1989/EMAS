package com.evmtv.epg.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.evmtv.epg.entity.TIncFile;
import com.evmtv.epg.entity.TIncFileExample;
import com.evmtv.epg.entity.TIncFileExample.Criteria;
import com.evmtv.epg.mapper.TIncFileMapper;
import com.evmtv.epg.service.IIncFile;
/**
 * 
 * TODO
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type 
 * @project_name EAMS
 * @file_name IncFileService.java
 * @package_name com.evmtv.epg.impl
 * @date_time 2013年10月29日上午12:18:16
 * @type_name IncFileService
 */
@Service
public class IncFileService implements IIncFile {

	@Resource TIncFileMapper mapper;
	
	@Override
	public int insert(TIncFile incFile) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(incFile);
	}

	@Override
	public int delete(List<Long> id) {
		// TODO Auto-generated method stub
		TIncFileExample example = new TIncFileExample();
		example.createCriteria().andIdIn(id);
		return mapper.deleteByExample(example);
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(TIncFile incFile) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(incFile);
	}

	@Override
	public TIncFile selectByKey(Long id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TIncFile> selectByExample(TIncFile incFile) {
		// TODO Auto-generated method stub
		TIncFileExample example = new TIncFileExample();
		Criteria criteria = example.createCriteria();
		if(incFile.getFbranchid() != null){
			criteria.andFbranchidEqualTo(incFile.getFbranchid());
		}
		if(incFile.getFstatus() != null){
			criteria.andFstatusEqualTo(incFile.getFstatus());
		}
		if(incFile.getHolder() != null){
			incFile.getHolder().value = mapper.countByExample(example);
		}
		example.setLimit(incFile.getLimit());
		example.setStart(incFile.getStart());
		example.setOrderByClause(" t_inc_file.ID DESC");
		
		return mapper.selectByExample(example);
	}

	@Override
	public List<TIncFile> selectByBranchid(Long fbranchid) {
		// TODO Auto-generated method stub
		TIncFileExample example = new TIncFileExample();
		Criteria criteria = example.createCriteria();
		
		criteria.andFbranchidEqualTo(fbranchid);
		
		return mapper.selectByExample(example);
	}

	@Override
	public List<TIncFile> selectById(List<Long> incIds) {
		// TODO Auto-generated method stub
		TIncFileExample example = new TIncFileExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdIn(incIds);
		
		return mapper.selectByExample(example);
	}

}