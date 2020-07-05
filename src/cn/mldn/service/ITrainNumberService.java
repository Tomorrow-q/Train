package cn.mldn.service;

import java.sql.SQLException;
import java.util.List;

import cn.mldn.pojo.TrainNumber;

/**
 * 这是定义trainNumber表的业务层执行标准
 * @author ASUS
 *
 */
public interface ITrainNumberService {
	
	/**
	 * 实现车次信息的添加操作
	 * 需要调用ITrainNumberDao的isExist()方法，判断增加的车次信息是否和数据库中有相同信息，相同则票数增加
	 * addTicket()方法，增加的车次信息不存在且车次号不重复，则直接调用此方法
	 * @param train 车次实体对象
	 * @return 是否添加成功
	 * @throws Exception 
	 */
//	public boolean insert(String trainNum, String startPlace, String dest, String goTime, Integer remainTicket,
//			String price) throws SQLException, Exception; 
	public boolean insert(TrainNumber train) throws SQLException, Exception; 
	
	/**
	 * 管理员实现车次信息删除操作
	 * @param trainNum 车次号
	 * @return 是否删除成功
	 *  @throws SQLException 
	 */
	public boolean delete(String trainNum) throws SQLException;
	
	/**
	 * 实现用户查询车次信息，根据输入的始发地和目的
	 * @param startPlace 始发地
	 * @param destPlace  目的地
	 * @return 查询的结果以集合形式返回
	 *  @throws SQLException 
	 */
	public List<TrainNumber> selectUser(String startPlace, String destPlace) throws SQLException;
	
	/**
	 * 管理员查看所有车次信息
	 * @return 所有车此信息以集合形式返回，若没车次信息，则集合的size为0
	 * @throws SQLException 
	 */
	public List<TrainNumber> selectAll(int num) throws SQLException;
	
//	/**
//	 * 管理员查看所有的售票记录
//	 * @return 以集合的形式返回所有售票记录
//	 */
//	public List<TrainNumber> selectOrder() throws SQLException;
	
	/**
	 * 实现管理员修改车次信息
	 * @param trainNum 需要修改的车次号
	 * @param attribute  需要修改的属性如：始发地、目的地、价格、票数、价格
	 * @param content  修改后的内容
	 * @return 是否修改成功
	 * @throws SQLException 
	 */
	public boolean Update(String trainNum, String attribute, String content) throws SQLException;
	
	public List<TrainNumber> sort(int num);
	
}

