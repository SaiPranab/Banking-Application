package com.jtbank.backend.service.impl;

import com.jtbank.backend.model.Account;
import com.jtbank.backend.repository.AccountRepository;
import com.jtbank.backend.service.IAccountService;
import com.jtbank.backend.utility.GenerateAccountNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements IAccountService {
    private final AccountRepository accountRepository;

    @Override
    public Account createAccount(Account account) {
        // this line generates a random number
        var accountNumber = GenerateAccountNumber.generateNumber();
        account.setAccountNumber(accountNumber);
        return accountRepository.save(account);
    }

    @Override
    public Account updateAccount(long accountNumber, Account account) {
        var exisitingAccount = getAccount(accountNumber);

        exisitingAccount.setAccountType(account.getAccountType());
        exisitingAccount.setCredentials(account.getCredentials());
        exisitingAccount.setAccountHolderName(account.getAccountHolderName());
        exisitingAccount.setContactNumber(account.getContactNumber());
        exisitingAccount.setAboutCustomer(account.getAboutCustomer());

        accountRepository.save(exisitingAccount);
        return exisitingAccount;
    }

    @Override
    public Account deleteAccount(long accountNumber) {
        var account = getAccount(accountNumber);
        accountRepository.delete(account);
        return account;
    }

    @Override
    public Account getAccount(long accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber).orElseThrow();
    }

    @Override
    public Account getAccountBySlNo(int slNo) {
        return accountRepository.findById(slNo).orElseThrow(() -> new RuntimeException("Sl not found " + slNo));
    }

    @Override
    public Account getAccountByEmailAndPassword(String email, String password) {
        return accountRepository.findByCredentialsAccountEmailAndCredentialsAccountPassword(email, password)
                .orElseThrow(() -> new RuntimeException("Account Not Found"));
    }

    @Override
    public Account getAccountByEmail(String email) {
        return accountRepository.findByEmail(email).orElseThrow();
    }

    @Override
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public void depositBalance(long accountNumber, double balance) {
        accountRepository.addBalance(accountNumber, balance);
    }

    public void withdrawBalance(long accountNumber, double balance) {
        accountRepository.debitBalance(accountNumber, balance);
    }

    @Transactional
    @Override
    public void transfer(long senderAccountNumber, long receiverAccountNumber, double balance) {
        var receiverAccount = accountRepository.existsByAccountNumber(receiverAccountNumber);
        if (!receiverAccount) {
            throw new RuntimeException("Receiver account not found");
        }

        var senderAccount = getAccount(senderAccountNumber);
        var senderBalace = senderAccount.getAccountBalance();

        if (senderBalace < balance) {
            throw new RuntimeException("Insufficient funds!!!");
        }

        accountRepository.addBalance(receiverAccountNumber, balance);
        accountRepository.debitBalance(senderAccountNumber, balance);
    }
}
