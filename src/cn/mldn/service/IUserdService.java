package cn.mldn.service;

import java.io.IOException;

import cn.mldn.pojo.Userd;

/**
 * 这是用户登录注册
 * @author ASUS
 *
 */
public interface IUserdService {
	/**
	 * 用户登录
	 * @param username  用户名
	 * @param password  密码
	 * @return  是否登录成功
	 */
	public boolean login(String username, String password,Userd user);

	/**
	 * 用户注册
	 * @param userd 用户对象
	 * @return 是否注册成功
	 * @throws IOException 
	 */
	public boolean register(Userd userd)  throws IOException;
	
	/**
	 * 这是修改用户信息的功能
	 * @param userd 用户对象
	 * @return 是否修改成功，修改成功返回ture，否则false
	 */
	public boolean changeMessage(Userd userd);
	
	/**
	 * 这是对用户信息判断
	 * @param user
	 * @param password
	 * @return 是否相等，相等返回true
	 */
	public boolean isEqual(Userd user,String password);
	
	/**
	 * 这是用户注销账户功能
	 * @param user 用户对象
	 * @return 是否注销成功
	 */
	public boolean closeAccount(Userd user);
}
