package com.hcl.favourite.repository;

import com.hcl.favourite.domain.FavouriteAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouriteAccountRespository extends PagingAndSortingRepository<FavouriteAccount, Long> {

    Page<FavouriteAccount> findAllByUserId(long userId, Pageable pageable);
}
