package com.jtbank.backend.service;

import com.jtbank.backend.model.Account;

import java.util.List;

public interface IAccountService {
    Account createAccount(Account account);
    Account updateAccount(long accountNumber, Account account);
    Account deleteAccount(long accountNumber);
    Account getAccount(long accountNumber);
    Account getAccountBySlNo(int slNo);
    List<Account> getAccounts();
    Account getAccountByEmailAndPassword(String email, String password);
    Account getAccountByEmail(String email);
    void depositBalance(long accountNumber, double balance);
    void withdrawBalance(long accountNumber, double balance);
    void transfer(long senderAccountNumber, long receiverAccountNumber, double balance);
}
