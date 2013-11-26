package com.evmtv.epg.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.evmtv.epg.entity.TBranchAdvclass;
import com.evmtv.epg.entity.TBranchAdvclassExample;
import com.evmtv.epg.entity.TBranchAdvclassExample.Criteria;
import com.evmtv.epg.mapper.TBranchAdvclassMapper;
import com.evmtv.epg.service.IBranchAdvClass;

@Service
public class BranchAdvClassService implements IBranchAdvClass {

	@Resource
	TBranchAdvclassMapper mapper;
	@Override
	public int insert(TBranchAdvclass ba) {
		
		return mapper.insertSelective(ba);
	}

	@Override
	public int delete(Long branchId) {
		TBranchAdvclassExample example = new TBranchAdvclassExample();
		example.createCriteria().andFbranchidEqualTo(branchId);
		return mapper.deleteByExample(example);
	}

	@Override
	public int delete(List<Long> id) {
		TBranchAdvclassExample example = new TBranchAdvclassExample();
		example.createCriteria().andIdIn(id);
		return mapper.deleteByExample(example);
	}

	@Override
	public int update(TBranchAdvclass ba) {
		return 0;
	}

	@Override
	public TBranchAdvclass selectByKey(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TBranchAdvclass> selectByExample(TBranchAdvclass ba) {
		TBranchAdvclassExample example = new TBranchAdvclassExample();
		Criteria criteria = example.createCriteria();
		if(ba.getFadvclassid() != null){
			criteria.andFadvclassidEqualTo(ba.getFadvclassid());
		}
		if(ba.getFbranchid() != null){
			criteria.andFbranchidEqualTo(ba.getFbranchid());
		}
		return mapper.selectByExample(example);
	}

	@Override
	public List<Long> selectByBranchId(Long branchid) {
		TBranchAdvclassExample example = new TBranchAdvclassExample();
		Criteria criteria = example.createCriteria();
		if(branchid != null){
			criteria.andFbranchidEqualTo(branchid);
		}
		return mapper.selectByBranchId(example);
	}

	@Override
	public List<TBranchAdvclass> selectByBranchIdList(List<Long> branchs) {
		// TODO Auto-generated method stub
		TBranchAdvclassExample example = new TBranchAdvclassExample();
		Criteria criteria = example.createCriteria();
		criteria.andFbranchidIn(branchs);
		return mapper.selectByExample(example);
	}

}
