package com.evmtv.epg.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.omg.CORBA.IntHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.evmtv.epg.entity.TAdv;
import com.evmtv.epg.entity.TAdvClass;
import com.evmtv.epg.entity.TAdvWithBLOBs;
import com.evmtv.epg.entity.TBranch;
import com.evmtv.epg.entity.TMenuUsergroup;
import com.evmtv.epg.entity.TUser;
import com.evmtv.epg.response.PageUtils;
import com.evmtv.epg.service.IAdv;
import com.evmtv.epg.service.IAdvClass;
import com.evmtv.epg.service.IBranch;
import com.evmtv.epg.service.IBranchAdvClass;
import com.evmtv.epg.service.IMenuUsergroup;
import com.evmtv.epg.service.IUser;
import com.evmtv.epg.utils.DateUtils;
import com.evmtv.epg.utils.ReturnJsonUtils;
import com.evmtv.epg.utils.UserSession;
import com.google.gson.Gson;

/**
 * 广告位管理
 * @author fangzhu(fangzhu@evmtv.com)
 * @time 2013-6-17 上午9:52:07
 */
@Controller
@RequestMapping("/main/advs")
public class AdvController {
	
	@Resource
	IAdv iAdv;
	@Resource
	IAdvClass iAdvClass;
	@Resource 
	IMenuUsergroup iMenuUsergroup;
	@Resource
	private IBranch iBranch;
	@Resource IBranchAdvClass iBranchAdvClass;
	@Resource
	IUser iUser;
	/**
	 * 频点页面
	 * @return
	 */
	@RequestMapping("/listIndex")
	public String channelsIndex(Long fmenuid,HttpServletRequest request,Model model){
		//权限判断
		TMenuUsergroup mug = UserSession.getUserMenuFreadonly(request, iMenuUsergroup, fmenuid);
		model.addAttribute("usermenu", mug);

		//分公司
		List<TBranch> branchs = iBranch.query(new TBranch());
		model.addAttribute("branchs", branchs);
		
		return PageUtils.advIndex;
	}
	/**
	 * 条件查询
	 * @param model
	 * @param advs
	 * @return
	 */
	@RequestMapping("/query")
	public String selectByExmple(Model model,TAdv adv,HttpServletRequest request){
		IntHolder holder = new IntHolder();
		adv.setHolder(holder);
		if(adv.getFbranchid() == null){
			TUser user = UserSession.loginUser(request);
			adv.setFbranchid(user.getFbranchid());
		}
		List<TAdv> advs = iAdv.selectByExample(adv);
		int total = adv.getHolder().value;//总记录
		//总页数
		int totalPage = ReturnJsonUtils.getTotalPage(adv.getLimit(), total);
		//返回字符串
		String result = ReturnJsonUtils.getReturnJsonStr(advs, total, totalPage, adv.getPage());

		model.addAttribute("result", result);
		
		return PageUtils.json;
	}
	/**
	 * 保存
	 * @param model
	 * @param advs
	 * @return
	 */
	@RequestMapping("/insert")
	public String insert(Model model, TAdvWithBLOBs advs,HttpServletRequest request){
		TUser user = UserSession.loginUser(request);
		advs.setFcreateuserid(user.getId());
		advs.setFcreatetime(DateUtils.getCurrentTime());
		advs.setFdeleted("0");
		advs.setFchecked("0");
		int result = iAdv.insert(advs);
		model.addAttribute("result", result);
		return PageUtils.json;
	}
	/**
	 * 修改
	 * @param model
	 * @param advs
	 * @return
	 */
	@RequestMapping("/update")
	public String update(Model model, TAdvWithBLOBs advs,HttpServletRequest request){
		TUser user = UserSession.loginUser(request);
		advs.setFupdateuserid(user.getId());
		advs.setFupdatetime(DateUtils.getCurrentTime());
		int result = iAdv.update(advs);
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
	public String delete(Model model,String id){
		List<Long> channelsId = new ArrayList<Long>();
		String [] idArr = id.split(",");
		for(String i : idArr){
			channelsId.add(Long.valueOf(i));
		}
		String tips = "";
				
		int result = 0;
		if(!channelsId.isEmpty())
			result = iAdv.delete(channelsId);
		if(tips.isEmpty()){
			if(result > 0)
				tips = "操作成功";
			else
				tips = "操作失败";
		}else{
			if(result >0)
				tips +="成功删除"+result+"条数据";
			else
				tips += "不能删除";
		}
		
		model.addAttribute("result", tips);
		return PageUtils.json;
	}
	/**
	 * 根据id查询
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/selectByKey")
	public String queryById(Model model, Long id){
		//广告位
		List<TAdvClass> advClasses = iAdvClass.selectByExample(null);
		//分公司
		List<TBranch> branchs = iBranch.query(new TBranch());
		JSONObject json = new JSONObject();
		if(id != null){
			TAdv advs = iAdv.selectByKey(id);
			json.put("adv", advs);
		}
		json.put("advClass", advClasses);
		json.put("branchs", branchs);
		
		model.addAttribute("result", json);
		
		return PageUtils.json;
	}
	/**
	 * 查询所有频点
	 * @param model
	 * @param advs
	 * @return
	 */
	@RequestMapping("/selectAll")
	public String selectAll(Model model,TAdv adv){
		IntHolder holder = new IntHolder();
		adv.setHolder(holder);
		
		List<TAdv> advs = iAdv.selectByExample(adv);
		Gson gson = new Gson();
		String result = gson.toJson(advs);
		model.addAttribute("result", result);
		
		return PageUtils.json;
	}
	/**
	 * 审核
	 * @param model
	 * @param ids
	 * @param adv
	 * @return
	 */
	@RequestMapping("/checked")
	public String checked(Model model,String ids,TAdvWithBLOBs adv){
		List<Long> id = new ArrayList<Long>();
		String[] str = ids.split(",");
		for(String s : str){
			id.add(Long.valueOf(s));
		}
		int result = iAdv.checked(id, adv);
		model.addAttribute("result", result);
		return PageUtils.json;
	}
	/**
	 * 分公司广告位
	 * @param model
	 * @param branchid
	 * @return
	 */
	@RequestMapping("/loadBranchAdvclass")
	public String loadBranchAdv(Model model,Long branchid){
		List<Long> advids = null;
		if(!branchid.toString().equals("1")){
			//分公司广告位索引
			advids = iBranchAdvClass.selectByBranchId(branchid);
		}
		List<TAdvClass> advs = iAdvClass.select(advids);
		
		String result = new Gson().toJson(advs);
		model.addAttribute("result", result);
		return PageUtils.json;
	}
}