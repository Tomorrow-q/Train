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
 * ͨ�����෵��DAO�Ķ��󣨹�������ģʽ��
 * @author ASUS
 *
 */
public class DAOFactory {
	/**
	 * �˿�ʵ������
	 * @return ʵ������
	 */
	public static IUserdDao getIUserdDaoInstance(){
		return new UserdDaoImpl();
	}
	
	/**
	 * ����Աʵ������
	 * @return ʵ������
	 */
	public static IManageDao getIManageDaoInstance(){
		return new ManageDaoImpl();
	}
	
	/**
	 * �˿ͳ�Ʊ����ʵ������
	 * @param conn ���ݿ����Ӷ���
	 * @return ʵ������
	 */
	public static IUserOrderDao getIUserOrderDaoInstance(Connection conn){
		return new UserOrderDaoImpl(conn);
	}
	
	/**
	 * ���ڳ�Ʊ���в�����ʵ������
	 * @param conn ���ݿ����Ӷ���
	 * @return TrainNumberDao�ӿڶ���
	 */
	public static ITrainNumberDao getITrainNumberDaoInstance(Connection conn){
		return new TrainNumberDaoImpl(conn);
	}
}
