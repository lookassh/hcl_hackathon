package com.hcl.bankservice.service;

import com.hcl.bankservice.api.BankNameNotResolvedEvent;
import com.hcl.bankservice.api.BankNameResolvedEvent;
import com.hcl.bankservice.api.BankServiceConstraints;
import com.hcl.bankservice.api.ResolveBankNameEvent;
import com.hcl.bankservice.entity.BankEntity;
import com.hcl.bankservice.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ResolveBankNameListener {

    private final BankRepository bankRepository;
    private final KafkaTemplate<Object, Object> kafkaTemplate;

    @KafkaListener(topics = BankServiceConstraints.RESOLVE_BANK_NAME_TOPIC)
    public void onRegisterEvent(ResolveBankNameEvent resolveBankNameEvent) {
        String transactionId = resolveBankNameEvent.getId();
        log.debug("Received: {}", resolveBankNameEvent);
        Optional<BankEntity> opBankEntity =bankRepository.findById(resolveBankNameEvent.getBankCode());
        if(opBankEntity.isPresent()){
            BankEntity bankEntity = opBankEntity.get();
            kafkaTemplate.send(BankServiceConstraints.BANK_NAME_RESOLVED_TOPIC,
                    new BankNameResolvedEvent(transactionId, bankEntity.getCode(), bankEntity.getName()));
        }else {
            kafkaTemplate.send(BankServiceConstraints.BANK_NAME_NOT_FOUND_TOPIC,
                    new BankNameNotResolvedEvent(transactionId, resolveBankNameEvent.getBankCode()));
        }
    }

}
