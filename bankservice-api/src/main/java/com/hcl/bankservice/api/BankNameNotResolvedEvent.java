package com.hcl.bankservice.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Value
public class BankNameNotResolvedEvent {
    private String id;
    private String bankCode;

//    @JsonCreator
//    public BankNameNotResolvedEvent(String id, String bankCode) {
//        this.id = id;
//        this.bankCode = bankCode;
//    }

}
