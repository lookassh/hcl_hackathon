package com.hcl.bankservice.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

//@Value
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BankNameResolvedEvent {
    private String id;
    private String bankCode;
    private String bankName;

//    @JsonCreator
//    public BankNameResolvedEvent(String id, String bankCode, String bankName) {
//        this.id = id;
//        this.bankCode = bankCode;
//        this.bankName = bankName;
//    }

}
