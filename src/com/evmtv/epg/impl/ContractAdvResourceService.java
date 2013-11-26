package com.evmtv.epg.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.evmtv.epg.entity.BranchIdAndSourceId;
import com.evmtv.epg.entity.TContractAdvResource;
import com.evmtv.epg.entity.TContractAdvResourceExample;
import com.evmtv.epg.entity.TContractAdvResourceExample.Criteria;
import com.evmtv.epg.mapper.TContractAdvResourceMapper;
import com.evmtv.epg.response.ResourceContractAdvResponse;
import com.evmtv.epg.service.IContractAdvRescource;

@Service
public class ContractAdvResourceService implements IContractAdvRescource {

	@Resource TContractAdvResourceMapper mapper;
	@Override
	public int insert(TContractAdvResource record) {
		return mapper.insertSelective(record);
	}

	@Override
	public int delete(List<Long> id) {
		// TODO Auto-generated method stub
		TContractAdvResourceExample example = new TContractAdvResourceExample();
		example.createCriteria().andIdIn(id);
		return mapper.deleteByExample(example);
	}

	@Override
	public int update(TContractAdvResource record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public TContractAdvResource selectByKey(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TContractAdvResource> selectByExample(
			TContractAdvResource record) {
		TContractAdvResourceExample example = new TContractAdvResourceExample();
		Criteria criteria = example.createCriteria();
		criteria.andFcontractadvidEqualTo(record.getFcontractadvid());
		
		return mapper.selectByExample(example);
	}
	
	@Override
	public int count(Long contractAdvId){
		TContractAdvResourceExample example = new TContractAdvResourceExample();
		Criteria criteria = example.createCriteria();
		criteria.andFcontractadvidEqualTo(contractAdvId);
		return mapper.countByExample(example);
	}

	@Override
	public List<ResourceContractAdvResponse> selectResourceByRcaresponse(
			ResourceContractAdvResponse response) {
		if(response.getHolder() != null){
			response.getHolder().value = mapper.countResourceByRcaresponse(response);
		}
		return mapper.selectResourceByRcaresponse(response);
	}

	@Override
	public int updateCheckedNodeId(Long id, Long nodid, int order) {
		//修改合同广告素材审核节点
		TContractAdvResource resource = new TContractAdvResource();
		resource.setId(id);
		resource.setFnodeid(nodid);
		resource.setForder(order);
		return mapper.updateByPrimaryKeySelective(resource);
	}

	@Override
	public List<BranchIdAndSourceId> startDateVerifySource() {
		// TODO Auto-generated method stub
		return mapper.startDateVerifySource();
	}

	@Override
	public int updateBySourceId(List<Long> sourceIds) {
		// TODO Auto-generated method stub
		TContractAdvResourceExample example = new TContractAdvResourceExample();
		example.createCriteria().andFsourceidIn(sourceIds);
		TContractAdvResource record = new TContractAdvResource();
		record.setFisusing("0");
		record.setFsourceid(null);
		return mapper.updateByExampleSelective(record, example);
	}
}