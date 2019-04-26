package com.ibm.javabootcamp.casestudy.onlinebanking.restcontroller;

import java.math.BigDecimal;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
			@QueryParam("acct_no") BigDecimal acctNo,
			@QueryParam("acct_shortName") String shortName){
		
		try {
			
			List<Accounts> accounts;
			
			if(StringUtils.isAllBlank(shortName)) {
				accounts = accountsService.findAll();
				
			}else {
				accounts = accountsService.findByName(acctNo, shortName);
			}
			
			return accounts;
				
		}catch(Exception e) {
			throw new WebApplicationException(e);
		}
	}
	
	@GET
	@Path("{acct_no}")
	@Produces(MediaType.APPLICATION_JSON)
	public Accounts getAccount(@PathParam("acct_no") String acctNo) {
		try {
			Long longAcctNo = Long.parseLong(acctNo);
			Accounts account = accountsService.find(longAcctNo);
			return account;
		}catch(Exception e){
			throw new WebApplicationException(e);
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addAccount(Accounts account) {

		try {
			accountsService.addAccount(account);
			String result = "Account saved : " + account.getAcct_shortname();
			return Response.status(201).entity(result).build();
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}

	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateUser(Accounts account) {

		try {
			accountsService.update(account);
			String result = "Account " + account.getAcct_no() + " updated.";
			return Response.status(200).entity(result).build();
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}

	}
	
}