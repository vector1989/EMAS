/**
 * @project_name EAMS
 * @file_name SelectNode.java
 * @package_name com.evmtv.epg.request
 * @date_time 2013年12月4日下午3:24:26
 * @type_name SelectNode
 */
package com.evmtv.epg.request;

import com.evmtv.epg.entity.TNode;

/**
 * 
 * <p>Title: 获取节点</p> 
 * <p>Description: </p> 
 * <p>Date: 2013年12月4日下午3:24:26</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * <p>Company: www.evmtv.com</p> 
 * @author fangzhu@evmtv.com
 * @version 1.0 
 */
public class SelectNode {
	
	/**
	 * 合同编辑节点
	 * @return
	 */
	public static TNode getContractEdit(){
		TNode node = new TNode();
		node.setFischecked("0");
		node.setFtype("0");
		return node;
	}
	
	/**
	 * 合同审核节点
	 * @return
	 */
	public static TNode getContectChecked(){
		TNode node = new TNode();
		node.setFtype("0");
		node.setFischecked("1");
		return node;
	}
	/**
	 * 素材审核
	 * @return
	 */
	public static TNode getResourceChecked(){
		TNode node = new TNode();
		node.setFischecked("1");
		node.setFtype("1");
		return node;
	}
	/**
	 * 素材审核
	 * @return
	 */
	public static TNode getSourceEdit(){
		TNode node = new TNode();
		node.setFtype("1");
		node.setFischecked("0");
		return node;
	}
	/**
	 * 省公司测试
	 * @return
	 */
	public static TNode getProveTestNode(){
		TNode node = new TNode();
		node.setFtype("2");
		node.setFischecked("1");
		node.setFisprovincecompany("1");
		
		return node;
	}
	/**
	 * 分公司测试
	 * @return
	 */
	public static TNode getBranchTestNode(){
		TNode node = new TNode();
		node.setFtype("2");
		node.setFischecked("1");
		node.setFisprovincecompany("0");
		return node;
	}
	
	/**
	 * 广告发布到省公司测试节点
	 * @return
	 */
	public static TNode getReleaseToProvNode(){
		TNode node = new TNode();
		node.setFtype("3");
		node.setFischecked("2");
		return node;
	}
	/**
	 * 广告发布到分公司测试节点
	 * @return
	 */
	/*public static TNode getReleaseToBranchNode(){
		TNode node = new TNode();
		node.setFtype("4");
		node.setFischecked("2");
		return node;
	}*/
	/**
	 * 广告发布节点
	 * @return
	 */
	public static TNode getReleaseNode(){
		TNode node = new TNode();
		node.setFtype("5");
		node.setFischecked("2");
		
		return node;
	}
}