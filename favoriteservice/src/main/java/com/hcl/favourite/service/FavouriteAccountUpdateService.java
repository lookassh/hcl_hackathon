package com.hcl.favourite.service;

import com.hcl.favourite.domain.FavouriteAccount;
import com.hcl.favourite.repository.FavouriteAccountRespository;
import com.hcl.favourite.repository.UserRepository;
import com.hcl.favourite.service.bankname.ResolveBankNameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FavouriteAccountUpdateService {

    @Autowired
    private FavouriteAccountRespository favouriteAccountRespository;
    @Autowired
    private ResolveBankNameService resolveBankNameService;
    @Autowired
    private IbanToBankCode ibanToBankCode;


    public FavouriteAccount updateFavouriteAccount(FavouriteAccount favouriteAccount){
        FavouriteAccount returnFavouriteAccount = favouriteAccountRespository.save(favouriteAccount);

        try{
            String iBan = favouriteAccount.getIban();
            String bankCode = ibanToBankCode.resolve(iBan);
            resolveBankNameService.submitResolveBankNameEvent(iBan, bankCode);

        }catch(Exception e){
            log.error("Iban not found", e);
        }
        return returnFavouriteAccount;
    }

}
