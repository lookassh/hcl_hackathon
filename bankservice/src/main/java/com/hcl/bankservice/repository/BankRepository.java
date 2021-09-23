package com.hcl.bankservice.repository;

import com.hcl.bankservice.entity.BankEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<BankEntity, String> {
}