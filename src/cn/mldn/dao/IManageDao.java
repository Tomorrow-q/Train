package cn.mldn.dao;
/**
 * ���ǹ���Ա��¼��DAO
 * @author ASUS
 *
 */
public interface IManageDao {
	/**
	 * ���ǹ���Ա��¼��¼����
	 * @param username  �û���
	 * @param password  �û�����
	 * @return  �Ƿ��¼�ɹ�����¼�ɹ�����true������Ϊfalse
	 */
	public abstract boolean ManageLogion(String username,String password);
}
