package com.ibm.javabootcamp.casestudy.onlinebanking.domain;

import java.math.BigDecimal;
import java.sql.Date;

public class Payments {
	
	Long pymt_id;
	private Long pymt_no;
	private BigDecimal pymt_amount;
	private Date pymt_date;
	
	public Payments(Long pymt_no, BigDecimal pymt_amount, Date pymt_date) {
		this(null, pymt_no, pymt_amount, pymt_date);
	}
	
	
	public Payments(Long pymt_id, Long pymt_no, BigDecimal pymt_amount, Date pymt_date) {
		super();
		this.pymt_id = pymt_id;
		this.pymt_no = pymt_no;
		this.pymt_amount = pymt_amount;
		this.pymt_date = pymt_date;
	}
	
	
	public Long getPymt_id() {
		return pymt_id;
	}
	public void setPymt_id(Long pymt_id) {
		this.pymt_id = pymt_id;
	}
	public Long getPymt_no() {
		return pymt_no;
	}
	public void setPymt_no(Long pymt_no) {
		this.pymt_no = pymt_no;
	}
	public BigDecimal getPymt_amount() {
		return pymt_amount;
	}
	public void setPymt_amount(BigDecimal pymt_amount) {
		this.pymt_amount = pymt_amount;
	}
	public Date getPymt_date() {
		return pymt_date;
	}
	public void setPymt_date(Date pymt_date) {
		this.pymt_date = pymt_date;
	}
	
	
	
}
