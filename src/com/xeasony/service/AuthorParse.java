package com.xeasony.service;

import com.xeasony.model.Author;
import com.xeasony.service.base.IParse;
import com.xeasony.util.Constant;

import java.util.Arrays;
import java.util.List;

public final class AuthorParse implements IParse<Author> {
    @Override
    public Author parseFromLine(String line) {
        List<String> record = Arrays.asList(line.split(Constant.SEMICOLON_SEPARATOR));
        return new Author(record.get(1), record.get(2), record.get(0));
    }
}
