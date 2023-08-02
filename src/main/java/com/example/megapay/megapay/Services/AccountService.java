package com.example.megapay.megapay.Services;

import org.springframework.stereotype.Service;

import com.example.megapay.megapay.Models.Account;
import com.example.megapay.megapay.Repository.AccountRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account not found with id: " + accountId));
    }

    public Account updateAccount(Account account) {
        return accountRepository.save(account);
    }
}