package cn.mldn.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * �������ݿ�Ĳ�����
 * �������ݿ�����Ӻ͹ر�
 * @author ASUS
 *
 */
public class DataBaseConnection {
	//��������
	private final String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private final String URL="jdbc:sqlserver://localhost;database=Ticket";
	private Connection conn = null;
	
	public  DataBaseConnection(){
		try {
			//�������ݿ���������
			Class.forName(driver);
			//���ݿ�����
			this.conn = DriverManager.getConnection(URL,"sa","qqt990908");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}
	
	/**
	 * �������ݿ�����
	 * @return ���ݿ����Ӷ���
	 */
	public Connection getConnection(){
		return this.conn;
	}
	
	/**
	 *���ǹر����ݿ����ӵķ���
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
