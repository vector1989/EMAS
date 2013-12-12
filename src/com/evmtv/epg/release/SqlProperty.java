package com.evmtv.epg.release;

import java.util.Set;

/**
 * sql语句
 * TODO
 * @author Fangzhu(Fangzhu@evmtv.com)
 * @enclosing_type 
 * @project_name EAMS
 * @File_name Sql.java
 * @package_name com.evmtv.epg.db
 * @date_time 2013年7月22日下午4:50:32
 * @type_name Sql
 */
public class SqlProperty {
	
	//版本
	public static final String updateVersionSql = "UPDATE t_version set ";
	
	private static String[] versionColums = {"FBootFullscreen","FBootVideo","FMainMenu","FInfoBar","FProramList","FFavoritesList","FHDList","FCategoryList","FDigitalAudio","FProgramGuide","FNVOD","TVolumeBar"};
	/**
	 * 版本控制字段
	 * @param pos
	 * @return
	 */
	public static String[] toUpdateColumns(Set<Integer> pos){
		String[] colums = new String[pos.size()];
		int i=0;
		for(int p : pos){
			colums[i++] = versionColums[p-1];
		}
		return colums;
	}
	/**
	 * 查询类
	 * TODO
	 * @author fangzhu(fangzhu@evmtv.com)
	 * @enclosing_type Sql
	 * @project_name EAMS
	 * @file_name Sql.java
	 * @package_name com.evmtv.epg.release
	 * @date_time 2013-8-30下午3:51:19
	 * @type_name Query
	 */
	public static class Query{
		//查询分公司广告的所以合同
		public static final String queryContractIdSql = "SELECT DISTINCT FContractID FROM t_source";
		
		//最大修改时间和最大添加时间
		@Deprecated
		public static final String queryMaxCreateTime = "SELECT FCreatetime FROM t_source  ORDER BY FCreatetime DESC LIMIT 0,1";
		
		@Deprecated
		public static final String queryMaxUpdatetime = "SELECT FUpdatetime FROM t_source  ORDER BY FUpdatetime DESC LIMIT 0,1";

		@Deprecated
		public static final String[] RELEASE_ERROR_LOG = {"广告发布失败","广告时间段发布失败","广告频点业务发布失败","广告位更新失败"};
	}
	/**
	 * 删除
	 * TODO
	 * @author fangzhu(fangzhu@evmtv.com)
	 * @enclosing_type SqlProperty
	 * @project_name EAMS
	 * @file_name SqlProperty.java
	 * @package_name com.evmtv.epg.release
	 * @date_time 2013-8-30下午4:06:43
	 * @type_name Delete
	 */
	public static class Delete{
		/**
		 * ?为占位符，根据合同删除资源
		 */
		public static final String DELETE_SOURCE_SQL = "DELETE FROM t_source WHERE FContractId IN (?)";

		public static final String DELETE_SOURCE_SQL_ALL = "DELETE FROM t_source WHERE FDefinition = ";
		public static final String DELETE_ADV_SQL_ALL = "DELETE FROM t_adv WHERE FDefinition = ";
		public static final String DELETE_CHANNELS_SQL_ALL = "DELETE FROM t_channels";
		public static final String DELETE_TIME_SQL_ALL = "DELETE FROM t_time_period WHERE FDefinition = ";
	}
	/**
	 * 字段
	 * TODO
	 * @author fangzhu(fangzhu@evmtv.com)
	 * @enclosing_type SqlProperty
	 * @project_name EAMS
	 * @file_name SqlProperty.java
	 * @package_name com.evmtv.epg.release
	 * @date_time 2013-8-30下午3:58:30
	 * @type_name Filed
	 */
	public static class Filed{
		//广告位
		public static final String[] advColumns = {"Id","Fadvclassid","Ftype","Fdefinition","Fbranchid","Fcontractid","Fpositionid"};
		//频点
		public static final String[] channelColumns = {"Id","Ftsid","Fonid","Fname","Fserviceid","Fbranchid"};
		//广告
		public static final String[] sourceColumns = {"Id","Fresourceid","Ftimeperiodid","Fpath","Fchannelsid","Fadvid","Fbitdata","Fleft","Ftop","Forder","Ffile","Fversion","Fname","Fwidth","Fheight","Fcontractid","Fdefinition","Fsourceid","Fplaydate","Fenddate","Felementtype","Fduration"};//"Fpid","Ftableid",
		//时间段
		public static final String[] timeColums = {"Id","Fstarttime","Fendtime","Fadvclassid","Fbranchid","Fdeleted"};
		
	}
}