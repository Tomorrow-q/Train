package cn.mldn.GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import cn.mldn.Util.Util;
import cn.mldn.Util.ValidCode;
import cn.mldn.factory.ServiceFactory;
import cn.mldn.pojo.Userd;
import cn.mldn.service.IManageService;
import cn.mldn.service.IUserdService;

/**
 * ϵͳ��¼����
 * @author ASUS
 *
 */
@SuppressWarnings("serial")
public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField T_uname;
	private JPasswordField T_password;
	//JLabel validcode = new JLabel();
	private JTextField jtf_code = new JTextField();
	//�����֤��
	private ValidCode vcode;

	private Userd user=new Userd();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public LoginFrame() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/source/Dg14.png")));
		setTitle("\u7CFB\u7EDF\u767B\u5F55");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//��֤�����
		vcode = new ValidCode();
		vcode.setBounds(42, 88, 94, 34);
		//��֤���ı���
		jtf_code.setBounds(146, 88, 142, 34);
		JDesktopPane contentPane = new JDesktopPane() {
			@Override
			public void paintComponent(Graphics g) {//�ػ���屳��
				//����һ��δ��ʼ����ͼ��ͼ�꣬�ο�API
				ImageIcon icon=new ImageIcon("src"+File.separator+"source"+File.separator+"bj4.jpg");
				//����ָ��ͼ���������ŵ��ʺ�ָ�������ڲ���ͼ�񣬲ο�API
				g.drawImage(icon.getImage(), 0, 0, this.getWidth(), this.getHeight(),this);
			}
		};
		
		
		
		//���ù��������ô������
		Util.setFrameCenter(this);
		
		setContentPane(contentPane);
		JPanel panel = new JPanel();
		panel.setBackground(null);
		panel.setOpaque(false);
		panel.setBounds(350, 97, 382, 244);
		panel.add(vcode);
		panel.add(jtf_code);
		panel.setBackground(Color.white);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel uname = new JLabel("\u7528\u6237\u540D\uFF1A");
		uname.setFont(new Font("���Ŀ���", Font.BOLD, 18));
		uname.setBounds(52, 10, 84, 30);
		panel.add(uname);
		
		JLabel uword = new JLabel("\u5BC6\u7801\uFF1A");
		uword.setFont(new Font("���Ŀ���", Font.BOLD, 18));
		uword.setBounds(52, 42, 84, 30);
		panel.add(uword);
		
		T_uname = new JTextField();
		T_uname.setBounds(146, 11, 142, 29);
		panel.add(T_uname);
		T_uname.setColumns(10);
		
		/*
		 * ��¼��ť���ü����¼�
		 */
		JButton Button_login = new JButton("\u767B\u5F55");
		Button_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//����ж��Ƿ��¼�ɹ�
				boolean flag=false;
				//��ȡ������е��û���������
				String in_name=T_uname.getText();
				String in_word=T_password.getText();
				
				
				if (!isValidCodeRight()) {
					JOptionPane.showMessageDialog(LoginFrame.this, "��֤�����");
					jtf_code.setText("");
					jtf_code.requestFocus();
				}else{
					
				
				if(in_name.length()==0){
					JOptionPane.showMessageDialog(null, "�û�������Ϊ��");
					T_password.setText("");
					T_uname.requestFocus();
					return;
				}else if(in_word.length()==0){
					JOptionPane.showMessageDialog(null, "���벻��Ϊ��");
					T_password.requestFocus();
					return;
				}
				
				//IUserdDao used	=DAOFactory.getIUserdDaoInstance();
				//ͨ��ҵ��㹤����ȡҵ���ʵ��������ȡ��½�ķ���
				IUserdService useS=ServiceFactory.getIUserdServiceInstance();
				flag=useS.login(in_name, in_word,user);
					if(flag){
						JOptionPane.showMessageDialog(null, "��¼�ɹ���");
						UserServerJFrame ujframe=new UserServerJFrame(user);
						ujframe.setVisible(true);
						dispose();//�ͷŵ�ǰ����
						return;
					}else{
						JOptionPane.showMessageDialog(null, "�˻��������");
						T_uname.setText("");
						T_password.setText("");
						T_uname.requestFocus();//��ȡ���
					}
				
			}}
		});
		Button_login.setBounds(187, 153, 68, 29);
		panel.add(Button_login);
		
		
		/*
		 * ע�ᰴť��Ӽ����¼�
		 */
		JButton btnNewButton = new JButton("\u6CE8\u518C");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//��תע�����
				RegisterFrame rf=new RegisterFrame();
				dispose();  //�ͷŵ�ǰ��Ļ
				rf.setVisible(true);
				
			}
		});
		btnNewButton.setBounds(64, 192, 191, 29);
		panel.add(btnNewButton);
		
		//��ӹ���Ա��¼�����¼�
		JButton btnNewButton_1 = new JButton("\u7BA1\u7406\u5458\u767B\u5F55");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean flag=false;
				//��ȡ������е��û���������
				String pasen_name=T_uname.getText();
				String pasen_word=T_password.getText();
				 if(pasen_name.length()==0){
					JOptionPane.showMessageDialog(null, "�˻�����Ϊ��");
					T_password.setText("");
					T_uname.requestFocus();
					return;
				}else if(pasen_word.length()==0){
					JOptionPane.showMessageDialog(null, "���벻��Ϊ��");
					T_password.requestFocus();
					return;
				}
				
				//��ȡIManageService������Ա��¼��ʵ��(��������ģʽ),������ȡ����Ա��¼����
				IManageService ims=ServiceFactory.getIManageServiceInstance();
					flag=ims.ManageLogin(pasen_name, pasen_word);
					if (!isValidCodeRight()) {
						JOptionPane.showMessageDialog(LoginFrame.this, "��֤�����");
						jtf_code.setText("");
						jtf_code.requestFocus();
						return;
					}else if(flag){
						JOptionPane.showMessageDialog(null, "����Ա��¼�ɹ���");
						ManagerInJFrame manager=new ManagerInJFrame();
						manager.setVisible(true);
						//�ͷŵ�ǰҳ��
						dispose();
					}else{
						JOptionPane.showMessageDialog(null, "����Ա�˻��������");
						T_uname.setText("");
						T_password.setText("");
						T_uname.requestFocus();//��ȡ���
					}
				
			}
		});
		btnNewButton_1.setBounds(58, 153, 104, 29);
		panel.add(btnNewButton_1);
		
		T_password = new JPasswordField();
		T_password.setBounds(146, 45, 142, 26);
		panel.add(T_password);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(LoginFrame.class.getResource("/source/customers.png")));
		lblNewLabel.setBounds(10, 10, 35, 30);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(LoginFrame.class.getResource("/source/lock.png")));
		lblNewLabel_1.setBounds(10, 42, 54, 30);
		panel.add(lblNewLabel_1);
		
		JLabel label_1 = new JLabel("\u533A\u5206\u5927\u5C0F\u5199");
		label_1.setBounds(52, 124, 74, 31);
		panel.add(label_1);
		label_1.setFont(new Font("���Ŀ���", Font.PLAIN, 14));
		
		JLabel label = new JLabel("\u6B22 \u8FCE \u4F7F \u7528 \u706B \u8F66 \u552E \u7968 \u7CFB \u7EDF");
		label.setBackground(Color.GREEN);
		label.setFont(new Font("���Ĳ���", Font.PLAIN, 28));
		label.setForeground(Color.MAGENTA);
		label.setBounds(255, 10, 389, 60);
		contentPane.add(label);
	}
	
	

	 
	/**
	 * ��֤���У��
	 * 
	 * @return
	 */
	public boolean isValidCodeRight() {
 
		if (jtf_code == null) {
			return false;
		}
		if (vcode == null) {
			return true;
		}
		if (vcode.getCode().equals(jtf_code.getText())) {
			return true;
		}
		return false;
	}
 

}
