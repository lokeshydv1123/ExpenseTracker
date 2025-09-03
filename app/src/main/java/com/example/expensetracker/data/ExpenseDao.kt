package com.example.expensetracker.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Query("SELECT * FROM expenses ORDER BY date DESC")
    fun getAllExpenses(): Flow<List<Expense>>
    
    @Query("SELECT SUM(amount) FROM expenses")
    fun getTotalBalance(): Flow<Double?>
    
    @Insert
    suspend fun insertExpense(expense: Expense)
    
    @Delete
    suspend fun deleteExpense(expense: Expense)
    
    @Update
    suspend fun updateExpense(expense: Expense)
}
