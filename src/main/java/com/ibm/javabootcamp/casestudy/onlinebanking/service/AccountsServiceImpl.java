package com.ibm.javabootcamp.casestudy.onlinebanking.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ibm.javabootcamp.casestudy.onlinebanking.dao.AccountsJdbcConnect;
import com.ibm.javabootcamp.casestudy.onlinebanking.domain.Accounts;

public class AccountsServiceImpl implements AccountsService {

	AccountsJdbcConnect accountsDao;

	public AccountsServiceImpl() {
		this.accountsDao = AccountsJdbcConnect.getinstance();
	}

	@Override
	public void addAccount(Accounts account) {
		accountsDao.addAccount(account);

	}

	@Override
	public List<Accounts> showAcctDetails() {

		return accountsDao.showAcctDetails();
	}

	@Override
	public Accounts find(Long id) {

		return accountsDao.findAccount(id);
	}

	@Override
	public void update(Accounts account) {

		accountsDao.update(account);

	}

	@Override
	public void payMerchant(Accounts account) {
		
		accountsDao.payMerchant(account);

	}

}
