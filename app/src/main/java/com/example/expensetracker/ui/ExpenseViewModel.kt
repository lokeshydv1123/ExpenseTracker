package com.example.expensetracker.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.data.Expense
import com.example.expensetracker.data.ExpenseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ExpenseViewModel(private val repository: ExpenseRepository) : ViewModel() {
    val expenses = repository.getAllExpenses()
    val totalBalance = repository.getTotalBalance()
    
    private val _showAddExpenseDialog = MutableStateFlow(false)
    val showAddExpenseDialog: StateFlow<Boolean> = _showAddExpenseDialog.asStateFlow()
    
    fun showAddExpenseDialog() {
        _showAddExpenseDialog.value = true
    }
    
    fun hideAddExpenseDialog() {
        _showAddExpenseDialog.value = false
    }
    
    fun addExpense(amount: Double, description: String, category: String) {
        viewModelScope.launch {
            val expense = Expense(
                amount = amount,
                description = description,
                category = category
            )
            repository.insertExpense(expense)
        }
    }
    
    fun deleteExpense(expense: Expense) {
        viewModelScope.launch {
            repository.deleteExpense(expense)
        }
    }
}
