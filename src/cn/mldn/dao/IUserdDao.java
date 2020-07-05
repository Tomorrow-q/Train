package cn.mldn.dao;

import java.io.IOException;
import java.util.List;

import cn.mldn.pojo.Userd;


/**
 * 这是普通用户注册登录的DAO
 * @author ASUS
 *
 */
public interface IUserdDao {
	/**
	 * 这是用户登录功能
	 * @param username  用户名
	 * @param password  用户密码
	 * @param user 传入对象，将登录成功的用户信息存储然后传给主界面 
	 * @return  是否登录成功
	 */
	public abstract boolean isLogion(String username,String password,Userd user) ;
	
	/**
	 * 这是用户注册功能
	 * @param user  用户对象
	 * @return  是否注册成功
	 */
	//public abstract boolean register(Userd userd) throws IOException ;
	
	/**
	 * 这是用户修改自己的账户信息的功能
	 * @return 更改信息是否成功
	 */
	public boolean changeMessage(Userd user);
	
	/**
	 * 这是用户信息判断，在修改密码时，判断用户的信息
	 * @param user 用户对象
	 * @param password 密码
	 * @return  相同返回true，否则返回false
	 */
	public boolean isEqual(Userd user,String password);
	
	
	/**
	 * 注销个人账户
	 * @param user 用户对象
	 * @return 是否注销成功，注销成功返回true
	 * @throws IOException 
	 */
	public boolean closePerson(Userd user) throws IOException;
	
	/**
	 * 这是查询正在注册的用户是否存在
	 * @param user 要注册的账户对象
	 * @return 存在返回true，不存在返回false
	 */
	public boolean findUser(Userd user);
	
	/**
	 * 这是将注册的用户存入文本文件
	 * @param user 要存入的用户对象
	 * @return 存入成功返回true，否则返回false
	 */
	public boolean DoAdd(Userd user);
	
	/**
	 * 这是用户信息更改后重新写入文件的功能
	 * @param list 存储用户对象集合
	 * @return  是否更改成功，成功为true，否则为false
	 */
	public boolean docreat(List list);
}
