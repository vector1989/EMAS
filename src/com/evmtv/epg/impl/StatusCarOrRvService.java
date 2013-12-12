/**
 * @project_name EAMS
 * @file_name StatusCarOrRvService.java
 * @package_name com.evmtv.epg.impl
 * @date_time 2013年12月2日下午6:11:07
 * @type_name StatusCarOrRvService
 */
package com.evmtv.epg.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.evmtv.epg.entity.TStatusCarOrRv;
import com.evmtv.epg.entity.TStatusCarOrRvExample;
import com.evmtv.epg.entity.TStatusCarOrRvExample.Criteria;
import com.evmtv.epg.mapper.TStatusCarOrRvMapper;
import com.evmtv.epg.response.MyTask;
import com.evmtv.epg.service.IStatusCarOrRv;
import com.evmtv.epg.utils.DateUtils;

/**
 * 
 * <p>Title: </p> 
 * <p>Description: </p> 
 * <p>Date: 2013年12月2日下午6:11:07</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * <p>Company: www.evmtv.com</p> 
 * @author fangzhu@evmtv.com
 * @version 1.0 
 */
@Service
public class StatusCarOrRvService implements IStatusCarOrRv {

	@Resource TStatusCarOrRvMapper mapper;
	/* (non-Javadoc)
	 * @see com.evmtv.epg.service.IStatusCarOrRv#insert(com.evmtv.epg.entity.TStatusCarOrRv)
	 * @enclosing_method insert
	 * @data_time 2013年12月2日 下午6:11:07
	 */
	@Override
	public int insert(TStatusCarOrRv s) {
		// TODO Auto-generated method stub
		s.setFupdatetime(DateUtils.getCurrentTime());
		return mapper.insertSelective(s);
	}

	/* (non-Javadoc)
	 * @see com.evmtv.epg.service.IStatusCarOrRv#delete(java.util.List)
	 * @enclosing_method delete
	 * @data_time 2013年12月2日 下午6:11:07
	 */
	@Override
	public int delete(List<Long> id) {
		// TODO Auto-generated method stub
		TStatusCarOrRvExample example = new TStatusCarOrRvExample();
		example.createCriteria().andIdIn(id);
		return mapper.deleteByExample(example);
	}

	/* (non-Javadoc)
	 * @see com.evmtv.epg.service.IStatusCarOrRv#update(com.evmtv.epg.entity.TStatusCarOrRv)
	 * @enclosing_method update
	 * @data_time 2013年12月2日 下午6:11:07
	 */
	@Override
	public int update(TStatusCarOrRv record) {
		// TODO Auto-generated method stub
		TStatusCarOrRvExample example = new TStatusCarOrRvExample();
		Criteria criteria = example.createCriteria();
		if(record.getFcontractadvresourceid() != null)
			criteria.andFcontractadvresourceidEqualTo(record.getFcontractadvresourceid());
		if(record.getFcontractid() != null){
			criteria.andFcontractidEqualTo(record.getFcontractid());
		}
		if(record.getFreleaseversionid() != null){
			criteria.andFreleaseversionidEqualTo(record.getFreleaseversionid());
		}
		record.setFupdatetime(DateUtils.getCurrentTime());
		return mapper.updateByExampleSelective(record, example);
	}

	/* (non-Javadoc)
	 * @see com.evmtv.epg.service.IStatusCarOrRv#selectByKey(java.lang.Long)
	 * @enclosing_method selectByKey
	 * @data_time 2013年12月2日 下午6:11:07
	 */
	@Override
	public TStatusCarOrRv selectByKey(Long id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.evmtv.epg.service.IStatusCarOrRv#selectByExample(com.evmtv.epg.entity.TAdv)
	 * @enclosing_method selectByExample
	 * @data_time 2013年12月2日 下午6:11:07
	 */
	@Override
	public List<TStatusCarOrRv> selectByExample(TStatusCarOrRv record) {
		// TODO Auto-generated method stub
		TStatusCarOrRvExample example = new TStatusCarOrRvExample();
//		Criteria criteria = example.createCriteria();
	
		return mapper.selectByExample(example);
	}

	/* (non-Javadoc)
	 * @see com.evmtv.epg.service.IStatusCarOrRv#delete(com.evmtv.epg.entity.TStatusCarOrRv)
	 * @enclosing_method delete
	 * @data_time 2013年12月2日 下午6:12:47
	 */
	@Override
	public int delete(TStatusCarOrRv s) {
		// TODO Auto-generated method stub
		TStatusCarOrRvExample example = new TStatusCarOrRvExample();
		return mapper.deleteByExample(example);
	}

	/* (non-Javadoc)
	 * @see com.evmtv.epg.service.IStatusCarOrRv#selectMyTask(com.evmtv.epg.entity.TStatusCarOrRv)
	 * @enclosing_method selectMyTask
	 * @data_time 2013年12月7日 下午12:15:54
	 */
	@Override
	public List<MyTask> selectMyTask(TStatusCarOrRv record) {
		// TODO Auto-generated method stub
		if(record.getHolder() != null){
			record.getHolder().value = mapper.countMyTask(record);
		}
		return mapper.selectMyTask(record);
	}

	/* (non-Javadoc)
	 * @see com.evmtv.epg.service.IStatusCarOrRv#updateByKey(com.evmtv.epg.entity.TStatusCarOrRv)
	 * @enclosing_method updateByKey
	 * @data_time 2013年12月7日 下午2:57:24
	 */
	@Override
	public int updateByKey(TStatusCarOrRv s) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(s);
	}

	/* (non-Javadoc)
	 * @see com.evmtv.epg.service.IStatusCarOrRv#countMyTask(com.evmtv.epg.entity.TStatusCarOrRv)
	 * @enclosing_method countMyTask
	 * @data_time 2013年12月7日 下午3:40:42
	 */
	@Override
	public Integer countMyTask(TStatusCarOrRv record) {
		// TODO Auto-generated method stub
		return mapper.countMyTask(record);
	}

}
