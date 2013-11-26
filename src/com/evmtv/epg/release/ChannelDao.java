package com.evmtv.epg.release;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.evmtv.epg.entity.TChannels;
import com.evmtv.epg.entity.TDbConfig;
import com.evmtv.epg.utils.ArraysUtils;
/**
 * 
 * TODO
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type 
 * @project_name EAMS
 * @file_name ChannelDao.java
 * @package_name com.evmtv.epg.db
 * @date_time 2013年7月23日下午6:00:40
 * @type_name ChannelDao
 */
public class ChannelDao extends AbstractDao<TChannels> {

	public ChannelDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChannelDao(TDbConfig config) {
		super(config);
		// TODO Auto-generated constructor stub
	}

	public int insert(String[] channelcolumns, List<TChannels> channels) {
		// TODO Auto-generated method stub
		return super.insert(channelcolumns, "t_channels", channels);
	}
	
	public int insert(List<TChannels> cs,String definition){
		String sql = "REPLACE INTO t_channels (Id,Ftsid,Fonid,Fname,Fserviceid,Fbranchid,FDefinition) VALUES (?,?,?,?,?,?,?)";
		Connection conn = this.getJDBCConnection();
		int[] result = null;
		try {
			// 关闭事务自动提交
			conn.setAutoCommit(false);
			PreparedStatement pst = conn.prepareStatement(sql);
			for(TChannels c : cs){
				pst.setObject(1, c.getId());
				pst.setObject(2, c.getFtsid());
				pst.setObject(3, c.getFonid());
				pst.setObject(4, c.getFname());
				pst.setObject(5, c.getFserviceid());
				pst.setObject(6, c.getFbranchid());
				pst.setObject(7, definition);
				
				pst.addBatch();
			}
			result = pst.executeBatch();
			// 语句执行完毕，提交本事务
			conn.commit();
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}finally{
			closeConnection(conn);
		}
		return ArraysUtils.plus(result);
	}
	
}