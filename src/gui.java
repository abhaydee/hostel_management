import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Statement;


import net.proteanit.sql.DbUtils;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class gui extends JFrame {

	private String search_str = null;
	private JPanel contentPane;
	private JTextField stu_name;
	private JTextField stu_father;
	private JTextField stu_phone;
	private JTextField stu_dob;
	private JTextField stu_address;
	private JTextField stu_doj;
	private JTextField stu1_name;
	private JTextField stu2_name;
	private JTextField stu3_name;
	private JTextField st_name;
	private JTextField st_phone;
	private JTextField st_dob;
	private JTextField st_address;
	private JTextField st_salary;
	private JTextField st_doj;
	private JTextField st1_name;
	private JTextField st2_name;
	private JTextField st3_name;
	private JTextField room_id;
	private JTextField room2_name;
	private JTextField fee_id;
	private JTextField fees_mon_yr;
	private JTextField fees_pay_date;
	private JTextField fee2_name;
	private JTable table_ret_stu;
	private JTable table_ret_st;
    DefaultTableModel dm;
    private JTable table_up_stu;
    
    private JTextField st2_id;
    private JTextField st2_name2;
    private JTextField st2_phone;
    private JTextField st2_dept;
    private JTextField st2_dob;
    private JTextField st2_gender;
    private JTextField st2_address;
    private JTextField st2_salary;
    private JTextField st2_doj;
    private JTextField st2_dol;
    private JTable table_up_st;
    private JTextField stu2_id;
    private JTextField stu2_name2;
    private JTextField stu2_father;
    private JTextField stu2_phone;
    private JTextField stu2_gender;
    private JTextField stu2_dob;
    private JTextField stu2_branch;
    private JTextField stu2_semester;
    private JTextField stu2_address;
    private JTextField stu2_doj;
    private JTextField stu2_dol;
    private JTable table_del_stu;
    private JTextField stu3_id;
    private JTable table_del_st;
    private JTextField st3_id;
    private JTable table_room;
    private JTextField room2_id;
    private JTable table_room2;
    private JComboBox stu_gender;
    private JComboBox st_gender;
    private JComboBox room_no;
    private HashMap<Integer, String> room;
    private JTextField st3_hid;
    private JTextField fee2_id;
    private JTable table_fees;
    private JTextField st2_hid;
   // private String [] r = {};
    private JComboBox stu_branch;
    private JComboBox stu_sem;
    private JComboBox st_hid;
    private JComboBox st_dept;
    private JComboBox room_hid;
    private JComboBox room3_hid;
    private JComboBox room3_no;
   
	/**
	 * Launch the application.
	 */
	public void start_app() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui frame = new gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public void combobox(String hostel_id) {
	try {
		db_config.connect_to_database();
		
		String query = "Select * from room where hostel_id='"+hostel_id+"' and vacancy >0;";
		System.out.println(query);
		PreparedStatement ps = db_config.conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		room_no.removeAllItems();
		while(rs.next()) {
			room_no.addItem(rs.getString(1));
		}
	}catch(Exception ex) {
		System.out.println(ex);
	}
	}
	
	
	
	
	public gui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 714, 649);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(204, 204, 255));
		setJMenuBar(menuBar);
		
		JMenu mnStudent = new JMenu("Student");
		mnStudent.setBackground(SystemColor.control);
		mnStudent.setFont(new Font("Mongolian Baiti", Font.BOLD, 15));
		Image img = new ImageIcon(this.getClass().getResource("/student.png")).getImage();
		mnStudent.setIcon(new ImageIcon(img));
		menuBar.add(mnStudent);
		
		JMenuItem mntmAdd = new JMenuItem("Add");
		mntmAdd.setBackground(new Color(240, 248, 255));
		mntmAdd.setFont(new Font("Mongolian Baiti", Font.BOLD, 13));
		Image img1 = new ImageIcon(this.getClass().getResource("/add_student.png")).getImage();
		 mntmAdd.setIcon(new ImageIcon(img1));
		mntmAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout c =(CardLayout)(contentPane.getLayout());
				c.show(contentPane,"p2");
			}
		});
		mnStudent.add(mntmAdd);
		
		JMenuItem mntmRetrieve = new JMenuItem("Retrieve");
		mntmRetrieve.setBackground(new Color(240, 248, 255));
		mntmRetrieve.setFont(new Font("Mongolian Baiti", Font.BOLD, 12));
		Image img2 = new ImageIcon(this.getClass().getResource("/retrieve.png")).getImage();
		 mntmRetrieve.setIcon(new ImageIcon(img2));
		mntmRetrieve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c =(CardLayout)(contentPane.getLayout());
				c.show(contentPane,"p3");
			}
		});
		mnStudent.add(mntmRetrieve);
		
		JMenuItem mntmUpdate = new JMenuItem("Update");
		mntmUpdate.setBackground(new Color(240, 248, 255));
		mntmUpdate.setFont(new Font("Mongolian Baiti", Font.BOLD, 12));
		Image img4 = new ImageIcon(this.getClass().getResource("/update.png")).getImage();
		mntmUpdate.setIcon(new ImageIcon(img4));
		mntmUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c =(CardLayout)(contentPane.getLayout());
				c.show(contentPane,"p4");
			}
		});
		mnStudent.add(mntmUpdate);
		
		JMenuItem mntmDelete = new JMenuItem("Delete");
		mntmDelete.setBackground(new Color(240, 248, 255));
		mntmDelete.setFont(new Font("Mongolian Baiti", Font.BOLD, 12));
		Image img3 = new ImageIcon(this.getClass().getResource("/delete.png")).getImage();
		 mntmDelete.setIcon(new ImageIcon(img3));
		mntmDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c =(CardLayout)(contentPane.getLayout());
				c.show(contentPane,"p5");
			}
		});
		mnStudent.add(mntmDelete);
		
		JMenu mnStaff = new JMenu("Staff");
		mnStaff.setBorder(new CompoundBorder());
		mnStaff.setFont(new Font("Mongolian Baiti", Font.BOLD, 15));
		Image img5 = new ImageIcon(this.getClass().getResource("/staff.png")).getImage();
		mnStaff.setIcon(new ImageIcon(img5));
		menuBar.add(mnStaff);
		
		JMenuItem mntmAdd_1 = new JMenuItem("Add");
		mntmAdd_1.setBackground(new Color(240, 248, 255));
		mntmAdd_1.setFont(new Font("Mongolian Baiti", Font.BOLD, 12));
		Image img6 = new ImageIcon(this.getClass().getResource("/add_student.png")).getImage();
		 mntmAdd_1.setIcon(new ImageIcon(img6));
		mntmAdd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c =(CardLayout)(contentPane.getLayout());
				c.show(contentPane,"p6");
			}
		});
		mnStaff.add(mntmAdd_1);
		
		JMenuItem mntmRetrieve_1 = new JMenuItem("Retrieve");
		mntmRetrieve_1.setBackground(new Color(240, 248, 255));
		mntmRetrieve_1.setFont(new Font("Mongolian Baiti", Font.BOLD, 12));
		Image img7 = new ImageIcon(this.getClass().getResource("/retrieve.png")).getImage();
		 mntmRetrieve_1.setIcon(new ImageIcon(img7));
		mntmRetrieve_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c =(CardLayout)(contentPane.getLayout());
				c.show(contentPane,"p7");
			}
		});
		mnStaff.add(mntmRetrieve_1);
		
		JMenuItem mntmUpdate_1 = new JMenuItem("Update");
		mntmUpdate_1.setBackground(new Color(240, 248, 255));
		mntmUpdate_1.setFont(new Font("Mongolian Baiti", Font.BOLD, 12));
		Image img8 = new ImageIcon(this.getClass().getResource("/update.png")).getImage();
		mntmUpdate_1.setIcon(new ImageIcon(img8));
		mntmUpdate_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c =(CardLayout)(contentPane.getLayout());
				c.show(contentPane,"p8");
			}
		});
		mnStaff.add(mntmUpdate_1);
		
		JMenuItem mntmDelete_1 = new JMenuItem("Delete");
		mntmDelete_1.setBackground(new Color(240, 248, 255));
		mntmDelete_1.setFont(new Font("Mongolian Baiti", Font.BOLD, 12));
		Image img9 = new ImageIcon(this.getClass().getResource("/delete.png")).getImage();
		 mntmDelete_1.setIcon(new ImageIcon(img9));
		mntmDelete_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c =(CardLayout)(contentPane.getLayout());
				c.show(contentPane,"p9");
			}
		});
		mnStaff.add(mntmDelete_1);
		
		JMenu mnRoom = new JMenu("Room");
		mnRoom.setBackground(new Color(255, 204, 255));
		mnRoom.setBorder(new CompoundBorder());
		 mnRoom.setFont(new Font("Mongolian Baiti", Font.BOLD, 15));
		Image img10 = new ImageIcon(this.getClass().getResource("/room.png")).getImage();
		mnRoom.setIcon(new ImageIcon(img10));
		menuBar.add(mnRoom);
		
		JMenuItem mntmAssign = new JMenuItem("Assign");
		mntmAssign.setBackground(new Color(240, 248, 255));
		mntmAssign.setFont(new Font("Mongolian Baiti", Font.BOLD, 12));
		Image img11 = new ImageIcon(this.getClass().getResource("/assign.png")).getImage();
		mntmAssign.setIcon(new ImageIcon(img11));
		mntmAssign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c =(CardLayout)(contentPane.getLayout());
				c.show(contentPane,"p10");
			}
		});
		mnRoom.add(mntmAssign);
		
		JMenuItem mntmLeave = new JMenuItem("Leave");
		mntmLeave.setBackground(new Color(240, 248, 255));
		mntmLeave.setFont(new Font("Mongolian Baiti", Font.BOLD, 12));
		Image img12 = new ImageIcon(this.getClass().getResource("/leave.png")).getImage();
		mntmLeave.setIcon(new ImageIcon(img12));
		mntmLeave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c =(CardLayout)(contentPane.getLayout());
				c.show(contentPane,"p11");
			}
		});
		mnRoom.add(mntmLeave);
		
		JMenuItem mntmView = new JMenuItem("View");
		mntmView.setBackground(new Color(240, 248, 255));
		mntmView.setFont(new Font("Mongolian Baiti", Font.BOLD, 12));
		Image img13 = new ImageIcon(this.getClass().getResource("/view.png")).getImage();
		mntmView.setIcon(new ImageIcon(img13));
		mntmView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c =(CardLayout)(contentPane.getLayout());
				c.show(contentPane,"p12");
			}
		});
		mnRoom.add(mntmView);
		
		JMenu mnFeeDetails = new JMenu("Fee Details");
		mnFeeDetails.setBackground(SystemColor.control);
		mnFeeDetails.setBorder(new CompoundBorder());
		mnFeeDetails.setFont(new Font("Mongolian Baiti", Font.BOLD, 15));
			Image img14 = new ImageIcon(this.getClass().getResource("/fees.png")).getImage();
			mnFeeDetails.setIcon(new ImageIcon(img14));
		menuBar.add(mnFeeDetails);
		
		JMenuItem mntmPay = new JMenuItem("Pay");
		mntmPay.setBackground(new Color(240, 248, 255));
		mntmPay.setFont(new Font("Mongolian Baiti", Font.BOLD, 12));
		Image img15 = new ImageIcon(this.getClass().getResource("/pay.png")).getImage();
		mntmPay.setIcon(new ImageIcon(img15));
		mntmPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c =(CardLayout)(contentPane.getLayout());
				c.show(contentPane,"p13");
			}
		});
		mnFeeDetails.add(mntmPay);
		
		JMenuItem mntmView_1 = new JMenuItem("View");
		mntmView_1.setBackground(new Color(240, 248, 255));
		mntmView_1.setFont(new Font("Mongolian Baiti", Font.BOLD, 12));
		Image img16 = new ImageIcon(this.getClass().getResource("/view.png")).getImage();
		mntmView_1.setIcon(new ImageIcon(img16));
		mntmView_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c =(CardLayout)(contentPane.getLayout());
				c.show(contentPane,"p14");
			}
		});
		mnFeeDetails.add(mntmView_1);
		
		JButton btn_home = new JButton("Home");
		btn_home.setBorder(new CompoundBorder());
		btn_home.setFont(new Font("Mongolian Baiti", Font.BOLD, 15));
		btn_home.setBackground(new Color(204, 204, 255));
		Image img39 = new ImageIcon(this.getClass().getResource("/home.png")).getImage();
		btn_home.setIcon(new ImageIcon(img39));
		btn_home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c =(CardLayout)(contentPane.getLayout());
				c.show(contentPane,"p1");
			}
		});
		menuBar.add(btn_home);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JPanel first = new JPanel();
		first.setBackground(new Color(204, 204, 255));
		first.setFont(new Font("Mongolian Baiti", Font.BOLD, 14));
		contentPane.add(first, "p1");
		first.setLayout(null);
		
		
		JLabel lblHostelManagement = new JLabel("");
		lblHostelManagement.setHorizontalAlignment(SwingConstants.CENTER);
		lblHostelManagement.setForeground(new Color(0, 0, 255));
		lblHostelManagement.setFont(new Font("Constantia", Font.BOLD | Font.ITALIC, 34));
		lblHostelManagement.setBounds(252, 144, 325, 43);
		first.add(lblHostelManagement);
		Image img40 = new ImageIcon(this.getClass().getResource("/hostel3.png")).getImage();
		
		JLabel lblNewLabel_3 = new JLabel("Hostel Management");
		lblNewLabel_3.setFont(new Font("Modern No. 20", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(233, 32, 247, 37);
		first.add(lblNewLabel_3);
		
		JLabel lblDoneBy = new JLabel("Done By-");
		lblDoneBy.setFont(new Font("Lucida Sans", Font.BOLD | Font.ITALIC, 15));
		lblDoneBy.setBounds(405, 515, 87, 35);
		first.add(lblDoneBy);
		
		JLabel lblApekshaSJoshi = new JLabel("Apeksha S Joshi");
		lblApekshaSJoshi.setFont(new Font("Lucida Sans", Font.BOLD | Font.ITALIC, 14));
		lblApekshaSJoshi.setBounds(543, 525, 135, 17);
		first.add(lblApekshaSJoshi);
		
		JLabel lblAbhayDeshpande = new JLabel("Abhay Deshpande");
		lblAbhayDeshpande.setFont(new Font("Lucida Sans", Font.BOLD | Font.ITALIC, 14));
		lblAbhayDeshpande.setBounds(517, 545, 161, 17);
		first.add(lblAbhayDeshpande);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(img40));
		label_1.setForeground(new Color(0, 0, 255));
		
		label_1.setBounds(0, -11, 708, 584);
		first.add(label_1);
		
		JPanel add_student = new JPanel();
		add_student.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		contentPane.add(add_student, "p2");
		add_student.setLayout(null);
		
		JLabel lblEnterStudentDetails = new JLabel("Enter Student Details");
		lblEnterStudentDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterStudentDetails.setForeground(new Color(0, 0, 0));
		lblEnterStudentDetails.setBackground(SystemColor.control);
		lblEnterStudentDetails.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblEnterStudentDetails.setBounds(221, 11, 240, 33);
		add_student.add(lblEnterStudentDetails);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(new Color(0, 0, 0));
		lblName.setFont(new Font("Lucida Sans", Font.BOLD, 14));
		lblName.setBounds(200, 70, 46, 14);
		add_student.add(lblName);
		
		JLabel lblFathersName = new JLabel("Father's Name");
		lblFathersName.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblFathersName.setBounds(200, 112, 111, 14);
		add_student.add(lblFathersName);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblPhoneNumber.setBounds(200, 158, 111, 14);
		add_student.add(lblPhoneNumber);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblGender.setBounds(200, 199, 61, 14);
		add_student.add(lblGender);
		
		JLabel lblDob = new JLabel("DOB");
		lblDob.setHorizontalAlignment(SwingConstants.CENTER);
		lblDob.setFont(new Font("Lucida Sans", Font.BOLD, 14));
		lblDob.setBounds(200, 245, 46, 14);
		add_student.add(lblDob);
		
		JLabel lblBranch = new JLabel("Branch");
		lblBranch.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblBranch.setBounds(200, 288, 61, 14);
		add_student.add(lblBranch);
		
		JLabel lblSemester = new JLabel("Semester");
		lblSemester.setFont(new Font("Lucida Sans", Font.BOLD, 14));
		lblSemester.setBounds(200, 331, 86, 14);
		add_student.add(lblSemester);
		
		JLabel lblHomeAddress = new JLabel("Home Address");
		lblHomeAddress.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblHomeAddress.setBounds(200, 373, 102, 14);
		add_student.add(lblHomeAddress);
		
		JLabel lblDoj = new JLabel("DOJ ");
		lblDoj.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblDoj.setBounds(200, 416, 135, 14);
		add_student.add(lblDoj);
		
		stu_name = new JTextField();
		stu_name.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		stu_name.setBounds(372, 67, 111, 20);
		add_student.add(stu_name);
		stu_name.setColumns(10);
		
		stu_father = new JTextField();
		stu_father.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		stu_father.setBounds(372, 109, 111, 20);
		add_student.add(stu_father);
		stu_father.setColumns(10);
		
		stu_phone = new JTextField();
		stu_phone.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		stu_phone.setBounds(372, 155, 111, 20);
		add_student.add(stu_phone);
		stu_phone.setColumns(10);
		
		stu_dob = new JTextField();
		stu_dob.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		stu_dob.setBounds(372, 243, 111, 20);
		add_student.add(stu_dob);
		stu_dob.setColumns(10);
		
		stu_address = new JTextField();
		stu_address.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		stu_address.setBounds(372, 370, 111, 20);
		add_student.add(stu_address);
		stu_address.setColumns(10);
		
		stu_doj = new JTextField();
		stu_doj.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		stu_doj.setBounds(372, 413, 111, 20);
		add_student.add(stu_doj);
		stu_doj.setColumns(10);
		
		
		
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setBackground(SystemColor.control);
		btnInsert.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		Image img17 = new ImageIcon(this.getClass().getResource("/insert.png")).getImage();
		btnInsert.setIcon(new ImageIcon(img17));
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db_config.connect_to_database();
				
				try {
				hostel_operations ho = new hostel_operations();
				HashMap<Integer, String> student = new HashMap<Integer, String>();
				student.put(1, stu_name.getText());
				student.put(2, stu_father.getText());
				student.put(3, stu_phone.getText());
				
				student.put(4, (String) stu_gender.getItemAt(stu_gender.getSelectedIndex()));
				student.put(5, stu_dob.getText());
				student.put(6,(String) stu_branch.getItemAt(stu_gender.getSelectedIndex()));
				student.put(7, (String) stu_sem.getItemAt(stu_gender.getSelectedIndex()));
				student.put(8, stu_address.getText());
				student.put(9, stu_doj.getText());
				
				
				ho.add_student(student);
				
				JOptionPane.showMessageDialog(null, "Data Inserted");
				String id=ho.pass_room(stu_name.getText());
				CardLayout c =(CardLayout)(contentPane.getLayout());
				c.show(contentPane,"p10");
				room_id.setText(id);
				stu_name.setText("");
				stu_father.setText("");
				stu_phone.setText("");
				stu_dob.setText("");
				stu_address.setText("");
				stu_doj.setText("");
				
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Failed");
					//System.out.println(ex);
				}
			}
		});
		btnInsert.setBounds(258, 478, 135, 33);
		add_student.add(btnInsert);
		String gender[] = {"M","F"};
		stu_gender = new JComboBox(gender);
		stu_gender.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		
			
		stu_gender.setBounds(372, 196, 111, 20);
		add_student.add(stu_gender);
		Image img41 = new ImageIcon(this.getClass().getResource("/insert9.jpg")).getImage();
		
		String branch[] = {"CSE","ECE","ISE","MECH"};
		stu_branch = new JComboBox(branch);
		stu_branch.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		stu_branch.setBounds(372, 286, 111, 20);
		add_student.add(stu_branch);
		
		String sem[] = {"1","2","3","4","5","6","7","8"};
		 stu_sem = new JComboBox(sem);
		 stu_sem.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		stu_sem.setBounds(372, 330, 111, 20);
		add_student.add(stu_sem);
		
		JLabel lblDdmmyyyy = new JLabel("(DD-MM-YYYY)");
		lblDdmmyyyy.setBounds(200, 259, 75, 14);
		add_student.add(lblDdmmyyyy);
		
		JLabel lblDdmmyyyy_1 = new JLabel("(DD-MM-YYYY)");
		lblDdmmyyyy_1.setBounds(189, 430, 86, 14);
		add_student.add(lblDdmmyyyy_1);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(img41));
		label.setBounds(0, 0, 688, 573);
		add_student.add(label);
		
		JPanel retrieve_student = new JPanel();
		retrieve_student.setBackground(new Color(32, 178, 170));
		contentPane.add(retrieve_student, "p3");
		retrieve_student.setLayout(null);
		
		JLabel lblEnterStudentId = new JLabel("Enter Student Name");
		lblEnterStudentId.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblEnterStudentId.setBounds(79, 47, 145, 20);
		retrieve_student.add(lblEnterStudentId);
		
		stu1_name = new JTextField();
		stu1_name.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		stu1_name.setBounds(272, 44, 86, 20);
		retrieve_student.add(stu1_name);
		stu1_name.setColumns(10);
		
		
		
		JButton btnGo = new JButton("Get Details");
		btnGo.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		Image img19 = new ImageIcon(this.getClass().getResource("/getdetails.png")).getImage();
		btnGo.setIcon(new ImageIcon(img19));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVisible(false);
		scrollPane.setBackground(new Color(135, 206, 235));
		scrollPane.setBounds(10, 124, 668, 255);
		retrieve_student.add(scrollPane);
		
		table_ret_stu = new JTable();
		table_ret_stu.setVisible(false);
		scrollPane.setViewportView(table_ret_stu);
		
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				db_config.connect_to_database();
				scrollPane.setVisible(true);
				table_ret_stu.setVisible(true);
				try {
						ResultSet rs = db_config.getData_operation(stu1_name.getText(),"student");
						table_ret_stu.setModel(DbUtils.resultSetToTableModel(rs));
						stu1_name.setText("");
						
				}catch(Exception e1) {
					System.out.println("error"+e1);
				}
			}
		});
		btnGo.setBounds(469, 37, 138, 33);
		retrieve_student.add(btnGo);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 96, 688, 2);
		retrieve_student.add(separator);
		
		
		
		JPanel update_student = new JPanel();
		update_student.setBackground(new Color(32, 178, 170));
		contentPane.add(update_student, "p4");
		update_student.setLayout(null);
		
		JLabel lblEnterStudentId_1 = new JLabel("Enter Student Name");
		lblEnterStudentId_1.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblEnterStudentId_1.setBounds(88, 37, 149, 20);
		update_student.add(lblEnterStudentId_1);
		
		stu2_name = new JTextField();
		stu2_name.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		stu2_name.setBounds(286, 34, 133, 20);
		update_student.add(stu2_name);
		stu2_name.setColumns(10);
		
		JButton btnGo_1 = new JButton("Get Details");
		btnGo_1.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		Image img20 = new ImageIcon(this.getClass().getResource("/getdetails.png")).getImage();
		btnGo_1.setIcon(new ImageIcon(img20));
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setVisible(false);
		scrollPane_5.setBounds(10, 97, 668, 181);
		update_student.add(scrollPane_5);
		
		table_up_stu = new JTable();
		table_up_stu.setVisible(false);
		scrollPane_5.setViewportView(table_up_stu);
		
		btnGo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db_config.connect_to_database();
				scrollPane_5.setVisible(true);
				table_up_stu.setVisible(true);
				search_str = stu2_name.getText();
				try {
						ResultSet rs = db_config.getData_operation(stu2_name.getText(),"student");
						table_up_stu.setModel(DbUtils.resultSetToTableModel(rs));
						stu2_name.setText("");
						
				}catch(Exception e1) {
					System.out.println("error"+e1);
				}
			}
		});
		btnGo_1.setBounds(493, 22, 133, 33);
		update_student.add(btnGo_1);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 84, 688, 2);
		update_student.add(separator_1);
		
		JPanel stu_up_details = new JPanel();
		stu_up_details.setBackground(new Color(135, 206, 235));
		stu_up_details.setBounds(10, 289, 668, 279);
		update_student.add(stu_up_details);
		stu_up_details.setLayout(null);
		stu_up_details.setVisible(false);
		
		
		
		
		
		//createColumns();
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		Image img18 = new ImageIcon(this.getClass().getResource("/update3.png")).getImage();
		btnUpdate.setIcon(new ImageIcon(img18));
		btnUpdate.setBounds(407, 219, 141, 33);
		stu_up_details.add(btnUpdate);
		
		JLabel lblName_1 = new JLabel("Student ID");
		lblName_1.setFont(new Font("Lucida Sans", Font.BOLD, 12));
		lblName_1.setBounds(34, 29, 78, 14);
		stu_up_details.add(lblName_1);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Lucida Sans", Font.BOLD, 12));
		lblNewLabel.setBounds(34, 59, 46, 14);
		stu_up_details.add(lblNewLabel);
		
		JLabel lblFathersName_1 = new JLabel("Father's Name");
		lblFathersName_1.setFont(new Font("Lucida Sans", Font.BOLD, 12));
		lblFathersName_1.setBounds(34, 90, 89, 14);
		stu_up_details.add(lblFathersName_1);
		
		JLabel lblPhoneNumber_1 = new JLabel("Phone Number");
		lblPhoneNumber_1.setFont(new Font("Lucida Sans", Font.BOLD, 12));
		lblPhoneNumber_1.setBounds(34, 128, 123, 14);
		stu_up_details.add(lblPhoneNumber_1);
		
		JLabel lblGender_1 = new JLabel("Gender");
		lblGender_1.setFont(new Font("Lucida Sans", Font.BOLD, 12));
		lblGender_1.setBounds(34, 167, 46, 14);
		stu_up_details.add(lblGender_1);
		
		JLabel lblDob_1 = new JLabel("DOB");
		lblDob_1.setFont(new Font("Lucida Sans", Font.BOLD, 12));
		lblDob_1.setBounds(34, 204, 46, 14);
		stu_up_details.add(lblDob_1);
		
		stu2_id = new JTextField();
		stu2_id.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		stu2_id.setBounds(183, 26, 86, 20);
		stu_up_details.add(stu2_id);
		stu2_id.setColumns(10);
		
		stu2_name2 = new JTextField();
		stu2_name2.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		stu2_name2.setBounds(183, 56, 86, 20);
		stu_up_details.add(stu2_name2);
		stu2_name2.setColumns(10);
		
		stu2_father = new JTextField();
		stu2_father.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		stu2_father.setBounds(183, 87, 86, 20);
		stu_up_details.add(stu2_father);
		stu2_father.setColumns(10);
		
		stu2_phone = new JTextField();
		stu2_phone.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		stu2_phone.setBounds(183, 125, 86, 20);
		stu_up_details.add(stu2_phone);
		stu2_phone.setColumns(10);
		
		stu2_gender = new JTextField();
		stu2_gender.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		stu2_gender.setBounds(183, 164, 86, 20);
		stu_up_details.add(stu2_gender);
		stu2_gender.setColumns(10);
		
		stu2_dob = new JTextField();
		stu2_dob.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		stu2_dob.setBounds(183, 201, 86, 20);
		stu_up_details.add(stu2_dob);
		stu2_dob.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Branch");
		lblNewLabel_1.setFont(new Font("Lucida Sans", Font.BOLD, 12));
		lblNewLabel_1.setBounds(346, 29, 46, 14);
		stu_up_details.add(lblNewLabel_1);
		
		JLabel lblSemester_1 = new JLabel("Semester");
		lblSemester_1.setFont(new Font("Lucida Sans", Font.BOLD, 12));
		lblSemester_1.setBounds(346, 59, 78, 14);
		stu_up_details.add(lblSemester_1);
		
		JLabel lblNewLabel_2 = new JLabel("Home Address");
		lblNewLabel_2.setFont(new Font("Lucida Sans", Font.BOLD, 12));
		lblNewLabel_2.setBounds(346, 90, 98, 14);
		stu_up_details.add(lblNewLabel_2);
		
		JLabel lblDoj_1 = new JLabel("DOJ");
		lblDoj_1.setFont(new Font("Lucida Sans", Font.BOLD, 12));
		lblDoj_1.setBounds(346, 128, 46, 14);
		stu_up_details.add(lblDoj_1);
		
		JLabel lblDol_1 = new JLabel("DOL");
		lblDol_1.setFont(new Font("Lucida Sans", Font.BOLD, 12));
		lblDol_1.setBounds(346, 167, 46, 14);
		stu_up_details.add(lblDol_1);
		
		stu2_branch = new JTextField();
		stu2_branch.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		stu2_branch.setBounds(504, 26, 86, 20);
		stu_up_details.add(stu2_branch);
		stu2_branch.setColumns(10);
		
		stu2_semester = new JTextField();
		stu2_semester.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		stu2_semester.setBounds(504, 56, 86, 20);
		stu_up_details.add(stu2_semester);
		stu2_semester.setColumns(10);
		
		stu2_address = new JTextField();
		stu2_address.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		stu2_address.setBounds(504, 87, 86, 20);
		stu_up_details.add(stu2_address);
		stu2_address.setColumns(10);
		
		stu2_doj = new JTextField();
		stu2_doj.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		stu2_doj.setBounds(504, 125, 86, 20);
		stu_up_details.add(stu2_doj);
		stu2_doj.setColumns(10);
		
		stu2_dol = new JTextField();
		stu2_dol.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		stu2_dol.setBounds(504, 164, 86, 20);
		stu_up_details.add(stu2_dol);
		stu2_dol.setColumns(10);
		
		
		table_up_stu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				stu_up_details.setVisible(true);
				stu2_id.setText(table_up_stu.getValueAt(table_up_stu.getSelectedRow(), 0).toString());
				stu2_name2.setText(table_up_stu.getValueAt(table_up_stu.getSelectedRow(), 1).toString());
				stu2_father.setText(table_up_stu.getValueAt(table_up_stu.getSelectedRow(), 2).toString());
				stu2_phone.setText(table_up_stu.getValueAt(table_up_stu.getSelectedRow(), 3).toString());
				stu2_gender.setText(table_up_stu.getValueAt(table_up_stu.getSelectedRow(), 4).toString());
				stu2_dob.setText(table_up_stu.getValueAt(table_up_stu.getSelectedRow(),5).toString());
				stu2_branch.setText(table_up_stu.getValueAt(table_up_stu.getSelectedRow(), 6).toString());
				stu2_semester.setText(table_up_stu.getValueAt(table_up_stu.getSelectedRow(), 7).toString());
				stu2_address.setText(table_up_stu.getValueAt(table_up_stu.getSelectedRow(), 8).toString());
				stu2_doj.setText(table_up_stu.getValueAt(table_up_stu.getSelectedRow(), 9).toString());
				stu2_dol.setText(table_up_stu.getValueAt(table_up_stu.getSelectedRow(), 10).toString());
			}
		});
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					db_config.connect_to_database();
				
				
				
				String query = "update student set student_id = '"+stu2_id.getText()+"', name = '"+stu2_name2.getText()+"',father_name = '"+stu2_father.getText()+"',phone_number = '"+stu2_phone.getText()+"',gender = '"+stu2_gender.getText()+"',dob = '"+stu2_dob.getText()+"',branch = '"+stu2_branch.getText()+"',semester = '"+stu2_semester.getText()+"',home_address = '"+stu2_address.getText()+"',doj = '"+stu2_doj.getText()+"',dol = '"+stu2_dol.getText()+"'where student_id='"+stu2_id.getText()+"'";
				db_config.update_operation(query);
				
				ResultSet rs = db_config.getData_operation(stu2_name2.getText(),"student");
				table_up_stu.setModel(DbUtils.resultSetToTableModel(rs));
				
				JOptionPane.showMessageDialog(null, "Data Updated");
				stu2_id.setText("");
				stu2_name2.setText("");
				stu2_father.setText("");
				stu2_phone.setText("");
				stu2_gender.setText("");
				stu2_dob.setText("");
				stu2_branch.setText("");
				stu2_semester.setText("");
				stu2_address.setText("");
				stu2_doj.setText("");
				stu2_dol.setText("");
				}catch(Exception e2) {
					JOptionPane.showMessageDialog(null, "Update Failed");
				}
			}
		});
		
		JPanel delete_student = new JPanel();
		delete_student.setBackground(new Color(32, 178, 170));
		contentPane.add(delete_student, "p5");
		delete_student.setLayout(null);
		
		JLabel lblEnterStudentId_2 = new JLabel("Enter Student Name");
		lblEnterStudentId_2.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblEnterStudentId_2.setBounds(90, 56, 140, 20);
		delete_student.add(lblEnterStudentId_2);
		
		stu3_name = new JTextField();
		stu3_name.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		stu3_name.setBounds(287, 53, 118, 20);
		delete_student.add(stu3_name);
		stu3_name.setColumns(10);
		
		JButton btnUpdate_1 = new JButton("Get Details");
		btnUpdate_1.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		Image img21 = new ImageIcon(this.getClass().getResource("/getdetails.png")).getImage();
		btnUpdate_1.setIcon(new ImageIcon(img21));
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBackground(new Color(210, 180, 140));
		scrollPane_4.setVisible(false);
		scrollPane_4.setBounds(10, 125, 668, 230);
		delete_student.add(scrollPane_4);
		
		table_del_stu = new JTable();
		table_del_stu.setVisible(false);
		scrollPane_4.setViewportView(table_del_stu);
		
		btnUpdate_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				db_config.connect_to_database();
				scrollPane_4.setVisible(true);
				table_del_stu.setVisible(true);
				try {
						ResultSet rs = db_config.getData_operation(stu3_name.getText(),"student");
						table_del_stu.setModel(DbUtils.resultSetToTableModel(rs));
						stu3_name.setText("");
						
				}catch(Exception e1) {
					System.out.println("error"+e1);
				}
			}
		});
		btnUpdate_1.setBounds(479, 46, 140, 33);
		delete_student.add(btnUpdate_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 103, 688, 2);
		delete_student.add(separator_2);
		
		JPanel stu_del_details = new JPanel();
		stu_del_details.setBackground(new Color(176, 224, 230));
		stu_del_details.setBounds(20, 366, 658, 196);
		delete_student.add(stu_del_details);
		stu_del_details.setLayout(null);
		stu_del_details.setVisible(false);
		
		
		table_del_stu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				stu_del_details.setVisible(true);
				stu3_id.setText(table_del_stu.getValueAt(table_del_stu.getSelectedRow(), 0).toString());
			}
		});
		
		
		
		JLabel lblStudentId_2 = new JLabel("Student ID");
		lblStudentId_2.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblStudentId_2.setBounds(206, 41, 86, 14);
		stu_del_details.add(lblStudentId_2);
		
		stu3_id = new JTextField();
		stu3_id.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		stu3_id.setBounds(391, 38, 86, 20);
		stu_del_details.add(stu3_id);
		stu3_id.setColumns(10);
		
		JButton btnDelete_1 = new JButton("Delete");
		btnDelete_1.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		Image img22 = new ImageIcon(this.getClass().getResource("/delete2.png")).getImage();
		btnDelete_1.setIcon(new ImageIcon(img22));
		btnDelete_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				db_config.connect_to_database();
				String query = "update student set dol = NOW() where student_id ='"+stu3_id.getText()+"';";
				db_config.update_operation(query);
				

				query = "update allot_student set is_deleted='Y' where student_id = "+stu3_id.getText()+";";
				db_config.update_operation(query);
				ResultSet rs3 = db_config.delete_operation(stu3_id.getText(),"student");
				table_del_stu.setModel(DbUtils.resultSetToTableModel(rs3));
				JOptionPane.showMessageDialog(null, "Deleted");
				stu3_id.setText("");
				CardLayout c =(CardLayout)(contentPane.getLayout());
				c.show(contentPane,"p11");
				}
				catch(Exception exe) {
					JOptionPane.showMessageDialog(null, "Could not Delete");
					//System.out.println(exe);
				}
			}
		});
		btnDelete_1.setBounds(277, 110, 127, 33);
		stu_del_details.add(btnDelete_1);
		
		JPanel add_staff = new JPanel();
		contentPane.add(add_staff, "p6");
		add_staff.setLayout(null);
		
		JLabel lblEnterStaffDetails = new JLabel("Enter Staff Details");
		lblEnterStaffDetails.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		lblEnterStaffDetails.setBounds(253, 11, 171, 21);
		add_staff.add(lblEnterStaffDetails);
		
		JLabel lblName_3 = new JLabel("Name");
		lblName_3.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblName_3.setBounds(191, 75, 46, 14);
		add_staff.add(lblName_3);
		
		JLabel lblPhoneNumber_3 = new JLabel("Phone Number");
		lblPhoneNumber_3.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblPhoneNumber_3.setBounds(191, 112, 123, 14);
		add_staff.add(lblPhoneNumber_3);
		
		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblDepartment.setBounds(191, 151, 100, 14);
		add_staff.add(lblDepartment);
		
		JLabel lblDob_3 = new JLabel("DOB");
		lblDob_3.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblDob_3.setBounds(191, 190, 46, 14);
		add_staff.add(lblDob_3);
		
		JLabel lblAge_3 = new JLabel("Hostel ID");
		lblAge_3.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblAge_3.setBounds(191, 269, 86, 17);
		add_staff.add(lblAge_3);
		
		JLabel lblGender_3 = new JLabel("Gender");
		lblGender_3.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblGender_3.setBounds(191, 231, 70, 14);
		add_staff.add(lblGender_3);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblAddress.setBounds(191, 312, 70, 14);
		add_staff.add(lblAddress);
		
		JLabel lblSalary = new JLabel("Salary");
		lblSalary.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblSalary.setBounds(191, 355, 46, 14);
		add_staff.add(lblSalary);
		
		JLabel lblDoj_3 = new JLabel("DOJ");
		lblDoj_3.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblDoj_3.setBounds(191, 398, 46, 14);
		add_staff.add(lblDoj_3);
		
		st_name = new JTextField();
		st_name.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		st_name.setBounds(380, 72, 86, 20);
		add_staff.add(st_name);
		st_name.setColumns(10);
		
		st_phone = new JTextField();
		st_phone.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		st_phone.setBounds(380, 109, 86, 20);
		add_staff.add(st_phone);
		st_phone.setColumns(10);
		
		st_dob = new JTextField();
		st_dob.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		st_dob.setBounds(380, 187, 86, 20);
		add_staff.add(st_dob);
		st_dob.setColumns(10);
		
		st_address = new JTextField();
		st_address.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		st_address.setBounds(380, 309, 86, 20);
		add_staff.add(st_address);
		st_address.setColumns(10);
		
		st_salary = new JTextField();
		st_salary.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		st_salary.setBounds(380, 352, 86, 20);
		add_staff.add(st_salary);
		st_salary.setColumns(10);
		
		st_doj = new JTextField();
		st_doj.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		st_doj.setBounds(380, 395, 86, 20);
		add_staff.add(st_doj);
		st_doj.setColumns(10);
		
		JButton btnInsert_1 = new JButton("Insert");
		btnInsert_1.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		Image img23 = new ImageIcon(this.getClass().getResource("/insert.png")).getImage();
		btnInsert_1.setIcon(new ImageIcon(img23));
		btnInsert_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				db_config.connect_to_database();
				
				try {
				hostel_operations ho = new hostel_operations();
				HashMap<Integer, String> faculty = new HashMap<Integer, String>();
				faculty.put(1, st_name.getText());
				faculty.put(2, st_phone.getText());
				faculty.put(3, (String) st_dept.getItemAt(st_dept.getSelectedIndex()));
				faculty.put(4, st_dob.getText());
				
				faculty.put(5, (String) st_gender.getItemAt(st_gender.getSelectedIndex()));
				faculty.put(6, (String) st_hid.getItemAt(st_hid.getSelectedIndex()));
				faculty.put(7, st_address.getText());
				faculty.put(8, st_salary.getText());
				faculty.put(9, st_doj.getText());
				
				ho.add_faculty(faculty);
				ho.faculty_incharge_rooms((String) st_hid.getItemAt(st_hid.getSelectedIndex()));
				JOptionPane.showMessageDialog(null, "Data Inserted");
				
				st_name.setText("");
				st_phone.setText("");		
				st_dob.setText("");
				st_address.setText("");
				st_salary.setText("");
				st_doj.setText("");
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Failed");
					//System.out.println(ex);
				}
				
			}
		});
		btnInsert_1.setBounds(267, 468, 123, 33);
		add_staff.add(btnInsert_1);
		
		String st_gend [] = {"M","F"};
		 st_gender = new JComboBox(st_gend);
		 st_gender.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		st_gender.setBounds(380, 228, 86, 20);
		add_staff.add(st_gender);
		Image img42 = new ImageIcon(this.getClass().getResource("/insertstaff5.jpg")).getImage();
		
		String hostel_id[] = {"1","2"};
		 st_hid = new JComboBox(hostel_id);
		 st_hid.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		st_hid.setBounds(380, 268, 86, 20);
		add_staff.add(st_hid);
		
		String dept[] = {"CSE","ECE","MECH","ISE"};
		 st_dept = new JComboBox(dept);
		 st_dept.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		st_dept.setBounds(380, 149, 86, 20);
		add_staff.add(st_dept);
		
		JLabel lblddmmyyyy = new JLabel("(DD-MM-YYYY)");
		lblddmmyyyy.setBounds(191, 204, 100, 14);
		add_staff.add(lblddmmyyyy);
		
		JLabel lblddmmyyyy_1 = new JLabel("(DD-MM-YYYY)");
		lblddmmyyyy_1.setBounds(191, 411, 86, 14);
		add_staff.add(lblddmmyyyy_1);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(img42));
		label_2.setBounds(0, 0, 688, 573);
		add_staff.add(label_2);
		
		JPanel retrieve_staff = new JPanel();
		retrieve_staff.setBackground(new Color(255, 218, 185));
		contentPane.add(retrieve_staff, "p7");
		retrieve_staff.setLayout(null);
		
		JLabel lblEnterStaffId = new JLabel("Enter Staff Name");
		lblEnterStaffId.setFont(new Font("Lucida Sans", Font.BOLD, 15));
		lblEnterStaffId.setBounds(97, 40, 135, 23);
		retrieve_staff.add(lblEnterStaffId);
		
		st1_name = new JTextField();
		st1_name.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		st1_name.setBounds(289, 37, 135, 26);
		retrieve_staff.add(st1_name);
		st1_name.setColumns(10);
		
		JButton btnGo_2 = new JButton("Get Details");
		btnGo_2.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		Image img24 = new ImageIcon(this.getClass().getResource("/getdetails.png")).getImage();
		btnGo_2.setIcon(new ImageIcon(img24));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVisible(false);
		scrollPane_1.setBounds(10, 131, 668, 267);
		retrieve_staff.add(scrollPane_1);
		
		table_ret_st = new JTable();
		table_ret_st.setVisible(false);
		scrollPane_1.setViewportView(table_ret_st);
		
		btnGo_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				db_config.connect_to_database();
				scrollPane_1.setVisible(true);
				table_ret_st.setVisible(true);
				try {
					ResultSet rs = db_config.getData_operation(st1_name.getText(),"faculty");
					table_ret_st.setModel(DbUtils.resultSetToTableModel(rs));
					st1_name.setText("");
			}catch(Exception e1) {
				System.out.println("error"+e1);
			}
			}
		});
		btnGo_2.setBounds(499, 30, 127, 33);
		retrieve_staff.add(btnGo_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(0, 96, 688, 2);
		retrieve_staff.add(separator_3);
		
		
		
		JPanel update_staff = new JPanel();
		update_staff.setBackground(new Color(255, 218, 185));
		contentPane.add(update_staff, "p8");
		update_staff.setLayout(null);
		
		JLabel lblEnterStaffId_1 = new JLabel("Enter Staff Name");
		lblEnterStaffId_1.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblEnterStaffId_1.setBounds(105, 34, 121, 20);
		update_staff.add(lblEnterStaffId_1);
		
		st2_name = new JTextField();
		st2_name.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		st2_name.setBounds(290, 35, 112, 20);
		update_staff.add(st2_name);
		st2_name.setColumns(10);
		
		JButton btnGo_3 = new JButton("Get Details");
		btnGo_3.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		Image img26 = new ImageIcon(this.getClass().getResource("/getdetails.png")).getImage();
		btnGo_3.setIcon(new ImageIcon(img26));
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setVisible(false);
		scrollPane_6.setBackground(SystemColor.control);
		scrollPane_6.setBounds(10, 102, 668, 190);
		update_staff.add(scrollPane_6);
		
		table_up_st = new JTable();
		table_up_st.setVisible(false);
		scrollPane_6.setViewportView(table_up_st);
		
		btnGo_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				db_config.connect_to_database();
				scrollPane_6.setVisible(true);
				table_up_st.setVisible(true);
				try {
					ResultSet rs = db_config.getData_operation(st2_name.getText(),"faculty");
					table_up_st.setModel(DbUtils.resultSetToTableModel(rs));
					st2_name.setText("");
			}catch(Exception e1) {
				System.out.println("error"+e1);
			}
			}
		});
		btnGo_3.setBounds(480, 21, 133, 33);
		update_staff.add(btnGo_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(0, 89, 688, 2);
		update_staff.add(separator_4);
		
		
		JPanel staff_up_details = new JPanel();
		staff_up_details.setBackground(new Color(204, 204, 204));
		staff_up_details.setBounds(10, 300, 668, 234);
		update_staff.add(staff_up_details);
		staff_up_details.setLayout(null);
		staff_up_details.setVisible(false);
		
		JLabel lblStaffId = new JLabel("Staff ID");
		lblStaffId.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblStaffId.setBounds(25, 11, 78, 14);
		staff_up_details.add(lblStaffId);
		
		JLabel lblName_2 = new JLabel("Name");
		lblName_2.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblName_2.setBounds(25, 47, 46, 14);
		staff_up_details.add(lblName_2);
		
		JLabel lblPhoneNumber_2 = new JLabel("Phone Number");
		lblPhoneNumber_2.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblPhoneNumber_2.setBounds(25, 85, 107, 14);
		staff_up_details.add(lblPhoneNumber_2);
		
		JLabel lblDepartment_1 = new JLabel("Department");
		lblDepartment_1.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblDepartment_1.setBounds(25, 121, 107, 14);
		staff_up_details.add(lblDepartment_1);
		
		JLabel lblDob_2 = new JLabel("DOB");
		lblDob_2.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblDob_2.setBounds(25, 153, 46, 14);
		staff_up_details.add(lblDob_2);
		
		st2_id = new JTextField();
		st2_id.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		st2_id.setBounds(183, 8, 86, 20);
		staff_up_details.add(st2_id);
		st2_id.setColumns(10);
		
		st2_name2 = new JTextField();
		st2_name2.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		st2_name2.setBounds(183, 44, 86, 20);
		staff_up_details.add(st2_name2);
		st2_name2.setColumns(10);
		
		st2_phone = new JTextField();
		st2_phone.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		st2_phone.setBounds(183, 82, 86, 20);
		staff_up_details.add(st2_phone);
		st2_phone.setColumns(10);
		
		st2_dept = new JTextField();
		st2_dept.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		st2_dept.setBounds(183, 118, 86, 20);
		staff_up_details.add(st2_dept);
		st2_dept.setColumns(10);
		
		st2_dob = new JTextField();
		st2_dob.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		st2_dob.setBounds(183, 150, 86, 20);
		staff_up_details.add(st2_dob);
		st2_dob.setColumns(10);
		
		JLabel lblGender_2 = new JLabel("Gender");
		lblGender_2.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblGender_2.setBounds(25, 199, 78, 14);
		staff_up_details.add(lblGender_2);
		
		JLabel lblHomeAddress_2 = new JLabel("Home Address");
		lblHomeAddress_2.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblHomeAddress_2.setBounds(361, 47, 107, 14);
		staff_up_details.add(lblHomeAddress_2);
		
		JLabel lblSalary_1 = new JLabel("Salary");
		lblSalary_1.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblSalary_1.setBounds(361, 85, 46, 14);
		staff_up_details.add(lblSalary_1);
		
		JLabel lblDoj_2 = new JLabel("DOJ");
		lblDoj_2.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblDoj_2.setBounds(361, 121, 46, 14);
		staff_up_details.add(lblDoj_2);
		
		JLabel lblDol_2 = new JLabel("DOL");
		lblDol_2.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblDol_2.setBounds(361, 153, 46, 14);
		staff_up_details.add(lblDol_2);
		
		JButton btnUpdate_2 = new JButton("Update");
		btnUpdate_2.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		Image img27 = new ImageIcon(this.getClass().getResource("/update3.png")).getImage();
		btnUpdate_2.setIcon(new ImageIcon(img27));
		btnUpdate_2.setBounds(440, 190, 130, 33);
		staff_up_details.add(btnUpdate_2);
		
		st2_gender = new JTextField();
		st2_gender.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		st2_gender.setBounds(183, 196, 86, 20);
		staff_up_details.add(st2_gender);
		st2_gender.setColumns(10);
		
		st2_address = new JTextField();
		st2_address.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		st2_address.setBounds(508, 44, 86, 20);
		staff_up_details.add(st2_address);
		st2_address.setColumns(10);
		
		st2_salary = new JTextField();
		st2_salary.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		st2_salary.setBounds(508, 79, 86, 20);
		staff_up_details.add(st2_salary);
		st2_salary.setColumns(10);
		
		st2_doj = new JTextField();
		st2_doj.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		st2_doj.setBounds(508, 118, 86, 20);
		staff_up_details.add(st2_doj);
		st2_doj.setColumns(10);
		
		st2_dol = new JTextField();
		st2_dol.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		st2_dol.setBounds(508, 150, 86, 20);
		staff_up_details.add(st2_dol);
		st2_dol.setColumns(10);
		
		JLabel lblHostelId_2 = new JLabel("Hostel ID");
		lblHostelId_2.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblHostelId_2.setBounds(361, 11, 78, 14);
		staff_up_details.add(lblHostelId_2);
		
		st2_hid = new JTextField();
		st2_hid.setBounds(508, 9, 86, 20);
		staff_up_details.add(st2_hid);
		st2_hid.setColumns(10);
		
		
		table_up_st.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				staff_up_details.setVisible(true);
				st2_id.setText(table_up_st.getValueAt(table_up_st.getSelectedRow(), 0).toString());
				st2_name2.setText(table_up_st.getValueAt(table_up_st.getSelectedRow(), 1).toString());
				st2_phone.setText(table_up_st.getValueAt(table_up_st.getSelectedRow(), 2).toString());
				st2_dept.setText(table_up_st.getValueAt(table_up_st.getSelectedRow(), 3).toString());
				st2_dob.setText(table_up_st.getValueAt(table_up_st.getSelectedRow(), 4).toString());
				st2_gender.setText(table_up_st.getValueAt(table_up_st.getSelectedRow(), 5).toString());
				st2_hid.setText(table_up_st.getValueAt(table_up_st.getSelectedRow(), 6).toString());
				st2_address.setText(table_up_st.getValueAt(table_up_st.getSelectedRow(), 7).toString());
				st2_salary.setText(table_up_st.getValueAt(table_up_st.getSelectedRow(), 8).toString());
				st2_doj.setText(table_up_st.getValueAt(table_up_st.getSelectedRow(), 9).toString());
				st2_dol.setText(table_up_st.getValueAt(table_up_st.getSelectedRow(), 10).toString());
			}
		});
		btnUpdate_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					db_config.connect_to_database();
					
					ResultSet rs = db_config.getData_operation(st2_name2.getText(),"faculty");
					table_up_st.setModel(DbUtils.resultSetToTableModel(rs));
				String query = "update faculty set faculty_id = '"+st2_id.getText()+"',name = '"+st2_name2.getText()+"',phone_number = '"+st2_phone.getText()+"',department = '"+st2_dept.getText()+"',dob = '"+st2_dob.getText()+"',gender = '"+st2_gender.getText()+"',hostel_id = '"+st2_hid.getText()+"',address = '"+st2_address.getText()+"',salary = '"+st2_salary.getText()+"',doj = '"+st2_doj.getText()+"',dol = '"+st2_dol.getText()+"'where faculty_id='"+st2_id.getText()+"'";
				db_config.update_operation(query);
				
				ResultSet rs2 = db_config.getData_operation(st2_name2.getText(),"faculty");
				table_up_st.setModel(DbUtils.resultSetToTableModel(rs2));
				JOptionPane.showMessageDialog(null, "Data Updated");
				st2_id.setText("");
				st2_name2.setText("");
				st2_phone.setText("");
				st2_dept.setText("");
				st2_dob.setText("");
				st2_hid.setText("");
				st2_gender.setText("");
				st2_address.setText("");
				st2_salary.setText("");
				st2_doj.setText("");
				st2_dol.setText("");
				}catch(Exception e2) {
					JOptionPane.showMessageDialog(null, "Update Failed");
					//System.out.println(e2);
				}
			}
		});
		
		JPanel delete_staff = new JPanel();
		delete_staff.setBackground(new Color(255, 218, 185));
		contentPane.add(delete_staff, "p9");
		delete_staff.setLayout(null);
		
		JLabel lblEnterStaffId_2 = new JLabel("Enter Staff Name");
		lblEnterStaffId_2.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblEnterStaffId_2.setBounds(81, 41, 132, 23);
		delete_staff.add(lblEnterStaffId_2);
		
		st3_name = new JTextField();
		st3_name.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		st3_name.setBounds(286, 38, 122, 26);
		delete_staff.add(st3_name);
		st3_name.setColumns(10);
		
		JButton btnDelete = new JButton("Get Details");
		btnDelete.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		Image img25 = new ImageIcon(this.getClass().getResource("/getdetails.png")).getImage();
		btnDelete.setIcon(new ImageIcon(img25));
		

		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setVisible(false);
		scrollPane_7.setBounds(10, 108, 668, 210);
		delete_staff.add(scrollPane_7);
		
		table_del_st = new JTable();
		table_del_st.setVisible(false);
		scrollPane_7.setViewportView(table_del_st);
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				scrollPane_7.setVisible(true);
				table_del_st.setVisible(true);
				db_config.connect_to_database();
				try {
					ResultSet rs = db_config.getData_operation(st3_name.getText(),"faculty");
					table_del_st.setModel(DbUtils.resultSetToTableModel(rs));
					st3_name.setText("");
			}catch(Exception e1) {
				System.out.println("error"+e1);
			}
			}
		});
		btnDelete.setBounds(496, 31, 132, 33);
		delete_staff.add(btnDelete);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(0, 95, 688, 2);
		delete_staff.add(separator_5);
		JPanel st_del_details = new JPanel();
		st_del_details.setBackground(new Color(204, 204, 204));
		st_del_details.setBounds(20, 329, 658, 210);
		delete_staff.add(st_del_details);
		st_del_details.setLayout(null);
		st_del_details.setVisible(false);
		
		table_del_st.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				st_del_details.setVisible(true);
				st3_id.setText(table_del_st.getValueAt(table_del_st.getSelectedRow(), 0).toString());
				st3_hid.setText(table_del_st.getValueAt(table_del_st.getSelectedRow(), 6).toString());
			}
		});
		
		
		
		JLabel lblStaffName = new JLabel("Staff ID");
		lblStaffName.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblStaffName.setBounds(197, 45, 92, 14);
		st_del_details.add(lblStaffName);
		
		st3_id = new JTextField();
		st3_id.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		st3_id.setBounds(364, 42, 86, 20);
		st_del_details.add(st3_id);
		st3_id.setColumns(10);
		
		JButton btnDelete_2 = new JButton("Delete");
		btnDelete_2.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		Image img28 = new ImageIcon(this.getClass().getResource("/delete2.png")).getImage();
		btnDelete_2.setIcon(new ImageIcon(img28));
		btnDelete_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					db_config.connect_to_database();
					hostel_operations ho = new hostel_operations();
					String query = "update faculty set dol = NOW() where faculty_id ='"+st3_id.getText()+"';";
					db_config.update_operation(query);
					ho.faculty_incharge_rooms(st3_hid.getText());
					ResultSet rs3 = db_config.delete_operation(st3_id.getText(),"faculty");
					table_del_st.setModel(DbUtils.resultSetToTableModel(rs3));
					JOptionPane.showMessageDialog(null, "Deleted");
					st3_id.setText("");
					st3_hid.setText("");
					}
					catch(Exception exe) {
						JOptionPane.showMessageDialog(null, "Could not Delete");
						//System.out.println(exe);
					}
			}
		});
		btnDelete_2.setBounds(271, 140, 128, 33);
		st_del_details.add(btnDelete_2);
		
		JLabel lblHostelId_1 = new JLabel("Hostel ID");
		lblHostelId_1.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblHostelId_1.setBounds(197, 94, 92, 14);
		st_del_details.add(lblHostelId_1);
		
		st3_hid = new JTextField();
		st3_hid.setBounds(364, 92, 86, 20);
		st_del_details.add(st3_hid);
		st3_hid.setColumns(10);
		
		JPanel assign_room = new JPanel();
		
		contentPane.add(assign_room, "p10");
		assign_room.setLayout(null);
		Image img43 = new ImageIcon(this.getClass().getResource("/room2.jpg")).getImage();
		
		JLabel lblRoomAllocation = new JLabel("Room Allocation");
		lblRoomAllocation.setHorizontalAlignment(SwingConstants.CENTER);
		lblRoomAllocation.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblRoomAllocation.setBounds(231, 65, 187, 21);
		assign_room.add(lblRoomAllocation);
		
		JLabel lblHostelId = new JLabel("Student ID");
		lblHostelId.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblHostelId.setBounds(199, 178, 89, 14);
		assign_room.add(lblHostelId);
		
		JLabel lblRoomNumber = new JLabel("Hostel ID");
		lblRoomNumber.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblRoomNumber.setBounds(199, 230, 65, 14);
		assign_room.add(lblRoomNumber);
		
		
		room_id = new JTextField();
		
		
		
		
		room_id.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		room_id.setBounds(359, 172, 86, 20);
		assign_room.add(room_id);
		room_id.setColumns(10);
		
		
		
		JButton btnAssign = new JButton("Assign");
		btnAssign.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		Image img31 = new ImageIcon(this.getClass().getResource("/assign2.png")).getImage();
		btnAssign.setIcon(new ImageIcon(img31));
		
		
		room_no = new JComboBox();
		 room_no.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		 room_no.setBounds(359, 312, 86, 20);
		 assign_room.add(room_no);
		 
		 JLabel label_5 = new JLabel("");
			label_5.setVisible(false);
			label_5.setBounds(185, 304, 271, 48);
			assign_room.add(label_5);
		 
		 JButton btnCheckVacancy = new JButton("Check Vacancy");
		 btnCheckVacancy.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		label_5.setVisible(true);
		 		int flag=-1;
		 		 room = new HashMap<Integer, String>();
				
				room.put(1, room_id.getText());
				room.put(2, (String) room_hid.getItemAt(room_hid.getSelectedIndex()));
				
				
				combobox((String) room_hid.getItemAt(room_hid.getSelectedIndex()));
					try{
						db_config.connect_to_database();
						String query_call="call allot_room(?,@bool)";
						PreparedStatement pst = db_config.conn.prepareStatement(query_call);
						//pst.setInt(1,5);
						pst.setString(1,room_id.getText());
						pst.execute();
						String ret_val ="select @bool";
						PreparedStatement pst1 = db_config.conn.prepareStatement(ret_val);
						ResultSet rs = pst1.executeQuery();
					
						while(rs.next()) {
							flag = rs.getInt("@bool");	
						}
						System.out.println(flag);
						if(flag == 0) {
						JOptionPane.showMessageDialog(null, "Room already alloted to this ID");
						}
						
					}catch(Exception ec) {
						System.out.println(ec);
					}
				
				room.put(3,  (String)room_no.getItemAt(room_no.getSelectedIndex()));
				//pass_room(room);
		 	}
		 });
		 btnCheckVacancy.setBounds(258, 270, 129, 23);
		 assign_room.add(btnCheckVacancy);
		 
		
		btnAssign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db_config.connect_to_database();
				try {
				hostel_operations ho = new hostel_operations();					
				ho.allot_room(room);
				JOptionPane.showMessageDialog(null, "Room Alloted");
				}catch(Exception ex) {
					//System.out.println(ex);
					JOptionPane.showMessageDialog(null, "Failed");
				}
			}
		});
		btnAssign.setBounds(258, 376, 129, 33);
		assign_room.add(btnAssign);
		
		
		
		JLabel lblRoomNumber_1 = new JLabel("Room Number");
		lblRoomNumber_1.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblRoomNumber_1.setBounds(199, 315, 97, 14);
		assign_room.add(lblRoomNumber_1);
		 
		String hid[] = {"1","2"};
		  room_hid = new JComboBox(hid);
		  room_hid.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		 room_hid.setBounds(359, 228, 86, 20);
		 assign_room.add(room_hid);
		  
		  JLabel label_3 = new JLabel("");
		  label_3.setIcon(new ImageIcon(img43));
		  label_3.setBounds(0, 0, 688, 573);
		  assign_room.add(label_3);

		
		JPanel leave_room = new JPanel();
		leave_room.setBackground(new Color(219, 112, 147));
		contentPane.add(leave_room, "p11");
		leave_room.setLayout(null);
		
		JLabel lblEnterStudentId_3 = new JLabel("Enter student Name");
		lblEnterStudentId_3.setFont(new Font("Lucida Sans", Font.BOLD, 14));
		lblEnterStudentId_3.setBounds(75, 46, 164, 17);
		leave_room.add(lblEnterStudentId_3);
		
		room2_name = new JTextField();
		room2_name.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		room2_name.setBounds(280, 45, 142, 20);
		leave_room.add(room2_name);
		room2_name.setColumns(10);
		
		JButton btnLeave = new JButton("Get Details");
		btnLeave.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		Image img29 = new ImageIcon(this.getClass().getResource("/getdetails.png")).getImage();
		btnLeave.setIcon(new ImageIcon(img29));
		
		JScrollPane scrollPane_8 = new JScrollPane();
		scrollPane_8.setVisible(false);
		scrollPane_8.setBounds(10, 136, 668, 217);
		leave_room.add(scrollPane_8);
		
		table_room = new JTable();
		table_room.setVisible(false);
		scrollPane_8.setViewportView(table_room);
		
		btnLeave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollPane_8.setVisible(true);
				table_room.setVisible(true);
				db_config.connect_to_database();
				
				try {
						ResultSet rs = db_config.getData_operation(room2_name.getText(),"room");
						table_room.setModel(DbUtils.resultSetToTableModel(rs));
						room2_name.setText("");
						
				}catch(Exception e1) {
					System.out.println("error"+e1);
				}
			}
		});
		btnLeave.setBounds(497, 33, 127, 33);
		leave_room.add(btnLeave);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setBounds(0, 100, 698, 2);
		leave_room.add(separator_6);
		
		JPanel room_details = new JPanel();
		room_details.setBackground(new Color(255, 204, 204));
		room_details.setBounds(10, 364, 668, 193);
		leave_room.add(room_details);
		room_details.setLayout(null);
		room_details.setVisible(false);
		
		
		table_room.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				room_details.setVisible(true);
				room2_id.setText(table_room.getValueAt(table_room.getSelectedRow(), 0).toString());
			}
		});
		
		
		
		JLabel lblStudentId_3 = new JLabel("Student ID");
		lblStudentId_3.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblStudentId_3.setBounds(200, 48, 86, 14);
		room_details.add(lblStudentId_3);
		
		room2_id = new JTextField();
		room2_id.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		room2_id.setBounds(383, 45, 121, 20);
		room_details.add(room2_id);
		room2_id.setColumns(10);
		
		JButton btnLeave_1 = new JButton("Leave");
		btnLeave_1.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		Image img32 = new ImageIcon(this.getClass().getResource("/leave2.png")).getImage();
		btnLeave_1.setIcon(new ImageIcon(img32));
		btnLeave_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					try {
					db_config.connect_to_database();
					String query = "update student set dol = NOW() where student_id ='"+room2_id.getText()+"';";
					db_config.update_operation(query);
					query = "update allot_student set is_deleted='Y' where student_id = "+room2_id.getText()+";";
					db_config.update_operation(query);
					ResultSet rs3 = db_config.delete_operation(room2_id.getText(),"allot_student");
					table_room.setModel(DbUtils.resultSetToTableModel(rs3));
					JOptionPane.showMessageDialog(null, "Left");
					room2_id.setText("");
					}
					catch(Exception exe) {
						JOptionPane.showMessageDialog(null, "Could not Delete");
						//System.out.println(exe);
					}
			}
		});
		btnLeave_1.setBounds(277, 106, 121, 33);
		room_details.add(btnLeave_1);
		
		JPanel view_room = new JPanel();
		view_room.setBackground(new Color(219, 112, 147));
		contentPane.add(view_room, "p12");
		view_room.setLayout(null);
		
		JLabel lblEnterStudentId_4 = new JLabel("Enter Hostel ID");
		lblEnterStudentId_4.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblEnterStudentId_4.setBounds(92, 42, 102, 14);
		view_room.add(lblEnterStudentId_4);
		
		JButton btnGetDetails = new JButton("Get Details");
		btnGetDetails.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		Image img34 = new ImageIcon(this.getClass().getResource("/getdetails.png")).getImage();
		btnGetDetails.setIcon(new ImageIcon(img34));
		btnGetDetails.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		Image img30 = new ImageIcon(this.getClass().getResource("/getdetails.png")).getImage();
		btnGetDetails.setIcon(new ImageIcon(img30));
		

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setVisible(false);
		scrollPane_2.setBounds(10, 170, 668, 225);
		view_room.add(scrollPane_2);
		
		table_room2 = new JTable();
		table_room2.setVisible(false);
		scrollPane_2.setViewportView(table_room2);
		
		btnGetDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					scrollPane_2.setVisible(true);
					table_room2.setVisible(true);
					db_config.connect_to_database();
					String query="SELECT a.hostel_id, a.room_no, s.student_id, s.name, a.is_deleted, s.phone_number, f.name FROM allot_student a, student s, room r, faculty f WHERE a.hostel_id = r.hostel_id AND a.room_no = r.room_no AND a.student_id = s.student_id AND r.faculty_id = f.faculty_id AND a.hostel_id ="+(String)room3_hid.getItemAt(room3_hid.getSelectedIndex())+" and a.room_no="+(String)room3_no.getItemAt(room3_no.getSelectedIndex())+";";
					
					ResultSet rs =db_config.room_op(query);
					
					table_room2.setModel(DbUtils.resultSetToTableModel(rs));
					
				}catch(Exception exe) {
				//JOptionPane.showMessageDialog(null, "Error");
				System.out.println(exe);
			   }
			}
		});
		btnGetDetails.setBounds(505, 53, 127, 33);
		view_room.add(btnGetDetails);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setBounds(0, 157, 688, 2);
		view_room.add(separator_7);
		
		JLabel lblEnterRoomNumber = new JLabel("Enter Room Number");
		lblEnterRoomNumber.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblEnterRoomNumber.setBounds(92, 97, 154, 14);
		view_room.add(lblEnterRoomNumber);
		
		String hid2[] = {"1","2"};
		 room3_hid = new JComboBox(hid2);
		 room3_hid.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		room3_hid.setBounds(287, 40, 86, 20);
		view_room.add(room3_hid);
		
		String room[] = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"};
		 room3_no = new JComboBox(room);
		 room3_no.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		room3_no.setBounds(287, 95, 86, 20);
		view_room.add(room3_no);
		
		
		JPanel fees_pay = new JPanel();
		fees_pay.setBackground(new Color(255, 239, 213));
		contentPane.add(fees_pay, "p13");
		fees_pay.setLayout(null);
		
		JLabel lblFeesPayment = new JLabel("Fees Payment");
		lblFeesPayment.setBackground(new Color(255, 255, 255));
		lblFeesPayment.setForeground(new Color(0, 128, 128));
		lblFeesPayment.setHorizontalAlignment(SwingConstants.CENTER);
		lblFeesPayment.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 21));
		lblFeesPayment.setBounds(230, 127, 181, 21);
		fees_pay.add(lblFeesPayment);
		
		JLabel lblStudentId = new JLabel("Student ID");
		lblStudentId.setForeground(new Color(0, 0, 255));
		lblStudentId.setFont(new Font("Lucida Sans", Font.BOLD, 15));
		lblStudentId.setBounds(193, 188, 95, 14);
		fees_pay.add(lblStudentId);
		
		JLabel lblMonthyear = new JLabel("Month-Year");
		lblMonthyear.setForeground(new Color(0, 0, 255));
		lblMonthyear.setFont(new Font("Lucida Sans", Font.BOLD, 15));
		lblMonthyear.setBounds(193, 236, 95, 14);
		fees_pay.add(lblMonthyear);
		
		JLabel lblPaymentDate = new JLabel("Payment Date");
		lblPaymentDate.setForeground(new Color(0, 0, 255));
		lblPaymentDate.setFont(new Font("Lucida Sans", Font.BOLD, 15));
		lblPaymentDate.setBounds(193, 289, 129, 17);
		fees_pay.add(lblPaymentDate);
		
		fee_id = new JTextField();
		fee_id.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		fee_id.setBounds(357, 182, 86, 20);
		fees_pay.add(fee_id);
		fee_id.setColumns(10);
		
		fees_mon_yr = new JTextField();
		fees_mon_yr.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		fees_mon_yr.setBounds(357, 233, 86, 20);
		fees_pay.add(fees_mon_yr);
		fees_mon_yr.setColumns(10);
		
		fees_pay_date = new JTextField();
		fees_pay_date.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		fees_pay_date.setBounds(357, 286, 86, 20);
		fees_pay.add(fees_pay_date);
		fees_pay_date.setColumns(10);
		
		JButton btnPay = new JButton("Pay");
		btnPay.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		Image img33 = new ImageIcon(this.getClass().getResource("/fees2.png")).getImage();
		btnPay.setIcon(new ImageIcon(img33));
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db_config.connect_to_database();
				
				try {
				hostel_operations ho = new hostel_operations();
				HashMap<Integer, String> fees = new HashMap<Integer, String>();
				fees.put(1, fee_id.getText());
				fees.put(2, fees_mon_yr.getText());
				fees.put(3, fees_pay_date.getText());
				
				ho.pay_fees(fees);
				
				JOptionPane.showMessageDialog(null, "Fee Paid");
				fee_id.setText("");
				fees_mon_yr.setText("");
				fees_pay_date.setText("");
				
			}catch(Exception ex) {
				//System.out.println(ex);
				JOptionPane.showMessageDialog(null, "Fee not paid");
			}
			}
		});
		
			
		btnPay.setBounds(262, 360, 104, 33);
		fees_pay.add(btnPay);
		Image img44 = new ImageIcon(this.getClass().getResource("/money3.jpg")).getImage();
		
		JLabel lblMmmyyyy = new JLabel("(MMM-YYYY)");
		lblMmmyyyy.setHorizontalAlignment(SwingConstants.CENTER);
		lblMmmyyyy.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMmmyyyy.setForeground(Color.BLUE);
		lblMmmyyyy.setBounds(193, 250, 95, 14);
		fees_pay.add(lblMmmyyyy);
		
		JLabel label_4 = new JLabel("");
		label_4.setForeground(new Color(85, 107, 47));
		label_4.setIcon(new ImageIcon(img44));
		label_4.setBounds(0, 0, 688, 573);
		fees_pay.add(label_4);
		
		JPanel view_fees = new JPanel();
		view_fees.setBackground(new Color(0, 153, 204));
		contentPane.add(view_fees, "p14");
		view_fees.setLayout(null);
		
		JLabel lblStudentId_1 = new JLabel("Student Name");
		lblStudentId_1.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblStudentId_1.setBounds(74, 51, 112, 14);
		view_fees.add(lblStudentId_1);
		
		fee2_name = new JTextField();
		fee2_name.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		fee2_name.setBounds(285, 48, 112, 20);
		view_fees.add(fee2_name);
		fee2_name.setColumns(10);
		
		JButton btnGo_4 = new JButton("Get Details");
		btnGo_4.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		Image img36 = new ImageIcon(this.getClass().getResource("/getdetails.png")).getImage();
		btnGo_4.setIcon(new ImageIcon(img36));
		
		btnGo_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//	scrollPane_3.setVisible(true);
				table_fees.setVisible(true);
				db_config.connect_to_database();
				
				try {
						ResultSet rs = db_config.getData_operation(fee2_name.getText(),"fees");
						table_fees.setModel(DbUtils.resultSetToTableModel(rs));
						fee2_name.setText("");
						
				}catch(Exception e1) {
					System.out.println("error"+e1);
				}
			}
		});
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(20, 163, 640, 234);
		view_fees.add(scrollPane_3);
		
		table_fees = new JTable();
		scrollPane_3.setViewportView(table_fees);
		btnGo_4.setBounds(491, 41, 141, 33);
		view_fees.add(btnGo_4);
		
		JSeparator separator_8 = new JSeparator();
		separator_8.setBounds(10, 150, 688, 2);
		view_fees.add(separator_8);
		
		JButton btnDuesOfCurrent = new JButton("Dues of current year");
		btnDuesOfCurrent.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		Image img37 = new ImageIcon(this.getClass().getResource("/dues.png")).getImage();
		btnDuesOfCurrent.setIcon(new ImageIcon(img37));
		btnDuesOfCurrent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollPane_3.setVisible(true);
				table_fees.setVisible(true);
				db_config.connect_to_database();
				
				try {
						
						hostel_operations ho = new hostel_operations();
						ResultSet rs = ho.get_payment_details(fee2_id.getText());
						table_fees.setModel(DbUtils.resultSetToTableModel(rs));
						fee2_name.setText("");
						
				}catch(Exception e1) {
					System.out.println("error"+e1);
				}
			}
		});
		btnDuesOfCurrent.setBounds(429, 95, 231, 33);
		view_fees.add(btnDuesOfCurrent);
		
		fee2_id = new JTextField();
		fee2_id.setBounds(285, 102, 86, 20);
		view_fees.add(fee2_id);
		fee2_id.setColumns(10);
		
		JLabel lblStudentId_4 = new JLabel("Student ID");
		lblStudentId_4.setFont(new Font("Lucida Sans", Font.BOLD, 13));
		lblStudentId_4.setBounds(74, 95, 112, 24);
		view_fees.add(lblStudentId_4);
		
		
	}
}
