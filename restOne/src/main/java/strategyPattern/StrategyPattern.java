package strategyPattern;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class StrategyPattern {
	
	public Context ctx;
	
	public void getDataFromOracle(String risk) {

		ArrayList <Float> large = new ArrayList <Float>();
		ArrayList <Float> med = new ArrayList <Float>();
		ArrayList <Float> small = new ArrayList <Float>();
		ArrayList <Integer> type1 = new ArrayList <Integer>();
		ArrayList <Integer> type2 = new ArrayList <Integer>();
		ArrayList <Integer> type3 = new ArrayList <Integer>();	 
	
		ArrayList <String> symbol1 = new ArrayList <String>();
		ArrayList <String> symbol2 = new ArrayList <String>();
		ArrayList <String> symbol3 = new ArrayList <String>();		  
	
		float price;
		int type;
		String symbol;	  
	
		Connection con = null;
		try {
			//Connection of oracle
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe","SYSTEM","ichbinmich");
		
			Statement stmt1 = null;
			String query1 = "select * from stockref where type=2 order by price DESC";
			String query2 = "select * from stockref where type=1 order by price DESC";
			String query3 = "select * from stockref where type=0 order by price DESC";
	
			try {
				stmt1 = con.createStatement();
				ResultSet rs1 = stmt1.executeQuery(query1);
				while (rs1.next()) {
					type=rs1.getInt("type");
				    price=rs1.getFloat("price");
				    symbol=rs1.getString("symbol");
				    large.add(price);
				    type1.add(type);
				    symbol1.add(symbol);
				}
				
				ResultSet rs2 = stmt1.executeQuery(query2);
				while (rs2.next()) {
						type=rs2.getInt("type");
				        price=rs2.getFloat("price");
				        symbol=rs2.getString("symbol");
				        med.add(price);
				        type2.add(type);
				        symbol2.add(symbol);		         
				}
				        
				ResultSet rs3 = stmt1.executeQuery(query3);
				while (rs3.next()) {    
					type=rs3.getInt("type");
					price=rs3.getFloat("price");
					symbol=rs3.getString("symbol");
					small.add(price);
					type3.add(type);
					symbol3.add(symbol);
				        
				}
			} catch (SQLException e ) {
				e.printStackTrace();
			}	     
				   
			String riskname=risk;
			if(riskname.equals("high")==true){
				ctx = new Context(new High());
				ctx.arrange(large,med,small,type1,type2,type3,symbol1,symbol2,symbol3);
			} else if(riskname.equals("medium")==true){
				ctx = new Context(new Med());
				ctx.arrange(large,med,small,type1,type2,type3,symbol1,symbol2,symbol3);
			} else if(riskname.equals("low")==true){
				ctx = new Context(new Low());
				ctx.arrange(large,med,small,type1,type2,type3,symbol1,symbol2,symbol3);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
