package com.evmtv.epg.service;

import java.util.List;

import com.evmtv.epg.entity.TContract;
import com.evmtv.epg.entity.TContractWithBLOBs;
import com.evmtv.epg.response.ContractResponse;
import com.evmtv.epg.statistics.StatisticsRequest;
import com.evmtv.epg.statistics.StatisticsResponse;

/**
 * @author Lei<helei@evmtv.com>
 * @data 2013-6-27 下午3:37:07
 */

public interface IContract {
	/**
	 * 
	 * @param source
	 * @return
	 */
	List<ContractResponse> queryContract(TContract source);
	
	/**
	 * 
	 * @param tContractWithBLOBs
	 * @return
	 */
	int addContract(TContractWithBLOBs tContractWithBLOBs);
	
	/**
	 * 
	 * @param tContractWithBLOBs
	 * @return
	 */
	int updateContract(TContractWithBLOBs tContractWithBLOBs);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	TContractWithBLOBs queryByid(Long id);
	
	/**
	 * 
	 * @param fguid
	 * @return
	 */
	TContractWithBLOBs queryByFguid(String fguid);
	
	/**
	 * 
	 * @param ids
	 * @return
	 */
	int delete(List<Long> ids);
	
	/**
	 * 
	 * @param ids
	 * @return
	 */
	List<TContractWithBLOBs> selectIn(List<Long> ids);
	
	/**
	 * 
	 * @param contract
	 * @return
	 */
	List<TContract> query(TContract contract);
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	List<StatisticsResponse> queryStatistics(StatisticsRequest request);
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	List<StatisticsResponse> queryCompany(StatisticsRequest request);
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	List<StatisticsResponse> queryAdv(StatisticsRequest request);
}
