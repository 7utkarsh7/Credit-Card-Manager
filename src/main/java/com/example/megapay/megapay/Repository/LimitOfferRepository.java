package com.example.megapay.megapay.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.megapay.megapay.Models.LimitOffer;
import com.example.megapay.megapay.Models.OfferStatus;

@Repository
public interface LimitOfferRepository extends JpaRepository<LimitOffer, Long> {
    List<LimitOffer> findByAccountIdAndOfferActivationTimeBeforeAndOfferExpiryTimeAfterAndStatus(Long accountId, LocalDateTime offerActivationTime, LocalDateTime offerExpiryTime, OfferStatus status);
}
