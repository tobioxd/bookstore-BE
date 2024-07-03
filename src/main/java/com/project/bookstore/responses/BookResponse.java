package com.project.bookstore.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

// public class BookResponse extends BaseResponse{
    public class BookResponse{

    private String name;

    private Float price;
    
    private String photourl;
    
    private String author;
    
    private String description;
    
    private int sumofproduct;
    
    private Float rating;
    
    private int quantityrating;

    @JsonProperty("category_id")
    private Long categoryId;

}
