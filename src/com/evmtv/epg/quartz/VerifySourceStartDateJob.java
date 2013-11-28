package com.evmtv.epg.quartz;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;

import com.evmtv.epg.request.SourceStartDateVerifyRequest;
import com.evmtv.epg.service.IBranchSourceRelease;
import com.evmtv.epg.service.IContractAdvRescource;
import com.evmtv.epg.service.IDbConfig;

/**
 * 验证广告开始日期，开始日期与当前日期相等时，将广告改为播放状态
 * TODO
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type 
 * @project_name EAMS
 * @file_name VerifySourceStartDateJob.java
 * @package_name com.evmtv.epg.quartz
 * @date_time 2013-10-8上午10:24:43
 * @type_name VerifySourceStartDateJob
 */
public class VerifySourceStartDateJob implements IQuartz {

	@Resource
	IDbConfig iDbConfig;
	@Resource
	IBranchSourceRelease iBranchSourceRelease;
	@Resource
	IContractAdvRescource iContractAdvRescource;
	
//	@Scheduled(cron="0 0 1 * * ?")
	@Override
	public void job() {
		// TODO Auto-generated method stub
		SourceStartDateVerifyRequest request = new SourceStartDateVerifyRequest(iDbConfig, iBranchSourceRelease, iContractAdvRescource);
		request.verify();
	}

}