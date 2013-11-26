package com.evmtv.epg.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.evmtv.epg.entity.TChannels;

/**
 * xml文件解析 TODO
 * 
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type
 * @project_name EAMS
 * @file_name XmlHandler.java
 * @package_name com.evmtv.epg.utils
 * @date_time 2013-8-18下午9:30:20
 * @type_name XmlHandler
 */
public class XmlHandler extends DefaultHandler {
	// 频点集合
	private List<TChannels> channels = null;
	private TChannels channel = null;
	// 作用是记录解析时的上一个节点名称
	private String preTag = null;
	private Long fbranchid;
	private Long rvid;
	/**
	 * 解析xml文档入口
	 * @param xmlStream
	 * @return
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * @throws Exception
	 */
	public List<TChannels> getChannels(InputStream xmlStream,Long fbranchid,Long rvid) throws IOException, ParserConfigurationException, SAXException {
		//分公司
		this.fbranchid = fbranchid;
		this.rvid = rvid;
		//Sax工厂对象
		SAXParserFactory factory = SAXParserFactory.newInstance();
		//解析xml文档对象
		SAXParser parser = factory.newSAXParser();
	
		parser.parse(xmlStream, this);
		return channels;
	}

	@Override
	public void startDocument() throws SAXException {
		channels = new ArrayList<TChannels>();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attrs) throws SAXException {
		if ("item".equals(qName) && attrs.getLength() > 0) {
			channel = new TChannels();
			channel.setFbranchid(fbranchid);
			channel.setFreleaseversionid(rvid);
		}
		preTag = qName;// 将正在解析的节点名称赋给preTag
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if("item".equals(qName)){
			channels.add(channel);
			channel = null;
		}
		/**
		 * 当解析结束时置为空。这里很重要，例如，当图中画3的位置结束后，会调用这个方法，如果这里不把preTag置为null，
		 * 根据startElement(....)方法，preTag的值还是book，当文档顺序读到图中标记4的位置时，
		 * 会执行characters(char[] ch, int start, int length)这个方法，
		 * 而characters(....)方法判断preTag!=null，会执行if判断的代码，
		 * 这样就会把空值赋值给book，这不是我们想要的。
		 */
		preTag = null;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (preTag != null && channel != null) {
			String text = new String(ch, start, length);
			switch (preTag) {
				case "original_network_id":
					channel.setFonid(text);
					break;
				case "service_provider":
					channel.setFname(text);
					break;
				case "transport_stream_id":
					channel.setFtsid(text);
					break;
				case "service_id":
					channel.setFserviceid(text);
					break;
			}
		}
	}
}