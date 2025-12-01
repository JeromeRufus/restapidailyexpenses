package com.dailyexpenses.dto.response;

import java.util.Map;

public class ExpenseResponse {
	
	private String expenseId;
	private String description;
	private double totalAmount;
	private Map<String,Double> userShares;
	
	public ExpenseResponse() {
	}
	public ExpenseResponse(String expenseId, String description, double totalAmount, Map<String, Double> userShares) {
		this.expenseId = expenseId;
		this.description = description;
		this.totalAmount = totalAmount;
		this.userShares = userShares;
	}
	
	public String getExpenseId() {
		return expenseId;
	}
	public void setExpenseId(String expenseId) {
		this.expenseId = expenseId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Map<String, Double> getUserShares() {
		return userShares;
	}
	public void setUserShares(Map<String, Double> userShares) {
		this.userShares = userShares;
	}
	
	
	
	
	
	

}
