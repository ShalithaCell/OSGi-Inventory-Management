package com.june_we_04.osgi.financialservice.model;

public class FinancialLog {
	
	private int itemID;
	private String itemName;
	private int qty;
	private double sellPrice;
	private double buyPrice;
	private int storeID;
	
	
	
	public FinancialLog() {
		super();
		this.itemID = 0;
		this.itemName = "";
		this.qty = 0;
		this.sellPrice = 0;
		this.buyPrice = 0;
		this.storeID = 0;
	}

	public FinancialLog(int itemID, String itemName, int qty, double sellPrice, double buyPrice, int storeID) {
		super();
		this.itemID = itemID;
		this.itemName = itemName;
		this.qty = qty;
		this.sellPrice = sellPrice;
		this.buyPrice = buyPrice;
		this.storeID = storeID;
	}
	
	public int getStoreID() {
		return storeID;
	}

	public void setStoreID(int storeID) {
		this.storeID = storeID;
	}

	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public double getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}
	public double getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}
	
	

}
