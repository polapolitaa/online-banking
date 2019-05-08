package com.ibm.javabootcamp.casestudy.onlinebanking.service;

import java.math.BigDecimal;
import java.util.List;

import com.ibm.javabootcamp.casestudy.onlinebanking.domain.Accounts;


public interface AccountsService {
	
	public void addAccount(Accounts account);

	public List<Accounts> showAcctDetails();

	public Accounts find(Long id);
	
	public void update(Accounts account);
	
	public void payMerchant(Accounts account);

}
