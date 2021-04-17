package com.june_we_04.osgi.financialservice.application;

import java.util.List;

import com.june_we_04.osgi.financialservice.interfaces.IFinancialService;
import com.june_we_04.osgi.financialservice.model.FinancialLog;
import com.june_we_04.osgi.financialservice.model.InMemoryDb;
import com.june_we_04.osgi.financialservice.model.TotalTransactionStatus;

public class FinancialServiceImpl implements IFinancialService{
	
	

	@Override
	public List<FinancialLog> GetAllFinancialRecords(int storeID) {
		
		InMemoryDb dataCollection = InMemoryDb.getInstance();
		
		return dataCollection.GetFinancialInformation(storeID);
	}

	@Override
	public boolean AddNewRecord(FinancialLog log) {
		
		InMemoryDb dataCollection = InMemoryDb.getInstance();
		
		dataCollection.AddFinancialRecord(log);
		
		return true;
	}

	@Override
	public void CleanAllRecords() {
		InMemoryDb dataCollection = InMemoryDb.getInstance();
		dataCollection.clearFinancialLog();
	}

	@Override
	public TotalTransactionStatus GetFinancialStatus(int storeID) {
		
		InMemoryDb dataCollection = InMemoryDb.getInstance();
		
		TotalTransactionStatus status = new TotalTransactionStatus();
		
		double netProfit = 0;
		double totalAmount = 0;
		
		for (FinancialLog log : dataCollection.GetFinancialInformation(storeID)) {
			netProfit+= (log.getSellPrice()-log.getBuyPrice());
			totalAmount += log.getSellPrice();
		}
		
		status.setNetProfit(netProfit);
		status.setTotalTransactionAmount(totalAmount);
		
		return status;
	}

}
