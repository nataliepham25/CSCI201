
class HourlyEmployee extends Employee {

	private double hourlyRate;

	private int numberHoursPerWeek;



	public HourlyEmployee(String firstName, String lastName, String birthDate, int employeeID, String jobTitle, String company, double hourlyRate, int numberHoursPerWeek) {

		super(firstName, lastName, birthDate, employeeID, jobTitle, company);

		this.hourlyRate = hourlyRate;

		this.numberHoursPerWeek = numberHoursPerWeek;

	}

	public double getHourlyRate() {

		return hourlyRate;

	}

	public void setHourlyRate(double hourlyRate) {

		this.hourlyRate = hourlyRate;

	}

	public int getNumberHoursPerWeek() {

		return numberHoursPerWeek;

	}

	public void setNumberHoursPerWeek(int numberHoursPerWeek) {

		this.numberHoursPerWeek = numberHoursPerWeek;

	}

	@Override

	public double getAnnualSalary() {

		return 52 * hourlyRate * numberHoursPerWeek;

	}

}
