package com.ibm.javabootcamp.casestudy.onlinebanking.dao;

import java.math.BigDecimal;
import java.util.List;

import com.ibm.javabootcamp.casestudy.onlinebanking.domain.Accounts;


public interface AccountsDao {
	
	public List<Accounts> showAcctDetails();

	public Accounts findAccount(Long id);

	public void addAccount(Accounts account);
	
	public void update(Accounts account);
	
	public void payMerchant(Accounts account);
	
	public void transferFundAdd(Accounts account, Long id);
	
}
