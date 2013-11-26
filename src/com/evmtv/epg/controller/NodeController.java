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
import com.evmtv.epg.entity.TNode;
import com.evmtv.epg.entity.TUsergroup;
import com.evmtv.epg.response.PageUtils;
import com.evmtv.epg.service.IMenuUsergroup;
import com.evmtv.epg.service.INode;
import com.evmtv.epg.service.IUserGroup;
import com.evmtv.epg.utils.ReturnJsonUtils;
import com.evmtv.epg.utils.UserSession;

/**
 * 流程控制
 * @author fangzhu(fangzhu@evmtv.com)
 * @time 2013-6-17 上午9:52:07
 */
@Controller
@RequestMapping("/main/node")
public class NodeController {
	
	@Resource INode iNode;
	@Resource IMenuUsergroup imenugroup;
	@Resource IUserGroup iUserGroup;
	/**
	 * 用户节点页面
	 * @return
	 */
	@RequestMapping("/index")
	public String index(Long fmenuid,HttpServletRequest request,Model model){
		//权限判断
		TMenuUsergroup mug = UserSession.getUserMenuFreadonly(request, imenugroup, fmenuid);
		model.addAttribute("usermenu", mug);
		return PageUtils.nodeIndex;
	}
	/**
	 * 条件查询
	 * @param model
	 * @param userNode
	 * @return
	 */
	@RequestMapping("/query")
	public String selectByExmple(Model model,TNode node){
		IntHolder holder = new IntHolder();
		node.setHolder(holder);
		
		List<TNode> nodes = iNode.selectByExample(node);
		int total = node.getHolder().value;//总记录
		//总页数
		int totalPage = ReturnJsonUtils.getTotalPage(node.getLimit(), total);
		//返回字符串
		String result = ReturnJsonUtils.getReturnJsonStr(nodes, total, totalPage, node.getPage());

		model.addAttribute("result", result);
		
		return PageUtils.json;
	}
	/**
	 * 保存
	 * @param model
	 * @param userNode
	 * @return
	 */
	@RequestMapping("/insert")
	public String insert(Model model, TNode node){
		if(!StringUtils.hasText(node.getFisprovincecompany())){
			node.setFisprovincecompany("0");
		}
		node.setForder(iNode.maxOrder() + 1);
		
		int result = iNode.insert(node);
		model.addAttribute("result", result);
		return PageUtils.json;
	}
	/**
	 * 修改
	 * @param model
	 * @param userNode
	 * @return
	 */
	@RequestMapping("/update")
	public String update(Model model, TNode node){
		if(!StringUtils.hasText(node.getFisprovincecompany())){
			node.setFisprovincecompany("0");
		}
		int result = iNode.update(node);
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
			result = iNode.delete(groupIds);
		
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
		List<TUsergroup> usergroup = iUserGroup.selectByExample(null);
		if(id != null){
			TNode node = iNode.selectByKey(id);
			json.put("node", node);
		}
		json.put("usergroups", usergroup);
		model.addAttribute("result", json);
		
		return PageUtils.json;
	}
}