package com.hcl.favourite.service;

import com.hcl.favourite.domain.FavouriteAccount;
import com.hcl.favourite.repository.FavouriteAccountRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FavouriteAccountListService {

    @Autowired
    private FavouriteAccountRespository favouriteAccountRespository;

    public Page<FavouriteAccount> viewFavorites(Long userId, Pageable pageable) {
        return  favouriteAccountRespository.findAllByUserId(userId, pageable);
    }
}
