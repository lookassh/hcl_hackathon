package com.hcl.favourite.service.bankname;

import com.hcl.bankservice.api.BankNameNotResolvedEvent;
import com.hcl.bankservice.api.BankNameResolvedEvent;
import com.hcl.bankservice.api.BankServiceConstraints;
import com.hcl.bankservice.api.ResolveBankNameEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Slf4j
@RequiredArgsConstructor
@Component
class BankNameResolveListener {

    @KafkaListener(topics = BankServiceConstraints.BANK_NAME_RESOLVED_TOPIC)
    public void onBankNameResolved(BankNameResolvedEvent event) {
        log.info("Received: {}", event);
    }

    @KafkaListener(topics = BankServiceConstraints.BANK_NAME_NOT_FOUND_TOPIC)
    public void onBankNameNotFound(BankNameNotResolvedEvent event) {
        log.info("Received: {}", event);
    }

}
