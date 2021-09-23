package com.hcl.favourite.service;

import com.hcl.favourite.repository.FavouriteAccountRespository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@ExtendWith(SpringExtension.class)
public class FavouriteAccountCreateServiceTest {

    @TestConfiguration
    static class TestContextConfiguration {

        @Bean
        public MethodValidationPostProcessor methodValidationPostProcessor() {
            return new MethodValidationPostProcessor();
        }

        @Bean
        public FavouriteAccountCreateService favouriteAccountCreateService() {
            return new FavouriteAccountCreateService();
        }
    }

    @MockBean
    private FavouriteAccountRespository favouriteAccountRespository;

    @Autowired
    private FavouriteAccountCreateService favouriteAccountCreateService;
}
