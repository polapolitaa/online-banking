package com.ibm.javabootcamp.casestudy.onlinebanking.domain;

public class Depositors {

	Long dep_id;
	private String dep_fname;
	private String dep_lname;
	private String dep_mname;
	private String address;
	private long contactNo;
	
	
	public Depositors(String dep_fname, String dep_lname, String dep_mname, String address, long contactNo) {
		this(null, dep_fname, dep_lname, dep_mname, address, contactNo);
	}

	public Depositors(Long dep_id, String dep_fname, String dep_lname, String dep_mname, String address, long contactNo) {
		this.dep_id = dep_id;
		this.dep_fname = dep_fname;
		this.dep_lname = dep_lname;
		this.dep_mname = dep_mname;
		this.address = address;
		this.contactNo = contactNo;
	}

	public Long getDep_id() {
		return dep_id;
	}

	public void setDep_id(Long dep_id) {
		this.dep_id = dep_id;
	}

	public String getDep_fname() {
		return dep_fname;
	}

	public void setDep_fname(String dep_fname) {
		this.dep_fname = dep_fname;
	}

	public String getDep_lname() {
		return dep_lname;
	}

	public void setDep_lname(String dep_lname) {
		this.dep_lname = dep_lname;
	}

	public String getDep_mname() {
		return dep_mname;
	}

	public void setDep_mname(String dep_mname) {
		this.dep_mname = dep_mname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getContactNo() {
		return contactNo;
	}

	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}

}
