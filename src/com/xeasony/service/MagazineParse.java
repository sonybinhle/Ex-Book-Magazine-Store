package com.xeasony.service;

import com.xeasony.Container;
import com.xeasony.model.Author;
import com.xeasony.model.Magazine;
import com.xeasony.model.Publication;
import com.xeasony.service.base.IParse;
import com.xeasony.util.Constant;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class MagazineParse implements IParse<Publication> {
    @Override
    public Magazine parseFromLine(String line) {
        AuthorService authorService = Container.getInstance().getAuthorService();
        List<String> records = Arrays.asList(line.split(Constant.SEMICOLON_SEPARATOR));
        List<Author> authors = Arrays.asList(records.get(2).split(Constant.COMMA_SEPARATOR))
                .stream()
                .map((email) -> authorService.add(new Author("", "", email)))
                .collect(Collectors.toList());
        try {
            DateFormat format = new SimpleDateFormat(Constant.DEFAULT_DATE_FORMAT, Locale.GERMAN);
            Date releaseDate = format.parse(records.get(3));
            return new Magazine(records.get(0), records.get(1), authors, releaseDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
