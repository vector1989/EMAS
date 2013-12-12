package com.evmtv.epg.response;

import java.util.ArrayList;
import java.util.List;

import com.evmtv.epg.entity.TAdv;
import com.evmtv.epg.entity.TReleaseVersion;
import com.evmtv.epg.entity.TVersionSource;
import com.evmtv.epg.service.IReleaseVersion;
import com.evmtv.epg.service.IVersionAdv;
import com.evmtv.epg.utils.ArraysUtils;
import com.evmtv.epg.utils.CollectionUtills;
/**
 * 根据版本好获取分公司广告位 信息
 * TODO
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type 
 * @project_name EAMS
 * @file_name BranchVersionSourceResponse.java
 * @package_name com.evmtv.epg.response
 * @date_time 2013年11月18日上午11:17:06
 * @type_name BranchVersionSourceResponse
 */
public class BranchVersionSourceResponse {
	/**
	 * 获取分公司广告位信息
	 * @param vs
	 * @param iReleaseVersion
	 * @param iVersionAdv
	 */
	public void getBranchAdv(TVersionSource vs, TReleaseVersion rv,IReleaseVersion iReleaseVersion,IVersionAdv iVersionAdv){
		if(!"1".equals(rv.getFbranchid().toString())){
			List<TAdv> provadvs = null;
			if(rv.getFprovreleaseversionid() != null){
				vs.setTemp(rv.getId() + "," + rv.getFprovreleaseversionid());
				provadvs = iVersionAdv.selectAdvByReleaseVersionid(rv.getFprovreleaseversionid());
			}else{
				vs.setTemp(rv.getId()+"");
			}
			vs.setFreleaseversionid(null);
			//版本广告位
			List<TAdv> advs = iVersionAdv.selectAdvByReleaseVersionid(rv.getId());
			BranchAdv ba = new BranchAdv();
			//分公司广告位
			List<Long> advIds = ba.getAdvIds(advs, provadvs, rv.getFbranchid());
			if(CollectionUtills.hasElements(advIds))
				vs.setAdvids(ArraysUtils.toString(advIds));
		}else{
			//省公司时
			List<TAdv> advs = iVersionAdv.selectAdvByReleaseVersionid(rv.getId());
			List<Long> advIds = new ArrayList<Long>();
			for(TAdv a : advs){
				if(a.getId() != null)
					advIds.add(a.getId());
			}
			if(CollectionUtills.hasElements(advIds))
				vs.setAdvids(ArraysUtils.toString(advIds));
		}
	}
}
