package com.evmtv.epg.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.omg.CORBA.IntHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.evmtv.epg.entity.TIncFile;
import com.evmtv.epg.entity.TUser;
import com.evmtv.epg.response.PageUtils;
import com.evmtv.epg.service.IIncFile;
import com.evmtv.epg.utils.DateUtils;
import com.evmtv.epg.utils.FileUtils;
import com.evmtv.epg.utils.MD5Utils;
import com.evmtv.epg.utils.ReturnJsonUtils;
import com.evmtv.epg.utils.UserSession;

/**
 * 分公司inc文件管理 TODO
 * 
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type
 * @project_name EAMS
 * @file_name IncFileController.java
 * @package_name com.evmtv.epg.controller
 * @date_time 2013年10月28日下午11:13:27
 * @type_name IncFileController
 */
@Controller
@RequestMapping("/main/inc")
public class IncFileController {

	private ResourceBundle rb = ResourceBundle.getBundle("utils");
	@Resource IIncFile iIncFile;
	
	public String index() {

		return "";
	}
	/**
	 * 查询inc文件列表
	 * @param model
	 * @param request 
	 * @param incFile
	 * @return
	 */
	@RequestMapping("/query")
	public String query(Model model,HttpServletRequest request,TIncFile incFile){
		incFile.setHolder(new IntHolder());
		//当前用户
		TUser user = UserSession.loginUser(request);
		
		if("0".equals(incFile.getFbranchid().toString())){
			incFile.setFbranchid(null);
		}else if(incFile.getFbranchid() == null){
			incFile.setFbranchid(user.getFbranchid());
		}
		//查询
		List<TIncFile> incFiles = iIncFile.selectByExample(incFile);
		
		int total = incFile.getHolder().value;//总记录
		//总页数
		int totalPage = ReturnJsonUtils.getTotalPage(incFile.getLimit(), total);
		//返回字符串
		String result = ReturnJsonUtils.getReturnJsonStr(incFiles, total, totalPage, incFile.getPage());

		model.addAttribute("result", result);
		
		return PageUtils.json;
	}
	/**
	 * inc文件上传
	 * @param model
	 * @param incFile
	 * @param request
	 * @param file
	 * @return
	 */
	@RequestMapping("/uploadInc")
	public String upload(Model model, TIncFile incFile,HttpServletRequest request, @RequestParam(value = "file") MultipartFile file) {
		TUser user = UserSession.loginUser(request);
		// 文件路径
		String realPath = FileUtils.getRealPath(request) + "/";
		String absPath = rb.getString("file.upload.path") + "/incs/";
		String filePath = realPath + absPath;
		FileUtils.dirExists(filePath);
		
		//文件名
		String fileName = file.getOriginalFilename();
		// 素材属性
		String suffix = FileUtils.getFileSuffix(fileName);// 后缀名
		// 文件名
		try {
			String sRandomName = MD5Utils.getMD5String(file.getBytes()).concat(suffix);
			String sFileName = filePath.concat(sRandomName);
			File sfile = new File(sFileName);
			file.transferTo(sfile);
			
			incFile.setFpath(absPath + sRandomName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		incFile.setFname(FileUtils.getFileName(fileName));
		if(incFile.getFbranchid() == null)
			incFile.setFbranchid(user.getFbranchid());
		incFile.setFstatus(0);
		incFile.setFcreateuserid(user.getId());
		incFile.setFcreatetime(DateUtils.getCurrentTime());
		
		int result = iIncFile.insert(incFile);
		
		model.addAttribute("result", result);
		
		return PageUtils.json;
	}
	
	/**
	 * 
	 * @param model
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteIncFile")
	public String deleteIncFile(Model model,String id, HttpServletRequest request){
		// 文件路径
		String realPath = FileUtils.getRealPath(request) + "/";
		
		List<Long> incIds = new ArrayList<Long>();
		String [] idArr = id.split(",");
		for(String i : idArr){
			incIds.add(Long.valueOf(i));
		}
		//根据id查询
		List<TIncFile> files = iIncFile.selectById(incIds);
		for(TIncFile inc : files){
			File f = new File(realPath + inc.getFpath());
			f.delete();
		}
		String tips = "";
		int result = 0;
		if(!incIds.isEmpty())
			result = iIncFile.delete(incIds);
		if(result >0)
			tips +="成功删除"+result+"条数据";
		else
			tips += "不能删除";
	
		model.addAttribute("result", tips);
		return PageUtils.json;
	}
}