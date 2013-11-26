package com.evmtv.epg.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.omg.CORBA.IntHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.evmtv.epg.entity.TMenuUsergroup;
import com.evmtv.epg.entity.TChannelGroup;
import com.evmtv.epg.response.PageUtils;
import com.evmtv.epg.service.IChannelGroup;
import com.evmtv.epg.service.IMenuUsergroup;
import com.evmtv.epg.utils.ReturnJsonUtils;
import com.evmtv.epg.utils.UserSession;

/**
 * 频道分组
 * TODO
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type 
 * @project_name EAMS
 * @file_name ChannelGroupController.java
 * @package_name com.evmtv.epg.controller
 * @date_time 2013-9-11下午9:38:08
 * @type_name ChannelGroupController
 */
@Controller
@RequestMapping("/main/channelGroup")
public class ChannelGroupController {
	
	@Resource IMenuUsergroup imenugroup;
	@Resource IChannelGroup iChannelGroup;
	/**
	 * 用户节点页面
	 * @return
	 */
	@RequestMapping("/index")
	public String index(Long fmenuid,HttpServletRequest request,Model model){
		//权限判断
		TMenuUsergroup mug = UserSession.getUserMenuFreadonly(request, imenugroup, fmenuid);
		model.addAttribute("usermenu", mug);
		return PageUtils.cgroupIndex;
	}
	/**
	 * 条件查询
	 * @param model
	 * @param cgroup
	 * @return
	 */
	@RequestMapping("/query")
	public String selectByExmple(Model model,TChannelGroup cgroup){
		IntHolder holder = new IntHolder();
		cgroup.setHolder(holder);
		
		List<TChannelGroup> cGroups = iChannelGroup.selectByExample(cgroup);
		int total = cgroup.getHolder().value;//总记录
		//总页数
		int totalPage = ReturnJsonUtils.getTotalPage(cgroup.getLimit(), total);
		//返回字符串
		String result = ReturnJsonUtils.getReturnJsonStr(cGroups, total, totalPage, cgroup.getPage());

		model.addAttribute("result", result);
		
		return PageUtils.json;
	}
	/**
	 * 保存
	 * @param model
	 * @param cgroup
	 * @return
	 */
	@RequestMapping("/insert")
	public String insert(Model model, TChannelGroup cgroup){
		int result = iChannelGroup.insert(cgroup);
		model.addAttribute("result", result);
		return PageUtils.json;
	}
	/**
	 * 修改
	 * @param model
	 * @param cgroup
	 * @return
	 */
	@RequestMapping("/update")
	public String update(Model model, TChannelGroup cgroup){
		if(!StringUtils.hasText(cgroup.getFisprovincecompany())){
			cgroup.setFisprovincecompany("0");
		}
		int result = iChannelGroup.update(cgroup);
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
		List<Long> groupIds = new ArrayList<Long>();
		String [] idArr = id.split(",");
		for(String i : idArr){
			groupIds.add(Long.valueOf(i));
		}
		String tips = "";

		int result = 0;
		if(!groupIds.isEmpty())
			result = iChannelGroup.delete(groupIds);
		
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
		JSONObject json = new JSONObject();
		//用户分组
		if(id != null){
			TChannelGroup cgroup = iChannelGroup.selectByKey(id);
			json.put("cgroup", cgroup);
		}
		model.addAttribute("result", json);
		
		return PageUtils.json;
	}
}