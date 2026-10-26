package com.openfinledger.openfinledger.controller;

import com.openfinledger.openfinledger.model.Account;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Map;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final Map<UUID, Account> accounts = new ConcurrentHashMap<>();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account createAccount(
            @Valid @RequestBody CreateAccountRequest request
    ) {
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

    @GetMapping
    public List<Account> getAccounts() {
        return accounts.values().stream().toList();
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
            @NotBlank(message = "ownerName must not be blank")
            String ownerName,

            @NotBlank(message = "currency must not be blank")
            @Pattern(
                    regexp = "^[A-Z]{3}$",
                    message = "currency must be a 3-letter uppercase code"
            )
            String currency
    ) {
    }
}