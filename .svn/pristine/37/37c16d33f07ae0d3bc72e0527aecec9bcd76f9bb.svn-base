package com.evmtv.epg.release;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.evmtv.epg.entity.TDbConfig;
import com.evmtv.epg.utils.ArraysUtils;
/**
 * TODO 数据库操作抽象类
 * @param <T>
 * @author fangzhu(fangzhu@evmtv.com)
 * @enclosing_type 
 * @project_name EAMS
 * @file_name AbstractDao.java
 * @package_name com.evmtv.epg.db
 * @date_time 2013年7月22日下午4:00:36
 * @type_name AbstractDao
 */
public abstract class AbstractDao<T> implements IDao<T> {

	Logger logger = Logger.getLogger(getClass());
	
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private String db_url = "jdbc:mysql://localhost:3306/eams?useUnicode=true&characterEncoding=UTF-8";
	private String user = "root";
	private String pass = "mysql";
	
	/**
	 * 获取链接
	 * @return
	 */
	protected Connection getJDBCConnection() {
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER).newInstance();
			conn = DriverManager.getConnection(db_url, user, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	@Override
	public List<T> find(String queryString, Object... params){return null;}

	@Override
	public int insert(String[] column,String tableName,T t) {
		Connection conn = this.getJDBCConnection();
		PreparedStatement pst = null;
		int result = 0;
		String sql = toInsertSql(column, tableName);
		try {
			pst = conn.prepareStatement(sql);
			List<Object> strs = params(t,column);
			for(int i=0,len=strs.size();i<len;i++){
				pst.setObject(i+1, strs.get(i));
			}
			result = pst.executeUpdate();
		} catch (SQLException | SecurityException | IllegalArgumentException e) {
//			e.printStackTrace();
			logger.info(e.getMessage());
		}finally{
			closeConnection(conn);
		}
		
		return result;
	}

	@Override
	public int insert(String[] column,String tableName,List<T> ts) {
		Connection conn = this.getJDBCConnection();
		PreparedStatement pst = null;
		int result[] = {};
		String sql = toInsertSql(column, tableName);
		try {
			// 关闭事务自动提交
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(sql);
			for(T t : ts){
				List<Object> strs = params(t,column);
				for(int i=1,len=strs.size();i<=len;i++){
					pst.setObject(i, strs.get(i-1));
				}
				pst.addBatch();
			}
			result = pst.executeBatch();
			// 语句执行完毕，提交本事务
			conn.commit();
		} catch (SQLException | SecurityException | IllegalArgumentException e) {
//			e.printStackTrace();
			logger.info(e.getMessage());
		}finally{
			closeConnection(conn);
		}
		return ArraysUtils.plus(result);
	}
	
	@Override
	public int update(String...params) {
		Connection conn = this.getJDBCConnection();
		PreparedStatement pst = null;
		int flag = 0;
		StringBuilder sql = new StringBuilder("UPDATE t_version SET FVersion = (FVersion+1)%1024");
		if(params != null && params.length > 0){
			for(String param : params){
				sql.append(",").append(param).append("=(").append(param).append("+1)%1024");
			}
//			String s = sql.substring(0, sql.length()-1);
			// 创建预处理对象
		}
		try {
			pst = conn.prepareStatement(sql.toString());
			flag = pst.executeUpdate();
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.info(e.getMessage());
		}finally{
			closeConnection(conn);
		}
		return flag;
	}
	
	public int delete(String sql){
		int result = 0;
		// 获取数据库连接
		Connection conn = this.getJDBCConnection();
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			System.out.println(sql);
			result = pst.executeUpdate();
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.info(e.getMessage());
		} finally {
			closeConnection(conn);
		}
		return result;
	}
	
	@Override
	public int delete(String[] column,String tableName,String... params) {
		String sql = toDeleteSql(column, tableName);
		Connection conn = this.getJDBCConnection();
		PreparedStatement pst = null;
		int flag = 0;
		if(params.length > 0){
			String pamams = ArraysUtils.toString(params);
			sql.concat(" ").concat(pamams);
			sql = sql.substring(0, sql.length()-1);
			// 创建预处理对象
			try {
				pst = conn.prepareStatement(sql);
				flag = pst.executeUpdate();
			} catch (SQLException e) {
				//e.printStackTrace();
				logger.info(e.getMessage());
			}finally{
				closeConnection(conn);
			}
		}
		return flag;
	}

	@Override
	public int update(String[] column,String tableName,List<Long> ids) {
		String sql = toUpdateSql(column, tableName);
		Connection conn = this.getJDBCConnection();
		PreparedStatement pst = null;
		int flag = 0;
		if(!ids.isEmpty()){
			String pamams = ArraysUtils.toString(ids);
			sql.concat(" ").concat(pamams);
			sql = sql.substring(0, sql.length()-1);
			// 创建预处理对象
			try {
				pst = conn.prepareStatement(sql);
				flag = pst.executeUpdate();
			} catch (SQLException e) {
				//e.printStackTrace();
				logger.info(e.getMessage());
			}finally{
				closeConnection(conn);
			}
		}
		return flag;
	}

	/**
	 * 关闭数据库连接
	 * @param dbConnection
	 */
	protected void closeConnection(Connection dbConnection) {
		try {
			if (dbConnection != null) {
				dbConnection.close();
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
	}

	protected void closeResultSet(ResultSet result) {
		try {
			if (result != null) {
				result.close();
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
	}

	protected void closeStatement(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
	}

	public AbstractDao() {
		super();
	}
	/**
	 * 构造注入
	 * @param config
	 */
	public AbstractDao(TDbConfig config) {
		db_url = "jdbc:mysql://"+config.getFip()+":3306/eams?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false";
		user = config.getFuser();
		pass = config.getFpasswd(); 
	}
	/**
	 * 将对象转成参数值
	 * @param t
	 * @return
	 */
	private List<Object> params(T t,String[] columns){
		List<Object> strs = new ArrayList<Object>();
		try {
			Class<?> clz = t.getClass();
			for(String f : columns){
				// 构造方法名
				StringBuilder methodName = new StringBuilder("get");
				methodName.append(f);
				// 获取该方法
				Method method = clz.getMethod(methodName.toString());
				// 执行该方法
				Object value = method.invoke(t);
				strs.add(value);
			}
		} catch (NoSuchMethodException | SecurityException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
//			e.printStackTrace();
			logger.info(e.getMessage());
		}
		return strs;
	}
	/**
	 * 保存sql语句
	 * @param column
	 * @param tableName
	 * @return
	 */
	private String toInsertSql(String[] column,String tableName){
		StringBuilder placeholder = new StringBuilder();
		StringBuilder sql = new StringBuilder("REPLACE INTO ");
		sql.append(tableName);
		sql.append(" (");
		for(String c : column){
			sql.append(c).append(",");
			placeholder.append("?,");
		}
		sql = sql.deleteCharAt(sql.length()-1);
		sql.append(")");
		sql.append(" VALUES (").append(placeholder.deleteCharAt(placeholder.length()-1)).append(")");
		
		return sql.toString();
	}
	/**
	 * 更新sql
	 * @param column
	 * @param tableName
	 * @return
	 */
	private String toUpdateSql(String[] column,String tableName){
		StringBuilder sql = new StringBuilder("UPDATE ");
		sql.append(tableName);
		for(String c : column){
			sql.append(c).append("=?,");
		}
		return sql.substring(0, sql.length()-1);
	}
	/**
	 * 删除sql
	 * @param column
	 * @param tableName
	 * @return
	 */
	private String toDeleteSql(String[] column,String tableName){
		StringBuilder sql = new StringBuilder("DELETE FROM ");
		sql.append(tableName);
		sql.append(" WHERE 1=1 ");
		for(String c : column){
			sql.append("AND ").append(c).append("=?,");
		}
		return sql.substring(0, sql.length()-1);
	}
	
}