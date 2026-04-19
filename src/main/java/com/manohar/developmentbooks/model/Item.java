package com.manohar.developmentbooks.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item {

    @NotNull
    private BookName bookName;

    @Min(1)
    private Integer quantity;
}
