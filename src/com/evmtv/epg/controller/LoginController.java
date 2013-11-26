package com.evmtv.epg.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.evmtv.epg.entity.TUser;
import com.evmtv.epg.entity.TUsergroup;
import com.evmtv.epg.response.PageUtils;
import com.evmtv.epg.service.IBranch;
import com.evmtv.epg.service.IUser;
import com.evmtv.epg.service.IUserGroup;
import com.evmtv.epg.utils.UserSession;
/**
 * 
 * @author fangzhu(fangzhu@evmtv.com)
 * @time 2013-6-3 下午9:03:18
 */
@Controller
@RequestMapping("/")
public class LoginController {
	
	@Resource IUser iUserService;
	@Resource IUserGroup iUserGroup;
	@Resource IBranch iBranch;
	
	@RequestMapping(method = RequestMethod.GET)
	public String LoginIndex(){
		return PageUtils.loginIndex;
	}
	
	/**
	 * 
	 * @param request
	 * @param model
	 * @param user
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String loginChecked(HttpServletRequest request,Model model,TUser user){
		String error = "";
		if(!StringUtils.hasText(user.getFusername())){
			error = "用户名不能为空";
		}else{
			TUser u = iUserService.loginCheck(user.getFusername());
			if(null == u){
				error = "用户名不存在";//+user.getFusername();
			}else if(!u.getFpassword().equals(user.getFpassword())){
				error = "用户名或密码错误";
			}else{
				TUsergroup usergroup = iUserGroup.selectByKey(u.getFusergroupid());
				u.setFtype(usergroup.getFtype());
				u.setFguid(usergroup.getFname());
				u.setUsergroup(usergroup.getFname());
				u.setBranch(iBranch.queryById(u.getFbranchid()).getFname());
				request.getSession().setAttribute("USERLOGIN", u);
			}
		}
		boolean bool = StringUtils.hasText(error);
		if(bool){
			model.addAttribute("error", error);
			return PageUtils.loginIndex;
		}		
		return "redirect:main/index";
	}
	
	@RequestMapping("main/logout")
	public String logout(HttpServletRequest request,Model model){
		if(null!=UserSession.loginUser(request)){
			request.getSession().removeAttribute("USERLOGIN");
			request.getSession().invalidate();
		}
		return PageUtils.loginIndex;
	}
}
