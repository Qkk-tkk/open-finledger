package com.openfinledger.openfinledger.controller;

import com.openfinledger.openfinledger.model.Account;
import com.openfinledger.openfinledger.service.AccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account createAccount(
            @Valid @RequestBody CreateAccountRequest request
    ) {
        return accountService.create(
                request.ownerName(),
                request.currency()
        );
    }

    @GetMapping
    public List<Account> getAccounts() {
        return accountService.getAll();
    }

    @GetMapping("/{id}")
    public Account getAccount(@PathVariable UUID id) {
        Account account = accountService.get(id);

        if (account == null) {
            throw new org.springframework.web.server.ResponseStatusException(
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