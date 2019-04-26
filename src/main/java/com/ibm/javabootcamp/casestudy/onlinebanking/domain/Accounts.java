package com.ibm.javabootcamp.casestudy.onlinebanking.domain;

import java.math.BigDecimal;

public class Accounts {

	private BigDecimal acct_no;
	private String acct_shortname;
	private String acct_type;
	private BigDecimal curr_balance;
	
	public Accounts() {
		
	}
	
	public Accounts(BigDecimal acct_no) {
		this.acct_no = acct_no;
	}
	
	
	public Accounts(BigDecimal acct_no, String acct_shortname, String acct_type, BigDecimal curr_balance) {
		super();
		this.acct_no = acct_no;
		this.acct_shortname = acct_shortname;
		this.acct_type = acct_type;
		this.curr_balance = curr_balance;
	}
	
	
	public BigDecimal getAcct_no() {
		return acct_no;
	}
	public void setAcct_no(BigDecimal acct_no) {
		this.acct_no = acct_no;
	}
	public String getAcct_shortname() {
		return acct_shortname;
	}
	public void setAcct_shortname(String acct_shortname) {
		this.acct_shortname = acct_shortname;
	}
	public String getAcct_type() {
		return acct_type;
	}
	public void setAcct_type(String acct_type) {
		this.acct_type = acct_type;
	}
	public BigDecimal getCurr_balance() {
		return curr_balance;
	}
	public void setCurr_balance(BigDecimal curr_balance) {
		this.curr_balance = curr_balance;
	}
	
	
	
}
