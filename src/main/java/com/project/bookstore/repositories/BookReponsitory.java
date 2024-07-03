package com.project.bookstore.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.bookstore.models.Book;

@Repository
public interface BookReponsitory extends JpaRepository<Book,Long> {

    boolean existsByName(String name);

    @SuppressWarnings("null")
    Page<Book> findAll(Pageable pageable);

}
