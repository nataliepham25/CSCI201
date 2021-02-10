public abstract class Employee extends Person{

	protected int employeeID;
	protected String jobTitle;
	protected String company;


	public Employee() {
		jobTitle="";
		company="";


	}


	public Employee(String firstName, String lastName, String birthDate, int employeeID, String jobTitle, String company) {
		super(firstName, lastName, birthDate);
		this.employeeID = employeeID;

		this.jobTitle = jobTitle;

		this.company = company;

	
	}




	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {

		this.employeeID = employeeID;

	}

	public String getJobTitle() {

		return jobTitle;

	}

	public void setJobTitle(String jobTitle) {

		this.jobTitle = jobTitle;

	}

	public String getCompany() {

		return company;

	}

	public void setCompany(String company) {

		this.company = company;

	}


	public abstract double getAnnualSalary();



}
