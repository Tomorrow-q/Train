package cn.mldn.Util;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * ���Ǵ�����ʾ�Ĺ�����
 * @author ASUS
 *
 */
public class Util {
	//���ô��������ʾ
		public static void setFrameCenter(JFrame jf) {
			Toolkit tk=Toolkit.getDefaultToolkit();//��ȡĬ�Ϲ��߰���
			Dimension d=tk.getScreenSize();//��ȡ��Ļ�ߴ�
			int swidth=d.width;//��ȡ��Ļ���
			int sheight=d.height;//��ȡ��Ļ�߶�
			int x=(swidth-jf.getWidth())/2;//(��Ļ���-�Ӵ�����)/2
			int y=(sheight-jf.getHeight())/2;//(��Ļ�߶�-�Ӵ���߶�)/2
			//�����Ӵ���λ��
			jf.setLocation(x, y);
		}
}
