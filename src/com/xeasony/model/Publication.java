package com.xeasony.model;

import java.util.List;
import java.util.Objects;

public abstract class Publication {
    protected String title;
    protected String isbn;
    protected List<Author> authors;

    public Publication(String title, String isbn, List<Author> authors) {
        setTitle(title);
        setIsbn(isbn);
        setAuthors(authors);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publication publication = (Publication) o;
        return Objects.equals(isbn, publication.isbn);
    }

    @Override
    public String toString() {
        return "Title: '" + title + "\'\n" +
                "ISBN: '" + isbn + "\'\n" +
                "Authors: " + authors + "\n";
    }
}
