package com.xeasony.model;

import java.util.Date;
import java.util.List;

public class Magazine extends Publication{
    private Date releaseDate;

    public Magazine(String title, String isbn, List<Author> authors, Date releaseDate) {
        super(title, isbn, authors);
        setReleaseDate(releaseDate);
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return super.toString() + "Release Date: '" + releaseDate + "\'\n";
    }
}
