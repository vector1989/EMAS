package com.evmtv.epg.test;

public class Inc {

	/*private String descriptorTag;
	private int descriptorLength;
	private int bouquetNum;
	private String bouquetId;
	private int bouquetNameLength;
	private String bouquetName;
	
	private int tsNumber;
	private String tsId;
	private String onId;
	private String frequency;
	private String symbolRate;
	private String modulation;
	private int serviceCnt;
	private String serviceId;
	private String fecOuter;
	private String fecInner;
	private String reservedFautureUse;*/
	
	private int progCnt;
	private String dtag;
	private int dLength;
	private String onid;
	private String sid;
	private String stype;
	private String pmtPid;
	private String vType;
	private String vPid;
	private int acount;
	private String atype;
	private String apid;
	private String pcrPid;
	private String subtitlePid;
	private String teleTextPid;
	private String scrambleFlag;
	private int spnLength;
	private String serviceProvName;
	private int serviceNameLength;
	private String serviceName;
	private int bouqetIdNumber;
	private String bouquetId;
	private int authoridNumber;
	private String authorId;
	private int programPrivLoopLength;
	private String program;
	private String programReserved;
	private String tsid;
	public int getProgCnt() {
		return progCnt;
	}
	public void setProgCnt(int progCnt) {
		this.progCnt = progCnt;
	}
	public String getDtag() {
		return dtag;
	}
	public void setDtag(String dtag) {
		this.dtag = dtag;
	}
	public int getdLength() {
		return dLength;
	}
	public void setdLength(int dLength) {
		this.dLength = dLength;
	}
	public String getOnid() {
		return onid;
	}
	public void setOnid(String onid) {
		this.onid = onid;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getStype() {
		return stype;
	}
	public void setStype(String stype) {
		this.stype = stype;
	}
	public String getPmtPid() {
		return pmtPid;
	}
	public void setPmtPid(String pmtPid) {
		this.pmtPid = pmtPid;
	}
	public String getvType() {
		return vType;
	}
	public void setvType(String vType) {
		this.vType = vType;
	}
	public String getvPid() {
		return vPid;
	}
	public void setvPid(String vPid) {
		this.vPid = vPid;
	}
	public int getAcount() {
		return acount;
	}
	public void setAcount(int acount) {
		this.acount = acount;
	}
	public String getAtype() {
		return atype;
	}
	public void setAtype(String atype) {
		this.atype = atype;
	}
	public String getApid() {
		return apid;
	}
	public void setApid(String apid) {
		this.apid = apid;
	}
	public String getPcrPid() {
		return pcrPid;
	}
	public void setPcrPid(String pcrPid) {
		this.pcrPid = pcrPid;
	}
	public String getSubtitlePid() {
		return subtitlePid;
	}
	public void setSubtitlePid(String subtitlePid) {
		this.subtitlePid = subtitlePid;
	}
	public String getTeleTextPid() {
		return teleTextPid;
	}
	public void setTeleTextPid(String teleTextPid) {
		this.teleTextPid = teleTextPid;
	}
	public String getScrambleFlag() {
		return scrambleFlag;
	}
	public void setScrambleFlag(String scrambleFlag) {
		this.scrambleFlag = scrambleFlag;
	}
	public int getSpnLength() {
		return spnLength;
	}
	public void setSpnLength(int spnLength) {
		this.spnLength = spnLength;
	}
	public String getServiceProvName() {
		return serviceProvName;
	}
	public void setServiceProvName(String serviceProvName) {
		this.serviceProvName = serviceProvName;
	}
	public int getServiceNameLength() {
		return serviceNameLength;
	}
	public void setServiceNameLength(int serviceNameLength) {
		this.serviceNameLength = serviceNameLength;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public int getBouqetIdNumber() {
		return bouqetIdNumber;
	}
	public void setBouqetIdNumber(int bouqetIdNumber) {
		this.bouqetIdNumber = bouqetIdNumber;
	}
	public String getBouquetId() {
		return bouquetId;
	}
	public void setBouquetId(String bouquetId) {
		this.bouquetId = bouquetId;
	}
	public int getAuthoridNumber() {
		return authoridNumber;
	}
	public void setAuthoridNumber(int authoridNumber) {
		this.authoridNumber = authoridNumber;
	}
	public String getAuthorId() {
		return authorId;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	public int getProgramPrivLoopLength() {
		return programPrivLoopLength;
	}
	public void setProgramPrivLoopLength(int programPrivLoopLength) {
		this.programPrivLoopLength = programPrivLoopLength;
	}
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	public String getProgramReserved() {
		return programReserved;
	}
	public void setProgramReserved(String programReserved) {
		this.programReserved = programReserved;
	}
	public String getTsid() {
		return tsid;
	}
	public void setTsid(String tsid) {
		this.tsid = tsid;
	}
	@Override
	public String toString() {
		return "Inc [onid=" + onid + ", sid=" + sid + ", serviceName=" + serviceName +", tsid=" + tsid + "]";
	}
	
}
