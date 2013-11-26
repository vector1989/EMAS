/**
 *
 * @author fangzhu@evmtv.com
 * @time 2013-7-1 下午5:56:46
 */
package com.evmtv.epg.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.evmtv.epg.entity.TMenuUsergroup;
import com.evmtv.epg.entity.TMenuUsergroupExample;
import com.evmtv.epg.entity.TMenuUsergroupExample.Criteria;
import com.evmtv.epg.mapper.TMenuUsergroupMapper;
import com.evmtv.epg.service.IMenuUsergroup;

/**
 * @author fangzhu@evmtv.com
 * @time 2013-7-1 下午5:56:46
 * @project EAMS
 * @package com.evmtv.epg.impl 
 * @fileName MenuUsergroup.java
 * @enclosing_type 
 * @type_name MenuUsergroup TODO
 */
@Service
public class MenuUsergroup implements IMenuUsergroup {

	@Resource
	TMenuUsergroupMapper mapper;
	/* (non-Javadoc)
	 * @see com.evmtv.epg.service.IMenuUsergroup#insert(com.evmtv.epg.entity.TMenuUsergroup)
	 */
	@Override
	public int insert(TMenuUsergroup group) {
		
		return mapper.insertSelective(group);
	}

	/* (non-Javadoc)
	 * @see com.evmtv.epg.service.IMenuUsergroup#delete(java.lang.Long)
	 */
	@Override
	public int delete(Long groupId) {
		// TODO Auto-generated method stub
		TMenuUsergroupExample example = new TMenuUsergroupExample();
		example.createCriteria().andFusergroupidEqualTo(groupId);
		return mapper.deleteByExample(example);
	}

	/* (non-Javadoc)
	 * @see com.evmtv.epg.service.IMenuUsergroup#delete(java.util.List)
	 */
	@Override
	public int delete(List<Long> id) {
		TMenuUsergroupExample example = new TMenuUsergroupExample();
		example.createCriteria().andIdIn(id);
		return mapper.deleteByExample(example);
	}

	/* (non-Javadoc)
	 * @see com.evmtv.epg.service.IMenuUsergroup#update(com.evmtv.epg.entity.TMenuUsergroup)
	 */
	@Override
	public int update(TMenuUsergroup group) {
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.evmtv.epg.service.IMenuUsergroup#selectByKey(java.lang.Long)
	 */
	@Override
	public TMenuUsergroup selectByKey(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.evmtv.epg.service.IMenuUsergroup#selectByExample(com.evmtv.epg.entity.TMenuUsergroup)
	 */
	@Override
	public List<TMenuUsergroup> selectByExample(TMenuUsergroup group) {
		TMenuUsergroupExample example = new TMenuUsergroupExample();
		Criteria criteria = example.createCriteria();
		if(group != null){
			if(group.getFusergroupid() != null){
				criteria.andFusergroupidEqualTo(group.getFusergroupid());
			}
			if(group.getFmenuid() != null){
				criteria.andFmenuidEqualTo(group.getFmenuid());
			}
		}
		return mapper.selectByExample(example);
	}

	/* (non-Javadoc)
	 * @see com.evmtv.epg.service.IMenuUsergroup#query(java.util.List)
	 */
	@Override
	public List<TMenuUsergroup> query(List<Long> groupIds) {
		TMenuUsergroupExample example = new TMenuUsergroupExample();
		example.createCriteria().andFusergroupidIn(groupIds);
		return mapper.selectByExample(example);
	}

	/* (non-Javadoc)
	 * @see com.evmtv.epg.service.IMenuUsergroup#deleteByUid(java.util.List)
	 */
	@Override
	public int deleteByUid(List<Long> uids) {
		// TODO Auto-generated method stub
		TMenuUsergroupExample example = new TMenuUsergroupExample();
		example.createCriteria().andFusergroupidIn(uids);
		return mapper.deleteByExample(example);
	}

	/* (non-Javadoc)
	 * @see com.evmtv.epg.service.IMenuUsergroup#queryByUidAndMid(java.lang.Long, java.lang.Long)
	 */
	@Override
	public TMenuUsergroup queryByUidAndMid(Long mid, Long gid) {
		// TODO Auto-generated method stub
		TMenuUsergroupExample example = new TMenuUsergroupExample();
		Criteria criteria = example.createCriteria();
		criteria.andFmenuidEqualTo(mid);
		criteria.andFusergroupidEqualTo(gid);
		List<TMenuUsergroup> usergroups = mapper.selectByExample(example);
		return usergroups.isEmpty() ? null : usergroups.get(0);
	}

}
