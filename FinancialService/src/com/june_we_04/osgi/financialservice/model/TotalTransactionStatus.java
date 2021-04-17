package com.june_we_04.osgi.financialservice.model;

public class TotalTransactionStatus {
	
	private double netProfit;
	private double totalTransactionAmount;
	
	public TotalTransactionStatus() {
		this.netProfit = 0;
		this.totalTransactionAmount = 0;
	}
	
	public TotalTransactionStatus(double netProfit, double totalTransactionAmount) {
		super();
		this.netProfit = netProfit;
		this.totalTransactionAmount = totalTransactionAmount;
	}
	
	public double getNetProfit() {
		return netProfit;
	}
	public void setNetProfit(double netProfit) {
		this.netProfit = netProfit;
	}
	public double getTotalTransactionAmount() {
		return totalTransactionAmount;
	}
	public void setTotalTransactionAmount(double totalTransactionAmount) {
		this.totalTransactionAmount = totalTransactionAmount;
	}
	
	

}
