package com.example.megapay.megapay.Models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class LimitOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long limitOfferId;
    private Long accountId;
    @Enumerated(EnumType.STRING)
    private LimitType limitType;
    private BigDecimal newLimit;
    private LocalDateTime offerActivationTime;
    private LocalDateTime offerExpiryTime;
    @Enumerated(EnumType.STRING)
    private OfferStatus status;
    public LimitOffer() {
    }
    public LimitOffer(Long limitOfferId, Long accountId, LimitType limitType, BigDecimal newLimit,
            LocalDateTime offerActivationTime, LocalDateTime offerExpiryTime, OfferStatus status) {
        this.limitOfferId = limitOfferId;
        this.accountId = accountId;
        this.limitType = limitType;
        this.newLimit = newLimit;
        this.offerActivationTime = offerActivationTime;
        this.offerExpiryTime = offerExpiryTime;
        this.status = status;
    }
    public Long getLimitOfferId() {
        return limitOfferId;
    }
    public void setLimitOfferId(Long limitOfferId) {
        this.limitOfferId = limitOfferId;
    }
    public Long getAccountId() {
        return accountId;
    }
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
    public LimitType getLimitType() {
        return limitType;
    }
    public void setLimitType(LimitType limitType) {
        this.limitType = limitType;
    }
    public BigDecimal getNewLimit() {
        return newLimit;
    }
    public void setNewLimit(BigDecimal newLimit) {
        this.newLimit = newLimit;
    }
    public LocalDateTime getOfferActivationTime() {
        return offerActivationTime;
    }
    public void setOfferActivationTime(LocalDateTime offerActivationTime) {
        this.offerActivationTime = offerActivationTime;
    }
    public LocalDateTime getOfferExpiryTime() {
        return offerExpiryTime;
    }
    public void setOfferExpiryTime(LocalDateTime offerExpiryTime) {
        this.offerExpiryTime = offerExpiryTime;
    }
    public OfferStatus getStatus() {
        return status;
    }
    public void setStatus(OfferStatus status) {
        this.status = status;
    }

    // Constructors, getters, setters, and other methods
}