package com.evmtv.epg.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.evmtv.epg.utils.StringUtils;

import com.evmtv.epg.entity.TNode;
import com.evmtv.epg.entity.TNodeExample;
import com.evmtv.epg.entity.TNodeExample.Criteria;
import com.evmtv.epg.mapper.TNodeMapper;
import com.evmtv.epg.service.INode;
import com.evmtv.epg.utils.CollectionUtills;
@Service
public class NodeService implements INode {
	
	@Resource
	TNodeMapper mapper;
	
	@Override
	public int insert(TNode node) {
		return mapper.insertSelective(node);
	}

	@Override
	public int delete(List<Long> id) {
		TNodeExample example = new TNodeExample();
		example.createCriteria().andIdIn(id);
		return mapper.deleteByExample(example);
	}

	@Override
	public int update(TNode node) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(node);
	}

	@Override
	public TNode selectByKey(Long id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TNode> selectByExample(TNode node) {
		TNodeExample example = new TNodeExample();
		Criteria criteria = example.createCriteria();
		criteria.andForderGreaterThan(-1);
		example.setOrderByClause("t_node.FOrder ASC");
		if(node != null){
			if(StringUtils.hasText(node.getFischecked()))
				criteria.andFischeckedEqualTo(node.getFischecked());
			if(node.getFusergroupid() != null)
				criteria.andFusergroupidEqualTo(node.getFusergroupid());
			if(node.getForder() != null && node.getForder() > 0){
				example.setOrderByClause("t_node.FOrder DESC");
				criteria.andForderLessThan(node.getForder());
			}
			if(node.getFtype() != null){
				if(StringUtils.hasText(node.getFtype())){
					criteria.andFtypeEqualTo(node.getFtype());
				}else{
					criteria.andFtypeNotEqualTo(2);
				}
			}
			if(node.getHolder() != null){
				example.setStart(node.getStart());
				example.setLimit(node.getLimit());
			}
		}
		return mapper.selectByExample(example);
	}

	@Override
	public int maxOrder() {
		TNodeExample example = new TNodeExample();
		example.setOrderByClause("t_node.FOrder DESC");
		example.setStart(0);
		example.setLimit(1);
		List<TNode> nodes = mapper.selectByExample(example);
		int order = 0;
		if(CollectionUtills.hasElements(nodes)){
			order = nodes.get(0).getForder();
		}
		return order;
	}

	@Override
	public TNode selectByNode(TNode node) {
		TNodeExample example = new TNodeExample();
		Criteria criteria = example.createCriteria();
		if(StringUtils.hasText(node.getFischecked()))
			criteria.andFischeckedEqualTo(node.getFischecked());
		criteria.andFtypeEqualTo(node.getFtype());
		if(node.getForder() != null){
			criteria.andForderEqualTo(node.getForder());
		}
		if(StringUtils.hasText(node.getFisprovincecompany())){
			criteria.andFisprovincecompanyEqualTo(node.getFisprovincecompany());
		}
		example.setOrderByClause("t_node.FOrder ASC");
		example.setStart(0);
		example.setLimit(1);
		List<TNode> nodes = mapper.selectByExample(example);
		
		TNode n = null;
		if(CollectionUtills.hasElements(nodes)){
			n = nodes.get(0);
		}
		return n;
	}
	
	@Override
	public TNode selectNodeByUsergroupId(Long ugid){
		TNodeExample example = new TNodeExample();
		Criteria criteria = example.createCriteria();
		criteria.andFusergroupidEqualTo(ugid);
		List<TNode> nodes = mapper.selectByExample(example);
		
		return CollectionUtills.hasElements(nodes) ? nodes.get(0) : null;
	}
}