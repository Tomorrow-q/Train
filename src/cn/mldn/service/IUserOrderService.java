package cn.mldn.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import cn.mldn.pojo.TrainNumber;
import cn.mldn.pojo.UserOrder;
import cn.mldn.pojo.Userd;

/**
 * 这是普通用户操作的业务层标准
 * @author ASUS
 *
 */
public interface IUserOrderService {
	
	/**
	 * 这是用户买票功能
	 * @param user 用户对象 
	 * @param trainNum 车次号
	 * @return 是否购买成功，成功则为true
	 * @throws SQLException 
	 */
	public boolean buy(Userd user,String trainNum,Integer remain)  throws SQLException;
	
	/**
	 * 这是用户查询订单操作
	 * @param IDCard 身份证号
	 * @return 查询订单详情
	 * @throws SQLException 
	 */
	public List<UserOrder> findUseOrder(String IDCard)  throws SQLException;
	
	/**
	 * 这是用户退票
	 * @param trainNum 车次号
	 * @param id 身份证号
	 * @return 是否退票成功
	 * @throws SQLException 
	 */
	public boolean returnTic(String trainNum,String id)  throws SQLException;
	
	/**
	 *这是管理员进行查询订单操作
	 * @return 查询的订单集合，如果没有订单，则集合的size=0
	 */
	public List<UserOrder> findManageAllOrder();
	
}
