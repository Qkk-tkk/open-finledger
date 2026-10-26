package com.openfinledger.openfinledger.model;

import java.math.BigDecimal;
import java.util.UUID;

public record Account(
        UUID id,
        String ownerName,
        BigDecimal balance,
        String currency
) {
}