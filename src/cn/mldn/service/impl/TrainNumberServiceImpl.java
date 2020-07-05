package cn.mldn.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.mldn.dao.ITrainNumberDao;
import cn.mldn.dao.IUserOrderDao;
import cn.mldn.dbc.DataBaseConnection;
import cn.mldn.factory.DAOFactory;
import cn.mldn.pojo.TrainNumber;
import cn.mldn.pojo.UserOrder;
import cn.mldn.service.ITrainNumberService;
/**
 * 这是对业务层标准的实现类（子类）
 * 该类要负责对数据库的打开和关闭
 * 
 * @author ASUS
 *
 */
public class TrainNumberServiceImpl implements ITrainNumberService {
	 //取得数据库连接
	private Connection conn=new DataBaseConnection().getConnection();


	/**
	 * 实现车次信息的添加操作
	 * 需要调用ITrainNumberDao的isExist()方法，判断增加的车次信息是否和数据库中有相同信息，相同则票数增加
	 * addTicket()方法，增加的车次信息不存在且车次号不重复，则直接调用此方法
	 * @param train 车次实体对象
	 * @return 是否添加成功
	 * @throws SQLException 
	 */
	
//	public boolean insert(String trainNum, String startPlace, String dest, String goTime, Integer remainTicket,
//			String price) throws Exception {
	@Override
	public boolean insert(TrainNumber train) throws SQLException{
		//利用工厂方法模式获得ITrainNumberDao实例对象
		ITrainNumberDao railway=DAOFactory.getITrainNumberDaoInstance(conn);
		int flag=0;
		try {
			//flag=railway.isExist(trainNum, startPlace, dest, goTime, remainTicket, price);
			flag=railway.isExist(train);
			if(flag==2){
				railway.addTicket(train);
				return true;
			}else if(flag==0){
				return true;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}finally{
			//关闭数据库连接
			conn.close();
		}
		
			return false;
		
	}


	/**
	 * 管理员实现车次信息删除操作
	 * @param trainNum 车次号
	 * @return 是否删除成功
	 * @throws SQLException 
	 */
	@Override
	public boolean delete(String trainNum) throws SQLException{
		//通过数据层的工厂类获得数据层车次表操作的实例对象，用于调用数据层方法
		ITrainNumberDao Itrain =DAOFactory.getITrainNumberDaoInstance(conn);
		boolean flag=false;
		flag=Itrain.deleteTicket(trainNum);
		this.conn.close(); //关闭数据库连接
		return flag;
		
		
	}

	
	/**
	 * 实现用户查询车次信息，根据输入的始发地和目的
	 * @param startPlace 始发地
	 * @param destPlace  目的地
	 * @return 查询的结果以集合形式返回
	 * @throws SQLException 
	 */
	@Override
	public List<TrainNumber> selectUser(String startPlace, String destPlace) throws SQLException {
		//利用工厂方法模式获得ITrainNumberDao实例对象
		ITrainNumberDao railway=DAOFactory.getITrainNumberDaoInstance(conn);
		List<TrainNumber> list=new ArrayList<TrainNumber>();
		//查询用户所查找的车次
		list=railway.findUserTicket(startPlace, destPlace);
		conn.close(); //关闭数据库连接
		return list;
	}

	
	/**
	 * 管理员查看所有车次信息
	 * @return 所有车此信息以集合形式返回，若没车次信息，则集合的size为0
	 * @throws SQLException 
	 */
	@Override
	public List<TrainNumber> selectAll(int num) throws SQLException{
		ITrainNumberDao itrainDao=DAOFactory.getITrainNumberDaoInstance(conn);
		List<TrainNumber> trainList=new ArrayList<TrainNumber>();
		trainList=itrainDao.findAllTicket(num);
		conn.close();
		return trainList;
	}

//	@Override
//	public List<UserOrder> selectOrder() throws SQLException{
//		//通过工厂模式获取数据层访问对象
//		IUserOrderDao iuseOrder=DAOFactory.getIUserOrderDaoInstance(conn);
//		List<UserOrder> list=new ArrayList<UserOrder>();
//		list=iuseOrder.findManageOrder();
//		return list;
//		return null;
//	}

	/**
	 * 实现管理员修改车次信息
	 * @param trainNum 需要修改的车次号
	 * @param attribute  需要修改的属性如：始发地、目的地、价格、票数、价格
	 * @param content  修改后的内容
	 * @return 是否修改成功
	 * @throws SQLException 
	 */
	@Override
	public boolean Update(String trainNum, String attribute, String content) throws SQLException{
		boolean flag=false;
		ITrainNumberDao itrainDao=DAOFactory.getITrainNumberDaoInstance(conn);
		if(itrainDao.updateTicket(trainNum, attribute, content)){
			flag=true;
		}
		this.conn.close();
		return flag;
	}


	@Override
	public List<TrainNumber> sort(int num) {
		ITrainNumberDao train=DAOFactory.getITrainNumberDaoInstance(conn);
		List<TrainNumber> list=train.sort(num);
		
		return list;
	}

}
