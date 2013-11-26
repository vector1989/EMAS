package com.evmtv.epg.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.evmtv.epg.entity.TAdv;
import com.evmtv.epg.entity.TAdvExample;
import com.evmtv.epg.entity.TAdvExample.Criteria;
import com.evmtv.epg.entity.TAdvWithBLOBs;
import com.evmtv.epg.mapper.TAdvMapper;
import com.evmtv.epg.service.IAdv;
import com.evmtv.epg.utils.CollectionUtills;
import com.evmtv.epg.utils.StringUtils;

/**
 * 广告管理
 * 
 * @author fangzhu(fangzhu@evmtv.com)
 * @time 2013-6-17 下午6:41:13
 */
@Service
public class AdvService implements IAdv {

	@Resource
	TAdvMapper mapper;

	@Override
	public int insert(TAdvWithBLOBs adv) {
		return mapper.insertSelective(adv);
	}

	@Override
	public int delete(List<Long> id) {
		TAdvExample example = new TAdvExample();
		example.createCriteria().andIdIn(id);
		return mapper.deleteByExample(example);
	}

	@Override
	public int update(TAdvWithBLOBs adv) {
		return mapper.updateByPrimaryKeySelective(adv);
	}

	@Override
	public TAdvWithBLOBs selectByKey(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TAdv> selectByExample(TAdv adv) {
		TAdvExample example = new TAdvExample();

		if (adv != null) {
			Criteria criteria = example.createCriteria();
			String checked = adv.getFchecked();
			if (StringUtils.hasText(checked)) {
				criteria.andFcheckedEqualTo(checked);
			}
			if (adv.getFbranchid() != null) {
				criteria.andFbranchidEqualTo(adv.getFbranchid());
			}
			if (StringUtils.hasText(adv.getFdefinition())){
				criteria.andFdefinitionEqualTo(adv.getFdefinition());
			}
			if (adv.getFpositionid() != null){
				criteria.andFpositionidEqualTo(adv.getFpositionid());
			}
			example.setLimit(adv.getLimit());
			example.setStart(adv.getStart());
			example.setOrderByClause(" ID DESC");
			if (adv.getHolder() != null)
				adv.getHolder().value = mapper.countByExample(example);
		}
		List<TAdv> advs = mapper.selectBranchAdvByExample(example);

		if (adv != null && adv.getHolder() != null && adv.getHolder().value>0)
			if(!CollectionUtills.hasElements(advs) && adv.getStart() > 1){
				int start = adv.getStart() - adv.getLimit();
				adv.setStart(start);
				example.setStart(start);
				advs = mapper.selectBranchAdvByExample(example);
			}
		return advs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.evmtv.epg.service.IAdv#select(java.util.List)
	 */
	@Override
	public List<TAdv> select(List<Long> id) {
		TAdvExample example = new TAdvExample();
		example.createCriteria().andIdIn(id);
		return mapper.selectByExample(example);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.evmtv.epg.service.IAdv#checked(java.util.List,
	 * com.evmtv.epg.entity.TAdvWithBLOBs)
	 */
	@Override
	public int checked(List<Long> id, TAdvWithBLOBs adv) {
		TAdvExample example = new TAdvExample();
		example.createCriteria().andIdIn(id);
		return mapper.updateByExampleSelective(adv, example);
	}

	@Override
	public List<TAdv> query(List<Long> fadvclassid) {
		// TODO Auto-generated method stub
		TAdvExample example = new TAdvExample();
		if (fadvclassid != null && !fadvclassid.isEmpty())
			example.createCriteria().andFadvclassidIn(fadvclassid);
		return mapper.selectByExample(example);
	}

	@Override
	public List<TAdv> query(Long fbranchid) {
		TAdvExample example = new TAdvExample();
		Criteria criteria = example.createCriteria();
		if (fbranchid != null)
			criteria.andFbranchidEqualTo(fbranchid);
		criteria.andFdeletedEqualTo("0");
		return mapper.selectByExample(example);
	}

	@Override
	public List<TAdv> selectByFadvClassIdAndFbranchid(Long fadvclassid, Long fbranchid) {
		TAdvExample example = new TAdvExample();
		Criteria criteria = example.createCriteria();
		criteria.andFadvclassidEqualTo(fadvclassid);
		criteria.andFbranchidEqualTo(fbranchid);
		return mapper.selectByExample(example);
	}

	@Override
	public int updateDelete(Long fbranchid, List<Long> ids, String delete,boolean notIn) {
		TAdvExample example = new TAdvExample();
		Criteria criteria = example.createCriteria();
		if(notIn)
			criteria.andFadvclassidNotIn(ids);
		else
			criteria.andFadvclassidIn(ids);
		criteria.andFbranchidEqualTo(fbranchid);
		
		TAdvWithBLOBs adv = new TAdvWithBLOBs();
		adv.setFdeleted(delete);
		return mapper.updateByExampleSelective(adv, example);
	}

	@Override
	public List<TAdv> query(Long fbranchid, String definition) {
		TAdvExample example = new TAdvExample();
		Criteria criteria = example.createCriteria();
		criteria.andFbranchidEqualTo(fbranchid);
		if(StringUtils.hasText(definition))
			criteria.andFdefinitionEqualTo(definition);
		criteria.andFdeletedEqualTo("0");
		example.setOrderByClause(" t_adv.FPositionId ASC");
		return mapper.selectByExample(example);
	}

	@Override
	public int batchInsert(List<TAdv> advs) {
		// TODO Auto-generated method stub
		return mapper.batchInsert(advs);
	}

}
