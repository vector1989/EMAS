package com.evmtv.epg.response;

import org.springframework.util.StringUtils;

import com.evmtv.epg.request.BaseRequest;
/**
 * 资源合同广告位信息
 * TODO
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type 
 * @project_name EAMS
 * @file_name ResourceContractAdvResponse.java
 * @package_name com.evmtv.epg.response
 * @date_time 2013-9-11下午12:27:39
 * @type_name ResourceContractAdvResponse
 */
public class ResourceContractAdvResponse extends BaseRequest{
	
	/**
	 * @field serialVersionUID
	 * @field_type long
	 */
	private static final long serialVersionUID = -7924595128678565479L;
	/**
	 * 素材索引
	 */
	private Long id;
	/**
	 * 素材创建时间
	 */
	private String fcreatetime;
	/**
	 * 素材类型
	 */
	private String ftype;
	/**
	 * 素材水水平分辨率
	 */
	private int fwidth;
	/**
	 * 素材垂直分辨率
	 */
	private int fheight;
	/**
	 * 素材路径
	 */
	private String fpath;
	/**
	 * 素材名称
	 */
	private String fname;
	/**
	 * 合同广告位索引
	 */
	private Long caid;
	/**
	 * 合同索引
	 */
	private Long fcontractid;
	/**
	 * 广告位索引
	 */
	private Long fadvid;
	/**
	 * 解析度
	 */
	private String fdefinition;
	/***
	 * 合同广告位素材索引
	 */
	private Long carid;
	/**
	 * 素材是否使用
	 */
	private String fisusing;
	/**
	 * 合同有效期开始日期
	 */
	private String fusestarttime;
	/**
	 * 合同有效期截至日期
	 */
	private String fuseendtime;
	/**
	 * 节点索引
	 */
	private Long fnodeid;
	
	private int forder;
	
	private Long fusergroupid;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the fcreatetime
	 */
	public String getFcreatetime() {
		return fcreatetime;
	}

	/**
	 * @param fcreatetime the fcreatetime to set
	 */
	public void setFcreatetime(String fcreatetime) {
		this.fcreatetime = fcreatetime;
	}

	/**
	 * @return the ftype
	 */
	public String getFtype() {
		return ftype;
	}

	/**
	 * @param ftype the ftype to set
	 */
	public void setFtype(String ftype) {
		this.ftype = ftype;
	}

	/**
	 * @return the fwidth
	 */
	public int getFwidth() {
		return fwidth;
	}

	/**
	 * @param fwidth the fwidth to set
	 */
	public void setFwidth(int fwidth) {
		this.fwidth = fwidth;
	}

	/**
	 * @return the fheight
	 */
	public int getFheight() {
		return fheight;
	}

	/**
	 * @param fheight the fheight to set
	 */
	public void setFheight(int fheight) {
		this.fheight = fheight;
	}

	/**
	 * @return the fpath
	 */
	public String getFpath() {
		return fpath;
	}

	/**
	 * @param fpath the fpath to set
	 */
	public void setFpath(String fpath) {
		this.fpath = fpath;
	}

	/**
	 * @return the fname
	 */
	public String getFname() {
		if(!StringUtils.hasText(fname)){
			fname = null;
		}
		return fname;
	}

	/**
	 * @param fname the fname to set
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}

	/**
	 * @return the caid
	 */
	public Long getCaid() {
		return caid;
	}

	/**
	 * @param caid the caid to set
	 */
	public void setCaid(Long caid) {
		this.caid = caid;
	}

	/**
	 * @return the fcontractid
	 */
	public Long getFcontractid() {
		return fcontractid;
	}

	/**
	 * @param fcontractid the fcontractid to set
	 */
	public void setFcontractid(Long fcontractid) {
		this.fcontractid = fcontractid;
	}

	/**
	 * @return the fadvid
	 */
	public Long getFadvid() {
		return fadvid;
	}

	/**
	 * @param fadvid the fadvid to set
	 */
	public void setFadvid(Long fadvid) {
		this.fadvid = fadvid;
	}

	/**
	 * @return the fdefinition
	 */
	public String getFdefinition() {
		return fdefinition;
	}

	/**
	 * @param fdefinition the fdefinition to set
	 */
	public void setFdefinition(String fdefinition) {
		this.fdefinition = fdefinition;
	}

	/**
	 * @return the carid
	 */
	public Long getCarid() {
		return carid;
	}

	/**
	 * @param carid the carid to set
	 */
	public void setCarid(Long carid) {
		this.carid = carid;
	}

	/**
	 * @return the fisusing
	 */
	public String getFisusing() {
		if(!StringUtils.hasText(fisusing)){
			fisusing = null;
		}
		return fisusing;
	}

	/**
	 * @param fisusing the fisusing to set
	 */
	public void setFisusing(String fisusing) {
		this.fisusing = fisusing;
	}

	/**
	 * @return the fusrstarttime
	 */
	public String getFusestarttime() {
		return StringUtils.hasText(fusestarttime) ? fusestarttime : null;
	}

	/**
	 * @param fusrstarttime the fusrstarttime to set
	 */
	public void setFusestarttime(String fusestarttime) {
		this.fusestarttime = fusestarttime;
	}

	/**
	 * @return the fuseendtime
	 */
	public String getFuseendtime() {
		return StringUtils.hasText(fuseendtime) ? fuseendtime : null;
	}

	/**
	 * @return the fnodeid
	 */
	public Long getFnodeid() {
		return fnodeid;
	}

	/**
	 * @param fnodeid the fnodeid to set
	 */
	public void setFnodeid(Long fnodeid) {
		this.fnodeid = fnodeid;
	}

	/**
	 * @param fuseendtime the fuseendtime to set
	 */
	public void setFuseendtime(String fuseendtime) {
		this.fuseendtime = fuseendtime;
	}

	/**
	 * @return the forder
	 */
	public int getForder() {
		return forder;
	}

	/**
	 * @param forder the forder to set
	 */
	public void setForder(int forder) {
		this.forder = forder;
	}

	public Long getFusergroupid() {
		return fusergroupid;
	}

	public void setFusergroupid(Long fusergroupid) {
		this.fusergroupid = fusergroupid;
	}
	
}