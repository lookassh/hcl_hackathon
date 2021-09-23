package com.hcl.favourite.controller;

import com.hcl.favourite.controller.dto.FavoriteAccountCreate;
import com.hcl.favourite.controller.dto.FavoriteAccountView;
import com.hcl.favourite.domain.FavouriteAccount;
import com.hcl.favourite.service.FavouriteAccountCreateService;
import com.hcl.favourite.service.FavouriteAccountListService;
import com.hcl.favourite.service.FavouriteAccountDeleteService;
import com.hcl.favourite.service.FavouriteAccountUpdateService;
import com.hcl.favourite.service.UserService;
import com.hcl.favourite.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/favourites")
public class FavouriteAccountController {

    @Autowired
    private FavouriteAccountListService favouriteAccountListService;


    @Autowired
    private FavouriteAccountCreateService favouriteAccountCreateService;

    @Autowired
    private UserService userService;

    @Autowired
    private FavouriteAccountDeleteService favouriteAccountDeleteService;

    @Autowired
    private FavouriteAccountUpdateService favouriteAccountUpdateService;


    @GetMapping()
    public ResponseEntity<Page<FavouriteAccount>> getFavorites(@RequestHeader("User-Id") Long userId,
                                                               @PageableDefault(value=5) Pageable pageable) {
        if (!userService.isValidUser(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Page<FavouriteAccount> page = favouriteAccountListService.viewFavorites(userId, pageable);
        return new ResponseEntity(page, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public FavoriteAccountView create(@RequestBody FavoriteAccountCreate favoriteAccountCreate, @RequestHeader("User-Id") Long userId) throws FavouriteAccountCreateService.TooManyFavouriteAccountsBusinessException {
        if (!userService.isValidUser(userId)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        ModelMapper mapper = new ModelMapper();
        FavouriteAccount favouriteAccount = mapper.map(favoriteAccountCreate, FavouriteAccount.class);
        favouriteAccount.setUserId(userId);
        return mapper.map(favouriteAccountCreateService.create(favouriteAccount), FavoriteAccountView.class);
    }

    @DeleteMapping(path = "/{favId}")
    public void delete(@PathVariable Long favId) {
        favouriteAccountDeleteService.deleteById(favId);
    }

    @PutMapping(path = "/{favId}",
            consumes=MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE)
    public FavoriteAccountView updateFavouriteAccount(@PathVariable Long favId,
                                                   @RequestBody FavoriteAccountCreate favoriteAccountCreate){
        ModelMapper mapper = new ModelMapper();
        FavouriteAccount favouriteAccount = mapper.map(favoriteAccountCreate, FavouriteAccount.class);
        favouriteAccount.setId(favId);
        return mapper.map(favouriteAccountUpdateService.updateFavouriteAccount(favouriteAccount), FavoriteAccountView.class);
    }

    @ResponseStatus(value= HttpStatus.UNAUTHORIZED,
            reason="Missing User-Id header")
    @ExceptionHandler(MissingRequestHeaderException.class)
    public void handleMissingHeader(MissingRequestHeaderException ex) {

    }


    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, String> handleConstraintValidationException(ConstraintViolationException ex) {
        return ex.getConstraintViolations().stream().collect(Collectors.toMap(c -> c.getPropertyPath().toString(), c->c.getMessage()));
    }

}
