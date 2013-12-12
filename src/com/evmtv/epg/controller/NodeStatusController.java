package com.evmtv.epg.controller;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.evmtv.epg.entity.TContract;
import com.evmtv.epg.entity.TContractAdv;
import com.evmtv.epg.entity.TContractAdvResource;
import com.evmtv.epg.entity.TNode;
import com.evmtv.epg.entity.TNodeStatus;
import com.evmtv.epg.entity.TReleaseVersion;
import com.evmtv.epg.entity.TResource;
import com.evmtv.epg.entity.TStatusCarOrRv;
import com.evmtv.epg.entity.TUser;
import com.evmtv.epg.request.SelectNode;
import com.evmtv.epg.request.Status;
import com.evmtv.epg.response.PageUtils;
import com.evmtv.epg.service.IContract;
import com.evmtv.epg.service.IContractAdv;
import com.evmtv.epg.service.IContractAdvRescource;
import com.evmtv.epg.service.INode;
import com.evmtv.epg.service.INodeStatus;
import com.evmtv.epg.service.IReleaseVersion;
import com.evmtv.epg.service.IResource;
import com.evmtv.epg.service.IStatusCarOrRv;
import com.evmtv.epg.utils.CollectionUtills;
import com.evmtv.epg.utils.DateUtils;
import com.evmtv.epg.utils.UserSession;
import com.google.gson.Gson;

/**
 * <p>Title: 流程控制</p> 
 * <p>Description: </p> 
 * <p>Date: 2013年12月3日下午4:22:20</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * <p>Company: www.evmtv.com</p> 
 * @author fangzhu@evmtv.com
 * @version 2.0
 */
@Controller
@RequestMapping("/main/nodeStatus")
public class NodeStatusController {
	
	@Resource INode iNode;
	@Resource IContract iContract;
	@Resource IResource iResource;
	@Resource INodeStatus iNodeStatus;
	@Resource IContractAdv iContractAdv;
	@Resource IStatusCarOrRv iStatusCarOrRv;
	@Resource IReleaseVersion iReleaseVersion;
	@Resource IContractAdvRescource iContractAdvRescource;
	
	/**
	 * 流程查看
	 * @param model
	 * @param id 合同广告位资源索引
	 * @param rvid 版本索引
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryContractAdvResourceStatus")
	public String queryContractAdvResourceStatus(Model model,Long id,Long cid,Long rvid,HttpServletRequest request){
		String cmd = null;
		if(rvid != null){
			cmd = "'nodeStatus','freleaseversionid',"+rvid;
			TReleaseVersion version = iReleaseVersion.selectByKey(rvid);
			model.addAttribute("version", version);
		}else{
			if(cid != null){
				cmd = "'contract','fcontractid',"+cid;
				List<TContractAdv> advs = iContractAdv.selectByContractId(cid);
				model.addAttribute("advs", advs);
			}
			if(id != null){
				cmd = "'nodeStatus','fcontractadvresourceid',"+id;
				//合同广告索引
				TContractAdvResource contractAdvResource = iContractAdvRescource.selectByKey(id);
				Long caid = contractAdvResource.getFcontractadvid();
				
				TContractAdv contractAdv = iContractAdv.selectByKey(caid);
				cid = contractAdv.getFcontractid();
				//素材
				Long rid = contractAdvResource.getFresourceid();
				if(rid == null)
					rid = contractAdvResource.getForiginalresourceid();
				if(rid != null){
					TResource resource = iResource.queryById(rid);
					model.addAttribute("resource", resource);
				}
				model.addAttribute("car", contractAdvResource);
				model.addAttribute("contractAdv", contractAdv);
			}
			//合同
			TContract contract = iContract.queryByid(cid);
			model.addAttribute("contract", contract);
		}
		//审核节点
		TNodeStatus ns = new TNodeStatus();
		ns.setFcontractadvresourceid(id);
		ns.setFreleaseversionid(rvid);
		ns.setFcontractid(cid);
		
		//该广告节点
		List<TNodeStatus> status = iNodeStatus.selectByNodeStatus(ns);

		//所有节点
		List<TNode> nodes = iNode.selectByExample(null);
		
		if(CollectionUtills.hasElements(nodes) && CollectionUtills.hasElements(status)){
			boolean bool = false;
			for(TNodeStatus s : status){
				Long sid = s.getId();
				for(TNode node : nodes){
					if(node.getId().toString().equals(s.getFnodeid().toString())){
						BeanUtils.copyProperties(node, s);
						s.setId(sid);
					}
					if("2".equals(s.getFstatus())){
						bool = true;
						break;
					}
				}
			}
			if(bool && cid == null){
				TNodeStatus tn = new TNodeStatus();
				tn.setFnodetitle("<font color='green' style='font-size: 1em;'>结&nbsp;&nbsp;束</font>");
				tn.setFremark("<font color='green' style='font-size: 1.5em;'>流程未通过，强制终止结束！</font>");
				tn.setFstatus("-1");
				status.add(tn);
			}
		}
		model.addAttribute("cmd", cmd);
		model.addAttribute("status", status);
		
		return PageUtils.statusIndex;
	}
	
	/**
	 * 修改
	 * @param model
	 * @param status
	 * @param request
	 * @return
	 */
	@RequestMapping("/checked")
	public String update(Model model,TNodeStatus status,HttpServletRequest request){
		//当前用户
		TUser user = UserSession.loginUser(request);
		status.setFuserid(user.getId());
		status.setFcreatetime(DateUtils.getCurrentTime());
		if(status.getId() == null){
			TNodeStatus s = new TNodeStatus();
			s.setFparentid(status.getFparentid());
			TNodeStatus ns = iNodeStatus.selectByExample(s).get(0);
			status.setId(ns.getId());
		}
		//更新
		iNodeStatus.update(status);
		TStatusCarOrRv scor = new TStatusCarOrRv();
		//根据父节点查询
		TNode node = null;
//		TReleaseVersion rvs = new TReleaseVersion();
		if("2".equals(status.getFstatus())){
			if(status.getFcontractadvresourceid() != null){
				//查询合同编辑节点
				node = iNode.selectByNode(SelectNode.getContractEdit());
			}else if(status.getFreleaseversionid() != null){
				Long nid = iNodeStatus.selectByKey(status.getFparentid()).getFnodeid();
				node = iNode.selectByKey(nid);
			}else{
				node = iNode.selectByNode(SelectNode.getContractEdit());
			}
			scor.setFnextnodeusergroupid(node.getFusergroupid());
			scor.setFisvalid("0");
			scor.setFdesc(status.getFremark());
			scor.setFupdateuserid(user.getId());
			scor.setFupdatetime(DateUtils.getCurrentTime());

			/*if(status.getFreleaseversionid() != null){
				rvs.setFstatus(2);
			}*/
		}else{
			TNodeStatus ss = iNodeStatus.selectByParentId(status.getId());
			Long nugid = -1L;
			if(ss != null){
				//根据父节点查询
				nugid = iNode.selectByKey(ss.getFnodeid()).getFusergroupid();
			}
			scor.setFnextnodeusergroupid(nugid);
			scor.setFisvalid("1");
			
			/*if(status.getFreleaseversionid() != null){
				rvs.setFstatus(1);
				rvs.setFisfinishededit(1);
			}*/
		}
		scor.setFcontractadvresourceid(status.getFcontractadvresourceid());
		scor.setFcontractid(status.getFcontractid());
		scor.setFreleaseversionid(status.getFreleaseversionid());
		iStatusCarOrRv.update(scor);

		/*if(status.getFreleaseversionid() != null){
			rvs.setId(status.getFreleaseversionid());
			rvs.setFcreateuserid(user.getId());
			rvs.setFdesc(DateUtils.getCurrentTime() + "："+status.getFnodetitle() + "；"+status.getFremark() +"；<br/>");
			iReleaseVersion.update(rvs);
		}*/
		String redirect = PageUtils.json;
		
		if(status.getFreleaseversionid() != null){
			TReleaseVersion rv = iReleaseVersion.selectByKey(status.getFreleaseversionid());
			//当为分公司测试完成时，返回上已正式播出的版本
			if(rv.getFisfinishededit() == 2 && rv.getFpreviousversionid() != null){
				redirect = "redirect:../sourceRelease/release?status="+status.getFstatus()+"&freleaseversionid=" + rv.getFpreviousversionid();
			}else if(rv.getFisfinishededit() == 1){
				redirect = "redirect:../sourceRelease/release?status="+status.getFstatus()+"&freleaseversionid=" + rv.getId();
			}
		}
		if(redirect.equals(PageUtils.json)){
			Status s = new Status();
			s.setResult("审核成功");
			s.setStatus(1);
			model.addAttribute("result", new Gson().toJson(s));
		}
		return redirect;
	}
	/**
	 * 处理任务
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/handleTask")
	public String handleTask(Model model,Long id){
		TStatusCarOrRv s = new TStatusCarOrRv();
		s.setId(id);
		s.setFishandle("1");
		int result = iStatusCarOrRv.updateByKey(s);
		
		model.addAttribute("result", result);
		return PageUtils.json;
	}
}