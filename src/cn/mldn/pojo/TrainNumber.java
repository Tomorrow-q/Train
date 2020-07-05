package cn.mldn.pojo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * 这是车次信息的VO类
 * @author ASUS
 *
 */
@SuppressWarnings("serial")
public class TrainNumber implements Serializable {
	private String trainNum;  //车次号
	private String startPlace;  //出发地
	private String destination;  //目的地
	private String goTime;   //出发时间
	private Integer remainTicket;  //剩余票数
	private String price;   //票价
	
	private List<TrainNumber> trains=new ArrayList<TrainNumber>();
	public TrainNumber() {
		super();
	}
	public TrainNumber(String trainNum, String startPlace, String destination, String goTime, Integer remainTicket,
 String price) {
		super();
		this.trainNum = trainNum;
		this.startPlace = startPlace;
		this.destination = destination;
		this.goTime = goTime;
		this.remainTicket = remainTicket;
		this.price = price;
	}
	
	public String getTrainNum() {
		return trainNum;
	}
	
	public void setTrainNum(String trainNum) {
		this.trainNum = trainNum;
	}
	
	public String getStartPlace() {
		return startPlace;
	}
	
	public void setStartPlace(String startPlace) {
		this.startPlace = startPlace;
	}

	public String getDestination() {
		return destination;
	}
	
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public String getGoTime() {
		return goTime;
	}
	
	public void setGoTime(String goTime) {
		this.goTime = goTime;
	}
	
	public Integer getRemainTicket() {
		return remainTicket;
	}
	
	public void setRemainTicket(Integer remainTicket) {
		this.remainTicket = remainTicket;
	}
	
	
	public String getPrice() {
		return price;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}
	
	
}
