package com.evmtv.epg.request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.evmtv.epg.entity.BranchIdAndSourceId;
import com.evmtv.epg.entity.TBranchSourceRelease;
import com.evmtv.epg.entity.TDbConfig;
import com.evmtv.epg.release.VersionDao;
import com.evmtv.epg.service.IBranchSourceRelease;
import com.evmtv.epg.service.IContractAdvRescource;
import com.evmtv.epg.service.IDbConfig;
import com.evmtv.epg.utils.CollectionUtills;

/**
 * 广告开始日期验证，当开始日期与当期日期，重新生成播出端ts流文件
 * TODO
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type 
 * @project_name EAMS
 * @file_name SourceStartDateVerifyRequest.java
 * @package_name com.evmtv.epg.request
 * @date_time 2013-10-8上午10:29:29
 * @type_name SourceStartDateVerifyRequest
 */
public class SourceStartDateVerifyRequest {
	
	IDbConfig iDbConfig;
	IBranchSourceRelease iBranchSourceRelease;
	IContractAdvRescource iContractAdvRescource;
	
	public SourceStartDateVerifyRequest(IDbConfig iDbConfig,
			IBranchSourceRelease iBranchSourceRelease,IContractAdvRescource iContractAdvRescource) {
		super();
		this.iDbConfig = iDbConfig;
		this.iBranchSourceRelease = iBranchSourceRelease;
		this.iContractAdvRescource = iContractAdvRescource;
	}
	/**
	 * 校验
	 */
	public void verify(){
		//分公司广告
		List<BranchIdAndSourceId> branchIdAndSourceIds = iContractAdvRescource.startDateVerifySource();
		Map<Long, List<Long>> bs = new HashMap<Long, List<Long>>();
		boolean bool = false;
		for(BranchIdAndSourceId bas : branchIdAndSourceIds){
			Long branchid = bas.getFbranchid();
			if(!bool && branchid.toString().equals("1")){
				bool = true;
			}
			List<Long> sourceids = bs.get(branchid);
			if(!CollectionUtills.hasElements(sourceids)){
				sourceids = new ArrayList<Long>();
			}
			sourceids.add(bas.getFsourceid());
			bs.put(branchid, sourceids);
		}
		//修改
		if(!bs.isEmpty()){
			List<TDbConfig> configs = null;
			//省公司广告
			if(bool){
				configs = iDbConfig.query(new TDbConfig());
			}else{
				Set<Long> branchids = bs.keySet();
				configs = iDbConfig.query(new ArrayList<Long>(branchids));
			}
			for(TDbConfig db : configs){
				//更新版本号
				new VersionDao(db).update();
			}
			//修改播放状态
			for(Entry<Long, List<Long>> entry : bs.entrySet()){
				//修改发布数据播放状态
				TBranchSourceRelease bsr = new TBranchSourceRelease();
				bsr.setFbranchid(entry.getKey());
				bsr.setFsourceidList(entry.getValue());
				bsr.setFisusing("1");//正在播放
				iBranchSourceRelease.updateByExample(bsr);
			}
		}
	}
}