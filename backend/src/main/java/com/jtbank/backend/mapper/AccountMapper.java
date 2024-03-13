package com.jtbank.backend.mapper;

import com.jtbank.backend.dto.AccountRequestDTO;
import com.jtbank.backend.dto.AccountResponseDTO;
import com.jtbank.backend.model.helper.Credentials;
import com.jtbank.backend.model.Account;
import org.springframework.beans.BeanUtils;

public class AccountMapper {
    private AccountMapper() {
    }

    public static Account modelMapper(AccountRequestDTO dto) {
        var credentials = new Credentials();
//        credentials.setAccountEmail(dto.accountEmail());
//        credentials.setAccountPassword(dto.accountPassword());
        BeanUtils.copyProperties(dto, credentials);

        var account = new Account();
//        account.setAccountHolderName(dto.accountHolderName());
//        account.setAccountType(dto.accountType());
//        account.setAboutCustomer(dto.aboutCustomer());
//        account.setContactNumber(dto.contactNumber());
//        account.setAccountType(dto.accountType());
        BeanUtils.copyProperties(dto, account);
        account.setCredentials(credentials);

        return account;
    }

    public static AccountResponseDTO dtoMapper(Account account) {
        var accountNumber = account.getAccountNumber();
        var accountHolderName = account.getAccountHolderName();
        var accountType = account.getAccountType();
        var aboutCustomer = account.getAboutCustomer();
        var contactNumber = account.getContactNumber();
        var accountBalance = account.getAccountBalance();

        var credentials = account.getCredentials();
        var accountEmail = credentials.getAccountEmail();

        return new AccountResponseDTO(
                accountNumber,
                accountHolderName,
                contactNumber,
                aboutCustomer,
                accountEmail,
                accountBalance,
                accountType);
    }
}
