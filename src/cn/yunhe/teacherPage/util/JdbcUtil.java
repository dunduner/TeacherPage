package cn.yunhe.teacherPage.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class JdbcUtil {

	// 表示定义数据库的用户名
	private static String USERNAME ;

	// 定义数据库的密码
	private static String PASSWORD;

	// 定义数据库的驱动信息
	private static String DRIVER;

	// 定义访问数据库的地址
	private static String URL;

	// 定义数据库的链接
	private Connection connection;

	// 定义sql语句的执行对象
	private PreparedStatement pstmt;

	// 定义查询返回的结果集合
	private ResultSet resultSet;
	
	static{
		//加载数据库配置信息，并给相关的属性赋值
		loadConfig();
	}

	/**
	 * 加载数据库配置信息，并给相关的属性赋值
	 */
	public static void loadConfig() {
		try {
			InputStream inStream = JdbcUtil.class.getResourceAsStream("/jdbc.properties");
			Properties prop = new Properties();
			prop.load(inStream);
			USERNAME = prop.getProperty("jdbc.username");
			PASSWORD = prop.getProperty("jdbc.password");
			DRIVER= prop.getProperty("jdbc.driver");
			URL = prop.getProperty("jdbc.url");
		} catch (Exception e) {
			throw new RuntimeException("读取数据库配置文件异常！", e);
		}
	}

	

	/**
	 * 获取数据库连接
	 * 
	 * @return 数据库连接
	 */
	public Connection getConnection() {
		try {
			Class.forName(DRIVER); // 注册驱动
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); // 获取连接
		} catch (Exception e) {
			throw new RuntimeException("get connection error!", e);
		}
		return connection;
	}
	
	/**
	 * 释放资源
	 */
	public void releaseConn() {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		try {
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.releaseConn();
		}
	}
}
