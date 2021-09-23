package com.hcl.bankservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "bank", indexes = {
        @Index(name = "code", columnList = "code", unique = true)
})
@Entity
public class BankEntity{

    @Id
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

}