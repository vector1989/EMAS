package com.evmtv.epg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.omg.CORBA.IntHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.evmtv.epg.entity.TBranch;
import com.evmtv.epg.entity.TContract;
import com.evmtv.epg.entity.TMenuUsergroup;
import com.evmtv.epg.entity.TNode;
import com.evmtv.epg.entity.TNodeStatus;
import com.evmtv.epg.entity.TSource;
import com.evmtv.epg.entity.TStatusCarOrRv;
import com.evmtv.epg.entity.TUser;
import com.evmtv.epg.response.ContractQueryResponse;
import com.evmtv.epg.response.MyTask;
import com.evmtv.epg.response.PageUtils;
import com.evmtv.epg.service.IBranch;
import com.evmtv.epg.service.IContract;
import com.evmtv.epg.service.IMenuUsergroup;
import com.evmtv.epg.service.INode;
import com.evmtv.epg.service.INodeStatus;
import com.evmtv.epg.service.ISource;
import com.evmtv.epg.service.IStatusCarOrRv;
import com.evmtv.epg.utils.ReturnJsonUtils;
import com.evmtv.epg.utils.StringUtils;
import com.evmtv.epg.utils.UserSession;
/**
 * 
 * TODO
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type 
 * @project_name EAMS
 * @file_name MyTaskController.java
 * @package_name com.evmtv.epg.controller
 * @date_time 2013年11月6日上午10:20:10
 * @type_name MyTaskController
 */
@Controller
@RequestMapping("/main/source")
public class MyTaskController {

	@Resource INode iNode;
	@Resource IBranch iBranch;
	@Resource ISource iSource;
	@Resource IContract iContract;
	@Resource INodeStatus iNodeStatus;
	@Resource IStatusCarOrRv iStatusCarOrRv;
	@Resource IMenuUsergroup iMenuUsergroup;
	/**
	 * 我的任务
	 * @return
	 */
	@RequestMapping("/myTaskIndex")
	public String channelsIndex(Long fmenuid,HttpServletRequest request,Model model){
		TUser user = UserSession.loginUser(request);
		//权限判断
		TMenuUsergroup mug = UserSession.getUserMenuFreadonly(user, iMenuUsergroup, fmenuid);
		model.addAttribute("usermenu", mug);
		if("1".equals(user.getFbranchid().toString())){
			List<TBranch> branchs = iBranch.query(new TBranch());
			model.addAttribute("branchs", branchs);
		}
		//当前用可操作节点
		TNode node = iNode.selectNodeByUsergroupId(user.getFusergroupid());
		model.addAttribute("node", node);
		
		return PageUtils.myTask;
	}
	/**
	 * 我的任务
	 * @param model
	 * @param nid 节点索引
	 * @param record
	 * @return
	 */
	@RequestMapping("/myTask")
	public String selectMyTask(Model model, Long nid, TStatusCarOrRv record,HttpServletRequest request){
		IntHolder holder = new IntHolder();
		record.setHolder(holder);
		
		List<MyTask> tasks = null;
		int total = 0;
		TNode node = iNode.selectByKey(nid);
		
		TUser user = UserSession.loginUser(request);
		
		StringBuilder otherTotal = new StringBuilder();
		//我的任务
		if(node != null){
			//类型
			Integer type = Integer.valueOf(node.getFtype());
			record.setFnextnodeusergroupid(node.getFusergroupid());
			TStatusCarOrRv s = new TStatusCarOrRv();
			s.setFnextnodeusergroupid(node.getFusergroupid());
			if("0".equals(node.getFisprovincecompany())){
				record.setFbranchid(user.getFbranchid());
				s.setFbranchid(user.getFbranchid());
			}
			if("0".equals(record.getFisvalid()+"")){
				s.setFisvalid("1");
			}else{
				s.setFisvalid("0");
			}
			if((type == 0)){
				if("1".equals(record.getFisvalid()+"")){
					//合同、未通过的素材和未通过合同
					record.setFcontractid(1L);
					record.setFisvalid(null);
					//未通过素材数量
					s.setFcontractadvresourceid(1L);
					otherTotal.append("待解决“回退素材”总数：").append(iStatusCarOrRv.countMyTask(s));
				}else{
					//审核时：未通过素材
					record.setFcontractadvresourceid(1L);
					//未通过素材数量
					s.setFcontractid(1L);
					s.setFisvalid(null);
					otherTotal.append("待解决“正常流程和回退合同”总数：").append(iStatusCarOrRv.countMyTask(s));
				}
			}else if(type == 1){
				if("1".equals(record.getFisvalid()+"")){
					//待编辑素材
					record.setFcontractadvresourceid(1L);
					//回退版本
					s.setFreleaseversionid(1L);
					otherTotal.append("待解决“回退版本”总数：").append(iStatusCarOrRv.countMyTask(s));
				}else{
					//未通过的版本
					record.setFreleaseversionid(1L);
					//待编辑素材
					s.setFcontractadvresourceid(1L);
					otherTotal.append("待解决“待编辑素材”总数：").append(iStatusCarOrRv.countMyTask(s));
				}
			}else{
				//待处理版本
				record.setFreleaseversionid(1L);
			}
			
			tasks = iStatusCarOrRv.selectMyTask(record);
			total = record.getHolder().value;//总记录
		}
		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("node", node);
		map.put("tasks", tasks);
		map.put("otherTotal", otherTotal.append(" 条信息").toString());
		//总页数
		int totalPage = ReturnJsonUtils.getTotalPage(record.getLimit(), total);
		//返回字符串
		String result = ReturnJsonUtils.getReturnJsonStr(map, total, totalPage, record.getPage());

		model.addAttribute("result", result);
		
		return PageUtils.json;
	}
	/**
	 * 条件查询
	 * @param model
	 * @param source
	 * @return
	 */
//	@RequestMapping("/myTask")
	@Deprecated
	public String selectMyTask(Model model,TSource source, Long nid,HttpServletRequest request){
		IntHolder holder = new IntHolder();
		source.setHolder(holder);
		
		//当前用户
		TUser user = UserSession.loginUser(request);
		
		List<TSource> sources = null;
		int total = 0;

		TNode node = iNode.selectByKey(nid);
		//我的任务
		if(!StringUtils.hasText(source.getFdeleted())){
			if(node != null){
				Long nodeid = node.getId();
				int order = node.getForder();
				source.setNodeid(nodeid);
				source.setForder(order - 1);
				source.setFusergroupid(null);
				if(source.getFbranchid() == null){
					source.setFbranchid(user.getFbranchid());
				}
				sources = iSource.selectMyTask(source);
				total = source.getHolder().value;//总记录
			}
		}else{
			//我发起的任务
			source.setFcreateuserid(user.getId());
			sources = iSource.selectMyTask(source);
			total = source.getHolder().value;//总记录
		}
		//对象
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("node", node);
		map.put("source", sources);
		//总页数
		int totalPage = ReturnJsonUtils.getTotalPage(source.getLimit(), total);
		//返回字符串
		String result = ReturnJsonUtils.getReturnJsonStr(map, total, totalPage, source.getPage());

		model.addAttribute("result", result);
		
		return PageUtils.json;
	}
	/**
	 * 我操作过的任务
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/iOperateInfo")
	public String iOperateInfo(Model model,Long fbranchid,TNodeStatus s,TContract c,HttpServletRequest request){
		//当前用户
		TUser user = UserSession.loginUser(request);
		
		IntHolder holder = new IntHolder();
		//当前用户分组可用节点
//		TNode node = getUsergroupNode(ugid);
		TNode node = iNode.selectByKey(s.getFnodeid());
		//节点类型  0:合同    1：广告   2：广告版本
		String ftype = node.getFtype();
		//状态  0:编辑，1：审核，2：发布
		String fischecked = node.getFischecked();
		
		//总记录数
		int total = 0;
		//数据集合
		Object list = null;
		boolean bool = false;
		if("0".equals(ftype)&& "0".equals(fischecked)){
			bool = true;
			//查询合同
			ContractQueryResponse response = new ContractQueryResponse();
			c.setFbranchid(fbranchid);
			c.setHolder(holder);
			list = response.selectContract(c, user, iContract);
			total = c.getHolder().value;
		}else{
			s.setHolder(holder);
			s.setFuserid(user.getId());
			list = iNodeStatus.selectByUserid(s);
			total = s.getHolder().value;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("bool", bool);
		//总页数
		int totalPage = ReturnJsonUtils.getTotalPage(s.getLimit(), total);
		//返回字符串
		String result = ReturnJsonUtils.getReturnJsonStr(map, total, totalPage, s.getPage());
		
		System.out.println(result);
		
		model.addAttribute("result", result);
		return PageUtils.json;
	}
}