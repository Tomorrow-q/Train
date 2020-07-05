package cn.mldn.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import cn.mldn.pojo.UserOrder;
import cn.mldn.pojo.Userd;

/**
 * �����û���Ʊ��ϢDao�࣬����鿴��Ʊ����Ʊ���Լ�ɾ���ȹ��ܵĽӿ�
 * @author ASUS
 *
 */
public interface IUserOrderDao {
	
	/**
	 * �����û���ѯ��Ʊ�����Ĺ���
	 * @param IDCard �û����֤��
	 * @return ��ѯ�ĳ�Ʊ�����ļ���
	 * @throws SQLException 
	 */
	public List<UserOrder> findMyAllTicket(String IDCard) throws SQLException;
	
	/**
	 * �����û�������Ʊ���ܣ����ݲ�ѯ�ĳ��κ���Ʊ��
	 * @param trainNum ���κ�
	 * @param id ���֤��
	 * @return �Ƿ���Ʊ�ɹ�
	 * @throws SQLException 
	 */
	public boolean doReturn(String trainNum,String id) throws SQLException;
	
	/**
	 * �����û�������Ʊ�Ĺ���
	 * @param trainNum ���κ�
	 * @return �Ƿ���ɹ����ɹ�����true�����򷵻�false
	 * @throws SQLException
	 */
	public boolean doBuyTicket(Userd user,String trainNum) throws SQLException;
	
	/**
	 * �Ӷ�����¼ɾ���û��Լ��Ķ���
	 * @param trainNum ���κ�
	 * @param id  ���֤��
	 * @return ɾ���Ƿ�ɹ���ɾ���ɹ�����true������false
	 * @throws SQLException
	 */
	public boolean doDelete(String trainNum,String id) throws SQLException;
	
	/***
	 * ���ǹ���Ա�����ж����Ĳ�ѯ����
	 * @return ���ж�����Ϣ�ļ���
	 */
	public List<UserOrder> findManageOrder();
}
