package com.xeasony.service;

import com.xeasony.repository.MagazineRepository;

public class MagazineService extends PublicationService {
    public MagazineService(MagazineRepository magazineRepository, AuthorService authorService) {
        super(magazineRepository, authorService);
    }
}
