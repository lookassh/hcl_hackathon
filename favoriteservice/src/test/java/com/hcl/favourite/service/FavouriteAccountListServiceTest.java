package com.hcl.employeeservice.service.impl;

import com.hcl.favourite.domain.FavouriteAccount;
import com.hcl.favourite.repository.FavouriteAccountRespository;
import com.hcl.favourite.service.FavouriteAccountListService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class FavouriteAccountListServiceTest {

    @TestConfiguration
    static class FavouriteAccountListServiceTestContextConfiguration {

        @Bean
        public MethodValidationPostProcessor methodValidationPostProcessor() {
            return new MethodValidationPostProcessor();
        }

        @Bean
        public FavouriteAccountListService favouriteAccountListService() {
            return new FavouriteAccountListService();
        }
    }

    @MockBean
    private FavouriteAccountRespository favouriteAccountRespository;

    @Autowired
    private FavouriteAccountListService favouriteAccountListService;

    @Test
    public void testViewFavouriteAccounts() {
        FavouriteAccount favAccount = new FavouriteAccount();
        favAccount.setId(1L);
        favAccount.setIban("ESP1123400001234");
        favAccount.setFavName("My account");
        favAccount.setStatus(FavouriteAccount.Status.VALIDATED);
        favAccount.setBankName("BankOfSpain");

        PageImpl<FavouriteAccount> page = new PageImpl<>(Arrays.asList(favAccount));
        Mockito.doReturn(page).when(favouriteAccountRespository).findAllByUserId(Mockito.anyLong(), Mockito.any());

        Page<FavouriteAccount> responsePage = favouriteAccountListService.viewFavorites(1l, null);
        Assertions.assertEquals(1, responsePage.getTotalElements());
        FavouriteAccount response = page.getContent().get(0);
        Assertions.assertEquals(1L, response.getId());
        Assertions.assertEquals("My account", response.getFavName());
        Assertions.assertEquals(FavouriteAccount.Status.INVALID, response.getStatus());
    }
}
