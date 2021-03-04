import java.util.List;
import java.util.concurrent.Semaphore;

public class CompanyInfo {
	
	String name;
	String ticker;
	String startDate;
	int stockBrokers=0;
	String description;
	String exchangeCode;
	
	
	public CompanyInfo() {
		super();
		// TODO Auto-generated constructor stub
	}



	public CompanyInfo(String name, String ticker, String startDate, String stockBrokers, String description,
			String exchangeCode) {
		super();
		this.name = name;
		this.ticker = ticker;
		this.startDate = startDate;
		this.stockBrokers = Integer.parseInt(stockBrokers);
		this.description = description;
		this.exchangeCode = exchangeCode;
		
	}



	public int getStockBrokers() {
		return stockBrokers;
	}



	public void setStockBrokers(int stockBrokers) {
		this.stockBrokers = stockBrokers;
	}



	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getTicker() {
		return ticker;
	}


	public void setTicker(String ticker) {
		this.ticker = ticker;
	}


	public String getStartDate() {
		return startDate;
	}


	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getExchangeCode() {
		return exchangeCode;
	}


	public void setExchangeCode(String exchangeCode) {
		this.exchangeCode = exchangeCode;
	}


	public List getStocks() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
