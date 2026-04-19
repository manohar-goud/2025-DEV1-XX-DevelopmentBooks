package com.manohar.developmentbooks.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BasketRequest {

    @Valid
    @NotEmpty
    private final List<Item> basket;
}
