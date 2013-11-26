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

import com.evmtv.epg.entity.TDbConfig;
import com.evmtv.epg.entity.TBranch;
import com.evmtv.epg.entity.TMenuUsergroup;
import com.evmtv.epg.response.PageUtils;
import com.evmtv.epg.service.IBranch;
import com.evmtv.epg.service.IDbConfig;
import com.evmtv.epg.service.IMenuUsergroup;
import com.evmtv.epg.utils.ReturnJsonUtils;
import com.evmtv.epg.utils.UserSession;
import com.google.gson.Gson;
/**
 * 分公司数据库配置
 * TODO
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type 
 * @project_name EAMS
 * @file_name DbConfigController.java
 * @package_name com.evmtv.epg.controller
 * @date_time 2013-7-19下午1:51:53
 * @type_name DbConfigController
 */
@Controller
@RequestMapping("/main/dbconfig")
public class DbConfigController {
	
	@Resource 
	IMenuUsergroup iMenuUsergroup;
	@Resource
	private IBranch iBranch;
	@Resource
	IDbConfig iDbConfig;
	/**
	 * 数据库配置页面
	 * @return
	 */
	@RequestMapping("/listIndex")
	public String channelsIndex(Long fmenuid,HttpServletRequest request,Model model){
		//权限判断
		TMenuUsergroup mug = UserSession.getUserMenuFreadonly(request, iMenuUsergroup, fmenuid);
		model.addAttribute("usermenu", mug);
		return PageUtils.dbconfig;
	}
	/**
	 * 条件查询
	 * @param model
	 * @param config
	 * @return
	 */
	@RequestMapping("/query")
	public String selectByExmple(Model model,TDbConfig config,HttpServletRequest request){
		IntHolder holder = new IntHolder();
		config.setHolder(holder);
		//所有分公司
		List<TDbConfig> configs = iDbConfig.query(config);
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
	public String insert(Model model, TDbConfig config){
		int result = iDbConfig.insert(config);
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
	public String update(Model model, TDbConfig config){
		int result = iDbConfig.update(config);
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
			result = iDbConfig.delete(channelsId);
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
		//得到已编辑的分公司
//		List<Long> branchids = iDbConfig.selectAllFBranchid(null);
		
		List<TBranch> branchs = iBranch.query(new TBranch());
		
		JSONObject json = new JSONObject();
		if(id != null){
			TDbConfig config = iDbConfig.queryById(id);
			json.put("config", config);
		}
		json.put("branchs", branchs);
		
		model.addAttribute("result", json);
		
		return PageUtils.json;
	}
	/**
	 * 查询所有频点
	 * @param model
	 * @param config
	 * @return
	 */
	@RequestMapping("/selectAll")
	public String selectAll(Model model,TDbConfig config){
		IntHolder holder = new IntHolder();
		config.setHolder(holder);
		
		List<TDbConfig> configs = iDbConfig.query(config);
		Gson gson = new Gson();
		String result = gson.toJson(configs);
		model.addAttribute("result", result);
		
		return PageUtils.json;
	}
	
}
