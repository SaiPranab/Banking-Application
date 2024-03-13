package com.jtbank.backend.controller;

import com.jtbank.backend.dto.AccountRequestDTO;
import com.jtbank.backend.dto.AccountResponseDTO;
import com.jtbank.backend.mapper.AccountMapper;
import com.jtbank.backend.model.Account;
import com.jtbank.backend.model.helper.Credentials;
import com.jtbank.backend.service.IAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final IAccountService accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponseDTO createAccount(@Valid @RequestBody AccountRequestDTO accountRequestDTO) {
        var account = AccountMapper.modelMapper(accountRequestDTO);
        var result = accountService.createAccount(account);
        return AccountMapper.dtoMapper(result);
    }

    @GetMapping
    public List<AccountResponseDTO> getAccounts() {
        var accounts = accountService.getAccounts();

//        var list = new ArrayList<AccountResponseDTO>();
//        for(Account account : accounts){
//            var accountResponseDTO = AccountMapper.dtoMapper(account);
//            list.add(accountResponseDTO);
//        }
//        var list = accounts.stream().map(account -> AccountMapper.dtoMapper(account)).toList();

        var list = accounts.stream().map(AccountMapper::dtoMapper).toList();

        return list;
    }

    @GetMapping("/{slNo}")
    public AccountResponseDTO AccountBySlNo(@PathVariable int slNo) {
        var result = accountService.getAccountBySlNo(slNo);
        return AccountMapper.dtoMapper(result);
    }

    @GetMapping("/number/{accountNumber}")
    public AccountResponseDTO AccountByAccountNumber(long accountNumber) {
        var result = accountService.getAccount(accountNumber);
        return AccountMapper.dtoMapper(result);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AccountResponseDTO AccountByEmailAndPassword(@RequestBody Credentials credentials) {
        var email = credentials.getAccountEmail();
        var password = credentials.getAccountPassword();

        var result = accountService.getAccountByEmailAndPassword(email, password);
        return AccountMapper.dtoMapper(result);
    }

    @GetMapping("/email/{email}")
    public AccountResponseDTO accountByEmail(@PathVariable String email) {
        var result = accountService.getAccountByEmail(email);
        return AccountMapper.dtoMapper(result);
    }

    @DeleteMapping("/{accountNumber}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AccountResponseDTO deleteByAccountNumber(@PathVariable long accountNumber) {
        var result = accountService.deleteAccount(accountNumber);
        return AccountMapper.dtoMapper(result);
    }

    @PutMapping("/{accountNumber}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AccountResponseDTO updateByAccountNumber(@PathVariable long accountNumber, @RequestBody AccountRequestDTO accountRequestDTO){
        var account = AccountMapper.modelMapper(accountRequestDTO);
        var result = accountService.updateAccount(accountNumber, account);
        return AccountMapper.dtoMapper(result);
    }

    @PatchMapping("/{balance}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deposit(@RequestHeader long accountNumber, @PathVariable double balance){
        accountService.depositBalance(accountNumber, balance);
    }

    @PatchMapping("/withdraw/{balance}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void withdraw(@RequestHeader long accountNumber, @PathVariable double balance){
        accountService.withdrawBalance(accountNumber, balance);
    }


    @PatchMapping("/number/{receiver}/balance/{balance}")
    public void transfer(@RequestHeader long sender, @PathVariable long receiver, @PathVariable double balance){
        accountService.transfer(sender, receiver, balance);
    }
}
