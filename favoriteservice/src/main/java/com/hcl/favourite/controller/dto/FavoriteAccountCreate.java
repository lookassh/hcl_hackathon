package com.hcl.favourite.controller.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

/**
 * Favorite account create
 */
public class FavoriteAccountCreate {

    @NotBlank
    private String favName;

    @NotBlank @Max(20)
    private String iban;

    public String getFavName() {
        return favName;
    }

    public void setFavName(String favName) {
        this.favName = favName;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
}
