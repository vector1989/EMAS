/**
 * @project_name EAMS
 * @file_name StatusContractOrRvOrCar.java
 * @package_name com.evmtv.epg.response
 * @date_time 2013年12月7日上午11:30:57
 * @type_name StatusContractOrRvOrCar
 */
package com.evmtv.epg.response;

/**
 * <p>Title: 我的任务</p> 
 * <p>Description: </p> 
 * <p>Date: 2013年12月7日上午11:30:57</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * <p>Company: www.evmtv.com</p> 
 * @author fangzhu@evmtv.com
 * @version 1.0 
 */
public class MyTask {
	private Long id;
	private Integer fisvalid;//是否有效
	private Integer fisHandle;//是否处理
	private String branch;//分公司名称
	private String fguid;//合同编号
	private String fcontractname;//合同名称
	private Long cid;//合同索引
	private String fversion;//版本号
	private String fdefinition;//解析度
	private Long rvid;//版本索引
	private String rname;//素材名称
	private String fpath;//素材地址
	private Long carid;//合同广告位索引
	private String fdesc;
	private String fupdatetime;
	
	public Integer getFisvalid() {
		return fisvalid;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setFisvalid(Integer fisvalid) {
		this.fisvalid = fisvalid;
	}
	public Integer getFisHandle() {
		return fisHandle;
	}
	public void setFisHandle(Integer fisHandle) {
		this.fisHandle = fisHandle;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getFguid() {
		return fguid;
	}
	public void setFguid(String fguid) {
		this.fguid = fguid;
	}
	public String getFcontractname() {
		return fcontractname;
	}
	public void setFcontractname(String fcontractname) {
		this.fcontractname = fcontractname;
	}
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	public String getFversion() {
		return fversion;
	}
	public void setFversion(String fversion) {
		this.fversion = fversion;
	}
	public String getFdefinition() {
		return fdefinition;
	}
	public void setFdefinition(String fdefinition) {
		this.fdefinition = fdefinition;
	}
	public Long getRvid() {
		return rvid;
	}
	public void setRvid(Long rvid) {
		this.rvid = rvid;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getFpath() {
		return fpath;
	}
	public void setFpath(String fpath) {
		this.fpath = fpath;
	}
	public Long getCarid() {
		return carid;
	}
	public void setCarid(Long carid) {
		this.carid = carid;
	}
	public String getFdesc() {
		return fdesc;
	}
	public void setFdesc(String fdesc) {
		this.fdesc = fdesc;
	}
	public String getFupdatetime() {
		return fupdatetime;
	}
	public void setFupdatetime(String fupdatetime) {
		this.fupdatetime = fupdatetime;
	}
}
