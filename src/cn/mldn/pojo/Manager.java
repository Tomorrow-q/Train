package cn.mldn.pojo;
/**
 * ���ǹ���Ա��Ϣ��VO
 * @author ASUS
 *
 */
public class Manager {
	//����Ա�˻���
	private String manageName;
	//����Ա�˻�����
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
