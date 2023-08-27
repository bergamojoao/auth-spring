package com.example.auth.features.transaction.dtos;

import com.example.auth.features.transaction.entities.Transaction;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateTransactionDTO(@NotNull LocalDateTime date, @NotNull String type, @NotNull String category, @NotNull double value) {
    public Transaction toTransaction(){
        Transaction  transaction = new Transaction();
        transaction.setDate(date);
        transaction.setType(type);
        transaction.setCategory(category);
        transaction.setValue(value);
        return transaction;
    }
}
