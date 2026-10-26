package com.openfinledger.openfinledger.service;

import com.openfinledger.openfinledger.model.Account;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AccountService {

    private final Map<UUID, Account> accounts = new ConcurrentHashMap<>();

    public Account create(String ownerName, String currency) {
        UUID id = UUID.randomUUID();

        Account account = new Account(
                id,
                ownerName,
                BigDecimal.ZERO,
                currency
        );

        accounts.put(id, account);
        return account;
    }

    public Account get(UUID id) {
        return accounts.get(id);
    }

    public List<Account> getAll() {
        return accounts.values().stream().toList();
    }
}