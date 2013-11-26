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
import com.evmtv.epg.entity.TChannelGroup;
import com.evmtv.epg.entity.TChannels;
import com.evmtv.epg.entity.TContract;
import com.evmtv.epg.entity.TContractAdv;
import com.evmtv.epg.entity.TContractAdvResource;
import com.evmtv.epg.entity.TMenuUsergroup;
import com.evmtv.epg.entity.TNode;
import com.evmtv.epg.entity.TNodeStatus;
import com.evmtv.epg.entity.TReleaseVersion;
import com.evmtv.epg.entity.TResource;
import com.evmtv.epg.entity.TSource;
import com.evmtv.epg.entity.TSourceWithBLOBs;
import com.evmtv.epg.entity.TUser;
import com.evmtv.epg.entity.TVersionSource;
import com.evmtv.epg.response.BranchVersionSourceResponse;
import com.evmtv.epg.response.PageUtils;
import com.evmtv.epg.response.ResourceContractAdvResponse;
import com.evmtv.epg.service.IAdv;
import com.evmtv.epg.service.IBranch;
import com.evmtv.epg.service.IBranchSourceRelease;
import com.evmtv.epg.service.IChannelGroup;
import com.evmtv.epg.service.IChannels;
import com.evmtv.epg.service.IContract;
import com.evmtv.epg.service.IContractAdv;
import com.evmtv.epg.service.IContractAdvRescource;
import com.evmtv.epg.service.IMenuUsergroup;
import com.evmtv.epg.service.INode;
import com.evmtv.epg.service.INodeStatus;
import com.evmtv.epg.service.IReleaseVersion;
import com.evmtv.epg.service.IResource;
import com.evmtv.epg.service.ISource;
import com.evmtv.epg.service.ISourceChannels;
import com.evmtv.epg.service.ITimePeriod;
import com.evmtv.epg.service.IUser;
import com.evmtv.epg.service.IVersionAdv;
import com.evmtv.epg.service.IVersionSource;
import com.evmtv.epg.utils.ArraysUtils;
import com.evmtv.epg.utils.CollectionUtills;
import com.evmtv.epg.utils.DateUtils;
import com.evmtv.epg.utils.ReturnJsonUtils;
import com.evmtv.epg.utils.StringUtils;
import com.evmtv.epg.utils.UserSession;
import com.google.gson.Gson;

/**
 * 广告管理
 * @author fangzhu(fangzhu@evmtv.com)
 * @time 2013-6-17 上午9:52:07
 */
@Controller
@RequestMapping("/main/source")
public class SourceController {
	
	@Resource IAdv iAdv;
	@Resource INode iNode;
	@Resource IUser iUser;
	@Resource IBranch iBranch;
	@Resource ISource iSource;
	@Resource IChannels iChannels;
	@Resource IContract iContract;
	@Resource IResource iResource;
	@Resource INodeStatus iNodeStatus;
	@Resource ITimePeriod iTimePeriod;
	@Resource IVersionAdv iVersionAdv;
	@Resource IContractAdv iContractAdv;
	@Resource IChannelGroup iChannelGroup;
	@Resource IVersionSource iVersionSource;
	@Resource IMenuUsergroup iMenuUsergroup;
	@Resource ISourceChannels iSourceChannels;
	@Resource IReleaseVersion iReleaseVersion;
	@Resource IBranchSourceRelease iBranchSourceRelease;
	@Resource IContractAdvRescource iContractAdvRescource;
	
	/**
	 * 广告编辑页面
	 * @return
	 */
	@RequestMapping("/listIndex")
	public String index(Long fmenuid,HttpServletRequest request,Model model){
		TUser user = UserSession.loginUser(request);
		//权限判断
		TMenuUsergroup mug = UserSession.getUserMenuFreadonly(user, iMenuUsergroup, fmenuid);
		model.addAttribute("usermenu", mug);
		//版本号
		List<TReleaseVersion> rvs = iReleaseVersion.selectByBranchid(user.getFbranchid(), "HD",0);
		
		model.addAttribute("rvs", rvs);
		// 分公司
		List<TBranch> branchs = iBranch.query(new TBranch());
		model.addAttribute("branchs", branchs);
		
		return PageUtils.sourceIndex;
	}
	
	/**
	 * 广告位下或该时间段下广告素材信息
	 * @param model
	 * @param source
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryByAdvIdAndTimepreiodId")
	public String query(Model model,TVersionSource vs, TReleaseVersion rv, HttpServletRequest request){

		vs.setHolder(new IntHolder());
		//广告版本索引
		Long rvid = vs.getFreleaseversionid();
		List<TSource> sources = null;
		if(rvid == null && rv.getFstatus() != null && rv.getFstatus() == 1){
			rv = iReleaseVersion.selectMaxIdByFbranchidAndFdefinition(rv);
			if(rv != null){
				rvid = rv.getId();
			}
		}
		if(rvid != null){
			if(vs.getFadvid() == null && vs.getFtimeperiodid() == null){
				BranchVersionSourceResponse bvsr = new BranchVersionSourceResponse();
				rv = iReleaseVersion.selectByKey(rvid);
				bvsr.getBranchAdv(vs, rv, iReleaseVersion, iVersionAdv);
			}
			sources = iVersionSource.selectSourceJoinByVersionSource(vs);
		}
		
		int total = vs.getHolder().value;//总记录
		//总页数
		int totalPage = ReturnJsonUtils.getTotalPage(vs.getLimit(), total);
		//返回字符串
		String result = ReturnJsonUtils.getReturnJsonStr(sources, total, totalPage, vs.getPage());
		
		model.addAttribute("result", result);
		
		return PageUtils.json;
	}
	
	
	/**
	 * 保存
	 * @param model
	 * @param source
	 * @return
	 */
	@RequestMapping("/insert")
	public String insert(Model model, TSourceWithBLOBs s,HttpServletRequest request,int time){
		TUser user = UserSession.loginUser(request);
		
		int result = 0;
		if(time < 1) time = 1;
		
		//广告数据集合
		if(s.getId() == null){
			//数字音频背景 每张显示时间
			String duration = s.getFduration();
			String[] durations = null;
			if(StringUtils.hasText(duration))
				durations = duration.split(",");
			s.setFduration(null);
			//资源索引
			String temp = s.getTemp();
			String[] resourceIds = temp.split(",");
			//节点索引
			TNode node = new TNode();
			node.setFischecked("0");
			node.setFtype(1);
			node.setStart(0);
			node.setLimit(1);
			List<TNode> nodes = iNode.selectByExample(node);
			//节点索引
			node = nodes.get(0);
			//版本数据
			List<TVersionSource> vss = new ArrayList<TVersionSource>();
			for(int i =0,len=resourceIds.length;i<len;i++){
				String[] resourceIdArr = resourceIds[i].split("\\|");
				//素材索引
				Long rid = Long.valueOf(resourceIdArr[0]);
				//合同广告位索引
				Long caid = Long.valueOf(resourceIdArr[1]);
				//合同广告位素材索引
				Long carid = Long.valueOf(resourceIdArr[2]);
				for(int j=0;j<time;j++){
					//合同广告对象
					TContractAdv ca = iContractAdv.selectByKey(caid);
					s.setFbranchid(ca.getFbranchid());
					TSourceWithBLOBs source = toTSource(s, rid);
					source.setId(null);
					//持续显示时间
					if(StringUtils.hasText(duration))
						source.setFduration(durations[i]);//时长
					if(StringUtils.hasText(s.getFchannel())){
						source.setFexpand(s.getFchannel());//频道
						if(StringUtils.hasText(s.getFisprovincecompany()))
							source.setFisprovincecompany(s.getFisprovincecompany());//是否省公司
						source.setFfontcolor(s.getFfontcolor());
					}
					source.setFcreatetime(DateUtils.getCurrentTime());
					source.setFcreateuserid(user.getId());//创建用户
					source.setFguid(carid.toString());//广告合同素材索引
					source.setFcontractid(ca.getFcontractid());//合同索引
					result += iSource.insert(source);
				
					if(result > 0){
						//合同广告位素材
						TContractAdvResource car = new TContractAdvResource();
						car.setId(carid);
						car.setFedited("1");
						car.setFisusing("1");
						car.setFcontractadvid(caid);
						car.setFnodeid(node.getId());
						car.setForder(node.getForder());
						car.setFsourceid(source.getId());
						
						//修改
						iContractAdvRescource.update(car);
					}
					if(j == 0){
						//审批流程节点 节点状态
						TNodeStatus status = new TNodeStatus();
						status.setFstatus("1");
						status.setFremark("广告已编辑");
						status.setFcontractadvid(caid);
						status.setFuserid(user.getId());
						status.setFnodeid(node.getId());
						status.setFcontractadvresourceid(carid);
						status.setFcreatetime(DateUtils.getCurrentTime());
						status.setFcontractid(ca.getFcontractid());
						status.setFsourceid(source.getId());
						
						iNodeStatus.insert(status);
						//修改审核节点
						iContractAdvRescource.updateCheckedNodeId(carid, node.getId(),node.getForder());
					}
					TVersionSource vs = insertOrUpdateVs(s,source.getId());
					if(vs != null){
						vss.add(vs);
					}
				}
				if(CollectionUtills.hasElements(vss))
					iVersionSource.batchInsert(vss);
			}
		}else{
			insertOrUpdateVs(s,s.getId());
			result = 1;
		}

		model.addAttribute("result", result);
		return PageUtils.json;
	}
	/**
	 * 广告版本
	 * @param s
	 * @param sid
	 * @return
	 */
	private TVersionSource insertOrUpdateVs(TSourceWithBLOBs s,Long sid){
		//广告版本
		TVersionSource vs = new TVersionSource();
		if(s.getFadvid() != null)
			vs.setFadvid(s.getFadvid());
		if(StringUtils.hasText(s.getFfontcolor()))
			vs.setFchannelgroup(s.getFfontcolor());
		if(StringUtils.hasText(s.getFchannel()))
			vs.setFchannelsid(s.getFchannel());
		if(s.getFreleaseversionid() != null)
			vs.setFreleaseversionid(s.getFreleaseversionid());
		if(sid != null)
			vs.setFsourceid(sid);
		if(s.getFtimeperiodid() != null)
			vs.setFtimeperiodid(s.getFtimeperiodid());
		if(s.getCgroupsid() != null)
			vs.setFchannelgroupid(s.getCgroupsid());
		if(s.getVaid() != null)
			vs.setFversionadvid(s.getVaid());
		if(s.getVsid() != null){
			vs.setId(s.getVsid());
			iVersionSource.update(vs);
			return null;
		}
		return vs;
	}
	
	/**
	 * 修改
	 * @param model
	 * @param source
	 * @return
	 */
	@RequestMapping("/update")
	public String update(Model model, TSourceWithBLOBs s,HttpServletRequest request){
		TUser user = UserSession.loginUser(request);
		//数字音频背景 每张显示时间
		String duration = s.getFduration();
		String[] durations = null;
		if(StringUtils.hasText(duration))
			durations = duration.split(",");
		s.setFduration(null);
		//资源索引
		String temp = s.getTemp();
		int result = 0;
		//频道
		String fchannel = s.getFchannel();
		if(StringUtils.hasText(temp) || StringUtils.hasText(fchannel) || StringUtils.hasText(s.getFversion())){
			TSourceWithBLOBs source = new TSourceWithBLOBs();
			if(StringUtils.hasText(temp)){
				String[] resourceIdArr = temp.split("\\|");
				//合同广告位素材索引
				Long carid = Long.valueOf(resourceIdArr[2]);
				source = toTSource(s, Long.valueOf(resourceIdArr[0]));
				source.setFguid(carid.toString());//广告合同素材索引
			}
			source.setId(s.getId());
			//持续显示时间
			if(StringUtils.hasText(duration))
				source.setFduration(durations[0]);
			source.setFupdatetime(DateUtils.getCurrentTime());
			source.setFupdateuserid(user.getId());

			if(StringUtils.hasText(fchannel)){
				source.setFexpand(fchannel);
				source.setFfontcolor(s.getFfontcolor());
			}
			insertVersionSource(source);
			//删除已发布的数据
			iBranchSourceRelease.delete(s.getId());
			result = iSource.update(source);
		}
		model.addAttribute("result", result);
		
		return PageUtils.json;
	}
	/**
	 * 新增广告版本数据信息
	 * @param fadvid
	 * @param freleaseversionid
	 * @param fsourceid
	 * @param tid
	 * @param fchannelgroupid
	 * @param fchannelsid
	 */
	private void insertVersionSource(TSourceWithBLOBs s){
		TVersionSource vs = new TVersionSource();
		vs.setFadvid(s.getFadvid());
		vs.setFchannelgroup(s.getFfontcolor());
		vs.setFchannelsid(s.getFchannel());
		vs.setFreleaseversionid(s.getFreleaseversionid());
		vs.setFsourceid(s.getId());
		vs.setFtimeperiodid(s.getFtimeperiodid());
		vs.setFchannelgroupid(s.getCgroupsid());
		iVersionSource.insert(vs);
	}
	
	/**
	 * 插入广告频道
	 * @param fchannelsid
	 * @param sourceid
	 */
	/*private void insertSourceChannels(List<Long> fchannelsid, Long sourceid){
		//广告频道关联
		SourceChannelsRequest scr = new SourceChannelsRequest();
		scr.setFchannelsid(fchannelsid);
		scr.setFsourceid(sourceid);
		iSourceChannels.insert(scr);
	}*/
	/**
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	public String delete(Model model,Long id){
		int result = iVersionSource.delete(id);
		//删除或修改相应数据
		
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
	public String queryById(Model model, Long id,Long fpositionid){
		
		// 分公司
		JSONObject json = new JSONObject();
		if(id != null){
			//广告数据
			TSourceWithBLOBs source = iSource.queryById(id);
			json.put("source", source);
		}
		if(fpositionid == 4){
			List<TChannelGroup> cgroups = iChannelGroup.selectByExample(null);
			json.put("cgroups", cgroups);
		}
		model.addAttribute("result", json);
		
		return PageUtils.json;
	}
	/**
	 * 查询所有频道
	 * @param model
	 * @param source
	 * @return
	 */
	@RequestMapping("/selectAll")
	public String selectAll(Model model,TSourceWithBLOBs source){
		IntHolder holder = new IntHolder();
		source.setHolder(holder);
		
		List<TSourceWithBLOBs> sources = iSource.query(source);
		Gson gson = new Gson();
		String result = gson.toJson(sources);
		model.addAttribute("result", result);
		
		return PageUtils.json;
	}
	/**
	 * 加载合同
	 * @param model
	 * @param contract
	 * @param request
	 * @return
	 */
	@RequestMapping("/loadContract")
	public String loadContract(Model model,TContract contract,HttpServletRequest request){
		//当前用户
		List<TContract> contracts = iContract.query(contract);
		String result = new Gson().toJson(contracts);
		model.addAttribute("result", result);
		return PageUtils.json;
	}
	
	
	/**
	 * 转对象
	 * @param s
	 * @param rid 
	 * @return
	 */
	TSourceWithBLOBs toTSource(TSourceWithBLOBs s,Long rid){
		TResource resource = iResource.queryById(rid);
		//转对象
		JSONObject json = JSONObject.fromObject(new Gson().toJson(resource));
		TSourceWithBLOBs source = (TSourceWithBLOBs) JSONObject.toBean(json,TSourceWithBLOBs.class);
		if(s.getFadvid() != null)
			source.setFadvid(s.getFadvid());
		if(s.getFbranchid() != null)
			source.setFbranchid(s.getFbranchid());
		if(StringUtils.hasText(s.getFdefinition()))
			source.setFdefinition(s.getFdefinition());
		if(s.getFtimeperiodid() != null)
			source.setFtimeperiodid(s.getFtimeperiodid());
		if(StringUtils.hasText(s.getFversion()))
			source.setFversion(s.getFversion());
		if(rid != null)
			source.setFresourceid(rid);
		if(!StringUtils.hasText(source.getFguid())){
			source.setFguid(null);
		}
		if(s.getCgroupsid() != null)
			source.setCgroupsid(s.getCgroupsid());
		if(s.getFreleaseversionid() != null)
			source.setFreleaseversionid(s.getFreleaseversionid());
		
		return source;
	}
	
	/**
	 * 加载分公司频道信息
	 * @param model
	 * @param fbranchid 分公司索引
	 * @param cmd 1：时间段，2：频道，3：时间段和频道
	 * @param fadvid 广告位索引
	 * @param id 广告数据索引
	 * @return
	 */
	@RequestMapping("/editLoadTimeperoidOrChannel")
	public String editLoadChannel(Model model,Long fbranchid,int cmd,Long fadvid,Long id){
		//json对象
		JSONObject json = new JSONObject();
		//分公司频道
		if(cmd == 2 || cmd == 3){
			//获取分公司做大版本号
			Long rvid = iChannels.selectMaxRvIdByBranchid(fbranchid);
			List<TChannels> channels = iChannels.queryByBranchId(fbranchid,rvid);
			TChannelGroup cg = new TChannelGroup();
			if(fbranchid.toString().equals("1")){
				cg.setFisprovincecompany("1");
			}else{
				cg.setFisprovincecompany("0");
			}
			List<TChannelGroup> cgroups = iChannelGroup.selectByExample(cg);
			if(id != null){
				//广告数据
				TSourceWithBLOBs source = iSource.queryById(id);
				//当前已使用频道
				List<TChannels> userChannels = new ArrayList<TChannels>();
				List<Long> channelsid = ArraysUtils.toLongList(source.getFexpand());
				for(Long cid : channelsid){
					for(TChannels c : channels){
						if(cid.toString().equals(c.getId().toString())){
							userChannels.add(c);
							channels.remove(c);
							break;
						}
					}
				}
				json.put("userChannels", userChannels);
			}
			json.put("cgroups", cgroups);
			json.put("channel", channels);
			
		}
		model.addAttribute("result", json);
		return PageUtils.json;
	}
	
	/**
	 * 查询频道信息
	 * @param model
	 * @param fchannelsid
	 * @return
	 */
	@RequestMapping("/loadChannels")
	public String loadChannels(Model model,String fchannelsid){
		List<Long> fchannlids = ArraysUtils.toLongList(fchannelsid); 
		//频道信息
		List<TChannels> channels = iChannels.queryByIdList(fchannlids);
		Gson gson = new Gson();
		String result = gson.toJson(channels);
		model.addAttribute("result", result);
		return PageUtils.json;
	}
	/**
	 * 加载资源信息
	 * @param model
	 * @param adv
	 * @return
	 */
	@RequestMapping("/loadResource")
	public String loadResource(Model model,ResourceContractAdvResponse response){
		response.setHolder(new IntHolder());
		//节点索引
		TNode node = new TNode();
		node.setFischecked("1");
		node.setFtype(0);
		node.setStart(0);
		node.setLimit(1);
		List<TNode> nodes = iNode.selectByExample(node);
		if(CollectionUtills.hasElements(nodes)){
			response.setForder(nodes.get(0).getForder());
		}
		//获取分公司资源索引
		List<ResourceContractAdvResponse> resources = iContractAdvRescource.selectResourceByRcaresponse(response);
		
		//将集合转为json数组
		int total = response.getHolder().value;//总记录
		//总页数
		int totalPage = ReturnJsonUtils.getTotalPage(response.getLimit(), total);
		//返回字符串
		String result = ReturnJsonUtils.getReturnJsonStr(resources, total, totalPage, response.getPage());
		model.addAttribute("result", result);
		
		return PageUtils.json;
	}
	/**
	 * 查询分公司广告位
	 * @param model
	 * @param fbranchid
	 * @return
	 */
	@RequestMapping("/loadBranchAdv")
	public String loadBranchAdv(Model model, Long fbranchid){
		
		// 分公司可操作广告位
		List<TContractAdv> advs = iContractAdv.selectAdvByFbranchId(fbranchid);
		for (int i = 0; i < advs.size() - 1; i++) {
			for (int j = advs.size() - 1; j > i; j--) {
				if (advs.get(j).getFadvid().toString().equals(advs.get(i).getFadvid().toString())) {
					advs.remove(j);
				}
			}
		}
		Gson gson = new Gson();
		String result = gson.toJson(advs);
		model.addAttribute("result", result);
		return PageUtils.json;
	}
	
	/**
	 * 时间段
	 * @param model
	 * @param s
	 * @return
	 */
	@RequestMapping("/updateSourceTimeperiod")
	public String updateSourceTimeperiod(Model model,TVersionSource vs){
		
		//修改时间段
		int result = iVersionSource.update(vs);
		
		model.addAttribute("result", result);
		
		return PageUtils.json;
	}
	/**
	 * 加载分公司广告版本
	 * @param model
	 * @param rv
	 * @return
	 */
	@RequestMapping("/loadReleaseVersion")
	public String loadReleaseVersion(Model model, TReleaseVersion rv){
		List<TReleaseVersion> rvs = iReleaseVersion.selectByBranchid(rv.getFbranchid(), rv.getFdefinition(),rv.getFstatus());
		String result = new Gson().toJson(rvs);
		model.addAttribute("result", result);
		return PageUtils.json;
	}
}