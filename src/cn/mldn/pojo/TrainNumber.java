package cn.mldn.pojo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * ���ǳ�����Ϣ��VO��
 * @author ASUS
 *
 */
@SuppressWarnings("serial")
public class TrainNumber implements Serializable {
	private String trainNum;  //���κ�
	private String startPlace;  //������
	private String destination;  //Ŀ�ĵ�
	private String goTime;   //����ʱ��
	private Integer remainTicket;  //ʣ��Ʊ��
	private String price;   //Ʊ��
	
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
