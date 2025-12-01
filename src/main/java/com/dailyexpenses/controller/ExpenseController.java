package com.dailyexpenses.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dailyexpenses.dto.request.ExpenseRequest;
import com.dailyexpenses.dto.response.ExpenseResponse;
import com.dailyexpenses.service.impl.ExpenseServiceImpl;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

	private final ExpenseServiceImpl expenseService;

	public ExpenseController(ExpenseServiceImpl expenseService) {
		this.expenseService = expenseService;
	}

	@PostMapping
	public ExpenseResponse addExpense(@RequestBody ExpenseRequest request) {
		return expenseService.addExpenseService(request);
	}

	@GetMapping("/{id}")
	public ExpenseResponse getExpense(@PathVariable String id) {
		return expenseService.getExpense(id);
	}

	@GetMapping
	public List<ExpenseResponse> getAllExpenses() {
		return expenseService.getAllExpenses();
	}

	@GetMapping("/balance-sheet/download")
	public ResponseEntity<byte[]> downloadBalanceSheet() throws IOException {
	    byte[] excelFile = expenseService.generateBalanceSheetExcel();

	    return ResponseEntity.ok()
	            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=balance-sheet.xlsx")
	            .contentType(MediaType.parseMediaType(
	                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
	            .body(excelFile);
	}

}
