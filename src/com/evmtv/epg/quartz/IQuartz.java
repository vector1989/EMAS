package com.evmtv.epg.quartz;
/**
 * 任务调度,定时任务
 * TODO
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type 
 * @project_name EAMS
 * @file_name IQuartz.java
 * @package_name com.evmtv.epg.quartz
 * @date_time 2013-8-30下午3:43:29
 * @type_name IQuartz
 */
public interface IQuartz {
	/**
	 * 调度方法
	 */
	void job();
}
