package com.xeasony.service;

import com.xeasony.Container;
import com.xeasony.model.Author;
import com.xeasony.model.Book;
import com.xeasony.model.Publication;
import com.xeasony.service.base.IParse;
import com.xeasony.util.Constant;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BookParse implements IParse<Publication> {
    @Override
    public Book parseFromLine(String line) {
        AuthorService authorService = Container.getInstance().getAuthorService();
        List<String> records = Arrays.asList(line.split(Constant.SEMICOLON_SEPARATOR));
        List<Author> authors = Arrays.asList(records.get(2).split(Constant.COMMA_SEPARATOR))
                .stream()
                .map((email) -> authorService.add(new Author("", "", email)))
                .collect(Collectors.toList());
        return new Book(records.get(0), records.get(1), authors, records.get(3));
    }
}
