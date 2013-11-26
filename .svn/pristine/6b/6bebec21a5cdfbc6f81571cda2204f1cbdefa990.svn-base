package com.evmtv.epg.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.evmtv.epg.entity.TUserNodeStatus;
import com.evmtv.epg.entity.TUserNodeStatusExample;
import com.evmtv.epg.entity.TUserNodeStatusExample.Criteria;
import com.evmtv.epg.mapper.TUserNodeStatusMapper;
import com.evmtv.epg.service.IUserNodeStatus;
@Service
public class UserNodeStatusService implements IUserNodeStatus {
	
	@Resource
	TUserNodeStatusMapper mapper;
	
	@Override
	public int insert(TUserNodeStatus status) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(status);
	}

	@Override
	public int delete(List<Long> id) {
		TUserNodeStatusExample example = new TUserNodeStatusExample();
		example.createCriteria().andIdIn(id);
		return mapper.deleteByExample(example);
	}

	@Override
	public int update(TUserNodeStatus status) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(status);
	}

	@Override
	public TUserNodeStatus selectByKey(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TUserNodeStatus> selectByExample(TUserNodeStatus status) {
		// TODO Auto-generated method stub
		TUserNodeStatusExample example = new TUserNodeStatusExample();
		Criteria criteria = example.createCriteria();
		if(status != null && status.getFbranchid() != null){
			criteria.andFbranchidEqualTo(status.getFbranchid());
		}
		return mapper.selectByExample(example);
	}

	@Override
	public List<TUserNodeStatus> selectBranchUserNodeStatus(Long branchid,
			Long sourceId) {
		TUserNodeStatusExample example = new TUserNodeStatusExample();
		Criteria criteria = example.createCriteria();
		if(branchid != null){
			criteria.andFbranchidEqualTo(branchid);
		}
		if(sourceId != null){
			criteria.andFsourceidEqualTo(sourceId);
		}
		return mapper.selectByExample(example);
	}

	@Override
	public List<TUserNodeStatus> selectBranchNode(Long branchid, Long contractId) {
		/*TUserNodeStatusExample example = new TUserNodeStatusExample();
		Criteria criteria = example.createCriteria();
		if(branchid != null){
			criteria.andFbranchidEqualTo(branchid);
		}
		if(contractId != null){
			criteria.andFcontractidEqualTo(contractId);
		}*/
		return mapper.selectBranchNode(branchid, contractId);
	}
}