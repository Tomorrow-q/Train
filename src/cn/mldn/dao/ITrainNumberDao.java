package cn.mldn.dao;

import java.sql.SQLException;
import java.util.List;

import cn.mldn.pojo.TrainNumber;
import cn.mldn.pojo.UserOrder;

/**
 * ������Ի�Ʊ������ɾ��ĵĲ����Ľӿ�
 * @author ASUS
 *
 */
public interface ITrainNumberDao {
	
	/**
	 * ���ǶԻ𳵳�����Ϣ���ӵĹ���
	 * @param trainNum �����ӵĳ��κ�
	 * @param startPlace ʼ����
	 * @param dest Ŀ�ĵ�
	 * @param goTime ����ʱ��
	 * @param remainTicket ��Ʊ��
	 * @param price  �۸�
	 * @return  �Ƿ���ӳɹ����ɹ�Ϊture�����򷵻�false
	 */
	//public boolean  addTicket(String trainNum,String startPlace,String dest,String goTime,Integer remainTicket,String price) throws SQLException;
	public boolean  addTicket(TrainNumber trainNumber) throws SQLException;
	
	/**
	 * �����û��Ի𳵳�����Ϣ���ҵĹ���
	 * @param startPlace  ʼ����
	 * @param destPlace   Ŀ�ĵ�
	 * @return ��ѯ�ĳ��εļ��ϣ����û�иó��Σ���list���ϵ�sizeΪ0
	 */
	public List<TrainNumber> findUserTicket(String startPlace,String destPlace) throws SQLException;
	
	/**
	 * ���ǹ���Ա�Ի𳵳���ɾ���Ĳ���
	 * @param trainNum ���κ�
	 * @param goTime  ����ʱ��
	 * @return �Ƿ�ɾ���ɹ����ɹ�Ϊtrue
	 */
	public boolean deleteTicket(String trainNum) throws SQLException;
	
	/**
	 * ���ǹ���Ա�Գ�����Ϣ�����޸ĵĹ���
	 * @param trainNum  ���κ�
	 * @param attribute Ҫ�޸ĵ�����
	 * @param content   �޸ĺ������
	 * @return �Ƿ��޸ĳɹ����ɹ�Ϊtrue
	 */
	public boolean updateTicket(String trainNum,String attribute,String content) throws SQLException;
	
	/**
	 * ���ǹ���Ա��ѯ���г�����Ϣ�Ĺ���
	 * @return ��ѯ�����г�����Ϣ�ļ���
	 */
	public List<TrainNumber> findAllTicket(int num) throws SQLException;
	
	/**
	 * �����жϹ���Ա����ĳ�����Ϣ�Ƿ��������ݿ��д���
	 * @param trainNum ����
	 * @param startPlace ʼ����
	 * @param dest  Ŀ�ĵ�
	 * @param goTime ����ʱ�� 
	 * @param retick Ʊ��
	 * @return 0ΪƱ�����ӣ�������ͬ�ĳ��Σ�1������ͬ���κţ������ظ�����ֵ���������ӣ�2����������
	 * @throws SQLException
	 */
	public int isExist(TrainNumber train ) throws SQLException;
	
	public List<TrainNumber> sort(int num);
	
	//public List<UserOrder> findOrder()  throws SQLException;
}
