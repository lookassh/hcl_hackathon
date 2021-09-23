package com.hcl.favourite.service;

import com.hcl.favourite.repository.FavouriteAccountRespository;
import org.springframework.beans.factory.annotation.Autowired;

public class FavouriteAccountDeleteService {

    @Autowired
    private FavouriteAccountRespository favouriteAccountRespository;

    public void deleteById(Long favId) {
        favouriteAccountRespository.deleteById(favId);
    }
}
