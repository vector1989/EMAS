package com.evmtv.epg.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.omg.CORBA.IntHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.evmtv.epg.entity.TAdv;
import com.evmtv.epg.entity.TBranch;
import com.evmtv.epg.entity.TContract;
import com.evmtv.epg.entity.TMenuUsergroup;
import com.evmtv.epg.entity.TSource;
import com.evmtv.epg.entity.TUser;
import com.evmtv.epg.entity.TUserNode;
import com.evmtv.epg.entity.TUserNodeStatus;
import com.evmtv.epg.response.PageUtils;
import com.evmtv.epg.service.IAdv;
import com.evmtv.epg.service.IBranch;
import com.evmtv.epg.service.IContract;
import com.evmtv.epg.service.IContractAdv;
import com.evmtv.epg.service.IMenuUsergroup;
import com.evmtv.epg.service.ISource;
import com.evmtv.epg.service.IUserNode;
import com.evmtv.epg.service.IUserNodeStatus;
import com.evmtv.epg.utils.CollectionUtills;
import com.evmtv.epg.utils.DateUtils;
import com.evmtv.epg.utils.ReturnJsonUtils;
import com.evmtv.epg.utils.UserSession;
import com.google.gson.Gson;

/**
 * 流程控制
 * @author fangzhu(fangzhu@evmtv.com)
 * @time 2013-6-17 上午9:52:07
 */
@Controller
@RequestMapping("/main/myTask")
@Deprecated
public class UserNodeStatusController {
	
	@Resource IUserNodeStatus iUserNodeStatus;
	@Resource IUserNode iUserNode;
	@Resource ISource iSource;
	@Resource IContract iContract;
	@Resource IMenuUsergroup imenugroup;
	@Resource IBranch iBranch;
	@Resource IAdv iAdv;
	@Resource IContractAdv iContractAdv;
	/**
	 * 节点状态页面
	 * @return
	 */
	@RequestMapping("/index")
	public String channelsIndex(Long fmenuid,HttpServletRequest request,Model model){
		//权限判断
		TMenuUsergroup mug = UserSession.getUserMenuFreadonly(request, imenugroup, fmenuid);
		model.addAttribute("usermenu", mug);
		List<TBranch> branchs = iBranch.query(new TBranch());
		model.addAttribute("branch", branchs);
		TUser user = UserSession.loginUser(request);
		//分公司广告位
		List<TAdv> advs = iAdv.query(user.getFbranchid());
		model.addAttribute("advs", advs);
		return PageUtils.myTask;
	}
	/**
	 * 条件查询
	 * @param model
	 * @param node
	 * @return
	 */
	@RequestMapping("/query")
	public String selectByExmple(Model model,TUserNodeStatus status){
		IntHolder holder = new IntHolder();
		status.setHolder(holder);
		
		List<TUserNodeStatus> statuses = iUserNodeStatus.selectByExample(status);
		int total = status.getHolder().value;//总记录
		//总页数
		int totalPage = ReturnJsonUtils.getTotalPage(status.getLimit(), total);
		//返回字符串
		String result = ReturnJsonUtils.getReturnJsonStr(statuses, total, totalPage, status.getPage());

		model.addAttribute("result", result);
		
		return PageUtils.json;
	}
	/**
	 * 查询广告状态
	 * @param model
	 * @param fcontractid 合同索引
	 * @param sourceId 广告索引
	 * @param cmd 是否广告
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryNodeStatus")
	public String queryNodeStatus(Model model, Long fcontractid,Long sourceId, String cmd,HttpServletRequest request){
		
		//广告
		/*TSource source = null;
		if(sourceId != null)
			source = iSource.selectById(sourceId);*/
		//查询合同
		if(fcontractid == null){
//			TContractAdv cadv = iContractAdv.selectIdByResourceId(source.getFresourceid());
//			fcontractid = cadv.getFcontractid();
		}
		//合同
		TContract contract = iContract.queryByid(fcontractid);
		//广告位
		TAdv adv = iAdv.selectByKey(fcontractid);
		//分公司审核流程
		List<TUserNode> nodes = iUserNode.selectByExample(contract.getFbranchid());
		//如果分公司没有审核流程，就使用总公司流程
//		if(CollectionUtills.hasElements(nodes)){
//			nodes = iUserNode.selectByExample(1L);
//		}
		//审核状态
		List<TUserNodeStatus> status = iUserNodeStatus.selectBranchNode(contract.getFbranchid(),fcontractid);
		if(CollectionUtills.hasElements(status)){
			for(int i =0,len=nodes.size();i<len;i++){
				TUserNode node = nodes.get(i);
				for(TUserNodeStatus s : status){
					if(node.getId().toString().equals(s.getFusernodeid().toString())){
						node.setFstatus(s.getFstatus());
						node.setFcreatetime(s.getFcreatetime());
						node.setUserName(s.getUserName());
						node.setTemp(s.getFremark());
						node.setUserNodeStatusId(s.getId());
						nodes.set(i, node);
						break;
					}
				}
			}
		}
//		model.addAttribute("source", source);
		model.addAttribute("nodes", nodes);
		model.addAttribute("contract", contract);
		model.addAttribute("adv", adv);
		
		return PageUtils.statusIndex;
	}
	/**
	 * 审核
	 * @param model
	 * @param status
	 * @return
	 */
	@RequestMapping("/insert")
	private String insert(Model model,TUserNodeStatus status,HttpServletRequest request){
		TUser user = UserSession.loginUser(request);
		status.setFuserid(user.getId());
		status.setFcreatetime(DateUtils.getCurrentTime());
//		status.setFbranchid(user.getFbranchid());
		int result = 0;
		if(status.getId() != null)
			result = iUserNodeStatus.update(status);
		else
			result = iUserNodeStatus.insert(status);
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
	public String queryById(Model model, Long id){
		TUserNodeStatus status = iUserNodeStatus.selectByKey(id);
		Gson gson = new Gson();
		String result = gson.toJson(status);
		model.addAttribute("result", result);
		
		return PageUtils.json;
	}
	/**
	 * 查询审核资源
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/selectByChecked")
	public String queryByIdWithSourceOrContract(Model model,Long statusId,Long cid,Long sid){
		JSONObject json = new JSONObject();
		if(statusId != null){
			TUserNodeStatus status = iUserNodeStatus.selectByKey(statusId);
			json.put("status", status);
		}
		if(cid != null){
			//查询合同
			TContract contract = iContract.queryByid(cid);
			TAdv adv = iAdv.selectByKey(Long.valueOf(contract.getFadvlocid()));
			json.put("contract", contract);
			json.put("adv", adv);
		}
		if(sid != null){
			//查询广告资源
			TSource source = iSource.selectById(sid);
			json.put("source", source);
		}
		model.addAttribute("result", json);
		return PageUtils.json;
	}
}