package cn.mldn.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import cn.mldn.pojo.TrainNumber;
import cn.mldn.pojo.UserOrder;
import cn.mldn.pojo.Userd;

/**
 * ������ͨ�û�������ҵ����׼
 * @author ASUS
 *
 */
public interface IUserOrderService {
	
	/**
	 * �����û���Ʊ����
	 * @param user �û����� 
	 * @param trainNum ���κ�
	 * @return �Ƿ���ɹ����ɹ���Ϊtrue
	 * @throws SQLException 
	 */
	public boolean buy(Userd user,String trainNum,Integer remain)  throws SQLException;
	
	/**
	 * �����û���ѯ��������
	 * @param IDCard ���֤��
	 * @return ��ѯ��������
	 * @throws SQLException 
	 */
	public List<UserOrder> findUseOrder(String IDCard)  throws SQLException;
	
	/**
	 * �����û���Ʊ
	 * @param trainNum ���κ�
	 * @param id ���֤��
	 * @return �Ƿ���Ʊ�ɹ�
	 * @throws SQLException 
	 */
	public boolean returnTic(String trainNum,String id)  throws SQLException;
	
	/**
	 *���ǹ���Ա���в�ѯ��������
	 * @return ��ѯ�Ķ������ϣ����û�ж������򼯺ϵ�size=0
	 */
	public List<UserOrder> findManageAllOrder();
	
}
