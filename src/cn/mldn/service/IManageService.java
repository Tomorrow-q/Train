package cn.mldn.service;

/**
 * 这是管理员业务层操作标准
 * @author ASUS
 *
 */
public interface IManageService {
	/**
	 * 管理员进行登录
	 * @param username 管理员账号
	 * @param password 密码
	 * @return 
	 */
	public  boolean ManageLogin(String username,String password);
}
	
