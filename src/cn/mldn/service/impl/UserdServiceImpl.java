package cn.mldn.service.impl;

import java.io.IOException;
import java.sql.SQLException;

import cn.mldn.dao.IUserdDao;
import cn.mldn.factory.DAOFactory;
import cn.mldn.pojo.Userd;
import cn.mldn.service.IUserdService;

public class UserdServiceImpl implements IUserdService {

	/**
	 * ���Ƕ��û���Ϣ���е�¼�Ĺ���
	 * @param username �û��˺�
	 * @param password �˻�����
	 * @param user �û����������洢����������ϵͳ���û�
	 * ����DAO��ĵ�¼����
	 * @return �Ƿ��¼�ɹ����ɹ���Ϊtrue������false
	 */
	@Override
	public boolean login(String username, String password,Userd user) {
		IUserdDao ud=DAOFactory.getIUserdDaoInstance();
		if(ud.isLogion(username, password,user)){
			return true;
		}
		return false;
	}

	/**
	 * ���Ƕ��û���Ϣ����ע��Ĺ���
	 * @param user ��Ҫע�ᱣ���û�����
	 * findUserΪ��ѯ��ע������Ƿ��Ѵ��ڣ���������������DoAdd����ע�����
	 * @return �Ƿ�ע��ɹ����ɹ���Ϊtrue������false
	 * @throws IOException 
	 */
	@Override
	public boolean register(Userd userd) throws IOException {
		IUserdDao ud=DAOFactory.getIUserdDaoInstance();
		if(!ud.findUser(userd)){//��ѯע����˻��Ƿ����
			ud.DoAdd(userd); //��������ӽ�ȥ
			return true;
		}
		return false;
	}


	/**
	 * ���Ƕ��û��������Ĺ���
	 * @param user ��Ҫ�����Ϣ���û�����
	 * ����DAO��changeMessage����
	 * @return �Ƿ���ĳɹ����ɹ���Ϊtrue������false
	 */
	@Override
	public boolean changeMessage(Userd userd) {
		IUserdDao iud=DAOFactory.getIUserdDaoInstance();
			if(iud.changeMessage(userd)){
			return true;
		}
		
		
		return false;
	}

	/**
	 * ���Ƕ��û�������ʱ����ԭ�����жϵĹ���
	 * @param user ��Ҫ�����Ϣ���û�����
	 * @param password �����������
	 * ����DAO��isEqual����
	 * @return �Ƿ���ͬ����ͬ��Ϊtrue������false
	 */
	@Override
	public boolean isEqual(Userd user, String password) {
		IUserdDao iud=DAOFactory.getIUserdDaoInstance();
		if(iud.isEqual(user, password)){
				return true;
		}
		return false;
	}

	/**
	 * ���Ƕ��û��˻�ע���Ĺ���
	 * @param user ��Ҫע�����û�����
	 * ����DAO��closePerson����
	 * @return �Ƿ��ע���ɹ����ɹ���Ϊtrue������false
	 */
	@Override
	public boolean closeAccount(Userd user) {
		//��ȡ���ݲ��û�������׼ʵ������
		IUserdDao iuse=DAOFactory.getIUserdDaoInstance();
		try {
			if(iuse.closePerson(user)){
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}


}
