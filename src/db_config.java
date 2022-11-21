import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
public class db_config {
	public static Connection conn = null;
	private static String db_username = "root";
	private static String db_password = "";
	private static String db_name = "hostel_management";
	private static String db_host = "localhost";

	public static void connect_to_database(){
		try{
			conn = DriverManager.getConnection("jdbc:mysql://"+ db_host +"/"+ db_name +"", db_username, db_password);
			System.out.println("connection success");
		}catch(SQLException e){
			System.out.println("connection to the Database failed "+e);
		}
	}
	
	public ResultSet execute_query(String query){
		ResultSet rs = null;
		try{
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
		}catch(SQLException e){
			System.out.println("Failed to execute the query "+e);
		}
		return rs;
	}
	
	public void insert_data(String query, HashMap<Integer, String> data)throws SQLException{
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		for (int i=1;i<=data.size();i++){
			try{
				/* If parameter is integer */
				preparedStmt.setInt (i, Integer.parseInt(data.get(i)));
			}catch(NumberFormatException e)
			{
				/* If parameter is not Integer */
				preparedStmt.setString (i, data.get(i));
			}
		}
		preparedStmt.execute();
	}

public static ResultSet getData_operation(String name, String table_name) throws SQLException {
	// TODO Auto-generated method stub
	String query = null;
	if(table_name == "student") {
		query="Select * from student where name like ?;";
	}else if(table_name == "faculty") {
		query="Select * from faculty where name like ?;";
	}else if(table_name == "room") {
		query = "Select s.student_id,s.name,r.room_no,r.hostel_id, r.is_deleted from student s,allot_student r where s.student_id=r.student_id and s.name like ?;";
	}else if(table_name == "fees") {
		query = "select sf.student_id,s.name,sf.month_year,sf.payment_date from student s,fees sf where sf.student_id=s.student_id and s.name like ?;";
	}
	else {
		System.out.println("Encountered new table name please handle accordingly");
		System.exit(1);
	}
	
	PreparedStatement ps = conn.prepareStatement(query);
	name="%"+ name +"%";
	ps.setString(1, name);
	ResultSet rs = ps.executeQuery();
	return rs;
	//return null;
}


public static ResultSet delete_operation(String id, String table_name) throws SQLException {
	// TODO Auto-generated method stub
	String query = null;
	if(table_name == "student") {
		query="Select * from student where student_id = ?;";
	}else if(table_name == "faculty") {
		query="Select * from faculty where faculty_id = ?;";
	}else if(table_name == "allot_student") {
		query = "Select student_id, hostel_id, room_no, is_deleted from allot_student where student_id =?;";
	}
	else {
		System.out.println("Encountered new table name please handle accordingly");
		System.exit(1);
	}
	
	PreparedStatement ps = conn.prepareStatement(query);
	//s="%"+ s +"%";
	ps.setInt(1, Integer.parseInt(id));
	ResultSet rs = ps.executeQuery();
	return rs;
	//return null;
}

public static ResultSet room_op(String query) throws SQLException{

	PreparedStatement ps = conn.prepareStatement(query);
	ResultSet rs = ps.executeQuery();
	
	return rs;
}

public static ResultSet fees(String query) throws SQLException{

	System.out.println(query);
	PreparedStatement ps = conn.prepareStatement(query);
	ResultSet rs = ps.executeQuery();
	
	return rs;
}


public static void update_operation(String query) throws SQLException{
	Statement st = conn.createStatement();
	System.out.println("query is "+query);
	st.executeUpdate(query);
 }

public static ResultSet retireve_data(String query) throws Exception {
	
	PreparedStatement ps = conn.prepareStatement(query);
	ResultSet rs = ps.executeQuery();
	
	return rs;
}
}
