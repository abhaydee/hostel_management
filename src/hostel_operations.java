import java.awt.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.mockrunner.mock.jdbc.MockResultSet;
public class hostel_operations {
private static db_config db_ops = new db_config();
	
	/*insert student into the db*/
	public void add_student(HashMap<Integer, String> student) throws SQLException{
		String insert_query = "INSERT INTO `hostel_management`.`student` (`name`, `father_name`, `phone_number`, `gender`, `dob`, `branch`, `semester`, `home_address`, `doj`) VALUES (?,?,?,?,?,?,?,?,?);";
		System.out.println("insert query is "+insert_query);
		db_ops.insert_data(insert_query, student);
	}
	
	
	
	public String pass_room(String name) {
		String id="";
		try {
		String query="Select * from student where name = ?;";
		
		PreparedStatement ps = db_config.conn.prepareStatement(query);
		//name="%"+ name +"%";
		ps.setString(1, name);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			 id = rs.getString("student_id");
		}

		}catch(Exception e) {
			System.out.println(e);
		}
		
		return id;
	}
	
	
	
	public void add_faculty(HashMap<Integer, String> faculty) throws Exception{
		String insert_query = "INSERT INTO  `hostel_management`.`faculty` (`name` ,`phone_number` ,`department` ,`dob`, `gender` ,`hostel_id`, `address`,`salary` ,`doj`)VALUES (?,?,?,?,?,?,?,?,?);";
				
		System.out.println("insert query is "+insert_query);
		db_ops.insert_data(insert_query, faculty);
	}
	
	public void pay_fees(HashMap<Integer, String> fees) throws Exception{
		String insert_query ="INSERT INTO  `hostel_management`.`fees` (`student_id` ,`month_year` ,`payment_date`)VALUES (?,?,?);";
				
		System.out.println("insert query is "+insert_query);
		db_ops.insert_data(insert_query, fees);
	}
	
	
	
	public void faculty_incharge_rooms(String hostel_id) throws Exception {
		
		String query = "Select room_no from room where hostel_id = "+ Integer.parseInt(hostel_id) +";";
		String query_faculty = "Select 	faculty_id from faculty where hostel_id = "+ Integer.parseInt(hostel_id) +" and dol = \"\";";
		
		ArrayList<Integer>room_ids = new ArrayList<Integer>();
		ArrayList<Integer>faculty_ids = new ArrayList<Integer>();
		
		
		PreparedStatement ps = db_config.conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			room_ids.add((Integer) rs.getObject(1));
		}
				
		ps = db_config.conn.prepareStatement(query_faculty);
		ResultSet rs_1 = ps.executeQuery();
		
		while(rs_1.next()) {
			faculty_ids.add((Integer) rs_1.getObject(1));
		}
		
		int rooms = room_ids.size();
		int faculty_count = faculty_ids.size();
		System.out.println(rooms+"  "+faculty_count);
		
		//assigning the rooms in round robin fashion
		for(int i=0,j=0;i<rooms;i++,j++) {
			if(j == faculty_ids.size()) {
				j=0;
			}
			String update_query = "Update room set faculty_id = "+faculty_ids.get(j)+" where room_no = "+ room_ids.get(i) +" and hostel_id="+hostel_id+";";
			System.out.println("the query is "+update_query);
			Statement st = db_config.conn.createStatement();
			st.executeUpdate(update_query);
		}
		
		
	}

	public void allot_room(HashMap<Integer, String> room) throws Exception {
		// TODO Auto-generated method stub
		
		String insert_query="INSERT INTO  `hostel_management`.`allot_student` (`student_id`,`hostel_id` ,`room_no` )VALUES (?,?,?);";
		System.out.println("insert query is "+insert_query);
		db_ops.insert_data(insert_query, room);
	}
	
	public static Date get_parsed_date(String date, String format) throws ParseException {
		Date date_obj = new Date();
		if (format.equals("dd-MM-yyyy")) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			date_obj = sdf.parse(date);
		} else if (format.equals("MMM-yyyy")) {
			SimpleDateFormat sdf = new SimpleDateFormat("MMM-yyyy");
			date_obj = sdf.parse(date);
		}
		return date_obj;
	}

	public static ArrayList<String> get_master_list(Date doj_date) {
		SimpleDateFormat sdf = new SimpleDateFormat("MMM-yyyy");
		ArrayList<String> master_dates = new ArrayList<String>();
		Calendar c = Calendar.getInstance();
		Calendar today = Calendar.getInstance();
		c.setTime(doj_date);
		//master_dates.add(sdf.format(doj_date));
		while (true) {
			if (c.get(Calendar.MONTH) == today.get(Calendar.MONTH)) {
				if (c.get(Calendar.YEAR) == today.get(Calendar.YEAR)) {
					break;
				}
			}
			System.out.println("inside loop");
			if (c.get(Calendar.YEAR) == today.get(Calendar.YEAR)) {
			//c.add(Calendar.MONTH, 1);
			master_dates.add(sdf.format(new Date(c.getTimeInMillis())));
			c.add(Calendar.MONTH, 1);
			continue;
			}
			c.add(Calendar.MONTH, 1);
		}
		return master_dates;
	}

	public static ArrayList<String> get_paid_dates_list(ResultSet rs, int col_num) throws SQLException, ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("MMM-yyyy");
		ArrayList<String> paid_dates = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		Calendar today = Calendar.getInstance();
		while (rs.next()) {
			cal.setTime(get_parsed_date((String) rs.getObject(col_num), "MMM-yyyy"));
			if (cal.get(Calendar.YEAR) == today.get(Calendar.YEAR)) {
			paid_dates.add(sdf.format(new Date(cal.getTimeInMillis())));
			}
		}
		return paid_dates;
	}

	public static ArrayList<String> get_unpaid_months(ArrayList<String> master, ArrayList<String> paid) {
		for (int i = 0; i < paid.size(); i++) {
			if (master.indexOf(paid.get(i)) > -1) {
				master.remove(master.indexOf(paid.get(i)));
			}
		}
		return master;

	}

	public ResultSet get_payment_details(String student_id) throws Exception {
		String query = "select * from fees where student_id=" + student_id + ";";
		String query_1 = "select doj from student where student_id=" + student_id + ";";
		ResultSet rs_student = db_config.retireve_data(query_1);
		rs_student.next();

		Date doj_date = get_parsed_date((String) rs_student.getObject(1), "dd-MM-yyyy");
		System.out.println("date object is " + doj_date);

		ArrayList<String> master_dates = new ArrayList<String>();
		master_dates = get_master_list(doj_date);
		System.out.println("master is"+master_dates);
		ResultSet rs_fees = db_config.retireve_data(query);
		ArrayList<String> paid_dates = new ArrayList<String>();
		paid_dates = get_paid_dates_list(rs_fees, 2);
		System.out.println(paid_dates);
		ArrayList<String> unpaid_dates = new ArrayList<String>();
		unpaid_dates = get_unpaid_months(master_dates, paid_dates);
		
		MockResultSet mrs = new MockResultSet("hostel_operations");
		mrs.addColumn("Due Months");
		for (int j=0;j<unpaid_dates.size();j++) {
			ArrayList singleList = new ArrayList<Object>();
			singleList.add(unpaid_dates.get(j));
			mrs.addRow(singleList);
		}
		ResultSet rs = mrs;
		System.out.println(rs instanceof ResultSet);
		
		return rs;
		
	}
	
}

	