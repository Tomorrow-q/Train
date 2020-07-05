package cn.mldn.service;

import java.sql.SQLException;
import java.util.List;

import cn.mldn.pojo.TrainNumber;

/**
 * ���Ƕ���trainNumber���ҵ���ִ�б�׼
 * @author ASUS
 *
 */
public interface ITrainNumberService {
	
	/**
	 * ʵ�ֳ�����Ϣ����Ӳ���
	 * ��Ҫ����ITrainNumberDao��isExist()�������ж����ӵĳ�����Ϣ�Ƿ�����ݿ�������ͬ��Ϣ����ͬ��Ʊ������
	 * addTicket()���������ӵĳ�����Ϣ�������ҳ��κŲ��ظ�����ֱ�ӵ��ô˷���
	 * @param train ����ʵ�����
	 * @return �Ƿ���ӳɹ�
	 * @throws Exception 
	 */
//	public boolean insert(String trainNum, String startPlace, String dest, String goTime, Integer remainTicket,
//			String price) throws SQLException, Exception; 
	public boolean insert(TrainNumber train) throws SQLException, Exception; 
	
	/**
	 * ����Աʵ�ֳ�����Ϣɾ������
	 * @param trainNum ���κ�
	 * @return �Ƿ�ɾ���ɹ�
	 *  @throws SQLException 
	 */
	public boolean delete(String trainNum) throws SQLException;
	
	/**
	 * ʵ���û���ѯ������Ϣ�����������ʼ���غ�Ŀ��
	 * @param startPlace ʼ����
	 * @param destPlace  Ŀ�ĵ�
	 * @return ��ѯ�Ľ���Լ�����ʽ����
	 *  @throws SQLException 
	 */
	public List<TrainNumber> selectUser(String startPlace, String destPlace) throws SQLException;
	
	/**
	 * ����Ա�鿴���г�����Ϣ
	 * @return ���г�����Ϣ�Լ�����ʽ���أ���û������Ϣ���򼯺ϵ�sizeΪ0
	 * @throws SQLException 
	 */
	public List<TrainNumber> selectAll(int num) throws SQLException;
	
//	/**
//	 * ����Ա�鿴���е���Ʊ��¼
//	 * @return �Լ��ϵ���ʽ����������Ʊ��¼
//	 */
//	public List<TrainNumber> selectOrder() throws SQLException;
	
	/**
	 * ʵ�ֹ���Ա�޸ĳ�����Ϣ
	 * @param trainNum ��Ҫ�޸ĵĳ��κ�
	 * @param attribute  ��Ҫ�޸ĵ������磺ʼ���ء�Ŀ�ĵء��۸�Ʊ�����۸�
	 * @param content  �޸ĺ������
	 * @return �Ƿ��޸ĳɹ�
	 * @throws SQLException 
	 */
	public boolean Update(String trainNum, String attribute, String content) throws SQLException;
	
	public List<TrainNumber> sort(int num);
	
}

