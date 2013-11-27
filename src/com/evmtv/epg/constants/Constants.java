/**
 * @project_name EAMS
 * @file_name Constants.java
 * @package_name com.evmtv.epg.constants
 * @date_time 2013年11月27日下午1:28:57
 * @type_name Constants
 */
package com.evmtv.epg.constants;

/**
 * 
 * <p>Title: 常量</p> 
 * <p>Description: 封装部分常量</p> 
 * <p>Date: 2013年11月27日下午1:28:57</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * <p>Company: www.evmtv.com</p> 
 * @author fangzhu@evmtv.com
 * @version 1.0 
 */
public class Constants {
	/***
	 * ffmpeg文件路径
	 */
	public final static String FFMPEG_PATH = Constants.class.getResource("/").getFile().toString() + "../../ffmpeg/ffmpeg.exe";
}
