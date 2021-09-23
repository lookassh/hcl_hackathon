package com.hcl.favourite.service.bankname;

public interface ResolveBankNameService {

    void submitResolveBankNameEvent(String transactionId, String bankCode);

}
