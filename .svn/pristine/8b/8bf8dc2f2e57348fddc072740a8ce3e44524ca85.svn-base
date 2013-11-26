package com.evmtv.epg.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.evmtv.epg.entity.TAdv;
import com.evmtv.epg.entity.TVersionAdv;
import com.evmtv.epg.response.PageUtils;
import com.evmtv.epg.service.IAdv;
import com.evmtv.epg.service.IAdvClass;
import com.evmtv.epg.service.IBranchAdvClass;
import com.evmtv.epg.service.IVersionAdv;
import com.evmtv.epg.utils.CollectionUtills;
import com.evmtv.epg.utils.StringUtils;

/**
 * 分公司广告
 * TODO
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type
 */
@Controller
@RequestMapping("/main/branch")
public class BranchAdvClassController {

	@Resource IAdv iAdv;
	@Resource IAdvClass iAdvClass;
	@Resource IVersionAdv iVersionAdv;
	@Resource IBranchAdvClass iBranchAdvClass;
	/**
	 * 为分公司添加广告位
	 * @param model
	 * @param fbranchid
	 * @param advids
	 * @return
	 */
	@RequestMapping("/branchAdvClassInsert")
	public String insert(Model model,Long fbranchid,String advids, String rvid){
//		rvid = "29|";
		int result = 0;
		//版本查询广告位信息
		if(StringUtils.hasText(rvid) && rvid.length() > 1){
			String [] rvidArr = rvid.split("\\|");
			Long hd = null;
			Long sd = null;
			if(rvidArr.length == 1){
				if(rvid.indexOf("|") > 1){
					hd = Long.valueOf(rvidArr[0]);
				}else{
					sd = Long.valueOf(rvidArr[0]);
				}
			}else{
				hd = Long.valueOf(rvidArr[0]);
				sd = Long.valueOf(rvidArr[1]);
			}
			List<TVersionAdv> vs = new ArrayList<TVersionAdv>();
			String[] advidsArr = advids.split(",");
			for(String aid : advidsArr){
				String[] aidDefinition = aid.split("\\|");
				String definition = aidDefinition[1];
				
				TVersionAdv va = new TVersionAdv();
				if("hd".equalsIgnoreCase(definition)){
					if(hd == null)
						continue;
					va.setFreleaseversionid(hd);
				}else if("sd".equalsIgnoreCase(definition)){
					if(sd == null)
						continue;
					va.setFreleaseversionid(sd);
				}
				va.setFadvid(Long.valueOf(aidDefinition[0]));
				va.setFbranchid(fbranchid);
				va.setFdefinition(definition);
				vs.add(va);
			}
			if(StringUtils.hasText(advids)){
				TVersionAdv va = new TVersionAdv();
				if(hd != null){
					va.setFreleaseversionid(hd);
					iVersionAdv.delete(va);
				}
				if(sd != null){
					va = new TVersionAdv();
					va.setFreleaseversionid(sd);
					iVersionAdv.delete(va);
				}
			}
			
			result = iVersionAdv.batchInsert(vs);
		}
		model.addAttribute("result", result);
		return PageUtils.json;
	}
	/**
	 * 查询该分公司下的广告位
	 * @param <FBranchAdvclass>
	 * @param model
	 * @param fbranchid
	 * @return
	 */
	@RequestMapping("/branchAdvClassQuery")
	public String query(Model model, Long fbranchid, String rvid, HttpServletRequest request){
		JSONObject json = new JSONObject();
		//分公司广告位
		List<TAdv> advs = iAdv.query(fbranchid);
		//版本查询广告位信息
		if(StringUtils.hasText(rvid) && rvid.length() > 1){
			String [] rvidArr = rvid.split("\\|");
			Long hd = null;
			Long sd = null;
			if(rvidArr.length == 1){
				if(rvid.indexOf("|") > 1){
					hd = Long.valueOf(rvidArr[0]);
				}else{
					sd = Long.valueOf(rvidArr[0]);
				}
			}else{
				hd = Long.valueOf(rvidArr[0]);
				sd = Long.valueOf(rvidArr[1]);
			}
			List<TAdv> userAdvs = new ArrayList<TAdv>();
			List<TAdv> sdAdvs = null;
			if(hd != null)
				userAdvs = iVersionAdv.selectAdvByReleaseVersionid(hd);
			if(sd != null)
				sdAdvs = iVersionAdv.selectAdvByReleaseVersionid(sd);
			if(CollectionUtills.hasElements(sdAdvs))
				userAdvs.addAll(sdAdvs);
			json.put("userAdvs", userAdvs);
		}
		json.put("advs", advs);
		
		model.addAttribute("result", json.toString());

		return PageUtils.json;
	}
}
