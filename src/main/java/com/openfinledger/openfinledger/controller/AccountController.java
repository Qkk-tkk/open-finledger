package com.openfinledger.openfinledger.controller;

import com.openfinledger.openfinledger.model.Account;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final Map<UUID, Account> accounts = new ConcurrentHashMap<>();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account createAccount(@RequestBody CreateAccountRequest request) {
        UUID id = UUID.randomUUID();

        Account account = new Account(
                id,
                request.ownerName(),
                BigDecimal.ZERO,
                request.currency()
        );

        accounts.put(id, account);
        return account;
    }

    @GetMapping("/{id}")
    public Account getAccount(@PathVariable UUID id) {
        Account account = accounts.get(id);

        if (account == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Account not found"
            );
        }

        return account;
    }

    public record CreateAccountRequest(
            String ownerName,
            String currency
    ) {
    }
}