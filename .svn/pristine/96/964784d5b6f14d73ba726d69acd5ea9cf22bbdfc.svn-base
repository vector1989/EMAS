package com.evmtv.epg.release;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.evmtv.epg.entity.TDbConfig;
import com.evmtv.epg.entity.TSource;
import com.evmtv.epg.utils.ArraysUtils;
import com.evmtv.epg.utils.CollectionUtills;
import com.evmtv.epg.utils.DateUtils;

/**
 * 
 * TODO
 * 
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type
 * @project_name EAMS
 * @file_name SourceDao.java
 * @package_name com.evmtv.epg.db
 * @date_time 2013年7月23日下午6:01:13
 * @type_name SourceDao
 */
public class SourceDao extends AbstractDao<TSource> {

	public SourceDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SourceDao(TDbConfig config) {
		super(config);
		// TODO Auto-generated constructor stub
	}

	public int insert(String[] column, List<TSource> ts) {
		// TODO Auto-generated method stub
		return super.insert(column, "t_source", ts);
	}

	/**
	 * 
	 * @return
	 */
	@Deprecated
	public String getMaxTime() {
		Connection conn = getJDBCConnection();
		String createTime = "";
		String updateTime = "";
		try {
			// 查询最大新增时间
			PreparedStatement pst = conn
					.prepareStatement(SqlProperty.Query.queryMaxCreateTime);
			ResultSet rs = pst.executeQuery();
			if (rs.next())
				createTime = rs.getString(1);
			// 查询最大修改时间
			pst = conn.prepareStatement(SqlProperty.Query.queryMaxUpdatetime);
			rs = pst.executeQuery();
			if (rs.next())
				updateTime = rs.getString(1);
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.info(e.getMessage());
		} finally {
			closeConnection(conn);
		}
		if (StringUtils.hasText(createTime) && StringUtils.hasText(updateTime))
			return DateUtils.compare(createTime, updateTime);
		else
			return StringUtils.hasText(createTime) ? createTime : updateTime;
	}

	/**
	 * 查询分公司正在播发广告所有合同索引
	 * 
	 * @param sql
	 * @return
	 */
	@Deprecated
	public List<Long> find(String sql) {
		List<Long> contarctIds = new ArrayList<Long>();
		// 获取数据库连接
		Connection conn = this.getJDBCConnection();
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				contarctIds.add(rs.getLong(1));
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.info(e.getMessage());
		} finally {
			this.closeConnection(conn);
		}
		return contarctIds;
	}

	/**
	 * 删除已过期的广告
	 * 
	 * @param contracids
	 * @return
	 */
	public int delete(List<Long> contracids) {
		int result = 0;
		// 将集合转为字符串
		String conids = ArraysUtils.toString(contracids);
		if (CollectionUtills.hasElements(contracids)) {
			// 生成sql语句
			String sql = SqlProperty.Delete.DELETE_SOURCE_SQL.replace("?",
					conids);
			// 获取数据库连接
			Connection conn = this.getJDBCConnection();
			try {
				PreparedStatement pst = conn.prepareStatement(sql);
				result = pst.executeUpdate();
			} catch (SQLException e) {
//				e.printStackTrace();
				logger.info(e.getMessage());
			} finally {
				closeConnection(conn);
			}
		}
		return result;
	}
	/**
	 * 删除
	 * @return
	 */
	public int delete() {
		// 生成sql语句
		String sql = SqlProperty.Delete.DELETE_SOURCE_SQL_ALL;
		return super.delete(sql);
	}
}
