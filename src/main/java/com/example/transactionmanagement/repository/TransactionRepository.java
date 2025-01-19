package com.example.transactionmanagement.repository;

import com.example.transactionmanagement.model.Transaction;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TransactionRepository {
    private final List<Transaction> transactions = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Transaction save(Transaction transaction) {
        if (transaction.getId() == null) {
            transaction.setId(idGenerator.getAndIncrement());
        }
        if (transaction.getDate() == null) {
            transaction.setDate(java.time.LocalDateTime.now());
        }
        transactions.add(transaction);
        return transaction;
    }

    public Optional<Transaction> findById(Long id) {
        return transactions.stream().filter(t -> t.getId().equals(id)).findFirst();
    }

    public List<Transaction> findAll() {
        return transactions;
    }

    public boolean deleteById(Long id) {
        return transactions.removeIf(t -> t.getId().equals(id));
    }

    public List<Transaction> findAll(int page, int size) {
        int start = (page - 1) * size;
        int end = Math.min(start + size, transactions.size());
        return transactions.subList(start, end);
    }
}
