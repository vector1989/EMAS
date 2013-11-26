package com.evmtv.epg.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.evmtv.epg.entity.TBranch;
import com.evmtv.epg.entity.TBranchExample;
import com.evmtv.epg.entity.TBranchExample.Criteria;
import com.evmtv.epg.entity.TBranchWithBLOBs;
import com.evmtv.epg.mapper.TBranchMapper;
import com.evmtv.epg.service.IBranch;
import com.evmtv.epg.utils.CollectionUtills;

/**
 * 业务管理 TODO
 * 
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type
 */
@Service
public class BranchService implements IBranch {

	@Resource
	TBranchMapper mapper;

	@Override
	public int insert(TBranchWithBLOBs bloBs) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(bloBs);
	}

	@Override
	public int delete(List<Long> id) {
		TBranchExample example = new TBranchExample();
		if (id != null && !id.isEmpty())
			example.createCriteria().andIdIn(id);
		return mapper.deleteByExample(example);
	}

	@Override
	public int update(TBranchWithBLOBs bloBs) {

		return mapper.updateByPrimaryKeySelective(bloBs);
	}

	@Override
	public TBranchWithBLOBs queryById(Long id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TBranchWithBLOBs> queryWithBLOBs(TBranch bloBs) {
		// TODO Auto-generated method stub
		TBranchExample example = new TBranchExample();
		Criteria criteria = example.createCriteria();
		if (bloBs != null) {
			if (StringUtils.hasText(bloBs.getFname())) {
				criteria.andFnameLike("%" + bloBs.getFname() + "%");
			}
			if (bloBs.getLimit() != 0) {
				bloBs.getHolder().value = mapper.countByExample(example);
			}
			example.setStart(bloBs.getStart());
			example.setLimit(bloBs.getLimit());
		}
		List<TBranchWithBLOBs> branchs = mapper
				.selectByExampleWithBLOBs(example);
		if (bloBs != null && !CollectionUtills.hasElements(branchs)
				&& bloBs.getStart() > 1) {
			int start = bloBs.getStart() - bloBs.getLimit();
			bloBs.setStart(start);
			example.setStart(start);
			branchs = mapper.selectByExampleWithBLOBs(example);
			;
		}
		return branchs;
	}

	@Override
	public List<TBranch> query(TBranch branch) {
		// TODO Auto-generated method stub
		TBranchExample example = new TBranchExample();
		if (branch != null) {
			Criteria criteria = example.createCriteria();
			example.setStart(branch.getStart());
			example.setLimit(branch.getLimit());

			if (StringUtils.hasText(branch.getFname())) {
				criteria.andFnameLike("%" + branch.getFname() + "%");
			}
			if (branch.getLimit() != 0) {
				branch.getHolder().value = mapper.countByExample(example);
			}
		}
		return mapper.selectByExample(example);
	}

	@Override
	public List<TBranch> query(List<Long> id) {
		// TODO Auto-generated method stub
		TBranchExample example = new TBranchExample();
		if (CollectionUtills.hasElements(id))
			example.createCriteria().andIdNotIn(id);
		return mapper.selectByExample(example);
	}
}
