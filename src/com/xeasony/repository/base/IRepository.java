package com.xeasony.repository.base;

import java.util.List;

public abstract class IRepository<T> {
    private List<T> items;

    public IRepository(List<T> items) {
        this.items = items;
    }

    public T find(T entity) {
        return items.stream()
                .filter(item -> item.equals(entity))
                .findFirst()
                .orElse(null);
    }

    public List<T> findAll() {
        return items;
    }

    public T add(T entity) {
        T existed = find(entity);

        if (existed == null) {
            this.items.add(entity);
            return entity;
        } else {
            return existed;
        }
    }
}
