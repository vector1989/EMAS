package com.evmtv.epg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.evmtv.epg.response.PageUtils;
/**
 * 
 * @author fangzhu(fangzhu@evmtv.com)
 * @time 2013-5-31 下午3:01:48
 */
@Controller
@RequestMapping("/main")
public class SysView {

	@RequestMapping("/centerIndex")
	public String index(){
		return PageUtils.centerIndex;
	}
	@RequestMapping("/downIndex")
	public String defaultIndex(){
		return PageUtils.downIndex;
	}
	@RequestMapping("/leftIndex")
	public String leftFrameIndex(){
		return PageUtils.leftIndex;
	}
	
	@RequestMapping("/topIndex")
	public String topFrameIndex(){
		return PageUtils.topIndex;
	}
	
	@RequestMapping("/index")
	public String switchFrameIndex(){
		return PageUtils.mainIndex;
	}
	
	@RequestMapping("/error500")
	public String errorBy500(){
		return PageUtils._500Page;
	}
	
	@RequestMapping("/error404")
	public String errorBy404(){
		return PageUtils._404Page;
	}
	@RequestMapping("/tools")
	public String toolsIndex(){
		return PageUtils.toolsIndex;
	}
}
