package cn.mldn.pojo;

import java.io.Serializable;
/**
 * 这是用户订票信息(pojo类)
 * @author ASUS
 *
 */
@SuppressWarnings("serial")
public class UserOrder implements Serializable {
	private String TrainName;  //车次
	private String passengName; //乘客姓名
	private String IDCard;  //身份证号
	private String startPlace; //出发地
	private String destPlace;  //目的地
	
	public UserOrder() {
		super();
	}
	public UserOrder(String trainName, String passengName, String iDCard) {
		super();
		this.TrainName = trainName;
		this.passengName = passengName;
		this.IDCard = iDCard;
	}

	public String getTrainName() {
		return TrainName;
	}

	public void setTrainName(String trainName) {
		TrainName = trainName;
	}
	
	public String getPassengName() {
		return passengName;
	}
	
	public void setPassengName(String passengName) {
		this.passengName = passengName;
	}
	
	public String getIDCard() {
		return IDCard;
	}
	
	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}
	
	public String getStartPlace() {
		return startPlace;
	}
	
	public void setStartPlace(String startPlace) {
		this.startPlace = startPlace;
	}
	
	public String getDestPlace() {
		return destPlace;
	}
	
	public void setDestPlace(String destPlace) {
		this.destPlace = destPlace;
	}


	
}
