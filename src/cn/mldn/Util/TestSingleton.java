package cn.mldn.Util;


import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.*;

public class TestSingleton {

	private JFrame frame = new JFrame("����");

	private JPanel p = new JPanel();

	private JButton btn1 = new JButton("������һ������");

	private JButton btn2 = new JButton("�����ڶ�������");

	

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

		 * ����ť����¼�������

		 */

		btn1.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {

				f1 = Singleton1.getInstance();

				

				f1.setTitle("��һ������");

				f1.setSize(250, 180);

				f1.setLocation(270, 50);

				f1.setVisible(true);

			}

		});

		

		btn2.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {

				f2 = Singleton2.getInstance();

				

				f2.setTitle("�ڶ�������");

				f2.setSize(250, 250);

				f2.setLocation(270, 240);

				f2.setVisible(true);

			}

		});

	}

	

	/**

	 * ��̬�ڲ���ʵ�ֵ���ģʽ

	 */

	public static class Singleton1 extends JFrame{

		/**

		 * �ھ�̬�ڲ����ж��嵥������

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
