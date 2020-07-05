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
 * 这是普通用户(UserDao类)的实现类
 * 
 * @author ASUS
 *
 */
public class UserdDaoImpl implements IUserdDao {

	// 定义文件存放用户登录数据
	private static File userFile = new File("UserFile.txt");
	private static List<Userd> users = new ArrayList<Userd>();// 存储用户的列表集合
	//private static int n;// 存储用户数
	
	public UserdDaoImpl() {
		super();
	}

	//静态代码块，最先加载，将用户信息加载到集合，以便查询
	static {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(userFile));
			String line = null;
			Userd user = null;
			while ((line = br.readLine()) != null) {
				// 用户名,密码
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
					System.out.println("用户登录释放资源失败");
				}
			}
		}
	}
	
	
	/**
	 * 这是用户登录功能的实现
	 * @param username  用户名
	 * @param password  用户密码
	 * @param user 传入对象，将登录成功的用户信息存储然后传给主界面 
	 * @return  是否登录成功，登录成功返回ture，否则返回false
	 */
	@Override
	public boolean isLogion(String username, String password, Userd user) {
		boolean flag = false;
		for(Userd us:users){
			if(us.getUsername().equals(username)&&us.getPassword().equals(password)){
				// 找出用户账号
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
//			// 创建高效字符输出流来给文件写入数据，创建了一个可以追加写入的FileWriter，避免了文件中之前的用户信息被覆盖
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
//			// 如果没有相同的用户名就写入文件，注册成功
//			if (flag) {
//				// 把用户信息存进文件里(账户名、密码、姓名、性别、身份证)
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
//		if(flag){ //如果没有该用户，就添加
//			StringBuilder sb=new StringBuilder();
//			users.add(userd);
//			//sb.append(userd.getUsername()).append(",").append(userd.getPassword()).append(",").append(userd.getPasseName()).append(",").append(userd.getSex()).append(",").append(userd.getIDCard());
//			//bw.write(sb.toString());
//		}
//		return flag;
//
//	}
	
	
	/**
	 * 这是用户信息的更改
	 * @param user 欲更改信息的用户对象
	 * @return 更改成功返回true，否则返回false
	 */
	@Override
	public boolean changeMessage(Userd user) {
		// 定义随机访问流对象
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(userFile, "rw");
			String str = null;
			long lastPoint = 0; // 记住上一次的偏移量
			while ((str = raf.readLine()) != null) {
				// 将文件中读取的字符串拆分存放进字符串数组
				String[] line = str.split(",");
				final long point = raf.getFilePointer();
				// 找到需要修改密码的用户名
				if (line[0].equals(user.getUsername())) {
					// 将要修改的用户信息拼接成字符串
					String newUse = user.getUsername() + "," + user.getPassword() + "," + user.getPasseName() + ","
							+ user.getSex() + "," + user.getIDCard();
					// 用seek指定要写入位置为：当前查找到匹配信息的这行位置
					raf.seek(lastPoint);
					// 将修改后的用户信息重新写入文件
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
	 * 进行判断，需要更改信息（密码）的用户原密码是否正确
	 * @param user 欲更该密码的用户
	 * @param password 欲更该的密码
	 * @return 相同返回true，否则返回false
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
	 * 这是用户注销操作
	 *@param user 欲注销的用户
	 * @return 注销成功返回true，否则返回false
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
	 * 查詢用户是否存在
	 * @param user 欲添加的用户
	 * @return 如果数据存在 返回true 否则返回false
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
	 * 这是将注册的用户存入文本文件
	 * @param user 欲添加的用户
	 * @return 添加成功返回true，否则返回false
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
	 * 这是对用户信息如果有变动则可以进行写入
	 * @param list 存储用户信息的集合
	 * @return 是否写入成功，成功则为true，否则false
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
						//System.out.println("用户添加注册释放资源失败");
						return false;
					}
				}
			}
		}
		
		return true;
	}
}