/**
 * @project_name EAMS
 * @file_name BroadcastServerStatusController.java
 * @package_name com.evmtv.epg.controller
 * @date_time 2013年12月9日下午3:03:58
 * @type_name BroadcastServerStatusController
 */
package com.evmtv.epg.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.evmtv.epg.entity.TBranch;
import com.evmtv.epg.entity.TDbConfig;
import com.evmtv.epg.entity.TMenuUsergroup;
import com.evmtv.epg.entity.TUser;
import com.evmtv.epg.release.VersionDao;
import com.evmtv.epg.response.PageUtils;
import com.evmtv.epg.service.IBranch;
import com.evmtv.epg.service.IDbConfig;
import com.evmtv.epg.service.IMenuUsergroup;
import com.evmtv.epg.utils.UserSession;
import com.google.gson.Gson;

/**
 * <p>Title: 播发服务器状态</p> 
 * <p>Description: 修改播发服务器状态</p> 
 * <p>Date: 2013年12月9日下午3:03:58</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * <p>Company: www.evmtv.com</p> 
 * @author fangzhu@evmtv.com
 * @version 1.0 
 */
@Controller
@RequestMapping("/main/broadcastServer")
public class BroadcastServerStatusController {
	
	@Autowired IBranch iBranch;
	@Autowired IDbConfig iDbConfig;
	@Autowired IMenuUsergroup iMenuUsergroup;
	
	/**
	 * 返回页面
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/index")
	public String index(Model model,Long fmenuid,HttpServletRequest request){
		TUser user = UserSession.loginUser(request);
		//权限判断
		TMenuUsergroup mug = UserSession.getUserMenuFreadonly(request, iMenuUsergroup, fmenuid);
		model.addAttribute("usermenu", mug);
		if("1".equals(user.getFbranchid().toString())){
			model.addAttribute("branchs", iBranch.query(new TBranch()));
		}
		return PageUtils.broadcastServerIndex;
	}
	
	/**
	 * 查询分公司播出服务状态
	 * @param model
	 * @param fbranchid
	 * @param request
	 * @return
	 */
	@RequestMapping("/query")
	public String query(Model model,Long fbranchid,HttpServletRequest request){
		if(fbranchid == null){
			fbranchid = UserSession.loginUser(request).getFbranchid();
		}
		TDbConfig db = iDbConfig.query(fbranchid);
		model.addAttribute("result", new Gson().toJson(db));
		
		return PageUtils.json;
	}
	/**
	 * 修改服务器播放状态
	 * @param model
	 * @param db
	 * @param request
	 * @return
	 */
	@RequestMapping("/update")
	public String update(Model model,TDbConfig db,HttpServletRequest request){
		if(db.getFbranchid() == null){
			db.setFbranchid(UserSession.loginUser(request).getFbranchid());
		}
		TDbConfig dbc = iDbConfig.query(db.getFbranchid());
		VersionDao dao = new VersionDao(dbc);
		int result =  0;
		if(dao.update(db.getFplaystatus()) > 0){
			db.setId(dbc.getId());
			result = iDbConfig.update(db);
		}
		model.addAttribute("result", result);
		return PageUtils.json;
	}
}