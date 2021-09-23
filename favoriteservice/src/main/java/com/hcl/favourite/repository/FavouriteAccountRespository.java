package com.hcl.favourite.repository;

import com.hcl.favourite.domain.FavouriteAccount;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouriteAccountRespository extends PagingAndSortingRepository<FavouriteAccount, Long> {
}
