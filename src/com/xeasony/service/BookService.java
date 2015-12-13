package com.xeasony.service;

import com.xeasony.repository.BookRepository;

public class BookService extends PublicationService {
    public BookService(BookRepository bookRepository, AuthorService authorService) {
        super(bookRepository, authorService);
    }
}
