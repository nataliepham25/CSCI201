public class CommissionEmployee extends SalariedEmployee {

	private double salesTotal;
	private double commissionPercentage;


	public CommissionEmployee(String firstName, String lastName, String birthDate, int i, String jobTitle, String company, double basePay, double salesTotal, double commissionPercentage) {

		super(firstName, lastName, birthDate, i, jobTitle, company, basePay);

		this.salesTotal = salesTotal;

		this.commissionPercentage = commissionPercentage;

	}

	public double getSalesTotal() {

		return salesTotal;

	}

	public void setSalesTotal(double salesTotal) {

		this.salesTotal = salesTotal;

	}

	public double getCommissionPercentage() {

		return commissionPercentage;

	}

	public void setCommissionPercentage(double commissionPercentage) {

		this.commissionPercentage = commissionPercentage;

	}

	@Override

	public double getAnnualSalary() {

		return basePay + salesTotal * commissionPercentage;

	}

}
