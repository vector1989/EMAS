package com.evmtv.epg.response;
/**
 * 访问所请求的页面   形式：根目录/文件夹/JSP页面  JSP 页面不用跟后缀 spring会自动寻找该页面
 * @author fangzhu(fangzhu@evmtv.com)
 * @time 2013-5-31 上午8:39:37
 */
public interface PageUtils {

	/**登录首页*/
	public static final String loginIndex = "/main/login";
	
	public static final String centerIndex = "/main/center";
	/**左边菜单栏 */
	public static final String downIndex = "/main/down";
	/**头部*/
	public static final String topIndex = "/main/top";
	/**控制侧边栏显示隐藏*/
	public static final String leftIndex = "/main/left";
	public static final String mainIndex = "/main/main";
	public static final String json = "/json";
	/**500错误页*/
	public static final String _500Page = "/error/500";
	/**404错误页*/
	public static final String _404Page = "/error/404";
	
	public static final String createAdvIndex="/main/adv/createAdv";
	/**上传资源信息*/
	public static final String uploadResource = "/main/resource/uploadResource";
	/**资源列表列表页*/
	public static final String resourceListIndex = "/main/resource/list";
	//时间
	public static final String timeIndex = "/main/adv/time";
	//频点
	public static final String channelsIndex = "/main/adv/channels";
	//频道分组
	public static final String cgroupIndex = "/main/adv/channelGroup";
	//广告位编辑
	public static final String advIndex = "/main/adv/adv";
	//广告编辑
	public static final String sourceIndex = "/main/adv/sourceList";
	//基本数据
	public static final String advBaseIndex = "/main/adv/advBase";
	//分公司
	public static final String branchIndex = "/main/adv/branch";
	//用户
	public static final String userIndex = "/main/adv/user";
	//用户组
	public static final String groupIndex = "/main/adv/userGroup";
	//用户设置
	public static final String userSetting = "/main/adv/userSetting";
	//流程控制
	public static final String nodeIndex = "/main/adv/node";
	//用户节点
	public static final String userNodeIndex = "/main/adv/userNode";
	//我的任务
	public static final String myTask = "/main/adv/myTask";
	//审核状态页面
	public static final String statusIndex = "/main/adv/status";
	//分公司数据库配置
	public static final String dbconfig = "/main/adv/dbconfig";
	//广告发布
	public static final String advRelease = "/main/adv/sourceRelease";
	//正在使用的广告
	public static final String sourceUsing = "/main/adv/sourceUsing";
	//广告位编辑
	public static final String advClassConfigIndex = "/main/adv/advClassConfig";
	/**合同列表页*/
	public static final String contractIndex = "/main/contract/contractList";
	/**合同新增页*/
	public static final String addContract = "/main/contract/addContract";
	public static final String chartIndex = "/main/contract/contractStatistics";

	public static final String toolsIndex = "/main/tools";
	//播发服务器播发状态
	public static final String broadcastServerIndex = "/main/adv/broadcastServer";
	//测试页面
	public static final String testIndex = "/main/adv/sourceTest";


}