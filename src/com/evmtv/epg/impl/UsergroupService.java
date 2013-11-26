/**
 *
 * @author fangzhu@evmtv.com
 * @time 2013-7-1 下午4:46:43
 */
package com.evmtv.epg.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.evmtv.epg.entity.TUsergroup;
import com.evmtv.epg.entity.TUsergroupExample;
import com.evmtv.epg.mapper.TUsergroupMapper;
import com.evmtv.epg.service.IUserGroup;
import com.evmtv.epg.utils.CollectionUtills;

/**
 * @author fangzhu@evmtv.com
 * @time 2013-7-1 下午4:46:43
 * @project EAMS
 * @package com.evmtv.epg.impl 
 * @fileName UsergroupService.java
 * @enclosing_type 
 * @type_name UsergroupService TODO
 */
@Service
public class UsergroupService implements IUserGroup {

	@Resource
	TUsergroupMapper mapper;
	/* (non-Javadoc)
	 * @see com.evmtv.epg.service.IUserGroup#insert(com.evmtv.epg.entity.TUsergroup)
	 */
	@Override
	public int insert(TUsergroup group) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(group);
	}

	/* (non-Javadoc)
	 * @see com.evmtv.epg.service.IUserGroup#delete(java.util.List)
	 */
	@Override
	public int delete(List<Long> id) {
		// TODO Auto-generated method stub
		TUsergroupExample example = new TUsergroupExample();
		example.createCriteria().andIdIn(id);
		return mapper.deleteByExample(example);
	}

	/* (non-Javadoc)
	 * @see com.evmtv.epg.service.IUserGroup#update(com.evmtv.epg.entity.TUsergroup)
	 */
	@Override
	public int update(TUsergroup group) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(group);
	}

	/* (non-Javadoc)
	 * @see com.evmtv.epg.service.IUserGroup#selectByKey(java.lang.Long)
	 */
	@Override
	public TUsergroup selectByKey(Long id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.evmtv.epg.service.IUserGroup#selectByExample(com.evmtv.epg.entity.TUsergroup)
	 */
	@Override
	public List<TUsergroup> selectByExample(TUsergroup group) {
		// TODO Auto-generated method stub
		TUsergroupExample example = new TUsergroupExample();
		if(group != null){
			example.setLimit(group.getLimit());
			example.setStart(group.getStart());
			if(group.getHolder() != null)
				group.getHolder().value = mapper.countByExample(example);
		}
		List<TUsergroup> usergroups = mapper.selectByExample(example);
		if(group != null && !CollectionUtills.hasElements(usergroups) && group.getStart() > 0){
			int start = group.getStart() - group.getLimit();
			group.setStart(start);
			example.setStart(start);
			usergroups = mapper.selectByExample(example);
		}
		
		return usergroups;
	}
}