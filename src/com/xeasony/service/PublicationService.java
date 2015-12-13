package com.xeasony.service;

import com.xeasony.model.Publication;
import com.xeasony.repository.PublicationRepository;
import com.xeasony.service.base.IService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public abstract class PublicationService extends IService<Publication> {
    protected AuthorService authorService;

    protected Comparator<Publication> compareByTitle = (left, right) -> left.getTitle().compareTo(right.getTitle());

    public PublicationService(PublicationRepository publicationRepository, AuthorService authorService) {
        super(publicationRepository);
        this.authorService = authorService;
    }

    public List<Publication> findByISBN(String isbn) {
        return repository.findAll().stream().filter( item -> item.getIsbn().contains(isbn)).collect(Collectors.toList());
    }

    public List<Publication> findByAuthorEmail(String email) {
        return repository.findAll().stream()
                .filter(
                    item -> item.getAuthors().stream().anyMatch(author -> author.getEmail().contains(email))
                )
                .collect(Collectors.toList());

    }

    public void sortByTitle() {
        Collections.sort(repository.findAll(), compareByTitle);
    }
}
