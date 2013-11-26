package com.evmtv.epg.service;

import java.util.List;

import com.evmtv.epg.entity.TUser;
import com.evmtv.epg.entity.TUserWithBLOBs;

public interface IUser {

	TUser loginCheck(String fusername);

	List<TUserWithBLOBs> query(TUser user);
	
	int insert(TUserWithBLOBs user);
	
	int delete(List<Long> id);
	
	int update(TUserWithBLOBs user);
	
	TUserWithBLOBs queryById(Long id);

	/**
	 * @param ids
	 * @return
	 */
	List<TUser> query(List<Long> ids);

	List<TUser> select(TUser user);
}
