package com.dailyexpenses.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import com.dailyexpenses.dto.request.ExpenseRequest;
import com.dailyexpenses.dto.response.ExpenseResponse;
import com.dailyexpenses.model.Expense;
import com.dailyexpenses.repository.ExpenseRepository;
import com.dailyexpenses.service.ExpenseService;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public ExpenseResponse addExpenseService(ExpenseRequest request) {
        Map<String, Double> userShares = new HashMap<>();
        List<String> users = request.getParticipants();

        if (users == null || users.isEmpty()) {
            throw new IllegalArgumentException("Participants cannot be empty");
        }

        switch (request.getSplitType().toUpperCase()) {
            case "EQUAL":
                double equalShare = request.getTotalAmount() / users.size();
                users.forEach(user -> userShares.put(user, equalShare));
                break;

            case "EXACT":
                if (users.size() != request.getExactAmounts().size()) {
                    throw new IllegalArgumentException("Exact amounts must match participants size");
                }
                double sumExact = request.getExactAmounts().stream().mapToDouble(Double::doubleValue).sum();
                if (Double.compare(sumExact, request.getTotalAmount()) != 0) {
                    throw new IllegalArgumentException("Sum of exact amounts must equal total amount");
                }
                IntStream.range(0, users.size())
                        .forEach(i -> userShares.put(users.get(i), request.getExactAmounts().get(i)));
                break;

            case "PERCENTAGE":
                if (users.size() != request.getPercentage().size()) {
                    throw new IllegalArgumentException("Percentage list must match participants size");
                }
                int totalPercent = request.getPercentage().stream().mapToInt(Integer::intValue).sum();
                if (totalPercent != 100) {
                    throw new IllegalArgumentException("Percentages must add up to 100");
                }
                IntStream.range(0, users.size())
                        .forEach(i -> userShares.put(users.get(i),
                                request.getPercentage().get(i) / 100.0 * request.getTotalAmount()));
                break;

            default:
                throw new IllegalArgumentException("Invalid split type. Use EQUAL, EXACT, or PERCENTAGE.");
        }

        // Save to database
        Expense expense = new Expense();
        expense.setDescription(request.getDescription());
        expense.setTotalAmount(request.getTotalAmount());
        expense.setSplitType(request.getSplitType());
        Expense savedExpense = expenseRepository.save(expense);

        return new ExpenseResponse(savedExpense.getId(), savedExpense.getDescription(),
                savedExpense.getTotalAmount(), userShares);
    }

    @Override
    public ExpenseResponse getExpense(String id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Expense not found with ID: " + id));
        return new ExpenseResponse(expense.getId(), expense.getDescription(),
                expense.getTotalAmount(), new HashMap<>());
    }

    @Override
    public List<ExpenseResponse> getAllExpenses() {
        List<Expense> expenses = expenseRepository.findAll();
        return expenses.stream()
                .map(e -> new ExpenseResponse(e.getId(), e.getDescription(), e.getTotalAmount(), new HashMap<>()))
                .toList();
    }

    @Override
    public byte[] generateBalanceSheetExcel() throws IOException {
        List<Expense> expenses = expenseRepository.findAll();

        try (XSSFWorkbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            XSSFSheet sheet = workbook.createSheet("Balance Sheet");

            // Header
            XSSFCellStyle headerStyle = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            headerStyle.setFont(font);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            String[] columns = { "Expense ID", "Description", "Total Amount", "Split Type" };
            XSSFRow headerRow = sheet.createRow(0);
            for (int i = 0; i < columns.length; i++) {
                XSSFCell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerStyle);
            }

            int rowNum = 1;
            for (Expense e : expenses) {
                XSSFRow row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(e.getId());
                row.createCell(1).setCellValue(e.getDescription());
                row.createCell(2).setCellValue(e.getTotalAmount());
                row.createCell(3).setCellValue(e.getSplitType());
            }

            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(out);
            return out.toByteArray();
        }
    }
}
