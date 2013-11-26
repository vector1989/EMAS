package com.evmtv.epg.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.evmtv.epg.entity.TContractAdv;
import com.evmtv.epg.entity.TContractAdvExample;
import com.evmtv.epg.entity.TContractAdvExample.Criteria;
import com.evmtv.epg.mapper.TContractAdvMapper;
import com.evmtv.epg.service.IContractAdv;
import com.evmtv.epg.utils.CollectionUtills;
/**
 * 合同广告位关联
 * TODO
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type 
 * @project_name EAMS
 * @file_name ContractAdvService.java
 * @package_name com.evmtv.epg.impl
 * @date_time 2013-8-26下午2:51:00
 * @type_name ContractAdvService
 */
@Service
public class ContractAdvService implements IContractAdv {
	
	@Resource TContractAdvMapper mapper;
	
	@Override
	public int insert(TContractAdv contractAdv) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(contractAdv);
	}

	@Override
	public int delete(List<Long> id) {
		TContractAdvExample example = new TContractAdvExample();
		example.createCriteria().andIdIn(id);
		return mapper.deleteByExample(example);
	}
	
	@Override
	public int update(TContractAdv contractAdv) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(contractAdv);
	}

	@Override
	public TContractAdv selectByKey(Long id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TContractAdv> selectByExample(TContractAdv contractAdv) {
		// TODO Auto-generated method stub
		TContractAdvExample example = new TContractAdvExample();
		
		return mapper.selectByExample(example);
	}

	
	@Override
	public int deleteConadvByFcontractid(Long fcontractid) {
		TContractAdvExample example = new TContractAdvExample();
		Criteria criteria = example.createCriteria();
		criteria.andFcontractidEqualTo(fcontractid);
		return mapper.deleteByExample(example);
	}

	@Override
	public boolean checkAdvExist(TContractAdv adv) {

		TContractAdvExample example = new TContractAdvExample();
		Criteria criteria = example.createCriteria();
		criteria.andFcontractidEqualTo(adv.getFcontractid());
		criteria.andFadvidEqualTo(adv.getFadvid());
		criteria.andFdefinitionEqualTo(adv.getFdefinition());
		
		return CollectionUtills.hasElements(mapper.selectByExample(example));
	}

	@Override
	public List<TContractAdv> selectByContractId(Long fcontractid) {
		// TODO Auto-generated method stub
		return mapper.selectByContractId(fcontractid);
	}

	@Override
	public List<TContractAdv> selectResourceByContractId(Long fcontractid) {
		// TODO Auto-generated method stub
		return mapper.selectResourceByContractId(fcontractid);
	}

	@Override
	public List<TContractAdv> selectByFbranchId(Long fbranchid) {
		// TODO Auto-generated method stub
		return mapper.selectByFbranchId(fbranchid);
	}

	@Override
	public List<TContractAdv> selectAdvByFbranchId(Long fbranchid) {
		// TODO Auto-generated method stub
		return mapper.selectAdvByFbranchId(fbranchid);
	}

	@Override
	public TContractAdv selectObjectByContractId(Long fcontractadvid) {
		// TODO Auto-generated method stub
		return mapper.selectObjectByContractId(fcontractadvid);
	}

	@Override
	public int delete(TContractAdv adv) {
		TContractAdvExample example = new TContractAdvExample();
		Criteria criteria = example.createCriteria();
		if(CollectionUtills.hasElements(adv.getAdvIdList())){
			criteria.andFadvidNotIn(adv.getAdvIdList());
		}
		if(adv.getFcontractid() != null){
			criteria.andFcontractidEqualTo(adv.getFcontractid());
		}
		return mapper.deleteByExample(example);
	}

}