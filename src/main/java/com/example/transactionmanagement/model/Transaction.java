package com.example.transactionmanagement.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Transaction {
    private Long id;
    private String description;
    private BigDecimal amount;
    private LocalDateTime date;
}
