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
 * �û���Ϣע�����
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
	
	//��������
	private Point pressedPoint;
	//���ص�¼����ķ���
	public void goLogin(){
		//ȡ������¼����
		LoginFrame lf=new LoginFrame();
		dispose();//�ͷŵ�ǰ��Ļ
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
		//���ù����࣬���ô������
		Util.setFrameCenter(this);
		
		/*
		 * ���ô�������϶��¼�
		 */
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e){//��갴���¼�
				pressedPoint=e.getPoint(); //��ȡ�������
			}
		});
		
		/*
		 * �����ק�¼�
		 */
		this.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e){
				//��ȡ��ǰ����
				Point point=e.getPoint();
				Point locationPoint=getLocation();//��ȡ��������
				//�����ƶ����������
				int x=locationPoint.x+point.x-pressedPoint.x;
				int y=locationPoint.y+point.y-pressedPoint.y;
				setLocation(x,y);
			}
			
			
			
		});
		
		
		JDesktopPane contentPane = new JDesktopPane() {
			@Override
			public void paintComponent(Graphics g) {//�ػ���屳��
				//����һ��δ��ʼ����ͼ��ͼ�꣬�ο�API
				ImageIcon icon=new ImageIcon("src"+File.separator+"source"+File.separator+"bj3.jpg");
				//����ָ��ͼ���������ŵ��ʺ�ָ�������ڲ���ͼ�񣬲ο�API
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
		label.setFont(new Font("��������", Font.PLAIN, 15));
		label.setBounds(20, 46, 65, 24);
		panel.add(label);
		
		JLabel lblNewLabel = new JLabel("\u5BC6\u7801\uFF1A");
		lblNewLabel.setFont(new Font("��������", Font.PLAIN, 15));
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
		lblNewLabel_1.setFont(new Font("��Բ", Font.ITALIC, 15));
		lblNewLabel_1.setBounds(50, 10, 118, 26);
		panel.add(lblNewLabel_1);
		
		JLabel passenName = new JLabel("\u59D3\u540D\uFF1A");
		passenName.setFont(new Font("��������", Font.PLAIN, 15));
		passenName.setBounds(21, 118, 54, 31);
		panel.add(passenName);
		
		textField_passename = new JTextField();
		textField_passename.setBounds(100, 118, 101, 30);
		panel.add(textField_passename);
		textField_passename.setColumns(10);
		
		JLabel label_ID = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7\uFF1A");
		label_ID.setFont(new Font("��������", Font.PLAIN, 15));
		label_ID.setBounds(14, 201, 86, 29);
		panel.add(label_ID);
		
		textField_ID = new JTextField();
		textField_ID.setBounds(100, 201, 98, 30);
		panel.add(textField_ID);
		textField_ID.setColumns(10);
		
		JLabel label_sex = new JLabel("\u6027\u522B\uFF1A");
		label_sex.setFont(new Font("��������", Font.PLAIN, 15));
		label_sex.setBounds(21, 163, 54, 26);
		panel.add(label_sex);
		
		//�Ա�ѡ��ť
		JRadioButton RadioButton_male = new JRadioButton("\u7537");
		RadioButton_male.setBounds(84, 166, 43, 23);
		panel.add(RadioButton_male);
		
		JRadioButton RadioButton_female = new JRadioButton("\u5973");
		RadioButton_female.setBounds(143, 166, 43, 23);
		panel.add(RadioButton_female);

		//���Ƶ�ѡ��ť��
		ButtonGroup bgroup=new ButtonGroup();
		bgroup.add(RadioButton_male);
		bgroup.add(RadioButton_female);
		
		
		/*
		 * ȷ�ϰ�ť��Ӽ����¼�
		 */
		JButton btnNewButton = new JButton("\u786E\u8BA4");
		btnNewButton.setContentAreaFilled(false);//���ð�ť͸��
		//����͹��ť
		//btnNewButton.setBorder(BorderFactory.createRaisedBevelBorder());
		//���ð���ť
		//btnNewButton.setBorder(BorderFactory.createLoweredBevelBorder());
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//��ȡ�û�ע����Ϣ
				String uname=text_uname.getText();//�˻���
				String uword=T_password.getText();  //�˻�����
				String passeName=textField_passename.getText();  //�˿�����
				//��ȡ��ѡ��ť���Ա��ֵ
				String psex=(RadioButton_male.isSelected()?RadioButton_male.getText():RadioButton_female.getText());
				//��ȡ���֤��
				String ID=textField_ID.getText(); 
				//���֤����Ϣ�����ж�
				Pattern pattern =Pattern.compile("\\d{18}");
				
				String pwordRegex="\\w{6,12}"; //���룺������ĸ�»���
				if(uname.length()==0){
					JOptionPane.showMessageDialog(null, "�û�������Ϊ��");
					T_password.setText("");
					text_uname.requestFocus();
					return;
				}else if(uword.length()==0){
					JOptionPane.showMessageDialog(null, "���벻��Ϊ��");
					T_password.requestFocus();
					return;
				}else if(!uword.matches(pwordRegex)){
					JOptionPane.showMessageDialog(null, "����ӦΪ6-12�����֡���ĸ���»���");
				//	text_uname.setText("");
					T_password.setText("");
					T_password.requestFocus();
					return;
				}else if(passeName.length()==0){
					JOptionPane.showMessageDialog(null, "����û������������");
					textField_passename.requestFocus();
					return;
				}else if(psex==null){
					JOptionPane.showMessageDialog(null, "����û��ѡ���Ա�");
					//textField_passename.requestFocus();
					return;
				}else if(ID.length()==0){
					JOptionPane.showMessageDialog(null, "���������֤�ţ�");
					textField_ID.requestFocus();
					return;
				}else if(pattern.matcher(psex).matches()){
					JOptionPane.showMessageDialog(null, "���֤�ű���Ϊ18λ�����֣�");
					textField_ID.setText("");
					textField_ID.requestFocus();
					return;
				}
				
				//��װ���û�����
				Userd user=new Userd();
				user.setUsername(uname);
				user.setPassword(uword);
				user.setPasseName(passeName);
				user.setSex(psex);
				user.setIDCard(ID);
				
				//��ȡҵ���ʵ��������Ա���ҵ���������ݲ�DAO��ע�᷽��������Ϣע��
				IUserdService iuService=ServiceFactory.getIUserdServiceInstance();
				//IUserdDao useD=DAOFactory.getIUserdDaoInstance();
					try {
						flag=iuService.register(user);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					if(flag){
						JOptionPane.showMessageDialog(null, "�û�ע��ɹ�");
						goLogin();//���ص�¼����
						return;
						
					}else{
						JOptionPane.showMessageDialog(null, "���û����Ѵ��ڣ����������룡");
						text_uname.setText("");
						T_password.setText("");
						text_uname.requestFocus();//��ȡ���
					}
				
			}
		});
		btnNewButton.setFont(new Font("��������", Font.PLAIN, 18));
		btnNewButton.setBounds(20, 237, 85, 35);
		panel.add(btnNewButton);
		
		/*
		 * �����ð�ť��Ӽ����¼�
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
		btnNewButton_1.setFont(new Font("��������", Font.PLAIN, 18));
		btnNewButton_1.setBounds(115, 237, 86, 35);
		panel.add(btnNewButton_1);
		
		/**
		 * ���ذ�ť��Ӽ����¼�
		 */
		JButton button = new JButton("\u8FD4\u56DE");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goLogin();
			}
		});
		button.setFont(new Font("����", Font.PLAIN, 18));
		button.setBounds(21, 10, 87, 32);
		contentPane.add(button);
		
		
	}
}
