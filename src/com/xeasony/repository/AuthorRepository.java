package com.xeasony.repository;

import com.xeasony.model.Author;
import com.xeasony.repository.base.IRepository;

import java.util.ArrayList;

public class AuthorRepository extends IRepository<Author> {
    public AuthorRepository() {
        super(new ArrayList<>());
    }
}
