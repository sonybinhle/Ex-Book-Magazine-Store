package com.xeasony;

import com.xeasony.model.Publication;
import com.xeasony.service.*;
import com.xeasony.util.Constant;

import java.util.List;

public class Main {
    private static BookService bookService;
    private static MagazineService magazineService;

    private static void init() {
        //Dependency Injection class
        Container container = Container.getInstance();

        AuthorService authorService = container.getAuthorService();
        authorService.readFromCsvFile(Config.authorFilePath, Constant.EURO_CHARSET, new AuthorParse());

        bookService = container.getBookService();
        bookService.readFromCsvFile(Config.bookFilePath, Constant.EURO_CHARSET, new BookParse());

        magazineService = container.getMagazineService();
        magazineService.readFromCsvFile(Config.magazineFilePath, Constant.EURO_CHARSET, new MagazineParse());
    }

    private static void printPublications(List<Publication> publications) {
        publications.stream().forEach(System.out::println);
    }

    private static void printBooksAndMagazines() {
        System.out.println("-------Print all Books and Magazines-------\n");
        System.out.println("***All Books***\n");
        printPublications(bookService.findAll());

        System.out.println("***All Magazines***\n");
        printPublications(magazineService.findAll());
    }

    private static void findBooksAndMagazinesByISBN(String isbn) {
        List<Publication> books = bookService.findByISBN(isbn);
        List<Publication> magazines = magazineService.findByISBN(isbn);
        int total = books.size() + magazines.size();

        if (total > 0 ) {
            System.out.format("-------There are %d publication found with isbn=%s-------\n\n", total, isbn);
            printPublications(books);
            printPublications(magazines);
        } else {
            System.out.format("-------There are no publication found with isbn=%s-------\n\n", isbn);
        }
    }

    private static void findBooksAndMagazinesByAuthorEmail(String email) {
        List<Publication> books = bookService.findByAuthorEmail(email);
        List<Publication> magazines = magazineService.findByAuthorEmail(email);
        int total = books.size() + magazines.size();

        if (total > 0 ) {
            System.out.format("-------There are %d publication found with author_email=%s-------\n\n", total, email);
            printPublications(books);
            printPublications(magazines);
        } else {
            System.out.format("-------There are no publication found with author_email=%s-------\n\n", email);
        }
    }

    public static void sortAllBooksAndMagazinesByTitle() {
        bookService.sortByTitle();
        magazineService.sortByTitle();

        System.out.println("-------Print all sorted Books and Magazines-------\n");
        System.out.println("***All Books***\n");
        printPublications(bookService.findAll());

        System.out.println("***All Magazines***\n");
        printPublications(magazineService.findAll());
    }

    public static void main(String[] args) {
        init();

        printBooksAndMagazines();

        findBooksAndMagazinesByISBN("1313-4545-8875");
        findBooksAndMagazinesByISBN("545");
        findBooksAndMagazinesByISBN("5456");

        findBooksAndMagazinesByAuthorEmail("mueller");
        findBooksAndMagazinesByAuthorEmail("pr-gustafsson@optivo.de");
        findBooksAndMagazinesByAuthorEmail("pr-gustafsson@optivo.dee");

        sortAllBooksAndMagazinesByTitle();
    }
}
