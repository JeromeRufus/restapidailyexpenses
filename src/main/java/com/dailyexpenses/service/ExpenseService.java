package com.dailyexpenses.service;

import java.io.IOException;
import java.util.List;

import com.dailyexpenses.dto.request.ExpenseRequest;
import com.dailyexpenses.dto.response.ExpenseResponse;

public interface ExpenseService {
	ExpenseResponse addExpenseService(ExpenseRequest request);
	ExpenseResponse getExpense(String id);
	List<ExpenseResponse> getAllExpenses();
	byte[] generateBalanceSheetExcel() throws IOException;

}
