import java.sql.Connection;
import java.sql.Statement;
import java.sql.*;
import com.mysql.jdbc.*;

public class DataCall {
	static String dbUser = "root";
	static String dbPass = "plokamar1";
	static final String connUrl = "jdbc:mysql://localhost:3306/sdadb?autoReconnect=true&useSSL=false";
	public Connection dbconn = null;

	////// Start Connection/////
	public Connection dbConn() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		dbconn = DriverManager.getConnection(connUrl, dbUser, dbPass);
		return dbconn;
	}

	/////// Close connection///////////////
	public void dbClose(Connection dbconn) {
		try {
			dbconn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}

	}

	////// Kanei search ta eisagomena kai epistrefei to ResultSet meta thn
	////// ektelesh tou query
	public ResultSet search(String searchS) {
		DataCall call = new DataCall();
		Connection conn = null;
		String query0 = null;
		String query1 = null;
		ResultSet rs = null;
		try {
			conn = call.dbConn();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
		query0 = "SELECT * FROM products WHERE idProd LIKE '%" + searchS + "%' OR nameProd LIKE '%" + searchS
				+ "%' OR typeProd LIKE '%" + searchS + "%'";
		query1 = "SELECT * FROM products";
		System.out.println(searchS);
		try {
			conn.setAutoCommit(true);
			Statement stmt = (Statement) conn.createStatement();
			System.out.println(query0);
			if(searchS == null){ 
				rs = stmt.executeQuery(query1);
			}else{ 
				rs = stmt.executeQuery(query0);
			}
			return rs;
			/*
			 * String query1 = "SELECT * FROM products WHERE idProd LIKE ? OR nameProd LIKE ? OR typeProd LIKE ?";
			 *PreparedStatement pstmt = conn.prepareStatement(query1);
			 *stmt.setString(i++, "%"+searchS+"%"); 
			 *stmt.setString(i++,"%"+searchS+"%"); 
			 *stmt.setString(i++, "%"+searchS+"%");
			 *rs = stmt.exequteQuery(query1);
			 */

		} catch (SQLException se) {
			se.printStackTrace();
		}
		return null;
	}

}
