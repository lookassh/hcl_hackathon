package com.hcl.favourite.controller.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * Favorite account create
 */
public class FavoriteAccountCreate {

    @NotBlank
    private String favName;

    @Pattern(regexp = "^[A-Za-z0-9]{1,20}$", message = "IBAN must consist of up to 20 alphanumeric characters")
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
