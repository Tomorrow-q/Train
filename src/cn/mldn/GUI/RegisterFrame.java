package cn.mldn.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import cn.mldn.Util.Util;
import cn.mldn.dao.IUserdDao;
import cn.mldn.factory.DAOFactory;
import cn.mldn.factory.ServiceFactory;
import cn.mldn.pojo.Userd;
import cn.mldn.service.IUserdService;
/**
 * 用户信息注册界面
 * @author ASUS
 *
 */
@SuppressWarnings("serial")
public class RegisterFrame extends JFrame {

	private JPanel contentPane;
	private JTextField text_uname;
	private JPasswordField T_password;
	boolean flag=false;
	private JTextField textField_passename;
	private JTextField textField_ID;
	
	//窗体坐标
	private Point pressedPoint;
	//跳回登录界面的方法
	public void goLogin(){
		//取消到登录界面
		LoginFrame lf=new LoginFrame();
		dispose();//释放当前屏幕
		lf.setVisible(true);
			
	}
	
	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					RegisterFrame frame = new RegisterFrame();
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
	public RegisterFrame() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegisterFrame.class.getResource("/source/cttb.png")));
		setTitle("\u7528\u6237\u6CE8\u518C\u754C\u9762");
	//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 487, 364);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//调用工具类，设置窗体居中
		Util.setFrameCenter(this);
		
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
		
		
		JDesktopPane contentPane = new JDesktopPane() {
			@Override
			public void paintComponent(Graphics g) {//重绘面板背景
				//创建一个未初始化的图像图标，参考API
				ImageIcon icon=new ImageIcon("src"+File.separator+"source"+File.separator+"bj3.jpg");
				//绘制指定图像中已缩放到适合指定矩形内部的图像，参考API
				g.drawImage(icon.getImage(), 0, 0, this.getWidth(), this.getHeight(),this);
			}
		};
		
		setContentPane(contentPane);
		JPanel panel = new JPanel();
		panel.setBounds(223, 29, 211, 282);
		panel.setBackground(Color.pink);
		contentPane.add(panel);
		panel.setLayout(null); 
		
		JLabel label = new JLabel("\u7528\u6237\u540D\uFF1A");
		label.setFont(new Font("方正舒体", Font.PLAIN, 15));
		label.setBounds(20, 46, 65, 24);
		panel.add(label);
		
		JLabel lblNewLabel = new JLabel("\u5BC6\u7801\uFF1A");
		lblNewLabel.setFont(new Font("方正舒体", Font.PLAIN, 15));
		lblNewLabel.setBounds(20, 86, 54, 24);
		panel.add(lblNewLabel);
		
		text_uname = new JTextField();
		text_uname.setBounds(100, 43, 101, 30);
		panel.add(text_uname);
		text_uname.setColumns(10);
		
		T_password = new JPasswordField();
		T_password.setBounds(100, 80, 101, 30);
		panel.add(T_password);

		JLabel lblNewLabel_1 = new JLabel("  \u7528 \u6237 \u6CE8 \u518C");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("幼圆", Font.ITALIC, 15));
		lblNewLabel_1.setBounds(50, 10, 118, 26);
		panel.add(lblNewLabel_1);
		
		JLabel passenName = new JLabel("\u59D3\u540D\uFF1A");
		passenName.setFont(new Font("方正舒体", Font.PLAIN, 15));
		passenName.setBounds(21, 118, 54, 31);
		panel.add(passenName);
		
		textField_passename = new JTextField();
		textField_passename.setBounds(100, 118, 101, 30);
		panel.add(textField_passename);
		textField_passename.setColumns(10);
		
		JLabel label_ID = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7\uFF1A");
		label_ID.setFont(new Font("方正舒体", Font.PLAIN, 15));
		label_ID.setBounds(14, 201, 86, 29);
		panel.add(label_ID);
		
		textField_ID = new JTextField();
		textField_ID.setBounds(100, 201, 98, 30);
		panel.add(textField_ID);
		textField_ID.setColumns(10);
		
		JLabel label_sex = new JLabel("\u6027\u522B\uFF1A");
		label_sex.setFont(new Font("方正舒体", Font.PLAIN, 15));
		label_sex.setBounds(21, 163, 54, 26);
		panel.add(label_sex);
		
		//性别单选按钮
		JRadioButton RadioButton_male = new JRadioButton("\u7537");
		RadioButton_male.setBounds(84, 166, 43, 23);
		panel.add(RadioButton_male);
		
		JRadioButton RadioButton_female = new JRadioButton("\u5973");
		RadioButton_female.setBounds(143, 166, 43, 23);
		panel.add(RadioButton_female);

		//控制单选按钮组
		ButtonGroup bgroup=new ButtonGroup();
		bgroup.add(RadioButton_male);
		bgroup.add(RadioButton_female);
		
		
		/*
		 * 确认按钮添加监听事件
		 */
		JButton btnNewButton = new JButton("\u786E\u8BA4");
		btnNewButton.setContentAreaFilled(false);//设置按钮透明
		//设置凸按钮
		//btnNewButton.setBorder(BorderFactory.createRaisedBevelBorder());
		//设置凹按钮
		//btnNewButton.setBorder(BorderFactory.createLoweredBevelBorder());
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//获取用户注册信息
				String uname=text_uname.getText();//账户名
				String uword=T_password.getText();  //账户密码
				String passeName=textField_passename.getText();  //乘客姓名
				//获取单选按钮的性别的值
				String psex=(RadioButton_male.isSelected()?RadioButton_male.getText():RadioButton_female.getText());
				//获取身份证号
				String ID=textField_ID.getText(); 
				//身份证号信息正则判断
				Pattern pattern =Pattern.compile("\\d{18}");
				
				String pwordRegex="\\w{6,12}"; //密码：数字字母下划线
				if(uname.length()==0){
					JOptionPane.showMessageDialog(null, "用户名不能为空");
					T_password.setText("");
					text_uname.requestFocus();
					return;
				}else if(uword.length()==0){
					JOptionPane.showMessageDialog(null, "密码不能为空");
					T_password.requestFocus();
					return;
				}else if(!uword.matches(pwordRegex)){
					JOptionPane.showMessageDialog(null, "密码应为6-12个数字、字母、下划线");
				//	text_uname.setText("");
					T_password.setText("");
					T_password.requestFocus();
					return;
				}else if(passeName.length()==0){
					JOptionPane.showMessageDialog(null, "您还没有输入姓名！");
					textField_passename.requestFocus();
					return;
				}else if(psex==null){
					JOptionPane.showMessageDialog(null, "您还没有选择性别！");
					//textField_passename.requestFocus();
					return;
				}else if(ID.length()==0){
					JOptionPane.showMessageDialog(null, "请输入身份证号！");
					textField_ID.requestFocus();
					return;
				}else if(pattern.matcher(psex).matches()){
					JOptionPane.showMessageDialog(null, "身份证号必须为18位纯数字！");
					textField_ID.setText("");
					textField_ID.requestFocus();
					return;
				}
				
				//封装成用户对象
				Userd user=new Userd();
				user.setUsername(uname);
				user.setPassword(uword);
				user.setPasseName(passeName);
				user.setSex(psex);
				user.setIDCard(ID);
				
				//获取业务层实现类对象，以便于业务层调用数据层DAO的注册方法进行信息注册
				IUserdService iuService=ServiceFactory.getIUserdServiceInstance();
				//IUserdDao useD=DAOFactory.getIUserdDaoInstance();
					try {
						flag=iuService.register(user);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					if(flag){
						JOptionPane.showMessageDialog(null, "用户注册成功");
						goLogin();//跳回登录界面
						return;
						
					}else{
						JOptionPane.showMessageDialog(null, "该用户名已存在，请重新输入！");
						text_uname.setText("");
						T_password.setText("");
						text_uname.requestFocus();//获取光标
					}
				
			}
		});
		btnNewButton.setFont(new Font("方正舒体", Font.PLAIN, 18));
		btnNewButton.setBounds(20, 237, 85, 35);
		panel.add(btnNewButton);
		
		/*
		 * 给重置按钮添加监听事件
		 */
		JButton btnNewButton_1 = new JButton("\u91CD\u7F6E");
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text_uname.setText("");
				T_password.setText("");
				text_uname.requestFocus();
			}
		});
		btnNewButton_1.setFont(new Font("方正舒体", Font.PLAIN, 18));
		btnNewButton_1.setBounds(115, 237, 86, 35);
		panel.add(btnNewButton_1);
		
		/**
		 * 返回按钮添加监听事件
		 */
		JButton button = new JButton("\u8FD4\u56DE");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goLogin();
			}
		});
		button.setFont(new Font("楷体", Font.PLAIN, 18));
		button.setBounds(21, 10, 87, 32);
		contentPane.add(button);
		
		
	}
}
