package com.evmtv.epg.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.evmtv.epg.entity.TNodeStatusExample.Criteria;
import com.evmtv.epg.entity.TNodeStatus;
import com.evmtv.epg.entity.TNodeStatusExample;
import com.evmtv.epg.mapper.TNodeStatusMapper;
import com.evmtv.epg.request.NodeStatusExpand;
import com.evmtv.epg.service.INodeStatus;
import com.evmtv.epg.utils.CollectionUtills;
/**
 * 广告审批流程
 * TODO
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type 
 * @project_name EAMS
 * @file_name NodeStatusService.java
 * @package_name com.evmtv.epg.impl
 * @date_time 2013-9-10下午6:01:33
 * @type_name NodeStatusService
 */
@Service
public class NodeStatusService implements INodeStatus {

	@Resource TNodeStatusMapper mapper;
	
	@Override
	public int insert(TNodeStatus status) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(status);
	}

	@Override
	public int delete(List<Long> id) {

		TNodeStatusExample example = new TNodeStatusExample();
		example.createCriteria().andIdIn(id);
		
		return mapper.deleteByExample(example);
	}

	@Override
	public int delete(Long fcontractadvresourceid) {
		// TODO Auto-generated method stub
		TNodeStatusExample example = new TNodeStatusExample();
		example.createCriteria().andFcontractadvresourceidEqualTo(fcontractadvresourceid);
		return mapper.deleteByExample(example);
	}

	@Override
	public int update(TNodeStatus status) {
		
		return mapper.updateByPrimaryKeySelective(status);
	}

	@Override
	public TNodeStatus selectByKey(Long id) {
		
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TNodeStatus> selectByExample(TNodeStatus status) {
		// TODO Auto-generated method stub
		TNodeStatusExample example = new TNodeStatusExample();
		if(status != null){
			Criteria criteria = example.createCriteria();
			if(status.getFcontractadvid() != null)
				criteria.andFcontractadvidEqualTo(status.getFcontractadvid());
			if(status.getFcontractadvresourceid() != null)
				criteria.andFcontractadvresourceidEqualTo(status.getFcontractadvresourceid());
			if(status.getFcontractid() != null)
				criteria.andFcontractidEqualTo(status.getFcontractid());
			if(status.getFsourceid() != null)
				criteria.andFsourceidEqualTo(status.getFsourceid());
		}
		return mapper.selectByExample(example);
	}
	
	@Override
	public List<TNodeStatus> selectByContractAdvResourceId(Long carid){
		TNodeStatusExample example = new TNodeStatusExample();
		example.createCriteria().andFcontractadvresourceidEqualTo(carid);
		return mapper.selectByExample(example);
	}
	
	@Override
	public List<TNodeStatus> selectByNodeStatus(TNodeStatus status){
		
		return mapper.selectByNodeStatus(status);
	}

	@Override
	public boolean query(TNodeStatus status) {
		TNodeStatusExample example = new TNodeStatusExample();
		Criteria criteria = example.createCriteria();
		if(status.getFcontractadvid() != null){
			criteria.andFcontractadvidEqualTo(status.getFcontractadvid());
		}
		if(status.getFcontractadvresourceid() != null){
			criteria.andFcontractadvresourceidEqualTo(status.getFcontractadvresourceid());
		}
		if(status.getFcontractid() != null){
			criteria.andFcontractidEqualTo(status.getFcontractid());
		}
		if(status.getFnodeid() != null){
			criteria.andFnodeidEqualTo(status.getFnodeid());
		}
		if(status.getFsourceid() != null){
			criteria.andFsourceidEqualTo(status.getFsourceid());
		}
		if(StringUtils.hasText(status.getFstatus())){
			criteria.andFstatusEqualTo(status.getFstatus());
		}
		
		return CollectionUtills.hasElements(mapper.selectByExample(example));
	}

	@Override
	public int deleteBySourceId(List<Long> sourceId) {
		// TODO Auto-generated method stub
		TNodeStatusExample example = new TNodeStatusExample();
		example.createCriteria().andFsourceidIn(sourceId);
		
		return mapper.deleteByExample(example);
	}

	@Override
	public List<NodeStatusExpand> selectByUserid(TNodeStatus status) {
		// TODO Auto-generated method stub
		if(status.getHolder() != null)
			status.getHolder().value = mapper.countByUserId(status);
		return mapper.selectByUserId(status);
	}
}