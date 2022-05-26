package com.example;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

public class UpdateService {
	
	private JSONObject object;
	private ArrayList<Stock> stocks = new ArrayList<>();
	private ArrayList<StockDataDay> stockDataDays = new ArrayList<>();
	
	public void getDataFromWeb() throws IOException, JSONException{
		
		String[] stockSymbolsLarge = {"ACC","ADANIPORTS","AMBUJACEM","ASIANPAINT","AXISBANK","BAJAJ-AUTO","BANKBARODA",
				"BHEL","BPCL","BHARTIARTL","BOSCHLTD","CIPLA","COALINDIA","DRREDDY","EICHERMOT"};
		
		String[] stockSymbolsMed={"ADANIENT","ADANIPOWER","ABIRLANUVO","AJANTPHARM","AMARAJABAT","APOLLOTYRE",
				"BANKINDIA","BATAINDIA","BIOCON","CESC","CANBK","CENTURYTEX","DISHTV","ENGINERSIN"};
		
		String[] stockSymbolsSmall={"ALBK","ANDHRABANK","ASTRAZEN","ATUL","BEML","BBTC","CARERATING","CAPF","CEATLTD","CENTURYPLY",
				"CHENNPETRO","CROMPGREAV","DCBBANK","EQUITAS","FINCABLES"};
		
		for(int i=0; i < stockSymbolsLarge.length; i++){
			try{
				String url ="http://finance.google.com/finance/info?q="+stockSymbolsLarge[i];
				object = JSONParser.readJsonFromUrl(url);
				String dailyUrl = "https://www.google.com/finance/getprices?q=" + stockSymbolsLarge[i]  + "&p=14d&auto=0";
				stockDataDays = getDailyData(dailyUrl);
			} catch (Exception e){
				e.printStackTrace();
				System.out.println("Skipping " + stockSymbolsLarge[i]);
			} finally{
				Stock stock = new Stock();
				stock.setSymbol(stockSymbolsLarge[i]);
				stock.setCurrentPrice(Float.parseFloat(object.getString("l_cur").replace("GBX","").replace("&#8377;", "").replace(",", "")));
				stock.setType(2);
				stock.setStockDataDay(stockDataDays);
				System.out.println(stock.getCurrentPrice()); // remove afterwards
				stocks.add(stock);
			}
		}
		
		
		for(int i=0; i < stockSymbolsMed.length; i++){
			try{
				String url ="http://finance.google.com/finance/info?q="+stockSymbolsMed[i];
				object = JSONParser.readJsonFromUrl(url);
				String dailyUrl = "https://www.google.com/finance/getprices?q=" + stockSymbolsMed[i]  + "&p=14d&auto=0";
				stockDataDays = getDailyData(dailyUrl);
			} catch (Exception e){
				e.printStackTrace();
				System.out.println("Skipping " + stockSymbolsMed[i]);
			} finally{
				Stock stock = new Stock();
				stock.setSymbol(stockSymbolsMed[i]);
				stock.setCurrentPrice(Float.parseFloat(object.getString("l_cur").replace("GBX","").replace("&#8377;", "").replace(",", "")));
				stock.setType(1);
				stock.setStockDataDay(stockDataDays);
				System.out.println(stock.getCurrentPrice()); // remove afterwards
				stocks.add(stock);
			}
		}


		for(int i=0; i < stockSymbolsSmall.length; i++){
			try{
				String url ="http://finance.google.com/finance/info?q="+stockSymbolsSmall[i];
				object = JSONParser.readJsonFromUrl(url);
				String dailyUrl = "https://www.google.com/finance/getprices?q=" + stockSymbolsSmall[i]  + "&p=14d&auto=0";
				stockDataDays = getDailyData(dailyUrl);
			} catch (Exception e){
				e.printStackTrace();
				System.out.println("Skipping " + stockSymbolsSmall[i]);
			} finally {
				Stock stock = new Stock();
				stock.setSymbol(stockSymbolsSmall[i]);
				stock.setCurrentPrice(Float.parseFloat(object.getString("l_cur").replace("GBX","").replace("&#8377;", "").replace(",", "")));
				stock.setType(0);
				stock.setStockDataDay(stockDataDays);
				System.out.println(stock.getCurrentPrice()); // remove afterwards
				stocks.add(stock);
			}
		}
		
	}
	
	public void addDataToOracle(Connection connection){
		
		try{
			String clearStockTable= "delete from stockref";
			String clearStockWeekTable= "delete from stockWeek";
			String query = "insert into stockref values(?,?,?)";
			String daysQuery= "insert into stockWeek values(?,?,?,?,?,?,?,?)"; //write query here for new table.
			 
			Statement stmt = connection.createStatement();
			stmt.execute(clearStockWeekTable);
			connection.commit();
			
			Statement stmt2 = connection.createStatement();
			stmt2.execute(clearStockTable);
			connection.commit();

			PreparedStatement ps = connection.prepareStatement(query);
			for(int i=0; i<stocks.size(); i++){
				Stock stock = stocks.get(i);
				System.out.println("Saving " + stock.getSymbol() + " : " + stock.getCurrentPrice() + " : " + stock.getType());
				ps.setString(1, stock.getSymbol());
				ps.setFloat(2, stock.getCurrentPrice());
				ps.setInt(3, stock.getType());			
				ps.addBatch();	
				System.out.println(stock.getSymbol() + " added to batch");
			}
			ps.executeBatch();
			System.out.println("Batch Stockref Executed");
			ps.close();
			connection.commit();
			
			for(int i=0; i<stocks.size(); i++){
				ArrayList<StockDataDay> daysArray = stocks.get(i).getStockDataDay();
				PreparedStatement daysPs = connection.prepareStatement(daysQuery);
				for(int j=0; j<daysArray.size(); j++){
					daysPs.setString(1, stocks.get(i).getSymbol());
					daysPs.setInt(2, j+1); // usually 0 is not allowed in database integers so lets start index from 1
					daysPs.setInt(3, daysArray.get(j).getDate());
					daysPs.setFloat(4, daysArray.get(j).getClose());
					daysPs.setFloat(5, daysArray.get(j).getHigh());
					daysPs.setFloat(6, daysArray.get(j).getLow());
					daysPs.setFloat(7, daysArray.get(j).getOpen());
					daysPs.setFloat(8, daysArray.get(j).getVolume());
					daysPs.addBatch(); //call this after everything is added.
					System.out.println(stocks.get(i).getSymbol() + " day " +  j + " added to batch");
				}
				daysPs.executeBatch();
				System.out.println("Batch stockWeek Executed");
				daysPs.close();
				connection.commit();
			}
			
		} catch (Exception e){
			e.printStackTrace();
		}
		

	}

	public ArrayList<StockDataDay> getDailyData(String url) throws IOException{
		ArrayList<StockDataDay> stockDataDays = new ArrayList<>();
		
		URL googleData = new URL(url);
		
		URLConnection data = googleData.openConnection();
		Scanner input = new Scanner(data.getInputStream());
		if(input.hasNext()) {//skipping the header just the data
			input.nextLine();
			input.nextLine();
			input.nextLine();
			input.nextLine();
			input.nextLine();
			input.nextLine();
			input.nextLine();
		}
		while(input.hasNextLine()) {
			String line=input.nextLine();
			System.out.println(line);
			String[] parts = line.split(",");
			StockDataDay dataDay = new StockDataDay();
			dataDay.setDate(Integer.parseInt(parts[0].replace("a", "")));
			dataDay.setClose(Float.parseFloat(parts[1]));
			dataDay.setHigh(Float.parseFloat(parts[2]));
			dataDay.setLow(Float.parseFloat(parts[3]));
			dataDay.setOpen(Float.parseFloat(parts[4]));
			dataDay.setVolume(Float.parseFloat(parts[5]));
			stockDataDays.add(dataDay);
			System.out.println("Daily data fetched.");
		}
		
		input.close();
		return stockDataDays;
		
	}
	
	public ArrayList<Stock> getStocksArray(){
		return stocks;
	}

}
