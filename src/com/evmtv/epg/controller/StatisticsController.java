package com.evmtv.epg.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.evmtv.epg.entity.TBranch;
import com.evmtv.epg.response.PageUtils;
import com.evmtv.epg.service.IBranch;
import com.evmtv.epg.service.IContract;
import com.evmtv.epg.statistics.CreateChartUtil;
import com.evmtv.epg.statistics.StatisticsRequest;
import com.evmtv.epg.statistics.StatisticsResponse;

/**
 * 统计图
 * @author Lei<helei@evmtv.com>
 * @data 2013-7-18 下午12:43:51
 */
@Controller
@RequestMapping("/main/contract/")
public class StatisticsController {
	
	@Resource
	IContract contractService;
	@Resource
	IBranch branchService;
	
	@RequestMapping("/chartIndex")
	public String statisticsChartIndex(Model model){
		return PageUtils.chartIndex;
	}
	
	@RequestMapping("/gennerationStatisticsChart")
	public String gennerationStatisticsChart(HttpServletRequest request,HttpServletResponse response,Model model,StatisticsRequest statisticsRequest) throws Exception{
		
		if(!StringUtils.hasText(statisticsRequest.getStartYear())){
			statisticsRequest.setStartYear("0000");
		}
		if(!StringUtils.hasText(statisticsRequest.getEndYear())){
			statisticsRequest.setEndYear("9999");
		}
		if(!StringUtils.hasText(statisticsRequest.getChooseYear())){
			statisticsRequest.setChooseYear("0");
		}
		if(!StringUtils.hasText(statisticsRequest.getStartMonth())){
			statisticsRequest.setStartMonth("01");
		}
		if(!StringUtils.hasText(statisticsRequest.getEndMonth())){
			statisticsRequest.setEndMonth("12");
		}
		if(null==statisticsRequest.getBranchid()){
			statisticsRequest.setBranchid((long)0);
		}
		statisticsRequest.setStartMonth(statisticsRequest.getChooseYear()+"-"+statisticsRequest.getStartMonth());
		statisticsRequest.setEndMonth(statisticsRequest.getChooseYear()+"-"+statisticsRequest.getEndMonth());
		List<StatisticsResponse> statisticsResponses = new ArrayList<StatisticsResponse>();
		if("year".equals(statisticsRequest.getStatisType())||"month".equals(statisticsRequest.getStatisType())){
			statisticsResponses = contractService.queryStatistics(statisticsRequest);
			if("bar".equals(statisticsRequest.getChartType())){
				CreateChartUtil.generateToPage(CreateChartUtil.createBarChart("合同统计", "合同时段", "合同数量", CreateChartUtil.getBarData(statisticsResponses)), 800, 500, response);
			}else{
				CreateChartUtil.generateToPage(CreateChartUtil.createPieChart("合同统计",  CreateChartUtil.getPieData(statisticsResponses)), 800, 500, response);
			}
		}
		if("company".equals(statisticsRequest.getStatisType())){
			statisticsResponses = contractService.queryCompany(statisticsRequest);
			if("bar".equals(statisticsRequest.getChartType())){
				CreateChartUtil.generateToPage(CreateChartUtil.createBarChart("金融统计", "分公司名称", "合同金额", CreateChartUtil.getBarData(statisticsResponses)), 800, 500, response);
			}else{
				CreateChartUtil.generateToPage(CreateChartUtil.createPieChart("金融统计",  CreateChartUtil.getPieData(statisticsResponses)), 850, 500, response);
			}
		}
		if("adv".equals(statisticsRequest.getStatisType())){
			statisticsResponses = contractService.queryAdv(statisticsRequest);
			if("bar".equals(statisticsRequest.getChartType())){
				CreateChartUtil.generateToPage(CreateChartUtil.createBarChart("金融统计", "广告位名称", "合同金额", CreateChartUtil.getBarData(statisticsResponses)), 800, 500, response);
			}else{
				CreateChartUtil.generateToPage(CreateChartUtil.createPieChart("金融统计",  CreateChartUtil.getPieData(statisticsResponses)), 850, 500, response);
			}
		}
		return null;
	}
	
	@RequestMapping("/getBranch")
	public String getBranch(Model model){
		List<TBranch> branchs = branchService.query(new TBranch());
		JSONObject json = new JSONObject();
		json.put("branchs", branchs);
		model.addAttribute("result",json);
		return PageUtils.json;
	}
}
