package com.example.megapay.megapay.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.megapay.megapay.Models.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
