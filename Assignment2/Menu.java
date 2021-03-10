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
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import java.util.Collections;
import java.util.Random;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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


public class Menu extends Thread{
	
	private ArrayList<CompanyInfo> companyList = new ArrayList<CompanyInfo>();
	private ArrayList<CompanyTradeData> companyTradeDatas = new ArrayList<CompanyTradeData>();
	private ArrayList<TradeData> tradeDataList = new ArrayList<TradeData>();
	ExecutorService executors = Executors.newCachedThreadPool();
	private String fileName= null;
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
		for(CompanyInfo ci:companyList) {
			companyTradeDatas.add(new CompanyTradeData(ci));
		}
		this.parseCsv();
		System.out.println(); 
		System.out.println("Starting execution of program...");
		ExecutorService executors = Executors.newCachedThreadPool();
		MainOption.setStartDate(new Date());
		for(TradeData tradeData : tradeDataList) {
			executors.execute(tradeData);
		}
		
		executors.shutdown();	
		
		while(!executors.isTerminated()) {
			Thread.yield();
		}
		
		System.out.println("All trades completed!");
	}
	
	
	
	
	
	private void parseJSON()	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Gson gson = new Gson();
		boolean isJSON = false;
		
		
		while (!isJSON) {
			System.out.print("What is the name of JSON File? ");
			
			try {
				this.fileName = br.readLine();
				BufferedReader bufferedReader = new BufferedReader(new FileReader(this.fileName));
				gson.fromJson(bufferedReader, CompanyInfo.class);
				bufferedReader.close();
				
				@SuppressWarnings("deprecation")
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
					
					if(ci.getStockBrokers()==0) {
						throw new NullPointerException();
					}
				}		
				
				companyList.addAll(temp);
		//		System.out.println(companyList.size());
				System.out.println("Json File Read Successfully.");
				isJSON = true;
				}catch (NullPointerException e) {
					
					System.out.println("That file is missing some parameters");
				}					
				
			} catch (JsonSyntaxException jse) {
				System.out.println("That file is not a well-formed JSON file.");
				
			} catch (IOException ioe) {
				System.out.println("That file could not be found.");
				
			} 
			
		}
		
		
	}
	
	private void parseCsv() {
		String line = "";  
		String splitBy = ",";  
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean isCSV = false;
		
		while (!isCSV) {
			System.out.print("What is the name of the CSV File? ");
			
		try   
		{  
			this.fileName = br.readLine();
			BufferedReader bufferedReader = new BufferedReader(new FileReader(this.fileName));
		
		while ((line = bufferedReader.readLine()) != null)   //returns a Boolean value  
		{  
		CompanyTradeData temp = null;
		String[] data = line.split(splitBy);    // use comma as separator  
		
		if (data.length!=3) {
			throw new Exception();
		}
		for(CompanyTradeData companyTradeData: companyTradeDatas) {
			
			if(companyTradeData.getCompanyInfo().getTicker().equals(data[1])) {
				temp = companyTradeData;
			}
		}
		tradeDataList.add(new TradeData(Integer.parseInt(data[0]), Integer.parseInt(data[2]), temp));
		}  
		
		System.out.println("CSV File Read Succesfully.");
		isCSV = true;
		}  
		catch (IOException e)   
		{  
			System.out.println("That file could not be found.");
		}  
		catch (Exception e) {
			System.out.println("File is Not Valid CSV.");
		} 
	}
	}
}
