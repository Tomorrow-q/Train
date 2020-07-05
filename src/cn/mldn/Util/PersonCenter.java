package cn.mldn.Util;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JList;

public class PersonCenter extends JInternalFrame {

	//定义子窗体为私有
	private static PersonCenter ch1=null;
	
	//静态公开方法，只产生一个对象，synchronized保证线程案例
	public static synchronized PersonCenter getInstance(){
		if(ch1==null){
			ch1=new PersonCenter ();
		}
		return ch1;
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PersonCenter frame = new PersonCenter();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PersonCenter() {
		setClosable(true);
		setTitle("\u7528\u6237\u4E2A\u4EBA\u4E2D\u5FC3");
		setFrameIcon(new ImageIcon(PersonCenter.class.getResource("/source/premium.png")));
		setBounds(100, 100, 429, 397);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		//对象化面板
		JPanel combop = new JPanel();
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		tabbedPane.add(p1,"基本资料");
		p1.setLayout(null);
		
		JList list = new JList();
		list.setBounds(89, 88, 167, 123);
		p1.add(list);
		tabbedPane.add(p2,"账户信息");
		combop.add(new JLabel("个人中心管理"));
		getContentPane().add(combop, BorderLayout.NORTH);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		

	}
}
