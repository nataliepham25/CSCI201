import java.util.Date;
import java.util.Scanner;

public class MainOption {
	
	static Scanner sc1= new Scanner(System.in);
	static Date startDate;
	
	public static Date getStartDate() {
		return startDate;
	}

	public static void setStartDate(Date startDate) {
		MainOption.startDate = startDate;
	}

	public static void main(String[] args) {
		//System.out.println("Starting execution of program...");
		Menu menu = new Menu(sc1);
		
	}
}
