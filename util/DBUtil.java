package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class DBUtil {
	private static final String url="jdbc:mysql://localhost:3306/test";
	private static final String driver="com.mysql.jdbc.Driver";
	private static final String un="root";
	private static final String psd="123";
	
	public static Connection connect(){
//		
		Connection con=null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,un,psd);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public static  void free(Connection con,PreparedStatement pst,ResultSet rs){
		try {
			if(rs!=null){
				rs.close();
			}
			if(pst!=null){
				pst.close();
			}
			if(con!=null){
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Connection con=connect();
		System.out.println(con);
	}
}
