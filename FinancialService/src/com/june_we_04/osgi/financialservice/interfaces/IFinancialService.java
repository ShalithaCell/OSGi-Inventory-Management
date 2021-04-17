package com.june_we_04.osgi.financialservice.interfaces;

import java.util.List;

import com.june_we_04.osgi.financialservice.model.FinancialLog;
import com.june_we_04.osgi.financialservice.model.TotalTransactionStatus;

public interface IFinancialService {
	
	/**
	 * Return all the financial information according to the store
	 * @param storeID
	 * @return
	 */
	public List<FinancialLog> GetAllFinancialRecords(int storeID);
	
	/**
	 * Add new financial information
	 * @param log
	 * @return
	 */
	public boolean AddNewRecord(FinancialLog log);
	
	/**
	 * Clean all the records
	 */
	public void CleanAllRecords();
	
	/**
	 * Return all the transaction information
	 * @param storeID
	 * @return
	 */
	public TotalTransactionStatus GetFinancialStatus(int storeID);
	

}
