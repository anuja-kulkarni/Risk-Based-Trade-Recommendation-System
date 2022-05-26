package com.example;

import java.util.ArrayList;

public class Stock {
	private String symbol;
	private float price;
	private int type;
	private String riskType;
	private float currentPrice;
	private int stockType;
	ArrayList<StockDataDay> stockDataDay = new ArrayList<>();
	
	public float getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(float currentPrice) {
		this.currentPrice = currentPrice;
	}
	public int getStockType() {
		return stockType;
	}
	public void setStockType(int stockType) {
		this.stockType = stockType;
	}
	public ArrayList<StockDataDay> getStockDataDay() {
		return stockDataDay;
	}
	public void setStockDataDay(ArrayList<StockDataDay> stockDataDay) {
		this.stockDataDay = stockDataDay;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	public String getRiskType() {
		return riskType;
	}
	
	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}

}
