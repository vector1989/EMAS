package com.evmtv.epg.utils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.evmtv.epg.entity.TAdv;
import com.evmtv.epg.entity.TMenuUsergroup;
import com.evmtv.epg.entity.TUser;
import com.evmtv.epg.service.IAdv;
import com.evmtv.epg.service.IBranchAdvClass;
import com.evmtv.epg.service.IMenuUsergroup;

/**
 * 获取当前用户
 * 
 * @author fangzhu(fangzhu@rvmtv.com)
 * @time 2012-12-10 下午3:36:55
 */
public class UserSession {
	/**
	 * 获取当前用户信息
	 * 
	 * @param request
	 * @return
	 */
	public static TUser loginUser(HttpServletRequest request) {
		return (TUser) request.getSession().getAttribute("USERLOGIN");
	}

	/**
	 * 是否为管理员
	 * 
	 * @param user
	 * @return
	 */
	public static boolean isAdmin(TUser user) {
		return user != null && "1".equals(user.getId().toString());
	}

	/**
	 * 用户操作权限
	 * 
	 * @param request
	 * @param iMenuUsergroup
	 * @param fmenuid
	 * @return
	 */
	public static TMenuUsergroup getUserMenuFreadonly(
			HttpServletRequest request, IMenuUsergroup iMenuUsergroup,
			Long fmenuid) {
		TUser user = UserSession.loginUser(request);
		TMenuUsergroup g = new TMenuUsergroup();
		if (!isAdmin(user)) {
			g = iMenuUsergroup.queryByUidAndMid(fmenuid, user.getFusergroupid());
		}else{
			g.setFreadonly("a");
		}
		return g;
	}
	/**
	 * 用户操作权限
	 * 
	 * @param user
	 * @param iMenuUsergroup
	 * @param fmenuid
	 * @return
	 */
	public static TMenuUsergroup getUserMenuFreadonly(TUser user, IMenuUsergroup iMenuUsergroup,Long fmenuid) {
		TMenuUsergroup g = new TMenuUsergroup();
		if (!isAdmin(user)) {
			g = iMenuUsergroup.queryByUidAndMid(fmenuid, user.getFusergroupid());
		}else{
			g.setFreadonly("a");
		}
		return g;
	}
	/**
	 * 当前用户可操作的广告位
	 * @param request
	 * @param iBranchAdvClass
	 * @param iAdv
	 * @return
	 */
	public static List<TAdv> getCurrentUserAdvClass(HttpServletRequest request, IBranchAdvClass iBranchAdvClass, IAdv iAdv) {
		// 广告位
		TUser user = UserSession.loginUser(request);
		Long fbranchid = user.getFbranchid();
		if(!fbranchid.toString().equals("1")){
			fbranchid = null;
		}
		List<Long> fadvclassid = iBranchAdvClass.selectByBranchId(fbranchid);
		return iAdv.query(fadvclassid);
	}
}
