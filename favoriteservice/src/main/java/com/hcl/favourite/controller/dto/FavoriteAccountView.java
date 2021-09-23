package com.hcl.favourite.controller.dto;

/**
 * Details of favorite account
 */
public class FavoriteAccountView extends FavoriteAccountCreate {

    private Long id;

    private String bankName;

    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
