package cn.mldn.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.mldn.dao.IUserOrderDao;
import cn.mldn.pojo.UserOrder;
import cn.mldn.pojo.Userd;
/**
 * 这是数据层IUserOrderDao的实现类
 * 完成用户车票订单的查询和退票，购票等一系列操作的实现
 * @author ASUS
 *
 */
public class UserOrderDaoImpl implements IUserOrderDao {
	private Connection conn=null;//定义数据库连接对象
	private PreparedStatement pstmt;//定义预编译对象
	private UserOrder useOrder;
	public UserOrderDaoImpl(Connection conn){
		super();
		this.conn=conn;
	}
	
	/**
	 * 这是用户查询车票订单的功能
	 * @param IDCard 用户身份证号
	 * @return 查询的车票订单的集合
	 * @throws SQLException 
	 */
	@Override
	public List<UserOrder> findMyAllTicket(String IDCard) throws SQLException {
		//创建List集合存储已购买的车次
		List<UserOrder> ownList=new ArrayList<UserOrder>();

		String sql="select trainNumber.trainNum,passengerName,IDCard,startPlace,destination "
				+ "from trainNumber join userOrder "
				+ "on (trainNumber.trainNum = userOrder.trainNum and userOrder.IDCard=?)";
	
		//创建预编译对象
		this.pstmt=this.conn.prepareStatement(sql);
		this.pstmt.setString(1, IDCard);
		
		//获得结果集
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			//创建订单对象，存储用户自己的订单信息
			useOrder=new UserOrder();
			useOrder.setStartPlace(rs.getString("startPlace"));
			useOrder.setPassengName(rs.getString("passengerName"));
			useOrder.setTrainName(rs.getString("trainNum"));
			useOrder.setDestPlace(rs.getString("destination"));
			useOrder.setIDCard(rs.getString("IDCard"));
			ownList.add(useOrder);//将查询结果全部存入集合
		}
		
		return ownList;//返回订票记录的结果集合
	}

	
	
	/**
	 * 这是用户进行退票功能（根据查询的车次号退票）
	 * @param trainNum 车次号
	 * @param id 用户身份证号
	 * @return 是否退票成功
	 * @throws SQLException 
	 */
	@Override
	public boolean doReturn(String trainNum,String id) throws SQLException {
		
		boolean flag=false;
		String sql="select trainNum,remainTicket from trainNumber where trainNum in "
				+ "(select trainNum from userOrder where trainNum=? and IDCard=?)";

		//创建预编译对象
		this.pstmt=this.conn.prepareStatement(sql);
		this.pstmt.setString(1, trainNum);
		this.pstmt.setString(2, id);
		//获得查询的结果集
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			//在总 车次表中修改该车次的票数，退票则票数+1
				String sql1="Update TrainNumber set remainTicket= replace(remainTicket,?,?) where trainNum = ?";
				PreparedStatement psmt=this.conn.prepareStatement(sql1);
				//退票后将原车票的票数增加
				Integer ir=rs.getInt(2)+1;
				psmt.setInt(1, rs.getInt(2));
				psmt.setInt(2, ir);
				psmt.setString(3, rs.getString(1));
				psmt.executeUpdate();
				flag=true;
				
		break;
		}

		return flag;
	}
	
	
	

	/**
	 * 这是用户进行买票的功能
	 * @param trainNum 车次号
	 * @param user 欲买票用户对象
	 * @return 是否购买成功，成功返回true，否则返回false
	 * @throws SQLException
	 */
	@Override
	public boolean doBuyTicket(Userd user,String trainNum) throws SQLException {
		
		//插入语句，将购票信息添加到购票表中
		String sql="insert into userOrder(trainNum,passengerName, IDCard) values(?,?,?)";
		this.pstmt=this.conn.prepareStatement(sql);
		this.pstmt.setString(1, trainNum);
		this.pstmt.setString(2, user.getPasseName());
		this.pstmt.setString(3, user.getIDCard());
		if(this.pstmt.executeUpdate()>0){
			return true;
		}
		
		return false;
	}
	
	/**
	 * 从订单记录删除用户自己的订单
	 * @param trainNum 车次号
	 * @param id  身份证号
	 * @return 删除是否成功。删除成功返回true，否则false
	 * @throws SQLException
	 */
	public boolean doDelete(String trainNum,String id) throws SQLException {
		String sql="delete from userOrder where trainNum=? and IDCard=?";
		PreparedStatement psmt=this.conn.prepareStatement(sql);
		//创建预编译对象
		psmt=this.conn.prepareStatement(sql);
		psmt.setString(1, trainNum);
		psmt.setString(2, id);
		psmt.executeUpdate();
		return true;
	}
	
	
	/***
	 * 这是管理员对所有订单的查询操作
	 * @return 所有订单信息的集合
	 */
	@Override
	public List<UserOrder> findManageOrder() {
		//String sql="select * from userOrder";,startPlace,destination,passengerName,IDCard
		String sql="select userOrder.trainNum,startPlace,destination,passengerName,IDCard"
				+ "  from userOrder,trainNumber"
				+ "  where trainNumber.trainNum = userOrder.trainNum";
 		
		List<UserOrder> list=new ArrayList<UserOrder>();
		//获取预编译对象
		try {
			this.pstmt=this.conn.prepareStatement(sql);
			//获取查询结果集
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				useOrder=new UserOrder();
				useOrder.setTrainName(rs.getString(1));
				useOrder.setStartPlace(rs.getString(2));
				useOrder.setDestPlace(rs.getString(3));
				useOrder.setPassengName(rs.getString(4));
				useOrder.setIDCard(rs.getString(5));
				list.add(useOrder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
