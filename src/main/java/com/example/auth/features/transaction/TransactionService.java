package com.example.auth.features.transaction;

import com.example.auth.features.transaction.dtos.CreateTransactionDTO;
import com.example.auth.features.transaction.dtos.UpdateTransactionDTO;
import com.example.auth.features.transaction.entities.Transaction;
import com.example.auth.features.user.entities.User;
import com.example.auth.utils.AppUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> list(User user, Map<String,String> filters){
        if(filters.containsKey("year")){
            DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-M-dd");
            var year = filters.get("year");
            var startDate = LocalDate.from(pattern.parse(year +"-01-01")).atTime(0,0);
            var endDate = startDate.plusYears(1).minusDays(1).withHour(23).withMinute(59);
            if(filters.containsKey("month")){
                var month = filters.get("month");
                startDate = LocalDate.from(pattern.parse(year+"-"+month+"-01")).atTime(0,0);
                endDate = startDate.plusMonths(1).minusDays(1).withHour(23).withMinute(59);
            }
            return transactionRepository.findByUserIdAndActiveAndDateBetween(user.getId(), true, startDate, endDate);
        }
        return transactionRepository.findByUserIdAndActive(user.getId(), true);
    }

    public Transaction create(CreateTransactionDTO data, User user){
        Transaction newTransaction = data.toTransaction();
        newTransaction.setUser(user);
        return transactionRepository.save(newTransaction);
    }

    public Optional<Transaction> read(String id, User user){
        return transactionRepository.findByIdAndUserIdAndActive(id, user.getId(), true);
    }

    public Optional<Transaction> update(String id, UpdateTransactionDTO data, User user){
        var transaction = transactionRepository.findByIdAndUserIdAndActive(id, user.getId(), true);
        if(transaction.isPresent()){
            AppUtils.copyNonNullProperties(data.toTransaction(), transaction.get());
            transaction = Optional.of(transactionRepository.save(transaction.get()));
        }
        return transaction;
    }

    public Optional<Transaction> delete(String id, User user){
        var transaction = transactionRepository.findByIdAndUserIdAndActive(id, user.getId(), true);
        if(transaction.isPresent()){
            var value =  transaction.get();
            value.setActive(false);
            transaction = Optional.of(transactionRepository.save(value));
        }
        return transaction;
    }
}
