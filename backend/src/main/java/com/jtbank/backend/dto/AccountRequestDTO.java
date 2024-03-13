package com.jtbank.backend.dto;

import com.jtbank.backend.model.AccountType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AccountRequestDTO(
        @NotNull(message = "Account Holder shouldn't be null")
        @NotEmpty
        String accountHolderName,
        @NotNull(message = "contact number shouldn't be null")
        String contactNumber,
        @NotNull(message = "About customer shouldn't be null")
        String aboutCustomer,
        @NotNull(message = "Account email shouldn't be null")
        String accountEmail,
        @NotNull(message = "Account password shouldn't be null")
        String accountPassword,
        @NotNull(message = "Account type should be savings or current")
        AccountType accountType
) {
}
