package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cyrpt.Bcrypt;
import strategyPattern.Context;
import strategyPattern.StrategyPattern;

@Controller
public class StockController {
	
	String userId;
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception ex) {
		ModelAndView model = new ModelAndView("error");
		return model;
	}
		
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView userAuth() {
	      return new ModelAndView("login", "command", new AuthUser());
	}   
	
	@RequestMapping(value = "/SavedStocks", method = RequestMethod.POST)
	public String getSavedStocks(ModelMap model){
		try{
			ArrayList<Stock> savedArray = new ArrayList<>();
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe","SYSTEM","ichbinmich");
			Statement statement = con.createStatement();
		    String query = ("SELECT * FROM savedStock where userid="+userId);
		    ResultSet rs = statement.executeQuery(query);
		    while (rs.next()) {
		    	String symbol = rs.getString("symbol");
		    	float price = rs.getFloat("price");
		    	int type = rs.getInt("type");
		    	String riskType = rs.getString("RISKTYPE");
		    	
		    	Stock stock = new Stock();
		    	stock.setSymbol(symbol);
		    	stock.setPrice(price);
		    	stock.setType(type);
		    	stock.setRiskType(riskType);
		    	
		    	savedArray.add(stock);
		    }
		    
		    model.addAttribute("savedArray", savedArray);
		    model.addAttribute("userId", userId);
		} catch (Exception e){
			e.printStackTrace();
		}
		return "savedStocks";
	}
	
	@RequestMapping(value = "/recommendedStocks", method = RequestMethod.POST)
	public String getStocks(@RequestParam("type") String selected, ModelMap model){
		String type = selected;
		
		switch (selected) {
		case "1":
			type = "low";
			break;
		case "2":
			type = "medium";
			break;
		case "3":
			type = "high";
			break;
		default:
			type = "low";
			break;
		}
				
		StrategyPattern strategy = new StrategyPattern();
		strategy.getDataFromOracle(type);
		Context context = strategy.ctx;
		String[] symbols = context.symbol;
		
		String[] types = new String[10];
		for(int i=0; i<10; i++){
			if(context.type[i] == 0){
				types[i] = "Small";
			} else if(context.type[i] == 1){
				types[i] = "Medium";
			} else if(context.type[i] == 2){
				types[i] = "Large";
			} else {
				types[i] = "Unknown";
			}
		}
		
		String[] prices = new String[10];
		for(int i=0; i<10; i++){
			prices[i] = String.valueOf(context.price[i]);
		}
		
		model.addAttribute("type", type);   
		System.out.println("User ID: " + userId);
		
		for(int i=0; i<10; i++){
			model.addAttribute("symbols" + i, symbols[i]);
			model.addAttribute("types" + i, types[i]);
			model.addAttribute("prices" + i, prices[i]);
		}
		
		return "stockTable";
	}
	
	@RequestMapping(value="/recommendation", method = RequestMethod.POST)
	public String goToRecommendation(){
		return "main";
	}
	
	@RequestMapping(value="/postSave", method = RequestMethod.POST)
	public String addToSave(HttpServletRequest request, ModelMap map){
		
		String riskType = request.getParameter("riskType");
		
		String[] symbolSave = request.getParameterValues("symbolSave");
		String[] priceSave = request.getParameterValues("priceSave");
		String[] typeSave = request.getParameterValues("typeSave");
		
		String[] saveCheck = request.getParameterValues("saveCheck");
		
		ArrayList<Stock> saveStock = new ArrayList<>();
		
		for(int i=0; i<saveCheck.length; i++){
			Stock saveStockSingle = new Stock();
			
			String symbol = symbolSave[Integer.parseInt(saveCheck[i])];
			saveStockSingle.setSymbol(symbol);
			
			float price = Float.parseFloat(priceSave[Integer.parseInt(saveCheck[i])]);
			saveStockSingle.setPrice(price);
			
			int type;
			if(typeSave[Integer.parseInt(saveCheck[i])].equals("Small")){
				type = 0;
			} else if(typeSave[Integer.parseInt(saveCheck[i])].equals("Medium")){
				type = 1;
			} else {
				type = 2;
			}

			saveStockSingle.setType(type);
			saveStock.add(saveStockSingle);
		}
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe","SYSTEM","ichbinmich");
			
			PreparedStatement pstmt;
			pstmt=con.prepareStatement("insert into savedStock values(?,?,?,?,?)");
			
			for(int i=0; i<saveStock.size(); i++){
				System.out.println("User Id for Save: " + userId);
				pstmt.setInt(1, Integer.parseInt(userId));
				pstmt.setString(2, saveStock.get(i).getSymbol());
				pstmt.setFloat(3, saveStock.get(i).getPrice());
				pstmt.setInt(4, saveStock.get(i).getType());
				pstmt.setString(5, riskType);
				pstmt.addBatch();
			}
			
			pstmt.executeBatch();
			pstmt.close();
			con.commit();
			
		} catch (Exception e){
			e.printStackTrace();
		}
				
		return "main";
	}
	
	@RequestMapping(value="/error", method = RequestMethod.POST)
	public String error(){
		return "error";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.POST)
	public String logout(){
		return "logout";
	}
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.POST)
	   public String authenticateStatus(@ModelAttribute("SpringWeb")AuthUser user, ModelMap model) {
		String username = user.getUsername();
		String password = user.getPassword();
		Connection con = null;
	      boolean authStatus = false;
	      String jspPage = "after";
			try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe","SYSTEM","ichbinmich");
				System.out.println("Connection is not null");
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM user_info where username=\'"+username+"\'");
				while(rs.next())
				{
					userId = String.valueOf(rs.getInt(1));
					String hashed = rs.getString(3);
					if(Bcrypt.checkpw(password, hashed)){
						authStatus = true;
					}
					else
						authStatus = false;
				}
				
				rs.close();
				stmt.close();
				
			} catch (Exception e) {
				e.printStackTrace();
				authStatus = false;
			}
			
			if(authStatus){
				try {
					con.setAutoCommit(false);
					UpdateService updater = new UpdateService();
					updater.getDataFromWeb();
					updater.addDataToOracle(con);
					con.setAutoCommit(true);
					con.close();
				} catch (Exception e) {
					model.addAttribute("error", "Server Error. Contact your system admin.");
					jspPage = "login";
					e.printStackTrace();
				} finally {
					jspPage = "main";
				}
						
			}
			else {
				model.addAttribute("error", "Login Failed. Try Again.");
				jspPage = "login";
			}
	      
	      return jspPage;
	   }

}
