package com.example.megapay.megapay.Services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.megapay.megapay.Models.Account;
import com.example.megapay.megapay.Models.LimitOffer;
import com.example.megapay.megapay.Models.LimitType;
import com.example.megapay.megapay.Models.OfferStatus;
import com.example.megapay.megapay.Repository.LimitOfferRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class LimitOfferService {
    private final LimitOfferRepository limitOfferRepository;





    
    public LimitOfferService(LimitOfferRepository limitOfferRepository) {
        this.limitOfferRepository = limitOfferRepository;
    }
 
    public LimitOffer createLimitOffer(LimitOffer limitOffer) {
        Long accountId = limitOffer.getAccountId();
        LimitType limitType = limitOffer.getLimitType();
        BigDecimal newLimit = limitOffer.getNewLimit();

        // Fetch the current account from the database
        Account account = accountService.getAccountById(accountId);

        // Check if the new limit is greater than the current limit
        if (limitType == LimitType.ACCOUNT_LIMIT && newLimit.compareTo(account.getAccountLimit()) <= 0) {
            throw new IllegalArgumentException("Account limit offer should be greater than the current account limit.");
        } else if (limitType == LimitType.PER_TRANSACTION_LIMIT && newLimit.compareTo(account.getPerTransactionLimit()) <= 0) {
            throw new IllegalArgumentException("Per transaction limit offer should be greater than the current per transaction limit.");
        }

        limitOffer.setStatus(OfferStatus.PENDING);
        return limitOfferRepository.save(limitOffer);
    }

 @Autowired   
AccountService accountService;
    public List<LimitOffer> getActiveLimitOffers(Long accountId, LocalDateTime activeDate) {
        return limitOfferRepository.findByAccountIdAndOfferActivationTimeBeforeAndOfferExpiryTimeAfterAndStatus(accountId, activeDate, activeDate, OfferStatus.PENDING);
    }

    public void updateLimitOfferStatus(Long limitOfferId, OfferStatus status) {
        LimitOffer limitOffer = limitOfferRepository.findById(limitOfferId)
                .orElseThrow(() -> new EntityNotFoundException("Limit offer not found with id: " + limitOfferId));

        limitOffer.setStatus(status);
        limitOfferRepository.save(limitOffer);

        // If the offer is accepted, update the account limits and timestamps
        if (status == OfferStatus.ACCEPTED) {
            Account account = accountService.getAccountById(limitOffer.getAccountId());
            LimitType limitType = limitOffer.getLimitType();
            BigDecimal newLimit = limitOffer.getNewLimit();

            if (limitType == LimitType.ACCOUNT_LIMIT) {
                account.setLastAccountLimit(account.getAccountLimit());
                account.setAccountLimit(newLimit);
                account.setAccountLimitUpdateTime(LocalDateTime.now());
            } else if (limitType == LimitType.PER_TRANSACTION_LIMIT) {
                account.setLastPerTransactionLimit(account.getPerTransactionLimit());
                account.setPerTransactionLimit(newLimit);
                account.setPerTransactionLimitUpdateTime(LocalDateTime.now());
            }

            accountService.updateAccount(account);
        }
    }
    public List<LimitOffer> getActiveLimitOffersForAccount(Long accountId, LocalDateTime activeDate) {
        return limitOfferRepository.findByAccountIdAndOfferActivationTimeBeforeAndOfferExpiryTimeAfterAndStatus(accountId, activeDate, activeDate, OfferStatus.PENDING);
    }

}
