package com.evmtv.epg.response;

import java.util.List;

import com.evmtv.epg.entity.TContractAdv;
import com.evmtv.epg.request.BaseRequest;

/**
 * @author Lei<helei@evmtv.com>
 * @data 2013-7-16 上午9:53:11
 */

public class ContractResponse extends BaseRequest{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1297264778772841425L;
	private Long id;
	private String fguid;
	private String ftitle;
	private String fadvname;
	private String fadvlocval;
	private String fadvlevel;
	private String fdefinition;
	private String fagent;
	private String fstarttime;
	private String fendtime;
	private String fcreateuser;
	private String fcheckuser;
	private String fcontactname;
	private String fcontacttel;
	private String fprice;
	private String fdiscount;
	private String fpayway;
	private String fchecked;
	private String ffreezed;
	private String fdeleted;
	private Long nsId;
	private List<TContractAdv> contractAdv;//合同广告位
	/*private List<ConadvResponse> conadvResponses;
	public List<ConadvResponse> getConadvResponses() {
		return conadvResponses;
	}
	public void setConadvResponses(List<ConadvResponse> conadvResponses) {
		this.conadvResponses = conadvResponses;
	}*/
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFguid() {
		return fguid;
	}
	public void setFguid(String fguid) {
		this.fguid = fguid;
	}
	public String getFtitle() {
		return ftitle;
	}
	public void setFtitle(String ftitle) {
		this.ftitle = ftitle;
	}
	public String getFadvname() {
		return fadvname;
	}
	public void setFadvname(String fadvname) {
		this.fadvname = fadvname;
	}
	public String getFadvlocval() {
		return fadvlocval;
	}
	public void setFadvlocval(String fadvlocval) {
		this.fadvlocval = fadvlocval;
	}
	public String getFadvlevel() {
		return fadvlevel;
	}
	public void setFadvlevel(String fadvlevel) {
		this.fadvlevel = fadvlevel;
	}
	public String getFdefinition() {
		return fdefinition;
	}
	public void setFdefinition(String fdefinition) {
		this.fdefinition = fdefinition;
	}
	public String getFagent() {
		return fagent;
	}
	public void setFagent(String fagent) {
		this.fagent = fagent;
	}
	public String getFstarttime() {
		return fstarttime;
	}
	public void setFstarttime(String fstarttime) {
		this.fstarttime = fstarttime;
	}
	public String getFendtime() {
		return fendtime;
	}
	public void setFendtime(String fendtime) {
		this.fendtime = fendtime;
	}
	public String getFcreateuser() {
		return fcreateuser;
	}
	public void setFcreateuser(String fcreateuser) {
		this.fcreateuser = fcreateuser;
	}
	public String getFcheckuser() {
		return fcheckuser;
	}
	public void setFcheckuser(String fcheckuser) {
		this.fcheckuser = fcheckuser;
	}
	public String getFcontactname() {
		return fcontactname;
	}
	public void setFcontactname(String fcontactname) {
		this.fcontactname = fcontactname;
	}
	public String getFcontacttel() {
		return fcontacttel;
	}
	public void setFcontacttel(String fcontacttel) {
		this.fcontacttel = fcontacttel;
	}
	public String getFprice() {
		return fprice;
	}
	public void setFprice(String fprice) {
		this.fprice = fprice;
	}
	public String getFdiscount() {
		return fdiscount;
	}
	public void setFdiscount(String fdiscount) {
		this.fdiscount = fdiscount;
	}
	public String getFpayway() {
		return fpayway;
	}
	public void setFpayway(String fpayway) {
		this.fpayway = fpayway;
	}
	public String getFchecked() {
		return fchecked;
	}
	public void setFchecked(String fchecked) {
		this.fchecked = fchecked;
	}
	public String getFfreezed() {
		return ffreezed;
	}
	public void setFfreezed(String ffreezed) {
		this.ffreezed = ffreezed;
	}
	public String getFdeleted() {
		return fdeleted;
	}
	public void setFdeleted(String fdeleted) {
		this.fdeleted = fdeleted;
	}
	public List<TContractAdv> getContractAdv() {
		return contractAdv;
	}
	public void setContractAdv(List<TContractAdv> contractAdv) {
		this.contractAdv = contractAdv;
	}
	public Long getNsId() {
		return nsId;
	}
	public void setNsId(Long nsId) {
		this.nsId = nsId;
	}
}
