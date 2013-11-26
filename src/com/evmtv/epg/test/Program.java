package com.evmtv.epg.test;

public class Program {
	private String tsid;
	private String onid;
	private String serviceid;
	private String servicename;
	/**
	 * @return the tsid
	 */
	public String getTsid() {
		return tsid;
	}
	/**
	 * @param tsid the tsid to set
	 */
	public void setTsid(String tsid) {
		this.tsid = tsid;
	}
	/**
	 * @return the onid
	 */
	public String getOnid() {
		return onid;
	}
	/**
	 * @param onid the onid to set
	 */
	public void setOnid(String onid) {
		this.onid = onid;
	}
	/**
	 * @return the serviceid
	 */
	public String getServiceid() {
		return serviceid;
	}
	/**
	 * @param serviceid the serviceid to set
	 */
	public void setServiceid(String serviceid) {
		this.serviceid = serviceid;
	}
	/**
	 * @return the servicename
	 */
	public String getServicename() {
		return servicename;
	}
	/**
	 * @param servicename the servicename to set
	 */
	public void setServicename(String servicename) {
		this.servicename = servicename;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * @enclosing_method toString
	 * @data_time 2013-9-13 上午1:19:55
	 */
	@Override
	public String toString() {
		return "Program [tsid=" + tsid + ", onid=" + onid + ", serviceid="
				+ serviceid + ", servicename=" + servicename + "]";
	}
	
}
