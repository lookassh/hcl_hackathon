package com.hcl.favourite.service;

import com.hcl.favourite.domain.FavouriteAccount;
import com.hcl.favourite.repository.FavouriteAccountRespository;
import com.hcl.favourite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavouriteAccountUpdateService {

    @Autowired
    private FavouriteAccountRespository favouriteAccountRespository;


    public FavouriteAccount updateFavouriteAccount(FavouriteAccount favouriteAccount){
        return favouriteAccountRespository.save(favouriteAccount);
    }

}
