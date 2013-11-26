package com.evmtv.epg.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.omg.CORBA.IntHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.evmtv.epg.entity.TBranch;
import com.evmtv.epg.entity.TChannelGroup;
import com.evmtv.epg.entity.TChannels;
import com.evmtv.epg.entity.TIncFile;
import com.evmtv.epg.entity.TMenuUsergroup;
import com.evmtv.epg.entity.TUser;
import com.evmtv.epg.request.ChannelRequest;
import com.evmtv.epg.request.Status;
import com.evmtv.epg.response.PageUtils;
import com.evmtv.epg.service.IBranch;
import com.evmtv.epg.service.IChannelGroup;
import com.evmtv.epg.service.IChannels;
import com.evmtv.epg.service.IIncFile;
import com.evmtv.epg.service.IMenuUsergroup;
import com.evmtv.epg.utils.ArraysUtils;
import com.evmtv.epg.utils.CollectionUtills;
import com.evmtv.epg.utils.FileUtils;
import com.evmtv.epg.utils.ReturnJsonUtils;
import com.evmtv.epg.utils.UserSession;
import com.evmtv.epg.utils.XmlUtils;
import com.google.gson.Gson;

/**
 * 频道管理
 * @author fangzhu(fangzhu@evmtv.com)
 * @time 2013-6-17 上午9:52:07
 */
@Controller
@RequestMapping("/main/channels")
public class ChannelsController {
	
	@Resource IBranch iBranch;
	@Resource IIncFile iIncFile;
	@Resource IChannels iChannels;
	@Resource IChannelGroup iChannelGroup;
	@Resource IMenuUsergroup iMenuUsergroup;
	/**
	 * 频道页面
	 * @return
	 */
	@RequestMapping("/listIndex")
	public String channelsIndex(Long fmenuid,HttpServletRequest request,Model model){
		//权限判断
		TMenuUsergroup mug = UserSession.getUserMenuFreadonly(request, iMenuUsergroup, fmenuid);
		model.addAttribute("usermenu", mug);
		// 分公司
		List<TBranch> branchs = iBranch.query(new TBranch());
		model.addAttribute("branchs", branchs);
		return PageUtils.channelsIndex;
	}
	/**
	 * 条件查询
	 * @param model
	 * @param channels
	 * @return
	 */
	@RequestMapping("/query")
	public String selectByExmple(Model model,TChannels channels,HttpServletRequest request){
		IntHolder holder = new IntHolder();
		channels.setHolder(holder);
		if(channels.getFbranchid() == null){
			//当前用户
			TUser user = UserSession.loginUser(request);
			channels.setFbranchid(user.getFbranchid());
		}
		//获取分公司频道最大版本号
		Long rvid = iChannels.selectMaxRvIdByBranchid(channels.getFbranchid());
		if(rvid != null)
			channels.setFreleaseversionid(rvid);
		
		List<TChannels> channels2 = iChannels.selectByExample(channels);
		
		int total = channels.getHolder().value;//总记录
		//总页数
		int totalPage = ReturnJsonUtils.getTotalPage(channels.getLimit(), total);
		//返回字符串
		String result = ReturnJsonUtils.getReturnJsonStr(channels2, total, totalPage, channels.getPage());

		model.addAttribute("result", result);
		
		return PageUtils.json;
	}
	/**
	 * 保存
	 * @param model
	 * @param channels
	 * @return
	 */
	@RequestMapping("/insert")
	public String insert(Model model, TChannels channels,HttpServletRequest request){
		channels.setFbranchid(UserSession.loginUser(request).getFbranchid());
		int result = iChannels.insert(channels);
		model.addAttribute("result", result);
		return PageUtils.json;
	}
	/**
	 * 修改
	 * @param model
	 * @param channels
	 * @return
	 */
	@RequestMapping("/update")
	public String update(Model model, TChannels channels){
		int result = iChannels.update(channels);
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
		List<Long> channelsId = new ArrayList<Long>();
		String [] idArr = id.split(",");
		for(String i : idArr){
			channelsId.add(Long.valueOf(i));
		}
		String tips = "";
		int result = 0;
		if(!channelsId.isEmpty())
			result = iChannels.delete(channelsId);
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
	public String queryById(Model model, Long id,int cmd,Long fbranchid){
		JSONObject json = new JSONObject();
		if (id != null) {
			TChannels channels = iChannels.selectByKey(id);
			json.put("channels", channels);
		}
		//频道分组
		List<TChannelGroup> cgroups = iChannelGroup.selectByExample(null);

		if(cmd == 2){
			List<TIncFile> incFiles = iIncFile.selectByBranchid(fbranchid);
			json.put("incs", incFiles);
		}

		json.put("cgroups", cgroups);
		model.addAttribute("result", json);

		return PageUtils.json;
	}
	/**
	 * 查询所有频道
	 * @param model
	 * @param channels
	 * @return
	 */
	@RequestMapping("/selectAll")
	public String selectAll(Model model,TChannels c,HttpServletRequest request){
		IntHolder holder = new IntHolder();
		c.setHolder(holder);
		//所有频道
		List<TChannels> channels = iChannels.selectByExample(c);
		Gson gson = new Gson();
		String result = gson.toJson(channels);
		model.addAttribute("result", result);
		
		return PageUtils.json;
	}
	/**
	 * 批量新增频道信息
	 * @param model
	 * @param fbranchid
	 * @param file
	 * @return
	 */
	@RequestMapping("/batchInsert")
	public String batchInsert(Model model, Long fbranchid,Long inc,@RequestParam(value="file")MultipartFile file){
		Status status = new Status();
		status.setStatus(0);
		if(file != null){
			String suffix = FileUtils.getSuffix(file.getOriginalFilename());
			if("xml".equalsIgnoreCase(suffix)){
				try {
					//获取分公司做大版本号
					Long rvid = iChannels.selectMaxRvIdByBranchid(fbranchid);
					if(rvid != null) 
						rvid += 1;
					else 
						rvid = 1L;
//					XmlHandler handler = new XmlHandler();
//					List<TChannels> channels = handler.getChannels(file.getInputStream(),fbranchid,rvid);
					List<TChannels> channels = XmlUtils.parseXml(file.getInputStream(),rvid,fbranchid);
					if(CollectionUtills.hasElements(channels)){
						//保存
						int s = iChannels.batchInsert(channels);
						if(s > 0){
							TIncFile incFile = new TIncFile();
							incFile.setId(inc);
							incFile.setFstatus(1);
							iIncFile.update(incFile);
						}
						status.setResult("成功导入频道数据" + s + "条");
						status.setStatus(1);
					}else{
						status.setResult("请检查上传文件，该文件暂无频道信息或文件解析出错!");
					}
				} catch (IOException e) {
					status.setResult("XML文件上传失败，请重试！");
				}
			}else{
				status.setResult("文件格式错误！不是xml文件，请上传xml文件！");
			}
		}else{
			status.setResult("请选择上传文件！");
		}
		String result = new Gson().toJson(status);
		model.addAttribute("result", result);
		return PageUtils.json;
	}
	/**
	 * 加载分公司频道
	 * @param model
	 * @param fbranchid
	 * @return
	 */
	@RequestMapping("/loadLevel")
	public String loadLevel(Model model,Long fbranchid,HttpServletRequest request){
		if(fbranchid == null){
			TUser user = UserSession.loginUser(request);
			fbranchid = user.getFbranchid();
		}
		//获取分公司做大版本号
		Long rvid = iChannels.selectMaxRvIdByBranchid(fbranchid);
		//查询分公司所有频道
		List<TChannels> channels = iChannels.queryByBranchId(fbranchid,rvid);
		//频道分组
		List<TChannelGroup> cgroups = iChannelGroup.selectByExample(null);
		
		JSONObject json = new JSONObject();
		json.put("channels", channels);
		json.put("cgroups", cgroups);
		model.addAttribute("result", json);
		return PageUtils.json;
	}
	/**
	 * 批量修改分组
	 * @param model
	 * @param flevel 分组 
	 * @param cid 频道索引
	 * @return
	 */
	@RequestMapping("/updateLevel")
	public String updateLevel(Model model,String flevel,String cid){
		//频道索引
		List<Long> cids = ArraysUtils.toLongList(cid);
		
//		iChannels.updateLevel(flevel, fbranchid);
		
		int result = iChannels.updateLevel(flevel, cids);
		model.addAttribute("result", result);
		return PageUtils.json;
	}
	/**
	 * 所有分公司
	 * @param model
	 * @return
	 */
	@RequestMapping("/loadBranch")
	public String loadBranch(Model model,boolean cmd){
		//已有频道分公司
		List<Long> branchids = new ArrayList<Long>();
		if(!cmd)
			branchids = iChannels.selectBranchid();
		// 分公司
		List<TBranch> branchs = iBranch.query(branchids);
		Gson gson = new Gson();
		model.addAttribute("result", gson.toJson(branchs));
		return PageUtils.json;
	}
	/**
	 * 批量修改频道分组
	 * @param model
	 * @param s1 开始业务号
	 * @param s2 结束业务号
	 * @param fbranchid 参考分公司
	 * @return
	 */
	@RequestMapping("/batchGroup")
	public String batchGroup(Model model,ChannelRequest request){
		//分公司频道分组
		List<TChannels> channels = iChannels.queryByBranchIdAndBetweenServiceId(request);
		//分组业务号
		Map<String,List<String>> serviceGroup = new HashMap<String, List<String>>();
		for(TChannels c : channels){
			String level = c.getFlevel();
			List<String> services = serviceGroup.get(level);
			if(!CollectionUtills.hasElements(services)){
				services = new ArrayList<String>();
			}
			services.add(c.getFserviceid());
			serviceGroup.put(level, services);
		}
		//遍历业务号并修改
		for(Entry<String, List<String>> entry : serviceGroup.entrySet()){
			//批量频道分组
			iChannels.updateGroup(entry.getKey(),entry.getValue());
		}
		return PageUtils.json;
	}
}