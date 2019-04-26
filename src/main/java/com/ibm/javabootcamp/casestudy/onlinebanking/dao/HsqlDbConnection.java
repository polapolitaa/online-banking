package com.ibm.javabootcamp.casestudy.onlinebanking.dao;

import org.hsqldb.jdbc.JDBCDataSource;

public abstract class HsqlDbConnection {
	
	
	public JDBCDataSource dataSource;
	
	public void init() {
		dataSource = new JDBCDataSource();
		dataSource.setDatabase("jdbc:hsqldb:hsql://localhost/onlinebanking");
		dataSource.setUser("SA");
		dataSource.setPassword("");
		
		
	}
	
}
