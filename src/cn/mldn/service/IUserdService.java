package cn.mldn.service;

import java.io.IOException;

import cn.mldn.pojo.Userd;

/**
 * �����û���¼ע��
 * @author ASUS
 *
 */
public interface IUserdService {
	/**
	 * �û���¼
	 * @param username  �û���
	 * @param password  ����
	 * @return  �Ƿ��¼�ɹ�
	 */
	public boolean login(String username, String password,Userd user);

	/**
	 * �û�ע��
	 * @param userd �û�����
	 * @return �Ƿ�ע��ɹ�
	 * @throws IOException 
	 */
	public boolean register(Userd userd)  throws IOException;
	
	/**
	 * �����޸��û���Ϣ�Ĺ���
	 * @param userd �û�����
	 * @return �Ƿ��޸ĳɹ����޸ĳɹ�����ture������false
	 */
	public boolean changeMessage(Userd userd);
	
	/**
	 * ���Ƕ��û���Ϣ�ж�
	 * @param user
	 * @param password
	 * @return �Ƿ���ȣ���ȷ���true
	 */
	public boolean isEqual(Userd user,String password);
	
	/**
	 * �����û�ע���˻�����
	 * @param user �û�����
	 * @return �Ƿ�ע���ɹ�
	 */
	public boolean closeAccount(Userd user);
}
