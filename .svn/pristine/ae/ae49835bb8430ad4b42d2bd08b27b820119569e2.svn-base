package com.evmtv.epg.quartz;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.evmtv.epg.entity.TBranch;
import com.evmtv.epg.request.ContractTimeVerifyReqeust;
import com.evmtv.epg.service.IBranch;
import com.evmtv.epg.service.IBranchSourceRelease;
import com.evmtv.epg.service.IDbConfig;

/**
 * 合同时间校验,将过期的合同取消播放
 * TODO
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type 
 * @project_name EAMS
 * @file_name VerifyContractTime.java
 * @package_name com.evmtv.epg.task
 * @date_time 2013-8-30下午3:05:30
 * @type_name VerifyContractTime
 */
@Component
public class VerifyContractTimeJob implements IQuartz{
	
	@Resource IBranch iBranch;
	@Resource IDbConfig iDbConfig;
	@Resource IBranchSourceRelease iBranchSourceRelease;
	/**
	 * 任务调度，每天凌晨一点执行
	 */
//	@Scheduled(cron="0 0/30 * * * ?")
	@Override
	public void job() {
		// TODO Auto-generated method stub
		//分公司
		List<TBranch> branchs = iBranch.query(new TBranch());
		//合同验证
		ContractTimeVerifyReqeust reqeust = new ContractTimeVerifyReqeust(iDbConfig,iBranchSourceRelease);
		//遍历分公司
		for(TBranch b : branchs){
			//遍历所有分公司，检查是否有过期合同，有则删除过去合同广告
			reqeust.verify(b.getId());
		}
	}
}