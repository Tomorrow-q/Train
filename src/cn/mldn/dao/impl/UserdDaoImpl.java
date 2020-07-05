package cn.mldn.dao.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.mldn.dao.IUserdDao;
import cn.mldn.pojo.Userd;

/**
 * ������ͨ�û�(UserDao��)��ʵ����
 * 
 * @author ASUS
 *
 */
public class UserdDaoImpl implements IUserdDao {

	// �����ļ�����û���¼����
	private static File userFile = new File("UserFile.txt");
	private static List<Userd> users = new ArrayList<Userd>();// �洢�û����б���
	//private static int n;// �洢�û���
	
	public UserdDaoImpl() {
		super();
	}

	//��̬����飬���ȼ��أ����û���Ϣ���ص����ϣ��Ա��ѯ
	static {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(userFile));
			String line = null;
			Userd user = null;
			while ((line = br.readLine()) != null) {
				// �û���,����
				String[] datas = line.split(",");
				user = new Userd(datas[0], datas[1],datas[2],datas[3],datas[4]);
				users.add(user);
			}
			//System.out.println(users);
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
	 * �����û���¼���ܵ�ʵ��
	 * @param username  �û���
	 * @param password  �û�����
	 * @param user ������󣬽���¼�ɹ����û���Ϣ�洢Ȼ�󴫸������� 
	 * @return  �Ƿ��¼�ɹ�����¼�ɹ�����ture�����򷵻�false
	 */
	@Override
	public boolean isLogion(String username, String password, Userd user) {
		boolean flag = false;
		for(Userd us:users){
			if(us.getUsername().equals(username)&&us.getPassword().equals(password)){
				// �ҳ��û��˺�
				user.setUsername(us.getUsername());
				user.setPassword(us.getPassword());
				user.setPasseName(us.getPasseName());
				user.setSex(us.getSex());
				user.setIDCard(us.getIDCard());
				flag=true;
			}
		}
		return flag;

	}

//	@Override
//	public boolean register(Userd userd) throws IOException {
//		BufferedWriter bw = null;
//		boolean flag = true;
//		try {
//			// ������Ч�ַ�����������ļ�д�����ݣ�������һ������׷��д���FileWriter���������ļ���֮ǰ���û���Ϣ������
//			
//			br = new BufferedReader(new FileReader(userFile));
//			String str = null;
//			while ((str = br.readLine()) != null) {
//				String[] line = str.split(",");
//				if (line[0].equals(userd.getUsername())) {
//					flag = false;
//					break;
//				}
//			}
//
//			// ���û����ͬ���û�����д���ļ���ע��ɹ�
//			if (flag) {
//				// ���û���Ϣ����ļ���(�˻��������롢�������Ա����֤)
//				//
//			}
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			bw.close();
//			br.close();
//		}
//		
//		Iterator<Userd> iterable = users.iterator();
//		while (iterable.hasNext()){
//			Userd use=iterable.next();
//			bw = new BufferedWriter(new FileWriter(userFile, true));
//			if(use.getUsername().equals(userd.getUsername())){
//				flag=false;
//			}
//		}
//		if(flag){ //���û�и��û��������
//			StringBuilder sb=new StringBuilder();
//			users.add(userd);
//			//sb.append(userd.getUsername()).append(",").append(userd.getPassword()).append(",").append(userd.getPasseName()).append(",").append(userd.getSex()).append(",").append(userd.getIDCard());
//			//bw.write(sb.toString());
//		}
//		return flag;
//
//	}
	
	
	/**
	 * �����û���Ϣ�ĸ���
	 * @param user ��������Ϣ���û�����
	 * @return ���ĳɹ�����true�����򷵻�false
	 */
	@Override
	public boolean changeMessage(Userd user) {
		// �����������������
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(userFile, "rw");
			String str = null;
			long lastPoint = 0; // ��ס��һ�ε�ƫ����
			while ((str = raf.readLine()) != null) {
				// ���ļ��ж�ȡ���ַ�����ִ�Ž��ַ�������
				String[] line = str.split(",");
				final long point = raf.getFilePointer();
				// �ҵ���Ҫ�޸�������û���
				if (line[0].equals(user.getUsername())) {
					// ��Ҫ�޸ĵ��û���Ϣƴ�ӳ��ַ���
					String newUse = user.getUsername() + "," + user.getPassword() + "," + user.getPasseName() + ","
							+ user.getSex() + "," + user.getIDCard();
					// ��seekָ��Ҫд��λ��Ϊ����ǰ���ҵ�ƥ����Ϣ������λ��
					raf.seek(lastPoint);
					// ���޸ĺ���û���Ϣ����д���ļ�
					raf.write(newUse.getBytes());
					
					return true;
				}
				lastPoint = point;

			}

		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return false;

	}

	/**
	 * �����жϣ���Ҫ������Ϣ�����룩���û�ԭ�����Ƿ���ȷ
	 * @param user ������������û�
	 * @param password �����õ�����
	 * @return ��ͬ����true�����򷵻�false
	 */
	@SuppressWarnings("resource")
	public boolean isEqual(Userd user, String password) {
		//BufferedReader br = null;
		boolean flag = false;
		for(Userd us:users){
			if(us.getPassword().equals(password)&&us.getUsername().equals(user.getUsername())){
				flag=true;
				break;
			}
		}
		return flag;

		
	}

	
	/**
	 * �����û�ע������
	 *@param user ��ע�����û�
	 * @return ע���ɹ�����true�����򷵻�false
	 */
	@Override
	public boolean closePerson(Userd user) throws IOException {
		System.out.println(users);
		System.out.println(user.getUsername());
		for(int i=0;i<users.size();i++){
			Userd u=users.get(i);
			
			System.out.println(u.getUsername());
			if(u.getUsername().equals(user.getUsername())){
				System.out.println(u.getUsername());
				users.remove(i);
				
				System.out.println(i);
				System.out.println(users);
				break;
			}
		}
		return docreat(users);
		
		//	List<Userd> list=new ArrayList<Userd>();
		
//		BufferedReader br=null;
//		BufferedWriter bw=null;
//		try {
//			br=new BufferedReader(new FileReader("UserFile.txt"));
//			bw=new BufferedWriter(new FileWriter("UserFile.txt",true));
//			String str=null;
//			while((str = br.readLine())!=null){
//				System.out.println(str);
//				String line[]=str.split(",");
//				System.out.println(line[0]);
//				Userd us=new Userd();
//				us.setUsername(line[0]);
//				us.setPassword(line[1]);
//				us.setPasseName(line[2]);
//				us.setSex(line[3]);
//				us.setIDCard(line[4]);
//				if(!(user.getUsername().equals(line[0]))){
//					list.add(us);
//					System.out.println(list);
//				}
//			}
//			for(Userd use:list){
//				StringBuilder sb=new StringBuilder();
//				
//				System.out.println(sb.toString());
//				bw.write(sb.toString());
//				bw.newLine();
//				bw.flush();
//				
//			}
//			
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}finally{
//			br.close();
//			bw.close();
//		}
//	

}


	/**
	 * ��ԃ�û��Ƿ����
	 * @param user ����ӵ��û�
	 * @return ������ݴ��� ����true ���򷵻�false
	 */
	@Override
	public boolean findUser(Userd user){
	boolean flag = false;
	for(Userd us:users){
		if(us.getUsername().equals(user.getUsername())){
			flag=true;
		}
	}
		return flag;

	}

	/**
	 * ���ǽ�ע����û������ı��ļ�
	 * @param user ����ӵ��û�
	 * @return ��ӳɹ�����true�����򷵻�false
	 */
	@Override
	public boolean DoAdd(Userd user){
		BufferedWriter bw=null;
		try {
			bw=new BufferedWriter(new FileWriter(userFile,true));
			String info = user.getUsername() + "," + user.getPassword() + "," + user.getPasseName() + ","
					+ user.getSex() + "," + user.getIDCard();
			bw.write(info);
			users.add(user);
			bw.newLine();
			bw.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	/**
	 * ���Ƕ��û���Ϣ����б䶯����Խ���д��
	 * @param list �洢�û���Ϣ�ļ���
	 * @return �Ƿ�д��ɹ����ɹ���Ϊtrue������false
	 */
	@Override
	public boolean docreat(List list){
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(userFile);
	    fileWriter.write("");
	    fileWriter.flush();
	    fileWriter.close(); 
	    } catch (IOException e1) {
			e1.printStackTrace();
		} 	
		
		Iterator<Userd> iterable = list.iterator();
		while (iterable.hasNext()){
			Userd user=iterable.next();
			BufferedWriter bw = null;
		try {
                bw = new BufferedWriter(new FileWriter(userFile,true));
				bw.write(user.getUsername()+","+user.getPassword()+","+user.getPasseName()+","+user.getSex()+","+user.getIDCard());
				bw.newLine();
				bw.flush();
			} catch (IOException e) {
				return false;
			} finally {
				if (bw != null) {
					try {
						bw.close();
					} catch (IOException e) {
						//System.out.println("�û����ע���ͷ���Դʧ��");
						return false;
					}
				}
			}
		}
		
		return true;
	}
}