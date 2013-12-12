package com.evmtv.epg.controller;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.evmtv.epg.entity.TAdv;
import com.evmtv.epg.entity.TAdvClassConfig;
import com.evmtv.epg.entity.TBranch;
import com.evmtv.epg.entity.TContractAdv;
import com.evmtv.epg.entity.TContractAdvResource;
import com.evmtv.epg.entity.TContractWithBLOBs;
import com.evmtv.epg.entity.TMenuUsergroup;
import com.evmtv.epg.entity.TNode;
import com.evmtv.epg.entity.TNodeStatus;
import com.evmtv.epg.entity.TStatusCarOrRv;
import com.evmtv.epg.entity.TUser;
import com.evmtv.epg.mail.SendEmailUtil;
import com.evmtv.epg.request.SelectNode;
import com.evmtv.epg.response.ContractQueryResponse;
import com.evmtv.epg.response.ContractResponse;
import com.evmtv.epg.response.PageUtils;
import com.evmtv.epg.service.IAdv;
import com.evmtv.epg.service.IAdvClassConfig;
import com.evmtv.epg.service.IBranch;
import com.evmtv.epg.service.IContract;
import com.evmtv.epg.service.IContractAdv;
import com.evmtv.epg.service.IContractAdvRescource;
import com.evmtv.epg.service.IMenuUsergroup;
import com.evmtv.epg.service.INode;
import com.evmtv.epg.service.INodeStatus;
import com.evmtv.epg.service.IStatusCarOrRv;
import com.evmtv.epg.service.IUser;
import com.evmtv.epg.service.IUserNode;
import com.evmtv.epg.service.IUserNodeStatus;
import com.evmtv.epg.utils.CollectionUtills;
import com.evmtv.epg.utils.DateUtils;
import com.evmtv.epg.utils.FileUtils;
import com.evmtv.epg.utils.ReturnJsonUtils;
import com.evmtv.epg.utils.StringUtils;
import com.evmtv.epg.utils.UserSession;
import com.evmtv.epg.utils.WordUtil;
import com.google.gson.Gson;

/**
 * @author Lei<helei@evmtv.com>
 * @data 2013-6-27 下午2:17:01
 */
@Controller
@RequestMapping("/main/contract")
public class ContractController {

	private ResourceBundle rb = ResourceBundle.getBundle("utils");
	@Resource IAdv iAdv;
	@Resource INode iNode;
	@Resource IBranch iBranch;
	@Resource IUser userService;
	@Resource IUserNode iUserNode;
	@Resource IContract iContract;
	@Resource INodeStatus iNodeStatus;
	@Resource IStatusCarOrRv iStatusCarOrRv;
	@Resource IMenuUsergroup iMenuUsergroup;
	@Resource IAdvClassConfig iAdvClassConfig;
	@Resource IContractAdv contractiAdv;
	@Resource IUserNodeStatus iUserNodeStatus;
	@Resource IContractAdvRescource iContractAdvRescource;
	/**
	 * 判断权限，跳到合同管理页面
	 * @param fmenuid
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/listIndex")
	public String contractIndex(Long fmenuid,HttpServletRequest request,Model model){
		//权限判断
		TMenuUsergroup mug = UserSession.getUserMenuFreadonly(request, iMenuUsergroup, fmenuid);
		model.addAttribute("usermenu", mug);
		// 分公司
		List<TBranch> branchs = iBranch.query(new TBranch());
		model.addAttribute("branchs", branchs);
		return PageUtils.contractIndex;
	}
	
	/**
	 * 跳转到新增合同页面
	 * @param model
	 * @param request
	 * @param tContractWithBLOBs
	 * @return
	 */
	@RequestMapping("/addContractIndex")
	public String addContractIndex(Model model,HttpServletRequest request,Long id){
		TUser user = UserSession.loginUser(request);
		List<TAdv> advs = iAdv.query(user.getFbranchid());
		
		TContractWithBLOBs tContractWithBLOBs = null;
		List<TContractAdv> contractAdvs = null;
		if(id!=null){
			tContractWithBLOBs = iContract.queryByid(id);
			contractAdvs = contractiAdv.selectByContractId(tContractWithBLOBs.getId());
		}
		model.addAttribute("advs", advs);
		model.addAttribute("conadvs",contractAdvs);
		model.addAttribute("contract", tContractWithBLOBs);
		return PageUtils.addContract;
	}
	
	/**
	 * 合同查询
	 * @param model
	 * @param source
	 * @param queryStartTime
	 * @param queryEndTime
	 * @param queryKeyWord
	 * @return
	 */
	@RequestMapping("/queryContract")
	public String queryContract(Model model,TContractWithBLOBs c,HttpServletRequest request){
		TUser user = UserSession.loginUser(request);
		ContractQueryResponse response = new ContractQueryResponse();
		List<ContractResponse> bolbs = response.selectContract(c, user, iContract);
		
		String emailStr = "";
		Double sumPrice = 0.0;
		for (ContractResponse contract : bolbs) {
			if(null!=contract.getFprice()){
				sumPrice += Double.valueOf(contract.getFprice())*Double.valueOf(contract.getFdiscount());
			}
			/*if(null!=contract.getFendtime()){
				//延期处理
				if("0".equals(contract.getFfreezed())&&contract.getFendtime().compareTo(DateUtils.formatDate())<0){
					emailStr += contract.getFguid()+",";
					TContractWithBLOBs blob = iContract.queryByid(contract.getId());
					String fendtime = DateUtils.addDay(3, contract.getFendtime(), 0);
					contract.setFfreezed("1");
					blob.setFfreezed("1");
					contract.setFendtime(fendtime);
					blob.setFendtime(fendtime);
					iContract.updateContract(blob);
				}
				//过期处理
				if("1".equals(contract.getFfreezed())&&contract.getFendtime().compareTo(DateUtils.formatDate())<0){
					TContractWithBLOBs blob = iContract.queryByid(contract.getId());
					blob.setFfreezed("2");
					contract.setFfreezed("2");
					iContract.updateContract(blob);
				}
			}*/
		}
		if(!"".equals(emailStr)){
			emailStr = emailStr.substring(0,emailStr.length()-1);
		}
		int total = c.getHolder().value;//总记录
		//总页数
		int totalPage = ReturnJsonUtils.getTotalPage(c.getLimit(), total);
		//返回字符串
		
		//总金保留两位小数
		DecimalFormat df=new DecimalFormat(".##");
		
		JSONObject json = new JSONObject();
		json.put("source", bolbs);
		json.put("total", total);
		json.put("totalPage", totalPage);
		json.put("page", c.getPage());
		json.put("emailStr", emailStr);
		json.put("sumPrice", df.format(sumPrice));
		String result = json.toString();
		model.addAttribute("result", result);
		return PageUtils.json;
	}
	
	
	/**
	 * 发送邮件
	 * @param model
	 * @param emailStr
	 * @return
	 */
	@RequestMapping("/sendMail")
	public String sendMail(Model model,String emailStr){
		SendEmailUtil.sendEmail(emailStr);
		String[] emailArr = emailStr.split(",");
//		for (String string : emailArr) {
//			TContractWithBLOBs contract = iContract.queryByFguid(string);
//			contract.setFfreezed("1");
//			String fendtime = DateUtils.addDay(3, contract.getFendtime(), 0);
//			contract.setFendtime(fendtime);
//			iContract.updateContract(contract);
//		}
		model.addAttribute("result",emailArr.length);
		return PageUtils.json;
	}
	
	/**
	 * 导入word文件合同，并显示到页面上
	 * @param model
	 * @param request
	 * @param file
	 * @return
	 */
	@RequestMapping("/addContract") 
	public String addContract(Model model,MultipartHttpServletRequest request,@RequestParam(value="file") MultipartFile file){
		
		String realPath = FileUtils.getRealPath(request)+"/";
		String absPath = rb.getString("word.upload.path") + "/";
		String filePath = realPath + absPath;
		filePath = FileUtils.checkFilePathEndSep(filePath);
		FileUtils.dirExists(filePath);
		JSONObject json = new JSONObject();
		try {
			String wordHtml = WordUtil.wordToHtml(file.getInputStream(),filePath);
			//str含有HTML标签的文本
			wordHtml = wordHtml.replace("&","&amp;");
			wordHtml = wordHtml.replace("<","&lt;");
			wordHtml = wordHtml.replace(">","&gt;");
			wordHtml = wordHtml.replace(" ","&nbsp;");
			json.put("filePath", absPath);
			json.put("fileOriginName", file.getOriginalFilename());
			json.put("fileName", file.getOriginalFilename());
			json.put("wordHtml", wordHtml);
		} catch(IOException | ParserConfigurationException | TransformerException e){
			
		}
		model.addAttribute("result", json);
		return PageUtils.json;
	}
	
	/**
	 * 保存新增或修改后的合同
	 * @param model
	 * @param request
	 * @param tContractWithBLOBs
	 * @return
	 */
	@RequestMapping("/saveContract")
	public String saveContract(Model model,HttpServletRequest request,TContractWithBLOBs c,String advloc){
		//广告位信息
		String [] advlocArr = advloc.split(",");
		
		if(!StringUtils.hasText(c.getFfiletitle())){
			c.setFfiletitle(DateUtils.currentTimeMillis()+".doc");
		}
		if(!StringUtils.hasText(c.getFfilepath())){
			String absPath = rb.getString("word.upload.path") + "/";
			c.setFfilepath(absPath);
		}
		String realPath = FileUtils.getRealPath(request).concat("/");
		String filePath = realPath + c.getFfilepath();
		filePath = FileUtils.checkFilePathEndSep(filePath);
		FileUtils.dirExists(filePath);
		if(!c.getFcontent().contains("<html>")){
			if(c.getFcontent().contains("charset=")){
				c.setFcontent("<html>"+c.getFcontent()+"</html>");
			}else{
				c.setFcontent("<html><meta http-equiv='Content-Type' content='text/html; charset=utf-8'/>"+c.getFcontent()+"</html>");
			}
		}
		String msg = WordUtil.htmlToWord(filePath, c.getFfiletitle(), c.getFcontent());

		TUser user = UserSession.loginUser(request);
		JSONObject json = new JSONObject();
		if(null == c.getId()){
			c.setFcreatetime(DateUtils.getCurrentTime());
			c.setFcreateuserid(user.getId());
			c.setFbranchid(user.getFbranchid());
			//查询合同编号是否存在
			if(null==iContract.queryByFguid(c.getFguid())){
				iContract.addContract(c);
				//新增合同广告关联数据
				insertContractAdv(advlocArr,c.getId(),false);

				//添加合同新增节点
				insertContractNodeStatus(c.getId(),user.getId(),user.getFbranchid());
			}else{
				json.put("errormsg", "合同编号已存在！");
				model.addAttribute("result", json);
				return PageUtils.json;
			}
		}else{
			c.setFupdatetime(DateUtils.getCurrentTime());
			c.setFupdateuserid(user.getId());
			if(null!=iContract.queryByFguid(c.getFguid())&&!iContract.queryByid(c.getId()).getFguid().equals(c.getFguid())){
				json.put("errormsg", "合同编号已存在！");
				model.addAttribute("result", json);
				return PageUtils.json;
			}else{
				//新增合同广告关联数据
				insertContractAdv(advlocArr,c.getId(),true);
				iContract.updateContract(c);
				//获取合同节点
				TNodeStatus s = iNodeStatus.queryByContractId(c.getId());
				
				s = iNodeStatus.selectByParentId(s.getId());
				s.setFstatus("0");
				s.setFremark(s.getFremark()+"；"+DateUtils.getCurrentTime()+"：[" + user.getFname()+"]修改合同");
				iNodeStatus.update(s);
				
				//获取节点索引
				TNode node = iNode.selectByKey(s.getFnodeid());
				TStatusCarOrRv r = new TStatusCarOrRv();
				r.setFnextnodeusergroupid(node.getFusergroupid());
				r.setFcontractid(c.getId());
				r.setFisvalid("0");
				iStatusCarOrRv.update(r);
			}
		}
		c.setId(c.getId());
		json.put("msg", msg);
		json.put("contract", c);
		model.addAttribute("result", json);
		return PageUtils.json;
	}
	/**
	 * 新增合同节点
	 * @param cid
	 * @param uid
	 */
	private void insertContractNodeStatus(Long cid,Long uid,Long branchid){
		TNode node = iNode.selectByNode(SelectNode.getContractEdit());
		//节点状态
		TNodeStatus status = new TNodeStatus();
		status.setFnodeid(node.getId());
		status.setFnodetitle(node.getFname());
		status.setFcontractid(cid);
		status.setFstatus("1");
		status.setFremark(DateUtils.getCurrentTime()+"：合同添加成功");
		status.setFcreatetime(DateUtils.getCurrentTime());
		status.setFuserid(uid);
		status.setFbranchid(branchid);
		iNodeStatus.insert(status);
		Long pid = status.getId();
		//下一个节点，合同审核节点插入
		node = iNode.selectByNode(SelectNode.getContectChecked());
		status = new TNodeStatus();
		status.setFnodeid(node.getId());
		status.setFnodetitle(node.getFname());
		status.setFcontractid(cid);
		status.setFbranchid(branchid);
		status.setFparentid(pid);//父节点
		iNodeStatus.insert(status);
		
		TStatusCarOrRv record = new TStatusCarOrRv();
		record.setFcontractid(cid);
		record.setFnextnodeusergroupid(node.getFusergroupid());
		iStatusCarOrRv.insert(record);
	}
	/**
	 * 新增合同广告数据
	 * @param advArr
	 * @param contracid
	 * @param check 
	 */
	private void insertContractAdv(String[] advArr,Long contracid,boolean check){
		List<Long> advidList = new ArrayList<Long>();
		for(String advStr : advArr){
			String[] adv = advStr.split("\\|");
			advidList.add(Long.valueOf(adv[0]));
			TContractAdv tContractAdv = new TContractAdv();
			tContractAdv.setFcontractid(contracid);
			tContractAdv.setFadvid(Long.valueOf(adv[0]));
			tContractAdv.setFdefinition(adv[1]);
			//是否检查
			if(check){
				if(!contractiAdv.checkAdvExist(tContractAdv)){
					contractiAdv.insert(tContractAdv);
				}
			}else{
				contractiAdv.insert(tContractAdv);
			}
		}
		if(check && CollectionUtills.hasElements(advidList)){
			TContractAdv adv = new TContractAdv();
			adv.setAdvIdList(advidList);
			adv.setFcontractid(contracid);
			contractiAdv.delete(adv);
		}
		
	}
	
	/**
	 * 删除合同
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	public String delete(Model model, String id){
		List<Long> ids = new ArrayList<Long>();
		List<Long> eids = new ArrayList<Long>();
		String [] idArr = id.split(",");
		for(String i : idArr){
			long aid = Long.valueOf(i);
			if("1".equals(iContract.queryByid(aid).getFchecked())){
				eids.add(aid);
			}else{
				ids.add(aid);
			}
		}
		JSONObject json = new JSONObject();
		if(eids.isEmpty()){
			List<TContractWithBLOBs> tContracts = iContract.selectIn(ids);
			for (TContractWithBLOBs tContract : tContracts) {
				File file = new File(tContract.getFfilepath()+tContract.getFfiletitle());
				if(file.exists()){
					file.delete();
				}
			}
			iContract.delete(ids);
			for (int i = 0; i < ids.size(); i++) {
				contractiAdv.deleteConadvByFcontractid(ids.get(i));
			}
			iNodeStatus.deleteByCids(ids);
			json.put("ids", ids);
		}else{
			json.put("eids", eids);
		}
		
		model.addAttribute("result", json);
		return PageUtils.json;
	}
	/**
	 * 合同审核
	 * @param model
	 * @param status
	 * @param request
	 * @return
	 */
	@RequestMapping("/checked")
	public String checkContract(Model model,TNodeStatus status,HttpServletRequest request){
		TContractWithBLOBs bloBs = new TContractWithBLOBs();
		bloBs.setId(status.getFcontractid());
		bloBs.setFchecked(status.getFstatus());
		iContract.updateContract(bloBs);
		
		return "forward:../nodeStatus/checked";
	}
	
	/**
	 * 合同审核
	 * @param model
	 * @param id
	 * @return
	 */
//	@RequestMapping("/check")
	@Deprecated
	public String check(Model model,String id){
		List<Long> ids = new ArrayList<Long>();
		String[] idStr = id.split(",");
		for (String string : idStr) {
			Long cid = Long.valueOf(string);
			ids.add(cid);
		}
		int count = 0;
		List<TContractWithBLOBs> tContracts = iContract.selectIn(ids);
		for (TContractWithBLOBs tContract : tContracts) {
			if(!"1".equals(tContract.getFchecked())){
				tContract.setFchecked("1");
				iContract.updateContract(tContract);
				count++;
			}
		}
		model.addAttribute("result", count);
		return PageUtils.json;
	}
	
	/**
	 * 取消审核
	 * @param model
	 * @param id
	 * @return
	 */
//	@RequestMapping("/uncheck")
	@Deprecated
	public String uncheck(Model model,String id){
		List<Long> ids = new ArrayList<Long>();
		String[] idStr = id.split(",");
		for (String string : idStr) {
			Long cid = Long.valueOf(string);
			ids.add(cid);
		}
		int count = 0;
		List<TContractWithBLOBs> tContracts = iContract.selectIn(ids);
		for (TContractWithBLOBs tContract : tContracts) {
			if(!"2".equals(tContract.getFchecked())){
				tContract.setFchecked("2");
				iContract.updateContract(tContract);
				count++;
			}
		}
		model.addAttribute("result", count);
		return PageUtils.json;
	}
	
	/**
	 * 合同下载
	 * @param model
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/download")
	public String download(Model model,HttpServletRequest request,String id){
		Long contractid = Long.valueOf(id);
		TContractWithBLOBs tContractWithBLOBs = iContract.queryByid(contractid);
		String realPath = FileUtils.getRealPath(request)+"/";
		String filePath = realPath + tContractWithBLOBs.getFfilepath();
		filePath = FileUtils.checkFilePathEndSep(filePath);
		JSONObject json = new JSONObject();
		if(!new File(filePath,tContractWithBLOBs.getFfiletitle()).exists()){
			json.put("msg", "文件不存在，请修改保存后下载！");
		}
		json.put("contract", tContractWithBLOBs);
		model.addAttribute("result", json);
		return PageUtils.json;
	}
	
	/**
	 * 上传图片
	 * @param model
	 * @param request
	 * @param file
	 * @param id
	 * @return
	 */
	@RequestMapping("/uploadPic")
	public String uploadPic(Model model,MultipartHttpServletRequest request,@RequestParam(value="file") MultipartFile file,String id){
		String realPath = FileUtils.getRealPath(request)+"/";
		String absPath = rb.getString("word.upload.path") + "/";
		String filePath = realPath + absPath;
		filePath = FileUtils.checkFilePathEndSep(filePath);
		FileUtils.dirExists(filePath);
		TContractWithBLOBs tContractWithBLOBs = iContract.queryByid(Long.valueOf(id));
		String wordName = tContractWithBLOBs.getFfiletitle();
		String fileName = wordName.substring(0,wordName.indexOf(".doc"))+file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
		JSONObject json = new JSONObject();
		tContractWithBLOBs.setFpictitle(fileName);
		try {
			file.transferTo(new File(filePath,fileName));
			iContract.updateContract(tContractWithBLOBs);
			json.put("msg", "上传成功！");
		} catch (Exception e) {
			json.put("msg", e.getMessage());
		}
		model.addAttribute("result", json);
		return PageUtils.json;
	}
	
	/**
	 * 合同图片预览
	 * @param model
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/preview")
	public String preview(Model model,HttpServletRequest request,String id){
		TContractWithBLOBs tContractWithBLOBs = iContract.queryByid(Long.valueOf(id));
		String realPath = FileUtils.getRealPath(request)+"/";
		String filePath = realPath + tContractWithBLOBs.getFfilepath();
		filePath = FileUtils.checkFilePathEndSep(filePath);
		JSONObject json = new JSONObject();
		if(null==tContractWithBLOBs.getFpictitle()||!new File(filePath,tContractWithBLOBs.getFpictitle()).exists()){
			json.put("msg", "图片不存在，请先上传合同图片！");
		}
		json.put("contract", tContractWithBLOBs);
		model.addAttribute("result", json);
		return PageUtils.json;
	}
	
	/**
	 * 合同内容预览
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/previewContract")
	public String previewContract(Model model,HttpServletRequest request,Long id,String temp){
		//合同
		TContractWithBLOBs tContract = iContract.queryByid(id);
		// 分公司可操作广告位
		List<TContractAdv> advs = contractiAdv.selectByContractId(tContract.getId());
		for (int i = 0; i < advs.size() - 1; i++) {
			for (int j = advs.size() - 1; j > i; j--) {
				if (advs.get(j).getFadvid().toString().equals(advs.get(i).getFadvid().toString())) {
					advs.remove(j);
				}
			}
		}
//		List<ConadvResponse> conadvResponses = contractiAdv.selectAdvByFcontractid(tContract.getId());
		JSONObject json = new JSONObject();
		json.put("advclass", advs);
		json.put("contract", tContract);
//		json.put("conadvs", conadvResponses);
		json.put("branch", iBranch.queryById(tContract.getFbranchid()));
		if(!StringUtils.hasText(temp)){
			TUser user = UserSession.loginUser(request);
			json.put("user", user);
		}
		model.addAttribute("result", json);
		return PageUtils.json;
	}
	/**
	 * 查询广告位配置信息
	 * @param model
	 * @param id 广告位索引
	 * @param fbranchid
	 * @return
	 */
	@RequestMapping("/loadAdvConfig")
	public String queryAdvConfig(Model model,Long id){
		TAdv adv = iAdv.selectByKey(id);
		TAdvClassConfig config = new TAdvClassConfig();
		config.setFdefinition(adv.getFdefinition());
		config.setFpositionid(adv.getFpositionid());
		//广告位配置文件
		config = iAdvClassConfig.selectByObject(config);
		
		model.addAttribute("result", new Gson().toJson(config));
		
		return PageUtils.json;
	}
	
	/**
	 * 加载当前合同素材预览信息
	 * @param model
	 * @param id 合同索引
	 * @return
	 */
	@RequestMapping("/loadPreviewResource")
	public String loadPreviewResource(Model model,Long id){
		
		//合同广告数据
		List<TContractAdv> contractAdvs = contractiAdv.selectResourceByContractId(id);

		Gson gson = new Gson();
		String result = gson.toJson(contractAdvs);
		model.addAttribute("result", result);
		
		return PageUtils.json;
	}
	/**
	 * 加载广告位和素材
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/loadAdvAndResourceByContract")
	public String loadAdvAndResourceByContract(Model model,Long id){
		List<TContractAdv> contractAdvs = contractiAdv.selectByContractId(id);
		String result = new Gson().toJson(contractAdvs);
		model.addAttribute("result", result);
		return PageUtils.json;
	}
	/**
	 * 修改素材有效期
	 * @param model
	 * @param resource
	 * @return
	 */
	@RequestMapping("/modifyResourceValid")
	public String modifyResourceValid(Model model,TContractAdvResource r){
		//合同广告对象
		TContractAdv ca = contractiAdv.selectObjectByContractId(r.getId());
		
		boolean b1 = DateUtils.dataToLong(r.getFusestarttime()) >= DateUtils.dataToLong(ca.getFstarttime());
		boolean b2 = DateUtils.dataToLong(r.getFuseendtime()) <= DateUtils.dataToLong(ca.getFendtime());
		String result = "";
		if(b1 && b2){
			iContractAdvRescource.update(r);
			result = "1";
		}else{
			if(!b1){
				result = "素材开始日期已大于合同的起始日期";
			}
			if(!b2){
				if(StringUtils.hasText(result)){
					result += ";";
				}
				result += "素材结束日期已大于合同的终止日期";
			}
		}		
		model.addAttribute("result", result);
		return PageUtils.json;
	}
	@RequestMapping("/hasAdv")
	public String hasAdv(Model model,HttpServletRequest request){
		TUser user = UserSession.loginUser(request);
		List<TAdv> advs = iAdv.query(user.getFbranchid());
		Gson gson = new Gson();
		String result = gson.toJson(advs.size());
		model.addAttribute("result", result);
		return PageUtils.json;
	}
}