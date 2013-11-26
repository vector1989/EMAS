package com.evmtv.epg.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.evmtv.epg.entity.TContract;
import com.evmtv.epg.entity.TContractExample;
import com.evmtv.epg.entity.TContractWithBLOBs;
import com.evmtv.epg.entity.TContractExample.Criteria;
import com.evmtv.epg.mapper.TContractMapper;
import com.evmtv.epg.response.ContractResponse;
import com.evmtv.epg.service.IContract;
import com.evmtv.epg.statistics.StatisticsRequest;
import com.evmtv.epg.statistics.StatisticsResponse;
import com.evmtv.epg.utils.CollectionUtills;

/**
 * @author Lei<helei@evmtv.com>
 * @data 2013-6-27 下午3:37:24
 */
@Service
public class ContractService implements IContract{

	@Resource 
	TContractMapper contractMapper;
	
	@Override
	public List<ContractResponse> queryContract(TContract contract) {
		TContractExample example = new TContractExample();
		if(null!=contract){
			Criteria criteria = example.createCriteria();
			example.setLimit(contract.getLimit());
			example.setStart(contract.getStart());
			if(StringUtils.hasText(contract.getFchecked())){
				criteria.andFcheckedEqualTo(contract.getFchecked());
			}
			if(StringUtils.hasText(contract.getFtitle())){
				criteria.andFtitleLike("%"+contract.getFtitle()+"%");
			}
			if(StringUtils.hasText(contract.getFstarttime())){
				criteria.andFstarttimeGreaterThanOrEqualTo(contract.getFstarttime());
			}
			if(StringUtils.hasText(contract.getFendtime())){
				criteria.andFendtimeLessThanOrEqualTo(contract.getFendtime());
			}
			if(contract.getFbranchid() != null){
				criteria.andFbranchidEqualTo(contract.getFbranchid());
			}
			if(contract.getFcreateuserid() != null){
				criteria.andFcreateuseridEqualTo(contract.getFcreateuserid());
			}
			
			if(contract.getHolder() != null){
				contract.getHolder().value = contractMapper.countByExample(example);
			}
		}
		
		List<ContractResponse> contractResponses = contractMapper.selectContract(example);
		if(!CollectionUtills.hasElements(contractResponses) && contract.getStart() > 0){
			int start = contract.getStart() - contract.getLimit();
			contract.setStart(start);
			example.setStart(start);
			contractResponses = contractMapper.selectContract(example);
		}
		return contractResponses;
	}

	@Override
	public int addContract(TContractWithBLOBs tContractWithBLOBs) {
		
		return contractMapper.insertSelective(tContractWithBLOBs);
	}

	@Override
	public int updateContract(TContractWithBLOBs tContractWithBLOBs) {
		return contractMapper.updateByPrimaryKeySelective(tContractWithBLOBs);
	}

	@Override
	public TContractWithBLOBs queryByid(Long id) {
		return contractMapper.selectByPrimaryKey(id);
	}

	@Override
	public int delete(List<Long> ids) {
		TContractExample example = new TContractExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdIn(ids);
		return contractMapper.deleteByExample(example);
	}

	@Override
	public List<TContractWithBLOBs> selectIn(List<Long> ids) {
		TContractExample example = new TContractExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdIn(ids);
		return contractMapper.selectByExampleWithBLOBs(example);
	}
	
	@Override
	public List<TContract> query(TContract contract) {
		TContractExample example = new TContractExample();
		if(contract != null){
			Criteria criteria = example.createCriteria();
			if(contract.getFbranchid() != null)
				criteria.andFbranchidEqualTo(contract.getFbranchid());
			if(StringUtils.hasText(contract.getFstarttime())){
				criteria.andFstarttimeGreaterThanOrEqualTo(contract.getFstarttime());
			}
		}
		return contractMapper.selectByExample(example);
	}

	@Override
	public TContractWithBLOBs queryByFguid(String fguid) {
		TContractExample example = new TContractExample();
		Criteria criteria = example.createCriteria();
		criteria.andFguidEqualTo(fguid);
		return contractMapper.selectByExampleWithBLOBs(example).size()>0?contractMapper.selectByExampleWithBLOBs(example).get(0):null;
	}

	@Override
	public List<StatisticsResponse> queryStatistics(StatisticsRequest request) {
		return contractMapper.selectStatistics(request);
	}

	@Override
	public List<StatisticsResponse> queryCompany(StatisticsRequest request) {
		return contractMapper.selectCompany(request);
	}

	@Override
	public List<StatisticsResponse> queryAdv(StatisticsRequest request) {
		return contractMapper.selectAdv(request);
	}
}
