package cn.mldn.dao;
/**
 * 这是管理员登录的DAO
 * @author ASUS
 *
 */
public interface IManageDao {
	/**
	 * 这是管理员登录登录功能
	 * @param username  用户名
	 * @param password  用户密码
	 * @return  是否登录成功，登录成功返回true，否则为false
	 */
	public abstract boolean ManageLogion(String username,String password);
}
