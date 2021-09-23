package com.hcl.favourite.service.bankname;

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
class ResolveBankNameServiceImpl implements ResolveBankNameService{

    private final KafkaTemplate<Object, Object> kafkaTemplate;

    @Override
    public void submitResolveBankNameEvent(String transactionId, String bankCode) {
        var event = new ResolveBankNameEvent(transactionId, bankCode);
        kafkaTemplate.send(BankServiceConstraints.RESOLVE_BANK_NAME_TOPIC, event);
    }

}
