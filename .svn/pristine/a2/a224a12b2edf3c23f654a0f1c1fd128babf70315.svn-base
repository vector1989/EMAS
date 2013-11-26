package com.evmtv.epg.request;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.evmtv.epg.entity.TBranchSourceRelease;
import com.evmtv.epg.entity.TContractAdv;
import com.evmtv.epg.entity.TDbConfig;
import com.evmtv.epg.release.SourceDao;
import com.evmtv.epg.release.VersionDao;
import com.evmtv.epg.service.IBranchSourceRelease;
import com.evmtv.epg.service.IDbConfig;
import com.evmtv.epg.utils.CollectionUtills;

/**
 * 合同时间校验
 * TODO
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type 
 * @project_name EAMS
 * @file_name ContractTimeVerifyReqeust.java
 * @package_name com.evmtv.epg.request
 * @date_time 2013-8-30下午4:30:06
 * @type_name ContractTimeVerifyReqeust
 */
public class ContractTimeVerifyReqeust {
	
	IDbConfig iDbConfig;
	IBranchSourceRelease iBranchSourceRelease;
	
	public ContractTimeVerifyReqeust(IDbConfig iDbConfig,
			IBranchSourceRelease iBranchSourceRelease) {
		super();
		this.iDbConfig = iDbConfig;
		this.iBranchSourceRelease = iBranchSourceRelease;
	}

	/**
	 * 合同校验
	 * @param fbranchid
	 * @return
	 */
	public String verify(Long fbranchid){
		//合同名称
		StringBuilder contractName = new StringBuilder();
		//过期合同索引
		List<TContractAdv> contractadvs = iBranchSourceRelease.selectExpiredContractId(fbranchid);
		if(CollectionUtills.hasElements(contractadvs)){
			//合同索引
			Set<Long> contractids = new HashSet<Long>();
			//广告位
//			Set<Integer> pos = new HashSet<Integer>();
			/*for(TContractAdv a : contractadvs){
				contractids.add(a.getFcontractid());
				pos.add(a.getFposition());
				contractName.append(a.getFcontractName()).append(",");
			}*/
			//查询分公司数据库配置信息
			TDbConfig dbConfig = iDbConfig.query(fbranchid);
			if(dbConfig != null){
				List<Long> contractid = new ArrayList<Long>(contractids);
				SourceDao dao = new SourceDao(dbConfig);
				int result = dao.delete(contractid);
				if(result > 0){
					//跟新版本号
					VersionDao vdao = new VersionDao(dbConfig);
					vdao.update();
					//修改发布数据播放状态
					TBranchSourceRelease bsr = new TBranchSourceRelease();
					bsr.setFbranchid(fbranchid);
					bsr.setFcontractidList(contractid);
					bsr.setFisusing("0");//取消播放
					iBranchSourceRelease.updateByExample(bsr);
				}
			}
		}
		
		return contractName.toString();
	}
}
