package com.poly.myasm.DAO;

import com.poly.myasm.DTO.Income_Expense;

import java.util.List;

public interface Inter_Income_Expense_DAO {
    List<Income_Expense> get(int _flags);
    List<Income_Expense> get();
    Income_Expense getId(int id);
    void insert(Income_Expense income_expense);
    void update(Income_Expense income_expense);
    void detele(int income_expense_id);
}
