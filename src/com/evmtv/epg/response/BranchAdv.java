package com.evmtv.epg.response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.evmtv.epg.entity.TAdv;
import com.evmtv.epg.entity.TBranch;
import com.evmtv.epg.entity.TReleaseVersion;
import com.evmtv.epg.entity.TTimePeriod;
import com.evmtv.epg.entity.TreeJson;
import com.evmtv.epg.service.IBranch;
import com.evmtv.epg.service.IReleaseVersion;
import com.evmtv.epg.service.ITimePeriod;
import com.evmtv.epg.service.IVersionAdv;
import com.evmtv.epg.utils.CollectionUtills;
import com.evmtv.epg.utils.ComparatorUtil;
import com.evmtv.epg.utils.StringUtils;

/**
 * TreeJsonList集合,用于生成树 TODO
 * 
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type
 * @project_name EAMS
 * @file_name BranchAdvTimeperiod.java
 * @package_name com.evmtv.epg.response
 * @date_time 2013年10月25日上午10:48:17
 * @type_name BranchAdvTimeperiod
 */
public class BranchAdv {
	// 判断分公司是否有信息条广告
	private	boolean hasInfoBarAdv = false;
	/**
	 * 获取分公司广告位索引
	 * @param iBranch
	 * @param iAdv
	 * @param fbranchid
	 * @param fdefinition
	 * @return
	 */
	public List<Long> getVaIds(List<TAdv> advs, List<TAdv> provAdvs, Long fbranchid) {
		//是有为省公司
		boolean isProv = (!"1".equals(fbranchid.toString()) && CollectionUtills.hasElements(provAdvs));
		
		//广告位索引
		List<Long> vaIds = new ArrayList<Long>();
		for (TAdv a : advs) {
			int pos = a.getFpositionid();
			vaIds.add(a.getVaid());
			if (!hasInfoBarAdv && pos == 4 && !"1".equals(fbranchid.toString())) {
				hasInfoBarAdv = true;
			}
			// 不是省公司时
			if (isProv) {
				for (int i = 0, len = provAdvs.size(); i < len; i++) {
					if (pos != 4 && pos == provAdvs.get(i).getFpositionid()) {
						provAdvs.remove(i);
						break;
					}
				}
			}
		}
		if (isProv && CollectionUtills.hasElements(provAdvs)) {
			for (TAdv preAdv : provAdvs) {
				preAdv.setTemp("1");
				advs.add(preAdv);
				vaIds.add(preAdv.getVaid());
			}
		}
		return vaIds;
	}
	/**
	 * 获取分公司广告位索引
	 * @param fbranchid
	 * @param fdefinition
	 * @return
	 */
	public List<Long> getAdvIds(List<TAdv> advs, List<TAdv> provAdvs, Long fbranchid) {

		//是有为省公司
		boolean isProv = (!"1".equals(fbranchid.toString()) && CollectionUtills.hasElements(provAdvs));
		//广告位索引
		List<Long> vaIds = new ArrayList<Long>();
		for (TAdv a : advs) {
			int pos = a.getFpositionid();
			vaIds.add(a.getVaid());
			// 不是省公司时
			if (isProv) {
				for (int i = 0, len = provAdvs.size(); i < len; i++) {
					if (pos != 4 && pos == provAdvs.get(i).getFpositionid()) {
						provAdvs.remove(i);
						break;
					}
				}
			}
		}
		// 不是省公司时
		if (isProv && CollectionUtills.hasElements(provAdvs)) {
			for (TAdv preAdv : provAdvs) {
//				if(preAdv.getFpositionid() == 3)
				preAdv.setTemp("1");
				advs.add(preAdv);
				vaIds.add(preAdv.getId());
			}
		}

		return vaIds;
	}

	/**
	 * 
	 * @param fbranchid
	 * @param fdefinition
	 * @param iAdv
	 * @param iTimePeriod
	 * @param iBranch
	 * @return
	 */
	public List<TreeJson> getTreeJsonList(TReleaseVersion rv, Integer isusing, ITimePeriod iTimePeriod, IBranch iBranch,IVersionAdv iVersionAdv, IReleaseVersion iReleaseVersion) {
		
		// 当前公司
		TBranch branch = iBranch.queryById(rv.getFbranchid());
		String definition = "高清";
		if ("SD".equals(rv.getFdefinition()))
			definition = "标清";
		// 生成树对象
		List<TreeJson> jsons = new ArrayList<TreeJson>();
		jsons.add(new TreeJson("at0", "0", branch.getFname() + "[" + definition + "]", true));

		List<TAdv> provAdvs = null;
		boolean hasRvid = true;//是否有版本号
		Integer status = null;
		if(rv.getId() == null && isusing != null && isusing == 1){
			rv = iReleaseVersion.selectMaxIdByFbranchidAndFdefinition(rv);
			hasRvid = false;
		}else{
			status = rv.getFstatus();
		}
		if(rv != null && rv.getId() != null){
			Long provRvid = null;
			if(!"1".equals(rv.getFbranchid().toString())){
				if(hasRvid)
					rv = iReleaseVersion.selectByKey(rv.getId());//iReleaseVersion.selectByBranchidAndMaxDayVersion(1L, fdefinition);//iVersionAdv.selectMaxReleaseVersionid(new VersionAdvResponse(1L,fdefinition));
				if(rv != null){
					provRvid = rv.getFprovreleaseversionid();
					provAdvs = iVersionAdv.selectAdvByReleaseVersionid(provRvid);
				}
			}
			// 分公司广告位
			List<TAdv> advs = iVersionAdv.selectAdvByReleaseVersionid(rv.getId());
			
			//判断是否为标清，标清是获取高清中正在播发的高清视频
			if(status != null && status == 1 && isusing != null && isusing == 1 && "SD".equalsIgnoreCase(rv.getFdefinition())){
				TReleaseVersion hdRv = new TReleaseVersion();
				hdRv.setFbranchid(rv.getFbranchid());
				hdRv.setFdefinition("HD");
				hdRv.setFstatus(1);
				//广告位
				TAdv adv = null;
				//该公司已发布最大版本号
				TReleaseVersion tempHdRv = iReleaseVersion.selectMaxIdByFbranchidAndFdefinition(hdRv);
				if(tempHdRv != null){
					adv = iVersionAdv.selectHdVideoAdvByRvidAndPosid(tempHdRv.getId());
					//判断该公司是否有高清开机视频广告位
					if(adv == null){
						hdRv.setFbranchid(1L);
						tempHdRv = iReleaseVersion.selectMaxIdByFbranchidAndFdefinition(hdRv);
						if(tempHdRv != null){
							adv = iVersionAdv.selectHdVideoAdvByRvidAndPosid(tempHdRv.getId());
						}
					}
				}else{
					hdRv.setFbranchid(1L);
					tempHdRv = iReleaseVersion.selectMaxIdByFbranchidAndFdefinition(hdRv);
					if(tempHdRv != null){
						adv = iVersionAdv.selectHdVideoAdvByRvidAndPosid(tempHdRv.getId());
					}
				}
				advs.add(adv);
			}
			
			
			//分公司广告位
			List<Long> vaIds = getVaIds(advs, provAdvs, rv.getFbranchid());
			// 分公司所有时间段
			List<TTimePeriod> periods = iTimePeriod.query(vaIds);
			
			//主菜单广告位
			TAdv rightAdv = getRightMainMenuAdv(advs,provAdvs);//getRightMainMenuAdv(iVersionAdv,fbranchid,fdefinition,-3);
			
			for (TAdv a : advs) {
				int pos = a.getFpositionid();
				Long rid = rv.getId();
				if(StringUtils.hasText(a.getTemp()) && "1".equals(a.getTemp())){
					rid = provRvid;
				}
				// 信息条广告判断
				if ((pos == 4 && (!"1".equals(a.getFbranchid().toString()) || !hasInfoBarAdv)) || pos != 4){
					jsons.add(setTreeJson(pos, a, rv.getFbranchid(),rid));
				}
				//主菜单-右
				if(pos != -3){
					for (TTimePeriod t : periods) {
						if (t.getFadvclassid().toString().equals(a.getId().toString())) {
							jsons.add(setTreeJson(rv.getFbranchid(),t,pos,a,null,rid,null,null));
							if(pos == 3){
								jsons.add(setTreeJson(rv.getFbranchid(),t,-3,rightAdv,a.getId(),rv.getId(),rid,a.getVaid()));
							}
						}
					}
				}
			}
			Collections.sort(jsons, new ComparatorUtil());
		}
		return jsons;
	}
	/**
	 * 主菜单-右
	 * @param advs
	 * @param provAdvs
	 * @return
	 */
	private TAdv getRightMainMenuAdv(List<TAdv> advs, List<TAdv> provAdvs) {
		TAdv rightAdv = null;
		for(TAdv a : advs){
			if(a.getFpositionid() == -3){
				rightAdv = a;
				break;
			}
		}
		if(rightAdv == null && CollectionUtills.hasElements(provAdvs))
			for(TAdv a : provAdvs){
				if(a.getFpositionid() == -3){
					rightAdv = a;
					break;
				}
			}
		return rightAdv;
	}

	/**
	 * 这是广告位时间段
	 * @param fbranchid
	 * @param t
	 * @param pos
	 * @param aid
	 * @return
	 */
	private TreeJson setTreeJson(Long fbranchid,TTimePeriod t,int pos,TAdv a,Long leftAdvid, Long rvid,Long rid,Long vaid){
		TreeJson j = new TreeJson("t" + t.getId().toString(), "a" + pos, t.getFstarttime() + "-" + t.getFendtime());
		if (!"1".equals(fbranchid.toString()) && "1".equals(t.getFbranchid().toString())) {
			j.setDisabled(false);
		}
		j.setPositionid(pos);
		j.setAdvid(a.getId());
		j.setTimeperiodid(t.getId());
		j.setStarttime(t.getFstarttime());
		j.setLeftMenuAdvid(leftAdvid);
		j.setRvid(rvid);
		j.setProvRvid(rid);
		if(vaid != null)
			j.setVaid(vaid);
		else
			j.setVaid(a.getVaid());
		
		return j;
	}
	
	/**
	 * 
	 * @param pos
	 * @param a
	 * @param fbranchid
	 * @return
	 */
	private TreeJson setTreeJson(int pos, TAdv a, Long fbranchid,Long rvid) {
		TreeJson tj = new TreeJson("a" + pos, "at0", a.getFtype());
		if (pos == 1 || pos == 2 || pos == 9) {
			tj.setClick(false);
		}
		tj.setIsParent(true);
		if (!"1".equals(fbranchid.toString())
				&& "1".equals(a.getFbranchid().toString())) {
			tj.setDisabled(false);
		}
		tj.setAdvid(a.getId());
		tj.setPositionid(pos);
		tj.setRvid(rvid);
		tj.setVaid(a.getVaid());

		return tj;
	}	
}