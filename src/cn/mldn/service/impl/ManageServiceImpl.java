package cn.mldn.service.impl;

import cn.mldn.dao.IManageDao;
import cn.mldn.factory.DAOFactory;
import cn.mldn.service.IManageService;

/**
 * ���ǹ���Աҵ���ʵ����
 * @author ASUS
 *
 */
public class ManageServiceImpl implements IManageService {

	/**
	 * ���ǹ���Ա��¼���ܵ�ʵ��
	 * @param username  �û���
	 * @param password  �û�����
	 * @return  �Ƿ��¼�ɹ�����¼�ɹ�����true������Ϊfalse
	 */
	@Override
	public boolean ManageLogin(String username, String password) {
		IManageDao ims=DAOFactory.getIManageDaoInstance();
		if(ims.ManageLogion(username, password)){
			return true;
		}
	
		return false;
	}

}
