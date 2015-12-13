package com.xeasony.repository;

import com.xeasony.model.Publication;
import com.xeasony.repository.base.IRepository;

import java.util.ArrayList;

public abstract class PublicationRepository extends IRepository<Publication> {
    public PublicationRepository() {
        super(new ArrayList<>());
    }
}
