package com.example.auth.features.transaction;

import com.example.auth.features.transaction.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
    Optional<Transaction> findByIdAndUserIdAndActive(String id, String userId, boolean active);

    List<Transaction> findByUserIdAndActive(String userId, boolean active);

    List<Transaction> findByUserIdAndActiveAndDateBetween(String userId, boolean active, LocalDateTime startDate, LocalDateTime endDate);
}
