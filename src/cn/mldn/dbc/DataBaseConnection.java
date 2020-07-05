package cn.mldn.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 这是数据库的操作类
 * 负责数据库的连接和关闭
 * @author ASUS
 *
 */
public class DataBaseConnection {
	//驱动程序
	private final String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private final String URL="jdbc:sqlserver://localhost;database=Ticket";
	private Connection conn = null;
	
	public  DataBaseConnection(){
		try {
			//加载数据库驱动程序
			Class.forName(driver);
			//数据库连接
			this.conn = DriverManager.getConnection(URL,"sa","qqt990908");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}
	
	/**
	 * 返回数据库连接
	 * @return 数据库连接对象
	 */
	public Connection getConnection(){
		return this.conn;
	}
	
	/**
	 *这是关闭数据库连接的方法
	 */
	public void close(){
		if(this.conn!=null){
			try {
			this.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
		
	}
}
