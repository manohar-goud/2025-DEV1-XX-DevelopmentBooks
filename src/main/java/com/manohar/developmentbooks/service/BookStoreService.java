package com.manohar.developmentbooks.service;

import com.manohar.developmentbooks.model.BasketRequest;
import com.manohar.developmentbooks.repository.BookRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookStoreService {

    private final BookRepository bookRepository;

    public double calculateBasketPrice(BasketRequest basketRequest) {

        basketRequest.getBasket().forEach(item -> bookRepository.findByCode(item.getBookName().name()));
        return 0.0;
    }
}
