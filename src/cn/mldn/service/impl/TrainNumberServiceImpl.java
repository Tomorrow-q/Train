package cn.mldn.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.mldn.dao.ITrainNumberDao;
import cn.mldn.dao.IUserOrderDao;
import cn.mldn.dbc.DataBaseConnection;
import cn.mldn.factory.DAOFactory;
import cn.mldn.pojo.TrainNumber;
import cn.mldn.pojo.UserOrder;
import cn.mldn.service.ITrainNumberService;
/**
 * ���Ƕ�ҵ����׼��ʵ���ࣨ���ࣩ
 * ����Ҫ��������ݿ�Ĵ򿪺͹ر�
 * 
 * @author ASUS
 *
 */
public class TrainNumberServiceImpl implements ITrainNumberService {
	 //ȡ�����ݿ�����
	private Connection conn=new DataBaseConnection().getConnection();


	/**
	 * ʵ�ֳ�����Ϣ����Ӳ���
	 * ��Ҫ����ITrainNumberDao��isExist()�������ж����ӵĳ�����Ϣ�Ƿ�����ݿ�������ͬ��Ϣ����ͬ��Ʊ������
	 * addTicket()���������ӵĳ�����Ϣ�������ҳ��κŲ��ظ�����ֱ�ӵ��ô˷���
	 * @param train ����ʵ�����
	 * @return �Ƿ���ӳɹ�
	 * @throws SQLException 
	 */
	
//	public boolean insert(String trainNum, String startPlace, String dest, String goTime, Integer remainTicket,
//			String price) throws Exception {
	@Override
	public boolean insert(TrainNumber train) throws SQLException{
		//���ù�������ģʽ���ITrainNumberDaoʵ������
		ITrainNumberDao railway=DAOFactory.getITrainNumberDaoInstance(conn);
		int flag=0;
		try {
			//flag=railway.isExist(trainNum, startPlace, dest, goTime, remainTicket, price);
			flag=railway.isExist(train);
			if(flag==2){
				railway.addTicket(train);
				return true;
			}else if(flag==0){
				return true;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}finally{
			//�ر����ݿ�����
			conn.close();
		}
		
			return false;
		
	}


	/**
	 * ����Աʵ�ֳ�����Ϣɾ������
	 * @param trainNum ���κ�
	 * @return �Ƿ�ɾ���ɹ�
	 * @throws SQLException 
	 */
	@Override
	public boolean delete(String trainNum) throws SQLException{
		//ͨ�����ݲ�Ĺ����������ݲ㳵�α������ʵ���������ڵ������ݲ㷽��
		ITrainNumberDao Itrain =DAOFactory.getITrainNumberDaoInstance(conn);
		boolean flag=false;
		flag=Itrain.deleteTicket(trainNum);
		this.conn.close(); //�ر����ݿ�����
		return flag;
		
		
	}

	
	/**
	 * ʵ���û���ѯ������Ϣ�����������ʼ���غ�Ŀ��
	 * @param startPlace ʼ����
	 * @param destPlace  Ŀ�ĵ�
	 * @return ��ѯ�Ľ���Լ�����ʽ����
	 * @throws SQLException 
	 */
	@Override
	public List<TrainNumber> selectUser(String startPlace, String destPlace) throws SQLException {
		//���ù�������ģʽ���ITrainNumberDaoʵ������
		ITrainNumberDao railway=DAOFactory.getITrainNumberDaoInstance(conn);
		List<TrainNumber> list=new ArrayList<TrainNumber>();
		//��ѯ�û������ҵĳ���
		list=railway.findUserTicket(startPlace, destPlace);
		conn.close(); //�ر����ݿ�����
		return list;
	}

	
	/**
	 * ����Ա�鿴���г�����Ϣ
	 * @return ���г�����Ϣ�Լ�����ʽ���أ���û������Ϣ���򼯺ϵ�sizeΪ0
	 * @throws SQLException 
	 */
	@Override
	public List<TrainNumber> selectAll(int num) throws SQLException{
		ITrainNumberDao itrainDao=DAOFactory.getITrainNumberDaoInstance(conn);
		List<TrainNumber> trainList=new ArrayList<TrainNumber>();
		trainList=itrainDao.findAllTicket(num);
		conn.close();
		return trainList;
	}

//	@Override
//	public List<UserOrder> selectOrder() throws SQLException{
//		//ͨ������ģʽ��ȡ���ݲ���ʶ���
//		IUserOrderDao iuseOrder=DAOFactory.getIUserOrderDaoInstance(conn);
//		List<UserOrder> list=new ArrayList<UserOrder>();
//		list=iuseOrder.findManageOrder();
//		return list;
//		return null;
//	}

	/**
	 * ʵ�ֹ���Ա�޸ĳ�����Ϣ
	 * @param trainNum ��Ҫ�޸ĵĳ��κ�
	 * @param attribute  ��Ҫ�޸ĵ������磺ʼ���ء�Ŀ�ĵء��۸�Ʊ�����۸�
	 * @param content  �޸ĺ������
	 * @return �Ƿ��޸ĳɹ�
	 * @throws SQLException 
	 */
	@Override
	public boolean Update(String trainNum, String attribute, String content) throws SQLException{
		boolean flag=false;
		ITrainNumberDao itrainDao=DAOFactory.getITrainNumberDaoInstance(conn);
		if(itrainDao.updateTicket(trainNum, attribute, content)){
			flag=true;
		}
		this.conn.close();
		return flag;
	}


	@Override
	public List<TrainNumber> sort(int num) {
		ITrainNumberDao train=DAOFactory.getITrainNumberDaoInstance(conn);
		List<TrainNumber> list=train.sort(num);
		
		return list;
	}

}
