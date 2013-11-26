package com.evmtv.epg.response;

public class ConadvResponse {
	private Long id;
	private Long fcontractid;
	private Long fadvid;
	private String fadvlocval;
	private String fdefinition;
	private String foriginalresourceid;//原始素材索引
	private String fresourceid;//素材索引
	private Long fsourceid;//广告索引
	private String fedited;
	private String fpath;//素材地址
	private int fwidth;//素材宽
	private int fheight;//素材高
	
	public int getFwidth() {
		return fwidth;
	}
	public void setFwidth(int fwidth) {
		this.fwidth = fwidth;
	}
	public int getFheight() {
		return fheight;
	}
	public void setFheight(int fheight) {
		this.fheight = fheight;
	}
	public String getFpath() {
		return fpath;
	}
	public void setFpath(String fpath) {
		this.fpath = fpath;
	}
	public Long getFadvid() {
		return fadvid;
	}
	public void setFadvid(Long fadvid) {
		this.fadvid = fadvid;
	}
	public String getFedited() {
		return fedited;
	}
	public void setFedited(String fedited) {
		this.fedited = fedited;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getFcontractid() {
		return fcontractid;
	}
	public void setFcontractid(Long fcontractid) {
		this.fcontractid = fcontractid;
	}
	public String getFadvlocval() {
		return fadvlocval;
	}
	public void setFadvlocval(String fadvlocval) {
		this.fadvlocval = fadvlocval;
	}
	public String getFdefinition() {
		return fdefinition;
	}
	public void setFdefinition(String fdefinition) {
		this.fdefinition = fdefinition;
	}
	public String getForiginalresourceid() {
		return foriginalresourceid;
	}
	public void setForiginalresourceid(String foriginalresourceid) {
		this.foriginalresourceid = foriginalresourceid;
	}
	public String getFresourceid() {
		return fresourceid;
	}
	public void setFresourceid(String fresourceid) {
		this.fresourceid = fresourceid;
	}
	public Long getFsourceid() {
		return fsourceid;
	}
	public void setFsourceid(Long fsourceid) {
		this.fsourceid = fsourceid;
	}
}
