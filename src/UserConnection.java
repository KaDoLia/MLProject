
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserConnection {
	static String connUser = null;
	static String connPass = null;
	public String firstName = null;
	public String lastName = null;
	public int perms;
	public DataCall call = new DataCall();
	public Connection conn = null;

	UserConnection(String _userName, String _passWord) {
		connUser = _userName;
		connPass = _passWord.toString();
		connUser = _userName;// .toString();
	}

	public int userConnect() {
		Statement stmt = null;
		String query;
		try {
			conn = call.dbConn();
			stmt = conn.createStatement();
			// System.out.println(connUser);
			// System.out.println(connPass);
			query = "SELECT Name,LastName,Permissions FROM users WHERE UserName='" + connUser + "'AND PassWord='"
					+ connPass + "'"; // mysql query

			ResultSet rs = stmt.executeQuery(query);// execute query

			while (rs.next()) {
				firstName = rs.getString("Name");
				// System.out.println(firstName);
				lastName = rs.getString("LastName");
				// System.out.println(lastName);
				perms = rs.getInt("Permissions");
				// System.out.println(perms);
			}
			// int rows = stmt.executeUpdate("UPDATE .");
			if (firstName != null)
				return 1; // ean exoume epistrofh data apo th vash h eisodos
							// einai epityxhs
		} catch (SQLException se) {
			se.printStackTrace();
		} catch( ClassNotFoundException se){
			se.printStackTrace();
		}
		call.dbClose(conn);
		return 0;

	}

	// epistrofh stoixeiwn
	public String getName() {
		return firstName;
	}

	public String getLast() {
		return lastName;
	}

	public int getPerm() {
		return perms;
	}

	public DataCall getCall(){
		return call;
	}

}
