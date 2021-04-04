import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCPrint {
	public static void printStudentCount(Statement st) {
		System.out.printf("%-15s%-10s \n", "ClassName", "Number of Students");
		System.out.printf("%-15s%-10s \n", "---------", "------------------");
		ResultSet rs = null;
		try {
			rs = st.executeQuery("SELECT ClassName, COUNT(*) FROM Grades GROUP BY ClassName ORDER BY COUNT(*)");
			while(rs.next()) {
				String className = rs.getString("ClassName");
				int count = rs.getInt("COUNT(*)");
				System.out.printf("%-15s%-10s \n", className, count);
			}
		} catch(SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
	}
	
	public static void printClassGrades(Statement st) {
		System.out.printf("%-15s%-20s%-5s \n", "ClassName", "Name", "Grade");
		System.out.printf("%-15s%-20s%-5s \n", "---------", "----", "-----");
		ResultSet rs = null;
		try {
			rs = st.executeQuery("SELECT g.ClassName, s.Name, g.Grade FROM StudentInfo s, Grades g WHERE s.SID=g.SID");
			while(rs.next()) {
				String className = rs.getString("ClassName");
				String studentName = rs.getString("Name");
				String grade = rs.getString("Grade");
				System.out.printf("%-15s%-20s%-5s \n", className, studentName, grade);
			}
		} catch(SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
	}
	
	public static void main(String [] args) {
		Connection conn = null;
		Statement st = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/lab9?user=root&password=Livinglife27$");
			st = conn.createStatement();
			printStudentCount(st);
			System.out.println("\n\n");
			printClassGrades(st);
		} catch(SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
	}
}
