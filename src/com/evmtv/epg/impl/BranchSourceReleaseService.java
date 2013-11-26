package com.evmtv.epg.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.evmtv.epg.entity.TBranchSourceRelease;
import com.evmtv.epg.entity.TBranchSourceReleaseExample;
import com.evmtv.epg.entity.TBranchSourceReleaseExample.Criteria;
import com.evmtv.epg.entity.TContractAdv;
import com.evmtv.epg.entity.TSourceWithBLOBs;
import com.evmtv.epg.mapper.TBranchSourceReleaseMapper;
import com.evmtv.epg.service.IBranchSourceRelease;
import com.evmtv.epg.utils.CollectionUtills;
import com.evmtv.epg.utils.DateUtils;
/***
 * 分公司广告发布
 * TODO
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type 
 * @project_name EAMS
 * @file_name BranchSourceRelease.java
 * @package_name com.evmtv.epg.impl
 * @date_time 2013年7月26日下午1:03:01
 * @type_name BranchSourceRelease
 */
@Service
public class BranchSourceReleaseService implements IBranchSourceRelease {

	@Resource private TBranchSourceReleaseMapper mapper;
	
	@Override
	public int insert(TBranchSourceRelease bsr) {
		int result = 0;
		if(CollectionUtills.hasElements(bsr.getFsourceidList())){
			List<Long> sourceids = bsr.getFsourceidList();
			List<Long> contractids = bsr.getFcontractidList();
			List<TSourceWithBLOBs> sources = bsr.getSources();
			//当前日期
			String cuuentDate = DateUtils.formatDate();
			if(CollectionUtills.hasElements(sources) && CollectionUtills.hasElements(contractids) && CollectionUtills.hasElements(sourceids))
				for(int i =0,len=sourceids.size(); i < len;i++){
					TSourceWithBLOBs s = sources.get(i);
					bsr.setFsourceid(sourceids.get(i));
					bsr.setFcontractid(contractids.get(i));
					if(DateUtils.dataToLong(cuuentDate) <= DateUtils.dataToLong(s.getFplaydate())){
						bsr.setFisusing("0");
					} 
					
					result += mapper.insertSelective(bsr);                       
				}
		}else{
			result += mapper.insertSelective(bsr);
		}
		
		return result;
	}

	@Override
	public int delete(List<Long> id) {
		// TODO Auto-generated method stub
		TBranchSourceReleaseExample example = new TBranchSourceReleaseExample();
		example.createCriteria().andIdIn(id);
		return mapper.deleteByExample(example);
	}

	@Override
	public int update(TBranchSourceRelease bsr) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(bsr);
	}

	@Override
	public TBranchSourceRelease queryById(Long id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TBranchSourceRelease> query(TBranchSourceRelease bsr) {
		// TODO Auto-generated method stub
		TBranchSourceReleaseExample example = new TBranchSourceReleaseExample();
		Criteria criteria = example.createCriteria();
		if(bsr.getFbranchid() != null){
			criteria.andFbranchidEqualTo(bsr.getFbranchid());
		}
		if(bsr.getFsourceid() != null){
			criteria.andFsourceidEqualTo(bsr.getFsourceid());
		}
		if(StringUtils.hasText(bsr.getQueryStartTime()) && StringUtils.hasText(bsr.getQueryEndTime())){
			criteria.andFcreatetimeBetween(bsr.getQueryStartTime(), bsr.getQueryEndTime());
		}
		if(bsr.getHolder() != null){
			bsr.getHolder().value = mapper.countByExample(example);
		}
		example.setLimit(bsr.getLimit());
		example.setStart(bsr.getStart());
		example.setOrderByClause(" ID DESC");
		
		List<TBranchSourceRelease> releases = mapper.selectByExample(example);
		if(!CollectionUtills.hasElements(releases) && bsr.getStart() > 1){
			int start = bsr.getStart() - bsr.getLimit();
			bsr.setStart(start);
			example.setStart(start);
			releases = mapper.selectByExample(example);
		}
		
		return releases;
	}

	@Override
	public List<Long> queryBranchId(TBranchSourceRelease bsr) {
		TBranchSourceReleaseExample example = new TBranchSourceReleaseExample();
		Criteria criteria = example.createCriteria();
		if(bsr.getFbranchid() != null){
			criteria.andFbranchidEqualTo(bsr.getFbranchid());
		}
		if(bsr.getFsourceid() != null){
			criteria.andFsourceidEqualTo(bsr.getFsourceid());
		}
		if(StringUtils.hasText(bsr.getQueryStartTime()) && StringUtils.hasText(bsr.getQueryEndTime())){
			criteria.andFcreatetimeBetween(bsr.getQueryStartTime(), bsr.getQueryEndTime());
		}

		if(bsr.getHolder() != null){
			bsr.getHolder().value = mapper.countByExample(example);
			example.setLimit(bsr.getLimit());
			example.setStart(bsr.getStart());
		}
		example.setOrderByClause(" ID DESC");
		List<Long> releases = mapper.selectSourceId(example);
		if(!CollectionUtills.hasElements(releases) && bsr.getStart() > 1){
			int start = bsr.getStart() - bsr.getLimit();
			bsr.setStart(start);
			example.setStart(start);
			releases = mapper.selectSourceId(example);
		}
		return releases;
	}

	@Override
	public List<Long> queryBranchId(Long fbranchid) {
		// TODO Auto-generated method stub
		TBranchSourceReleaseExample example = new TBranchSourceReleaseExample();
		Criteria criteria = example.createCriteria();
		if (fbranchid != null) {
			criteria.andFbranchidEqualTo(fbranchid);
		}
		example.setOrderByClause(" ID DESC");

		return mapper.selectSourceId(example);
	}

	@Override
	public String selectByCreateTime(Long fbranchid) {
		// TODO Auto-generated method stub
		TBranchSourceReleaseExample example = new TBranchSourceReleaseExample();
		Criteria criteria = example.createCriteria();
		if (fbranchid != null) {
			criteria.andFbranchidEqualTo(fbranchid);
		}
		return mapper.selectByCreateTime(example);
	}

	@Override
	public int delete(Long sourceId) {
		TBranchSourceReleaseExample example = new TBranchSourceReleaseExample();
		Criteria criteria = example.createCriteria();
		if(sourceId != null){
			criteria.andFsourceidEqualTo(sourceId);
		}
		return mapper.deleteByExample(example);
	}

	@Override
	public List<TContractAdv> selectExpiredContractId(Long fbranchid) {
		// TODO Auto-generated method stub
		return mapper.selectExpiredContractId(fbranchid);
	}

	@Override
	public int updateByExample(TBranchSourceRelease bsr) {
		TBranchSourceReleaseExample example = new TBranchSourceReleaseExample();
		Criteria criteria = example.createCriteria();
		if(CollectionUtills.hasElements(bsr.getFcontractidList())){
			criteria.andFcontractidIn(bsr.getFcontractidList());
		}
		if(CollectionUtills.hasElements(bsr.getFsourceidList())){
			criteria.andFsourceidIn(bsr.getFsourceidList());
		}
		if(bsr.getFbranchid() != null){
			criteria.andFbranchidEqualTo(bsr.getFbranchid());
		}
		
		return mapper.updateByExampleSelective(bsr, example);
	}

	@Override
	public List<TBranchSourceRelease> selectByBranchid(Long fbranchid) {
		// TODO Auto-generated method stub
		TBranchSourceReleaseExample example = new TBranchSourceReleaseExample();
		Criteria criteria = example.createCriteria();
		if(fbranchid != null){
			criteria.andFbranchidEqualTo(fbranchid);
		}
		example.setGroupByClause("FVersion");
		return mapper.selectByExample(example);
	}

	@Override
	public List<TSourceWithBLOBs> query(List<TSourceWithBLOBs> sources,Long branchid) {
		// TODO Auto-generated method stub
		
		List<Long> sids = new ArrayList<Long>();
		for(TSourceWithBLOBs s : sources){
			sids.add(s.getId());
		}
		
		TBranchSourceReleaseExample example = new TBranchSourceReleaseExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdIn(sids);
		criteria.andFbranchidEqualTo(branchid);
		
		List<TBranchSourceRelease> list = mapper.selectByExample(example);
		for(TBranchSourceRelease r : list){
			for(TSourceWithBLOBs s : sources){
				if(r.getFsourceid().toString().equals(s.getId().toString())){
					sources.remove(s);
					break;
				}
			}
		}
		
		return sources;
	}

	@Override
	public int deleteBySourceId(List<Long> sourceId) {
		TBranchSourceReleaseExample example = new TBranchSourceReleaseExample();
		example.createCriteria().andFsourceidIn(sourceId);
		return mapper.deleteByExample(example);
	}
}
