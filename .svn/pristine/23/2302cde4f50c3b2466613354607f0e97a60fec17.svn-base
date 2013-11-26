package com.evmtv.epg.response;

import java.util.List;

import org.omg.CORBA.IntHolder;

import com.evmtv.epg.entity.TContract;
import com.evmtv.epg.entity.TUser;
import com.evmtv.epg.service.IContract;
/**
 * 合同查询
 * TODO
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type 
 * @project_name EAMS
 * @file_name ContractQueryResponse.java
 * @package_name com.evmtv.epg.response
 * @date_time 2013年11月6日下午1:52:41
 * @type_name ContractQueryResponse
 */
public class ContractQueryResponse {
	
	/**
	 * 查询合同信息
	 * @param c
	 * @param user
	 * @param iContract
	 * @return
	 */
	public List<ContractResponse> selectContract(TContract c,TUser user,IContract iContract){
		if(c.getFbranchid() == null){
			c.setFbranchid(user.getFbranchid());
			c.setFcreateuserid(user.getId());
		}
		c.setFstarttime(c.getQueryStartTime());
		c.setFtitle(c.getQueryKeyWord());
		c.setFendtime(c.getQueryEndTime());
		c.setHolder(new IntHolder());
		
		return iContract.queryContract(c);
	}
}