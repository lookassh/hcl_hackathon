package com.hcl.favourite.service;

import org.springframework.stereotype.Component;

@Component
public class IbanToBankCodeImpl implements IbanToBankCode{
    @Override
    public String resolve(String iban) {
        return iban.substring(4, 8);
    }
}
