package com.evmtv.epg.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.evmtv.epg.entity.TTimePeriod;
import com.evmtv.epg.entity.TTimePeriodExample;
import com.evmtv.epg.entity.TTimePeriodExample.Criteria;
import com.evmtv.epg.mapper.TTimePeriodMapper;
import com.evmtv.epg.service.ITimePeriod;
import com.evmtv.epg.utils.CollectionUtills;
/**
 * 时间段
 * @author fangzhu(fangzhu@evmtv.com)
 * @time 2013-6-14 下午3:52:37
 */
@Service
public class TimePeroidService implements ITimePeriod {

	@Resource
	TTimePeriodMapper mapper;
	@Override
	public int insert(TTimePeriod period) {
		return mapper.insertSelective(period);
	}

	@Override
	public int delete(List<Long> id) {
		TTimePeriodExample example = new TTimePeriodExample();
		example.createCriteria().andIdIn(id);
		return mapper.deleteByExample(example);
	}
	
	@Override
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(TTimePeriod period) {
		return mapper.updateByPrimaryKeySelective(period);
	}

	@Override
	public List<TTimePeriod> query(TTimePeriod period) {
		TTimePeriodExample example = new TTimePeriodExample();
		if(period != null){
			Criteria criteria = example.createCriteria();
			Long advClassid = period.getFadvclassid();
			Long branchid = period.getFbranchid();
			if(advClassid != null){
				criteria.andFadvclassidEqualTo(advClassid);
			}
			if(branchid != null){
				criteria.andFbranchidEqualTo(branchid);
			}
			example.setStart(period.getStart());
			example.setLimit(period.getLimit());
			if(period.getHolder() != null)
				period.getHolder().value = mapper.countByExample(example);
		}
		List<TTimePeriod> periods = mapper.selectBranchTimeByExample(example);
		
		if(period.getHolder() != null)
			if(period.getHolder().value > 0 && period != null && !CollectionUtills.hasElements(periods) && period.getStart() > 0){
				int start = period.getStart() - period.getLimit();
				period.setStart(start);
				example.setStart(start);
				
				periods = mapper.selectBranchTimeByExample(example);
			}
		
		return periods;
	}

	@Override
	public TTimePeriod queryById(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	/*@Override
	public List<TTimePeriod> query(TTimePeriod period, List<Long> fbranchids) {
		TTimePeriodExample example = new TTimePeriodExample();
		if(period != null){
			Criteria criteria = example.createCriteria();
			if(fbranchids != null && !fbranchids.isEmpty()){
				criteria.andFbranchidIn(fbranchids);
			}
			Long advClassid = period.getFadvclassid();
			Long branchid = period.getFbranchid();
			if(advClassid != null){
				criteria.andFadvclassidEqualTo(advClassid);
			}
			if(branchid != null){
				criteria.andFbranchidEqualTo(branchid);
			}
			example.setStart(period.getStart());
			example.setLimit(period.getLimit());
			example.setOrderByClause(" ID DESC");
			if(period.getHolder() != null)
				period.getHolder().value = mapper.countByExample(example);
		}
		return mapper.selectByExample(example);
	}*/
	@Override
	public List<TTimePeriod> query(Long branchid,Long fadvid) {
		TTimePeriodExample example = new TTimePeriodExample();
		if(branchid != null){
			Criteria criteria = example.createCriteria();
			if(branchid != null){
				criteria.andFbranchidEqualTo(branchid);
			}
			if(fadvid != null){
				criteria.andFadvclassidEqualTo(fadvid);
			}
		}
		return mapper.selectByExample(example);
	}

	@Override
	public List<TTimePeriod> queryByIdList(List<Long> id) {
		// TODO Auto-generated method stub
		TTimePeriodExample example = new TTimePeriodExample();
		example.createCriteria().andIdIn(id);
		return mapper.selectByExample(example);
	}

	@Override
	public int selectCheckRepeat(TTimePeriod period) {
		return mapper.selectCheckRepeat(period);
	}

	@Override
	public List<TTimePeriod> query(List<Long> vaIds) {
		TTimePeriodExample example = new TTimePeriodExample();
		Criteria criteria = example.createCriteria();
		if(CollectionUtills.hasElements(vaIds)){
			criteria.andFversionadvidIn(vaIds);
		}

		example.setOrderByClause("TIME_TO_SEC(t_time_period.FStartTime) ASC");	
		return mapper.selectByExample(example);
	}

	@Override
	public List<TTimePeriod> queryByAdvid(Long fadvid,Long rvid,Long vaid) {
		TTimePeriodExample example = new TTimePeriodExample();
		Criteria criteria = example.createCriteria();
		
		criteria.andFadvclassidEqualTo(fadvid);
		criteria.andFversionadvidEqualTo(vaid);
		criteria.andFreleaseversionidEqualTo(rvid);
		
		example.setOrderByClause("TIME_TO_SEC(t_time_period.FStartTime) ASC");	
		return mapper.selectByExample(example);
	}
}
