package cn.mldn.factory;

import cn.mldn.dao.IManageDao;
import cn.mldn.dao.IUserdDao;
import cn.mldn.dao.impl.ManageDaoImpl;
import cn.mldn.pojo.TrainNumber;
import cn.mldn.service.IManageService;
import cn.mldn.service.ITrainNumberService;
import cn.mldn.service.IUserOrderService;
import cn.mldn.service.IUserdService;
import cn.mldn.service.impl.ManageServiceImpl;
import cn.mldn.service.impl.TrainNumberServiceImpl;
import cn.mldn.service.impl.UserOrderServiceImpl;
import cn.mldn.service.impl.UserdServiceImpl;

/**
 * 业务层工厂类，方便其他层次对业务层进行调用
 * @author ASUS
 *
 */
public class ServiceFactory {
	/**
	 * 取得业务层车次信息表实例
	 * @return  业务层车次的实例对象
	 */
	public static ITrainNumberService getITrainNumberServiceInstance(){
		return new TrainNumberServiceImpl();
	}
	
	/**
	 * 取得业务层用户信息表的实例
	 * @return 用户信息表的实例对象
	 */
	public static IUserdService getIUserdServiceInstance(){
		return new UserdServiceImpl();
	}
	
	/**
	 * 取得业务层用户订购表的实例
	 * @return 用户订购表的实例对象
	 */
	public static IUserOrderService getIUserOrderServiceInstance(){
		return new UserOrderServiceImpl();
	}
	
	/**
	 * 取得业务层管理员表的实例
	 * @return 管理员的业务层对象实例
	 */
	public static IManageService getIManageServiceInstance(){
		return new ManageServiceImpl();
	}
}
