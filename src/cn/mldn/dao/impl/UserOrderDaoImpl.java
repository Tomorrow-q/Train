package cn.mldn.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.mldn.dao.IUserOrderDao;
import cn.mldn.pojo.UserOrder;
import cn.mldn.pojo.Userd;
/**
 * �������ݲ�IUserOrderDao��ʵ����
 * ����û���Ʊ�����Ĳ�ѯ����Ʊ����Ʊ��һϵ�в�����ʵ��
 * @author ASUS
 *
 */
public class UserOrderDaoImpl implements IUserOrderDao {
	private Connection conn=null;//�������ݿ����Ӷ���
	private PreparedStatement pstmt;//����Ԥ�������
	private UserOrder useOrder;
	public UserOrderDaoImpl(Connection conn){
		super();
		this.conn=conn;
	}
	
	/**
	 * �����û���ѯ��Ʊ�����Ĺ���
	 * @param IDCard �û����֤��
	 * @return ��ѯ�ĳ�Ʊ�����ļ���
	 * @throws SQLException 
	 */
	@Override
	public List<UserOrder> findMyAllTicket(String IDCard) throws SQLException {
		//����List���ϴ洢�ѹ���ĳ���
		List<UserOrder> ownList=new ArrayList<UserOrder>();

		String sql="select trainNumber.trainNum,passengerName,IDCard,startPlace,destination "
				+ "from trainNumber join userOrder "
				+ "on (trainNumber.trainNum = userOrder.trainNum and userOrder.IDCard=?)";
	
		//����Ԥ�������
		this.pstmt=this.conn.prepareStatement(sql);
		this.pstmt.setString(1, IDCard);
		
		//��ý����
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			//�����������󣬴洢�û��Լ��Ķ�����Ϣ
			useOrder=new UserOrder();
			useOrder.setStartPlace(rs.getString("startPlace"));
			useOrder.setPassengName(rs.getString("passengerName"));
			useOrder.setTrainName(rs.getString("trainNum"));
			useOrder.setDestPlace(rs.getString("destination"));
			useOrder.setIDCard(rs.getString("IDCard"));
			ownList.add(useOrder);//����ѯ���ȫ�����뼯��
		}
		
		return ownList;//���ض�Ʊ��¼�Ľ������
	}

	
	
	/**
	 * �����û�������Ʊ���ܣ����ݲ�ѯ�ĳ��κ���Ʊ��
	 * @param trainNum ���κ�
	 * @param id �û����֤��
	 * @return �Ƿ���Ʊ�ɹ�
	 * @throws SQLException 
	 */
	@Override
	public boolean doReturn(String trainNum,String id) throws SQLException {
		
		boolean flag=false;
		String sql="select trainNum,remainTicket from trainNumber where trainNum in "
				+ "(select trainNum from userOrder where trainNum=? and IDCard=?)";

		//����Ԥ�������
		this.pstmt=this.conn.prepareStatement(sql);
		this.pstmt.setString(1, trainNum);
		this.pstmt.setString(2, id);
		//��ò�ѯ�Ľ����
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			//���� ���α����޸ĸó��ε�Ʊ������Ʊ��Ʊ��+1
				String sql1="Update TrainNumber set remainTicket= replace(remainTicket,?,?) where trainNum = ?";
				PreparedStatement psmt=this.conn.prepareStatement(sql1);
				//��Ʊ��ԭ��Ʊ��Ʊ������
				Integer ir=rs.getInt(2)+1;
				psmt.setInt(1, rs.getInt(2));
				psmt.setInt(2, ir);
				psmt.setString(3, rs.getString(1));
				psmt.executeUpdate();
				flag=true;
				
		break;
		}

		return flag;
	}
	
	
	

	/**
	 * �����û�������Ʊ�Ĺ���
	 * @param trainNum ���κ�
	 * @param user ����Ʊ�û�����
	 * @return �Ƿ���ɹ����ɹ�����true�����򷵻�false
	 * @throws SQLException
	 */
	@Override
	public boolean doBuyTicket(Userd user,String trainNum) throws SQLException {
		
		//������䣬����Ʊ��Ϣ��ӵ���Ʊ����
		String sql="insert into userOrder(trainNum,passengerName, IDCard) values(?,?,?)";
		this.pstmt=this.conn.prepareStatement(sql);
		this.pstmt.setString(1, trainNum);
		this.pstmt.setString(2, user.getPasseName());
		this.pstmt.setString(3, user.getIDCard());
		if(this.pstmt.executeUpdate()>0){
			return true;
		}
		
		return false;
	}
	
	/**
	 * �Ӷ�����¼ɾ���û��Լ��Ķ���
	 * @param trainNum ���κ�
	 * @param id  ���֤��
	 * @return ɾ���Ƿ�ɹ���ɾ���ɹ�����true������false
	 * @throws SQLException
	 */
	public boolean doDelete(String trainNum,String id) throws SQLException {
		String sql="delete from userOrder where trainNum=? and IDCard=?";
		PreparedStatement psmt=this.conn.prepareStatement(sql);
		//����Ԥ�������
		psmt=this.conn.prepareStatement(sql);
		psmt.setString(1, trainNum);
		psmt.setString(2, id);
		psmt.executeUpdate();
		return true;
	}
	
	
	/***
	 * ���ǹ���Ա�����ж����Ĳ�ѯ����
	 * @return ���ж�����Ϣ�ļ���
	 */
	@Override
	public List<UserOrder> findManageOrder() {
		//String sql="select * from userOrder";,startPlace,destination,passengerName,IDCard
		String sql="select userOrder.trainNum,startPlace,destination,passengerName,IDCard"
				+ "  from userOrder,trainNumber"
				+ "  where trainNumber.trainNum = userOrder.trainNum";
 		
		List<UserOrder> list=new ArrayList<UserOrder>();
		//��ȡԤ�������
		try {
			this.pstmt=this.conn.prepareStatement(sql);
			//��ȡ��ѯ�����
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				useOrder=new UserOrder();
				useOrder.setTrainName(rs.getString(1));
				useOrder.setStartPlace(rs.getString(2));
				useOrder.setDestPlace(rs.getString(3));
				useOrder.setPassengName(rs.getString(4));
				useOrder.setIDCard(rs.getString(5));
				list.add(useOrder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
