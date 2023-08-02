package com.example.megapay.megapay.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.megapay.megapay.Models.LimitOffer;
import com.example.megapay.megapay.Models.OfferStatus;
import com.example.megapay.megapay.Repository.LimitOfferRepository;
import com.example.megapay.megapay.Services.LimitOfferService;

@RestController
public class LimitOfferController {
    private final LimitOfferService limitOfferService;

    public LimitOfferController(LimitOfferService limitOfferService) {
        this.limitOfferService = limitOfferService;
    }

    @PostMapping("/limit-offers")
    public ResponseEntity<LimitOffer> createLimitOffer(@RequestBody LimitOffer limitOffer) {
        LimitOffer savedLimitOffer = limitOfferService.createLimitOffer(limitOffer);
        return ResponseEntity.ok(savedLimitOffer);
    }

    @GetMapping("/limit-offers/{accountId}")
    public ResponseEntity<List<LimitOffer>> getActiveLimitOffers(@PathVariable Long accountId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime activeDate) {
        List<LimitOffer> activeLimitOffers = limitOfferService.getActiveLimitOffers(accountId,
                activeDate != null ? activeDate : LocalDateTime.now());
        return ResponseEntity.ok(activeLimitOffers);
    }

    @GetMapping("/limit-offers")
    public ResponseEntity<List<LimitOffer>> getActiveLimitOffersForAccountAndDate(@RequestParam Long accountId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime activeDate) {
        List<LimitOffer> activeLimitOffers = limitOfferService.getActiveLimitOffersForAccount(accountId,
                activeDate != null ? activeDate : LocalDateTime.now());
        return ResponseEntity.ok(activeLimitOffers);
    }

    @Autowired
    LimitOfferRepository limitOfferRepository;

    @PutMapping("/limit-offers/{limitOfferId}")
    public ResponseEntity<String> updateLimitOfferStatus(@PathVariable Long limitOfferId,
            @RequestParam("status") OfferStatus status) {
        Optional<LimitOffer> opt = limitOfferRepository.findById(limitOfferId);
        if (opt.isEmpty())
            return new ResponseEntity<>("Offer Not Found", HttpStatus.NOT_FOUND);

        if (status.equals(OfferStatus.ACCEPTED))
            limitOfferService.updateLimitOfferStatus(limitOfferId, OfferStatus.ACCEPTED);
        else if (status.equals(OfferStatus.PENDING))
            limitOfferService.updateLimitOfferStatus(limitOfferId, OfferStatus.PENDING);
        else if (status.equals(OfferStatus.REJECTED))
            limitOfferService.updateLimitOfferStatus(limitOfferId, OfferStatus.REJECTED);
        else
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }
}
