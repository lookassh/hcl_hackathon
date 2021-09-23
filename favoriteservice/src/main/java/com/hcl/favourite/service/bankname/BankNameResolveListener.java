package com.hcl.favourite.service.bankname;

import com.hcl.bankservice.api.BankNameNotResolvedEvent;
import com.hcl.bankservice.api.BankNameResolvedEvent;
import com.hcl.bankservice.api.BankServiceConstraints;
import com.hcl.favourite.domain.FavouriteAccount;
import com.hcl.favourite.repository.FavouriteAccountRespository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@Component
class BankNameResolveListener {

    private final FavouriteAccountRespository favouriteAccountRespository;

    @KafkaListener(topics = BankServiceConstraints.BANK_NAME_RESOLVED_TOPIC)
    public void onBankNameResolved(BankNameResolvedEvent event) {
        log.info("Received: {}", event);
        Optional<FavouriteAccount> favouriteAccountOptional = favouriteAccountRespository.findById(Long.parseLong(event.getId()));
        if (favouriteAccountOptional.isPresent()) {
            FavouriteAccount account = favouriteAccountOptional.get();
            account.setBankName(event.getBankName());
            account.setStatus(FavouriteAccount.Status.VALID);
            favouriteAccountRespository.save(account);
        } else {
            log.info("Invalid favourite account id: {}", event.getId());
        }
    }

    @KafkaListener(topics = BankServiceConstraints.BANK_NAME_NOT_FOUND_TOPIC)
    public void onBankNameNotFound(BankNameNotResolvedEvent event) {
        log.info("Received: {}", event);
        Optional<FavouriteAccount> favouriteAccountOptional = favouriteAccountRespository.findById(Long.parseLong(event.getId()));
        if (favouriteAccountOptional.isPresent()) {
            FavouriteAccount account = favouriteAccountOptional.get();
            account.setStatus(FavouriteAccount.Status.INVALID);
            favouriteAccountRespository.save(account);
        } else {
            log.info("Invalid favourite account id: {}", event.getId());
        }
    }

}
