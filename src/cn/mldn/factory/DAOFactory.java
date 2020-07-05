package cn.mldn.factory;

import java.sql.Connection;

import cn.mldn.dao.IManageDao;
import cn.mldn.dao.ITrainNumberDao;
import cn.mldn.dao.IUserOrderDao;
import cn.mldn.dao.IUserdDao;
import cn.mldn.dao.impl.ManageDaoImpl;
import cn.mldn.dao.impl.TrainNumberDaoImpl;
import cn.mldn.dao.impl.UserOrderDaoImpl;
import cn.mldn.dao.impl.UserdDaoImpl;

/**
 * 通过此类返回DAO的对象（工厂方法模式）
 * @author ASUS
 *
 */
public class DAOFactory {
	/**
	 * 乘客实例对象
	 * @return 实例对象
	 */
	public static IUserdDao getIUserdDaoInstance(){
		return new UserdDaoImpl();
	}
	
	/**
	 * 管理员实例对象
	 * @return 实例对象
	 */
	public static IManageDao getIManageDaoInstance(){
		return new ManageDaoImpl();
	}
	
	/**
	 * 乘客车票订单实例对象
	 * @param conn 数据库连接对象
	 * @return 实例对象
	 */
	public static IUserOrderDao getIUserOrderDaoInstance(Connection conn){
		return new UserOrderDaoImpl(conn);
	}
	
	/**
	 * 对于车票进行操作的实例对象
	 * @param conn 数据库连接对象
	 * @return TrainNumberDao接口对象
	 */
	public static ITrainNumberDao getITrainNumberDaoInstance(Connection conn){
		return new TrainNumberDaoImpl(conn);
	}
}
