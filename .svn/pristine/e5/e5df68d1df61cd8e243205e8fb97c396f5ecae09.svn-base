package com.evmtv.epg.release;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.evmtv.epg.entity.TDbConfig;
import com.evmtv.epg.entity.TTimePeriod;
import com.evmtv.epg.utils.ArraysUtils;
/**
 * 时间段
 * TODO
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type 
 * @project_name EAMS
 * @file_name TimeDao.java
 * @package_name com.evmtv.epg.db
 * @date_time 2013年7月23日下午5:59:58
 * @type_name TimeDao
 */
public class TimeDao extends AbstractDao<TTimePeriod> {

	public TimeDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TimeDao(TDbConfig config) {
		super(config);
		// TODO Auto-generated constructor stub
	}

	public int insert(String[] timecolums, List<TTimePeriod> periods) {
		// TODO Auto-generated method stub
		
		return super.insert(timecolums, "t_time_period", periods);
	}
	
	public int insert(List<TTimePeriod> periods,String definition){
		String sql = "REPLACE INTO t_time_period (Id,Fstarttime,Fendtime,Fadvclassid,Fbranchid,Fdeleted,FDefinition) VALUES (?,?,?,?,?,?,?)";
		Connection conn = this.getJDBCConnection();
		int[] result = null;
		try {
			// 关闭事务自动提交
			conn.setAutoCommit(false);
			PreparedStatement pst = conn.prepareStatement(sql);
			for(TTimePeriod t : periods){
				pst.setObject(1, t.getId());
				pst.setObject(2, t.getFstarttime());
				pst.setObject(3, t.getFendtime());
				pst.setObject(4, t.getFadvclassid());
				pst.setObject(5, t.getFbranchid());
				pst.setObject(6, t.getFdeleted());
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
