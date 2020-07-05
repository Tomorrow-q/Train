package cn.mldn.Util;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * 这是窗体显示的工具类
 * @author ASUS
 *
 */
public class Util {
	//设置窗体居中显示
		public static void setFrameCenter(JFrame jf) {
			Toolkit tk=Toolkit.getDefaultToolkit();//获取默认工具包。
			Dimension d=tk.getScreenSize();//获取屏幕尺寸
			int swidth=d.width;//获取屏幕宽度
			int sheight=d.height;//获取屏幕高度
			int x=(swidth-jf.getWidth())/2;//(屏幕宽度-子窗体宽度)/2
			int y=(sheight-jf.getHeight())/2;//(屏幕高度-子窗体高度)/2
			//设置子窗体位置
			jf.setLocation(x, y);
		}
}
