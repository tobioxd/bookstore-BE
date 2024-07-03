package com.project.bookstore.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.project.bookstore.dtos.BookDTO;
import com.project.bookstore.exceptions.DataNotFoundException;
import com.project.bookstore.models.Book;
import com.project.bookstore.models.Category;
import com.project.bookstore.repositories.BookReponsitory;
import com.project.bookstore.repositories.CategoryRepository;
import com.project.bookstore.responses.BookResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService implements IBookService {

    private final BookReponsitory bookRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Book createBook(BookDTO bookDTO) throws DataNotFoundException {
        Category category = categoryRepository.findById(bookDTO.getCategoryId())
                .orElseThrow(() -> new DataNotFoundException("Category not found."));

        //System.out.println(category.getName());
                
        Book book = Book.builder()
                .name(bookDTO.getName())
                .price(bookDTO.getPrice())
                .photourl(bookDTO.getPhotourl())
                .author(bookDTO.getAuthor())
                .description(bookDTO.getDescription())
                .sumofproduct(bookDTO.getSumofproduct())
                .rating(bookDTO.getRating())
                .quantityrating(bookDTO.getQuantityrating())
                .build();

        book.setCategory(category);

        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(Long id) throws DataNotFoundException {
        return bookRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Book not found."));
    }

    @Override
    public Page<BookResponse> getAllBooks(PageRequest pageRequest) {
        return bookRepository.findAll(pageRequest).map(book -> {
            BookResponse bookResponse = BookResponse.builder()
                    .name(book.getName())
                    .price(book.getPrice()) 
                    .photourl(book.getPhotourl())
                    .author(book.getAuthor())
                    .description(book.getDescription())
                    .sumofproduct(book.getSumofproduct())
                    .rating(book.getRating())
                    .quantityrating(book.getQuantityrating())
                    .categoryId(book.getCategory().getId())
                    .build();
            // bookResponse.setCreatedAt(book.getCreatedAt());
            // bookResponse.setUpdatedAt(book.getUpdatedAt());
            return bookResponse;
        });
    }

    @Override
    public Book updateBook(Long id, BookDTO bookDTO) throws DataNotFoundException {
        Book existingBook = getBookById(id);
        if(existingBook != null) {
            Category category = categoryRepository.findById(bookDTO.getCategoryId())
                .orElseThrow(() -> new DataNotFoundException("Category not found."));
            
            existingBook.setName(bookDTO.getName());
            existingBook.setPrice(bookDTO.getPrice());
            existingBook.setPhotourl(bookDTO.getPhotourl());
            existingBook.setAuthor(bookDTO.getAuthor());
            existingBook.setDescription(bookDTO.getDescription());
            existingBook.setSumofproduct(bookDTO.getSumofproduct());
            existingBook.setRating(bookDTO.getRating());
            existingBook.setQuantityrating(bookDTO.getQuantityrating());
            existingBook.setCategory(category);

            return bookRepository.save(existingBook);
        }
        return null;
    }

    @Override
    public void deleteBook(Long id) throws DataNotFoundException {
        Optional<Book> book = bookRepository.findById(id);  
        if(book.isPresent()) {
            bookRepository.delete(book.get());
        } else {
            throw new DataNotFoundException("Book not found.");
        }
    }

    @Override
    public boolean existsByName(String name) {
        return bookRepository.existsByName(name);
    }
    
}
