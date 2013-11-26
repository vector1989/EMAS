package com.evmtv.epg.request;

import java.io.Serializable;

import org.omg.CORBA.IntHolder;
import org.springframework.util.StringUtils;

/**
 * 
 * @author fangzhu(fangzhu@evmtv.com)
 * @time 2013-6-3 下午9:09:51
 */
public class BaseRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
	private Integer page; //

	private Integer limit;

	private Integer start;

	private IntHolder holder;

	private String queryStartTime; // 开始时间

	private String queryEndTime;// 结束时间

	private String queryKeyWord;// 关键字
	
	private String temp;//

	public Integer getLimit() {
		
		return limit == null ? 0 : limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getStart() {
		if (page != null) {
			this.start = (page - 1) * limit;
		}
		return start == null ? 0 : start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public IntHolder getHolder() {
		return holder;
	}

	public void setHolder(IntHolder holder) {
		this.holder = holder;
	}

	public Integer getPage() {
		if(start != null && limit > 0){
			this.page = this.start/this.limit + 1;
		}
		return page == null?1:page;
	}

	public void setPage(Integer page) {
		if (page != null) {
			this.start = (page - 1) * limit;
		}
		this.page = page;
	}

	public String getQueryStartTime() {
		if(!StringUtils.hasText(queryStartTime))
			queryStartTime = null;
		return queryStartTime;
	}

	public void setQueryStartTime(String queryStartTime) {
		this.queryStartTime = queryStartTime;
	}

	public String getQueryEndTime() {
		if(!StringUtils.hasText(queryEndTime))
			queryEndTime = null;
		return queryEndTime;
	}

	public void setQueryEndTime(String queryEndTime) {
		this.queryEndTime = queryEndTime;
	}

	public String getQueryKeyWord() {
		if(!StringUtils.hasText(queryKeyWord))
			queryKeyWord = null;
		return queryKeyWord;
	}

	public void setQueryKeyWord(String queryKeyWord) {
		this.queryKeyWord = queryKeyWord;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

}