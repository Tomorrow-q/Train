package cn.mldn.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import cn.mldn.dao.IUserOrderDao;
import cn.mldn.dbc.DataBaseConnection;
import cn.mldn.factory.DAOFactory;
import cn.mldn.pojo.TrainNumber;
import cn.mldn.pojo.UserOrder;
import cn.mldn.pojo.Userd;
import cn.mldn.service.IUserOrderService;

public class UserOrderServiceImpl implements IUserOrderService{
	
	 //ȡ�����ݿ�����
	private Connection conn=new DataBaseConnection().getConnection();
	PreparedStatement pstmt;
	//��ȡ���󣬷���ҵ����ϲ��ȡ
	public UserOrderServiceImpl(){
		super();
	}
	/**
	 * �����û���Ʊ����
	 * @param user �û����� 
	 * @param trainNum ���κ�
	 * @param remain ��Ʊ������Ʊ��Ҫ���г�Ʊ��-1
	 * @return �Ƿ���ɹ�
	 */
	@Override
	public boolean buy(Userd user, String trainNum,Integer remain) throws SQLException {
		String sql1="select count(trainNum) from userOrder where trainNum =?";
		int coun=0;
		this.pstmt=this.conn.prepareStatement(sql1);
		this.pstmt.setString(1, trainNum);
		ResultSet rs=this.pstmt.executeQuery();
		while(rs.next()){
			coun=rs.getInt(1);
		}
		if(coun==1){
			return false;
		}
		//ͨ��DAO������ȡ����ʵ����������ȡ��Ʊ�ķ���
		IUserOrderDao iorder=DAOFactory.getIUserOrderDaoInstance(conn);
		
		//��������ķ���Ϊ��ǰ����ϵͳ���û��Լ����κ�
		if(iorder.doBuyTicket(user, trainNum)){
			//�û�����ɹ����򽫳��α��иó���Ʊ��-1
			String sql="Update trainNumber"+" set remainTicket =? where trainNum= ?";
			remain=remain-1;
//			if(remain==0){
//				//���û�������Ʊ��Ȼ���Ʊ�պ����꣬��ɾ���˳�Ʊ
//				itrain.deleteTicket(trainNum);
//			}
			PreparedStatement psmt=this.conn.prepareStatement(sql);
			psmt.setInt(1, remain);
			psmt.setString(2, trainNum);
			psmt.executeUpdate();
			
		}
		conn.close();
		return true;
	}

	/**
	 * �����û���ѯ��������
	 * @param IDCard ���֤��
	 * @return ��ѯ��������
	 */
	@Override
	public List<UserOrder> findUseOrder(String IDCard) {
		//ͨ������ģʽ���DAO������ʵ�������
		IUserOrderDao iuseD=DAOFactory.getIUserOrderDaoInstance(conn);
		//���弯�ϣ��洢�û��Ķ�����Ϣ
		List<UserOrder> orderList=new ArrayList<UserOrder>();
		//����ɱ䳤�������洢�û��Ķ�����Ϣ
		try {
			orderList=iuseD.findMyAllTicket(IDCard);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}

	
	/**
	 * �����û���Ʊ
	 * @param trainNum ���κ�
	 * @param id ���֤��
	 * @return �Ƿ���Ʊ�ɹ�
	 */
	@Override
	public boolean returnTic(String trainNum,String id) {
		
		//ͨ�����ݲ㹤�����ö��󡣵�����Ʊ����
		IUserOrderDao iuOrder=DAOFactory.getIUserOrderDaoInstance(conn);
		boolean flag=false;
		try {
			//�ж��Ƿ���Ʊ�ɹ�
			if(iuOrder.doReturn(trainNum, id)){
				//�ɹ�����Ӷ�����ɾ����Ʊ
				iuOrder.doDelete(trainNum, id);
				flag=true;
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	try {
		conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
		return flag;
	}

	
	/**
	 *���ǹ���Ա���в�ѯ��������
	 * @return ��ѯ�Ķ������ϣ����û�ж������򼯺ϵ�size=0
	 */
	@Override
	public List<UserOrder> findManageAllOrder() {
		//ͨ�����ݲ㹤����ȡ�����ݶ��������ʵ������
		IUserOrderDao iuseOr=DAOFactory.getIUserOrderDaoInstance(conn);
		List<UserOrder> orderList=new ArrayList<UserOrder>();
		//��ѯ���ж�����ϢȻ�󷵻ؼ���
		orderList=iuseOr.findManageOrder();
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
		
	}

}
