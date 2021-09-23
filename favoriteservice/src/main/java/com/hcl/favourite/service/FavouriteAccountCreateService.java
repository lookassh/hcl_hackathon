package com.hcl.favourite.service;


import com.hcl.favourite.domain.FavouriteAccount;
import com.hcl.favourite.repository.FavouriteAccountRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * Service for favourite account creation
 */
@Service
@Validated
//TODO: we can split implementation from api (interface)
public class FavouriteAccountCreateService {

    @Autowired
    private FavouriteAccountRespository favouriteAccountRespository;

    /**
     * Creates favourite account
     * @param account account to be created
     * @return created account
     */
    public FavouriteAccount create(@Valid FavouriteAccount account) throws TooManyFavouriteAccountsBusinessException {
        //validate number of accounts
        //TODO: limit for accounts can be read from for example database
        if (favouriteAccountRespository.countByUserId(account.getUserId()) >= 20) {
            throw new TooManyFavouriteAccountsBusinessException();
        }
        //TODO: enumeration instead of string
        account.setStatus(FavouriteAccount.Status.VALIDATION);
        return favouriteAccountRespository.save(account);
    }

    public static class TooManyFavouriteAccountsBusinessException extends Exception {

    }
}
