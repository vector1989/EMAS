package com.evmtv.epg.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.evmtv.epg.entity.TAdv;
import com.evmtv.epg.entity.TBranch;
import com.evmtv.epg.entity.TMenuUsergroup;
import com.evmtv.epg.entity.TReleaseVersion;
import com.evmtv.epg.entity.TSource;
import com.evmtv.epg.entity.TTimePeriod;
import com.evmtv.epg.entity.TUser;
import com.evmtv.epg.entity.TreeJson;
import com.evmtv.epg.response.BranchAdv;
import com.evmtv.epg.response.PageUtils;
import com.evmtv.epg.service.IAdv;
import com.evmtv.epg.service.IBranch;
import com.evmtv.epg.service.IBranchAdvClass;
import com.evmtv.epg.service.IMenuUsergroup;
import com.evmtv.epg.service.IReleaseVersion;
import com.evmtv.epg.service.ISource;
import com.evmtv.epg.service.ITimePeriod;
import com.evmtv.epg.service.IVersionAdv;
import com.evmtv.epg.utils.CollectionUtills;
import com.evmtv.epg.utils.StringUtils;
import com.evmtv.epg.utils.UserSession;
import com.google.gson.Gson;

/**
 * 广告时间管理
 * 
 * @author fangzhu(fangzhu@evmtv.com)
 * @time 2013-6-13 下午6:26:44
 */
@Controller
@RequestMapping("/main/time")
public class TimePeroidController {

	@Resource IAdv iAdv;
	@Resource ISource iSource;
	@Resource IBranch iBranch;
	@Resource IVersionAdv iVersionAdv;
	@Resource ITimePeriod iTimePeriod;
	@Resource IMenuUsergroup iMenuUsergroup;
	@Resource IReleaseVersion iReleaseVersion;
	@Resource IBranchAdvClass iBranchAdvClass;

	@RequestMapping("/timeIndex")
	public String timeIndex(Long fmenuid, HttpServletRequest request, Model model) {
		// 权限判断
		TMenuUsergroup mug = UserSession.getUserMenuFreadonly(request, iMenuUsergroup, fmenuid);
		model.addAttribute("usermenu", mug);
		Long fbranchid = null;
		if(fbranchid  == null){
			// 广告位
			TUser user = UserSession.loginUser(request);
			fbranchid = user.getFbranchid();
		}
		/*List<TAdv> advs =filterAdv(fbranchid);
		model.addAttribute("advs", advs);*/

		// 分公司
		List<TBranch> branchs = iBranch.query(new TBranch());
		model.addAttribute("branchs", branchs);

		return PageUtils.timeIndex;
	}

	/**
	 * 广告位时间段信息
	 * @param model
	 * @param period 时间段参数
	 * @param rvid 版本号
	 * @param fisusing 是否正在使用
	 * @param request
	 * @return
	 */
	@RequestMapping("/query")
	public String query(Model model, TReleaseVersion rv, Long rvid, Integer isusing,HttpServletRequest request) {
		//分公司
		if(rv.getFbranchid() == null){
			rv.setFbranchid(UserSession.loginUser(request).getFbranchid());
		}
		//treejson对象
		BranchAdv bat = new BranchAdv();
//		rv.setId(rvid);
		List<TreeJson> jsons = bat.getTreeJsonList(rv, isusing, iTimePeriod, iBranch, iVersionAdv,iReleaseVersion);
		
		String result = new Gson().toJson(jsons);
		model.addAttribute("result", result);
		
		return PageUtils.json;
	}

	/**
	 * 保存
	 * 
	 * @param model
	 * @param period
	 * @return
	 */
	@RequestMapping("/insert")
	public String insert(Model model, TTimePeriod period) {
		int result = 0;
		if("00:00".equals(period.getFstarttime()) && "23:59".equals(period.getFendtime()))
			period.setFdeleted("1");
		if(StringUtils.hasText(period.getAdv()) && "4".equals(period.getAdv()) && !"1".equals(period.getFbranchid())){
			//获取省公司版本
			TReleaseVersion rv = iReleaseVersion.selectByKey(period.getFreleaseversionid());
			if(rv != null && rv.getFprovreleaseversionid() != null){
				TTimePeriod t = period;
				t.setFadvclassid(null);
				t.setTemp(period.getFreleaseversionid() + "," + rv.getFprovreleaseversionid());
			}
		}
		boolean b = iTimePeriod.selectCheckRepeat(period) > 0;
		if(!b){
			result = iTimePeriod.insert(period);
		}else{
			result = -1;
		}
			
		model.addAttribute("result", result);
		return PageUtils.json;
	}

	/**
	 * 修改
	 * 
	 * @param model
	 * @param period
	 * @return
	 */
	@RequestMapping("/update")
	public String update(Model model, TTimePeriod period) {
		
		if("00:00".equals(period.getFstarttime()) && "23:59".equals(period.getFendtime()))
			period.setFdeleted("1");
		if(StringUtils.hasText(period.getAdv()) && "4".equals(period.getAdv()) && !"1".equals(period.getFbranchid())){
			TAdv adv = new TAdv();
			adv.setFbranchid(1L);
			adv.setFpositionid(4);
			adv.setFdefinition(period.getFdefinition());
			List<TAdv> advs = iAdv.selectByExample(adv);
			TAdv a = CollectionUtills.hasElements(advs) ? advs.get(0) : null;
			period.setTemp(a.getId() + "," + period.getFadvclassid());
			period.setFadvclassid(null);
		}
		int result = 0;
		boolean b = iTimePeriod.selectCheckRepeat(period) > 0;
		if(!b){
			result = iTimePeriod.update(period);
		}else{
			result = -1;
		}
		model.addAttribute("result", result);
		return PageUtils.json;
	}

	/**
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	public String delete(Model model, Long id) {
		String tips = "";
		
		List<TSource> sources = iSource.queryByTimeId(id);
		
		int result = 0;
		if(CollectionUtills.hasElements(sources)){
			tips = "数据正在使用中,不能删除";
		}else{
			result = iTimePeriod.delete(id);
			if (result > 0)
				tips = "操作成功";
			else
				tips = "操作失败";
		}

		model.addAttribute("result", tips);
		return PageUtils.json;
	}

	/**
	 * 根据id查询
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/selectByKey")
	public String queryById(Model model, Long id,Long fbranchid,HttpServletRequest request) {
		if(fbranchid == null){
			// 广告位
			TUser user = UserSession.loginUser(request);
			fbranchid = user.getFbranchid();
		}
		List<TAdv> advs =filterAdv(fbranchid);
		// 分公司
		List<TBranch> branchs = iBranch.query(new TBranch());
		JSONObject json = new JSONObject();
		if (id != null) {
			TTimePeriod period = iTimePeriod.queryById(id);
			json.put("period", period);
		}
		json.put("adv", advs);
		json.put("branchs", branchs);

		model.addAttribute("result", json);

		return PageUtils.json;
	}

	/**
	 * 查询时间段信息
	 * 
	 * @param model
	 * @param period
	 * @return
	 */
	@RequestMapping("/queryByFpositionid")
	public String queryByFpositionid(Model model, TTimePeriod period) {
		List<TTimePeriod> periods = iTimePeriod.query(period);
		Gson gson = new Gson();
		String result = gson.toJson(periods);

		model.addAttribute("result", result);

		return PageUtils.json;
	}

	/**
	 * 加载分公司广告位
	 * 
	 * @param model
	 * @param branchid
	 * @return
	 */
	@RequestMapping("/loadBranchAdv")
	public String loadBranchAdv(Model model, Long branchid) {

		List<TAdv> advs =filterAdv(branchid);
		Gson gson = new Gson();
		String result = gson.toJson(advs);
		model.addAttribute("result", result);
		return PageUtils.json;
	}
	/**
	 * 过滤不需要广告位
	 * @param fbranchids
	 * @return
	 */
	private List<TAdv> filterAdv(Long fbranchid){
		List<TAdv> tAdvs = iAdv.query(fbranchid);
		List<TAdv> advs = new ArrayList<TAdv>();
		for(TAdv a : tAdvs){
			int positionid = a.getFpositionid();
			if(positionid != 1 && positionid != 2 && positionid != 9){
				advs.add(a);
			}
		}
		return advs;
	}
	/**
	 * 加载时间段根据广告位索引
	 * @Deprecated 已弃用
	 * @param model
	 * @param fadvid
	 * @return
	 */
	@RequestMapping("/loadTimeperiodByAdvid")
	public String loadTimeperiodByAdvid(Model model,Long fadvid, Long rvid, Long vaid){
		//广告位版本
		List<TTimePeriod> periods = iTimePeriod.queryByAdvid(fadvid, rvid, vaid);
		
		Gson gson = new Gson();
		String result = gson.toJson(periods);
		
		model.addAttribute("result", result);
		
		return PageUtils.json;
	}
}
