package cn.mldn.service.impl;

import cn.mldn.dao.IManageDao;
import cn.mldn.factory.DAOFactory;
import cn.mldn.service.IManageService;

/**
 * 这是管理员业务层实现类
 * @author ASUS
 *
 */
public class ManageServiceImpl implements IManageService {

	/**
	 * 这是管理员登录功能的实现
	 * @param username  用户名
	 * @param password  用户密码
	 * @return  是否登录成功，登录成功返回true，否则为false
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
