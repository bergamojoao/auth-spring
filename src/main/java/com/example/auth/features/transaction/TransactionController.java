package com.example.auth.features.transaction;

import com.example.auth.features.transaction.dtos.CreateTransactionDTO;
import com.example.auth.features.transaction.dtos.UpdateTransactionDTO;
import com.example.auth.features.user.entities.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public ResponseEntity list(@RequestParam Map<String, String> params, Authentication authentication){
        var user = (User) authentication.getPrincipal();
        var transaction = transactionService.list(user, params);
        return ResponseEntity.ok(transaction);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid CreateTransactionDTO data, Authentication authentication){
        var user = (User) authentication.getPrincipal();
        var result = transactionService.create(data, user);
        return ResponseEntity.ok(result);
    }

    @GetMapping("{id}")
    public ResponseEntity read(@PathVariable("id") String id, Authentication authentication){
        var user = (User) authentication.getPrincipal();
        var transaction = transactionService.read(id, user);
        if(transaction.isPresent()){
            return ResponseEntity.ok(transaction.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") String id, @RequestBody @Valid UpdateTransactionDTO data, Authentication authentication){
        var user = (User) authentication.getPrincipal();
        var transaction = transactionService.update(id, data, user);
        if(transaction.isPresent()){
            return ResponseEntity.ok(transaction.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") String id, Authentication authentication){
        var user = (User) authentication.getPrincipal();
        var transaction = transactionService.delete(id, user);
        if(transaction.isPresent()){
            return ResponseEntity.ok(transaction.get());
        }
        return ResponseEntity.notFound().build();
    }
}
