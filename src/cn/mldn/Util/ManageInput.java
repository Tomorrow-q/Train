package cn.mldn.Util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
/**
 * ����������ɹ���Ա�˻�����
 * @author ASUS
 *
 */
public class ManageInput {
	public static void main(String[] args) {
		BufferedWriter bw=null;
		try {
			 // д�����ݣ��ļ�д��ʱ׷��
			bw=new BufferedWriter(new FileWriter("ManageFile.txt",true));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//д��50������Ա����
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
		System.out.println("��ɣ�");
	
	}
}
