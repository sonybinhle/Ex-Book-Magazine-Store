package com.xeasony.service;

import com.xeasony.model.Author;
import com.xeasony.repository.AuthorRepository;
import com.xeasony.service.base.IService;

public class AuthorService extends IService<Author> {
    public AuthorService(AuthorRepository authorRepository) {
        super(authorRepository);
    }
}
