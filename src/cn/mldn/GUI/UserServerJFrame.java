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
 * ������ͨ�û�����ϵͳ������������
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
	//���ģ��
	private DefaultTableModel listRecords = null; //�����
	//��ͷ
	private String []headings={"\u8F66\u6B21", "\u59CB\u53D1\u5730", "\u76EE\u7684\u5730", "\u51FA\u53D1\u65F6\u95F4", "\u5269\u4F59\u8F66\u7968", "\u4EF7\u683C"};
	
	//��������
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

	
	//�����û�����
	public UserServerJFrame(Userd user) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(UserServerJFrame.class.getResource("/source/000.png")));
		
		setTitle("                                                                      \u6B22 \u8FCE \u6765 \u5230 \u706B \u8F66 \u552E \u7968 \u7CFB \u7EDF");
	//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//���ô���رհ�ťʧЧ���Ա���ʹ�ò˵������˳���ť
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		//contentPane = new JPanel();
		this.user=user;
		
		
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
		
		
		
		contentPane = new JDesktopPane(){
			@Override
			public void paintComponent(Graphics g){//�ػ���屳��
				//����һ��δ��ʼ����ͼ��ͼ�꣬�ο�API
				ImageIcon icon=new ImageIcon("src"+File.separator+"source"+File.separator+"00.jpg");
				//����ָ��ͼ���������ŵ��ʺ�ָ�������ڲ���ͼ�񣬲ο�API
				g.drawImage(icon.getImage(), 0, 0, this.getWidth(), this.getHeight(),this);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u6B22\u8FCE\u4F7F\u7528\u4FCA\u70CA\u706B\u8F66\u552E\u7968\u7CFB\u7EDF");
		label.setForeground(Color.MAGENTA);
		label.setFont(new Font("����Ҧ��", Font.PLAIN, 24));
		label.setBounds(273, 10, 312, 28);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u59CB\u53D1\u5730\uFF1A");
		label_1.setFont(new Font("����Ҧ��", Font.PLAIN, 18));
		label_1.setBounds(61, 67, 72, 26);
		contentPane.add(label_1);
		
		textField_startP = new JTextField();
		textField_startP.setBounds(143, 70, 80, 27);
		textField_startP.setColumns(10);
		contentPane.add(textField_startP);
		
		
		JLabel label_2 = new JLabel("\u76EE\u7684\u5730\uFF1A");
		label_2.setFont(new Font("����Ҧ��", Font.PLAIN, 18));
		label_2.setBounds(61, 107, 72, 26);
		contentPane.add(label_2);
		
		textField_destP = new JTextField();
		textField_destP.setColumns(10);
		textField_destP.setBounds(143, 110, 80, 27);
		contentPane.add(textField_destP);
		table = new JTable();
		table.setRowHeight(25);
		//��ʼ�����ģ�ͣ�headings�Ǳ��������
		listRecords= new DefaultTableModel(null, this.headings);
			
		//��ѯ��ť��Ӽ����¼�
		JButton Button_Find = new JButton("\u67E5\u8BE2");
		Button_Find.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<TrainNumber> trainList=new ArrayList<TrainNumber>();
				//ͨ����������ȡ��ҵ���ʵ��
				ITrainNumberService trainser=ServiceFactory.getITrainNumberServiceInstance();
				if(textField_startP.getText().equals("")||textField_destP.getText().equals("")){
					JOptionPane.showMessageDialog(null, "������ʼ���غ�Ŀ�ĵأ�");
					return;
				}
				
				//ȡ���ı�������ݣ�ʼ���غ�Ŀ�ĵؽ��в���
				String start=textField_startP.getText();
				String dest=textField_destP.getText();
			
				try {
					trainList=trainser.selectUser(start, dest);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			//������ϵĳ���>0,��˵���鵽������
			if(trainList.size()>0&&trainList!=null){
				table.removeAll();
				for(TrainNumber train:trainList){
					//������������󣬿�ʵ���Զ������Ķ�������
					@SuppressWarnings("rawtypes")
					Vector<Comparable> v=new Vector<Comparable> ();
					v.add(train.getTrainNum());
					v.add(train.getStartPlace());
					v.add(train.getDestination());
					v.add(train.getGoTime());
					v.add(train.getRemainTicket());
					v.add(train.getPrice());

					//�����ӵ�һ�����ݲ�����ʵ�ֶ�̬����
					
					listRecords.addRow(v);
					
					textField_startP.setText("");
					textField_destP.setText("");
				}

				
			}else{
				JOptionPane.showMessageDialog(null, "�ܱ�Ǹ��û�д�·�߳��Σ�");
				return;
			}
				
			}
		});
		table.setModel(listRecords);
		Button_Find.setFont(new Font("���Ŀ���", Font.PLAIN, 18));
		Button_Find.setBounds(53, 158, 80, 36);
		contentPane.add(Button_Find);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(253, 81, 521, 239);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(table);
	
		JLabel label_3 = new JLabel("     \u67E5 \u8BE2 \u7ED3 \u679C");
		label_3.setForeground(new Color(220, 20, 60));
		label_3.setFont(new Font("����", Font.PLAIN, 17));
		label_3.setBounds(460, 48, 159, 26);
		contentPane.add(label_3);
		
		//��Ʊ�����¼� 
		JButton button = new JButton("\u8D2D\u7968");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//�ڹ�Ʊ֮ǰ�жϱ���Ƿ���ѡ����
				if(table.getSelectedRow()==-1){
					JOptionPane.showMessageDialog(null, "����û��ѡ��Ʊ����ѡ����ڹ���Ŷ��");
					return;
				}
					//��ȡ����ϵͳ�Ķ���
					int result = JOptionPane.showConfirmDialog(
							null,
	                        "ȷ�������Ʊ��",
	                        "ȷ����ʾ",
	                        JOptionPane.YES_NO_CANCEL_OPTION
	                );
	               
					if(result==0){
		            	int coun=table.getSelectedRow();//��ȡѡ�е��к�
		   				String railNum=(String)table.getValueAt(coun, 0);
		   				Integer remain=(Integer) table.getValueAt(coun, 4);

		   			//����ҵ��㹤����ȡ���û�������ʵ�������������ù�Ʊ����
		   				IUserOrderService uos=ServiceFactory.getIUserOrderServiceInstance();
		   				
		   				try {
						boolean flag=false;
						//���뵱ǰ����ϵͳ���û�����͹���ĳ��κ�
						flag=uos.buy(user, railNum,remain);
							if(flag){
								JOptionPane.showMessageDialog(null, "��Ʊ�ɹ�");
							}else{
								JOptionPane.showMessageDialog(null, "���ѹ����˸ó��εĳ�Ʊ�ˣ��������ظ�����Ŷ��");
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
		   				
		               }
				
			

			}
		});
		button.setFont(new Font("���Ŀ���", Font.PLAIN, 18));
		button.setBounds(53, 216, 80, 36);
		contentPane.add(button);
		
		/*
		 * ����Ʊ��ť��Ӽ����¼�
		 */
		JButton button_1 = new JButton("\u9000\u7968");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//������Ʊ�Ի���,��������ĳ��κ�
				String ticketNum=JOptionPane.showInputDialog(UserServerJFrame.this,"��������Ʊ���κţ�\n","��Ʊ����",JOptionPane.PLAIN_MESSAGE);
				//ʹ��ҵ��㹤����ȡ�û����󣬵����û���Ʊ����
				IUserOrderService iuseOrder=ServiceFactory.getIUserOrderServiceInstance();
				try {
					if(iuseOrder.returnTic(ticketNum, user.getIDCard())){
						JOptionPane.showMessageDialog(null, "��Ʊ�ɹ���");
					}else{
						JOptionPane.showMessageDialog(null, "����û�й���˳��εĳ�ƱŶ��");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
				
			}
		});
		button_1.setFont(new Font("���Ŀ���", Font.PLAIN, 18));
		button_1.setBounds(143, 216, 80, 36);
		contentPane.add(button_1);
		
		/*
		 * �Զ�����¼��Ӽ����¼�
		 */
		JButton button_2 = new JButton("\u6211\u7684\u8BA2\u5355\u8BB0\u5F55");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserOrdeDialog uojDialog=new UserOrdeDialog(user);
				// ����ģʽ���͡�
				uojDialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);    
				uojDialog.setVisible(true);
				
			}
		});
		button_2.setFont(new Font("���Ŀ���", Font.PLAIN, 15));
		button_2.setBounds(53, 263, 146, 36);
		contentPane.add(button_2);

		//�������
		Util.setFrameCenter(this);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("\u7528\u6237\u76F8\u5173");
		menu.setIcon(new ImageIcon(UserServerJFrame.class.getResource("/source/user.png")));
		
		menuBar.add(menu);
		
		/*
		 * �û�������Ϣ���� ��ť��Ӽ����¼�
		 */
		JFrame jf=this;
		menuItem = new JMenuItem("\u4E2A\u4EBA\u4E2D\u5FC3\u7BA1\u7406");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//��ת�������Ĺ������
				PersonCenterJf pjf=new PersonCenterJf(user,jf);
				//����������Ϊ����
				jf.setEnabled(false);
				//��ת����������ʾ
				pjf.setVisible(true);
				
			}
		});
		menu.add(menuItem);
		
		/*
		 * �û��˳�ϵͳ�����¼�
		 */
		JMenuItem menuItem_2 = new JMenuItem("\u9000\u51FA\u7CFB\u7EDF");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n=JOptionPane.showConfirmDialog(null, "��ȷ���˳�ϵͳ��","ȷ���˳�",JOptionPane.YES_NO_OPTION);
				if(n==0){//����Ϊ0���ʾȷ��
					LoginFrame login=new LoginFrame();
					login.setVisible(true);
					dispose();
				}
			}
		});
		menu.add(menuItem_2);
		
		/*
		 * ע���˻���ť��Ӽ����¼�
		 */
		JMenuItem menuItem_3 = new JMenuItem("\u6CE8\u9500\u8D26\u6237");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//��ȡҵ����û�������׼ʵ������
				IUserdService iuseSer=ServiceFactory.getIUserdServiceInstance();
				int n=JOptionPane.showConfirmDialog(null, "һ��ע���޷����ģ�ȷ��ע�����˻���","ע���˻�",JOptionPane.YES_NO_OPTION);
				if(n==0){
					if(iuseSer.closeAccount(user)){
						System.out.println();
						JOptionPane.showMessageDialog(null, "ע���ɹ��������˳���");
						//�˻ص���¼����
						LoginFrame login=new LoginFrame();
						login.setVisible(true);
						dispose();
					}
				}
				
			}
		});
		menu.add(menuItem_3);
		
		/*
		 * �����˵�����¼�
		 */
		JMenu menu_1 = new JMenu("\u4F7F\u7528\u5E2E\u52A9");
		menu_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		menu_1.setIcon(new ImageIcon(UserServerJFrame.class.getResource("/source/small1.jpg")));
		menuBar.add(menu_1);
		
		/*
		 * �����˵���Ӽ����¼�
		 */
		JMenuItem menuItem_1 = new JMenuItem("\u4F7F\u7528\u76F8\u5173");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, 
					"1.��������������Ϣ��ʼ���غ�Ŀ�ĵص㣬������ѯ�� ��ť������ʾ������Ϣ\n"
					+ "2.�����ڱ����ѡ���Լ���Ҫ�ĳ��Σ���������� ��ť����\n"
					+ "3.�˵����е�����û���ء�->�������Ĺ����ɿ����Լ����˻���Ϣ\n"
					+ "4. �����������¼����ť���ɲ鿴�Լ��������Ʊ\n"		
				);
				
			}
		});
		menu_1.add(menuItem_1);

		
		

	}
}
