package com.programacion.distribuida.books.servicios;

import com.programacion.distribuida.books.db.Book;
import com.programacion.distribuida.books.db.Inventory;
import com.programacion.distribuida.books.dto.BookDto;
import com.programacion.distribuida.books.repo.BookRepository;
import com.programacion.distribuida.books.repo.InventoryRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;

import java.util.List;

@ApplicationScoped
public class ServicioBooks {

    @Inject
    BookRepository bookRepository;

    @Inject
    InventoryRepository inventoryRepository;

    @Inject
    ModelMapper modelMapper;

    @Transactional
    public void createBook(BookDto bookDto) {
        Book book = modelMapper.map(bookDto, Book.class);
        bookRepository.persist(book);

        Inventory inventory = new Inventory();
        inventory.setBook(book);
        inventory.setSold(bookDto.getInventorySold());
        inventory.setSupplied(bookDto.getInventorySupplied());
        inventoryRepository.persist(inventory);
    }

    @Transactional
    public void updateBook(String isbn, BookDto bookDto) {
        bookRepository.findByIdOptional(isbn).ifPresent(book -> {
            book.setTitle(bookDto.getTitle());
            book.setPrice(bookDto.getPrice());

            inventoryRepository.findByIdOptional(isbn).ifPresent(inventory -> {
                inventory.setSold(bookDto.getInventorySold());
                inventory.setSupplied(bookDto.getInventorySupplied());
            });
        });
    }

    @Transactional
    public void deleteBook(String isbn) {
        bookRepository.deleteById(isbn);
    }

    public BookDto findBookById(String isbn) {
        Book book = bookRepository.findById(isbn);
        return modelMapper.map(book, BookDto.class);
    }

    public List<BookDto> findAllBooks() {
        return bookRepository.streamAll()
                .map(book -> modelMapper.map(book, BookDto.class))
                .toList();
    }

}
