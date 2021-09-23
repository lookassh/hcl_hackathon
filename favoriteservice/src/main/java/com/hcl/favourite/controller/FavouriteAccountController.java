package com.hcl.favourite.controller;

import com.hcl.favourite.domain.FavouriteAccount;
import com.hcl.favourite.service.FavouriteAccountListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/favourites")
public class FavouriteAccountController {

    @Autowired
    private FavouriteAccountListService favouriteAccountListService;

    @GetMapping()
    public ResponseEntity<Page<FavouriteAccount>> getFavorites(@RequestHeader("userId") Long userId,
                                                               Pageable pageable) {
        Page<FavouriteAccount> page = favouriteAccountListService.viewFavorites(userId, pageable);
        return new ResponseEntity(page, HttpStatus.OK);
    }

}
