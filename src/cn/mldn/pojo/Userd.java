package cn.mldn.pojo;

import java.io.Serializable;

/**
 * 这是用户信息的简单java类（pojo）
 * 该类实现序列化接口
 * @author ASUS
 *
 */
@SuppressWarnings("serial")
public class Userd implements Serializable{
	private String username;  //用户名
	private String password;  //密码
	private String passeName; //乘客姓名
	private String sex;  //性别
	private String IDCard; //身份证号
	
	public Userd() {
		super();
	}

	public Userd(String username, String password, String passeName, String sex, String iDCard) {
		super();
		this.username = username;
		this.password = password;
		this.passeName = passeName;
		this.sex = sex;
		IDCard = iDCard;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasseName() {
		return passeName;
	}

	public void setPasseName(String passeName) {
		this.passeName = passeName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIDCard() {
		return IDCard;
	}

	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}
	
	
}
