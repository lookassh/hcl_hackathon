package com.hcl.bankservice.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

//@Value
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResolveBankNameEvent {
    private String id;
    private String bankCode;

//    @JsonCreator
//    public ResolveBankNameEvent(String id, String bankCode) {
//        this.id = id;
//        this.bankCode = bankCode;
//    }

}
