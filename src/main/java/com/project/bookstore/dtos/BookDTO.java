package com.project.bookstore.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class BookDTO {

    @NotBlank (message = "Name is required")
    @Size(min = 3, max = 200, message = "Name must be between 3 and 200 characters")
    private String name;

    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0.01", message = "Price must be greater than zero")
    @Max(value = 10000000, message = "Price must be less than 10000000")
    private Float price;
    
    @NotBlank (message = "Photo URL is required")
    private String photourl;
    
    @NotBlank (message = "Author is required")
    private String author;
    
    @NotBlank (message = "Description is required")
    private String description;
    
    @NotNull(message = "Quantity cannot be null")
    private int sumofproduct;
    
    private Float rating = 0.0f;
    
    private int quantityrating;

    @JsonProperty("category_id")
    private Long categoryId;

}
