package com.evmtv.epg.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.evmtv.epg.entity.TUser;
import com.evmtv.epg.entity.TUserExample;
import com.evmtv.epg.entity.TUserWithBLOBs;
import com.evmtv.epg.mapper.TUserMapper;
import com.evmtv.epg.service.IUser;
import com.evmtv.epg.utils.CollectionUtills;
@Service
public class UserService implements IUser{
	@Resource
	TUserMapper mapper;
	@Override
	public TUser loginCheck(String fusername) {
		TUserExample example = new TUserExample();
		example.createCriteria().andFusernameEqualTo(fusername);
		List<TUser> users = mapper.selectByExample(example);
		return users.isEmpty() ? null : users.get(0);
	}
	@Override
	public List<TUserWithBLOBs> query(TUser user) {
		TUserExample example = new TUserExample();
		if(user != null){
			example.setLimit(user.getLimit());
			example.setStart(user.getStart());
			if(user.getHolder() != null)
				user.getHolder().value = mapper.countByExample(example);
		}
		List<TUserWithBLOBs> bloBs = mapper.selectByExampleWithBLOBs(example);
		if(user != null && !CollectionUtills.hasElements(bloBs) && user.getStart() > 0){
			int start = user.getStart() - user.getLimit();
			user.setStart(start);
			example.setStart(start);
			bloBs = mapper.selectByExampleWithBLOBs(example);
		}
		
		return bloBs;
	}
	@Override
	public List<TUser> select(TUser user) {
		TUserExample example = new TUserExample();
		if(user != null){
			example.setLimit(user.getLimit());
			example.setStart(user.getStart());
			if(user.getHolder() != null)
				user.getHolder().value = mapper.countByExample(example);
		}
		List<TUser> bloBs = mapper.selectByExample(example);
		if(user != null && !CollectionUtills.hasElements(bloBs) && user.getStart() > 0){
			int start = user.getStart() - user.getLimit();
			user.setStart(start);
			example.setStart(start);
			bloBs = mapper.selectByExample(example);
		}
		
		return bloBs;
	}
	@Override
	public int insert(TUserWithBLOBs user) {
		return mapper.insertSelective(user);
	}
	@Override
	public int delete(List<Long> id) {
		TUserExample example = new TUserExample();
		example.createCriteria().andIdIn(id);
		return mapper.deleteByExample(example);
	}
	@Override
	public int update(TUserWithBLOBs user) {
		
		return mapper.updateByPrimaryKeySelective(user);
	}
	@Override
	public TUserWithBLOBs queryById(Long id) {
		return mapper.selectByPrimaryKey(id);
	}
	/* (non-Javadoc)
	 * @see com.evmtv.epg.service.IUser#query(java.util.List)
	 */
	@Override
	public List<TUser> query(List<Long> ids) {
		// TODO Auto-generated method stub
		TUserExample example = new TUserExample();
		example.createCriteria().andFbranchidIn(ids);
		return mapper.selectByExample(example);
	}

}
