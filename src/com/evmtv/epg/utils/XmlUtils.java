package com.evmtv.epg.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.InputSource;

import com.evmtv.epg.entity.TChannels;
/**
 * xml文件解析工具类
 * TODO
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type 
 * @project_name EAMS
 * @file_name XmlUtils.java
 * @package_name com.evmtv.epg.utils
 * @date_time 2013年11月26日下午3:26:38
 * @type_name XmlUtils
 */
public class XmlUtils {
	
	private static final Logger logger = Logger.getLogger(XmlUtils.class);
	/**
	 * 解析文件
	 * @param xml 字符串
	 * @param rvid
	 * @return
	 */
	public static List<TChannels> parseXml(String xml,Long rvid,Long branchid) {
		// 创建一个新的字符串
		StringReader read = new StringReader(xml);
		// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
		InputSource source = new InputSource(read);

		return parseXml(source,rvid,branchid);
	}
	/**
	 * 解析xml文件
	 * @param is 输入流
	 * @param rvid
	 * @return
	 */
	public static List<TChannels> parseXml(InputStream is,Long rvid,Long branchid) {
		// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
		InputSource source = null;
		try {
			source = new InputSource(new InputStreamReader(is,"gb2312"));
		} catch (UnsupportedEncodingException e) {
			logger.info(e.getMessage());
			e.printStackTrace();
			return null;
		}
		return parseXml(source,rvid,branchid);
	}
	/**
	 * 解析xml文件
	 * @param file 文件
	 * @param rvid
	 * @return
	 */
	public static List<TChannels> parseXml(File file,Long rvid,Long branchid) {
		// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
		InputSource source = null;
		try {
			source = new InputSource(new InputStreamReader(new FileInputStream(file),"gb2312"));
		} catch (FileNotFoundException e) {
			logger.info(e.getMessage());
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			logger.info(e.getMessage());
			e.printStackTrace();
			return null;
		}
		return parseXml(source,rvid,branchid);
	}

	/**
	 * 文档解析
	 * @param is
	 * @return
	 */
	private static List<TChannels> parseXml(InputSource is,Long rvid,Long branchid) {

		SAXBuilder builder = new SAXBuilder();
		Document doc = null;
		try {
			doc = builder.build(is);
		} catch (JDOMException e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			return null;
		}
		// 根节点
		Element root = doc.getRootElement();
		// 根节点下所有元素
		List<Element> childrenRoot = root.getChild("program_detail_descroptor").getChildren();
		//频道信息
		List<TChannels> channels = new ArrayList<TChannels>();
		for (Element item : childrenRoot) {
			List<Element> children = item.getChildren();
			TChannels c = new TChannels();
			c.setFreleaseversionid(rvid);
			c.setFbranchid(branchid);
			for(Element ele : children){
				if("original_network_id".equalsIgnoreCase(ele.getName()))
					c.setFonid(ele.getTextTrim());
				else if("transport_stream_id".equalsIgnoreCase(ele.getName()))
					c.setFtsid(ele.getTextTrim());
				else if("service_provider".equalsIgnoreCase(ele.getName()))
					c.setFname(ele.getTextTrim());
				else if("service_id".equalsIgnoreCase(ele.getName()))
					c.setFserviceid(ele.getTextTrim());
			}
			channels.add(c);
		}
		return channels;
	}
}
