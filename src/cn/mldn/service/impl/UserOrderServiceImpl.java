package cn.mldn.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import cn.mldn.dao.IUserOrderDao;
import cn.mldn.dbc.DataBaseConnection;
import cn.mldn.factory.DAOFactory;
import cn.mldn.pojo.TrainNumber;
import cn.mldn.pojo.UserOrder;
import cn.mldn.pojo.Userd;
import cn.mldn.service.IUserOrderService;

public class UserOrderServiceImpl implements IUserOrderService{
	
	 //取得数据库连接
	private Connection conn=new DataBaseConnection().getConnection();
	PreparedStatement pstmt;
	//获取对象，方便业务层上层获取
	public UserOrderServiceImpl(){
		super();
	}
	/**
	 * 这是用户买票功能
	 * @param user 用户对象 
	 * @param trainNum 车次号
	 * @param remain 车票数，购票后要进行车票数-1
	 * @return 是否购买成功
	 */
	@Override
	public boolean buy(Userd user, String trainNum,Integer remain) throws SQLException {
		String sql1="select count(trainNum) from userOrder where trainNum =?";
		int coun=0;
		this.pstmt=this.conn.prepareStatement(sql1);
		this.pstmt.setString(1, trainNum);
		ResultSet rs=this.pstmt.executeQuery();
		while(rs.next()){
			coun=rs.getInt(1);
		}
		if(coun==1){
			return false;
		}
		//通过DAO工厂获取对象实例，用来获取订票的方法
		IUserOrderDao iorder=DAOFactory.getIUserOrderDaoInstance(conn);
		
		//传入参数的方法为当前进入系统的用户以及车次号
		if(iorder.doBuyTicket(user, trainNum)){
			//用户购买成功，则将车次表中该车次票数-1
			String sql="Update trainNumber"+" set remainTicket =? where trainNum= ?";
			remain=remain-1;
//			if(remain==0){
//				//若用户购买了票，然后该票刚好售完，就删除此车票
//				itrain.deleteTicket(trainNum);
//			}
			PreparedStatement psmt=this.conn.prepareStatement(sql);
			psmt.setInt(1, remain);
			psmt.setString(2, trainNum);
			psmt.executeUpdate();
			
		}
		conn.close();
		return true;
	}

	/**
	 * 这是用户查询订单操作
	 * @param IDCard 身份证号
	 * @return 查询订单详情
	 */
	@Override
	public List<UserOrder> findUseOrder(String IDCard) {
		//通过工厂模式获得DAO层数据实现类对象
		IUserOrderDao iuseD=DAOFactory.getIUserOrderDaoInstance(conn);
		//定义集合，存储用户的订单信息
		List<UserOrder> orderList=new ArrayList<UserOrder>();
		//定义可变长向量，存储用户的订单信息
		try {
			orderList=iuseD.findMyAllTicket(IDCard);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}

	
	/**
	 * 这是用户退票
	 * @param trainNum 车次号
	 * @param id 身份证号
	 * @return 是否退票成功
	 */
	@Override
	public boolean returnTic(String trainNum,String id) {
		
		//通过数据层工厂类获得对象。调用退票方法
		IUserOrderDao iuOrder=DAOFactory.getIUserOrderDaoInstance(conn);
		boolean flag=false;
		try {
			//判断是否退票成功
			if(iuOrder.doReturn(trainNum, id)){
				//成功，则从订单中删除此票
				iuOrder.doDelete(trainNum, id);
				flag=true;
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	try {
		conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
		return flag;
	}

	
	/**
	 *这是管理员进行查询订单操作
	 * @return 查询的订单集合，如果没有订单，则集合的size=0
	 */
	@Override
	public List<UserOrder> findManageAllOrder() {
		//通过数据层工厂类取得数据订购表操作实例对象
		IUserOrderDao iuseOr=DAOFactory.getIUserOrderDaoInstance(conn);
		List<UserOrder> orderList=new ArrayList<UserOrder>();
		//查询所有订单信息然后返回集合
		orderList=iuseOr.findManageOrder();
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
		
	}

}
