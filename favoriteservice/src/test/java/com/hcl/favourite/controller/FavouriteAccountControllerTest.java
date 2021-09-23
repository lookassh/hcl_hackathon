package com.hcl.favourite.controller;

import com.hcl.favourite.domain.FavouriteAccount;
import com.hcl.favourite.repository.FavouriteAccountRespository;
import com.hcl.favourite.service.FavouriteAccountCreateService;
import com.hcl.favourite.service.FavouriteAccountListService;
import com.hcl.favourite.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Valid;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FavouriteAccountController.class)
public class FavouriteAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private FavouriteAccountRespository favouriteAccountRespository;

    @MockBean
    private FavouriteAccountListService favouriteAccountListService;

    @TestConfiguration
    static class TestContextConfiguration {

        @Bean
        public MethodValidationPostProcessor methodValidationPostProcessor() {
            return new MethodValidationPostProcessor();
        }

        @Bean
        public FavouriteAccountCreateService favouriteAccountCreateService() {
            return new FavouriteAccountCreateService(){
                @Override
                public FavouriteAccount create(@Valid FavouriteAccount account) {
                    account.setId(1l);
                    account.setStatus(FavouriteAccount.Status.VALIDATION);
                    return account;
                }
            };
        }
    }

    @Test
    public void testUnauthorizedNoHeader() throws Exception {
        Mockito.when(userService.isValidUser(Mockito.anyLong())).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/favourites")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{ \"iban\": \"ES1111111111111111\",\"name\": \"Account 1\"}")).andDo(print()).andExpect(status().isUnauthorized());
    }

    @Test
    public void testUnauthorizedInvalidUser() throws Exception {
        Mockito.when(userService.isValidUser(Mockito.anyLong())).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/favourites")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{ \"iban\": \"ES1111111111111111\",\"name\": \"Account 1\"}").header("User-Id", 1)).andDo(print()).andExpect(status().isUnauthorized());
    }

    @Test
    public void testCreateNewFavouriteAccount() throws Exception {
        Mockito.when(userService.isValidUser(Mockito.anyLong())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/favourites")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{ \"iban\": \"ES1111111111111111\",\"favName\": \"Account 1\"}").header("User-Id", 1)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1, \"iban\": \"ES1111111111111111\",\"favName\": \"Account 1\", \"bankName\":null, \"status\":\"VALIDATION\"}", true));
    }
}
