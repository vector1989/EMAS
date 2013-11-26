package com.evmtv.epg.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.evmtv.epg.entity.TAdv;
import com.evmtv.epg.entity.TBranch;
import com.evmtv.epg.entity.TReleaseVersion;
import com.evmtv.epg.entity.TTimePeriod;
import com.evmtv.epg.entity.TVersionAdv;
import com.evmtv.epg.entity.TVersionSource;
import com.evmtv.epg.response.PageUtils;
import com.evmtv.epg.service.IAdv;
import com.evmtv.epg.service.IBranch;
import com.evmtv.epg.service.IReleaseVersion;
import com.evmtv.epg.service.ISource;
import com.evmtv.epg.service.ITimePeriod;
import com.evmtv.epg.service.IVersionAdv;
import com.evmtv.epg.service.IVersionSource;
import com.evmtv.epg.utils.CollectionUtills;
import com.evmtv.epg.utils.DateUtils;
import com.evmtv.epg.utils.PinYinUtils;
import com.evmtv.epg.utils.StringUtils;
import com.evmtv.epg.utils.UserSession;

/**
 * 广告版本，新增版本复制上一版本
 * TODO
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type 
 * @project_name EAMS
 * @file_name SourceVersionController.java
 * @package_name com.evmtv.epg.controller
 * @date_time 2013年11月9日下午1:40:08
 * @type_name SourceVersionController
 */
@Controller
@RequestMapping("/main/source/")
public class VersionSourceController{
	
	@Resource IAdv iAdv;
	@Resource ISource iSource;
	@Resource IBranch iBranch;
	@Resource IVersionAdv iVersionAdv;
	@Resource ITimePeriod iTimePeriod;
	@Resource IVersionSource iVersionSource;
	@Resource IReleaseVersion iReleaseVersion;
	
	/**
	 * 复制新增
	 * @param model
	 * @param fprovreleaseversionid 
	 * @return
	 */
	@RequestMapping("copyInsertVersion")
	public String copyInsertVersion(Model model,Long fbranchid,String fdefinition, Long provid,HttpServletRequest request){
		//分公司
		TBranch branch = iBranch.queryById(fbranchid);
		String vno = PinYinUtils.getFirstSpell(branch.getFname()) + branch.getId() + fdefinition.toLowerCase() +DateUtils.formatDate("yyyyMMdd");
		//上一个广告版本
		TReleaseVersion rv = iReleaseVersion.selectMaxIdByFbranchidAndFdefinition(fbranchid,fdefinition,null);
		//省公司最新版本
		if(provid == null && !"1".equals(fbranchid.toString())){
			TReleaseVersion provrv = iReleaseVersion.selectMaxIdByFbranchidAndFdefinition(1L, fdefinition,null);
			if(provrv != null)
				provid = provrv.getId();
		}
		int dv = 1;
		if(rv != null){
			String ct = rv.getFcreatetime();
			if(StringUtils.hasText(ct)){
				ct = ct.split(" ")[0];
				String cdate = DateUtils.formatDate();
				if(ct.equals(cdate)){
					dv = rv.getFdayversion() + 1;
				}
			}
		}
		vno += "-"+(dv);
		//新增版本
		TReleaseVersion v = new TReleaseVersion();
		v.setFbranchid(fbranchid);
		v.setFdefinition(fdefinition);
		v.setFprovreleaseversionid(provid);
		v.setFversion(vno);
		v.setFdayversion(dv);
		v.setFcreatetime(DateUtils.getCurrentTime());
		v.setFcreateuserid(UserSession.loginUser(request).getId());
		//插入新版本
		int result = iReleaseVersion.insert(v);
		if( result > 0){
			if(rv == null){
				List<TAdv> advs = iAdv.query(fbranchid, fdefinition);
				if(CollectionUtills.hasElements(advs)){
					List<TVersionAdv> vas = new ArrayList<TVersionAdv>();
					for(TAdv a : advs){
						//版本广告位
						TVersionAdv va = new TVersionAdv();
						va.setFbranchid(fbranchid);
						va.setFdefinition(fdefinition);
						va.setFadvid(a.getId());
						va.setFreleaseversionid(v.getId());
						vas.add(va);
					}
					//新增广告位版本
					iVersionAdv.batchInsert(vas);
				}
			}else {
				//有版本信息，获取该版本下广告信息，并保存为新版本
				//上一版本广告位
				List<TAdv> advs = iVersionAdv.selectAdvByReleaseVersionid(rv.getId());
				for(TAdv a : advs){
					int pos = a.getFpositionid();
					Long aid = a.getId();
					//版本广告位
					TVersionAdv va = new TVersionAdv();
					va.setFadvid(aid);
					va.setFbranchid(fbranchid);
					va.setFdefinition(fdefinition);
					va.setFreleaseversionid(v.getId());
					//新增广告位版本
					iVersionAdv.insert(va);
					
					//这几个广告位不支持时间段
					if(pos != 1 && pos != 2 && pos != 9){
						//广告位下时间段,上一版本
						List<TTimePeriod> times = iTimePeriod.queryByAdvid(aid,rv.getId(),a.getVaid());
						for(TTimePeriod t : times){
							batchInsertVsersionSource(a.getId(),rv.getId(),v.getId(),va.getId(),t);
						}
					}else{
						batchInsertVsersionSource(aid,rv.getId(),v.getId(),va.getId(),null);
					}
				}
			}
		}
		model.addAttribute("result", result);
		
		return PageUtils.json;
	}
	/**
	 * 插入广告版本信息
	 * @param aid 广告索引（原来）
	 * @param oldRvid 版本索引（原来）
	 * @param rvid 版本索引（新）
	 * @param t 时间段
	 * @param vaid 
	 */
	private void batchInsertVsersionSource(Long aid, Long oldRvid,Long rvid, Long vaid,TTimePeriod t){
		TVersionSource vs = new TVersionSource();
		vs.setFadvid(aid);
		vs.setFreleaseversionid(oldRvid);
		//插入广告数据信息
		if(t != null){
			vs.setFtimeperiodid(t.getId());
			//插入时间段信息
			t.setId(null);
			t.setFreleaseversionid(rvid);
			t.setFversionadvid(vaid);
			iTimePeriod.insert(t);
		}
		//查询时间段下广告信息
		List<TVersionSource> vss = iVersionSource.selectByExample(vs);
		if(CollectionUtills.hasElements(vss)){
			for(TVersionSource s : vss){
				if(t != null)
					s.setFtimeperiodid(t.getId());
				s.setFreleaseversionid(rvid);
				s.setFversionadvid(vaid);
			}
			iVersionSource.batchInsert(vss);
		}
	}
}