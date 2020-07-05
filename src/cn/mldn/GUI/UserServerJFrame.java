package cn.mldn.GUI;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.beans.PropertyVetoException;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import cn.mldn.Util.Util;
import cn.mldn.factory.ServiceFactory;
import cn.mldn.pojo.TrainNumber;
import cn.mldn.pojo.Userd;
import cn.mldn.service.ITrainNumberService;
import cn.mldn.service.IUserOrderService;
import cn.mldn.service.IUserdService;

import java.awt.Toolkit;

/**
 * 这是普通用户进入系统后的主界面操作
 * @author ASUS
 *
 */
@SuppressWarnings("serial")
public class UserServerJFrame extends JFrame {

	//private JPanel contentPane;
	private JMenuItem menuItem;
	private JTextField textField_startP;
	private JTextField textField_destP;
	private JTable table;
	JDesktopPane contentPane;
	
	private Userd user;
	//表格模型
	private DefaultTableModel listRecords = null; //类变量
	//表头
	private String []headings={"\u8F66\u6B21", "\u59CB\u53D1\u5730", "\u76EE\u7684\u5730", "\u51FA\u53D1\u65F6\u95F4", "\u5269\u4F59\u8F66\u7968", "\u4EF7\u683C"};
	
	//窗体坐标
	private Point pressedPoint;
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserServerJFrame frame = new UserServerJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	
	//传入用户对象
	public UserServerJFrame(Userd user) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(UserServerJFrame.class.getResource("/source/000.png")));
		
		setTitle("                                                                      \u6B22 \u8FCE \u6765 \u5230 \u706B \u8F66 \u552E \u7968 \u7CFB \u7EDF");
	//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置窗体关闭按钮失效，以便于使用菜单栏的退出按钮
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		//contentPane = new JPanel();
		this.user=user;
		
		
		/*
		 * 设置窗体鼠标拖动事件
		 */
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e){//鼠标按下事件
				pressedPoint=e.getPoint(); //获取鼠标坐标
			}
		});
		
		/*
		 * 鼠标拖拽事件
		 */
		this.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e){
				//获取当前坐标
				Point point=e.getPoint();
				Point locationPoint=getLocation();//获取窗体坐标
				//计算移动后的新坐标
				int x=locationPoint.x+point.x-pressedPoint.x;
				int y=locationPoint.y+point.y-pressedPoint.y;
				setLocation(x,y);
			}
			
			
			
		});
		
		
		
		contentPane = new JDesktopPane(){
			@Override
			public void paintComponent(Graphics g){//重绘面板背景
				//创建一个未初始化的图像图标，参考API
				ImageIcon icon=new ImageIcon("src"+File.separator+"source"+File.separator+"00.jpg");
				//绘制指定图像中已缩放到适合指定矩形内部的图像，参考API
				g.drawImage(icon.getImage(), 0, 0, this.getWidth(), this.getHeight(),this);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u6B22\u8FCE\u4F7F\u7528\u4FCA\u70CA\u706B\u8F66\u552E\u7968\u7CFB\u7EDF");
		label.setForeground(Color.MAGENTA);
		label.setFont(new Font("方正姚体", Font.PLAIN, 24));
		label.setBounds(273, 10, 312, 28);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u59CB\u53D1\u5730\uFF1A");
		label_1.setFont(new Font("方正姚体", Font.PLAIN, 18));
		label_1.setBounds(61, 67, 72, 26);
		contentPane.add(label_1);
		
		textField_startP = new JTextField();
		textField_startP.setBounds(143, 70, 80, 27);
		textField_startP.setColumns(10);
		contentPane.add(textField_startP);
		
		
		JLabel label_2 = new JLabel("\u76EE\u7684\u5730\uFF1A");
		label_2.setFont(new Font("方正姚体", Font.PLAIN, 18));
		label_2.setBounds(61, 107, 72, 26);
		contentPane.add(label_2);
		
		textField_destP = new JTextField();
		textField_destP.setColumns(10);
		textField_destP.setBounds(143, 110, 80, 27);
		contentPane.add(textField_destP);
		table = new JTable();
		table.setRowHeight(25);
		//初始化表格模型，headings是标题的数组
		listRecords= new DefaultTableModel(null, this.headings);
			
		//查询按钮添加监听事件
		JButton Button_Find = new JButton("\u67E5\u8BE2");
		Button_Find.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<TrainNumber> trainList=new ArrayList<TrainNumber>();
				//通过工厂方法取得业务层实例
				ITrainNumberService trainser=ServiceFactory.getITrainNumberServiceInstance();
				if(textField_startP.getText().equals("")||textField_destP.getText().equals("")){
					JOptionPane.showMessageDialog(null, "请输入始发地和目的地！");
					return;
				}
				
				//取得文本框的内容，始发地和目的地进行查找
				String start=textField_startP.getText();
				String dest=textField_destP.getText();
			
				try {
					trainList=trainser.selectUser(start, dest);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			//如果集合的长度>0,则说明查到了数据
			if(trainList.size()>0&&trainList!=null){
				table.removeAll();
				for(TrainNumber train:trainList){
					//创建向量类对象，可实现自动增长的对象数组
					@SuppressWarnings("rawtypes")
					Vector<Comparable> v=new Vector<Comparable> ();
					v.add(train.getTrainNum());
					v.add(train.getStartPlace());
					v.add(train.getDestination());
					v.add(train.getGoTime());
					v.add(train.getRemainTicket());
					v.add(train.getPrice());

					//将增加的一行数据插入表格，实现动态插入
					
					listRecords.addRow(v);
					
					textField_startP.setText("");
					textField_destP.setText("");
				}

				
			}else{
				JOptionPane.showMessageDialog(null, "很抱歉，没有此路线车次！");
				return;
			}
				
			}
		});
		table.setModel(listRecords);
		Button_Find.setFont(new Font("华文楷体", Font.PLAIN, 18));
		Button_Find.setBounds(53, 158, 80, 36);
		contentPane.add(Button_Find);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(253, 81, 521, 239);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(table);
	
		JLabel label_3 = new JLabel("     \u67E5 \u8BE2 \u7ED3 \u679C");
		label_3.setForeground(new Color(220, 20, 60));
		label_3.setFont(new Font("楷体", Font.PLAIN, 17));
		label_3.setBounds(460, 48, 159, 26);
		contentPane.add(label_3);
		
		//购票监听事件 
		JButton button = new JButton("\u8D2D\u7968");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//在购票之前判断表格是否有选中项
				if(table.getSelectedRow()==-1){
					JOptionPane.showMessageDialog(null, "您还没有选择车票，请选择后在购买哦！");
					return;
				}
					//获取进入系统的对象
					int result = JOptionPane.showConfirmDialog(
							null,
	                        "确定购买此票吗？",
	                        "确认提示",
	                        JOptionPane.YES_NO_CANCEL_OPTION
	                );
	               
					if(result==0){
		            	int coun=table.getSelectedRow();//获取选中的行号
		   				String railNum=(String)table.getValueAt(coun, 0);
		   				Integer remain=(Integer) table.getValueAt(coun, 4);

		   			//调用业务层工厂类取得用户订购的实例对象，用来调用购票方法
		   				IUserOrderService uos=ServiceFactory.getIUserOrderServiceInstance();
		   				
		   				try {
						boolean flag=false;
						//传入当前进入系统的用户对象和购买的车次号
						flag=uos.buy(user, railNum,remain);
							if(flag){
								JOptionPane.showMessageDialog(null, "购票成功");
							}else{
								JOptionPane.showMessageDialog(null, "您已购买了该车次的车票了，不可以重复购买哦！");
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
		   				
		               }
				
			

			}
		});
		button.setFont(new Font("华文楷体", Font.PLAIN, 18));
		button.setBounds(53, 216, 80, 36);
		contentPane.add(button);
		
		/*
		 * 对退票按钮添加监听事件
		 */
		JButton button_1 = new JButton("\u9000\u7968");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//弹出退票对话框,返回输入的车次号
				String ticketNum=JOptionPane.showInputDialog(UserServerJFrame.this,"请输入退票车次号：\n","退票窗口",JOptionPane.PLAIN_MESSAGE);
				//使用业务层工厂获取用户对象，调用用户退票功能
				IUserOrderService iuseOrder=ServiceFactory.getIUserOrderServiceInstance();
				try {
					if(iuseOrder.returnTic(ticketNum, user.getIDCard())){
						JOptionPane.showMessageDialog(null, "退票成功！");
					}else{
						JOptionPane.showMessageDialog(null, "您还没有购买此车次的车票哦！");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
				
			}
		});
		button_1.setFont(new Font("华文楷体", Font.PLAIN, 18));
		button_1.setBounds(143, 216, 80, 36);
		contentPane.add(button_1);
		
		/*
		 * 对订单记录添加监听事件
		 */
		JButton button_2 = new JButton("\u6211\u7684\u8BA2\u5355\u8BB0\u5F55");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserOrdeDialog uojDialog=new UserOrdeDialog(user);
				// 设置模式类型。
				uojDialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);    
				uojDialog.setVisible(true);
				
			}
		});
		button_2.setFont(new Font("华文楷体", Font.PLAIN, 15));
		button_2.setBounds(53, 263, 146, 36);
		contentPane.add(button_2);

		//窗体居中
		Util.setFrameCenter(this);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("\u7528\u6237\u76F8\u5173");
		menu.setIcon(new ImageIcon(UserServerJFrame.class.getResource("/source/user.png")));
		
		menuBar.add(menu);
		
		/*
		 * 用户个人信息管理 按钮添加监听事件
		 */
		JFrame jf=this;
		menuItem = new JMenuItem("\u4E2A\u4EBA\u4E2D\u5FC3\u7BA1\u7406");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//跳转个人中心管理界面
				PersonCenterJf pjf=new PersonCenterJf(user,jf);
				//将主界面设为禁用
				jf.setEnabled(false);
				//跳转界面设置显示
				pjf.setVisible(true);
				
			}
		});
		menu.add(menuItem);
		
		/*
		 * 用户退出系统监听事件
		 */
		JMenuItem menuItem_2 = new JMenuItem("\u9000\u51FA\u7CFB\u7EDF");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n=JOptionPane.showConfirmDialog(null, "您确认退出系统吗？","确认退出",JOptionPane.YES_NO_OPTION);
				if(n==0){//返回为0则表示确认
					LoginFrame login=new LoginFrame();
					login.setVisible(true);
					dispose();
				}
			}
		});
		menu.add(menuItem_2);
		
		/*
		 * 注销账户按钮添加监听事件
		 */
		JMenuItem menuItem_3 = new JMenuItem("\u6CE8\u9500\u8D26\u6237");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//获取业务层用户操作标准实例对象
				IUserdService iuseSer=ServiceFactory.getIUserdServiceInstance();
				int n=JOptionPane.showConfirmDialog(null, "一旦注销无法更改，确定注销该账户吗？","注销账户",JOptionPane.YES_NO_OPTION);
				if(n==0){
					if(iuseSer.closeAccount(user)){
						System.out.println();
						JOptionPane.showMessageDialog(null, "注销成功，即将退出！");
						//退回到登录界面
						LoginFrame login=new LoginFrame();
						login.setVisible(true);
						dispose();
					}
				}
				
			}
		});
		menu.add(menuItem_3);
		
		/*
		 * 帮助菜单添加事件
		 */
		JMenu menu_1 = new JMenu("\u4F7F\u7528\u5E2E\u52A9");
		menu_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		menu_1.setIcon(new ImageIcon(UserServerJFrame.class.getResource("/source/small1.jpg")));
		menuBar.add(menu_1);
		
		/*
		 * 帮助菜单添加监听事件
		 */
		JMenuItem menuItem_1 = new JMenuItem("\u4F7F\u7528\u76F8\u5173");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, 
					"1.主界面中输入信息：始发地和目的地点，击“查询” 按钮即可显示车次信息\n"
					+ "2.购买：在表格中选择自己想要的车次，点击“购买” 按钮即可\n"
					+ "3.菜单栏中点击“用户相关”->个人中心管理，可看到自己的账户信息\n"
					+ "4. 点击“订单记录”按钮，可查看自己所购买的票\n"		
				);
				
			}
		});
		menu_1.add(menuItem_1);

		
		

	}
}
