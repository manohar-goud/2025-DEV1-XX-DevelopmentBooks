package com.manohar.developmentbooks.service;

import com.manohar.developmentbooks.entity.Book;
import com.manohar.developmentbooks.exception.BookNotFoundException;
import com.manohar.developmentbooks.model.BasketRequest;
import com.manohar.developmentbooks.model.Item;
import com.manohar.developmentbooks.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookStoreService {

    private final BookRepository bookRepository;
    private final PricingService pricingService;

    public double calculateBasketPrice(BasketRequest basketRequest) {

        Map<Long, Integer> bookQunatitiesMap = basketRequest.getBasket().stream()
                .collect(Collectors.toMap(item -> findBook(item).getId(),
                        Item::getQuantity,
                        Integer::sum));

        return pricingService.calculatePrice(bookQunatitiesMap);
    }

    private Book findBook(Item item) {
        String code = item.getBookName().name();
        return bookRepository.findByCode(code)
                .orElseThrow(() -> new BookNotFoundException(code));
    }
}