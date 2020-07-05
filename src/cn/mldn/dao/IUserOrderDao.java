package cn.mldn.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import cn.mldn.pojo.UserOrder;
import cn.mldn.pojo.Userd;

/**
 * 这是用户订票信息Dao类，定义查看车票、退票、以及删除等功能的接口
 * @author ASUS
 *
 */
public interface IUserOrderDao {
	
	/**
	 * 这是用户查询车票订单的功能
	 * @param IDCard 用户身份证号
	 * @return 查询的车票订单的集合
	 * @throws SQLException 
	 */
	public List<UserOrder> findMyAllTicket(String IDCard) throws SQLException;
	
	/**
	 * 这是用户进行退票功能（根据查询的车次号退票）
	 * @param trainNum 车次号
	 * @param id 身份证号
	 * @return 是否退票成功
	 * @throws SQLException 
	 */
	public boolean doReturn(String trainNum,String id) throws SQLException;
	
	/**
	 * 这是用户进行买票的功能
	 * @param trainNum 车次号
	 * @return 是否购买成功，成功返回true，否则返回false
	 * @throws SQLException
	 */
	public boolean doBuyTicket(Userd user,String trainNum) throws SQLException;
	
	/**
	 * 从订单记录删除用户自己的订单
	 * @param trainNum 车次号
	 * @param id  身份证号
	 * @return 删除是否成功。删除成功返回true，否则false
	 * @throws SQLException
	 */
	public boolean doDelete(String trainNum,String id) throws SQLException;
	
	/***
	 * 这是管理员对所有订单的查询操作
	 * @return 所有订单信息的集合
	 */
	public List<UserOrder> findManageOrder();
}
