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

import com.evmtv.epg.entity.TAdvClass;
import com.evmtv.epg.entity.TAdvClassConfig;
import com.evmtv.epg.entity.TBranch;
import com.evmtv.epg.entity.TMenuUsergroup;
import com.evmtv.epg.entity.TUser;
import com.evmtv.epg.response.PageUtils;
import com.evmtv.epg.service.IAdv;
import com.evmtv.epg.service.IAdvClass;
import com.evmtv.epg.service.IAdvClassConfig;
import com.evmtv.epg.service.IBranch;
import com.evmtv.epg.service.IBranchAdvClass;
import com.evmtv.epg.service.IMenuUsergroup;
import com.evmtv.epg.utils.ReturnJsonUtils;
import com.evmtv.epg.utils.UserSession;

/**
 * TODO 广告位配置信息
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type 
 * @project_name EAMS
 * @file_name AdvClassConfigController.java
 * @package_name com.evmtv.epg.controller
 * @date_time 2013-8-12上午11:14:41
 * @type_name AdvClassConfigController
 */
@Controller
@RequestMapping("/main/advClassConfig")
public class AdvClassConfigController {
	
	@Resource IAdvClass iAdvClass;
	@Resource IMenuUsergroup iMenuUsergroup;
	@Resource IBranch iBranch;
	@Resource IBranchAdvClass iBranchAdvClass;
	@Resource IAdvClassConfig iAdvClassConfig;
	@Resource IAdv iAdv;
	/**
	 * 广告位配置信息页面
	 * @return
	 */
	@RequestMapping("/index")
	public String channelsIndex(Long fmenuid,HttpServletRequest request,Model model){
		//权限判断
		TMenuUsergroup mug = UserSession.getUserMenuFreadonly(request, iMenuUsergroup, fmenuid);
		model.addAttribute("usermenu", mug);
		//当前用户
		TUser user = UserSession.loginUser(request);
		model.addAttribute("user", user);
		//分公司
		List<TBranch> branchs = iBranch.query(new TBranch());
		model.addAttribute("branchs", branchs);
		
		return PageUtils.advClassConfigIndex;
	}
	/**
	 * 条件查询
	 * @param model
	 * @param config
	 * @return
	 */
	@RequestMapping("/query")
	public String selectByExmple(Model model,TAdvClassConfig config){
		config.setHolder(new IntHolder());
		//广告位配置信息
		List<TAdvClassConfig> configs = iAdvClassConfig.selectByExample(config);
		
		int total = config.getHolder().value;//总记录
		//总页数
		int totalPage = ReturnJsonUtils.getTotalPage(config.getLimit(), total);
		//返回字符串
		String result = ReturnJsonUtils.getReturnJsonStr(configs, total, totalPage, config.getPage());

		model.addAttribute("result", result);
		
		return PageUtils.json;
	}
	/**
	 * 保存
	 * @param model
	 * @param config
	 * @return
	 */
	@RequestMapping("/insert")
	public String insert(Model model, TAdvClassConfig config){
		int result = iAdvClassConfig.insert(config);
		model.addAttribute("result", result);
		return PageUtils.json;
	}
	/**
	 * 修改
	 * @param model
	 * @param config
	 * @return
	 */
	@RequestMapping("/update")
	public String update(Model model, TAdvClassConfig config){
		int result = iAdvClassConfig.update(config);
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
			result = iAdvClassConfig.delete(channelsId);
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
			TAdvClassConfig config = iAdvClassConfig.selectByKey(id);
			json.put("config", config);
		}
		json.put("advClass", advClasses);
		json.put("branchs", branchs);
		
		model.addAttribute("result", json);
		
		return PageUtils.json;
	}
	/**
	 * 查询对象
	 * @param model
	 * @param config
	 * @return
	 */
	@RequestMapping("/querybyObject")
	public String queryByObject(Model model, TAdvClassConfig config,HttpServletRequest request){
		//分公司索引
		Long fbranchid = config.getFbranchid();
		if(fbranchid == null){
			//获取当前用户分公司索引
			fbranchid = UserSession.loginUser(request).getFbranchid();
		}
		//分公司
		TBranch branch = iBranch.queryById(fbranchid);
		//广告位素材配置信息
		TAdvClassConfig c = iAdvClassConfig.selectByObject(config);
		//json对象
		JSONObject json = new JSONObject();
		json.put("branch", branch);
		json.put("config", c);
		model.addAttribute("result", json);
		
		return PageUtils.json;
	}
}