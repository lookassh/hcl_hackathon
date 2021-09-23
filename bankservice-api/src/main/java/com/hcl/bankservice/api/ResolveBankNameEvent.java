package com.hcl.bankservice.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Value;

@Value
public class ResolveBankNameEvent {
    String id;
    String bankCode;

    @JsonCreator
    public ResolveBankNameEvent(String id, String bankCode) {
        this.id = id;
        this.bankCode = bankCode;
    }

}
