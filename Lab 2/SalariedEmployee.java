public class SalariedEmployee extends Employee {

	protected double basePay;

	
	
	

	public SalariedEmployee(String firstName, String lastName, String birthDate, int employeeID, String jobTitle, String company, double basePay) {

		super(firstName, lastName, birthDate, employeeID, jobTitle, company);

		this.basePay = basePay;

	}

	public double getBasePay() {

		return basePay;

	}

	public void setBasePay(double basePay) {

		this.basePay = basePay;

	}

	@Override

	public double getAnnualSalary() {

		return basePay;

	}

}
