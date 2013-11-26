package com.evmtv.epg.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.evmtv.epg.entity.TUserNode;
import com.evmtv.epg.entity.TUserNodeExample;
import com.evmtv.epg.entity.TUserNodeExample.Criteria;
import com.evmtv.epg.mapper.TUserNodeMapper;
import com.evmtv.epg.service.IUserNode;
@Service
public class UserNodeService implements IUserNode {
	@Resource TUserNodeMapper mapper;
	@Override
	public int insert(TUserNode node) {
		return mapper.insertSelective(node);
	}

	@Override
	public int delete(List<Long> id) {
		// TODO Auto-generated method stub
		TUserNodeExample example = new TUserNodeExample();
		example.createCriteria().andIdIn(id);
		return mapper.deleteByExample(example);
	}

	@Override
	public int update(TUserNode node) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(node);
	}

	@Override
	public TUserNode selectByKey(Long id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TUserNode> selectByExample(TUserNode node) {
		// TODO Auto-generated method stub
		TUserNodeExample example = new TUserNodeExample();
		Criteria criteria = example.createCriteria();
		if(node != null){
			if(node.getFbranchid() != null){
				criteria.andFbranchidEqualTo(node.getFbranchid());
			}
			if(node.getFusergroupid() != null){
				criteria.andFusergroupidEqualTo(node.getFusergroupid());
			}
			if(StringUtils.hasLength(node.getFstatus())){
				criteria.andForderEqualTo(0);
			}
		}
		return mapper.selectByExampleWithBranch(example);
	}

	@Override
	public int count(Long branchid) {
		TUserNodeExample example = new TUserNodeExample();
		Criteria criteria = example.createCriteria();
		if(branchid != null){
			criteria.andFbranchidEqualTo(branchid);
		}
		return mapper.countByExample(example);
	}

	@Override
	public int order(TUserNode userNode) {
		// TODO Auto-generated method stub
		if(userNode.getFbranchid() != null){
			TUserNodeExample example = new TUserNodeExample();
			Criteria criteria = example.createCriteria();
			criteria.andFbranchidEqualTo(userNode.getFbranchid());
			List<TUserNode> nodes = mapper.selectByExample(example);
			for(TUserNode n : nodes){
				if(userNode.getForder() > n.getForder()){
					
				}
			}
		}
		return 0;
	}

	@Override
	public List<TUserNode> queryByBranchId(Long fbranchid) {
		TUserNodeExample example = new TUserNodeExample();
		Criteria criteria = example.createCriteria();
		if(fbranchid != null){
			criteria.andFbranchidEqualTo(fbranchid);
		}
		return mapper.selectByExample(example);
	}

	@Override
	public List<TUserNode> selectByExample(Long fbranchid) {
		// TODO Auto-generated method stub
		TUserNodeExample example = new TUserNodeExample();
		Criteria criteria = example.createCriteria();
			if(fbranchid != null){
				criteria.andFbranchidEqualTo(fbranchid);
		}
		return mapper.selectByExampleWithBranch(example);
	}
}
