package com.evmtv.epg.controller;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
import it.sauronsoftware.jave.VideoInfo;
import it.sauronsoftware.jave.VideoSize;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.omg.CORBA.IntHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.evmtv.epg.constants.Constants;
import com.evmtv.epg.entity.TAdv;
import com.evmtv.epg.entity.TAdvClassConfig;
import com.evmtv.epg.entity.TBranch;
import com.evmtv.epg.entity.TContract;
import com.evmtv.epg.entity.TContractAdv;
import com.evmtv.epg.entity.TContractAdvResource;
import com.evmtv.epg.entity.TMenuUsergroup;
import com.evmtv.epg.entity.TNode;
import com.evmtv.epg.entity.TNodeStatus;
import com.evmtv.epg.entity.TReleaseVersion;
import com.evmtv.epg.entity.TResource;
import com.evmtv.epg.entity.TResourceWithBLOBs;
import com.evmtv.epg.entity.TSourceWithBLOBs;
import com.evmtv.epg.entity.TUser;
import com.evmtv.epg.response.BranchOfContract;
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
import com.evmtv.epg.service.IReleaseVersion;
import com.evmtv.epg.service.IResource;
import com.evmtv.epg.service.ISource;
import com.evmtv.epg.service.IVersionSource;
import com.evmtv.epg.utils.ArraysUtils;
import com.evmtv.epg.utils.CollectionUtills;
import com.evmtv.epg.utils.DateUtils;
import com.evmtv.epg.utils.FileUtils;
import com.evmtv.epg.utils.MD5Utils;
import com.evmtv.epg.utils.ReturnJsonUtils;
import com.evmtv.epg.utils.UserSession;
import com.evmtv.epg.utils.ffmpeg.FFMpegUtil;
import com.google.gson.Gson;

/**
 * 资源管理
 * 
 * @author fangzhu(fangzhu@evmtv.com)
 * @time 2013-6-3 下午10:52:01
 */
@Controller
@RequestMapping("/main/resource")
public class ResourceController {

	private ResourceBundle rb = ResourceBundle.getBundle("utils");
	@Resource IAdv iAdv;
	@Resource INode iNode;
	@Resource IBranch iBranch;
	@Resource ISource iSource;
	@Resource IContract iContract;
	@Resource IResource iResource;
	@Resource INodeStatus iNodeStatus;
	@Resource IContractAdv iContractAdv;
	@Resource IMenuUsergroup iMenuUsergroup;
	@Resource IVersionSource iVersionSource;
	@Resource IReleaseVersion iReleaseVersion;
	@Resource IAdvClassConfig iAdvClassConfig;
	@Resource IContractAdvRescource iContractAdvRescource;

	// 不通过的素材资源
	private StringBuilder badSuffix = null;
	private StringBuilder badSize = null;
	private StringBuilder badWH = null;
	private StringBuilder badDuration = null;

	@RequestMapping("/uploadResource")
	public String uploadResource(Long fmenuid, HttpServletRequest request, Model model) {
		// 权限判断
		TMenuUsergroup mug = UserSession.getUserMenuFreadonly(request, iMenuUsergroup, fmenuid);
		model.addAttribute("usermenu", mug);

		return PageUtils.uploadResource;
	}
	/**
	 * 
	 * @param fmenuid 菜单索引
	 * @param ftype 广告素材类型
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/listIndex")
	public String listIndex(Long fmenuid, String ftype,HttpServletRequest request,Model model) {
		//当前用户
		TUser user = UserSession.loginUser(request);
		//分公司
		List<TBranch> branchs = iBranch.query(new TBranch());
		//广告位
		List<TAdv> currentAdv = currentAdv(user.getFbranchid(),ftype);
		// 权限判断
		TMenuUsergroup mug = UserSession.getUserMenuFreadonly(request,iMenuUsergroup, fmenuid);
		model.addAttribute("usermenu", mug);
		model.addAttribute("advs", currentAdv);
		model.addAttribute("ftype", ftype);
		model.addAttribute("branchs", branchs);
		return PageUtils.resourceListIndex;
	}
	/**
	 * 当前素材广告位
	 * @param branchid
	 * @param ftype
	 * @return
	 */
	private List<TAdv> currentAdv(Long branchid,String ftype){
		//广告位
		List<TAdv> advs = iAdv.query(branchid);
		List<TAdv> currentAdv = new ArrayList<TAdv>();
		for(int i = 0,len=advs.size();i<len;i++){
			TAdv a  = advs.get(i);
			boolean bool = true;
			Integer pos = a.getFpositionid();
			if("0".equals(ftype) && (pos == 1 || pos == 2 || pos == 9)){
				bool = false;
			}else if("3".equals(ftype) && pos != 1 && pos != 9){
				bool = false;
			}else if("10".equals(ftype) && pos != 2){
				bool = false;
			}
			if(bool){
				currentAdv.add(a);
			}
		}
		return currentAdv;
	} 

	/**
	 * 上传素材
	 * @param model
	 * @param request
	 * @param r 资源对象
	 * @param cid 合同广告位关联索引
	 * @param carId 合同广告位资源索引
	 * @return
	 */
	@RequestMapping("/insert")
	public String insert(Model model, MultipartHttpServletRequest request, TResource r,Long cid,Long carId) {
		badSize = new StringBuilder(1000);
		badSuffix = new StringBuilder(1000);
		badWH = new StringBuilder(1000);
		badDuration = new StringBuilder(1000);
		// 文件路径
		String realPath = FileUtils.checkFilePathEndSep(FileUtils.getRealPath(request));
		String absPath = FileUtils.checkFilePathEndSep(rb.getString("file.upload.path"));
		String filePath = realPath.concat(absPath);
		FileUtils.dirExists(filePath);
		// 当前用户
		TUser user = UserSession.loginUser(request);
		// 文件列表
		List<MultipartFile> files = request.getFiles("file");
		// 广告位属性
		TAdvClassConfig config = new TAdvClassConfig();
		config.setFdefinition(r.getFdefinition());
		config.setFpositionid(r.getFpositionid());
		//广告位配置信息
		config = iAdvClassConfig.selectByObject(config);

		//分公司索引
		Long fbranchid = r.getFbranchid();
		if(fbranchid == null){
			fbranchid = user.getFbranchid();
		}
		//合同对像
		TContract contract = null;
		if(carId == null){
			//合同广告位
			TContractAdv contractAdv = iContractAdv.selectByKey(cid);
			contract = iContract.queryByid(contractAdv.getFcontractid());
		}
		// 遍历所有上传文件
		int result = 0;
		//审核
		TNode node = new TNode();
		node.setFischecked("0");
		node.setFtype(0);
		//合同编辑索引
		node = iNode.selectByNode(node);
		//合同广告位对象
		TContractAdv contractAdv = iContractAdv.selectByKey(cid);
		
		for (MultipartFile file : files) {
			//素材信息
			TResourceWithBLOBs bs = setResource(user,file,filePath,config,r,absPath);
			if(bs != null){
				bs.setFbranchid(fbranchid);
				result += iResource.insert(bs);
				//合同广告位资源
				TContractAdvResource car = new TContractAdvResource();
				car.setFedited("0");
				if(!"11".equals(r.getFtype())){
					car.setFresourceid(bs.getId());
					car.setFisoriginalresource("0");
					if(r.getId() != null)
						car.setForiginalresourceid(r.getId());
				}else{
					car.setForiginalresourceid(bs.getId());
					car.setFisoriginalresource("1");
				}
				car.setFcontractadvid(cid);
				if(carId != null){
					car.setId(carId);
					iContractAdvRescource.update(car);
				}else{
					car.setFusestarttime(contract.getFstarttime());
					car.setFuseendtime(contract.getFendtime());
					iContractAdvRescource.insert(car);
					//节点状态
					TNodeStatus status = new TNodeStatus();
					status.setFnodeid(node.getId());
					status.setFcontractid(contractAdv.getFcontractid());
					status.setFstatus("1");
					status.setFremark("新建合同,添加素材成功");
					status.setFcontractadvid(cid);
					status.setFcontractadvresourceid(car.getId());
					status.setFcreatetime(DateUtils.getCurrentTime());
					status.setFuserid(user.getId());
					iNodeStatus.insert(status);
					
					//修改合同广告素材审核节点
					iContractAdvRescource.updateCheckedNodeId(car.getId(), node.getId(),node.getForder());
				}
			}
		}
		// 转为json
		JSONObject json = new JSONObject();
		json.put("total", result);
		json.put("badSuffix", badSuffix.toString());
		json.put("badSize", badSize.toString());
		json.put("badWH", badWH.toString());
		json.put("badDuration", badDuration.toString());

		model.addAttribute("result", json);

		return PageUtils.json;
	}
	
	/**
	 * 查询资源信息
	 * 
	 * @param model
	 * @param bloBs
	 * @return
	 */
	@RequestMapping("/list")
	public String queryList(Model model, TResource res, HttpServletRequest request) {
		IntHolder holder = new IntHolder();
		res.setHolder(holder);
		if(res.getFbranchid() == null){
			TUser user = UserSession.loginUser(request);
			Long branchid = user.getFbranchid();
			res.setFbranchid(branchid);
		}
		List<TResource> list = iResource.selectResource(res);
		
		int total = res.getHolder().value;// 总记录
		int totalPage = ReturnJsonUtils.getTotalPage(res.getLimit(), total);
		String result = ReturnJsonUtils.getReturnJsonStr(list, total,totalPage, res.getPage());

		model.addAttribute("result", result);
		return PageUtils.json;
	}

	/**
	 * 加载合同
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryContrant")
	@Deprecated
	public String loadContront(Model model, HttpServletRequest request) {
		TUser user = UserSession.loginUser(request);
		BranchOfContract boc = new BranchOfContract();
		List<TContract> contracts = boc.getContractByBranch(user, iContract, iAdv);
		Gson gson = new Gson();
		String result = gson.toJson(contracts);
		model.addAttribute("result", result);
		return PageUtils.json;
	}
	/**
	 * 设置对象
	 * @param user
	 * @param file
	 * @param filePath
	 * @param config
	 * @param r
	 * @param absPath
	 * @return
	 */
	private TResourceWithBLOBs setResource(TUser user,MultipartFile file, String filePath,TAdvClassConfig config, TResource r, String absPath){
		
		//文件名
		String orginalName = file.getOriginalFilename();
		// 素材属性
		String suffix = FileUtils.getSuffix(orginalName);// 后缀名
		suffix = suffix.equalsIgnoreCase("jpeg") || suffix.equalsIgnoreCase("jpg") ? "jpg" : suffix;
		//文件名
		File sfile = null;
		String sRandomName = null;
		String sFileName = null;
		// 素材
		TResourceWithBLOBs bs = new TResourceWithBLOBs();
		if(!StringUtils.hasText(r.getFtype())){
			// 判断是否位要求的资源
			if (!suffix.equalsIgnoreCase(config.getFformat())) {
				badSuffix.append(orginalName).append(",");
				return null;
			}
			try {
				sRandomName = MD5Utils.getMD5String(file.getBytes());
				sFileName = filePath.concat(sRandomName.concat(".").concat(suffix));
				sfile = new File(sFileName);
				// 保存文件
				file.transferTo(sfile);
			} catch (IllegalStateException | IOException e) {
				return null;
			}
			//视频文件属性
			Encoder encoder = new Encoder();
	        MultimediaInfo m = null;
			try {
				m = encoder.getInfo(sfile);
			} catch (EncoderException e) {
//				e.printStackTrace();
				return null;
			}
	        VideoInfo info = m.getVideo();
	        long ls = file.getSize();//文件大小
	        //时长
	        long duration = m.getDuration()/1000;
			// 素材大小
	        VideoSize videoSize = info.getSize();
			if(!"11".equals(r.getFtype())){
				long size = (ls / 1024);
				// 素材大小验证
				if (config.getFsize() < size) {
					badSize.append(orginalName).append(",");
					sfile.delete();
					return null;
				}
				if(duration > 0 && config.getFtime() != null && duration > config.getFtime()){
					badDuration.append(orginalName).append(",");
					sfile.delete();
					return null;
				}
			}
			if(config.getFwidth() != videoSize.getWidth() || config.getFheight() != videoSize.getHeight()){
				badWH.append(orginalName).append(",");
				sfile.delete();
				return null;
			}
			String type = "jpg".equalsIgnoreCase(suffix) || "jpeg".equalsIgnoreCase(suffix) ? "0" : "m2v".equalsIgnoreCase(suffix) ? "3" : "10";
			bs.setFtype(type);
			bs.setFwidth(videoSize.getWidth()+"");
			bs.setFheight(videoSize.getHeight()+"");
		}else{
			bs.setFtype(r.getFtype());
		}
		bs.setFadvclassid(r.getFadvclassid());
		bs.setFcontractid(r.getFcontractid());
		bs.setFdefinition(r.getFdefinition());
		bs.setFcreateuserid(user.getId());
		bs.setFchecked("0");
		bs.setFdeleted("0");
		bs.setFfreezed("0");
		bs.setFcreatetime(DateUtils.getCurrentTime());
		bs.setFpath(absPath.concat(sRandomName).concat(".").concat(suffix));
		bs.setFname(FileUtils.getFileName(orginalName));
		if("m2v".equalsIgnoreCase(suffix)){
			FFMpegUtil ffmepg = new FFMpegUtil(Constants.FFMPEG_PATH, sFileName);
			String fpath = filePath.concat(sRandomName.concat(".jpg"));
			try {
				ffmepg.iframeToJpg(fpath);
				bs.setFguid(absPath.concat(sRandomName).concat(".jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		return bs;
	}
	/**
	 * 文件下载
	 * @param fileName 文件名
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/download")
	public void download(String fileName,String filePath,HttpServletRequest request,final HttpServletResponse response){
		fileName = fileName.concat(FileUtils.getFileSuffix(filePath));
		//服务器真实路径
		String realPath = FileUtils.getRealPath(request).concat("/");
		try {
			String userAgent = request.getHeader("User-Agent");
			if (userAgent.toLowerCase().indexOf("firefox") > 0 || userAgent.toLowerCase().indexOf("chrome") > 0)
				fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");// firefox浏览器
			else if (userAgent.toUpperCase().indexOf("MSIE") > 0)
				fileName = URLEncoder.encode(fileName, "UTF-8");// IE浏览器
			response.reset();
			byte[] data = FileUtils.readFileToBytes(realPath.concat(filePath));
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			response.addHeader("Content-Length", "" + data.length);
			response.setContentType("application/octet-stream; charset=UTF-8");  
			OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
			outputStream.write(data);
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 加载分公司合同广告位
	 * @param model
	 * @param fbranchid
	 * @return
	 */
	@RequestMapping("/loadContractAdv")
	public String loadContractAdv(Model model,Long fbranchid,HttpServletRequest request){
		//当页面上没有分公司信息时，为分公司自己上传资源信息
		if(fbranchid == null){
			//当前用户
			TUser user = UserSession.loginUser(request);
			fbranchid = user.getFbranchid();
		}
		JSONObject json = new JSONObject();
		//广告位合同
		List<TContractAdv> cadvs = iContractAdv.selectByFbranchId(fbranchid);
		json.put("cadvs", cadvs);
		
		if("1".equals(fbranchid.toString())){
			List<TBranch> branchs = iBranch.query(new TBranch());
			json.put("branchs", branchs);
		}
		
		model.addAttribute("result", json);
		
		return PageUtils.json;
	}
	/**
	 * 加载分公司广告位
	 * @param model
	 * @param fbranchid 分公司索引
	 * @return
	 */
	@RequestMapping("/loadAdv")
	public String loadAdv(Model model,Long fbranchid,String ftype){
		//广告位
		List<TAdv> advs = currentAdv(fbranchid,ftype);
		Gson gson = new Gson();
		String result = gson.toJson(advs);
		model.addAttribute("result", result);
		return PageUtils.json;
	}
	
	/**
	 * 查询广告使用情况
	 * @param model
	 * @param sid 广告索引
	 * @param rid 素材索引
	 * @return
	 */
	@RequestMapping("/queryResourceUsed")
	public String queryResourceUsed(Model model,Long sid,Long rid){
		
		//素材使用版本
		List<TReleaseVersion> rv  = new ArrayList<TReleaseVersion>();
		
		if(sid != null){
			//根据广告索引查询广告数据
			TSourceWithBLOBs s = iSource.queryById(sid);
			if(s != null){
				rid = s.getFresourceid();
			}
		}
		
		if(rid != null){
			//广告素材索引查询广告索引
			List<Long> sids = iSource.querySidByRid(rid);
			if(CollectionUtills.hasElements(sids)){
				//查询广告使用版本
				List<Long> rvids = iVersionSource.selectRvidBySid(ArraysUtils.toString(sids));
				if(CollectionUtills.hasElements(rvids)){
					TReleaseVersion v = new TReleaseVersion();
					v.setIds(rvids);
					rv = iReleaseVersion.selectByExample(v);
				}
			}
		}
		
		Gson gson = new Gson();
		String result = gson.toJson(rv);
		model.addAttribute("result", result);

		return PageUtils.json;
	}
}