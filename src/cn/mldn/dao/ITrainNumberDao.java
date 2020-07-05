package cn.mldn.dao;

import java.sql.SQLException;
import java.util.List;

import cn.mldn.pojo.TrainNumber;
import cn.mldn.pojo.UserOrder;

/**
 * 这是针对火车票进行增删查改的操作的接口
 * @author ASUS
 *
 */
public interface ITrainNumberDao {
	
	/**
	 * 这是对火车车次信息增加的功能
	 * @param trainNum 欲增加的车次号
	 * @param startPlace 始发地
	 * @param dest 目的地
	 * @param goTime 出发时间
	 * @param remainTicket 车票数
	 * @param price  价格
	 * @return  是否添加成功，成功为ture，否则返回false
	 */
	//public boolean  addTicket(String trainNum,String startPlace,String dest,String goTime,Integer remainTicket,String price) throws SQLException;
	public boolean  addTicket(TrainNumber trainNumber) throws SQLException;
	
	/**
	 * 这是用户对火车车次信息查找的功能
	 * @param startPlace  始发地
	 * @param destPlace   目的地
	 * @return 查询的车次的集合，如果没有该车次，则list集合的size为0
	 */
	public List<TrainNumber> findUserTicket(String startPlace,String destPlace) throws SQLException;
	
	/**
	 * 这是管理员对火车车次删除的操作
	 * @param trainNum 车次号
	 * @param goTime  出发时间
	 * @return 是否删除成功，成功为true
	 */
	public boolean deleteTicket(String trainNum) throws SQLException;
	
	/**
	 * 这是管理员对车次信息进行修改的功能
	 * @param trainNum  车次号
	 * @param attribute 要修改的属性
	 * @param content   修改后的内容
	 * @return 是否修改成功，成功为true
	 */
	public boolean updateTicket(String trainNum,String attribute,String content) throws SQLException;
	
	/**
	 * 这是管理员查询所有车次信息的功能
	 * @return 查询的所有车次信息的集合
	 */
	public List<TrainNumber> findAllTicket(int num) throws SQLException;
	
	/**
	 * 这是判断管理员输入的车次信息是否已在数据库中存在
	 * @param trainNum 车次
	 * @param startPlace 始发地
	 * @param dest  目的地
	 * @param goTime 出发时间 
	 * @param retick 票数
	 * @return 0为票数增加，即有相同的车次；1是有相同车次号，不可重复主键值，不可增加；2可增加数据
	 * @throws SQLException
	 */
	public int isExist(TrainNumber train ) throws SQLException;
	
	public List<TrainNumber> sort(int num);
	
	//public List<UserOrder> findOrder()  throws SQLException;
}
