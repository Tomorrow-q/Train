package cn.mldn.Util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
/**
 * 这是随机生成管理员账户的类
 * @author ASUS
 *
 */
public class ManageInput {
	public static void main(String[] args) {
		BufferedWriter bw=null;
		try {
			 // 写出数据，文件写入时追加
			bw=new BufferedWriter(new FileWriter("ManageFile.txt",true));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//写入50条管理员数据
		for (int i = 0; i < 50; i++) {
			StringBuilder sb=new StringBuilder();
			sb.append("admin"+i).append(",").append("admin"+i);
			try {
				bw.write(sb.toString());
				bw.newLine();
				bw.flush();
			} catch(IOException e){
				e.printStackTrace();
			}
			
		}
		try {
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("完成！");
	
	}
}
