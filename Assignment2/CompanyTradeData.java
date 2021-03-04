import java.util.concurrent.Semaphore;

public class CompanyTradeData {
	Semaphore semaphore;
	CompanyInfo companyInfo;
	
	public CompanyTradeData(CompanyInfo companyInfo) {
		super();
		this.semaphore = new Semaphore(companyInfo.getStockBrokers());
		this.companyInfo = companyInfo;
	}

	public CompanyInfo getCompanyInfo() {
		return companyInfo;
	}

	public void setCompanyInfo(CompanyInfo companyInfo) {
		this.companyInfo = companyInfo;
	}

	public Semaphore getSemaphore() {
		return semaphore;
	}
	
	
}
