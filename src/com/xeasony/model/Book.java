package com.xeasony.model;

import java.util.List;

public class Book extends Publication{
    private String description;

    public Book(String title, String isbn, List<Author> authors, String description) {
        super(title, isbn, authors);
        setDescription(description);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return super.toString() + "Description: '" + description + "\'\n";
    }
}
