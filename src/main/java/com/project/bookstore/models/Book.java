package com.project.bookstore.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class Book extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    private Float price;

    @Column(name = "photoUrl" , nullable = false, length = 255)
    private String photoUrl;
    
    @Column(name = "author" , nullable = false, length = 255)
    private String author;
    
    @Column(name = "description" , nullable = false, length = 255)
    private String description;
    
    private int sumofproduct;
    
    private Float rating;
    
    private int quantityrating;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
