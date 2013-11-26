package com.evmtv.epg.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.evmtv.epg.entity.TSourceChannels;
import com.evmtv.epg.entity.TSourceChannelsExample;
import com.evmtv.epg.mapper.TSourceChannelsMapper;
import com.evmtv.epg.request.SourceChannelsRequest;
import com.evmtv.epg.service.ISourceChannels;

/**
 * 广告频道关联
 * 
 * @author fangzhu(fangzhu@evmtv.com)
 * @time 2013-6-17 下午6:41:13
 */
@Service
public class SourceChannelsService implements ISourceChannels {

	@Resource TSourceChannelsMapper mapper;
	
	@Override
	public int insert(TSourceChannels channels) {
		
		return mapper.insertSelective(channels);
	}

	@Override
	public int insert(SourceChannelsRequest request) {
		List<Long> channels = request.getFchannelsid();
		int result = 0;
		for(Long cid : channels){
			TSourceChannels c = new TSourceChannels();
			c.setFchannelsid(cid);
			c.setFsourceid(request.getFsourceid());
			result += mapper.insertSelective(c);
		}
		return result;
	}

	@Override
	public int delete(List<Long> id) {
		// TODO Auto-generated method stub
		TSourceChannelsExample example = new TSourceChannelsExample();
		example.createCriteria().andIdIn(id);
		return mapper.deleteByExample(example);
	}

	@Override
	public int deleteBySourceId(List<Long> fsourceid) {
		// TODO Auto-generated method stub
		TSourceChannelsExample example = new TSourceChannelsExample();
		example.createCriteria().andFsourceidIn(fsourceid);
		return mapper.deleteByExample(example);
	}

	@Override
	public int delete(Long fsourceid) {
		TSourceChannelsExample example = new TSourceChannelsExample();
		example.createCriteria().andFsourceidEqualTo(fsourceid);
		return mapper.deleteByExample(example);
	}

	@Override
	public int update(TSourceChannels channels) {
		
		return mapper.updateByPrimaryKeySelective(channels);
	}

	@Override
	public TSourceChannels selectByKey(Long id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TSourceChannels> selectByExample(TSourceChannels channels) {
		// TODO Auto-generated method stub
		TSourceChannelsExample example = new TSourceChannelsExample();
		example.createCriteria().andFsourceidEqualTo(channels.getFsourceid());
		return mapper.selectByExample(example);
	}

	@Override
	public List<TSourceChannels> select(List<Long> id) {
		// TODO Auto-generated method stub
		return null;
	}
}
