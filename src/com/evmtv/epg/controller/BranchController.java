package com.evmtv.epg.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.omg.CORBA.IntHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.evmtv.epg.entity.TAdv;
import com.evmtv.epg.entity.TAdvClass;
import com.evmtv.epg.entity.TBranch;
import com.evmtv.epg.entity.TBranchAdvclass;
import com.evmtv.epg.entity.TBranchWithBLOBs;
import com.evmtv.epg.entity.TMenuUsergroup;
import com.evmtv.epg.entity.TUser;
import com.evmtv.epg.response.PageUtils;
import com.evmtv.epg.response.VersionAdvResponse;
import com.evmtv.epg.service.IAdv;
import com.evmtv.epg.service.IAdvClass;
import com.evmtv.epg.service.IBranch;
import com.evmtv.epg.service.IBranchAdvClass;
import com.evmtv.epg.service.IMenuUsergroup;
import com.evmtv.epg.service.IUser;
import com.evmtv.epg.service.IVersionAdv;
import com.evmtv.epg.utils.CollectionUtills;
import com.evmtv.epg.utils.DateUtils;
import com.evmtv.epg.utils.ReturnJsonUtils;
import com.evmtv.epg.utils.UserSession;
import com.google.gson.Gson;

/**
 * 频点管理
 * @author fangzhu(fangzhu@evmtv.com)
 * @time 2013-6-17 上午9:52:07
 */
@Controller
@RequestMapping("/main/branch")
public class BranchController {

	@Resource IAdv iAdv;
	@Resource IUser iUser;
	@Resource IBranch iBranch;
	@Resource IAdvClass iAdvClass;
	@Resource IVersionAdv iVersionAdv;
	@Resource IMenuUsergroup iMenuUsergroup;
	@Resource IBranchAdvClass iBranchAdvClass;
	/**
	 * 频点页面
	 * @return
	 */
	@RequestMapping("/index")
	public String channelsIndex(Long fmenuid,HttpServletRequest request,Model model){
		//权限判断
		TMenuUsergroup mug = UserSession.getUserMenuFreadonly(request,iMenuUsergroup, fmenuid);
		model.addAttribute("usermenu", mug);
		return PageUtils.branchIndex;
	}
	/**
	 * 条件查询
	 * @param model
	 * @param branch
	 * @return
	 */
	@RequestMapping("/query")
	public String selectByExmple(Model model,TBranch branch,HttpServletRequest request){
		IntHolder holder = new IntHolder();
		branch.setHolder(holder);
		
		TUser user = UserSession.loginUser(request);
		TBranchAdvclass ba = new TBranchAdvclass();
		if(!user.getFbranchid().toString().equals("1")){
			ba.setFbranchid(user.getFbranchid());
		}
		//分公司
		List<TBranch> branchs = iBranch.query(branch);
		for(TBranch b : branchs){
			//分公司广告位
			List<TAdv> advs = iVersionAdv.selectAdvByMaxReleaseVersionid(new VersionAdvResponse(b.getId(), "HD"));//iAdv.query(b.getId());
			if(CollectionUtills.hasElements(advs)){
				advs = iAdv.query(b.getId(), "HD");
			}
			List<TAdv> advsd = iVersionAdv.selectAdvByMaxReleaseVersionid(new VersionAdvResponse(b.getId(), "SD"));//iAdv.query(b.getId());
			if(CollectionUtills.hasElements(advsd)){
				advsd = iAdv.query(b.getId(), "SD");
			}
			advs.addAll(advsd);
			StringBuilder sb = new StringBuilder();
			boolean bool = true;
			for(TAdv a : advs){
				//版本号
				if("HD".equals(a.getFdefinition()))
					b.setFguid(a.getFguid());
				else
					b.setFupdatetime(a.getFguid());
				if(a != null){
					if("SD".equals(a.getFdefinition()) && bool){
						sb.append("<br/>");
						bool = false;
					}
					sb.append(a.getFtype()+"["+a.getFdefinition()+"]").append(" ");
				}
			}
			
			if(sb.length() > 0){
				b.setTemp(sb.toString());//sb.substring(0, sb.length()-1)
			}
		}
		
		int total = branch.getHolder().value;//总记录
		//总页数
		int totalPage = ReturnJsonUtils.getTotalPage(branch.getLimit(), total);
		//返回字符串
		String result = ReturnJsonUtils.getReturnJsonStr(branchs, total, totalPage, branch.getPage());

		model.addAttribute("result", result);
		
		return PageUtils.json;
	}
	/**
	 * 保存
	 * @param model
	 * @param branch
	 * @return
	 */
	@RequestMapping("/insert")
	public String insert(Model model, TBranchWithBLOBs branch,HttpServletRequest request){
		int result = iBranch.insert(branch);
		
		batchAdv(branch.getId(),UserSession.loginUser(request).getId());
		
		model.addAttribute("result", result);
		return PageUtils.json;
	}
	/**
	 * 修改
	 * @param model
	 * @param branch
	 * @return
	 */
	@RequestMapping("/update")
	public String update(Model model, TBranchWithBLOBs branch,HttpServletRequest request){
		int result = iBranch.update(branch);
		
		List<TAdv> advs = iAdv.query(branch.getId());
		if(!CollectionUtills.hasElements(advs)){
			batchAdv(branch.getId(),UserSession.loginUser(request).getId());
		}
		
		model.addAttribute("result", result);
		return PageUtils.json;
	}
	/**
	 * 批量插入广告位
	 * @return
	 */
	private int batchAdv(Long fbranchid,Long uid){
		List<TAdvClass> advcs = iAdvClass.selectByExample(null);
		List<TAdv> advs = new ArrayList<TAdv>();
		String[] definitions = {"HD","SD"};
		for(TAdvClass as : advcs){
			for(String defin : definitions){
				int pos = as.getFpositionid();
				if(pos == 7 || pos == 2 && "SD".equals(defin)){
					continue;
				}
				TAdv a = new TAdv();
				a.setFadvclassid(as.getId());
				a.setFbranchid(fbranchid);
				a.setFcreatetime(DateUtils.getCurrentTime());
				a.setFcreateuserid(uid);
				a.setFdefinition(defin);
				a.setFpositionid(as.getFpositionid());
				a.setFtype(as.getFtype());
				advs.add(a);
			}
		}
		return iAdv.batchInsert(advs);
	}
	/**
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	public String delete(Model model,String id){
		List<Long> ids = new ArrayList<Long>();
		String [] idArr = id.split(",");
		for(String i : idArr){
			if(!"1".equals(i))
				ids.add(Long.valueOf(i));
		}
		String tips = "";
		List<TUser> users = iUser.query(ids);
		if(!users.isEmpty()){
			for(TUser u : users){
				for(Long i : ids){
					if(i.toString().equals(u.getFbranchid().toString())){
						ids.remove(i);
						tips = "部分数据使用中,";
						break;
					}
				}
			}
		}
		int result = 0;
		if(!ids.isEmpty())
			result = iBranch.delete(ids);

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
		TBranch branch = iBranch.queryById(id);
		Gson gson = new Gson();
		String result = gson.toJson(branch);
		model.addAttribute("result", result);
		
		return PageUtils.json;
	}
}