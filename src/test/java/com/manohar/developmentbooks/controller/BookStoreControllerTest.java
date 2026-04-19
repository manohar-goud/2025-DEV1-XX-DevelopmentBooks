package com.manohar.developmentbooks.controller;

import com.manohar.developmentbooks.service.BookStoreService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookStoreController.class)
public class BookStoreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookStoreService bookStoreService;

    @Test
    @DisplayName("POST /basket/calculatePrice returns 200")
    void calculatePriceShouldReturn200WithPrice() throws Exception {
        when(bookStoreService.calculateBasketPrice(any())).thenReturn(320.0);

        mockMvc.perform(post("/basket/calculatePrice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "basket": [
                                    { "bookName": "CLEAN_CODE",                             "quantity": 2 },
                                    { "bookName": "THE_CLEAN_CODER",                        "quantity": 2 },
                                    { "bookName": "CLEAN_ARCHITECTURE",                     "quantity": 2 },
                                    { "bookName": "TEST_DRIVEN_DEVELOPMENT_BY_EXAMPLE",     "quantity": 1 },
                                    { "bookName": "WORKING_EFFECTIVELY_WITH_LEGACY_CODE",   "quantity": 1 }
                                  ]
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.basketPrice").value(320.0));
    }
}
