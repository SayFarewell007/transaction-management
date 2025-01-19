package com.example.transactionmanagement.service;


import com.example.transactionmanagement.model.Transaction;
import com.example.transactionmanagement.repository.TransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    private Transaction transaction;

    @Before
    public void setUp() {
        transaction = new Transaction();
        transaction.setId(1L);
        transaction.setAmount(new BigDecimal("100.00"));
        transaction.setDate(LocalDateTime.now());
        transaction.setDescription("Test Transaction");
    }

    @Test
    public void createTransaction_ShouldReturnSavedTransaction() {
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        Transaction savedTransaction = transactionService.createTransaction(transaction);

        assertEquals(transaction, savedTransaction);
        verify(transactionRepository, times(1)).save(transaction);
    }

    @Test
    public void getTransactionById_TransactionExists_ShouldReturnTransaction() {
        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));

        Optional<Transaction> result = transactionService.getTransactionById(1L);

        assertTrue(result.isPresent());
        assertEquals(transaction, result.get());
        verify(transactionRepository, times(1)).findById(1L);
    }

    @Test
    public void getTransactionById_TransactionDoesNotExist_ShouldReturnEmptyOptional() {
        when(transactionRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<Transaction> result = transactionService.getTransactionById(2L);

        assertFalse(result.isPresent());
        verify(transactionRepository, times(1)).findById(2L);
    }

    @Test
    public void getAllTransactions_ShouldReturnAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        when(transactionRepository.findAll()).thenReturn(transactions);

        List<Transaction> result = transactionService.getAllTransactions();

        assertEquals(transactions, result);
        verify(transactionRepository, times(1)).findAll();
    }

    @Test
    public void getAllTransactionsWithPagination_ShouldReturnPaginatedTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        when(transactionRepository.findAll(0, 10)).thenReturn(transactions);

        List<Transaction> result = transactionService.getAllTransactions(0, 10);

        assertEquals(transactions, result);
        verify(transactionRepository, times(1)).findAll(0, 10);
    }

    @Test
    public void deleteTransaction_TransactionExists_ShouldReturnTrue() {
        when(transactionRepository.deleteById(1L)).thenReturn(true);

        boolean result = transactionService.deleteTransaction(1L);

        assertTrue(result);
        verify(transactionRepository, times(1)).deleteById(1L);
    }

    @Test
    public void deleteTransaction_TransactionDoesNotExist_ShouldReturnFalse() {
        when(transactionRepository.deleteById(2L)).thenReturn(false);

        boolean result = transactionService.deleteTransaction(2L);

        assertFalse(result);
        verify(transactionRepository, times(1)).deleteById(2L);
    }
}
