package com.manohar.developmentbooks.service;

import com.manohar.developmentbooks.entity.Book;
import com.manohar.developmentbooks.exception.BookNotFoundException;
import com.manohar.developmentbooks.model.BasketRequest;
import com.manohar.developmentbooks.model.BookName;
import com.manohar.developmentbooks.model.Item;
import com.manohar.developmentbooks.repository.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookStoreServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private PricingService pricingService;

    @InjectMocks
    private BookStoreService bookStoreService;

    @Test
    @DisplayName("Each book code is looked up in the repository by its enum value")
    void calculateBasketPriceShouldResolveEachBookByCode() {
        when(bookRepository.findByCode("CLEAN_CODE"))
                .thenReturn(Optional.of(book(1L, "CLEAN_CODE")));
        when(bookRepository.findByCode("THE_CLEAN_CODER"))
                .thenReturn(Optional.of(book(2L, "THE_CLEAN_CODER")));

        bookStoreService.calculateBasketPrice(frameRequest(
                item(BookName.CLEAN_CODE, 1),
                item(BookName.THE_CLEAN_CODER, 1)
        ));

        verify(bookRepository).findByCode("CLEAN_CODE");
        verify(bookRepository).findByCode("THE_CLEAN_CODER");
    }

    @Test
    @DisplayName("Unknown book code should throw BookNotFoundException")
    void calculateBasketPriceShouldThrowBookNotFoundException() {
        when(bookRepository.findByCode("CLEAN_CODE")).thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                bookStoreService.calculateBasketPrice(frameRequest(item(BookName.CLEAN_CODE, 1)))
        )
                .isInstanceOf(BookNotFoundException.class)
                .hasMessageContaining("CLEAN_CODE");
    }

    @Test
    @DisplayName("Quantities are passed to PricingService as { bookId → quantity }")
    void calculateBasketPriceWhenPassedCorrectQuantityMapToPricingService() {
        when(bookRepository.findByCode("CLEAN_CODE"))
                .thenReturn(Optional.of(book(1L, "CLEAN_CODE")));
        when(bookRepository.findByCode("THE_CLEAN_CODER"))
                .thenReturn(Optional.of(book(2L, "THE_CLEAN_CODER")));
        when(bookRepository.findByCode("CLEAN_ARCHITECTURE"))
                .thenReturn(Optional.of(book(3L, "CLEAN_ARCHITECTURE")));

        @SuppressWarnings("unchecked")
        ArgumentCaptor<Map<Long, Integer>> captor = ArgumentCaptor.forClass(Map.class);

        bookStoreService.calculateBasketPrice(frameRequest(
                item(BookName.CLEAN_CODE, 2),
                item(BookName.THE_CLEAN_CODER, 1),
                item(BookName.CLEAN_ARCHITECTURE, 3)
        ));

        verify(pricingService).calculatePrice(captor.capture());
        assertThat(captor.getValue()).containsExactlyInAnyOrderEntriesOf(
                Map.of(1L, 2, 2L, 1, 3L, 3)
        );
    }

    private BasketRequest frameRequest(Item... items) {
        return new BasketRequest(List.of(items));
    }

    private Item item(BookName bookName, int quantity) {
        return new Item(bookName, quantity);
    }

    private static Book book(Long id, String code) {
        Book book = new Book(code, code);
        try {
            var idField = Book.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(book, id);
        } catch (Exception e) {
            throw new RuntimeException("Could not set Book id in test", e);
        }
        return book;
    }
}
