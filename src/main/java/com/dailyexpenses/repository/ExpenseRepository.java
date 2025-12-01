package com.dailyexpenses.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dailyexpenses.model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense,String> {

}
