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
 * 这是管理员(ManageDao)登录的实现类
 * @author ASUS
 *
 */
public class ManageDaoImpl implements IManageDao{
	//管理员存放信息文件
	private static File ManageFile=new File("ManageFile.txt");
	// 存储管理员登录信息的列表集合
	private static List<Manager> managers = new ArrayList<Manager>();
	public ManageDaoImpl() {
		super();
	}
	
	//静态代码块，加载管理员信息到集合
	static {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(ManageFile));
			String line = null;
			Manager manage = null;
			//读取所有管理员账户信息到集合，以便登录时查询
			while ((line = br.readLine()) != null) {
				// 用户名,密码
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
					System.out.println("用户登录释放资源失败");
				}
			}
		}
	}
	
	
	
	/**
	 * 这是管理员登录登录功能
	 * @param managename  用户名
	 * @param password  用户密码
	 * @return  是否登录成功，登录成功返回true，否则为false
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
//		//创建高效字符流读取数据
//		try {
//			br=new BufferedReader(new FileReader(ManageFile));
//			String line=null;
//				while((line=br.readLine())!=null){//一次读取一行数据
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
