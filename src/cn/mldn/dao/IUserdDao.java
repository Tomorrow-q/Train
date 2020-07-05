package cn.mldn.dao;

import java.io.IOException;
import java.util.List;

import cn.mldn.pojo.Userd;


/**
 * ������ͨ�û�ע���¼��DAO
 * @author ASUS
 *
 */
public interface IUserdDao {
	/**
	 * �����û���¼����
	 * @param username  �û���
	 * @param password  �û�����
	 * @param user ������󣬽���¼�ɹ����û���Ϣ�洢Ȼ�󴫸������� 
	 * @return  �Ƿ��¼�ɹ�
	 */
	public abstract boolean isLogion(String username,String password,Userd user) ;
	
	/**
	 * �����û�ע�Ṧ��
	 * @param user  �û�����
	 * @return  �Ƿ�ע��ɹ�
	 */
	//public abstract boolean register(Userd userd) throws IOException ;
	
	/**
	 * �����û��޸��Լ����˻���Ϣ�Ĺ���
	 * @return ������Ϣ�Ƿ�ɹ�
	 */
	public boolean changeMessage(Userd user);
	
	/**
	 * �����û���Ϣ�жϣ����޸�����ʱ���ж��û�����Ϣ
	 * @param user �û�����
	 * @param password ����
	 * @return  ��ͬ����true�����򷵻�false
	 */
	public boolean isEqual(Userd user,String password);
	
	
	/**
	 * ע�������˻�
	 * @param user �û�����
	 * @return �Ƿ�ע���ɹ���ע���ɹ�����true
	 * @throws IOException 
	 */
	public boolean closePerson(Userd user) throws IOException;
	
	/**
	 * ���ǲ�ѯ����ע����û��Ƿ����
	 * @param user Ҫע����˻�����
	 * @return ���ڷ���true�������ڷ���false
	 */
	public boolean findUser(Userd user);
	
	/**
	 * ���ǽ�ע����û������ı��ļ�
	 * @param user Ҫ������û�����
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean DoAdd(Userd user);
	
	/**
	 * �����û���Ϣ���ĺ�����д���ļ��Ĺ���
	 * @param list �洢�û����󼯺�
	 * @return  �Ƿ���ĳɹ����ɹ�Ϊtrue������Ϊfalse
	 */
	public boolean docreat(List list);
}
