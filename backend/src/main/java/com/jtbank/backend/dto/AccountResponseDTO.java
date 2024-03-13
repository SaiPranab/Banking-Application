package com.jtbank.backend.dto;

import com.jtbank.backend.model.AccountType;

public record AccountResponseDTO(
        long accountNumber,
        String accountHolderName,
        String contactNumber,
        String aboutCustomer,
        String accountEmail,
        double accountBalance,
        AccountType accountType) {
}
