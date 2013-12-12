package com.evmtv.epg.release;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.evmtv.epg.entity.TDbConfig;
import com.evmtv.epg.entity.TVersion;
/**
 * 
 * TODO
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type 
 * @project_name EAMS
 * @file_name VersionDao.java
 * @package_name com.evmtv.epg.db
 * @date_time 2013年7月23日下午6:02:01
 * @type_name VersionDao
 */

public class VersionDao extends AbstractDao<TVersion> {

	public VersionDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VersionDao(TDbConfig config) {
		super(config);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 修改分公司服务器播放状态
	 * @param playStatus
	 * @return
	 */
	public int update(Integer playStatus){
		Connection conn = this.getJDBCConnection();
		PreparedStatement pst = null;
		int flag = 0;
		String sql = "UPDATE t_version SET FPlayStatus = ?";
		// 创建预处理对象
		try {
			pst = conn.prepareStatement(sql.toString());
			pst.setInt(1, playStatus);
			flag = pst.executeUpdate();
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.info(e.getMessage());
		}finally{
			closeConnection(conn);
		}
		return flag;
	}
}
