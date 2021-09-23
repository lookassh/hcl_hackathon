package com.hcl.bankservice.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankNameNotResolvedEvent {
    private String id;
    private String bankCode;
}
