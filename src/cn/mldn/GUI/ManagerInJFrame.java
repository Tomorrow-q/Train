package cn.mldn.GUI;

import java.awt.BorderLayout;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import cn.mldn.factory.ServiceFactory;
import cn.mldn.pojo.TrainNumber;
import cn.mldn.pojo.UserOrder;
import cn.mldn.service.ITrainNumberService;
import cn.mldn.service.IUserOrderService;
/**
 * ���ǹ���Ա������������
 * @author ASUS
 *
 */
@SuppressWarnings("serial")
public class ManagerInJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField_trainNum;
	private JTextField textField_startP;
	private JTextField textField_destP;
	private JTextField textField_countTic;
	private JTextField textField_price;
	private JTextField textField;
	private JTextField textField_1;
	//���г�����Ϣ�ı��
	private JTable table =null;
	//������Ʊ��¼�ı��
	private JTable table_1=null;
	private JScrollPane scrollPane;
	//���ģ�ͣ����г��εı��ģ�ͣ�
	//private TableModel model=null;
	private DefaultTableModel model= null; 
	//���ģ�ͣ�������Ʊ�ı��ģ�ͣ�
	private DefaultTableModel model_1=null;
	
	private showMessage show=new showMessage();

	private Point pressedPoint;
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ManagerInJFrame frame = new ManagerInJFrame();
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
	public ManagerInJFrame() {
		setResizable(false);
		//ȡ����������Ч��
	//	this.setUndecorated(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ManagerInJFrame.class.getResource("/source/max2.jpg")));
		setTitle("\u6B22\u8FCE\u7BA1\u7406\u5458\u56DE\u5230\u7CFB\u7EDF");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 717, 693);
	//	this.setExtendedState(JFrame.MAXIMIZED_BOTH);  //�������
		//���ô���رհ�ťʧЧ���Ա���ʹ�ò˵������˳���ť
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		contentPane =new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
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
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("\u6587\u4EF6");
		mnNewMenu.setIcon(new ImageIcon(ManagerInJFrame.class.getResource("/source/smiley-star-sad.png")));
		menuBar.add(mnNewMenu);
		
		/*
		 * �˳�ϵͳ��ť
		 */
		JMenuItem mntmNewMenuItem = new JMenuItem("\u9000\u51FA\u7CFB\u7EDF");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n=JOptionPane.showConfirmDialog(null, "ȷ���˳�ϵͳ��","�˳�ϵͳ",JOptionPane.YES_NO_OPTION);
				if(n==0){//0��ʾȷ��
					LoginFrame login=new LoginFrame();
					login.setVisible(true);
					dispose();
				}
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_3 = new JMenu("\u4F7F\u7528\u5E2E\u52A9");
		mnNewMenu_3.setIcon(new ImageIcon(ManagerInJFrame.class.getResource("/source/wj (2).png")));
		menuBar.add(mnNewMenu_3);
		
		/*
		 * ʹ�ð����˵� ��Ӽ����¼�
		 */
		JMenuItem menuItem = new JMenuItem("\u4F7F\u7528\u76F8\u5173");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"1.����ѡ��ɽ��в�ͬ�Ĳ�����������Ʊ���ӡ�ɾ�����޸ģ��鿴���г����Լ����г˿͹�Ʊ��Ϣ\n"
					+	 "2.Ҫͨ���ļ��˵��еġ��˳�ϵͳ���˵��ſ����˳�ϵͳŶ�� ");
			}
		});
		mnNewMenu_3.add(menuItem);
		setContentPane(contentPane);
		
		//contentPane = new JDesktopPane();
//		contentPane = new JDesktopPane(){
//			@Override
//			public void paintComponent(Graphics g){//�ػ���屳��
//				//����һ��δ��ʼ����ͼ��ͼ�꣬�ο�API
//				ImageIcon icon=new ImageIcon("src"+File.separator+"source"+File.separator+"00.jpg");
//				//����ָ��ͼ���������ŵ��ʺ�ָ�������ڲ���ͼ�񣬲ο�API
//				g.drawImage(icon.getImage(), 0, 0, this.getWidth(), this.getHeight(),this);
//			}
//		};
		
		//ѡ�
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		/*
		 * ��ѯ�������
		 */
		//JPanel panelFind = new JPanel();
		JDesktopPane panelFind = new JDesktopPane(){
			@Override
			public void paintComponent(Graphics g){//�ػ���屳��
				//����һ��δ��ʼ����ͼ��ͼ�꣬�ο�API
				ImageIcon icon=new ImageIcon("src"+File.separator+"source"+File.separator+"bj3.jpg");
				//����ָ��ͼ���������ŵ��ʺ�ָ�������ڲ���ͼ�񣬲ο�API
				g.drawImage(icon.getImage(), 0, 0, this.getWidth(), this.getHeight(),this);
			}
		};
		tabbedPane.addTab("�鿴���г���", null, panelFind, null);
		panelFind.setLayout(null);

		table=new JTable();
		model =new DefaultTableModel(
				new Object[][]{
					
				},
				new String[]{
					"���κ�", "������", "ʼ����", "����ʱ��", "Ʊ��", "�۸�"
				}
		);

		//��ʾ���ݿ�����
		//show.showTrainMessage();
		table.setModel(model);
		table.setRowHeight(25);
		//�Բ�ѯ���г��ν�������ݹ�������
		
		
		/*
		 * ɾ����ť��Ӽ����¼�
		 */
		JButton button_2 = new JButton("\u5220\u9664\u8F66\u6B21");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num=JOptionPane.showInputDialog("������Ҫɾ���ĳ��κţ�\n");
				ITrainNumberService itrain= ServiceFactory.getITrainNumberServiceInstance();
				try {
					if(itrain.delete(num)){
						JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
					}
//					else{
//						JOptionPane.showMessageDialog(null, "�˳�Ʊ�����ڣ�ɾ��ʧ�ܣ�");
//						
//					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		button_2.setFont(new Font("����", Font.PLAIN, 14));
		button_2.setBounds(583, 91, 93, 33);
		panelFind.add(button_2);
		
		//������
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 66, 569, 516);
		
		panelFind.add(scrollPane);
		scrollPane.setViewportView(table);
		
		/*
		 * ˢ�°�ť��Ӽ����¼�
		 */
		JButton button_3 = new JButton("\u5237\u65B0");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ÿˢ��һ�ξͽ�֮ǰ���������
				model.setRowCount(0);
				show.showTrainMessage(1);
			}
		});
		button_3.setFont(new Font("����", Font.PLAIN, 16));
		button_3.setBounds(583, 177, 93, 33);
		panelFind.add(button_3);
		
		/*
		 * �鿴��ť��Ӽ����¼�
		 */
		JButton btnNewButton = new JButton("\u67E5\u770B");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ÿ��ѯһ��ʱ�������һ������
				((DefaultTableModel) table.getModel()).getDataVector().clear();
				model.setRowCount(0);
				show.showTrainMessage(1);
			}
		});
		btnNewButton.setFont(new Font("����", Font.PLAIN, 16));
		btnNewButton.setBounds(583, 252, 93, 33);
		panelFind.add(btnNewButton);
		
		//�����������
		JComboBox<String> comboBox_sort = new JComboBox<String>();
		comboBox_sort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ITrainNumberService its=ServiceFactory.getITrainNumberServiceInstance();
				String train_sort=(String) comboBox_sort.getSelectedItem();
				if(train_sort.equals("����")){
					//ÿ��ѯһ��ʱ�������һ������
					((DefaultTableModel) table.getModel()).getDataVector().clear();
					model.setRowCount(0);
					show.showTrainMessage(1);
				}else{
					//ÿ��ѯһ��ʱ�������һ������
					((DefaultTableModel) table.getModel()).getDataVector().clear();
					model.setRowCount(0);
					show.showTrainMessage(2);
				}
				
			}
		});
		comboBox_sort.setFont(new Font("���Ŀ���", Font.PLAIN, 16));
		comboBox_sort.setModel(new DefaultComboBoxModel<String>(new String[] {"\u5347\u5E8F", "\u964D\u5E8F"}));
		comboBox_sort.setBounds(412, 23, 64, 33);
		panelFind.add(comboBox_sort);
		
	
		/*
		 * �޸ĳ������
		 */
		JPanel panelUpdate = new JPanel();
//		JDesktopPane panelUpdate = new JDesktopPane(){
//			@Override
//			public void paintComponent(Graphics g){//�ػ���屳��
//				//����һ��δ��ʼ����ͼ��ͼ�꣬�ο�API
//				ImageIcon icon=new ImageIcon("src"+File.separator+"source"+File.separator+"back01.jpg");
//				//����ָ��ͼ���������ŵ��ʺ�ָ�������ڲ���ͼ�񣬲ο�API
//				g.drawImage(icon.getImage(), 0, 0, this.getWidth(), this.getHeight(),this);
//			}
//		};
		tabbedPane.addTab("�޸ĳ���", null, panelUpdate, null);
		panelUpdate.setLayout(new BorderLayout(0, 0));
		
		JLabel label_1 = new JLabel("                              \u4FEE\u6539\u8F66\u6B21\u4FE1\u606F");
		label_1.setForeground(Color.MAGENTA);
		label_1.setFont(new Font("����", Font.PLAIN, 20));
		panelUpdate.add(label_1, BorderLayout.NORTH);
		
		//�޸ĳ����������
		JDesktopPane panel = new JDesktopPane(){
		@Override
		public void paintComponent(Graphics g){//�ػ���屳��
			//����һ��δ��ʼ����ͼ��ͼ�꣬�ο�API
			ImageIcon icon=new ImageIcon("src"+File.separator+"source"+File.separator+"back003.jpg");
			//����ָ��ͼ���������ŵ��ʺ�ָ�������ڲ���ͼ�񣬲ο�API
			g.drawImage(icon.getImage(), 0, 0, this.getWidth(), this.getHeight(),this);
		}
	};
		
		panelUpdate.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel label_2 = new JLabel("\u9700\u8981\u4FEE\u6539\u7684\u8F66\u6B21\u53F7\uFF1A");
		label_2.setFont(new Font("����", Font.PLAIN, 17));
		label_2.setBounds(76, 51, 170, 31);
		panel.add(label_2);
		
		textField = new JTextField();
		textField.setBounds(256, 51, 78, 27);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel label_3 = new JLabel("\u9700\u8981\u4FEE\u6539\u7684\u5C5E\u6027:");
		label_3.setFont(new Font("����", Font.PLAIN, 17));
		label_3.setBounds(76, 117, 141, 31);
		panel.add(label_3);
		
		JComboBox<String> comboBox_4 = new JComboBox<String>();
		comboBox_4.setFont(new Font("����", Font.PLAIN, 15));
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"startPlace", "destination", "goTime", "remainTicket", "price"}));
		comboBox_4.setBounds(256, 118, 125, 31);
		panel.add(comboBox_4);
		
		JLabel label_4 = new JLabel("\u4FEE\u6539\u540E\u7684\u5185\u5BB9\uFF1A");
		label_4.setFont(new Font("����", Font.PLAIN, 17));
		label_4.setBounds(76, 191, 141, 31);
		panel.add(label_4);
		
		textField_1 = new JTextField();
		textField_1.setBounds(256, 192, 78, 31);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		/*
		 * �޸İ�ť��Ӽ����¼�
		 */
		JButton button_1 = new JButton("\u786E\u8BA4\u4FEE\u6539");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//��ȡ����ҵ���ʵ�����󣬵��÷����޸�����
				ITrainNumberService itrainSer= ServiceFactory.getITrainNumberServiceInstance();
				String num =textField.getText();
				String attribute=(String) comboBox_4.getSelectedItem();
				String content=textField_1.getText();
				
					try {
						if(itrainSer.Update(num, attribute, content)){
							JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
							textField.setText("");
							textField_1.setText("");
						}else{
							JOptionPane.showMessageDialog(null, "�޸�ʧ�ܣ�");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
			
		});
		button_1.setFont(new Font("����", Font.PLAIN, 15));
		button_1.setBounds(398, 180, 116, 39);
		panel.add(button_1);
	
		
		/*
		 * ���ӳ������
		 */
	{
		//JPanel panelAdd = new JPanel();
		JDesktopPane panelAdd = new JDesktopPane(){
		@Override
		public void paintComponent(Graphics g){//�ػ���屳��
			//����һ��δ��ʼ����ͼ��ͼ�꣬�ο�API
			ImageIcon icon=new ImageIcon("src"+File.separator+"source"+File.separator+"back003.jpg");
			//����ָ��ͼ���������ŵ��ʺ�ָ�������ڲ���ͼ�񣬲ο�API
			g.drawImage(icon.getImage(), 0, 0, this.getWidth(), this.getHeight(),this);
		}
	};
		
		tabbedPane.addTab("���ӳ���", null, panelAdd, null);
		panelAdd.setLayout(null);
		JLabel lblNewLabel = new JLabel("\u8F66\u6B21\u53F7:");
		lblNewLabel.setIcon(new ImageIcon(ManagerInJFrame.class.getResource("/source/orange-ball.png")));
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 18));
		lblNewLabel.setBounds(66, 51, 88, 32);
		panelAdd.add(lblNewLabel);
		
		textField_trainNum = new JTextField();
		textField_trainNum.setBounds(178, 51, 112, 32);
		panelAdd.add(textField_trainNum);
		textField_trainNum.setColumns(10);
	
		JLabel lblNewLabel_1 = new JLabel("\u59CB\u53D1\u5730:");
		lblNewLabel_1.setIcon(new ImageIcon(ManagerInJFrame.class.getResource("/source/orange-ball.png")));
		lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(66, 106, 88, 32);
		panelAdd.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("\u76EE\u7684\u5730:");
		lblNewLabel_1_1.setIcon(new ImageIcon(ManagerInJFrame.class.getResource("/source/orange-ball.png")));
		lblNewLabel_1_1.setFont(new Font("����", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(66, 154, 88, 32);
		panelAdd.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("\u51FA\u53D1\u65F6\u95F4:");
		lblNewLabel_1_1_1.setIcon(new ImageIcon(ManagerInJFrame.class.getResource("/source/orange-ball.png")));
		lblNewLabel_1_1_1.setFont(new Font("����", Font.PLAIN, 18));
		lblNewLabel_1_1_1.setBounds(66, 201, 112, 32);
		panelAdd.add(lblNewLabel_1_1_1);
		
		textField_startP = new JTextField();
		textField_startP.setColumns(10);
		textField_startP.setBounds(178, 106, 112, 32);
		panelAdd.add(textField_startP);
		
		textField_destP = new JTextField();
		textField_destP.setColumns(10);
		textField_destP.setBounds(178, 154, 112, 32);
		panelAdd.add(textField_destP);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("\u7968 \u6570:");
		lblNewLabel_1_1_1_1.setIcon(new ImageIcon(ManagerInJFrame.class.getResource("/source/orange-ball.png")));
		lblNewLabel_1_1_1_1.setFont(new Font("����", Font.PLAIN, 18));
		lblNewLabel_1_1_1_1.setBounds(66, 257, 93, 32);
		panelAdd.add(lblNewLabel_1_1_1_1);
		
		textField_countTic = new JTextField();
		textField_countTic.setColumns(10);
		textField_countTic.setBounds(178, 257, 112, 32);
		panelAdd.add(textField_countTic);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("\u4EF7 \u683C:");
		lblNewLabel_1_1_1_1_1.setIcon(new ImageIcon(ManagerInJFrame.class.getResource("/source/orange-ball.png")));
		lblNewLabel_1_1_1_1_1.setFont(new Font("����", Font.PLAIN, 18));
		lblNewLabel_1_1_1_1_1.setBounds(66, 314, 93, 32);
		panelAdd.add(lblNewLabel_1_1_1_1_1);
		
		textField_price = new JTextField();
		textField_price.setColumns(10);
		textField_price.setBounds(178, 314, 112, 32);
		panelAdd.add(textField_price);
	
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("����", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		comboBox.setBounds(178, 203, 64, 32);
		panelAdd.add(comboBox);
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBox_1.setFont(new Font("����", Font.PLAIN, 14));
		comboBox_1.setBounds(289, 203, 64, 32);
		panelAdd.add(comboBox_1);
		
		JComboBox<String> comboBox_2 = new JComboBox<String>();
		comboBox_2.setModel(new DefaultComboBoxModel<String>(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		comboBox_2.setFont(new Font("����", Font.PLAIN, 14));
		comboBox_2.setBounds(410, 203, 64, 32);
		panelAdd.add(comboBox_2);
		
		JComboBox<String> comboBox_3 = new JComboBox<String>();
		comboBox_3.setModel(new DefaultComboBoxModel<String>(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		comboBox_3.setFont(new Font("����", Font.PLAIN, 14));
		comboBox_3.setBounds(529, 203, 64, 32);
		panelAdd.add(comboBox_3);
		
		//���Ӱ�ť��Ӽ����¼�
		JButton button = new JButton("\u589E \u52A0");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//��ȡҵ���ʵ�����󣬵������ݲ㷽�����ӳ���
				ITrainNumberService itrainS=ServiceFactory.getITrainNumberServiceInstance();
				String num=textField_trainNum.getText();
				String startPlace=textField_startP.getText();
				String dest=textField_destP.getText();
				String time=(String) comboBox.getSelectedItem()+"."+(String) comboBox_1.getSelectedItem()+" "+(String) comboBox_2.getSelectedItem()+":"+(String) comboBox_3.getSelectedItem();
				Integer coun=Integer.parseInt(textField_countTic.getText());
				String price=textField_price.getText();
				TrainNumber train=new TrainNumber(num,startPlace,dest,time,coun,price);
				if(num==null||startPlace==null||dest==null||time==null||coun==null||price==null){
					JOptionPane.showMessageDialog(null, "�ı�����Ϊ��Ŷ��^-^");
					return;
				}
				try {
					if(itrainS.insert(train)){
						JOptionPane.showMessageDialog(null, "��ӳɹ�");
						//��ӳɹ����ı�����Ϊ0
						textField_trainNum.setText("");
						textField_startP.setText("");
						textField_destP.setText("");
						textField_countTic.setText("");
						textField_price.setText("");
						
					}
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		button.setFont(new Font("����", Font.PLAIN, 16));
		button.setBounds(172, 381, 88, 32);
		panelAdd.add(button);
		
		JLabel label = new JLabel("\u8F66  \u7968  \u589E  \u52A0");
		label.setForeground(Color.MAGENTA);
		label.setFont(new Font("����", Font.PLAIN, 18));
		label.setBounds(281, 10, 145, 31);
		panelAdd.add(label);
		
		JLabel label_5 = new JLabel("\u6708");
		label_5.setFont(new Font("����", Font.PLAIN, 16));
		label_5.setBounds(253, 203, 37, 32);
		panelAdd.add(label_5);
		
		JLabel label_5_1 = new JLabel("\u65E5");
		label_5_1.setFont(new Font("����", Font.PLAIN, 16));
		label_5_1.setBounds(363, 202, 37, 32);
		panelAdd.add(label_5_1);
		
		JLabel label_5_1_1 = new JLabel("\u65F6");
		label_5_1_1.setFont(new Font("����", Font.PLAIN, 16));
		label_5_1_1.setBounds(490, 202, 43, 32);
		panelAdd.add(label_5_1_1);
		
		JLabel label_5_1_1_1 = new JLabel("\u5206");
		label_5_1_1_1.setFont(new Font("����", Font.PLAIN, 16));
		label_5_1_1_1.setBounds(603, 202, 43, 32);
		panelAdd.add(label_5_1_1_1);
	}
	
	/*
	 * �鿴��Ʊ��¼���
	 */
	JPanel panelOrder = new JPanel();
	tabbedPane.addTab("�鿴��Ʊ��¼", null, panelOrder, null);
	panelOrder.setLayout(null);
	
	JScrollPane scrollPane_1 = new JScrollPane();
	scrollPane_1.setBounds(32, 47, 623, 521);
	panelOrder.add(scrollPane_1);
	
	//��ʾ���ж�����¼�ı��
	table_1 = new JTable();
	scrollPane_1.setViewportView(table_1);
	
	model_1 =new DefaultTableModel(
			new Object[][]{
				
			},
			new String[]{
				"���κ�", "������", "ʼ����", "�˿�����", "���֤��"
			}
	);
	
	
	
	
	/*
	 * ��Ʊ��¼�鿴����ť��Ӽ����¼�
	 */
	JButton button_4 = new JButton("\u67E5\u770B");
	button_4.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			//ÿ��ѯһ��ʱ�������һ������
			((DefaultTableModel) table_1.getModel()).getDataVector().clear();
			model_1.setRowCount(0);
			show.showOrderMessage();
			
		}
	});
	table_1.setModel(model_1);
	button_4.setFont(new Font("����", Font.PLAIN, 16));
	button_4.setBounds(304, 10, 85, 34);
	panelOrder.add(button_4);
	
	
	}
	
	
 class showMessage{
		
	/*
	 * ���Ǵ����ݿ��ж�ȡ���г�����Ϣ���ݵ������У��Ա���ʾ�����
	 */
	public  void showTrainMessage(int num){
		/*
		 * ��ȡ���ݿ��г�Ʊȫ������
		 */

		ITrainNumberService itrainS=ServiceFactory.getITrainNumberServiceInstance();
		List<TrainNumber> trainList=new ArrayList<TrainNumber>();
		try {
			trainList=itrainS.selectAll(num);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		if(trainList!=null){
			for(TrainNumber train:trainList){
				Vector<Comparable> vec=new Vector<Comparable>();
				//TrainNumber tn=new TrainNumber();
				vec.add(train.getTrainNum());
				vec.add(train.getStartPlace());
				vec.add(train.getDestination());
				vec.add(train.getGoTime());
				vec.add(train.getRemainTicket());
				vec.add(train.getPrice());
				model.addRow(vec);
			}
		}
		
	}
	
	
	public  void showOrderMessage(){
		//ͨ������ģʽ��ȡҵ�����ʶ���
		IUserOrderService ios=ServiceFactory.getIUserOrderServiceInstance();
		//List<UserOrder> orderList=ios.findManageAllOrder();
		List<UserOrder> orderList=ios.findManageAllOrder();
		if(orderList!=null){
			for(UserOrder order:orderList){
				Vector<Comparable> vec=new Vector<Comparable>();
				//TrainNumber tn=new TrainNumber();
				vec.add(order.getTrainName());
				vec.add(order.getStartPlace());
				vec.add(order.getDestPlace());
				vec.add(order.getPassengName());
				//vec.add(order.getIDCard());
				model_1.addRow(vec);
			}
		}
		
	}
	
	
	
}
}
