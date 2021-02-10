package nqpham_CSCI201_Assignment1;

import java.util.List;

/*
 * Natalie Pham
 * CSCI 201
 * Professor Papa
 * Information of the companies
 */

public class CompanyInfo {
	
	String name;
	String ticker;
	String startDate;
	String description;
	String exchangeCode;
	
	
	public CompanyInfo() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CompanyInfo(String name, String ticker, String startDate, String description, String exchangeCode) {
		super();
		this.name = name;
		this.ticker = ticker;
		this.startDate = startDate;
		this.description = description;
		this.exchangeCode = exchangeCode;
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
