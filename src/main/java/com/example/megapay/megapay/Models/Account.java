package com.example.megapay.megapay.Models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    private Long customerId;
    private BigDecimal accountLimit;
    private BigDecimal perTransactionLimit;
    private BigDecimal lastAccountLimit;
    private BigDecimal lastPerTransactionLimit;
    private LocalDateTime accountLimitUpdateTime;
    private LocalDateTime perTransactionLimitUpdateTime;
    public Account(Long accountId, Long customerId, BigDecimal accountLimit, BigDecimal perTransactionLimit,
            BigDecimal lastAccountLimit, BigDecimal lastPerTransactionLimit, LocalDateTime accountLimitUpdateTime,
            LocalDateTime perTransactionLimitUpdateTime) {
        this.accountId = accountId;
        this.customerId = customerId;
        this.accountLimit = accountLimit;
        this.perTransactionLimit = perTransactionLimit;
        this.lastAccountLimit = lastAccountLimit;
        this.lastPerTransactionLimit = lastPerTransactionLimit;
        this.accountLimitUpdateTime = accountLimitUpdateTime;
        this.perTransactionLimitUpdateTime = perTransactionLimitUpdateTime;
    }
    public Account() {
    }
    public Long getAccountId() {
        return accountId;
    }
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public BigDecimal getAccountLimit() {
        return accountLimit;
    }
    public void setAccountLimit(BigDecimal accountLimit) {
        this.accountLimit = accountLimit;
    }
    public BigDecimal getPerTransactionLimit() {
        return perTransactionLimit;
    }
    public void setPerTransactionLimit(BigDecimal perTransactionLimit) {
        this.perTransactionLimit = perTransactionLimit;
    }
    public BigDecimal getLastAccountLimit() {
        return lastAccountLimit;
    }
    public void setLastAccountLimit(BigDecimal lastAccountLimit) {
        this.lastAccountLimit = lastAccountLimit;
    }
    public BigDecimal getLastPerTransactionLimit() {
        return lastPerTransactionLimit;
    }
    public void setLastPerTransactionLimit(BigDecimal lastPerTransactionLimit) {
        this.lastPerTransactionLimit = lastPerTransactionLimit;
    }
    public LocalDateTime getAccountLimitUpdateTime() {
        return accountLimitUpdateTime;
    }
    public void setAccountLimitUpdateTime(LocalDateTime accountLimitUpdateTime) {
        this.accountLimitUpdateTime = accountLimitUpdateTime;
    }
    public LocalDateTime getPerTransactionLimitUpdateTime() {
        return perTransactionLimitUpdateTime;
    }
    public void setPerTransactionLimitUpdateTime(LocalDateTime perTransactionLimitUpdateTime) {
        this.perTransactionLimitUpdateTime = perTransactionLimitUpdateTime;
    }

    // Constructors, getters, setters, and other methods
}