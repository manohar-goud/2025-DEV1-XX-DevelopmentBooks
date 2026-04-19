package com.manohar.developmentbooks.controller;

import com.manohar.developmentbooks.model.BasketRequest;
import com.manohar.developmentbooks.service.BookStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basket")
@RequiredArgsConstructor
public class BookStoreController {

    private final BookStoreService bookStoreService;

    @PostMapping("/calculatePrice")
    public ResponseEntity<Double> basketPrice(BasketRequest basketRequest) {
        double totalPrice = bookStoreService.calculateBasketPrice(basketRequest);

        return new ResponseEntity<>(totalPrice, HttpStatus.OK);
    }
}
