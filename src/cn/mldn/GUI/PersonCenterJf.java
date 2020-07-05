package cn.mldn.GUI;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import cn.mldn.Util.PersonalCenterDialog;
import cn.mldn.factory.ServiceFactory;
import cn.mldn.pojo.Userd;
import cn.mldn.service.IUserdService;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 * 用户个人中心界面
 * @author ASUS
 *
 */
@SuppressWarnings("serial")
public class PersonCenterJf extends JFrame {

	private JPanel contentPane;
	private Userd user=new Userd();
	private JTextField textField_passengeName;
	private JTextField textField_IDcard;
	private JTextField textField_useName;
	private JPasswordField passwordField;
	JComboBox<String> comboBox_sex;
	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					PersonJf frame = new PersonJf();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	
	public PersonCenterJf(Userd user,JFrame jf) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				//将主界面启用
				jf.setEnabled(true);
				setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			}
		});
		
		setBounds(100, 100, 450, 300);
		
		setLocationRelativeTo(jf);//定位在父类窗口中间
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.user=user;
		setIconImage(Toolkit.getDefaultToolkit().getImage(PersonalCenterDialog.class.getResource("/source/premium.png")));
		setTitle("\u7528\u6237\u4E2A\u4EBA\u4E2D\u5FC3");
		setBounds(100, 100, 429, 397);
		getContentPane().setLayout(new BorderLayout(0, 0));
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		//对象化面板
		JPanel combop = new JPanel();
		JPanel p1 = new JPanel();
		//JPanel p2 = new JPanel();
		tabbedPane.add(p1,"个人资料");
		p1.setLayout(null);
		
		JLabel label = new JLabel("\u59D3\u540D\uFF1A");
		label.setFont(new Font("华文楷体", Font.PLAIN, 16));
		label.setBounds(25, 10, 69, 33);
		p1.add(label);
		
		JLabel label_1 = new JLabel("\u6027\u522B\uFF1A");
		label_1.setFont(new Font("华文楷体", Font.PLAIN, 16));
		label_1.setBounds(25, 57, 69, 27);
		p1.add(label_1);
		
	
		
		JLabel label_2 = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7\uFF1A");
		label_2.setFont(new Font("华文楷体", Font.PLAIN, 16));
		label_2.setBounds(25, 94, 84, 28);
		p1.add(label_2);
		
		
		JLabel label_3 = new JLabel("\u8D26\u6237\uFF1A");
		label_3.setFont(new Font("华文楷体", Font.PLAIN, 16));
		label_3.setBounds(25, 144, 69, 27);
		p1.add(label_3);
		
		comboBox_sex = new JComboBox<String>();
		comboBox_sex.setEnabled(false);
		comboBox_sex.setModel(new DefaultComboBoxModel<String>(new String[] {"\u7537", "\u5973"}));
		comboBox_sex.setBounds(104, 58, 69, 26);
		p1.add(comboBox_sex);
		
		textField_passengeName = new JTextField();
		textField_passengeName.setEditable(false);
		textField_passengeName.setBounds(104, 11, 100, 33);
		p1.add(textField_passengeName);
		textField_passengeName.setColumns(10);
		
		textField_IDcard = new JTextField();
		textField_IDcard.setEditable(false);
		textField_IDcard.setBounds(104, 94, 143, 33);
		p1.add(textField_IDcard);
		textField_IDcard.setColumns(10);
		
		textField_useName = new JTextField();
		textField_useName.setEditable(false);
		textField_useName.setBounds(104, 142, 112, 33);
		p1.add(textField_useName);
		textField_useName.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setEditable(false);
		passwordField.setBounds(104, 193, 112, 29);
		p1.add(passwordField);
		
		//将用户数据显示到容器中
		setMessage();

		JLabel label_4 = new JLabel("\u5BC6\u7801\uFF1A");
		label_4.setFont(new Font("华文楷体", Font.PLAIN, 16));
		label_4.setBounds(25, 195, 69, 27);
		p1.add(label_4);
		
		//修改按钮添加监听事件
//		JButton button_modifyall = new JButton("\u4FDD\u5B58\u4FEE\u6539");
//		button_modifyall.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				///获取输入的账户信息
//				String name=textField_passengeName.getText();	
//				String sex=(String) comboBox_sex.getSelectedItem();
//				String id=textField_IDcard.getText();
//				//将用户信息重新赋值
//				user.setPasseName(name);
//				user.setSex(sex);
//				user.setIDCard(id);
//				
//				//获取业务层标准对象实例 
//				IUserdService iuse=ServiceFactory.getIUserdServiceInstance();
//				if(iuse.changeMessage(user)){
//					int n=JOptionPane.showConfirmDialog(null, "确定保存资料修改吗？", "资料修改确认",JOptionPane.YES_NO_OPTION);
//					if(n==0){
//						JOptionPane.showMessageDialog(null, "资料修改成功！");
//						
//					}
//				
//				}else{
//					JOptionPane.showMessageDialog(null, "修改失败！");
//				}
//				
//			}
//		});
//		button_modifyall.setFont(new Font("华文楷体", Font.PLAIN, 15));
//		button_modifyall.setBounds(33, 250, 105, 33);
//		p1.add(button_modifyall);
//		
		/*
		 * 修改密码按钮添加监听事件
		 */
		JFrame frame=this;
		JButton button_modifypass = new JButton("\u4FEE\u6539\u5BC6\u7801");
		button_modifypass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyDialog dialog=new MyDialog(frame, "修改密码", true);
				dialog.setModalityType(ModalityType.APPLICATION_MODAL);
				dialog.setVisible(true);
//				JOptionPane.showMessageDialog(null, "账户密码已过期，请重新登录！");
//				LoginFrame login=new LoginFrame();
//				frame.setVisible(false);
//				UserServerJFrame us=new UserServerJFrame(user);
//				login.setVisible(true);
				
			}
		});
		button_modifypass.setFont(new Font("华文楷体", Font.PLAIN, 15));
		button_modifypass.setBounds(229, 191, 112, 33);
		p1.add(button_modifypass);
		//tabbedPane.add(p2,"账户信息");
		combop.add(new JLabel("个人中心管理"));
		getContentPane().add(combop, BorderLayout.NORTH);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
	}
	/*
	 * 获取用户信息进行显示
	 */
	private void setMessage(){
		this.textField_passengeName.setText(user.getPasseName());
		this.comboBox_sex.setSelectedItem(user.getSex());
		this.textField_IDcard.setText(user.getIDCard());
		this.textField_useName.setText(user.getUsername());
		this.passwordField.setText(user.getPassword());
	}
	
	/*
	 * 弹出修改密码对话框
	 * @author ASUS
	 *
	 */
	class MyDialog extends JDialog {
		private final JPanel contentPanel = new JPanel();
		private JPasswordField passwordField;
		private JPasswordField passwordField_1;
		private JPasswordField passwordField_2;

		public MyDialog(JFrame frame,String title,boolean model) {
			super(frame);
			setTitle(title);
			setBounds(100, 100, 300, 221);
			setLocationRelativeTo(frame);//定位在父类窗口中间
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			contentPanel.setLayout(null);
			{
				JLabel label = new JLabel("\u65B0\u5BC6\u7801\uFF1A");
				label.setFont(new Font("楷体", Font.PLAIN, 14));
				label.setBounds(22, 67, 70, 25);
				contentPanel.add(label);
			}
			
			JLabel label = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
			label.setFont(new Font("楷体", Font.PLAIN, 14));
			label.setBounds(22, 102, 70, 25);
			contentPanel.add(label);
			
			JLabel label_1 = new JLabel("\u539F\u5BC6\u7801\uFF1A");
			label_1.setFont(new Font("楷体", Font.PLAIN, 14));
			label_1.setBounds(22, 31, 70, 25);
			contentPanel.add(label_1);
			
			JLabel label_2 = new JLabel("\u7968\u4EF7\uFF1A");
			label_2.setFont(new Font("楷体", Font.PLAIN, 14));
			label_2.setBounds(23, 185, 70, 25);
			contentPanel.add(label_2);
			
			passwordField = new JPasswordField();
			passwordField.setBounds(102, 26, 92, 30);
			contentPanel.add(passwordField);
			
			passwordField_1 = new JPasswordField();
			passwordField_1.setBounds(102, 65, 92, 30);
			contentPanel.add(passwordField_1);
			
			passwordField_2 = new JPasswordField();
			passwordField_2.setBounds(102, 104, 92, 30);
			contentPanel.add(passwordField_2);
			{
				JPanel buttonPane = new JPanel();
				buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
				getContentPane().add(buttonPane, BorderLayout.SOUTH);
				{
					JButton okButton = new JButton("OK");
					//ok按钮添加监听事件
					okButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							IUserdService ius=ServiceFactory.getIUserdServiceInstance();
							String old=passwordField.getText();
							String newp=passwordField_1.getText();
							String okp=passwordField_2.getText();
							if(!ius.isEqual(user, old)){
								JOptionPane.showMessageDialog(null, "原密码错误！");
								passwordField.setText("");
								return;
							}
							if(!(newp.equals(okp))){
								JOptionPane.showMessageDialog(null, "新密码与确认密码不相同，请重试！");
								return;
							}
							user.setPassword(newp);
							if(ius.changeMessage(user)){
								JOptionPane.showMessageDialog(null, "密码修改成功！");
								dispose();
								
								
								//setDefaultCloseOperation(); DO_NOTHING_ON_CLOSE, HIDE_ON_CLOSE, or DISPOSE_ON_CLOSE
								//setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							}
						}
					});
					okButton.setActionCommand("OK");
					buttonPane.add(okButton);
					getRootPane().setDefaultButton(okButton);
				}
				{
					/*
					 * 取消按钮添加监听事件
					 */
					JButton cancelButton = new JButton("Cancel");
					cancelButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							//setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							dispose();
							
						}
					});
					cancelButton.setActionCommand("Cancel");
					buttonPane.add(cancelButton);
				}
			}
			
		}
	}

}
		

