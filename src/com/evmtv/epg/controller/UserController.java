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

import com.evmtv.epg.entity.TMenuUsergroup;
import com.evmtv.epg.entity.TBranch;
import com.evmtv.epg.entity.TUser;
import com.evmtv.epg.entity.TUserWithBLOBs;
import com.evmtv.epg.entity.TUsergroup;
import com.evmtv.epg.response.PageUtils;
import com.evmtv.epg.service.IMenuUsergroup;
import com.evmtv.epg.service.IBranch;
import com.evmtv.epg.service.IUser;
import com.evmtv.epg.service.IUserGroup;
import com.evmtv.epg.utils.ReturnJsonUtils;
import com.evmtv.epg.utils.UserSession;

/**
 * 用户管理
 * @author fangzhu(fangzhu@evmtv.com)
 * @time 2013-6-17 上午9:52:07
 */
@Controller
@RequestMapping("/main/user")
public class UserController {

	@Resource
	IUser iUser;
	@Resource
	IBranch iBranch;
	@Resource
	IUserGroup iUserGroup;
	@Resource
	IMenuUsergroup iMenuUsergroup;
	/**
	 * 频点页面
	 * @return
	 */
	@RequestMapping("/index")
	public String channelsIndex(Long fmenuid,HttpServletRequest request,Model model){
		//权限判断
		TMenuUsergroup mug = UserSession.getUserMenuFreadonly(request,iMenuUsergroup, fmenuid);
		model.addAttribute("usermenu", mug);
		return PageUtils.userIndex;
	}
	
	/**
	 * 转向页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/userSetting")
	public String userSetting(Model model){
		
		return PageUtils.userSetting;
	}
	/**
	 * 条件查询
	 * @param model
	 * @param user
	 * @return
	 */
	@RequestMapping("/query")
	public String selectByExmple(Model model,TUser user){
		IntHolder holder = new IntHolder();
		user.setHolder(holder);
		
		List<TUserWithBLOBs> user2 = iUser.query(user);
		
		//用户组
		TUsergroup usergroup = new TUsergroup();
		usergroup.setHolder(new IntHolder());
		List<TUsergroup> usergroups = iUserGroup.selectByExample(usergroup);
		
		//区域
		TBranch branch = new TBranch();
		branch.setHolder(new IntHolder());
		List<TBranch> branchs = iBranch.query(branch);
		
		for(int i=0,len=user2.size();i<len;i++){
			TUserWithBLOBs bs = user2.get(i);
			for(TBranch r:branchs){
				Long gid = bs.getFbranchid();
				if(gid != null && r.getId().toString().equals(gid.toString())){
					bs.setBranch(r.getFname());
					user2.set(i, bs);
					break;
				}
			}
			for(TUsergroup g:usergroups){
				if(g.getId().toString().equals(bs.getFusergroupid().toString())){
					bs.setUsergroup(g.getFname());
					user2.set(i, bs);
					break;
				}
			}
		}
		
		int total = user.getHolder().value;//总记录
		//总页数
		int totalPage = ReturnJsonUtils.getTotalPage(user.getLimit(), total);
		//返回字符串
		String result = ReturnJsonUtils.getReturnJsonStr(user2, total, totalPage, user.getPage());

		model.addAttribute("result", result);
		
		return PageUtils.json;
	}
	/**
	 * 保存
	 * @param model
	 * @param user
	 * @return
	 */
	@RequestMapping("/insert")
	public String insert(Model model, TUserWithBLOBs user){
		int result = iUser.insert(user);
		model.addAttribute("result", result);
		return PageUtils.json;
	}
	/**
	 * 修改
	 * @param model
	 * @param user
	 * @return
	 */
	@RequestMapping("/update")
	public String update(Model model, TUserWithBLOBs user){
		int result = iUser.update(user);
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
		List<Long> uids = new ArrayList<Long>();
		String [] idArr = id.split(",");
		for(String i : idArr){
			uids.add(Long.valueOf(i));
		}
		iMenuUsergroup.deleteByUid(uids);
		int result = iUser.delete(uids);
		model.addAttribute("result", result);
		return PageUtils.json;
	}
	/**
	 * 根据id查询
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/selectByKey")
	public String queryById(Model model, Long id,HttpServletRequest request){
		//当前用户
		TUser sessionUser = UserSession.loginUser(request);
		TUserWithBLOBs user = null;
		if(id != null){
			user = iUser.queryById(id);
		}
		//用户组
		List<TUsergroup> usergroups = iUserGroup.selectByExample(null);

		JSONObject json = new JSONObject();
		json.put("groups", usergroups);
		json.put("user", user);
		
		//公司
		if("1".equals(sessionUser.getFbranchid().toString())){
			List<TBranch> branchs = iBranch.query(new TBranch());
			json.put("branchs", branchs);
		}
		
		model.addAttribute("result", json);
		
		return PageUtils.json;
	}
	
}