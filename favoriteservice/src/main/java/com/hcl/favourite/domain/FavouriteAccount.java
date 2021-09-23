package com.hcl.favourite.domain;

import javax.persistence.*;

@Entity
@Table(name="fav_account")
public class FavouriteAccount {

    public enum Status {
        VALIDATION,
        VALIDATED,
        INVALID
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="user_id")
    private Long userId;

    @Column(name="fav_name")
    private String favName;

    @Column(name="iban")
    private String iban;

    @Column(name="bank_name")
    private String bankName;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Status getStatus() { return status; }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }
}
