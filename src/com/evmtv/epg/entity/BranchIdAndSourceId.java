package com.evmtv.epg.entity;

/**
 * 分公司索引 和 广告索引 TODO
 * 
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type
 * @project_name EAMS
 * @file_name BranchIdAndSourceId.java
 * @package_name com.evmtv.epg.entity
 * @date_time 2013-10-8上午11:04:47
 * @type_name BranchIdAndSourceId
 */
public class BranchIdAndSourceId {
	private Long fbranchid;//分公司索引
	private Long fsourceid;//广告索引

	public Long getFbranchid() {
		return fbranchid;
	}

	public void setFbranchid(Long fbranchid) {
		this.fbranchid = fbranchid;
	}

	public Long getFsourceid() {
		return fsourceid;
	}

	public void setFsourceid(Long fsourceid) {
		this.fsourceid = fsourceid;
	}
}