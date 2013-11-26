package com.evmtv.epg.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.omg.CORBA.IntHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.evmtv.epg.entity.TMenuUsergroup;
import com.evmtv.epg.entity.TUsergroup;
import com.evmtv.epg.response.PageUtils;
import com.evmtv.epg.service.IMenu;
import com.evmtv.epg.service.IMenuUsergroup;
import com.evmtv.epg.service.IUserGroup;
import com.evmtv.epg.utils.ReturnJsonUtils;
import com.evmtv.epg.utils.UserSession;
import com.google.gson.Gson;

/**
 * 频点管理
 * @author fangzhu(fangzhu@evmtv.com)
 * @time 2013-6-17 上午9:52:07
 */
@Controller
@RequestMapping("/main/group")
public class UsergroupController {
	
	@Resource
	IUserGroup iGroup;
	@Resource
	IMenuUsergroup iUsergroup;
	@Resource
	IMenu iMenu;
	@Resource
	IMenuUsergroup imenugroup;
	/**
	 * 频点页面
	 * @return
	 */
	@RequestMapping("/index")
	public String channelsIndex(Long fmenuid,HttpServletRequest request,Model model){
		//权限判断
		TMenuUsergroup mug = UserSession.getUserMenuFreadonly(request,
				imenugroup, fmenuid);
		model.addAttribute("usermenu", mug);
		return PageUtils.groupIndex;
	}
	/**
	 * 条件查询
	 * @param model
	 * @param group
	 * @return
	 */
	@RequestMapping("/query")
	public String selectByExmple(Model model,TUsergroup group){
		IntHolder holder = new IntHolder();
		group.setHolder(holder);
		
		List<TUsergroup> group2 = iGroup.selectByExample(group);
		int total = group.getHolder().value;//总记录
		//总页数
		int totalPage = ReturnJsonUtils.getTotalPage(group.getLimit(), total);
		//返回字符串
		String result = ReturnJsonUtils.getReturnJsonStr(group2, total, totalPage, group.getPage());

		model.addAttribute("result", result);
		
		return PageUtils.json;
	}
	/**
	 * 保存
	 * @param model
	 * @param group
	 * @return
	 */
	@RequestMapping("/insert")
	public String insert(Model model, TUsergroup group){
		int result = iGroup.insert(group);
		model.addAttribute("result", result);
		return PageUtils.json;
	}
	/**
	 * 修改
	 * @param model
	 * @param group
	 * @return
	 */
	@RequestMapping("/update")
	public String update(Model model, TUsergroup group){
		int result = iGroup.update(group);
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
			if(!"1".equals(i))
				groupIds.add(Long.valueOf(i));
		}
		String tips = "";
		//用户分组菜单
		/*List<TMenuUsergroup> groups = imenugroup.query(groupIds);
		if(!groups.isEmpty()){
			for(TMenuUsergroup g : groups){
				for(Long i : groupIds){
					if(i.toString().equals(g.getFusergroupid().toString())){
						tips = "部分数据正在使用中,";
						groupIds.remove(i);
						break;
					}
				}
			}
		}*/
		
		int result = 0;
		if(!groupIds.isEmpty())
			result = iGroup.delete(groupIds);
		
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
		TUsergroup group = iGroup.selectByKey(id);
		Gson gson = new Gson();
		String result = gson.toJson(group);
		model.addAttribute("result", result);
		
		return PageUtils.json;
	}
}