package com.ibm.javabootcamp.casestudy.onlinebanking.domain;

public class Merchants {
	
	private Long merch_id;
	private String merch_name;
	
	
	public Merchants() {
		
	}
	
	public Merchants(String merch_name) {
		this(null, merch_name);
	}
	
	
	public Merchants(Long merch_id, String merch_name) {
		super();
		this.merch_id = merch_id;
		this.merch_name = merch_name;
	}
	
	
	public Long getMerch_id() {
		return merch_id;
	}
	public void setMerch_id(Long merch_id) {
		this.merch_id = merch_id;
	}
	public String getMerch_name() {
		return merch_name;
	}
	public void setMerch_name(String merch_name) {
		this.merch_name = merch_name;
	}
	
}
