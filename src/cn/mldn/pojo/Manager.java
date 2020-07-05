package cn.mldn.pojo;
/**
 * 这是管理员信息的VO
 * @author ASUS
 *
 */
public class Manager {
	//管理员账户名
	private String manageName;
	//管理员账户密码
	private String password;
	public Manager() {
		super();
	}
	
	
	
	public Manager(String manageName, String password) {
		super();
		this.manageName = manageName;
		this.password = password;
	}



	public String getManageName() {
		return manageName;
	}
	
	public void setManageName(String manageName) {
		this.manageName = manageName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
