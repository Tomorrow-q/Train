package cn.mldn.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import cn.mldn.factory.ServiceFactory;
import cn.mldn.pojo.UserOrder;
import cn.mldn.pojo.Userd;
import cn.mldn.service.IUserOrderService;
/**
 * 这是用户订单管理界面
 * @author ASUS
 *
 */
@SuppressWarnings("serial")
public class UserOrdeDialog extends JDialog {
	private JPanel contentPane;
	private JTable table;
	//JTable的模式
	private DefaultTableModel model; 
	private Userd userd=new Userd();
	//private JTable table_1;
	JScrollPane scrollPane;
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UserOrderJframe frame = new UserOrderJframe();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/*
	 * Create the frame.
	 */
	public UserOrdeDialog(Userd user) {
		this.userd=user;
		setIconImage(Toolkit.getDefaultToolkit().getImage(UserOrdeDialog.class.getResource("/source/Dg12.png")));
		setTitle("\u6211\u7684\u8BA2\u5355");
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 581, 397);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		//创建表
		table = new JTable();
		model=new DefaultTableModel(
			new Object[][]{
					
			},
			new String[]{
				"姓名","身份证号","车次号","始发地","目的地"
			}
		);
		
		//查看按钮添加监听事件
		JButton button = new JButton("\u70B9\u51FB\u67E5\u770B\u8BA2\u5355");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//每查询一次时，清空上一次数据
				((DefaultTableModel) table.getModel()).getDataVector().clear();
				
				//存储用户订单返回的结果
				List<UserOrder> useList=new ArrayList<UserOrder>();
				//通过业务层工厂获取业务层操作对象
				IUserOrderService iuser= ServiceFactory.getIUserOrderServiceInstance();
				try {
					useList=iuser.findUseOrder(user.getIDCard());
					for(UserOrder order:useList){
						Vector<String> v=new Vector<String> ();
						v.add(order.getPassengName());
						v.add(order.getIDCard());
						v.add(order.getTrainName());
						v.add(order.getStartPlace());
						v.add(order.getDestPlace());
						model.addRow(v);
						
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		table.setModel(model);
		contentPane.add(button, BorderLayout.NORTH);
		
		//设置表格行高
		table.setRowHeight(25);
		//设置表格列宽
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(40);
		table.getColumnModel().getColumn(3).setPreferredWidth(60);
		table.getColumnModel().getColumn(4).setPreferredWidth(60);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(table);
		
		
		/*
		  * 对“我的订票记录”按钮，添加监听事件显示所有订单记录
		  */
//		JButton button = new JButton("\u6211\u7684\u8BA2\u7968\u8BB0\u5F55");
//		button.addActionListener(new ActionListener() {
//			@SuppressWarnings("unchecked")
//			public void actionPerformed(ActionEvent e) {
//				//每查询一次时，清空上一次数据
//				((DefaultTableModel) table.getModel()).getDataVector().clear();
//				
//				//存储用户订单返回的结果
//				List<UserOrder> useList=new ArrayList<UserOrder>();
//				//通过业务层工厂获取业务层操作对象
//				IUserOrderService iuser= ServiceFactory.getIUserOrderServiceInstance();
//				try {
//					useList=iuser.findUseOrder(user.getIDCard());
//					for(UserOrder order:useList){
//						Vector<String> v=new Vector<String> ();
//						v.add(order.getPassengName());
//						v.add(order.getIDCard());
//						v.add(order.getTrainName());
//						v.add(order.getStartPlace());
//						v.add(order.getDestPlace());
//						model.addRow(v);
//						
//					}
//				} catch (SQLException e1) {
//					e1.printStackTrace();
//				}
//
//			}
//		});
//		
//		table.setModel(model);
//		button.setBounds(162, 22, 119, 27);
//		contentPane.add(button);
		
//		//滚动条
//		JScrollPane scrollPane = new JScrollPane();
//		scrollPane.setBounds(27, 77, 516, 266);
//		contentPane.add(scrollPane);
//
//		//设置表格行高
//		table.setRowHeight(25);
//
//		//设置表格列宽
//		table.getColumnModel().getColumn(0).setPreferredWidth(40);
//		table.getColumnModel().getColumn(1).setPreferredWidth(100);
//		table.getColumnModel().getColumn(2).setPreferredWidth(40);
//		table.getColumnModel().getColumn(3).setPreferredWidth(60);
//		table.getColumnModel().getColumn(4).setPreferredWidth(60);
//		
//		//对表格加滚动条
//		scrollPane.setViewportView(table);
		
//		JLabel label = new JLabel("<--\u70B9\u51FB\u67E5\u770B");
//		label.setFont(new Font("华文楷体", Font.PLAIN, 15));
//		label.setBounds(300, 22, 99, 27);
//		contentPane.add(label);
	}
	

	
}

