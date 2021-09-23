package com.hcl.bankservice.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BankNameResolvedEvent {
    private String id;
    private String bankCode;
    private String bankName;
}
