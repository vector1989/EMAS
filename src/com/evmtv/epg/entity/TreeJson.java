package com.evmtv.epg.entity;

/**
 * 
 * TODO
 * 
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type
 * @project_name EAMS
 * @file_name TreeJson.java
 * @package_name com.evmtv.epg.entity
 * @date_time 2013年10月23日上午11:32:18
 * @type_name TreeJson
 */
public class TreeJson {
	private String id;//当前节点索引
	private Long rvid;//版本号
	private Long vaid;//广告位索引
	private Long vsid;//广告版本索引
	private Long provRvid;//省公司版本
	private String pId;//父节点索引
	private Long advid;//广告位索引
	private String name;//树显示名称
	private String font;//字体
	private String starttime = "00:00";//开始时间
	private Long timeperiodid;//时间段索引
	private Integer positionid = -5;//广告位
	private Long leftMenuAdvid;//左主菜单广告位
	private boolean open = true;//树节点是否打开
	private boolean click = true;//点击事件
	private boolean root = false;//是否root节点
	private boolean disabled = true;
	private boolean checked = false;
	private boolean isParent = false;//是否父节点

	public TreeJson() {
		super();
	}

	public TreeJson(String id, String pId, String name, boolean isParent,
			String font) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.isParent = isParent;
		this.font = font;
	}

	public TreeJson(String id, String pId, String name, boolean root) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.root = root;
	}

	public TreeJson(String id, String pId, String name) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public String getFont() {
		return font;
	}

	public void setFont(String font) {
		this.font = font;
	}

	public boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}

	public boolean getClick() {
		return click;
	}

	public void setClick(boolean click) {
		this.click = click;
	}

	public boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public boolean getChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isRoot() {
		return root;
	}

	public void setRoot(boolean root) {
		this.root = root;
	}

	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}

	public Integer getPositionid() {
		return positionid;
	}

	public void setPositionid(Integer positionid) {
		this.positionid = positionid;
	}

	public Long getAdvid() {
		return advid;
	}

	public void setAdvid(Long advid) {
		this.advid = advid;
	}

	public Long getTimeperiodid() {
		return timeperiodid;
	}

	public void setTimeperiodid(Long timeperiodid) {
		this.timeperiodid = timeperiodid;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}


	@Override
	public String toString() {
		return "TreeJson [id=" + id + ", pId=" + pId + ", name=" + name
				+ ", open=" + open + ", isParent=" + isParent + ", font="
				+ font + ", click=" + click + ", disabled=" + disabled
				+ ", checked=" + checked + "]";
	}

	public Long getLeftMenuAdvid() {
		return leftMenuAdvid;
	}

	public void setLeftMenuAdvid(Long leftMenuAdvid) {
		this.leftMenuAdvid = leftMenuAdvid;
	}

	public Long getRvid() {
		return rvid;
	}

	public void setRvid(Long rvid) {
		this.rvid = rvid;
	}

	public Long getVaid() {
		return vaid;
	}

	public void setRaid(Long vaid) {
		this.vaid = vaid;
	}

	public Long getVsid() {
		return vsid;
	}

	public void setVsid(Long vsid) {
		this.vsid = vsid;
	}

	public Long getProvRvid() {
		return provRvid;
	}

	public void setProvRvid(Long provRvid) {
		this.provRvid = provRvid;
	}

	public void setVaid(Long vaid) {
		this.vaid = vaid;
	}
}