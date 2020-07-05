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
 * ҵ��㹤���࣬����������ζ�ҵ�����е���
 * @author ASUS
 *
 */
public class ServiceFactory {
	/**
	 * ȡ��ҵ��㳵����Ϣ��ʵ��
	 * @return  ҵ��㳵�ε�ʵ������
	 */
	public static ITrainNumberService getITrainNumberServiceInstance(){
		return new TrainNumberServiceImpl();
	}
	
	/**
	 * ȡ��ҵ����û���Ϣ���ʵ��
	 * @return �û���Ϣ���ʵ������
	 */
	public static IUserdService getIUserdServiceInstance(){
		return new UserdServiceImpl();
	}
	
	/**
	 * ȡ��ҵ����û��������ʵ��
	 * @return �û��������ʵ������
	 */
	public static IUserOrderService getIUserOrderServiceInstance(){
		return new UserOrderServiceImpl();
	}
	
	/**
	 * ȡ��ҵ������Ա���ʵ��
	 * @return ����Ա��ҵ������ʵ��
	 */
	public static IManageService getIManageServiceInstance(){
		return new ManageServiceImpl();
	}
}
