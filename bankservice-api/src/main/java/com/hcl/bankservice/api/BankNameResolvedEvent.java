package com.hcl.bankservice.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Value;

@Value
public class BankNameResolvedEvent {
    String id;
    String bankCode;
    String bankName;

    @JsonCreator
    public BankNameResolvedEvent(String id, String bankCode, String bankName) {
        this.id = id;
        this.bankCode = bankCode;
        this.bankName = bankName;
    }

}
