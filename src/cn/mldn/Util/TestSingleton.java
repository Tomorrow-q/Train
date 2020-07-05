package cn.mldn.Util;


import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.*;

public class TestSingleton {

	private JFrame frame = new JFrame("测试");

	private JPanel p = new JPanel();

	private JButton btn1 = new JButton("弹出第一个窗口");

	private JButton btn2 = new JButton("弹出第二个窗口");

	

	private JFrame f1;

	private JFrame f2;

	

	public TestSingleton(){

		init();

	}

	

	public void init(){

		p.add(btn1);

		p.add(btn2);

		

		frame.add(p);

		frame.setSize(222, 222);

		frame.setLocation(50, 50);

		frame.setVisible(true);

		

		/*

		 * 给按钮添加事件监听器

		 */

		btn1.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {

				f1 = Singleton1.getInstance();

				

				f1.setTitle("第一个窗口");

				f1.setSize(250, 180);

				f1.setLocation(270, 50);

				f1.setVisible(true);

			}

		});

		

		btn2.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {

				f2 = Singleton2.getInstance();

				

				f2.setTitle("第二个窗口");

				f2.setSize(250, 250);

				f2.setLocation(270, 240);

				f2.setVisible(true);

			}

		});

	}

	

	/**

	 * 静态内部类实现单例模式

	 */

	public static class Singleton1 extends JFrame{

		/**

		 * 在静态内部类中定义单例对象

		 */

		public static class SingletonClass1{

			private static final Singleton1 instance = new Singleton1();

		}

		

		private Singleton1(){

			

		}

		

		public static Singleton1 getInstance(){

			return SingletonClass1.instance;

		}

	}

	

	public static class Singleton2 extends JFrame{

		public static class SingletonClass2{

			private static final Singleton2 instance = new Singleton2();

		}

		

		private Singleton2(){

			

		}

		

		public static Singleton2 getInstance(){

			return SingletonClass2.instance;

		}

	}

	

	public static void main(String[] args) {

		TestSingleton test = new TestSingleton();

	}

}
