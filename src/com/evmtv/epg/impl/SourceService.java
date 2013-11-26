package com.evmtv.epg.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.evmtv.epg.entity.TSource;
import com.evmtv.epg.entity.TSourceExample;
import com.evmtv.epg.entity.TSourceExample.Criteria;
import com.evmtv.epg.entity.TSourceWithBLOBs;
import com.evmtv.epg.mapper.TSourceMapper;
import com.evmtv.epg.service.ISource;
import com.evmtv.epg.utils.CollectionUtills;
/**
 * 资源
 * @author fangzhu(fangzhu@evmtv.com)
 * @time 2013-6-4 下午1:22:02
 */
@Service
public class SourceService implements ISource {
	@Resource
	TSourceMapper mapper;
	
	@Override
	public int insert(TSourceWithBLOBs source) {
		
		return mapper.insertSelective(source);
	}

	@Override
	public int intsertBatch(List<TSourceWithBLOBs> sources) {
		int result = 0;
		for(TSourceWithBLOBs source : sources){
			result += mapper.insertSelective(source);
		}
		return result;
	}

	@Override
	public int update(TSourceWithBLOBs bloBs) {
		return mapper.updateByPrimaryKeySelective(bloBs);
	}

	@Override
	public int delete(List<Long> id) {
		TSourceExample example = new TSourceExample();
		Criteria criteria  = example.createCriteria();
		criteria.andIdIn(id);
		return mapper.deleteByExample(example);
	}
	@Override
	public int delete(Long advId){
		TSourceExample example = new TSourceExample();
		Criteria criteria  = example.createCriteria();
		criteria.andFadvidEqualTo(advId);
		return mapper.deleteByExample(example);
	}

	@Override
	public TSourceWithBLOBs queryById(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TSourceWithBLOBs> query(TSourceWithBLOBs source) {
		TSourceExample example = new TSourceExample();
		if(source != null){
			Criteria criteria = example.createCriteria();
			Long advid = source.getFadvid();
			String checked = source.getFchecked();
			
			if(advid != null){
				criteria.andFadvidEqualTo(advid);
			}
			if(StringUtils.hasText(checked)){
				criteria.andFcheckedEqualTo(checked);
			}
			if(StringUtils.hasText(source.getQueryStartTime()) && StringUtils.hasLength(source.getQueryEndTime())){
				criteria.andFcreatetimeBetween(source.getQueryStartTime(), source.getQueryEndTime());
			}
			if(StringUtils.hasText(source.getQueryKeyWord())){
				criteria.andFnameLike("%"+source.getQueryKeyWord()+"%");
			}
			if(StringUtils.hasText(source.getFflag())){
				criteria.andFflagEqualTo(source.getFflag());
			}
			if(StringUtils.hasText(source.getFexpand())){
				criteria.andFExpandNotLike("%".concat(source.getFexpand()).concat("%"));
			}
			
			example.setLimit(source.getLimit());
			example.setStart(source.getStart());
			example.setOrderByClause(" ID DESC");
			
			if(source.getHolder() != null)
				source.getHolder().value = mapper.countByExample(example);
		}
		
		List<TSourceWithBLOBs> bloBs = mapper.selectByExampleWithBLOBs(example);
		if(!CollectionUtills.hasElements(bloBs) && source.getStart() > 0){
			int start = source.getStart() - source.getLimit();
			source.setStart(start);
			example.setStart(start);
			
			bloBs = mapper.selectByExampleWithBLOBs(example);
		}
		
		return bloBs;
	}

	@Override
	public int deleteAdv(List<Long> advId) {
		TSourceExample example = new TSourceExample();
		Criteria criteria  = example.createCriteria();
		criteria.andFadvidIn(advId);
		return mapper.deleteByExample(example);
	}

	/* (non-Javadoc)
	 * @see com.evmtv.epg.service.ISource#delete(java.lang.String)
	 */
	@Override
	public int delete(String guid) {
		TSourceExample example = new TSourceExample();
		example.createCriteria().andFguidEqualTo(guid);
		
		return mapper.deleteByExample(example);
	}

	/* (non-Javadoc)
	 * @see com.evmtv.epg.service.ISource#query(java.util.List)
	 */
	@Override
	public List<TSource> query(List<Long> advId) {
		// TODO Auto-generated method stub
		TSourceExample example = new TSourceExample();
		example.createCriteria().andFadvidIn(advId);
		
		return mapper.selectByExample(example);
	}

	/* (non-Javadoc)
	 * @see com.evmtv.epg.service.ISource#queryByTimeId(java.util.List)
	 */
	@Override
	public List<TSource> queryByTimeId(List<Long> timesId) {
		// TODO Auto-generated method stub
		TSourceExample example = new TSourceExample();
		example.createCriteria().andFtimeperiodidIn(timesId);
		return mapper.selectByExample(example);
	}
	
	@Override
	public List<TSource> queryByTimeId(Long timeId) {
		// TODO Auto-generated method stub
		TSourceExample example = new TSourceExample();
		example.createCriteria().andFtimeperiodidEqualTo(timeId);
		return mapper.selectByExample(example);
	}


	@Override
	public List<TSource> query(TSource source) {
		
		if(source.getHolder() != null)
			source.getHolder().value = mapper.count(source);
		return mapper.select(source);
	}

	@Override
	public List<TSourceWithBLOBs> selectByBranchidList(List<Long> branchid) {
		// TODO Auto-generated method stub
		TSourceExample example = new TSourceExample();
		Criteria criteria = example.createCriteria();
		criteria.andFbranchidIn(branchid);
		return mapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public List<TSourceWithBLOBs> selectByIdList(List<Long> id) {
		// TODO Auto-generated method stub
		TSourceExample example = new TSourceExample();
		example.createCriteria().andIdIn(id);
		return mapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public int update(List<Long> id, Long branchid,String fflag) {
		// TODO Auto-generated method stub
		TSourceWithBLOBs source = new TSourceWithBLOBs();
		if(StringUtils.hasText(fflag)){
			source.setFflag(fflag);
		}
		TSourceExample example = new TSourceExample();
		Criteria criteria  = example.createCriteria();
		if(CollectionUtills.hasElements(id)){
			criteria.andIdIn(id);
		}
		if(branchid != null){
			criteria.andFbranchidEqualTo(branchid);
		}
		return mapper.updateByExampleSelective(source, example);
	}

	/*@Deprecated
	@Override
	public List<TSourceWithBLOBs> selectByBranchid(Long branchid,List<Long> badvid,String createTime) {
		// TODO Auto-generated method stub
		StringBuilder temp = new StringBuilder();
		StringBuilder daid = new StringBuilder();//广告位
		temp.append(" ((FBranchId = ").append(branchid);
		temp.append(" and FAdvId IN (");
		for(Long aid : badvid){
			daid.append(aid).append(",");
		}
		daid = daid.deleteCharAt(daid.length()-1);
		temp.append(daid).append(")) OR ( FBranchId=1 and FAdvId NOT IN (");
		temp.append(daid).append(")))");
		if(StringUtils.hasText(createTime)){
			temp.append(" AND (FCreateTime > '").append(createTime).append("' OR FUpdateTime > '").append(createTime).append("')");
		}
		TSource source = new TSource();
		source.setTemp(temp.toString());
		
		return mapper.selectRelease(source);
	}*/

	@Override
	public List<TSourceWithBLOBs> selectRelease(TSource source) {
		if(source.getHolder() != null){
			source.getHolder().value = mapper.countViewRelease(source);
		}
		
		return mapper.selectViewRelease(source);
	}
	@Override
	public TSource selectById(Long Id) {
		// TODO Auto-generated method stub
		return mapper.selectById(Id);
	}

	@Override
	public List<TSource> selectMyTask(TSource source) {
		if(source.getHolder() != null){
			source.getHolder().value = mapper.countMyTask(source);
		}
		return mapper.selectMyTask(source);
	}

	@Override
	public List<String> selectByfguid(List<Long> ids) {
		TSourceExample example = new TSourceExample();
		example.createCriteria().andIdIn(ids);
		return mapper.selectByfguid(example);
	}

	@Override
	public List<TSource> queryByAdvOrTimeperiod(TSource source) {
		// TODO Auto-generated method stub
		TSourceExample example = new TSourceExample();
		Criteria criteria = example.createCriteria();
		
		if(source.getFadvid() != null){
			criteria.andFadvidEqualTo(source.getFadvid());
		}
		if(source.getFtimeperiodid() != null){
			criteria.andFtimeperiodidEqualTo(source.getFtimeperiodid());
		}
		if(source.getFbranchid() != null){
			criteria.andFbranchidEqualTo(source.getFbranchid());
		}
		if(StringUtils.hasText(source.getFdefinition())){
			criteria.andFdefinitionEqualTo(source.getFdefinition());
		}
		
		if(source.getHolder() != null){
			example.setLimit(source.getLimit());
			example.setStart(source.getStart());
			source.getHolder().value = mapper.countByExample(example);
		}
		
		return mapper.selectByExample(example);
	}

	@Override
	public List<TSource> selectSourceRelease(TSource source) {
		// TODO Auto-generated method stub
		if(source.getHolder() != null){
			source.getHolder().value = mapper.countSourceRelease(source);
		}
		return mapper.selectSourceRelease(source);
	}

	@Override
	public List<Long> querySidByRid(Long rid) {
		// TODO Auto-generated method stub
		return mapper.selectSidByRid(rid);
	}
}
