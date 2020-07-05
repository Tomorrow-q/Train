package cn.mldn.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cn.mldn.dao.ITrainNumberDao;
import cn.mldn.pojo.TrainNumber;

/**
 * ���ǶԳ�����Ϣ������ITrainNumberDao����ʵ����
 * @author ASUS
 *
 */
public class TrainNumberDaoImpl implements ITrainNumberDao {
	private Connection conn=null;//�������ݿ����Ӷ���,��Ҫ����Connection�������
	private PreparedStatement pstmt;//����Ԥ�������
	
	/**
	 * ������ҵ���Ҫ�������ݲ㣬���ݿ�Ĵ���رս���ҵ��㴦��
	 * @param conn ���ݿ����Ӷ���
	 */
	public TrainNumberDaoImpl(Connection conn){
		super();
		this.conn=conn;//ȡ�����ݿ�����
	}
	
	/**
	 * ���ǶԻ𳵳�����Ϣ���ӵĹ���
	 * @param train �����ӵĳ��ζ���
	 * @return  �Ƿ���ӳɹ����ɹ�Ϊture�����򷵻�false
	 * @throws SQLException 
	 */
	@Override
//	public boolean addTicket(String trainNum, String startPlace, String dest, String goTime, Integer remainTicket,
//			String price) throws SQLException {
		public boolean addTicket(TrainNumber train) throws SQLException {

		//���������sql���
		String sql=" insert into trainNumber values(?,?,?,?,?,?)";
		//����Ԥ�������
		this.pstmt=this.conn.prepareStatement(sql);
		//��������Ϣ������ӵ�����
		this.pstmt.setString(1, train.getTrainNum());
		this.pstmt.setString(2,train.getStartPlace());
		this.pstmt.setString(3,train.getDestination());
		this.pstmt.setString(4,train.getGoTime());
		this.pstmt.setInt(5, train.getRemainTicket());
		this.pstmt.setString(6,train.getPrice());
		if(pstmt.executeUpdate()>0){
			return true;
		}
		
		return false;
	}

	
	/**
	 * �����û��Ի𳵳�����Ϣ���ҵĹ���
	 * @param startPlace  ʼ����
	 * @param destPlace   Ŀ�ĵ�
	 * @return ��ѯ�ĳ��εļ��ϣ����û�иó��Σ���list���ϵ�sizeΪ0
	 * @throws SQLException 
	 */
	@Override
	public List<TrainNumber> findUserTicket(String startPlace, String destPlace) throws SQLException {
		//�������ϴ����û���ѯ�ĳ�����Ϣ
		List<TrainNumber> messList=new ArrayList<TrainNumber>();
		//��ѯsql��䣺��trainNumber���в����û������ʼ���غ�Ŀ�ĵ�
		String sql="select * from trainNumber where startPlace = ? and destination = ?";
		
		//��ȡԤ�������
		this.pstmt=this.conn.prepareStatement(sql);
		this.pstmt.setString(1, startPlace);
		this.pstmt.setString(2, destPlace);
		//��ȡ�����
		ResultSet rs=this.pstmt.executeQuery();
		while(rs.next()){
			TrainNumber tn=new TrainNumber();
			tn.setTrainNum(rs.getString(1));
			tn.setStartPlace(rs.getString(2));
			tn.setDestination(rs.getString(3));
			tn.setGoTime(rs.getString(4));
			tn.setRemainTicket(rs.getInt(5));
			tn.setPrice(rs.getString(6));
			//����ѯ����ÿ�����ݼ��뼯��
			messList.add(tn);
			
		}
		
		return messList;
	}

	
	/**
	 * ���ǹ���Ա�Ի𳵳���ɾ���Ĳ���
	 * @param trainNum ���κ�
	 * @return �Ƿ�ɾ���ɹ����ɹ�Ϊtrue
	 * @throws SQLException 
	 */
	@Override
	public boolean deleteTicket(String trainNum) throws SQLException {
		//��ѯsql���
		String sql="delete from trainNumber where trainNum ='"+trainNum+"'";
		//��ȡԤ�������
		this.pstmt=this.conn.prepareStatement(sql);
		int count =pstmt.executeUpdate();
		if(count>0){
			return true;
		}
		return false;
	}

	
	/**
	 * ���ǹ���Ա�Գ�����Ϣ�����޸ĵĹ���
	 * @param trainNum  ���κ�
	 * @param attribute Ҫ�޸ĵ�����
	 * @param content   �޸ĺ������
	 * @return �Ƿ��޸ĳɹ����ɹ�Ϊtrue
	 * @throws SQLException 
	 */
	@Override
	public boolean updateTicket(String trainNum, String attribute, String content) throws SQLException {
		//��ѯsql���,��TrainNumber ���г��κ�ΪtrainNum������Ϊattribute�޸�Ϊcontent
		String sql="Update TrainNumber set "+attribute+" =? where  trainNum=? ";
		//��ȡԤ�������
		this.pstmt=this.conn.prepareStatement(sql);
		int attribContent;
		if(attribute.equals("remainTicket")){
			attribContent=Integer.parseInt(content);
			pstmt.setInt(1, attribContent);
			pstmt.setString(2, trainNum);
		}else{
			pstmt.setString(1, content);
			pstmt.setString(2, trainNum);
		}
		
		if(pstmt.executeUpdate()>0){
			return true;
		}

		
		return false;
	}

	
	
	/**
	 * ���ǹ���Ա��ѯ���г�����Ϣ�Ĺ���
	 * @return ��ѯ�����г�����Ϣ�ļ���
	 * @throws SQLException 
	 */
	@Override
	public List<TrainNumber> findAllTicket(int num) throws SQLException {
		
		List<TrainNumber> trainList =new ArrayList<TrainNumber>();
		String sql="select * from trainNumber";
		this.pstmt=this.conn.prepareStatement(sql);
		//��ò�ѯ�����
		ResultSet rs=this.pstmt.executeQuery();
		while(rs.next()){
			TrainNumber tn=new TrainNumber();
			tn.setTrainNum(rs.getString("trainNum"));
			tn.setStartPlace(rs.getString("startPlace"));
			tn.setDestination(rs.getString("destination"));
			tn.setRemainTicket(rs.getInt("remainTicket"));
			tn.setGoTime(rs.getString("goTime"));
			tn.setPrice(rs.getString("price"));
			//����ѯ�Ľ�����뼯��
			trainList.add(tn);
		}
		switch(num){
		
		
	case 1://����
		Collections.sort(trainList, new Comparator<TrainNumber>() {
			public int compare(TrainNumber t1,TrainNumber t2) {
				return (Integer.parseInt(t1.getPrice())-Integer.parseInt(t2.getPrice()));
			}
		});
		break;
		
	case 2://����
		Collections.sort(trainList, new Comparator<TrainNumber>() {
			public int compare(TrainNumber t1,TrainNumber t2) {
				return  (Integer.parseInt(t2.getPrice())-Integer.parseInt(t1.getPrice()));
			}
		});
		}
		return trainList;
	}


	/**
	 * �����жϹ���Ա����ĳ�����Ϣ�Ƿ��������ݿ��д���
	 * @param train ���ζ���
	 * @return 0ΪƱ�����ӣ�������ͬ�ĳ��Σ�1������ͬ���κţ������ظ�����ֵ���������ӣ�2�����Ӹó���
	 * @throws SQLException
	 */
	@Override
//	public int isExist(String trainNum, String startPlace, String dest, String goTime,Integer retick,String price ) throws SQLException {
	public int isExist(TrainNumber train) throws SQLException {	
	//��ѯ����ĳ����Ƿ���ڣ�������ֻ��Ʊ������
		//��ѯ��sql��䣺��trainNumber���в��ҳ��κ�ΪtrainNum�ĳ��εĳ��κš�ʼ���ء�Ŀ�ĵء�ʣ�೵Ʊ�ͳ���ʱ��
		String psql="select startPlace,destination,remainTicket, goTime,price from trainNumber where trainNum = ?";
		this.pstmt=this.conn.prepareStatement(psql);
		pstmt.setString(1, train.getTrainNum());
		ResultSet rs=pstmt.executeQuery();
		int flag=2;
		//�жϽ�����Ƿ�Ϊ�գ���Ϊ�����ʾ��ѯ�������ݣ� ����ͬ�ĳ��κ���Ϣ����ֱ�����Ӹó���Ʊ��
		if(rs!=null){
			while(rs.next()){
				String num=rs.getString(1);
					String start=rs.getString("startPlace");
					String desti=rs.getString("destination");
					String time=rs.getString("goTime");
					//��ȡԭ��Ʊ
					Integer remain=rs.getInt("remainTicket");
					String pric=rs.getString("price");
					if(train.getTrainNum().equals(num)&&train.getStartPlace().equals(start)&&train.getDestination().equals(desti)&&train.getGoTime().equals(time)&&train.getPrice().equals(pric)){
						remain+=train.getRemainTicket();
						pstmt.setInt(5, remain);
						//����true��ʾ����ĳ�����Ϣ�Ѵ������ݿ��У�ֻ��Ʊ������
						flag=0;//������Ʊ��
							break;
					}else if(flag!=0&&train.getTrainNum().equals(num)){
						flag=1;
					}
						
			}
			
		}
		//0ΪƱ�����ӣ�1Ϊ�Ѵ��ڳ��κŲ������ӣ�2��û�иó���,������
		return flag;
		
	}

	@Override
	public List<TrainNumber> sort(int num) {
		List<TrainNumber> trainList=null;
		try {
			trainList=findAllTicket(num);
			switch(num){
			case 1://����
				Collections.sort(trainList, new Comparator<TrainNumber>() {
					public int compare(TrainNumber t1,TrainNumber t2) {
						return t1.getTrainNum().compareTo(t2.getTrainNum());
					}
				});
				break;
				
			case 2://����
				Collections.sort(trainList, new Comparator<TrainNumber>() {
					public int compare(TrainNumber t1,TrainNumber t2) {
						return t2.getTrainNum().compareTo(t1.getTrainNum());
					}
				});
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return trainList;
	}


//	@Override
//	public List<UserOrder> findOrder() throws SQLException {
//		List<UserOrder> trainlist= new ArrayList<UserOrder>();
//		String sql="select trainNum,passengerName,IDCard from userOrder";
//		this.pstmt=this.conn.prepareStatement(sql);
//		ResultSet rs=this.pstmt.executeQuery();
//		while(rs.next()){
//			UserOrder uo=new UserOrder();
//			uo.setTrainName(rs.getString("trainNum"));
//			uo.setPassengName(rs.getString("passengerName"));
//			uo.setIDCard(rs.getString("IDCard"));
//			
//			//���뼯��
//			trainlist.add(uo);
//		}
//		
//		return trainlist;
//	}


	
}
