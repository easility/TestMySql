package test;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Statement;

public class TestSql {
	public static void main(String[] argv) {
		ResultSet rs = null;
		long startInsert = System.currentTimeMillis();
		// Change these properties for your MySql hostname, port, user and password
		String mySqlURL = "jdbc:mysql://localhost:3306/MySqlTest";
		String user = "root";
		String password = "root";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(mySqlURL, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		Statement pst;
		try {
			connection.setAutoCommit(false);
			pst = (Statement) connection.createStatement();
			int count = 0;
			for (int j = 1; j <= 500; j++) {
				System.out.println("wait data is adding in batch:no" + j);
				for (int i = 1; i <= 1000000; i++) {
					count++;
					pst.addBatch("INSERT INTO BigTable VALUES("+ count+ ","+ i+ ",'databus',"+ i+ ","+ i+ ",'sector47','noida','india','0120-1231232','single',"	+ 23 + ")");
				}
				pst.executeBatch();
				connection.commit();
				System.out.println("commint no :" + j);
			}
			long totalInsertTime = System.currentTimeMillis() - startInsert;
			System.out.println("totalInsertTime :" + totalInsertTime);
			long startRead = System.currentTimeMillis();
			String sql = "select * from BigTable where ID>100030000 and ID<100060000";
			rs = pst.executeQuery(sql);
			while (rs.next()) {
				System.out.print(rs.getString(1) + " \t");
				System.out.print(rs.getString(2) + " \t");
				System.out.print(rs.getString(3) + " \t");
				System.out.print(rs.getString(4) + " \t");
				System.out.print(rs.getString(5) + " \t");
				System.out.print(rs.getString(6) + " \t");
				System.out.print(rs.getString(7) + " \t");
				System.out.print(rs.getString(8) + " \t");
				System.out.print(rs.getString(9) + " \t");
				System.out.print(rs.getString(10) + " \t");
				System.out.println(rs.getString(11));
				System.out.println("*************");
				System.out.println("");
			}
			long totalReadTime = System.currentTimeMillis() - startRead;
			System.out.println("totalReadTime  :" + totalReadTime);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
