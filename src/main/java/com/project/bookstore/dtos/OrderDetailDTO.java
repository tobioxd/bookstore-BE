package com.project.bookstore.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class OrderDetailDTO {

    @JsonProperty("order_id")
    @Min(value = 1, message = "Order's ID must be > 0")
    private long orderid;

    @JsonProperty("book_id")
    @Min(value = 1, message = "Book's ID must be > 0")
    private long bookid;

    @NotNull(message = "Price of book is required")
    @Min(value = 0, message = "Price of book must be >= 0 ")
    private Float price;

    @JsonProperty("num_of_book")
    @NotNull(message = "Number of book is required")
    @Min(value = 1 ,message = "Number of book must be >= 1")
    private int num;

    @Min(value = 0 , message =  "Total money must be  >= 0 ")
    @JsonProperty("total_money")
    private Float totalmoney;

}
