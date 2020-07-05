package cn.mldn.service.impl;

import java.io.IOException;
import java.sql.SQLException;

import cn.mldn.dao.IUserdDao;
import cn.mldn.factory.DAOFactory;
import cn.mldn.pojo.Userd;
import cn.mldn.service.IUserdService;

public class UserdServiceImpl implements IUserdService {

	/**
	 * 这是对用户信息进行登录的功能
	 * @param username 用户账号
	 * @param password 账户密码
	 * @param user 用户对象，用来存储传递正进入系统的用户
	 * 调用DAO层的登录方法
	 * @return 是否登录成功，成功则为true，否则false
	 */
	@Override
	public boolean login(String username, String password,Userd user) {
		IUserdDao ud=DAOFactory.getIUserdDaoInstance();
		if(ud.isLogion(username, password,user)){
			return true;
		}
		return false;
	}

	/**
	 * 这是对用户信息进行注册的功能
	 * @param user 需要注册保存用户对象
	 * findUser为查询该注册对象是否已存在，如果不存在则调用DoAdd方法注册对象
	 * @return 是否注册成功，成功则为true，否则false
	 * @throws IOException 
	 */
	@Override
	public boolean register(Userd userd) throws IOException {
		IUserdDao ud=DAOFactory.getIUserdDaoInstance();
		if(!ud.findUser(userd)){//查询注册的账户是否存在
			ud.DoAdd(userd); //存在则添加进去
			return true;
		}
		return false;
	}


	/**
	 * 这是对用户密码变更的功能
	 * @param user 需要变更信息的用户对象
	 * 调用DAO层changeMessage方法
	 * @return 是否更改成功，成功则为true，否则false
	 */
	@Override
	public boolean changeMessage(Userd userd) {
		IUserdDao iud=DAOFactory.getIUserdDaoInstance();
			if(iud.changeMessage(userd)){
			return true;
		}
		
		
		return false;
	}

	/**
	 * 这是对用户密码变更时进行原密码判断的功能
	 * @param user 需要变更信息的用户对象
	 * @param password 输入的新密码
	 * 调用DAO层isEqual方法
	 * @return 是否相同，相同则为true，否则false
	 */
	@Override
	public boolean isEqual(Userd user, String password) {
		IUserdDao iud=DAOFactory.getIUserdDaoInstance();
		if(iud.isEqual(user, password)){
				return true;
		}
		return false;
	}

	/**
	 * 这是对用户账户注销的功能
	 * @param user 需要注销的用户对象
	 * 调用DAO层closePerson方法
	 * @return 是否更注销成功，成功则为true，否则false
	 */
	@Override
	public boolean closeAccount(Userd user) {
		//获取数据层用户操作标准实例对象
		IUserdDao iuse=DAOFactory.getIUserdDaoInstance();
		try {
			if(iuse.closePerson(user)){
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}


}
