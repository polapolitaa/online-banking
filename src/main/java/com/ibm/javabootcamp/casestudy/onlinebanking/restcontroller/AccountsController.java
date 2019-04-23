package com.ibm.javabootcamp.casestudy.onlinebanking.restcontroller;

import java.math.BigDecimal;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;

import com.ibm.javabootcamp.casestudy.onlinebanking.domain.Accounts;
import com.ibm.javabootcamp.casestudy.onlinebanking.service.AccountsService;
import com.ibm.javabootcamp.casestudy.onlinebanking.service.AccountsServiceImpl;

@Path("/accounts")
public class AccountsController {
	
	private AccountsService accountsService;
	
	public AccountsController() {
		this.accountsService = new AccountsServiceImpl();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Accounts> getAccounts(
			@QueryParam("acct_shortName") String shortName,
			@QueryParam("acct_type") String acctType,
			@QueryParam("curr_balance") BigDecimal currBalance){
		
		try {
			
			List<Accounts> accounts;
			
			if(StringUtils.isAllBlank(shortName, acctType)) {
				accounts = accountsService.findAll();
				
			}else {
				accounts = accountsService.findByName(shortName, acctType, currBalance);
			}
			
			return accounts;
				
		}catch(Exception e) {
			throw new WebApplicationException(e);
		}
	}
	
}
