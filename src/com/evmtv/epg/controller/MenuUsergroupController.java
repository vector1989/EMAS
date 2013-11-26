/**
 *
 * @author fangzhu@evmtv.com
 * @time 2013-7-1 下午6:22:11
 */
package com.evmtv.epg.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.evmtv.epg.entity.TMenu;
import com.evmtv.epg.entity.TMenuUsergroup;
import com.evmtv.epg.entity.TUser;
import com.evmtv.epg.response.PageUtils;
import com.evmtv.epg.service.IMenu;
import com.evmtv.epg.service.IMenuUsergroup;
import com.evmtv.epg.utils.UserSession;
import com.google.gson.Gson;

/**
 * @author fangzhu@evmtv.com
 * @time 2013-7-1 下午6:22:11
 * @project EAMS
 * @package com.evmtv.epg.controller 
 * @fileName MenuUsergroupController.java
 * @enclosing_type 
 * @type_name MenuUsergroupController TODO
 */
@Controller
@RequestMapping("/main/menuUser")
public class MenuUsergroupController {
	
	@Resource
	IMenuUsergroup iUsergroup;
	@Resource
	IMenu iMenu;
	/**
	 * 保存
	 * @param model
	 * @param str
	 * @param fusergroupid 用户分组id
	 * @return
	 */
	@RequestMapping("/insert")
	public String insert(Model model,String str,Long fusergroupid){
		String[] ms = str.split(",");//用户分组菜单数据
		TMenuUsergroup group = null;
		int result = 0;
		if(ms.length > 0){
			iUsergroup.delete(fusergroupid);//根据分组id删除用户菜单
			for(String m : ms){
				String[] km = m.split(":");
				group = new TMenuUsergroup(null, fusergroupid, Long.valueOf(km[0]), km[1]);
				result += iUsergroup.insert(group);
			}
		}
		model.addAttribute("result", result);
		return PageUtils.json;
	}
	
	/**
	 * 查询菜单
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/query")
	public String queryMenu(Model model,Long fusergroupid){
		//当前用户菜单
		List<TMenuUsergroup> groups = iUsergroup.selectByExample(new TMenuUsergroup(null, fusergroupid, null, null));
		//当前用户分组菜单是否为空
		boolean bool = groups != null && !groups.isEmpty();
		//所有菜单
		List<TMenu> menus = iMenu.query(null);
		for(int i=0,len=menus.size();i<len;i++){
			TMenu menu  = menus.get(i);
			if(bool && menu.getFparentid() != -1)
				for(TMenuUsergroup group : groups){
					if(group.getFmenuid().toString().equals(menu.getId().toString())){
						menu.setTemp(group.getFreadonly());
//						menus.set(i, menu);
						break;
					}
				}
		}
		
		Gson gson = new Gson();
		String result = gson.toJson(menus);
		model.addAttribute("result", result);
		
		return PageUtils.json;
	}
	/**
	 * 查询当前用户菜单
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryUserMenu")
	public String queryUserMenu(Model model,HttpServletRequest request){
		TUser user = UserSession.loginUser(request);
		//用户菜单
		List<TMenu> menus = null;
		if(user != null && "1".equals(user.getFusergroupid().toString())){
			menus = iMenu.query(null);
			for(TMenu menu : menus){
				if("1".equals(menu.getId().toString())){
					menus.remove(menu);
					break;
				}
			}
		}else{
			//该用户所有菜单
			List<TMenuUsergroup> groups = iUsergroup.selectByExample(new TMenuUsergroup(null, user.getFusergroupid(), null, null));
			//当前用户所用菜单
			List<Long> menuIds = new ArrayList<Long>();
			for(TMenuUsergroup group:groups){
				if(!"1".equals(user.getFusergroupid().toString()))
					menuIds.add(group.getFmenuid());
			}
			menus = iMenu.query(menuIds);
			
			/*if("1".equals(user.getFusergroupid().toString())){
				for(TMenu menu : menus){
					if("1".equals(menu.getId())){
						menus.remove(menu);
						break;
					}
				}
			}*/
		}

		Gson gson = new Gson();
		String result = gson.toJson(menus);
		model.addAttribute("result", result);
		
		return PageUtils.json;
	}
}
