package com.example.transactionmanagement.service;

import com.example.transactionmanagement.model.Transaction;
import com.example.transactionmanagement.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getAllTransactions(int page, int size) {
        return transactionRepository.findAll(page, size);
    }

    public boolean deleteTransaction(Long id) {
        return transactionRepository.deleteById(id);
    }
}
