package cn.mldn.Util;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import cn.mldn.pojo.Userd;

@SuppressWarnings("serial")
public class PersonalCenterDialog extends JDialog {

	
//	protected static final Frame PersonalCenterDialog = null;
	private Userd user=new Userd();
	private JTextField textField_passengeName;
	private JTextField textField_IDcard;
	private JTextField textField_useName;
	private JPasswordField passwordField;
	JComboBox<String> comboBox_sex;
//	public static void main(String[] args) {
//		try {
//			PersonalCenterDialog dialog = new PersonalCenterDialog();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/*
	 * ��������ϵͳ���û�����
	 */
	public PersonalCenterDialog(Userd user) {
		this.user=user;
		setIconImage(Toolkit.getDefaultToolkit().getImage(PersonalCenterDialog.class.getResource("/source/premium.png")));
		setTitle("\u7528\u6237\u4E2A\u4EBA\u4E2D\u5FC3");
		setBounds(100, 100, 429, 397);
		getContentPane().setLayout(new BorderLayout(0, 0));
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		//�������
		JPanel combop = new JPanel();
		JPanel p1 = new JPanel();
		//JPanel p2 = new JPanel();
		tabbedPane.add(p1,"��������");
		p1.setLayout(null);
		
		JLabel label = new JLabel("\u59D3\u540D\uFF1A");
		label.setFont(new Font("���Ŀ���", Font.PLAIN, 16));
		label.setBounds(25, 10, 69, 33);
		p1.add(label);
		
		JLabel label_1 = new JLabel("\u6027\u522B\uFF1A");
		label_1.setFont(new Font("���Ŀ���", Font.PLAIN, 16));
		label_1.setBounds(25, 57, 69, 27);
		p1.add(label_1);
		
	
		
		JLabel label_2 = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7\uFF1A");
		label_2.setFont(new Font("���Ŀ���", Font.PLAIN, 16));
		label_2.setBounds(25, 94, 84, 28);
		p1.add(label_2);
		
		
		JLabel label_3 = new JLabel("\u8D26\u6237\uFF1A");
		label_3.setFont(new Font("���Ŀ���", Font.PLAIN, 16));
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
		
		//���û�������ʾ��������
		setMessage();
//		textField_passengeName.setText(user.getPasseName());
//		comboBox_sex.setSelectedItem(user.getSex());
//		textField_IDcard.setText(user.getIDCard());
//		textField_useName.setText(user.getUsername());
//		passwordField.setText(user.getPassword());
		
		
		JLabel label_4 = new JLabel("\u5BC6\u7801\uFF1A");
		label_4.setFont(new Font("���Ŀ���", Font.PLAIN, 16));
		label_4.setBounds(25, 195, 69, 27);
		p1.add(label_4);
		
		//�޸İ�ť��Ӽ����¼�
//		JButton button_modifyall = new JButton("\u4FDD\u5B58\u4FEE\u6539");
//		button_modifyall.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				///��ȡ������˻���Ϣ
//				String name=textField_passengeName.getText();	
//				String sex=(String) comboBox_sex.getSelectedItem();
//				String id=textField_IDcard.getText();
//				//���û���Ϣ���¸�ֵ
//				user.setPasseName(name);
//				user.setSex(sex);
//				user.setIDCard(id);
//				
//				//��ȡҵ����׼����ʵ�� 
//				IUserdService iuse=ServiceFactory.getIUserdServiceInstance();
//				if(iuse.changeMessage(user)){
//					int n=JOptionPane.showConfirmDialog(null, "ȷ�����������޸���", "�����޸�ȷ��",JOptionPane.YES_NO_OPTION);
//					if(n==0){
//						JOptionPane.showMessageDialog(null, "�����޸ĳɹ���");
//						
//					}
//				
//				}else{
//					JOptionPane.showMessageDialog(null, "�޸�ʧ�ܣ�");
//				}
//				
//			}
//		});
//		button_modifyall.setFont(new Font("���Ŀ���", Font.PLAIN, 15));
//		button_modifyall.setBounds(33, 250, 105, 33);
//		p1.add(button_modifyall);
//		
		/*
		 * �޸����밴ť��Ӽ����¼�
		 */
		JButton button_modifypass = new JButton("\u4FEE\u6539\u5BC6\u7801");
		button_modifypass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog jdialog=new JDialog();
				jdialog.setVisible(true);
			}
		});
		button_modifypass.setFont(new Font("���Ŀ���", Font.PLAIN, 15));
		button_modifypass.setBounds(115, 261, 112, 33);
		p1.add(button_modifypass);
		//tabbedPane.add(p2,"�˻���Ϣ");
		combop.add(new JLabel("�������Ĺ���"));
		getContentPane().add(combop, BorderLayout.NORTH);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
	}
	private void setMessage(){
		this.textField_passengeName.setText(user.getPasseName());
		this.comboBox_sex.setSelectedItem(user.getSex());
		this.textField_IDcard.setText(user.getIDCard());
		this.textField_useName.setText(user.getUsername());
		this.passwordField.setText(user.getPassword());
	}
}
