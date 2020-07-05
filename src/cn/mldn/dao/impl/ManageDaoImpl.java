package cn.mldn.dao.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import cn.mldn.dao.IManageDao;
import cn.mldn.pojo.Manager;

/**
 * ���ǹ���Ա(ManageDao)��¼��ʵ����
 * @author ASUS
 *
 */
public class ManageDaoImpl implements IManageDao{
	//����Ա�����Ϣ�ļ�
	private static File ManageFile=new File("ManageFile.txt");
	// �洢����Ա��¼��Ϣ���б���
	private static List<Manager> managers = new ArrayList<Manager>();
	public ManageDaoImpl() {
		super();
	}
	
	//��̬����飬���ع���Ա��Ϣ������
	static {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(ManageFile));
			String line = null;
			Manager manage = null;
			//��ȡ���й���Ա�˻���Ϣ�����ϣ��Ա��¼ʱ��ѯ
			while ((line = br.readLine()) != null) {
				// �û���,����
				String[] datas = line.split(",");
				manage = new Manager(datas[0],datas[1]);
				managers.add(manage);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println("�û���¼�ͷ���Դʧ��");
				}
			}
		}
	}
	
	
	
	/**
	 * ���ǹ���Ա��¼��¼����
	 * @param managename  �û���
	 * @param password  �û�����
	 * @return  �Ƿ��¼�ɹ�����¼�ɹ�����true������Ϊfalse
	 */
	@Override
	public boolean ManageLogion(String managename, String password) {
		
		boolean flag=false;
		if(managers!=null){
			for(Manager ma:managers){
				if(ma.getManageName().equals(managename)&&ma.getPassword().equals(password)){
					flag=true;
					break;
				}
			}
		}
		
//		BufferedReader br=null;
//		//������Ч�ַ�����ȡ����
//		try {
//			br=new BufferedReader(new FileReader(ManageFile));
//			String line=null;
//				while((line=br.readLine())!=null){//һ�ζ�ȡһ������
//					 String data[]=line.split(",");
//					 if(data[0].equals(managename)&&data[1].equals(password)){
//						 flag=true;
//						 break;
//					 }
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}finally{
//				try {
//					br.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
		return flag;
	}
}
