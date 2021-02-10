package nqpham_CSCI201_Assignment1;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;


@SuppressWarnings("unused")

/*
 * Natalie Pham
 * CSCI 201
 * Professor Papa
 * Assignment 1: Menu of Stocks
 */

public class Menu {
	
	ArrayList<CompanyInfo> companyList = new ArrayList<CompanyInfo>();
	private String fileName= null;
	private boolean fileChanged= false;
	public Scanner sc1; 
	
	// constructor
	
	
	public Scanner getSc1() {
		return sc1;
	}

	public void setSc1(Scanner sc1) {
		this.sc1 = sc1;
	}

	public Menu(Scanner sc12) {
		setSc1(sc12);
		this.parseJSON();
		getOptionHelper();
			
	}
	//parsing
	
	private void parseJSON()	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Gson gson = new Gson();
		boolean isJSON = false;
		
		while (!isJSON) {
			System.out.print("What is the name of the file? ");
			
			try {
				this.fileName = br.readLine();
				BufferedReader bufferedReader = new BufferedReader(new FileReader(this.fileName));
				gson.fromJson(bufferedReader, CompanyInfo.class);
				bufferedReader.close();
				
				
				
				JsonParser parser = new JsonParser();
				JsonElement jsonTree = parser.parse(new FileReader(this.fileName));
				JsonObject jsonObject = jsonTree.getAsJsonObject();
				ArrayList<CompanyInfo> temp = null;
				try {
					
				Type listType = new TypeToken<List<CompanyInfo>>(){}.getType();
				JsonArray jsonArray = jsonObject.getAsJsonArray("data");
				temp = gson.fromJson(jsonArray.toString(), listType);
				
				for(CompanyInfo ci :temp) {
					if(!(ci.getName() != null && !ci.getName().trim().isEmpty())) {						
						throw new NullPointerException();
					}
					
					if(!(ci.getTicker() != null && !ci.getTicker().trim().isEmpty())) {						
						throw new NullPointerException();
					}
					
					if(!(ci.getStartDate() != null && !ci.getStartDate().trim().isEmpty())) {						
						throw new NullPointerException();
					}
					
					if(!(ci.getDescription() != null && !ci.getDescription().trim().isEmpty())) {					
						throw new NullPointerException();
					}
					
					if(!(ci.getExchangeCode() != null && !ci.getExchangeCode().trim().isEmpty())) {
						throw new NullPointerException();
					}
				}		
				
				companyList.addAll(temp);
				System.out.println("The file has been properly read.");
				isJSON = true;
				}catch (NullPointerException e) {
				
					System.out.println("The file " + this.fileName +" is not formatted properly.");
				}					
				
			} catch (JsonSyntaxException jse) {
				System.out.println("That file is not a well-formed JSON file.");
				
			} catch (IOException ioe) {
				System.out.println("The file " +this.fileName+" could not be found.");
				
			} 
			
		}
		
		
	}
	

	/**
	 * Prompts the user for an option between 1 to 7:
	 *  1) Display all public companies
	 *  2) Search for a stock (by ticker)
	 *  3) Search for all stocks on an exchange
	 *  4) Add a new company/stocks
	 *  5) Remove a company
	 *  6) Sort companies
	 *  7) Exit
	 */
	
	private void getOptionHelper()	{
		System.out.println();
		System.out.println("\t1) Display all public companies");
		System.out.println("\t2) Search for a stock (by ticker)");
		System.out.println("\t3) Search for all stocks on an exchange");
		System.out.println("\t4) Add a new company/stocks");
		System.out.println("\t5) Remove a company");
		System.out.println("\t6) Sort companies");
		System.out.println("\t7) Exit");
		System.out.println();
		System.out.print("What would you like to do? ");
		
		
		String option1= sc1.nextLine(); 
		
		try {
			int op = Integer.parseInt(option1);
			if( op<1 || op>7) {
				System.out.println("That is not a valid option.");
				getOptionHelper();
			}else {
				userOption(op);
			}
		} catch (Exception e) {
			System.out.println("That is not a valid option.");
			getOptionHelper();
		}
	}
	
	
	/* 
	 * Asks user to pick an option
	 */

	
	public void userOption(int option) {
		switch(option) {
		case 1: {
			displayCompanies();
		}
		
		case 2:{
			//search for stock by ticker
			searchForStock();
		}
		
		case 3:{
			//search for all stocks
			searchStockExchange();
		}
		
		case 4:{
			//add a new company
			addCompany();
		}
		
		case 5:{
			//remove a company
			removeCompany();
		        }
		
		case 6:{
			//sort companies
			sortCompanies();
			
		}
		case 7:{
			//exit
			exit();
			break;
		}
		default:	{
			System.out.println("This should never occur. Congratulations, you've broken the program");
			break;
		}
		}
	}
	
	/*
	 * display the companies
	 */
	
	
	private void displayCompanies() {
		for(CompanyInfo ci : companyList) {
			System.out.println(ci.getName()+", symbol "+ ci.getTicker()+", started on "+ci.getStartDate()+", listed on "+ci.getExchangeCode()+", "+("\n\t"+ci.getDescription()));
		}
		
		getOptionHelper();
	}
	
	
	//search for the stock
	private void searchForStock() {
		System.out.println("What is the name of the company you would like to search for?");
		
		String search = sc1.nextLine();
		
		boolean inlist = false;
		
		for(CompanyInfo ci :companyList) {
			if(search.equals(ci.getTicker())){
				inlist=true;
				System.out.println(ci.getName()+", symbol "+ ci.getTicker()+", started on "+ci.getStartDate()+", listed on "+ci.getExchangeCode());
			}
		}
		
		if(inlist) {
			getOptionHelper();
		}else {
			System.out.println(search+" could not be found.");
			searchForStock();
		}
		
	}
	
	
	
	
	//searching what is in the stock exchange
	
	private void searchStockExchange() {
		System.out.println();
		System.out.println("What Stock Exchange would you like to search for?");
		
		String search = sc1.nextLine();

		boolean inlist = false;
		ArrayList<String> result =  new ArrayList<String>();
		
		for(CompanyInfo ci :companyList) {
			if(search.equals(ci.getExchangeCode())){
				inlist=true;
				result.add(ci.getTicker());
			}
		}
		
		if(inlist) {
			//String temp = "Companies found on the "+search+" are : ";
			String temp="";
			for(String reString:result) {
				temp = temp + reString + ", ";
			}
			System.out.println(temp.substring(0, temp.length()-2)+" found on the "+ search + " exhange.");
			getOptionHelper();
		}else {
			System.out.println("No exchange named "+search+" found.");
			searchStockExchange();
		}
	}
	

	//adds a company
	private void addCompany() {
		boolean inlist = false;
		System.out.println("What is the name of the company you would like to add?");
		
		String companyName = sc1.nextLine();

		for(CompanyInfo ci : companyList) {
			if(ci.getName().equals(companyName)) {
				inlist = true;
			}
		}
		if(inlist) {
			System.out.println("There is already an entry for "+companyName+".");
			System.out.println();
			addCompany();
		}else {
			CompanyInfo newCompany = new CompanyInfo();
			newCompany.setName(companyName);
			
			System.out.println("What is the stock symbol of "+companyName+"?");
			newCompany.setTicker(sc1.nextLine());
			System.out.println("What is the start date of "+companyName+"?");
			newCompany.setStartDate(sc1.nextLine());
			System.out.println("What is the exchange where "+companyName+" is listed?");
			newCompany.setExchangeCode(sc1.nextLine());
			System.out.println("What is the description of "+companyName+"?");
			newCompany.setDescription(sc1.nextLine());
			
			companyList.add(newCompany);
			System.out.println("There is now a new entry for:");
			System.out.println(newCompany.getName()+", symbol "+ newCompany.getTicker()+", started on "+newCompany.getStartDate()+", listed on "+newCompany.getExchangeCode()+" ,"+newCompany.getDescription());
			getOptionHelper();
			}
		}
	
	
	
	/**
	 * Remove a company
	 */
	
	
	private void removeCompany() {
		int i = 1;
		for(CompanyInfo ci : companyList) {
			System.out.println("\t"+ i++ +") "+ci.getName());
		}
		
		System.out.println("Which company would you like to remove?");
		
		String option = sc1.nextLine();
		try {
			int op = Integer.parseInt(option);
			if( op<1 || op > companyList.size()) {
				System.out.println("Invalid Input , Please Try Again");
				removeCompany();
			}else {
				System.out.println(companyList.get(op-1).getName()+" is removed from list.");
				companyList.remove(op-1);
				getOptionHelper();
			}
		} catch (Exception e) {
			System.out.println("Invalid Input , Please Try Again");
			removeCompany();
		}
	}
	
	private void sortCompanies() {
		System.out.println("\t1) A to Z");
		System.out.println("\t2) Z to A");
		
		System.out.println();
		System.out.println("How would you like to sort by?");
		
		String option = sc1.nextLine();
		
		if(option.equals("1")) {
			companyList.sort(new sortAtoZ());
			System.out.println("Your companies are now sorted from in alphabetical order (A-Z).");
			getOptionHelper();
		}else if (option.equals("2")) {
			companyList.sort(new sortZtoA());
			System.out.println("Your companies are now sorted from in alphabetical order (Z-A).");
			getOptionHelper();
			
		}else {
			System.out.println("Invalid Input , Please Try Again");
			 sortCompanies();
		}
	}
	
	
	
	/**
	 * Writes the updated menu to the original file opened
	 */
	
	private void writeFile() {
		Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
		
		System.out.println();
		
		try {
			JsonWriter writer = new JsonWriter(new FileWriter(this.fileName));
			writer.setIndent(" ");
			writer.beginObject();
			writer.name("data");
			writer.beginArray();
			for (CompanyInfo ci : companyList) {
			        writer.beginObject();
			        writer.name("name").value(ci.getName());
			        writer.name("ticker").value(ci.getTicker());
			        writer.name("startDate").value(ci.startDate);
			        writer.name("description").value(ci.getDescription());
			        writer.name("exchangeCode").value(ci.getExchangeCode());
			        writer.endObject();
			    }
			 writer.endArray();
			 writer.endObject();
			 writer.close();
			
			
			
			
		} catch (JsonIOException | IOException e) {
			System.out.println("File has encountered an error saving.");
		}
	}
	
	
	/**
	 * Terminates the program
	 * changes made, ask the user if want to save
	 * Otherwise, no prompt
	 */
	
	private void exit() {
		System.out.print("Would you like to save the file before exiting? ");
		System.out.println();
		System.out.println("\t1) Yes");
		System.out.println("\t2) No");
		String option = sc1.nextLine();
		if(option.equals("1")) {
			writeFile();
			System.out.println("Your edits have been saved to " + fileName);
			System.out.println("Thank you for using my program!");
			System.exit(0);
		}else if (option.equals("2")) {
			System.out.println("Your edits have not been saved to "+ fileName);
			System.out.println("Thank you for using my program!");
			System.exit(0);
		}else {
			System.out.println("Invalid Input , Please Try Again");
			 exit();
		}
	}
	

}
