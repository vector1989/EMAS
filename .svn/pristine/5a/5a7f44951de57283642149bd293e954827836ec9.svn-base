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

import com.evmtv.epg.entity.TBranch;
import com.evmtv.epg.entity.TMenuUsergroup;
import com.evmtv.epg.entity.TNode;
import com.evmtv.epg.entity.TUserNode;
import com.evmtv.epg.entity.TUsergroup;
import com.evmtv.epg.response.PageUtils;
import com.evmtv.epg.service.IBranch;
import com.evmtv.epg.service.IMenuUsergroup;
import com.evmtv.epg.service.INode;
import com.evmtv.epg.service.IUserGroup;
import com.evmtv.epg.service.IUserNode;
import com.evmtv.epg.utils.ReturnJsonUtils;
import com.evmtv.epg.utils.UserSession;

/**
 * 流程控制
 * @author fangzhu(fangzhu@evmtv.com)
 * @time 2013-6-17 上午9:52:07
 */
@Controller
//@RequestMapping("/main/userNode")
@Deprecated
public class UserNodeController {
	
	@Resource IUserNode iUserNode;
	@Resource IMenuUsergroup imenugroup;
	@Resource INode iNode;
	@Resource IBranch iBranch;
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
		List<TBranch> branchs = iBranch.query(new TBranch());
		model.addAttribute("branchs", branchs);
		return PageUtils.userNodeIndex;
	}
	/**
	 * 条件查询
	 * @param model
	 * @param userNode
	 * @return
	 */
	@RequestMapping("/query")
	public String selectByExmple(Model model,TUserNode userNode){
		IntHolder holder = new IntHolder();
		userNode.setHolder(holder);
		
		List<TUserNode> nodes = iUserNode.selectByExample(userNode);
		int total = userNode.getHolder().value;//总记录
		//总页数
		int totalPage = ReturnJsonUtils.getTotalPage(userNode.getLimit(), total);
		//返回字符串
		String result = ReturnJsonUtils.getReturnJsonStr(nodes, total, totalPage, userNode.getPage());

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
	public String insert(Model model, TUserNode userNode){
		int order = iUserNode.count(userNode.getFbranchid());
		userNode.setForder(order);
		int result = iUserNode.insert(userNode);
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
	public String update(Model model, TUserNode userNode){
		int result = iUserNode.update(userNode);
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
		/*//用户分组菜单
		List<TMenuUsergroup> groups = imenugroup.query(groupIds);
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
			result = iUserNode.delete(groupIds);
		
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
		//分公司
		List<TBranch> branchs = iBranch.query(new TBranch());
		//节点
		List<TNode> nodes = iNode.selectByExample(null);
		//用户组
		List<TUsergroup> usergroups = iUserGroup.selectByExample(null);
		//json对象
		JSONObject json = new JSONObject();
		if(id != null){
			//分公司节点
			TUserNode userNode = iUserNode.selectByKey(id);
			json.put("userNode", userNode);
		}
		json.put("usergroups", usergroups);
		json.put("branchs", branchs);
		json.put("nodes", nodes);
		model.addAttribute("result", json);
		
		return PageUtils.json;
	}
	
	public String order(Model model,int opt,Long id){
		
		return PageUtils.json;
	}
}